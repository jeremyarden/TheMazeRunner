import java.util.*;
import java.io.*;

class Point
{
    int x;
    int y;
    int f;
    int g = 1;
    int h;
    
    Point(int x,int y)
    {
        this.x = x;
        this.y = y;
        this.h = 0;
        this.f = g + h;
    }
    public void setH(Maze m)
    {
        h = Math.abs(this.x - m.finish[0]) + Math.abs(this.y - m.finish[1]);
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Point p = (Point) obj;
        return (this.x == p.x && this.y == p.y && this.f == p.f);
    }
}

public class AStarSearch
{
    PriorityQueue<Point> openList;
    private ArrayList<Point> closedList;
    private Point current;
    
    public AStarSearch(Maze m)
    {
        openList = new PriorityQueue((m.row*m.col), new HComparator());
        closedList = new ArrayList<Point>();
        current = new Point(m.start[0], m.start[1]);
        current.setH(m);
    }
    public Maze solve(Maze m)
    {
        Maze ansMaze = new Maze(m.row, m.col);
        for (int i = 0; i < m.row; i++)
        {
            for (int j = 0; j < m.col; j++)
            {
                ansMaze.m[i][j] = m.m[i][j];
            }
        }
        ansMaze.start = m.start;
        ansMaze.finish = m.finish;

        generateMoves(m, current);
        while (current.x != m.finish[0] || current.y != m.finish[1])
        {
            closedList.add(current);
            current = openList.poll();
            current.setH(m);
            System.out.println(current.x+","+current.y);
            generateMoves(m, current);
        }

        return ansMaze;
    }
    private void generateMoves(Maze m, Point current)
    {
        if (Up(m, current) != -1 && !(openList.contains(new Point(current.x-1, current.y))) && !(closedList.contains(new Point(current.x-1, current.y))))
        {
            Point p = new Point(current.x-1, current.y);
            p.setH(m);
            openList.add(p);
        }
        if (Down(m, current) != -1 && !(openList.contains(new Point(current.x+1, current.y))) && !(closedList.contains(new Point(current.x+1, current.y))))
        {
            Point p = new Point(current.x+1, current.y);
            p.setH(m);
            openList.add(p);
        }
        if (Left(m, current) != -1 && !(openList.contains(new Point(current.x, current.y-1))) && !(closedList.contains(new Point(current.x, current.y-1))))
        {
            Point p = new Point(current.x, current.y-1);
            p.setH(m);
            openList.add(p);
        }
        if (Right(m, current) != -1 && !(openList.contains(new Point(current.x, current.y+1))) && !(closedList.contains(new Point(current.x, current.y+1))))
        {
            Point p = new Point(current.x, current.y+1);
            p.setH(m);
            openList.add(p);
        }
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
        if (current.x + 1 < m.row)
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
        if (current.y + 1 < m.col)
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
}