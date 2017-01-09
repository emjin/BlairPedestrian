import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawDotsOnMap extends JFrame {
	public DrawDotsOnMap() {
		add(new NewPanel());
	}
}

class NewPanel extends JPanel {
	double maxX, maxY, x0, y0;
	
	void initgr() {
		maxX = getWidth() - 1; //The biggest the x value can be
		maxY = getHeight() - 1; //The biggest the y value can be
		//minMaxXY = Math.min(maxX, maxY); //Make it a square
		x0 = maxX / 2;
		y0 = maxY / 2;
	}

	//round x 
	int iX(float x) {
		return Math.round(x);
	}

	int iY(float y) {
		return Math.round(y);
	}

	void drawCurve(Graphics g, double xCenter, double yCenter, double minMaxXY) {
		float side = (float) (0.95F * minMaxXY);
		float sideHalf = 0.5F * side;
		float h = sideHalf*(float)(Math.sqrt(3)); //height
		final float Q_VALUE = 0.048F; //constant
		final float P_VALUE = 1 - Q_VALUE;
		int sides = 5;
		float[] x = new float[sides];
		x[0] = (float)(xCenter);
		x[1] = (float)(xCenter + sideHalf);
		x[2] = (float)(xCenter + sideHalf/2);
		x[3] = (float)(xCenter - sideHalf/2);
		x[4] = (float)(xCenter - sideHalf);
		float[] y = new float[sides];
		y[0] = (float)(yCenter - 0.5 * h); 
		y[1] = (float)(yCenter); 
		y[2] = (float)(yCenter + 0.5 * h);
		y[3] = (float)(yCenter + 0.5 * h);
		y[4] = (float)(yCenter);
		for(int i = 0; i < 50; i++) {
			//draws the triangle
			for(int t = 0; t < sides; t++){
				//System.out.println((5-i/10.0));
				int b = (int)((iX(x[t])%(256-(156/(8-i/10.0))))*Math.sin((50-i)/18.0*(Math.PI/3)));
				int r = (int)((iY(y[t])%(256-(156/(8-i/10.0))))*Math.sin((50-i)/18.0*Math.PI/3));
				int gr = 0;
				g.setColor(new Color(r, gr, b));
				g.drawLine(iX(x[t]), iY(y[t]), iX(x[(t+1)%sides]), iY(y[(t+1)%sides]));
			}
			//adjust values to rotate and shrink the image
			for(int t = 0; t < sides; t++){
				x[t] = P_VALUE * x[t] + Q_VALUE * x[(t+1)%sides];
			}
			for(int t = 0; t < sides; t++){
				y[t] = P_VALUE * y[t] + Q_VALUE * y[(t+1)%sides];
			}
		}		
	}
	
	protected void paintComponent(Graphics g) {
		int numCurves = 5;
		double wid = maxX/numCurves;
		double ht = maxY/numCurves;
		super.paintComponent(g);
		initgr();
		setBackground(new Color(0, 0, 20));
		for(int i = 0; i < numCurves; i++){
			for(int j = 0; j < numCurves*ht/wid; j++){
				drawCurve(g, wid*i+wid/2, wid*j+wid/2, wid);
			}
		}
	}
}
