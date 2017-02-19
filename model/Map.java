package model;

import java.util.Random;

public class Map {
	public Cell [][] mapBoard;
	private String [] resourceArray;
	private String [] terrainArray;
	private Random rand;

	public Map(){
		mapBoard = new Cell [32][25];
		resourceArray = new String [8];
		terrainArray = new String [5];
		rand = new Random(102343);
		//seed that fails cause of movement onto a mountain tile is restricted: 102216
		//seed that works 													  : 102343
		resources();
		terrain();
	}
	

//	public static void main(String[] args){
//		Map game = new Map();
//		Player dan = new Player();
//		game.fillMap();
//		Settler dan1 = new Settler(dan,game.mapBoard[5][5]);
//		dan1.moveUnit("north");
//		dan1.foundCity("cambridge");
//		System.out.println(game.toStringMap());
//		System.out.println(game.mapBoard[5][5].getTerrain() + "," + game.mapBoard[0][0].getResource());
//		System.out.println("food:" + game.mapBoard[5][5].getYieldFood());
//		System.out.println("production:" + game.mapBoard[5][5].getYieldProduction());
//		System.out.println("north: " + game.mapBoard[5][5].getNorth().getRow()+ "," + game.mapBoard[5][5].getNorth().getColumn());
//		System.out.println("south East: " + game.mapBoard[5][5].getSouthEast().getRow()+ "," + game.mapBoard[5][5].getSouthEast().getColumn());
//		System.out.println("held object: " + ((City) game.mapBoard[4][5].getHeldObject()).getName());
//		System.out.println("production: " +  ((City) game.mapBoard[4][5].getHeldObject()).getProduction());
//		System.out.println("food: " + ((City) game.mapBoard[4][5].getHeldObject()).getFood());
//	}
	

	private void resources(){
		resourceArray[0] = "copper";
		resourceArray[1] = "coal";
		resourceArray[2] = "stone";
		resourceArray[3] = "iron";
		resourceArray[4] = "citrus";
		resourceArray[5] = "cows";
		resourceArray[6] = "sheep";
		resourceArray[7] = "pigs";
	}
	private void terrain(){
		terrainArray[0] = "grassland";
		terrainArray[1] = "forest";
		terrainArray[2] = "hills";
		terrainArray[3] = "dessert";
		terrainArray[4] = "mountain";
	}

	public void fillMap(){
		for (int row = 0;row < mapBoard.length ; row++){
			for (int column = 0; column < mapBoard[row].length ; column++){
				Cell tile = new Cell(row,column);
				int valueTerrian = rand.nextInt(5);
				int valuePossible = rand.nextInt(20);
				int valueResource = rand.nextInt(8);

				switch (valueTerrian){
				case 0: tile.setTerrain(terrainArray[0]);break;
				case 1: tile.setTerrain(terrainArray[1]);break;
				case 2: tile.setTerrain(terrainArray[2]);break;
				case 3: tile.setTerrain(terrainArray[3]);break;
				case 4: tile.setTerrain(terrainArray[4]);break;
				}
				if(valuePossible == 0){
					switch (valueResource){
					case 0: tile.setResource(resourceArray[0]);break;
					case 1: tile.setResource(resourceArray[1]);break;
					case 2: tile.setResource(resourceArray[2]);break;
					case 3: tile.setResource(resourceArray[3]);break;
					case 4: tile.setResource(resourceArray[4]);break;
					case 5: tile.setResource(resourceArray[5]);break;
					case 6: tile.setResource(resourceArray[6]);break;
					case 7: tile.setResource(resourceArray[7]);break;

					}


				}
				tile.setYield();
				tile.setHeldObject(null);
				mapBoard[row][column] = tile;
			}
		}
		for (int row = 0;row < mapBoard.length ; row++){
			for (int column = 0; column < mapBoard[row].length ; column++){
//				mapBoard[row][column].setNorth(row, column);
//				mapBoard[row][column].setNorthEast(row, column);
//				mapBoard[row][column].setNorthWest(row, column);
//				mapBoard[row][column].setSouth(row, column);
//				mapBoard[row][column].setSouthEast(row, column);
//				mapBoard[row][column].setSouthWest(row, column);
				northLogic(row, column);
				southLogic(row, column);
				northWestLogic(row, column);
				northEastLogic(row, column);
				southWestLogic(row, column);
				southEastLogic(row, column);
			}
		}
	}
	public String toStringMap () {
		String result = "\n";

		// Print the grid line by line 
		for (int row=0; row < mapBoard.length; row++) {
			// Print each element in a line
			for (int column=0; column < mapBoard[row].length; column++)
				if(mapBoard[row][column].getResource() == null){
					result += mapBoard[row][column].getTerrain() + ",";
				}else{
					result += mapBoard[row][column].getTerrain() + "(" + mapBoard[row][column].getResource() + ")" + ",";
				}
			result += "\n";
		}

		return result;
	}
	public String toStringResource() {
		String result = "\n";

		for (int column=0; column < resourceArray.length; column++)
			result += resourceArray[column] + ",";

		return result;
	}
	public String toStringTerrain() {
		String result = "\n";

		for (int column=0; column < terrainArray.length; column++)
			result += terrainArray[column] + ",";

		return result;
	}
	
	public void northLogic(int row,int column){
		if(row == 0){
			mapBoard[row][column].setNorth(null);
		}
		else { 
			mapBoard[row][column].setNorth(mapBoard[row-1][column]);
			
		}
	}
	public void southLogic(int row,int column){
		if(row == 31){
			mapBoard[row][column].setSouth(null);
		}
		else { 
			mapBoard[row][column].setSouth(mapBoard[row+1][column]);
		}
	}

	public void northWestLogic(int row,int column){
		if(column == 0 || (row == 0 && column%2 ==0)){
			mapBoard[row][column].setNorthWest(null);
		}else if(column%2 == 0){
			mapBoard[row][column].setNorthWest(mapBoard[row-1][column-1]);
		}else {
			mapBoard[row][column].setNorthWest(mapBoard[row][column-1]);
		}
	}

	public void northEastLogic(int row,int column){
		if(column == 24 || (row == 0 && column%2 ==0)){
			mapBoard[row][column].setNorthEast(null);
		}else if(column%2 == 0){
			mapBoard[row][column].setNorthEast(mapBoard[row-1][column+1]);
		}else {
			mapBoard[row][column].setNorthEast(mapBoard[row][column+1]);
		}
	}

	public void southWestLogic(int row,int column){
		if(column == 0 || (row == 31 && column%2 == 1)){
			mapBoard[row][column].setSouthWest(null);
		}else if(column%2 == 0){
			mapBoard[row][column].setSouthWest(mapBoard[row][column-1]);
		}else {
			mapBoard[row][column].setSouthWest(mapBoard[row+1][column-1]);
		}
	}

	public void southEastLogic(int row,int column){
		if(column == 24 || (row == 31 && column%2 == 1)){
			mapBoard[row][column].setSouthEast(null);
		}else if(column%2 == 0){
			mapBoard[row][column].setSouthEast(mapBoard[row][column+1]);
		}else {
			mapBoard[row][column].setSouthEast(mapBoard[row+1][column+1]);
		}
	}
}
