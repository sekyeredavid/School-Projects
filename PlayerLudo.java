public class PlayerLudo{

	private int[] position = new int[4];
	private int[] allPositionsX = new int[47];
	private int[] allPositionsY = new int[47];
	private boolean[] home = new boolean[4];
	private String color;
	private int number = 4;
	public final int START_POSITION = 0;
	public boolean[] isOut = new boolean[4];
	private int pieceID;

	public PlayerLudo(String color, int[] Xpos, int[] Ypos){

		this.color = color;
		allPositionsX = Xpos;
		allPositionsY = Ypos;
	
	}

	public void setIsOut(boolean isOut, int pieceID){
		this.isOut[pieceID] = isOut;
	}

	public boolean getIsOut(int pieceID){
		return isOut[pieceID];
	}
	
	public int getStartPosition(){
		return START_POSITION;	
	}

	public void setPosition(int position, int pieceID){
		this.position[pieceID] = position;
	}

	public int getPosition(int pieceID){
		return position[pieceID];
	}

	public int getCurrentX(int pieceID){
		if(position[pieceID] == 47)
			home[pieceID] = true;
		return allPositionsX[position[pieceID]];
	}

	public int getCurrentY(int pieceID){
		return allPositionsY[position[pieceID]];
	}

	public int getX(int pieceID){
		position[pieceID] += 1;
	
		return allPositionsX[position[pieceID]];
	
	}

	public int getY(int pieceID){
		return allPositionsY[position[pieceID]];
	}

	public void setHome(boolean home, int pieceID){
		this.home[pieceID] = home;
	}

	public boolean isHome(int pieceID){ 
		return home[pieceID];
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
}

