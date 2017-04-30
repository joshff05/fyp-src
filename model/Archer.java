package model;

public class Archer extends Unit {

	public Archer(Player player,Cell place){
		super.attack = 30;
		super.defence = 15;
		super.health = 60;
		super.movementLeft = 2;
		super.maxMovement = 2;
		super.range = 2;
		super.melee = false;
		super.taxCost = 1;
		super.canMove = true;
		//produceCost = 30;
		super.player = player;
		super.location = place;
		super.type = "archer";
		location.setHeldObject(this);
		
	}
}
