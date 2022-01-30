

import javax.swing.JFrame;


public class PeliKehys extends JFrame {
	
	PeliKehys(){
		this.add(new PeliRuutu());
		this.setTitle("MatoPeli");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
			
	}

}
