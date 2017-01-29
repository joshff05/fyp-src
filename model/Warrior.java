package model;

public class Warrior extends Unit {

	public Warrior(Player player,Cell place){
		super.attack = 30;
		super.defence = 20;
		super.health = 60;
		super.movementLeft = 2;
		super.maxMovement = 2;
		super.range = 1;
		super.melee = true;
		super.taxCost = 1;
		super.canMove = true;
		super.produceCost = 30;
		super.player = player;
		super.location = place;
		
	}
}
