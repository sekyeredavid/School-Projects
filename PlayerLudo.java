import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

public class ViewLudo extends JPanel implements MouseListener, ItemListener,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1230604044943904884L;
	private boolean moved = false;
	private int value;
	private int curX;
	private int curY;
	private int X;
	private int Y;
	private ControllerLudo cL;
	private int playerID = 0;
	private int pieceID = 0;

	//show which player has just played
	private JLabel lblPlayer = new JLabel("Game started");

	//indicate which player piece should play next
	private JRadioButton[] piece = new JRadioButton[4]; 

	private ButtonGroup bgPieces;

	//constructor to initialise stuff 
	public ViewLudo(){
		cL = new ControllerLudo();

		piece[0] = new JRadioButton("1");
		piece[1] = new JRadioButton("2");
		piece[2] = new JRadioButton("3");
		piece[3] = new JRadioButton("4");

		bgPieces = new ButtonGroup();

		piece[0].setSelected(true);

		add(lblPlayer);
		for(int i=0; i<4; i++){
			piece[i].addItemListener(this);
			bgPieces.add(piece[i]);
			add(piece[i]);
		}
		setBackground(Color.WHITE);
		addMouseListener(this);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);

		g.setColor(Color.BLACK);
	
		//vertical tiles	
		for(int y=80; y<=500; y+=30){
			for(int x=360; x<=420; x+=30){
				g.drawRect(x, y, 30, 30);
			} 
		}

		//horizontal tiles
		for(int x=180; x<=600; x+=30){
			for(int y=260; y<=320; y+=30){
				g.drawRect(x, y, 30, 30);
			}
		}

		//clear middle rect
		g.setColor(Color.WHITE);
		g.clearRect(361, 261, 89, 89);

		paintDie(g);

		paintRealDeal(g);
		
		paintBigCircles(g);
		paintLittleCircles(g);
		paintStuck(g);
		paintImportantTiles(g);
		
		paintCurrentOfAll(g);

	}

	//draw the big circle where the player pieces are kept before 6 is played
	public void paintBigCircles(Graphics g){

		//big fat circles

		//GREEN
		g.setColor(new Color(168, 255, 166));
		g.fillOval(240, 140, 100, 100);

		//YELLOW
		g.setColor(new Color(252, 255, 166));
		g.fillOval(240, 350, 100, 100);

		//RED
		g.setColor(new Color(255, 166, 166));
		g.fillOval(470, 140, 100, 100);

		//BLUE
		g.setColor(new Color(148, 158, 223));
		g.fillOval(470, 350, 100, 100);


	}

	//draw little player pieces stuck before 6 is played
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

	//update all player Pieces
	public void paintStuck(Graphics g){
		for(int id=0; id<4; id++){
			if(id == 0){
				g.setColor(new Color(168, 255, 166));
				if(cL.getNumber(id) < 4)
					g.fillOval(305, 210, 20, 20);
				if(cL.getNumber(id) < 3)
					g.fillOval(255, 210, 20, 20);
				if(cL.getNumber(id) < 2)
					g.fillOval(305, 160, 20, 20);
				if(cL.getNumber(id) < 1)
					g.fillOval(255, 160, 20, 20);
			}

			if(id == 1){
				g.setColor(new Color(255, 166, 166));
				if(cL.getNumber(id) < 4)
					g.fillOval(535, 210, 20, 20);
				if(cL.getNumber(id) < 3)
					g.fillOval(485, 210, 20, 20);
				if(cL.getNumber(id) < 2)
					g.fillOval(535, 160, 20, 20);
				if(cL.getNumber(id) < 1)
					g.fillOval(485, 160, 20, 20);
			}
		
			if(id == 2){
				g.setColor(new Color(148, 158, 223));
				if(cL.getNumber(id) < 4)
					g.fillOval(535, 420, 20, 20);
				if(cL.getNumber(id) < 3)
					g.fillOval(485, 420, 20, 20);
				if(cL.getNumber(id) < 2)
					g.fillOval(535, 370, 20, 20);
				if(cL.getNumber(id) < 1)
					g.fillOval(485, 370, 20, 20);
			}
	
			if(id == 3){
				g.setColor(new Color(252, 255, 166));
				if(cL.getNumber(id) < 4)
					g.fillOval(305, 420, 20, 20);
				if(cL.getNumber(id) < 3)
					g.fillOval(255, 420, 20, 20);
				if(cL.getNumber(id) < 2)
					g.fillOval(305, 370, 20, 20);
				if(cL.getNumber(id) < 1)
					g.fillOval(255, 370, 20, 20);
			}
		}	
		
	}

	public void paintImportantTiles(Graphics g){
		//important colored tiles
		g.setColor(new Color(168, 255, 166));
		g.fillRect(210+2, 260+2, 28, 28);
		for(int x=210; x<=360; x+=30){
			g.fillRect(x+2, 290+2, 28, 28);
		}

		g.setColor(new Color(255, 166, 166));
		g.fillRect(420+2, 110+2, 28, 28);
		for(int y=110; y<=260; y+=30){
			g.fillRect(390+2, y+2, 28, 28);
		}

		g.setColor(new Color(252, 255, 166));
		g.fillRect(360+2, 470+2, 28, 28);
		for(int y=320; y<=470; y+=30){
			g.fillRect(390+2, y+2, 28, 28);
		}

		g.setColor(new Color(148, 158, 223));
		g.fillRect(570+2, 320+2, 28, 28);
		for(int x=420; x<=570; x+=30){
			g.fillRect(x+2, 290+2, 28, 28);
		} 
	
	}	
	
	//where player logic takes place when die is rolled
	private void paintRealDeal(Graphics g){

		
		//if rolled die is 6	
		if(value == 6 && cL.getNumber(playerID) > 0){

			//check number of player pieces that are still not on board and release one.
			if(cL.getNumber(playerID) == 4)
				pieceID = 0;
			else if(cL.getNumber(playerID) == 3)
				pieceID = 1;
			else if(cL.getNumber(playerID) == 2)
				pieceID = 2;
			else if(cL.getNumber(playerID) == 1)
				pieceID = 3;
						
				
			cL.setIsOut(playerID, pieceID);
			cL.setNumber(playerID);	

			return;
		}

		//not played 6
		else{
			//even if it is not 6 and there is no player piece on board dont play
			if(cL.getIsOut(playerID, pieceID) == false);
								
		
			//player piece on board and 6 not played move it
			else{	
				
				if(playerID == 0)
					g.setColor(Color.GREEN);
				else if(playerID == 1)
					g.setColor(Color.RED);
				else if(playerID == 2)
					g.setColor(Color.BLUE);
				else if(playerID == 3)
					g.setColor(Color.YELLOW);
				X = cL.getX(value, playerID, pieceID);
				Y = cL.getY(playerID, pieceID);
			
				g.fillOval(X+5, Y+5, 20, 20);
			}
		}
		
	
		//after moving, check to make sure you can kick someone out. and do it. :D
		cL.kickOut(playerID, pieceID);

		//increase player ID so next player can play
		playerID++;
		if(playerID > 3)
			playerID = 0;				
	}

	private void paintCurrentOfAll(Graphics g){
                int curX, curY;

                for(int id=0; id < 4; id++){
                        for(int pid=0; pid < 4; pid++){

                                if(cL.getIsOut(id, pid) == false)
                                        continue;

                                curX = cL.getCurX(id, pid);
                                curY = cL.getCurY(id, pid);

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
        }

	private void paintDie(Graphics g){
		
		g.setColor(Color.BLACK);

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(650, 260, 45, 45);

		g.setColor(Color.WHITE);
		g.fillRect(650, 260, 45, 45);

		g.setColor(Color.BLACK);
		if(value == 1){
			g.setColor(Color.RED);
			g.fillOval(661, 272, 23, 23);
		}

		else if(value == 2){
			g.fillOval(650, 260, 11, 11);
			g.fillOval(684, 283, 11, 11);
		}

		else if(value == 3){
			g.fillOval(650, 260, 11, 11);
			g.fillOval(667, 272, 11, 11);
			g.fillOval(684, 283, 11, 11);
		}

		else if(value == 4){
			g.fillOval(650, 260, 11, 11);
			g.fillOval(650, 283, 11, 11);
			g.fillOval(684, 260, 11, 11);
			g.fillOval(684, 283, 11, 11);
		}

		else if(value == 5){
			g.fillOval(650, 260, 11, 11);
			g.fillOval(650, 283, 11, 11);
			g.fillOval(667, 272, 11, 11);
			g.fillOval(684, 260, 11, 11);
			g.fillOval(684, 285, 11, 11);
		}

		else if(value == 6){
			g.fillOval(650, 260, 11, 11);
			g.fillOval(667, 260, 11, 11);
			g.fillOval(684, 260, 11, 11);
			g.fillOval(650, 285, 11, 11);
			g.fillOval(667, 285, 11, 11);
			g.fillOval(684, 285, 11, 11);
		}

	}

	//so that a particular player piece can be moved
	public void itemStateChanged(ItemEvent evt){
		for(int i=0; i<4; i++){
			if(evt.getSource() == piece[i] && evt.getStateChange() == 1)
				pieceID = i+1;
		}
	}


	//just rerolling
	public void mouseClicked(MouseEvent e){
		
		if((e.getX() >= 650 && e.getX() <= 695) && (e.getY() >= 260 && e.getY() <= 305)){
			value = cL.rollDie();
			lblPlayer.setText(cL.getColor(playerID) + " just played.");
			repaint();
	
			for(int i=0; i<4; i++){
				if(cL.getIsOut(playerID, i)==false)
					piece[i].setEnabled(false);
				else
					piece[i].setEnabled(true);
					piece[i].setSelected(true);
			}
		}
				

	}

	public void mouseExited(MouseEvent e){}

	public void mouseReleased(MouseEvent e){}

	public void mousePressed(MouseEvent e){}

	public void mouseEntered(MouseEvent e){}

/*
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setSize(800, 800);
		frame.setTitle("PROJECT LUDO");
		
		lblPlayer.setBounds(50, 50, 100, 100);
		ViewLudo vL = new ViewLudo();
		vL.add(lblPlayer);
		frame.setContentPane(vL);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
	}
*/
}
