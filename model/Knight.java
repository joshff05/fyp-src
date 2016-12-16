package model;

public class Knight extends Unit {

	public Knight(Player player,int x,int y){
		super.attack = 40;
		super.defence = 15;
		super.health = 60;
		super.movementLeft = 4;
		super.maxMovement = 4;
		super.range = 1;
		super.melee = true;
		super.taxCost = 1;
		super.canMove = true;
		super.produceCost = 60;
		super.player = player;
		super.cellX = x;
		super.cellY = y;
		
		
	}
}
