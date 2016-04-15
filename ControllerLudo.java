import java.util.Random;
import java.awt.Color;
import java.io.Serializable;

public class ControllerLudo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1044890387729983854L;
	private Random rand;
	private int value;
	private PlayerLudo[] players = new PlayerLudo[4];

	public ControllerLudo(){
		rand = new Random();	

		init();
	}

	//initialising each players position on the board, for their movement paths
	public void init(){
		int[] XposG = {210, 240, 270, 300, 330, 360, 360, 360, 360, 360, 360, 390, 420, 420, 420, 420, 420, 420, 450, 480, 510, 540, 570, 600, 600, 600, 570, 540, 510, 480, 450, 420, 420, 420, 420, 420, 420, 390, 360, 360, 360, 360, 360, 360, 330, 300, 270, 240, 210, 180, 180, 240, 270, 300, 330, 360};
		int[] YposG = {260, 260, 260, 260, 260, 230, 200, 170, 140, 110, 80, 80, 80, 110, 140, 170, 200, 230, 260, 260, 260, 260, 260, 260, 290, 320, 320, 320, 320, 320, 320, 350, 380, 410, 440, 470, 500, 500, 500, 470, 440, 410, 380, 350, 320, 320, 320, 320, 320, 320, 290, 290, 290, 290, 290, 290, 290};
		players[0] = new PlayerLudo("green", XposG, YposG);

		int[] XposR = {420, 420, 420, 420, 420, 450, 480, 510, 540, 570, 600, 600, 600, 570, 540, 510, 480, 450, 420, 420, 420, 420, 420, 420, 390, 360, 360, 360, 360, 360, 360, 330, 300, 270, 240, 210, 180, 180, 180, 210, 240, 270, 300, 330, 360, 360, 360, 360, 360, 360, 390, 390, 390, 390, 390, 390, 390};
		int[] YposR = {110, 140, 170, 200, 230, 260, 260, 260, 260, 260, 260, 290, 320, 320, 320, 320, 320, 320, 350, 380, 410, 440, 470, 500, 500, 500, 470, 440, 410, 380, 350, 320, 320, 320, 320, 320, 320, 290, 260, 260, 260, 260, 260, 260, 230, 200, 170, 140, 110, 80, 80, 110, 140, 170, 200, 230, 260};
		players[1] = new PlayerLudo("red", XposR, YposR);

		int[] XposB = {570, 540, 510, 480, 450, 420, 420, 420, 420, 420, 420, 390, 360, 360, 360, 360, 360, 360, 330, 300, 270, 240, 180, 180, 180, 210, 240, 270, 300, 330, 360, 360, 360, 360, 360, 360, 390, 420, 420, 420, 420, 420, 420, 450, 480, 510, 540, 570, 600, 600, 570, 540, 510, 480, 450, 420};
		int[] YposB = {320, 320, 320, 320, 320, 350, 380, 410, 440, 470, 500, 500, 500, 470, 440, 410, 380, 350, 320, 320, 320, 320, 320, 320, 290, 260, 260, 260, 260, 260, 260, 230, 200, 170, 140, 110, 80, 80, 80, 110, 140, 170, 200, 230, 260, 260, 260, 260, 260, 260, 290, 290, 290, 290, 290, 290, 290};
		players[2] = new PlayerLudo("blue", XposB, YposB);


		int[] XposY = {360, 360, 360, 360, 360, 330, 300, 270, 240, 210, 180, 180, 180, 210, 240, 270, 300, 330, 360, 360, 360, 360, 360, 360, 390, 420, 420, 420, 420, 420, 420, 450, 480, 510, 540, 570, 600, 600, 600, 570, 540, 510, 480, 450, 420, 420, 420, 420, 420, 420, 390, 390, 390, 390, 390, 390, 390};
		int[] YposY = {470, 440, 410, 380, 350, 320, 320, 320, 320, 320, 320, 290, 260, 260, 260, 260, 260, 260, 230, 200, 170, 140, 110, 80, 80, 80, 110, 140, 170, 200, 230, 260, 260, 260, 260, 260, 260, 290, 320, 320, 320, 320, 320, 320, 350, 380, 410, 440, 470, 500, 500, 470, 440, 410, 380, 350, 320};
		players[3] = new PlayerLudo("yellow", XposY, YposY);
 		
	}

	public int rollDie(){
		value = rand.nextInt(6)+1;

		return value;		
	}

	//get which players turn it is
	public String getColor(int id){
		return players[id].getColor();
	}

	public int getCurX(int id, int pid){
		return players[id].getCurrentX(pid);
	}

	public int getCurY(int id, int pid){
		return players[id].getCurrentY(pid);
	}

	public int getX(int value, int id, int pid){
		int X;
	
		try{	
			X = players[id].getX(value, pid);
		}catch(ArrayIndexOutOfBoundsException aiooe){
			players[id].setPosition(players[id].getPosition(pid)-value, pid);
			X = getCurX(id, pid); 
		}
		
		return X;
	}


	public int getY(int id, int pid){
		int Y;
	
		Y = players[id].getY(pid);
		return Y;
	}

	//release a player from hiding unto the board
	public void setIsOut(int id, int pid){
		players[id].setIsOut(true, pid);
		players[id].setPosition(0, pid);
	}

	//check if a player is already on the board
	public boolean getIsOut(int id, int pid){
		return players[id].getIsOut(pid);
	}

	//get current number of player pieces on the board
	public int getNumber(int id){
		return players[id].getNumber();
	}

	//reduce number of player pieces in hiding by 1 and put it on board
	public void setNumber(int id){
		players[id].setNumber(players[id].getNumber()-1);
	}

	//function to kick out if one player piece lands on anothers position
	//cID - current playerID
	//cPID - current player pieceID
	//o is other with same parameters
	public void kickOut(int cID, int cPID){
		for(int oID=0; oID<4; oID++){
			//looping so if same player piece skip
			if(cID == oID)
				continue;
			else{
				for(int oPID=0; oPID<4; oPID++){

					if(players[oID].getIsOut(oPID) == false)
						continue;

					if(players[cID].getCurrentX(cPID) == players[oID].getCurrentX(oPID) && players[cID].getCurrentY(cPID) == players[oID].getCurrentY(oPID)){
						players[oID].setNumber(players[oID].getNumber()+1);
						players[oID].setIsOut(false, oPID);
					}	
				}
			}
		}
	}

	//check if player piece is home
	public boolean isHome(int id, int pid){
	 	return players[id].isHome(pid);
	}
	
}


