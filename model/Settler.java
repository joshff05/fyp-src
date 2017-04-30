package model;

public class Settler extends Unit {
	
	public Settler(Player player,Cell place){
		super.attack = 1;
		super.defence = 10;
		super.health = 20;
		super.movementLeft = 2;
		super.maxMovement = 2;
		super.range = 1;
		super.melee = true;
		super.taxCost = 0;
		super.canMove = true;
		//produceCost = 50;
		super.player = player;
		super.location = place;
		super.type = "settler";
		location.setHeldObject(this);
		
	}
	
	public void foundCity(String cityName){
		City city = new City(cityName,player,location);
		location.setHeldObject(city);
		player.removeUnit(this);
		player.addCity(city);
	}

}
