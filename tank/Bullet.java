package tank;
public class Bullet implements Runnable {
	private int x;
	private int y;
	private int face;
	private int speed = 5;
	private boolean flag = true;
	public Bullet(int x,int y,int face) {
		this.x = x;
		this.y = y;
		this.face = face;
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
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			switch(face) {
			case 0:
				y-=speed;
				break;
			case 1:
				y+=speed;
				break;
			case 2:
				x-=speed;
				break;
			case 3:
				x+=speed;
				break;
			}
			if(x<0||x>400||y<0||y>300) {
				flag = false;
				break;
			}
		}
	}
}