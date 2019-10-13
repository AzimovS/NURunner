import javax.swing.*       ; 
import java.awt.Color      ; 
import java.awt.Graphics   ; 
import java.awt.Dimension  ; 
import java.awt.Graphics2D ; 
import java.io.*;
public class Display extends JPanel{
	public Common common;
	public int mapWidth;
	public int mapHeight;

	public Display(int numberStudents, int windowWidth, int windowHeight){
		common = new Common(numberStudents, windowWidth, windowHeight);
		// try{
		// common.map = new UniversityMap("NUMap-Faded.jpg", windowWidth, windowHeight);
		// }
		// catch(IOException e){

		// }
		// common.windowWidth = common.map.backgroundImage.getWidth(null);
		// common.windowHeight = common.map.backgroundImage.getHeight(null);
		this.mapWidth = windowWidth;
		this.mapHeight = windowHeight;
	}

	@Override public Dimension getPreferredSize(){
		return new Dimension( this.mapWidth, this.mapHeight );
	} 

	@Override public void paintComponent(Graphics g){
		super.paintComponent( g );
		Graphics2D g2d = (Graphics2D) g;
		common.stepAllEntities();
		common.drawAllEntities(g2d);
	}
}