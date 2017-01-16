package main;
import java.awt.Toolkit;
import javax.swing.JFrame;
public class Main {
	private JFrame frame = new JFrame();
	private Panel panel = new Panel();
	private Main() {
		this.initialComponent();
		this.addListener();
		this.initialFrame();
	}
	private void initialComponent() {
		frame.add(panel);
		new Thread(panel).start();
	}
	private void addListener() {
		frame.addKeyListener(panel);
	}
	private void initialFrame() {
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/tank.jpg")));
		frame.setTitle("Ì¹¿Ë´óÕ½");
		frame.setSize(400,300);
		frame.setLocation(300,280);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new Main();
	}
}