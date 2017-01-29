package model;

public class ChariotArcher extends Unit{
	
	public ChariotArcher(Player player,Cell place){
		super.attack = 40;
		super.defence = 15;
		super.health = 60;
		super.movementLeft = 4;
		super.maxMovement = 4;
		super.range = 2;
		super.melee = false;
		super.taxCost = 1;
		super.canMove = true;
		super.produceCost = 40;
		super.player = player;
		super.location = place;
		
	}

}
