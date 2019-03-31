import java.util.*;
import java.io.*;
import java.lang.Math;

public class Maze
{
    public int row;
    int col;
    public int[] start;
    public int[] finish;
    public int[][] m;

    public Maze(int x, int y)
    {
        this.row = x;
        this.col = y;
        m = new int[x][y];
        start = new int[2];
        finish = new int[2];
    }
    
    public void readMaze(String filename) throws FileNotFoundException
    {
        Scanner reader = new Scanner(new File(filename));
        String currString = reader.next();
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
            		m[i][j] = Character.getNumericValue(currString.charAt(j));
            }
            if(reader.hasNext())
            {
            		currString = reader.next();
            }
        }
        reader.close();

        for (int i = 0; i < this.row; i++)
        {
            if (m[i][0] == 0)
            {
            		this.start[0] = i;
            		this.start[1] = 0;
            }
        }

        for (int i = 0; i < this.row; i++)
        {
            if (m[i][col-1] == 0)
            {
            		this.finish[0] = i;
            		this.finish[1] = col-1;
            }
        }
    }
}