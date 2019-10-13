import javax.swing.*                 ;
import java.awt.Container            ;
import java.awt.BorderLayout         ;
import java.awt.event.ActionEvent    ;
import java.awt.event.ActionListener ;
import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.lang.*;

public class NURunner extends JFrame{

	public Display display;
	public Common common;

	public NURunner(int numberOfStudents, int windowWidth, int windowHeight){
		setTitle("NUGraduation by Sherkhan Azimov");
		Container cp = getContentPane();
		display = new Display(numberOfStudents, windowWidth, windowHeight);
		// common =  new Common(numberOfStudents);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(display);
		pack();
	}	

	public static void main( String[] args ){
		System.out.println("Optional command line arguments: [NumStudents] [WindowWidth] [WindowHeight]");
		int numberOfStudents = 10, windowWidth = 800, windowHeight = 400;

		if (args.length > 2) windowHeight = Integer.parseInt(args[2]);
		if (args.length > 1) windowWidth = Integer.parseInt(args[1]);
		if (args.length > 0) numberOfStudents = Integer.parseInt(args[0]);
		NURunner nu = new NURunner(numberOfStudents, windowWidth, windowHeight);
		while(true){
			nu.setVisible(true);
			nu.repaint();
			try {
            Thread.sleep(15);
	        } catch (Exception e) {
	            System.out.println(e);
	        }
		}
	}
}