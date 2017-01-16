package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import javax.swing.JPanel;
import tank.Bullet;
import tank.EnemyTank;
import tank.Explosion;
import tank.MyTank;
@SuppressWarnings("serial")
public class Panel extends JPanel implements KeyListener,Runnable {
	private MyTank myTank = new MyTank(140,232);
	private Vector<EnemyTank> enemyTanks = new Vector<EnemyTank>();
	private Vector<Explosion> explosions = new Vector<Explosion>();
	private int number = 3;
	private Image explosionEffect1 = null;
	private Image explosionEffect2 = null;
	private Image explosionEffect3 = null;
	public Panel() {
		for(int i = 0;i<number;i++) {
			enemyTanks.add(new EnemyTank((i)*181+5,0));
		}
		explosionEffect1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/ExplosionEffect1.gif"));
		explosionEffect2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/ExplosionEffect2.gif"));
		explosionEffect3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/ExplosionEffect3.gif"));
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		if(myTank.getX()+30==400||myTank.getX()==0||myTank.getY()+30==300||myTank.getY()==0) {
			myTank.setX(140);
			myTank.setY(232);
		}
		this.drawTank(g, myTank.getX(), myTank.getY(), myTank.getFace(), 0);
		for(int i = 0;i<enemyTanks.size();i++) {
			EnemyTank enemyTank = enemyTanks.get(i);
			if(enemyTank.getFlag()){
				this.drawTank(g, enemyTanks.get(i).getX(), enemyTanks.get(i).getY(), 1, 1);
			}
		}
		for(int i = 0;i<myTank.getBullets().size();i++) {
			Bullet bullet = myTank.getBullets().get(i);
			if(myTank.getBullet()!=null&&myTank.getBullet().getFlag()) {
				g.setColor(Color.white);
				g.fill3DRect(bullet.getX(), bullet.getY(), 3, 3, false);
			}else if(!myTank.getBullet().getFlag()) {
				myTank.getBullets().remove(bullet);
			}
		}
		for(int i = 0;i<explosions.size();i++) {
			Explosion explosion = explosions.get(i);
			if(explosion.getSurvivalPeriod()>6) {
				g.drawImage(explosionEffect1, explosion.getX(), explosion.getY(), 30, 30, this);
			}else if(explosion.getSurvivalPeriod()>3) {
				g.drawImage(explosionEffect2, explosion.getX(), explosion.getY(), 30, 30, this);
			}else{
				g.drawImage(explosionEffect3, explosion.getX(), explosion.getY(), 30, 30, this);
			}
			new Thread(explosion).start();
			this.repaint();
			if(explosion.getSurvivalPeriod()==0) {
				explosions.remove(i);
			}
		}
	}
	private void drawTank(Graphics g,int x,int y,int face,int camp) {
		switch(camp) {
		case 0:
			g.setColor(Color.yellow);
			break;
		case 1:
			g.setColor(Color.green);
			break;
		}
		switch(face) {//坦克的方向：0上，1下，2左，3右
		case 0:
			g.fill3DRect(x, y, 5, 30, false);
			g.fill3DRect(x+5, y+5, 10, 20, false);
			g.fillOval(x+5, y+10, 10, 10);
			g.drawLine(x+10, y+15, x+10, y-3);
			g.fill3DRect(x+15, y, 5, 30, false);
			break;
		case 1:
			g.fill3DRect(x, y, 5, 30, false);
			g.fill3DRect(x+5, y+5, 10, 20, false);
			g.fillOval(x+5, y+10, 10, 10);
			g.drawLine(x+10, y+15, x+10, y+33);
			g.fill3DRect(x+15, y, 5, 30, false);
			break;
		case 2:
			g.fill3DRect(x, y, 30, 5, false);
			g.fill3DRect(x+5, y+5, 20, 10, false);
			g.fillOval(x+10, y+5, 10, 10);
			g.drawLine(x+15, y+10, x-3, y+10);
			g.fill3DRect(x, y+15, 30, 5, false);
			break;
		case 3:
			g.fill3DRect(x, y, 30, 5, false);
			g.fill3DRect(x+5, y+5, 20, 10, false);
			g.fillOval(x+10, y+5, 10, 10);
			g.drawLine(x+15, y+10, x+33, y+10);
			g.fill3DRect(x, y+15, 30, 5, false);
			break;
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_W) {
			myTank.setFace(0);
			myTank.Up();
		}else if(e.getKeyCode()==KeyEvent.VK_S) {
			myTank.setFace(1);
			myTank.Down();
		}else if(e.getKeyCode()==KeyEvent.VK_A) {
			myTank.setFace(2);
			myTank.left();
		}else if(e.getKeyCode()==KeyEvent.VK_D) {
			myTank.setFace(3);
			myTank.right();
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			if(myTank.getBullets().size()<8){
				myTank.firedBullets();
			}
		}
		this.repaint();
	}
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(int i = 0;i<myTank.getBullets().size();i++) {
				Bullet bullet =  myTank.getBullets().get(i);
				if(bullet.getFlag()) {
					for(int j = 0;j<enemyTanks.size();j++) {
						EnemyTank enemyTank = enemyTanks.get(j);
						if(enemyTank.getFlag()) {
							this.hit(bullet, enemyTank);
						}
					}
				}
				this.repaint();
			}
		}
	}
	private boolean hit(Bullet bullet, EnemyTank enemyTank) {
		boolean flag = false;
		switch(enemyTank.getFace()) {
		case 0:
		case 1:
			if(bullet.getX()>enemyTank.getX()&&bullet.getX()<enemyTank.getX()+20&&
					bullet.getY()>enemyTank.getY()&&bullet.getY()<enemyTank.getY()+30) {
				bullet.setFlag(false);
				enemyTank.setFlag(false);
				flag = true;
				Explosion explosion = new Explosion(enemyTank.getX(),enemyTank.getY());
				explosions.add(explosion);
			}
			break;
		case 2:
		case 3:
			if(bullet.getX()>enemyTank.getX()&&bullet.getX()<enemyTank.getX()+30&&
					bullet.getY()>enemyTank.getY()&&bullet.getY()<enemyTank.getY()+20) {
				bullet.setFlag(false);
				enemyTank.setFlag(false);
				flag = true;
				Explosion explosion = new Explosion(enemyTank.getX(),enemyTank.getY());
				explosions.add(explosion);
			}
			break;
		}
		return flag;
	}
}