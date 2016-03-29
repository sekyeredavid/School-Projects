import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ViewLudo extends JPanel implements MouseListener{
	
	public static Graphics g;
	private boolean started = false;
	private int value;
	private int curX;
	private int curY;
	private int X;
	private int Y;
	private ControllerLudo cL;
	private int playerID = 0;

	public ViewLudo(){
		cL = new ControllerLudo();
		setBackground(Color.WHITE);
		addMouseListener(this);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.g = g;

		g.setColor(Color.BLACK);
	
		//vertical tiles	
		for(int y=110; y<=470; y+=30){
			for(int x=360; x<=420; x+=30){
				g.drawRect(x, y, 30, 30);
			} 
		}

		//horizontal tiles
		for(int x=210; x<=570; x+=30){
			for(int y=260; y<=320; y+=30){
				g.drawRect(x, y, 30, 30);
			}
		}

		//clear middle rect
		g.setColor(Color.WHITE);
		g.clearRect(361, 261, 89, 89);

		if(started == true){
			paintRealDeal(g);
		}	

		cL.kickOut(playerID);

		paintDie(g, value);	
		paintBigCircles(g);
		paintLittleCircles(g);
		paintStuck(g);
		paintImportantTiles(g);
		
		paintCurrentOfAll(g);

	}

	public void paintBigCircles(Graphics g){

		//big fat circles
		g.setColor(new Color(168, 255, 166));
		g.fillOval(240, 140, 100, 100);
		g.setColor(new Color(252, 255, 166));
		g.fillOval(240, 350, 100, 100);
		g.setColor(new Color(255, 166, 166));
		g.fillOval(470, 140, 100, 100);
		g.setColor(new Color(148, 158, 223));
		g.fillOval(470, 350, 100, 100);


	}

	public void paintLittleCircles(Graphics g){
		//little inner circles
		g.setColor(Color.GREEN);
		g.fillOval(255, 160, 20, 20);
		g.fillOval(305, 160, 20, 20);
		g.fillOval(255, 210, 20, 20);
		g.fillOval(305, 210, 20, 20);

		g.setColor(Color.YELLOW);
		g.fillOval(255, 370, 20, 20);
		g.fillOval(305, 370, 20, 20);
		g.fillOval(255, 420, 20, 20);
		g.fillOval(305, 420, 20, 20);

		g.setColor(Color.RED);
		g.fillOval(485, 160, 20, 20);
		g.fillOval(535, 160, 20, 20);
		g.fillOval(485, 210, 20, 20);
		g.fillOval(535, 210, 20, 20);
		
		g.setColor(Color.BLUE);
		g.fillOval(485, 370, 20, 20);
		g.fillOval(535, 370, 20, 20);
		g.fillOval(485, 420, 20, 20);
		g.fillOval(535, 420, 20, 20);


	}

	public void paintStuck(Graphics g){
		for(int id=0; id<4; id++){
			if(id == 0){
				g.setColor(new Color(168, 255, 166));
				if(cL.getNumber(id) < 4)
					g.fillOval(305, 210, 30, 30);
				if(cL.getNumber(id) < 3)
					g.fillOval(255, 210, 30, 30);
				if(cL.getNumber(id) < 2)
					g.fillOval(305, 160, 30, 30);
				if(cL.getNumber(id) < 1)
					g.fillOval(155, 160, 30, 30);
			}

			if(id == 1){
				g.setColor(new Color(255, 166, 166));
				if(cL.getNumber(id) < 4)
					g.fillOval(535, 210, 30, 30);
				if(cL.getNumber(id) < 3)
					g.fillOval(485, 210, 30, 30);
				if(cL.getNumber(id) < 2)
					g.fillOval(535, 160, 30, 30);
				if(cL.getNumber(id) < 1)
					g.fillOval(485, 160, 30, 30);
			}
		
			if(id == 2){
				g.setColor(new Color(148, 158, 223));
				if(cL.getNumber(id) < 4)
					g.fillOval(535, 420, 30, 30);
				if(cL.getNumber(id) < 3)
					g.fillOval(485, 420, 30, 30);
				if(cL.getNumber(id) < 2)
					g.fillOval(535, 370, 30, 30);
				if(cL.getNumber(id) < 1)
					g.fillOval(485, 370, 30, 30);
			}
	
			if(id == 3){
				g.setColor(new Color(252, 255, 166));
				if(cL.getNumber(id) < 4)
					g.fillOval(305, 420, 30, 30);
				if(cL.getNumber(id) < 3)
					g.fillOval(255, 420, 30, 30);
				if(cL.getNumber(id) < 2)
					g.fillOval(305, 270, 30, 30);
				if(cL.getNumber(id) < 1)
					g.fillOval(255, 370, 30, 30);
			}
		}	
		
	}

	public void paintImportantTiles(Graphics g){
		//important colored tiles
		g.setColor(new Color(168, 255, 166));
		g.fillRect(240+2, 260+2, 28, 28);
		for(int x=240; x<=360; x+=30){
			g.fillRect(x+2, 290+2, 28, 28);
		}

		g.setColor(new Color(255, 166, 166));
		g.fillRect(420+2, 140+2, 28, 28);
		for(int y=140; y<=260; y+=30){
			g.fillRect(390+2, y+2, 28, 28);
		}

		g.setColor(new Color(252, 255, 166));
		g.fillRect(360+2, 440+2, 28, 28);
		for(int y=320; y<=440; y+=30){
			g.fillRect(390+2, y+2, 28, 28);
		}

		g.setColor(new Color(148, 158, 223));
		g.fillRect(540+2, 320+2, 28, 28);
		for(int x=420; x<=540; x+=30){
			g.fillRect(x+2, 290+2, 28, 28);
		} 
	
	}	

	private void paintRealDeal(Graphics g){
		value = cL.rollDie();

		for(int i=0; i<value; i++){
			curX = cL.getCurX(playerID);
			curY = cL.getCurY(playerID);
	
			g.setColor(Color.WHITE);
			g.fillOval(curX+5, curY+5, 20, 20);
	
			
			if(playerID == 0)
				g.setColor(Color.GREEN);
			else if(playerID == 1)
				g.setColor(Color.RED);
			else if(playerID == 2)
				g.setColor(Color.BLUE);
			else if(playerID == 3)
				g.setColor(Color.YELLOW);
			X = cL.getX(playerID);
			Y = cL.getY(playerID);
		
			g.fillOval(X+5, Y+5, 20, 20);

			curX = X;
			curY = Y;
		}
	}
	
	private void paintCurrentOfAll(Graphics g){
		int curX, curY;

		for(int id=0; id < 4; id++){
			curX = cL.getCurX(id);
			curY = cL.getCurY(id);
			
			if(id == 0)
				g.setColor(Color.GREEN);
			else if(id == 1)
				g.setColor(Color.RED);
			else if(id == 2)
				g.setColor(Color.BLUE);
			else if(id == 3)
				g.setColor(Color.YELLOW);
			
			g.fillOval(curX+5, curY+5, 20, 20);
		}
	}

	private void paintDie(Graphics g, int value){
		
		g.setColor(Color.BLACK);

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(600, 260, 45, 45);

		g.setColor(Color.WHITE);
		g.fillRect(600, 260, 45, 45);

		g.setColor(Color.BLACK);
		if(value == 1){
			g.setColor(Color.RED);
			g.fillOval(611, 272, 23, 23);
		}

		else if(value == 2){
			g.fillOval(600, 260, 11, 11);
			g.fillOval(634, 283, 11, 11);
		}

		else if(value == 3){
			g.fillOval(600, 260, 11, 11);
			g.fillOval(617, 272, 11, 11);
			g.fillOval(634, 283, 11, 11);
		}

		else if(value == 4){
			g.fillOval(600, 260, 11, 11);
			g.fillOval(600, 283, 11, 11);
			g.fillOval(634, 260, 11, 11);
			g.fillOval(634, 283, 11, 11);
		}

		else if(value == 5){
			g.fillOval(600, 260, 11, 11);
			g.fillOval(600, 283, 11, 11);
			g.fillOval(617, 272, 11, 11);
			g.fillOval(634, 260, 11, 11);
			g.fillOval(634, 285, 11, 11);
		}

		else if(value == 6){
			g.fillOval(600, 260, 11, 11);
			g.fillOval(617, 260, 11, 11);
			g.fillOval(634, 260, 11, 11);
			g.fillOval(600, 285, 11, 11);
			g.fillOval(617, 285, 11, 11);
			g.fillOval(634, 285, 11, 11);
		}

	}

	public void mouseClicked(MouseEvent e){
		started = true;
	
		repaint();
	
		playerID++;
		if(playerID > 3)
			playerID = 0;
	}

	public void mouseExited(MouseEvent e){}

	public void mouseReleased(MouseEvent e){}

	public void mousePressed(MouseEvent e){}

	public void mouseEntered(MouseEvent e){}

	private Graphics getG(){
		return this.g;
	}

	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setSize(800, 800);
		frame.setTitle("PROJECT LUDO");
		frame.setContentPane(new ViewLudo());

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
	}
}
