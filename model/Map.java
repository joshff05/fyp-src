package model;

import java.util.Random;

public class Map {
	public Cell [][] mapBoard;
	private String [] resourceArray;
	private String [] terrainArray;
	private Random rand;

	public Map(){
		mapBoard = new Cell [32][19];
		resourceArray = new String [8];
		terrainArray = new String [5];
		rand = new Random();
		//seed that fails cause of movement onto a mountain tile is restricted: 102216
		//seed that works 													  : 150009
		resources();
		terrain();
	}
	

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
				int valuePossible = rand.nextInt(15);
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
		if(column == 0){
			mapBoard[row][column].setNorth(null);
		}
		else { 
			mapBoard[row][column].setNorth(mapBoard[row][column-1]);
			
		}
	}
	public void southLogic(int row,int column){
		if(column == 18){
			mapBoard[row][column].setSouth(null);
		}
		else { 
			mapBoard[row][column].setSouth(mapBoard[row][column+1]);
		}
	}

	public void northWestLogic(int row,int column){
		if(row == 0 || (column == 0 && row%2 ==0)){
			mapBoard[row][column].setNorthWest(null);
		}else if(row%2 == 0){
			mapBoard[row][column].setNorthWest(mapBoard[row-1][column-1]);
		}else {
			mapBoard[row][column].setNorthWest(mapBoard[row-1][column]);
		}
	}

	public void northEastLogic(int row,int column){
		if(row == 31 || (column == 0 && row%2 ==0)){
			mapBoard[row][column].setNorthEast(null);
		}else if(row%2 == 0){
			mapBoard[row][column].setNorthEast(mapBoard[row+1][column-1]);
		}else {
			mapBoard[row][column].setNorthEast(mapBoard[row+1][column]);
		}
	}

	public void southWestLogic(int row,int column){
		if(row == 0 || (column == 18 && row%2 == 1)){
			mapBoard[row][column].setSouthWest(null);
		}else if(row%2 == 0){
			mapBoard[row][column].setSouthWest(mapBoard[row-1][column]);
		}else {
			mapBoard[row][column].setSouthWest(mapBoard[row-1][column+1]);
		}
	}

	public void southEastLogic(int row,int column){
		if(row == 31 || (column == 18 && row%2 == 1)){
			mapBoard[row][column].setSouthEast(null);
		}else if(row%2 == 0){
			mapBoard[row][column].setSouthEast(mapBoard[row+1][column]);
		}else {
			mapBoard[row][column].setSouthEast(mapBoard[row+1][column+1]);
		}
	}
}
