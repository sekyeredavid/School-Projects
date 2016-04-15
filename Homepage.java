import java.awt.Font;
import java.awt.BorderLayout;

import javax.swing.*;

import java.awt.event.*;
import java.io.Serializable;

public class Homepage extends JFrame implements ActionListener,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnlMain;
	private JLabel lblTitle;
	private JButton btnNew, btnContinue;
	public ViewLudo vf = new ViewLudo();

	public Homepage(){
		super("LUDO++");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pnlMain = new JPanel();
		lblTitle = new JLabel("LUDO++");
		lblTitle.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 45));

		btnNew = new JButton("New Game");
		btnContinue = new JButton("Continue");
		
		pnlMain.add(lblTitle);
		pnlMain.add(btnNew);
		pnlMain.add(btnContinue);

		add(pnlMain);
		setVisible(true);
		setResizable(false);

		btnNew.addActionListener(this);
		btnContinue.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btnNew){
			setVisible(false);	
			JFrame frame = new JFrame();
			
			frame.setContentPane(vf);

			frame.setVisible(true);
			frame.setResizable(false);
			frame.setSize(800, 800);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			frame.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					if(JOptionPane.showConfirmDialog(frame, "Would you like to save the current game?", "Quit game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
						//saveMe();
						SaveGame.save(vf);  //save the viewludo object and all its changes
						JOptionPane.showMessageDialog(null, "Saved");
					}else{
						JOptionPane.showMessageDialog(null, "See You Soon");
					}
				}
			});
			dispose();
		}

		else if(e.getSource() == btnContinue){
			//gonna call ViewLudo with the loaded data
			//might have to create another constructor in ViewLudo for just that purpose
			
			setVisible(false);	
			JFrame frame = new JFrame();
			ViewLudo vt = SaveGame.load();   //load content into a viewludo object
			frame.setContentPane(vt);   //set the content pane to this object
			//Homepage page = ;
			
			//vt = null;
			//frame.setContentPane(new ViewLudo());

			frame.setVisible(true);
			frame.setResizable(false);
			frame.setSize(800, 800);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			frame.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					if(JOptionPane.showConfirmDialog(frame, "Would you like to save the current game?", "Quit game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
						
						//saveMe();
						SaveGame.save(vf);  //save the viewludo object and all its changes
						JOptionPane.showMessageDialog(null, "Saved");
						
					}else{
						JOptionPane.showMessageDialog(null, "See You Soon");
					}
				}
			});
			dispose();
			
		}
	}
	public static void main(String[] args){
		new Homepage();
	}

}
