import java.util.Random;
import java.awt.Color;

public class ControllerLudo{

	private Random rand;
	private int value;
	private PlayerLudo[] players = new PlayerLudo[4];

	public ControllerLudo(){
		rand = new Random();	

		init();
	}

	public void init(){
		int[] XposG = {240, 270, 300, 330, 360, 360, 360, 360, 360, 390, 420, 420, 420, 420, 420, 450, 480, 510, 540, 570, 570, 570, 540, 510, 480, 450, 420, 420, 420, 420, 420, 390, 360, 360, 360, 360, 360, 330, 300, 270, 240, 210, 210, 240, 270, 300, 330, 360};
		int[] YposG = {260, 260, 260, 260, 230, 200, 170, 140, 110, 110, 110, 140, 170, 200, 230, 260, 260, 260, 260, 260, 290, 320, 320, 320, 320, 320, 350, 380, 410, 440, 470, 470, 470, 440, 410, 380, 350, 320, 320, 320, 320, 320, 290, 290, 290, 290, 290, 290};
		players[0] = new PlayerLudo("green", XposG, YposG);

		int[] XposR = {420, 420, 420, 420, 450, 480, 510, 540, 570, 570, 570, 540, 510, 480, 450, 420, 420, 420, 420, 420, 390, 360, 360, 360, 360, 360, 330, 300, 270, 240, 210, 210, 210, 240, 270, 300, 330, 360, 360, 360, 360, 360, 390, 390, 390, 390, 390, 390};
		int[] YposR = {140, 170, 200, 230, 260, 260, 260, 260, 260, 290, 320, 320, 320, 320, 320, 350, 380, 410, 440, 470, 470, 470, 440, 410, 380, 350, 320, 320, 320, 320, 320, 290, 260, 260, 260, 260, 260, 230, 200, 170, 140, 110, 110, 140, 170, 200, 230, 260};
		players[1] = new PlayerLudo("red", XposR, YposR);

		int[] XposB = {540, 510, 480, 450, 420, 420, 420, 420, 420, 390, 360, 360, 360, 360, 360, 330, 300, 270, 240, 210, 210, 210, 240, 270, 300, 330, 360, 360, 360, 360, 360, 390, 420, 420, 420, 420, 420, 450, 480, 510, 540, 570, 570, 540, 510, 480, 450, 420};
		int[] YposB = {320, 320, 320, 320, 350, 380, 410, 440, 470, 470, 470, 440, 410, 380, 350, 320, 320, 320, 320, 320, 290, 260, 260, 260, 260, 260, 230, 200, 170, 140, 110, 110, 110, 140, 170, 200, 230, 260, 260, 260, 260, 260, 290, 290, 290, 290, 290, 290};
		players[2] = new PlayerLudo("blue", XposB, YposB);


		int[] XposY = {360, 360, 360, 360, 330, 300, 270, 240, 210, 210, 210, 240, 270, 300, 330, 360, 360, 360, 360, 360, 390, 420, 420, 420, 420, 420, 450, 480, 510, 540, 570, 570, 570, 540, 510, 480, 450, 420, 420, 420, 420, 420, 390, 390, 390, 390, 390, 390};
		int[] YposY = {440, 410, 380, 350, 320, 320, 320, 320, 320, 290, 260, 260, 260, 260, 260, 230, 200, 170, 140, 110, 110, 110, 140, 170, 200, 230, 260, 260, 260, 260, 260, 290, 320, 320, 320, 320, 320, 350, 380, 410, 440, 470, 470, 440, 410, 380, 350, 320};
		players[3] = new PlayerLudo("yellow", XposY, YposY);
 		
	}

	public int rollDie(){
		value = rand.nextInt(6)+1;

		return value;		
	}

	public String getColor(int id){
		return players[id].getColor();
	}

	public int getCurX(int id, int pid){
		int curX;
		curX = players[id].getCurrentX(pid);

		return curX;
	}

	public int getCurY(int id, int pid){

		return players[id].getCurrentY(pid);
	}

	public int getX(int id, int pid){
		int X;
		
		if(players[id].isHome(pid))
			X = getCurX(id, pid);
		else
			X = players[id].getX(pid);
		
		return X;
	}

	public int getY(int id, int pid){
		int Y;

		if(players[id].isHome(pid))
			Y = getCurY(id, pid);
		else			
			Y = players[id].getY(pid);
		return Y;
	}

	public void setIsOut(int id, int pid){
		players[id].setIsOut(true, pid);
	}

	public boolean getIsOut(int id, int pid){
		return players[id].getIsOut(pid);
	}
	public int getNumber(int id){
		return players[id].getNumber();
	}

	public void setNumber(int id){
		players[id].setNumber(players[id].getNumber()-1);
	}

	public void kickOut(int cID, int cPID){
		for(int oID=0; oID<4; oID++){
			if(cID == oID)
				continue;
			else{
				for(int oPID=0; oPID<4; oPID++){
					if(players[cID].getCurrentX(cPID) == players[oID].getCurrentX(oPID) && players[cID].getCurrentY(cPID) == players[oID].getCurrentY(oPID)){
						players[oID].setNumber(players[oID].getNumber()+1);
						players[oID].setIsOut(false, oPID);
						players[oID].setPosition(0, oPID);
					}	
				}
			}
		}
	}

	public boolean isHome(int id, int pid){
	 	return players[id].isHome(pid);
	}
	
}


