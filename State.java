public abstract class State{
	public boolean isOver;
	public boolean isVisible;
	public boolean requirenew;

	public State(){
		this.isOver = false;
		this.isVisible = true;
		this.requirenew = false;
	}
	public void set(Vector2D newposition){

	}
	public abstract void step(Entity e);
}

class Rest extends State{
	public int time;

	public Rest(int time){
		super();
		this.time = time;
	}

	public String toString(){
		return "Rest";
	}

	public void step(Entity e){
		this.time = this.time-1;
		if (time<1){
			this.isOver = true;
		}		
	}
}

class ZigZag extends State{
	public Vector2D newpos;
	public int time;
	public ZigZag(Vector2D newpos, int time){
		super();
		this.time = time;
		this.newpos = newpos;
	}

	public void step(Entity e){
		this.time--;

		if (e.position.x < 5 || e.position.x > e.common.windowWidth - 5){
			this.newpos.x *= -1;
		}
		if (e.position.y < 5 || e.position.y > e.common.windowHeight - 5){
			this.newpos.y *= -1;
		}

		if (this.newpos.x > e.position.x){
			e.position.x += 1;
		}
		else if (this.newpos.x < e.position.x){
			e.position.x -= 1;
		}

		if (this.newpos.y > e.position.y){
			e.position.y += 1;
		}
		else if (this.newpos.y < e.position.y){
			e.position.y -= 1;
		}
		if (this.time < 0) this.isOver = true;
	}

	public String toString(){
		return "ZigZag";
	}
}

class GotoXY extends State{
	public Vector2D newpos;

	public GotoXY(Vector2D newpos){
		super();
		this.newpos = newpos;
	}

	public void step(Entity e){
		int check = 1;
		if (newpos.x > e.position.x){
			e.position.x += 1;
		}
		else if (newpos.x < e.position.x){
			e.position.x -= 1;
		}
		if (newpos.y > e.position.y){
			e.position.y += 1;
		}
		else if (newpos.y < e.position.y){
			e.position.y -= 1;
		}
		if (newpos.x == e.position.x && newpos.y == e.position.y) this.isOver = true;
	}

	public String toString(){
		return "GotoXY";
	}
}

class Closest extends State{
	public Vector2D newpos;
	public int number;
	public int time;

	public Closest(Vector2D newpos, int number){
		super();
		this.newpos = newpos;
		this.number = number;
		this.requirenew = false;
	}

	public void set(Vector2D newpos){
		this.newpos = newpos;
		this.requirenew = false;
	}
	public void step(Entity e){
		if (newpos.x > e.position.x){
			e.position.x += 1;
		}
		else if (newpos.x < e.position.x){
			e.position.x -= 1;
		}

		if (newpos.y > e.position.y){
			e.position.y += 1;
		}
		else if (newpos.y < e.position.y){
			e.position.y -= 1;
		}
		if ( this.number < 1){
			this.isOver = true;
		}
		else if (newpos.x == e.position.x && newpos.y == e.position.y){
			this.number -= 1;
			this.requirenew = true;
		}

	}

	public String toString(){
		return "Closest";
	}
}

class Stationary extends State{

	public Stationary(){
		super();
	}

	public void step(Entity e){

	}

	public String toString(){
		return "Stationary";
	}
}