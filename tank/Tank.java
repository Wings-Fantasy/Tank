package tank;
public class Tank {
	private int x;
	private int y;
	private int face = 0;
	private int speed = 2;
	private boolean flag = true;
	public Tank(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public int getFace() {
		return face;
	}
	public void setFace(int face) {
		this.face = face;
	}
	public int getSpeed() {
		return speed;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}