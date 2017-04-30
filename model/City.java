package model;

public class City {
	private String name;
	private int attack;
	private int defence;
	private int health;
	private int population;
	private int maxPopulation = 19;
	private int totalProduction;
	private int productionBank;
	private int productionPerPopulation;
	private int totalFood;
	private int foodBank;
	private int foodPerPopulation;
	private int foodDemand;
	private int goldPerTurn;//may not use
	private Player player;
	private Cell location;
	private Building [] buildingInCity;
	private Object [] productionQueue;
	private Object [] possibleBuildings;
	public String type;
	
	public City(String cityName,Player player,Cell location){
		this.name = cityName;
		this.attack = 35;
		this.defence = 10;
		this.health = 300;
		this.population = 1;
		this.goldPerTurn = 2;
		this.player = player;
		this.location = location;
		this.foodDemand = 10;
		setProduction();
		setFood();
		setconsumablesPerPop();
		this.type = "City";
	}
	
	public void borderSet(){
		//not done yet
	}
	
	public void productionQueue(){
		//not done yet
		//change amount based on build cost
	}
	//checked at the end of everyturn
	public void updatePopulation(){
		if(foodBank >= foodDemand){
			population++;
			foodBank = foodBank - foodDemand;
			foodDemand = (int) (foodDemand * 1.5);
			setconsumablesPerPop();
		}
	}
	
	public void addTofoodBank(){
		foodBank = foodBank + foodPerPopulation;
	}
	
	public void addToProdBank(){
		productionBank = productionBank + productionPerPopulation;
	}
	
	public void subtractProdBank(int value){
		productionBank = productionBank - value;
	}
	
	public void setconsumablesPerPop(){
		productionPerPopulation = (totalProduction / maxPopulation) * population;
		if(productionPerPopulation < 1){
			productionPerPopulation = 1;
		}
		foodPerPopulation = (totalFood/maxPopulation) * population;
		if(foodPerPopulation <1){
			foodPerPopulation = 1;
		}
	}
	
	public void setProduction(){
		// need a try catch for when a city is at the edge of the map.
		int total = location.getYieldProduction();
		try {
			total = total + location.getNorth().getYieldProduction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getNorth().getNorth().getYieldProduction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getNorth().getNorthEast().getYieldProduction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getNorthEast().getYieldProduction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getNorthEast().getNorthEast().getYieldProduction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getNorthEast().getSouthEast().getYieldProduction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getSouthEast().getYieldProduction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getSouthEast().getSouthEast().getYieldProduction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getSouthEast().getSouth().getYieldProduction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getSouth().getYieldProduction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getSouth().getSouth().getYieldProduction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getSouth().getSouthWest().getYieldProduction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getSouthWest().getYieldProduction();
		} catch (Exception e) {
			// TODO Auto-generated catch bloc
		}
		try {
			total = total + location.getSouthWest().getSouthWest().getYieldProduction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getSouthWest().getNorthWest().getYieldProduction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getNorthWest().getYieldProduction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getNorthWest().getNorthWest().getYieldProduction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getNorthWest().getNorth().getYieldProduction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}

		totalProduction = total;
	}
	public int getProduction(){
		return totalProduction;
	}
	public void setFood(){
		int total = location.getYieldProduction();
		try {
			total = total + location.getNorth().getYieldFood();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getNorth().getNorthEast().getYieldFood();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getNorth().getNorth().getYieldFood();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getNorthEast().getYieldFood() ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getNorthEast().getNorthEast().getYieldFood();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getNorthEast().getSouthEast().getYieldFood();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getSouthEast().getYieldFood() ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getSouthEast().getSouthEast().getYieldFood();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getSouthEast().getSouth().getYieldFood();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getSouth().getYieldFood();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getSouth().getSouth().getYieldFood();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getSouth().getSouthWest().getYieldFood();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getSouthWest().getYieldFood();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getSouthWest().getSouthWest().getYieldFood();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getSouthWest().getNorthWest().getYieldFood();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getNorthWest().getYieldFood();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getNorthWest().getNorthWest().getYieldFood();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			total = total + location.getNorthWest().getNorth().getYieldFood();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		totalFood = total;
	}
	public int getFood(){
		return totalFood;
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
	public String getType(){
		return type;
	}
	
	public Player getPlayer(){
		return player;
	}
	public int getProductionBank(){
		return productionBank;
	}
	public int getPopulation(){
		return population;
	}

}
