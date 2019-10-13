import java.util.*;
import java.awt.Image;
import java.lang.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.Graphics2D;

public class UniversityMap extends Entity{

	public Image backgroundImage; 
	public int windowWidth;
	public int windowHeight;

	public UniversityMap(String fileName, int windowWidth, int windowHeight) throws java.io.IOException {
		super(fileName);
		backgroundImage = ImageIO.read( new File(fileName));
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
	}

	public void draw(Graphics2D g2d){
		g2d.drawImage(backgroundImage, 0, 0, windowWidth, windowHeight, null);
	}	
}