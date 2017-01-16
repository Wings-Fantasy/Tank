package tank;
public class Explosion implements Runnable {
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
	@Override
	public void run() {
		while(flag) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(survivalPeriod>0) {
				survivalPeriod--;
			}else{
				flag = false;
			}
		}
	}
}