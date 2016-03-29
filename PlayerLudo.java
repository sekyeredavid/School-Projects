

public class PlayerLudo{

	public int[] specialPositionX = new int[6];	
	public int[] specialPositionY = new int[6];
	private int position;
	private static int[] allPositionsX = {360, 390, 420, 420, 420, 420, 420, 450, 480, 510, 540, 570, 570, 570, 540, 510, 480, 450, 420, 420, 420, 420, 420, 390, 360, 360, 360, 360, 360, 330, 300, 270, 240, 210, 210, 210, 240, 270, 300, 330, 360, 360, 360, 360};
	private static int[] allPositionsY = {110, 110, 110, 140, 170, 200, 230, 260, 260, 260, 260, 260, 290, 320, 320, 320, 320, 320, 350, 380, 410, 440, 470, 470, 470, 440, 410, 380, 350, 320, 320, 320, 320, 320, 290, 260, 260, 260, 260, 260, 230, 200, 170, 140};
	private int moves = 0;
	private boolean home = false;
	private String color;
	private int number = 4;
	public final int START_POSITION;
	private int specialPosition = 0;

	public PlayerLudo(String color, int position){
		this.color = color;
		this.position = position;
		this.START_POSITION = position;
	
	}

	public int getMoves(){
		return moves;
	}

	public void setMoves(int moves){
		this.moves = moves;
	}
	
	public int getStartPosition(){
		return START_POSITION;	
	}

	public void setPosition(int position){
		this.position =  position;
	}

	public int getPosition(){
		return position;
	}

	public int getCurrentX(){

		if(home)
			return specialPositionX[specialPosition];

		if(position == 44)
			position = 0;

		if(moves >= 42){
			return specialPositionX[specialPosition];
		}
		else	
			return allPositionsX[position];
	}

	public int getCurrentY(){
		return allPositionsY[position];
	}

	public int getX(){
		position += 1;

		if(specialPosition == 5)
			home = true;

		if(home)
			return specialPositionX[specialPosition];

		if(position == 44)
			position = 0;

		if(moves >= 42){
			specialPosition += 1;
			return specialPositionX[specialPosition];
		}
		else
			return allPositionsX[position];
	}

	public int getY(){
		return allPositionsY[position];
	}

	public boolean getHome(){
		return home;
	}

	public void setHome(boolean home){
		this.home = home;
	}

	public String getColor(){
		return color;
	}
	
	public int getNumber(){
		return number;
	}

	public void setNumber(int number){
		this.number = number;
	}

	public void setSpecialPositionsX(int[] specialPositionX){
		this.specialPositionX = specialPositionX;
	}

	public void setSpecialPositionsY(int[] specialPositionY){
		this.specialPositionY = specialPositionY;
	}
}

