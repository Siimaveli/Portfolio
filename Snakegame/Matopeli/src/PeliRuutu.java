import javax.management.timer.Timer;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.random.*;




public class PeliRuutu extends JPanel implements ActionListener {
	
	static final int SCREEN_WIDTH = 1200;
	static final int SCREEN_HEIGHT = 550;
	static final int UNIT_SIZE = 50;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/(UNIT_SIZE*UNIT_SIZE);
	static final int DELAY = 175;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 6;
	int applesEaten;
	int appleX;
	int appleY;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;
	
	
	PeliRuutu(){
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.darkGray);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		aloitaPeli();
		
	}
	public void aloitaPeli(){
		newApple();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
		
	}
	
	

}
