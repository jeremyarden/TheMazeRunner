import java.util.Scanner;
import java.io.*;

class MazeRunner
{
    public static void main(String[] args)
    {
        Maze maz = new Maze(11,11);

        try
        {
            maz.readMaze("tes.txt");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        AStarSearch astar = new AStarSearch(maz);
        astar.solve(maz);
    }
}