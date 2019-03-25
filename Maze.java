import java.util.*;
import java.io.*;
import java.lang.Math;

public class Maze
{
    int row;
    int col;
    int[] start;
    int[] finish;
    int dist;
    int[][] m;

    public Maze(int x, int y)
    {
        this.row = x;
        this.col = y;
        int[][] m = new int[x][y];
        int[] start = new int[2];
        int[] finish = new int[2];
        this.dist = 0;
    }
    public void readMaze(string filename)
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
            if (a[0][i] == 0)
            {
                start[0] = 0;
                start[1] = i;
            }
        }

        for (int i = 0; i < x; i++)
        {
            if (a[col-1][i] == 0)
            {
                finish[0] = col-1;
                finish[1] = i;
            }
        }
        
        this.dist = Math.abs(start[0] - finish[0]) + Math.abs(start[1] - finish[1]);
    }
}