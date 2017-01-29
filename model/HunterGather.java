package model;

public class HunterGather extends Unit {
	
	public HunterGather(Player player,Cell place){
		super.attack = 1;
		super.defence = 10;
		super.health = 20;
		super.movementLeft = 2;
		super.maxMovement = 2;
		super.range = 1;
		super.melee = true;
		super.taxCost = 0;
		super.canMove = true;
		super.produceCost = 40;
		super.player = player;
		super.location = place;
		
	}
	
	public void harvestResource(){
		
	}

}
