import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Surface extends JPanel implements ActionListener {
	JFrame frame = new JFrame("BlairPedestrian");
	private Timer timer;
	private final int DELAY = 150;
	BufferedImage floorPlan;
	
	public Person[] people;
	public Edge[] edges;

	public Surface(Person[] p, Edge[] ed) {
		people = p;
		edges = ed;		
				
		//--------------------Make Frame stuff---------------------------
		timer = new Timer(DELAY, this);
		timer.start();
		
		frame.setVisible(true);
		frame.setContentPane(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 600, 800);
	
		try {
			floorPlan = (BufferedImage) ImageIO.read(new File("FloorPlan.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.revalidate();
		frame.repaint();
	}
	
	public Timer getTimer() {
		return timer;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(floorPlan, 0, 0, 600, 800, null);

		//Replace this outer stuff with a loop going through all the people
		int numEdges = 0;
		for(int i = 0; i < edges.length; i++) {
			if(edges[i] != null) numEdges += 1;
		}
		for(int i = 0; i < numEdges; i++) {
			Edge edge = edges[i];
			double location = 0;
			
			Graphics2D g2d = (Graphics2D)g;
			double x = edge.x;
			double y = edge.y;
			if(edge.vertical == 0) {
				x += location;
				y += Math.random()*2;
			}else{
				y += location;
				x += Math.random()*2;
			}
			
			g2d.setColor(new Color(0, 255, 0));
			g2d.fillOval((int)x, (int)y, 5, 5);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		//System.out.println("This happened yay");
	}

}
