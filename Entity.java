import java.util.*;
import java.awt.*;

public abstract class Entity {
	public String name;
	public Vector2D position;
	public State state;
	public Common common;

	public Entity(String name){
		this.name = name;
	}
	public Entity(String name, Vector2D position, State state, Common common){
		this.name = name;
		this.position = position;
		this.state = state;
		this.common = common;
	}

	public abstract void draw(Graphics2D g2d);

	public void step(){
		state.step(this);
	}
} 