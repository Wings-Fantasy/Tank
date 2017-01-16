package tank;
public class Explosion {
	private int x;
	private int y;
	private int survivalPeriod = 9;
	private boolean flag = true;
	public Explosion(int x,int y) {
		this.x = x;
		this.y = y;
	}
	public int getSurvivalPeriod() {
		return survivalPeriod;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public boolean getFlag() {
		return flag;
	}
	public void dead() {
		if(survivalPeriod>0) {
			survivalPeriod--;
		}else{
			flag = false;
		}
	}
}