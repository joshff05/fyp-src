package model;

public class Spearman extends Unit {

	public Spearman(Player player,Cell place){
		super.attack = 35;
		super.defence = 25;
		super.health = 60;
		super.movementLeft = 2;
		super.maxMovement = 2;
		super.range = 1;
		super.melee = true;
		super.taxCost = 1;
		super.canMove = true;
		super.produceCost = 50;
		super.player = player;
		super.location = place;
		
		//bonus vs knights
		
	}
}
