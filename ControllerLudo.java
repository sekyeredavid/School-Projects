import java.util.Random;
import java.awt.Color;

public class ControllerLudo{

	private Random rand;
	private int value;
	private PlayerLudo[] players = new PlayerLudo[4];
	private int[] sPX = new int[6];
	private int[] sPY = new int[6];

	public ControllerLudo(){
		players[0] = new PlayerLudo("green", 36);
		players[1] = new PlayerLudo("red", 3);
		players[2] = new PlayerLudo("blue", 14);
		players[3] = new PlayerLudo("yellow", 25);
		rand = new Random();	
		
		setSPValues();
		
	}

	public void setSPValues(){

		int j=0;
		for(int i=210; i<=360; i+=30){
			sPX[j] = i;
			j++;
		}
		for(int i=0; i<5; i++){ 
			sPY[i] = 290;
		}
		players[0].specialPositionX = sPX;
		players[0].specialPositionY = sPY;

		for(int i=0; i<5; i++){
			sPX[i] = 390;
		}
		j=0;
		for(int i=110; i<=260; i+=30){
			sPY[j] = i;
			j++;
		} 
		players[1].specialPositionX = sPX;
		players[1].specialPositionY = sPY;

		j = 0;
		for(int i=570; i>=420; i-=30){
			sPX[j] = i;
			j++;
		}
		for(int i=0; i<5; i++){
			sPY[i] = 290;
		}
		players[2].specialPositionX = sPX;
		players[2].specialPositionY = sPY;

		j = 0;
		for(int i=0; i<5; i++){
			sPX[i] = 390;
		}
		for(int i=470; i>=320; i-=30){
			sPY[j] = i;
			j++;
		}
		players[3].specialPositionX = sPX;
		players[3].specialPositionY = sPY;

	}

	public int rollDie(){
		value = rand.nextInt(6)+1;

		return value;		
	}

	public int getCurX(int id){
		int curX;
		curX = players[id].getCurrentX();
		
		if(players[id].getPosition() == 44)
			players[id].setPosition(0);

		return curX;
	}

	public int getCurY(int id){
		return players[id].getCurrentY();
	}

	public int getX(int id){
		int X;

		X = players[id].getX();
		players[id].setMoves(players[id].getMoves()+1);
		
		return X;
	}

	public int getY(int id){
		return players[id].getY();
	}

	public int getNumber(int id){
		return players[id].getNumber();
	}

	public void setNumber(int id){
		players[id].setNumber(players[id].getNumber()-1);
	}

	public void kickOut(int cID){
		for(int oID=0; oID<4; oID++){
			if(cID == oID)
				continue;
			else{
				if(players[cID].getPosition() == players[oID].getPosition()){
					players[oID].setNumber(players[oID].getNumber()+1);
					players[oID].setPosition(players[oID].START_POSITION);
					players[oID].setMoves(0);
				}	

			}
		}
	}
	
}


