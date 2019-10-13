import java.awt.FontMetrics;
import java.awt.Paint;
import java.awt.geom.AffineTransform;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.swing.*;

public abstract class Assessment extends Entity{
	
	public int points;
	
	public Assessment(String name, Vector2D position, State state, Common common, int points){
		super(name, position, state, common);
		this.points = points;
	}
		
	public abstract void draw ( Graphics2D g2d );
}

class Lab extends Assessment{
	public Lab(String name, Vector2D position, State state, Common common, int points){
		super(name, position, state, common, points);
	}

	public void draw( Graphics2D g2d ){
		Font            fOriginal = g2d.getFont       () ;  // Save the default font of the g2d object
	    FontMetrics     fm        = g2d.getFontMetrics() ;  // Get a font metrics object that will help us calculate the width of strings in pixels
	    AffineTransform tOriginal = g2d.getTransform  () ;  // Save the default affine transformation of the g2d object (default coordinate axes)
	    String          str                              ;  // Just some string reference variable

	    g2d.setFont( new Font( "Sans Serif" , Font.BOLD , 14 ) ) ;  // Change the font of the g2d object to some nice, bold, larger font

	    g2d.translate( (int) this.position.x , (int) this.position.y ) ;  // Modify (translate) the default coordinate axes, origin is now at the center of the Student object

	    g2d.setPaint(Color.RED);
	    g2d.fillOval( -9, -9 , 18, 18 ) ;  // Draw the filled oval (body of student)

	    g2d.setPaint(Color.BLACK);
	    str = new Integer(this.points).toString();
	    g2d.drawString(str, (int)(-fm.stringWidth(str) / 2.0) + 1, 6);

	    g2d.setTransform( tOriginal ) ;  // Restore the saved affine transformation (restore default coordinate axes)
	    g2d.setFont     ( fOriginal ) ;  // Restore the default font
	}

}


class Quiz extends Assessment{
	public Quiz(String name, Vector2D position, State state, Common common, int points){
		super(name, position, state, common, points);
	}

	public void draw( Graphics2D g2d){
		Font            fOriginal = g2d.getFont       () ;  // Save the default font of the g2d object
	    FontMetrics     fm        = g2d.getFontMetrics() ;  // Get a font metrics object that will help us calculate the width of strings in pixels
	    AffineTransform tOriginal = g2d.getTransform  () ;  // Save the default affine transformation of the g2d object (default coordinate axes)
	    String          str                              ;  // Just some string reference variable

	    g2d.setFont( new Font( "Sans Serif" , Font.BOLD , 14 ) ) ;  // Change the font of the g2d object to some nice, bold, larger font

	    g2d.translate( (int) this.position.x , (int) this.position.y ) ;  // Modify (translate) the default coordinate axes, origin is now at the center of the Student object

	    g2d.setPaint(Color.BLUE);
	    g2d.fillPolygon(new int[] {0, 10, -10}, new int[] {-10, 10, 10} , 3);

	    g2d.setPaint(Color.BLACK);
	    str = points + "";
	    g2d.drawString(str, (int)(-fm.stringWidth(str) / 2.0) + 1, 6);

	    g2d.setTransform( tOriginal ) ;  // Restore the saved affine transformation (restore default coordinate axes)
	    g2d.setFont     ( fOriginal ) ;  // Restore the default font
	}
}

class Homework extends Assessment{
	public Homework(String name, Vector2D position, State state, Common common, int points){
		super(name, position, state, common, points);
	}

	public void draw( Graphics2D g2d){
		Font            fOriginal = g2d.getFont       () ;  // Save the default font of the g2d object
	    FontMetrics     fm        = g2d.getFontMetrics() ;  // Get a font metrics object that will help us calculate the width of strings in pixels
	    AffineTransform tOriginal = g2d.getTransform  () ;  // Save the default affine transformation of the g2d object (default coordinate axes)
	    String          str                              ;  // Just some string reference variable

	    g2d.setFont( new Font( "Sans Serif" , Font.BOLD , 14 ) ) ;  // Change the font of the g2d object to some nice, bold, larger font

	    g2d.translate( (int) this.position.x , (int) this.position.y ) ;  // Modify (translate) the default coordinate axes, origin is now at the center of the Student object

	    g2d.setPaint(Color.GREEN);
	    g2d.fillRect(-5, -5, 15, 15);

	    g2d.setPaint(Color.BLACK);
	    str = new Integer(this.points).toString();
	    g2d.drawString(str, (int)(-fm.stringWidth(str) / 2.0) + 1, 6);

	    g2d.setTransform( tOriginal ) ;  // Restore the saved affine transformation (restore default coordinate axes)
	    g2d.setFont     ( fOriginal ) ;  // Restore the default font
	}
}
