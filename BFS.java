import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class BFS 
{
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	private Point currPos;
	private ArrayList<Point> visitedPoint;
	//private ArrayList<ArrayList<Point>> pathways;
	private ArrayList<Point> queuePoint;
	private Scanner read;
	public Maze mazeUsed;
	
	public enum direction
	{
		Up,
		Right,
		Down,
		Left
	}
	
	public BFS(String filename) throws FileNotFoundException
	{
		int row, col, count;
		read = new Scanner(new File(filename));
		
		/*Menghitung jumlah row*/
		row = 0;
		while(read.hasNext())
		{
			row++;
			read.nextLine();
		}
		read.reset();
		read = new Scanner(new File(filename));
		count = 0;
		while(read.hasNext())
		{
			count+=read.next().length();
		}
		col = count/row;
		mazeUsed = new Maze(row,col);
		mazeUsed.readMaze(filename);
		
		currPos = new Point(mazeUsed.start[1], mazeUsed.start[0]);
		
		queuePoint = new ArrayList<Point>();
		visitedPoint = new ArrayList<Point>();
		
		
	}
	/*
	 * Mencari jalan keluar dengan menggunakan BFS
	 * Akan mengeluarkan semua kemungkinan jalan dan disimpan pada pathways
	 */
	public void findPath()
	{
		//CurrPos diset di awal maze
		int intersect;
		Point temp;	//temp yang digunakan untuk meproses Point-point yang bisa dilalui
		intersect = 0;
		setMazeEl(currPos.x,currPos.y,3);	//calon jalan menuju finish
		visitedPoint.add(currPos);
		queuePoint.add(currPos);
		
		while(!(queuePoint.isEmpty()))
		{
			currPos = queuePoint.get(0);
			queuePoint.remove(0);
			//System.out.println("Posisi sekarang: "+currPos.x + " , " + currPos.y);
			if(!isFinish(currPos))
			{
				if(!isStuck(currPos))
				{
					//System.out.print("Masuk sini\n");
					for(direction d : direction.values())
					{
						if(possibleMove(d,currPos))
						{
							temp = getDir(d,currPos);
							setMazeEl(temp.x,temp.y,3);
							visitedPoint.add(temp);
							queuePoint.add(temp);
							intersect++;
						}
					}
					if(intersect > 1)
					{
						setMazeEl(currPos.x,currPos.y,4);		//artinya merupakan intersection	
					}
					intersect = 0;
				}
				else
				{
					/*
					System.out.println("Masuk else");
					System.out.println("Posisi sekarang: "+currPos.x + " , " + currPos.y);
					*/
					deletePath(currPos);
				}
			}
			//drawMaze();
			
		}
	}
	
	public void drawMaze()
	{
		for(int i = 0;i< mazeUsed.row; i++)
		{
			for(int j = 0;j<mazeUsed.col;j++)
			{
				if(mazeUsed.m[i][j] == 1)
				{
					System.out.print("\u25FC");
				} else if(mazeUsed.m[i][j] == 0)
				{
					System.out.print(ANSI_WHITE+"\u25FB"+ANSI_RESET);
				} else if(mazeUsed.m[i][j] == 3)
				{
					System.out.print(ANSI_GREEN + "\u25FC" + ANSI_RESET);
				} else if(mazeUsed.m[i][j] == 2)
				{
					System.out.print(ANSI_RED+"\u25FC"+ANSI_RESET);
				}
				else
				{
					System.out.print(mazeUsed.m[i][j]);
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private void deletePath(Point stuckPos)
	{
		Point temp = null; 
		while(getMazeEl(stuckPos.x,stuckPos.y) != 4)
		{
			setMazeEl(stuckPos.x,stuckPos.y,getMazeEl(stuckPos.x,stuckPos.y) - 1);
			for(direction d : direction.values())
			{
				if(getElDir(d,stuckPos) != 1 && getElDir(d,stuckPos) != 2)
				{
					//System.out.println(d);
					temp = getDir(d,stuckPos);
					
				}
			}
			stuckPos.setPoint(temp.x, temp.y);
			//drawMaze();
		}
		setMazeEl(stuckPos.x,stuckPos.y,getMazeEl(stuckPos.x,stuckPos.y) - 1);
	}
	
	/*
	 * fungsi look untuk melihat jika dapat pergi ke petak atau tidak
	 * return true jika petak pada arah berikutnya 
	 */
	private boolean possibleMove(direction d, Point currPoint)
	{
		boolean possible;
		possible = false;
		if(d == direction.Up)
		{
			//System.out.println(getMazeEl(currPoint.x, currPoint.y - 1));
			if(getMazeEl(currPoint.x, currPoint.y - 1) == 0)
			{
				if(!visitedPoint.contains(getDir(d,currPoint)))
				{
					//System.out.println(d);
					possible = true;
				}
			}
		} 
		else if(d == direction.Down)
		{
			//System.out.println(getMazeEl(currPoint.x, currPoint.y + 1));
			if(getMazeEl(currPoint.x, currPoint.y + 1) == 0)
			{
				if(!visitedPoint.contains(getDir(d,currPoint)))
				{
					//System.out.println(d);
					possible = true;
				}
			}
		} 
		else if(d == direction.Left)
		{
			//System.out.println(getMazeEl(currPoint.x - 1, currPoint.y));
			if(getMazeEl(currPoint.x - 1, currPoint.y) == 0)
			{
				if(!visitedPoint.contains(getDir(d,currPoint)))
				{
					//System.out.println(d);
					possible = true;
				}
			}
		} 
		else if(d == direction.Right)
		{
			//System.out.println(getMazeEl(currPoint.x + 1, currPoint.y));
			if(getMazeEl(currPoint.x + 1, currPoint.y) == 0)
			{
				if(!visitedPoint.contains(getDir(d,currPoint)))
				{
					//System.out.println(d);
					possible = true;
				}
			}
		}
		//System.out.println(d + " " + possible);
		return possible;	
	}
	
	private boolean isStuck(Point p)
	{
		boolean stuck;
		stuck = false;
		int count;
		count = 0;
		
		for(direction d : direction.values())
		{
			if(getElDir(d,p) != 0)
			{
				count++;
			}
		}
		/*
		System.out.print("Posisi sekarang: "+currPos.x + " , " + currPos.y);
		System.out.println(" Count skrg " + count);
		*/
		stuck = count == 4;

		return stuck;
	}
	
	
	
	private boolean isFinish(Point p)
	{
		Point finish = getFinish();
		return p.x == finish.x && p.y == finish.y;
	}
	
	private Point getFinish()
	{
		return new Point(mazeUsed.finish[1], mazeUsed.finish[0]);
	}
	
	/*
	 * Mendapatkan point sesuai direction yang dapat dilalui
	 */
	private Point getDir(direction d, Point p)
	{
		if(d == direction.Up)
		{
			return new Point(p.x,p.y-1);
		}
		else if(d == direction.Down)
		{
			return new Point(p.x,p.y+1);
		}
		else if(d == direction.Right)
		{
			return new Point(p.x+1,p.y);
		}
		else if(d == direction.Left)
		{
			return new Point(p.x-1,p.y);
		}
		else
		{
			return null;
		}
		
	}
	/*
	 * mendapatkan isi maze sesuai direction dengan p sebagai posisi
	 * pada saat ini.
	 */
	private int getElDir(direction d, Point p)
	{
		if(d == direction.Up)
		{
			return getMazeEl(p.x,p.y-1);
		}
		else if(d == direction.Down)
		{
			return getMazeEl(p.x,p.y+1);
		}
		else if(d == direction.Right)
		{
			return getMazeEl(p.x+1,p.y);
		}
		else if(d == direction.Left)
		{
			return getMazeEl(p.x-1,p.y);
		}
		else
		{
			return 0;
		}
	}
	
	
	public int getMazeEl(int x,int y)
	{
		if(x>=0 && x<mazeUsed.col && y>=0 && y<mazeUsed.row)
		{
			return mazeUsed.m[y][x];
		}
		else
		{
			return -1;
		}
	}
	
	public void setMazeEl(int x, int y, int el)
	{
		mazeUsed.m[y][x] = el;
	}
	
	class Point
	{
		int x;
		int y;
		
		Point(int x,int y)
		{
			this.x = x;
			this.y = y;
		}
		
		void setPoint(int x,int y)
		{
			this.x = x;
			this.y = y;
		}
	}
}


