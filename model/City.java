package model;

public class City {
	private String name;
	private int attack;
	private int defence;
	private int health;
	private int population;
	private int production;
	private int food;
	private int goldPerTurn;
	private int range;
	private Player player;
	private boolean canAttack;
	private Cell location;
	private Building [] buildingInCity;
	private Object [] productionQueue;
	private Object [] possibleBuildings;
	
	public City(String cityName,Player player,Cell location){
		this.name = cityName;
		this.attack = 35;
		this.defence = 10;
		this.health = 300;
		this.population = 1;
		this.goldPerTurn = 2;
		this.range = 3;
		this.player = player;
		this.canAttack = true;
		this.location = location;
		setProduction();
		setFood();
	}
	
	public void borderSet(){
		//not done yet
	}
	
	public void productionQueue(){
		//not done yet
	}
	
	public void setProduction(){
		// need a try catch for when a city is at the edge of the map.
		int total = location.getYieldProduction();
		total = total + location.getNorth().getYieldProduction()
				+ location.getNorth().getNorth().getYieldProduction()
				+ location.getNorth().getNorthEast().getYieldProduction();
		total = total + location.getNorthEast().getYieldProduction()
				+ location.getNorthEast().getNorthEast().getYieldProduction()
				+ location.getNorthEast().getSouthEast().getYieldProduction();
		total = total + location.getSouthEast().getYieldProduction()
				+ location.getSouthEast().getSouthEast().getYieldProduction()
				+ location.getSouthEast().getSouth().getYieldProduction();
		total = total + location.getSouth().getYieldProduction()
				+ location.getSouth().getSouth().getYieldProduction()
				+ location.getSouth().getSouthWest().getYieldProduction();
		total = total + location.getSouthWest().getYieldProduction()
				+ location.getSouthWest().getSouthWest().getYieldProduction()
				+ location.getSouthWest().getNorthWest().getYieldProduction();
		total = total + location.getNorthWest().getYieldProduction()
				+ location.getNorthWest().getNorthWest().getYieldProduction()
				+ location.getNorthWest().getNorth().getYieldProduction();

		production = total;
	}
	public int getProduction(){
		return production;
	}
	public void setFood(){
		int total = location.getYieldProduction();
		total = total + location.getNorth().getYieldFood()
				+ location.getNorth().getNorthEast().getYieldFood()
				+ location.getNorth().getNorth().getYieldFood();
		total = total + location.getNorthEast().getYieldFood() 
				+ location.getNorthEast().getNorthEast().getYieldFood()
				+ location.getNorthEast().getSouthEast().getYieldFood();
		total = total + location.getSouthEast().getYieldFood() 
				+ location.getSouthEast().getSouthEast().getYieldFood()
				+ location.getSouthEast().getSouth().getYieldFood();
		total = total + location.getSouth().getYieldFood()
				+ location.getSouth().getSouth().getYieldFood()
				+ location.getSouth().getSouthWest().getYieldFood();
		total = total + location.getSouthWest().getYieldFood()
				+ location.getSouthWest().getSouthWest().getYieldFood()
				+ location.getSouthWest().getNorthWest().getYieldFood();
		total = total + location.getNorthWest().getYieldFood()
				+ location.getNorthWest().getNorthWest().getYieldFood()
				+ location.getNorthWest().getNorth().getYieldFood();
		food = total;
	}
	public int getFood(){
		return food;
	}
	
	public void setPopulation(){
		//not done
	}

	public int getHealth() {
		return health;
	}

	public int getDefence() {
		return defence;
	}
	public String getName(){
		return name;
	}

	public int getAttack() {
		
		return attack;
	}

	public void setHealth(int health) {
		this.health = health;
	}

}
