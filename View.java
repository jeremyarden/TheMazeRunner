import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class View extends JFrame{
	
	private BFS bfs;
	private Maze a;
	public View() throws FileNotFoundException
	{
		bfs = new BFS("/Users/abiyyuismunandar/Documents/Stima/MazeRunner/TheMazeRunner-master/MazeRunner/test/Maze_med.txt");
		bfs.findPath();
		a = new Maze(bfs.mazeUsed);
		setTitle("Maze");
		setSize(800,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
        
        g.translate(50, 50);
        
        // draw the maze
        for (int row = 0; row < a.m.length; row++) {
            for (int col = 0; col < a.m[0].length; col++) {
                Color color;
                switch( a.m[row][col]) {
                    case 1 : color = Color.BLACK; break;
                    case 2 : color = Color.RED; break;
                    case 3 : color = Color.GREEN; break;
                    case 9 : color = Color.RED; break;
                    default : color = Color.WHITE;
                }
                g.setColor(color);
                g.fillRect(30 * col, 30 * row, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(30 * col, 30 * row, 30, 30);
            }
        }
				
	}
		
	
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		/*
		System.out.println("Sebelum BFS: ");
		a.drawMaze();
		a.findPath();
		System.out.println("Sesudah BFS: ");
		a.drawMaze();
		
		System.out.println("done");
		*/
		SwingUtilities.invokeLater(
				new Runnable() {
					@Override
					public void run()
					{
						try {
							View view = new View();
							view.setVisible(true);
							
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
				);
	}
	

}
