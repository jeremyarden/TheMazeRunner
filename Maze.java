import java.util.*;
import java.io.*;
import java.lang.Math;

public class Maze
{
    int row;
    int col;
    int[] start;
    int[] finish;
    int[][] m;

    public Maze(int x, int y)
    {
        this.row = x;
        this.col = y;
        int[][] m = new int[x][y];
        int[] start = new int[2];
        int[] finish = new int[2];
    }
    
    public void readMaze(String filename) throws FileNotFoundException
    {
        Scanner reader = new Scanner(new File(filename));

        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if (reader.hasNextInt())
                {
                    m[i][j] = reader.nextInt();
                }
            }
        }

        for (int i = 0; i < row; i++)
        {
            if (m[i][0] == 0)
            {
            		start[0] = i;
            		start[1] = 0;
            }
        }

        for (int i = 0; i < row; i++)
        {
            if (m[i][col-1] == 0)
            {
            		finish[0] = i;
            		finish[1] = col-1;
            }
        }
    }
}
