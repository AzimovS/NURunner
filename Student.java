import java.awt.FontMetrics;
import java.awt.Paint;
import java.awt.geom.AffineTransform;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.swing.*;
// import java.awt.geom.*;
// import java.lang.Math;

public class Student extends Entity{
	public int grade;

	public Student(String name){
		super(name);
	}

	public Student(String name, Vector2D position, State state, Common common){
		super(name, position, state, common);
	}

	@Override 
	public void draw ( Graphics2D g2d ){
	    Font            fOriginal = g2d.getFont       () ; // Code from Piazza.
	    FontMetrics     fm        = g2d.getFontMetrics() ; 
	    AffineTransform tOriginal = g2d.getTransform  () ;  
	    String          str                              ;  

	    g2d.setFont( new Font( "Sans Serif" , Font.BOLD , 14 ) ) ;  
	    g2d.translate( (int) position.x , (int) position.y ) ;  

	    if   ( grade >= 100 )  { g2d.setPaint( Color.MAGENTA ) ; }  
	    else                   { g2d.setPaint( Color.CYAN    ) ; }

	    g2d.fillOval( -15 , -15 , 30 , 30 ) ; 

	    g2d.setPaint( Color.BLACK ) ; g2d.drawOval( -15 , -15 , 30 , 30 ) ;

	    str = name             ;  g2d.setPaint( Color.BLACK ) ;  g2d.drawString( str , (int) (-fm.stringWidth(str) / 2.0) + 1 , -18 ) ;  
	    str = grade + ""       ;  g2d.setPaint( Color.BLACK ) ;  g2d.drawString( str , (int) (-fm.stringWidth(str) / 2.0) + 1 ,   6 ) ;
	    str = state.toString() ;  g2d.setPaint( Color.BLACK ) ;  g2d.drawString( str , (int) (-fm.stringWidth(str) / 2.0) + 1 ,  30 ) ;

	    g2d.setTransform( tOriginal ) ;  
	    g2d.setFont     ( fOriginal ) ;  
  	}

}