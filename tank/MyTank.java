package tank;
import java.util.Vector;
public class MyTank extends Tank {
	private Vector<Bullet> bullets = new Vector<Bullet>();
	private Bullet bullet = null;
	public MyTank(int x, int y) {
		super(x, y);
	}
	public Bullet getBullet() {
		return bullet;
	}
	public Vector<Bullet> getBullets() {
		return bullets;
	}
	public void Up() {
		super.setY(super.getY()-super.getSpeed());
	}
	public void Down() {
		super.setY(super.getY()+super.getSpeed());
	}
	public void left() {
		super.setX(super.getX()-super.getSpeed());
	}
	public void right() {
		super.setX(super.getX()+super.getSpeed());
	}
	public void firedBullets() {
		switch(super.getFace()) {
		case 0:
			bullet = new Bullet(super.getX()+10, super.getY(), 0);
			break;
		case 1:
			bullet = new Bullet(super.getX()+10, super.getY()+30, 1);
			break;
		case 2:
			bullet = new Bullet(super.getX(), super.getY()+10, 2);
			break;
		case 3:
			bullet = new Bullet(super.getX()+30, super.getY()+10, 3);
			break;
		}
		bullets.add(bullet);
		new Thread(bullet).start();
	}
}