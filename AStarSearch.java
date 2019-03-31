import java.util.*;
import java.io.*;

public class AStarSearch
{
    PriorityQueue<Point> openList;
    private List<Point> closedList;
    private Point current;
    
    public AStarSearch(Maze m)
    {
        openList = new PriorityQueue<Point>((m.row*m.col), new HComparator());
        closedList = new ArrayList<Point>();
        current = new Point(m.start[0], m.start[1], m);
    }
    public void solve(Maze m)
    {
        generateMoves(m, current);
        Point p = new Point(current.x, current.y+1, m);
        if (openList.contains(p))
            System.out.println("anjing");
        System.out.println("kontol");
        System.out.println(m.start[0]);
        System.out.println(m.start[1]);
        System.out.println(m.finish[0]);
        System.out.println(m.finish[1]);
        /*while (current.x != m.finish[0] && current.y != m.finish[1])
        {
            System.out.println("pantek");
            closedList.add(current);
            current = openList.poll();
            System.out.println(current.x+","+current.y);
            generateMoves(m, current);
        }*/
    }
    private void generateMoves(Maze m, Point current)
    {
        if (Up(m, current) != -1 && !(openList.contains(current)) && !(closedList.contains(current)))
            openList.add(new Point(current.x-1, current.y, m));
        if (Down(m, current) != -1 && !(openList.contains(current)) && !(closedList.contains(current)))
            openList.add(new Point(current.x+1, current.y, m));
        if (Left(m, current) != -1 && !(openList.contains(current)) && !(closedList.contains(current)))
            openList.add(new Point(current.x, current.y-1, m));
        if (Right(m, current) != -1 && !(openList.contains(current)) && !(closedList.contains(current)))
            openList.add(new Point(current.x, current.y+1, m));
    }
    private int Up(Maze m, Point current)
    {
        if (current.x - 1 >= 0)
        {
            if (m.m[current.x-1][current.y] == 0)
            {
                return (m.m[current.x-1][current.y]);
            }
        }
        return (-1);
    }
    private int Left(Maze m, Point current)
    {
        if (current.y - 1 >= 0)
        {
            if (m.m[current.x][current.y-1] == 0)
            {
                return (m.m[current.x][current.y-1]);
            }
        }
        return (-1);
    }
    private int Down(Maze m, Point current)
    {
        if (current.x + 1 >= 0)
        {
            if (m.m[current.x+1][current.y] == 0)
            {
                return (m.m[current.x+1][current.y]);
            }
        }
        return (-1);
    }
    private int Right(Maze m, Point current)
    {
        if (current.y + 1 >= 0)
        {
            if (m.m[current.x][current.y+1] == 0)
            {
                return (m.m[current.x][current.y+1]);
            }
        }
        return (-1);
    }
    class HComparator implements Comparator<Point>
    {
        public int compare (Point p1, Point p2)
        {
            if (p1.f > p2.f)
            {
                return 1;
            }
            else if (p1.f < p2.f)
            {
                return -1;
            }
            return 0;
        }
    }
    private class Point
    {
		int x;
        int y;
        int f;
        int g = 1;
        int h;
		
		Point(int x,int y, Maze m)
		{
			this.x = x;
            this.y = y;
            this.h = Math.abs(this.x - m.finish[0]) + Math.abs(this.y - m.finish[1]);
            this.f = g + h;
		}
		
		void setPoint(int x,int y)
		{
			this.x = x;
			this.y = y;
        }
        public boolean equals(Point p)
        {
            if (this == p)
                return true;
            return false;
        }
	}
}