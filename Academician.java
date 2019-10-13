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
public class Academician extends Entity{

	public Image img;
	public int imgwidth;
	public int imgheight;

	public Academician(String name, Vector2D position, State state, Common common, String fileName){
		super(name, position, state, common);
		try{
			this.img = ImageIO.read( new File(fileName));
		} catch(IOException e){ }
		this.imgwidth = img.getWidth(null) / 4;
		this.imgheight = img.getHeight(null) / 4;
	}

	public void draw(Graphics2D g2d){
		Font            fOriginal = g2d.getFont       () ; 
	    FontMetrics     fm        = g2d.getFontMetrics() ;  
	    AffineTransform tOriginal = g2d.getTransform  () ;  
	    String          str                              ;  

	    g2d.setFont( new Font( "Sans Serif" , Font.BOLD , 14 ) ) ;  

	    g2d.translate( (int) position.x , (int) position.y ) ;  
	    g2d.drawImage(this.img, -this.imgwidth/2, -this.imgheight/2, this.imgwidth, this.imgheight, null);

	    g2d.setPaint( Color.BLACK );

	    str = name             ;  g2d.setPaint( Color.BLACK ) ;  g2d.drawString( str , (int) (-fm.stringWidth(str) / 2.0) + 1 , -this.imgheight / 2 ) ;  
	    str = state.toString() ;  g2d.setPaint( Color.BLACK ) ;  g2d.drawString( str , (int) (-fm.stringWidth(str) / 2.0) + 1 ,  45 ) ;

	    g2d.setTransform( tOriginal ) ;  
	    g2d.setFont     ( fOriginal ) ;  
	}

	public Assessment createAssessment(){
		Random random = new Random();
		int which = random.nextInt(3);
		AssessmentFactory factory;
		if (which == 0 ){
			factory = new LabFactory(this.common);
		}
		else if (which == 1){
			factory = new QuizFactory(this.common);
		}
		else{
			factory = new HomeworkFactory(this.common);
		}
		return factory.createAssessment(position.plus(new Vector2D(imgwidth/3, imgheight/3)));
	}
}