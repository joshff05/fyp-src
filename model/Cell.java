package model;

public class Cell {
	private Cell north;
	private Cell northWest;
	private Cell northEast;
	private Cell south;
	private Cell southWest;
	private Cell southEast;
	
	private String terrain;
	private String resource;
	private int row;
	private int column;
	private int yieldProduction;
	private int yieldFood;
	private Object heldObject;
	

	//add yields, cities/units to list.
	//hopefully can use these for movements
	//for start of game find resource and place settler within 2 of it. players should not share resource.
	//linked list with nodes
	//will be easier to retrieve cells and the yileds
	//round in a clockwise direction. 
	//set borders will allow to get city yields.
	//"doubly" linked list
	//hold an object
	
	public Cell(int row, int column){
		this.row = row;
		this.column = column;
	}

	public void setTerrain(String t){
		terrain = t;
	}
	public String getTerrain(){
		return terrain;
	}
	public void setResource(String r){
		resource = r;
	}
	public String getResource(){
		return resource;
	}
	
	public void setHeldObject(Object object){
		heldObject = object;
	}
	public Object getHeldObject(){
		return heldObject;
	}

	public void setYieldProduction(int production){
		yieldProduction = production;
	}

	public int getYieldProduction (){
		return yieldProduction;
	}

	public void setYieldFood(int food){
		yieldFood = food;
	}

	public int getYieldFood(){
		return yieldFood;
	}

	public void setYield(){
		switch(getTerrain()){
		case "grassland" : setYieldProduction(0) ;
		setYieldFood(2); break;
		case "forest" : setYieldProduction(2) ;
		setYieldFood(2); break;
		case "hills" : setYieldProduction(2);
		setYieldFood(1);break;
		case "dessert": setYieldProduction(1);
		setYieldFood(0);break;
		case "mountain": setYieldProduction(2);
		setYieldFood(0); break;
		}

		if(getResource() != null){
			switch(getResource()){
			default : break; 
			case "copper" : setYieldProduction(getYieldProduction() + 1);break;
			case "coal" : setYieldProduction(getYieldProduction() + 2); break;
			case "stone" : setYieldProduction(getYieldProduction() + 1);break;
			case "iron" : setYieldProduction(getYieldProduction() + 2);break;
			case "citrus" : setYieldFood(getYieldFood() + 1);break;
			case "cows" : setYieldFood(getYieldFood() + 2);break;
			case "sheep" : setYieldFood(getYieldFood() + 1);break;
			case "pigs" : setYieldFood(getYieldFood() + 2);break;
			}
		}
	}

	public void setNorth(Cell connection){
		north = connection;
	}
	public void setSouth(Cell connection){
		south = connection;
	}
	public void setNorthWest(Cell connection){
		northWest = connection;
	}
	public void setNorthEast(Cell connection){
		northEast = connection;
	}
	public void setSouthWest(Cell connection){
		southWest = connection;
	}
	public void setSouthEast(Cell connection){
		southEast = connection;
	}

	public int getRow(){
		return row;
	}
	
	public int getColumn(){
		return column;
	}
	
	public Cell getNorth(){
		return north;
	}
	public Cell getSouth(){
		return south;
	}
	public Cell getNorthWest(){
		return northWest;
	}
	public Cell getNorthEast(){
		return northEast;
	}
	public Cell getSouthWest(){
		return southWest;
	}
	public Cell getSouthEast(){
		return southEast;
	}
}
