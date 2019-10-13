import java.awt.Graphics2D;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.Random;
import java.awt.FontMetrics;
import java.awt.Paint;
import java.awt.geom.AffineTransform;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
public class Speaker extends Entity{

	public Image img;
	public int imgwidth;
	public int imgheight;

	public Speaker(String name, Vector2D position, State state, Common common, String fileName){
		super(name, position, state, common);
		try{
			this.img = ImageIO.read( new File(fileName));
		} catch(IOException e){ }
		this.imgwidth = img.getWidth(null) / 4;
		this.imgheight = img.getHeight(null) / 4;
	}

	public void draw(Graphics2D g2d){
		Font            fOriginal = g2d.getFont       () ;  // Save the default font of the g2d object
	    FontMetrics     fm        = g2d.getFontMetrics() ;  // Get a font metrics object that will help us calculate the width of strings in pixels
	    AffineTransform tOriginal = g2d.getTransform  () ;  // Save the default affine transformation of the g2d object (default coordinate axes)
	    String          str                              ;  // Just some string reference variable

	    g2d.setFont( new Font( "Sans Serif" , Font.BOLD , 14 ) ) ;  // Change the font of the g2d object to some nice, bold, larger font

	    g2d.translate( (int) position.x , (int) position.y ) ;  // Modify (translate) the default coordinate axes, origin is now at the center of the Student object
	    g2d.drawImage(this.img, -this.imgwidth/2, -this.imgheight/2, this.imgwidth, this.imgheight, null);

	    g2d.setPaint( Color.BLACK );

	    str = name             ;  g2d.setPaint( Color.BLACK ) ;  g2d.drawString( str , (int) (-fm.stringWidth(str) / 2.0) + 1 , -this.imgheight / 2 -2 ) ;  // fm.stringWidth(str) gives width in pixels

	    g2d.setTransform( tOriginal ) ;  // Restore the saved affine transformation (restore default coordinate axes)
	    g2d.setFont     ( fOriginal ) ;  // Restore the default font
	}
}