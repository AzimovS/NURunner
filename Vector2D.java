public class Vector2D{
	public double x;
	public double y;

	public Vector2D(double x, double y){
		this.x = x;
		this.y = y;
	}

	public void set(Vector2D v){
		this.x = v.x;
		this.y = v.y;
	}

	public double distanceTo( Vector2D other ){
		double dist = Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2);
		return Math.sqrt(dist);
	}

	public Vector2D plus( Vector2D other ){
		Vector2D newv = new Vector2D(this.x + other.x, this.y + other.y);
		return newv;
	}

	public Vector2D minus( Vector2D other ){
		Vector2D newv = new Vector2D(this.x - other.x, this.y - other.y);
		return newv;
	}

}