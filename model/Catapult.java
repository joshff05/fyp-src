package model;

public class Catapult extends Unit{
	
	public Catapult(Player player,Cell place){
		super.attack = 35;
		super.defence = 10;
		super.health = 30;
		super.movementLeft = 2;
		super.maxMovement = 2;
		super.range = 2;
		super.melee = false;
		super.taxCost = 1;
		super.canMove = true;
		super.produceCost = 60;
		super.player = player;
		super.location = place;
		
	}
	
	@Override
	public void attackCity(Unit attacker,City defender){
		//bonus damage to cities
	}

}
