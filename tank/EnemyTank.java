package tank;
import java.util.Vector;
public class EnemyTank extends Tank implements Runnable {
	private Vector<Bullet> enemyBullets = new Vector<Bullet>();
	private Bullet enemyBullet = null;
	private int time = 0;
	public EnemyTank(int x, int y) {
		super(x, y);
	}
	public Vector<Bullet> getEnemyBullets() {
		return enemyBullets;
	}
	@Override
	public void run() {
		while(true) {
			switch(super.getFace()) {
			case 0:
				for(int i = 0;i<30;i++) {
					if(super.getY()>0) {
						super.setY(super.getY()-super.getSpeed());
					}else break;
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;
			case 1:
				for(int i = 0;i<30;i++) {
					if(super.getY()<241) {
						super.setY(super.getY()+super.getSpeed());
					}else break;
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;
			case 2:
				for(int i = 0;i<30;i++) {
					if(super.getX()>0) {
						super.setX(super.getX()-super.getSpeed());
					}else break;
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;
			case 3:
				for(int i = 0;i<30;i++) {
					if(super.getX()<351) {
						super.setX(super.getX()+super.getSpeed());
					}else break;
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;
			}
			super.setFace((int)(Math.random()*4));
			time++;
			if(!super.getFlag()) {
				break;
			}else if(time%2==0) {
				if(enemyBullets.size()<8) {
					switch(super.getFace()) {
					case 0:
						enemyBullet = new Bullet(super.getX()+10, super.getY(), 0);
						break;
					case 1:
						enemyBullet = new Bullet(super.getX()+10, super.getY()+30, 1);
						break;
					case 2:
						enemyBullet = new Bullet(super.getX(), super.getY()+10, 2);
						break;
					case 3:
						enemyBullet = new Bullet(super.getX()+30, super.getY()+10, 3);
						break;
					}
					enemyBullets.add(enemyBullet);
					new Thread(enemyBullet).start();
				}
			}
		}
	}
}