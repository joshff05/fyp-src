package model;

public class Unit {

	protected int attack;
	protected int defence;
	protected int health;
	protected int movementLeft;
	protected int maxMovement;
	protected int range;
	protected Player player;
	protected boolean melee;
	protected int taxCost;
	protected boolean canMove;
	protected boolean canAttack;
	protected int produceCost;
	protected Cell location;
	//may need to change costs and stats for balance
	//could a city be classed as a stationary unit.
	//need to get cells instead of x and y values
	
	//add mountain detection for all, add getlocation for units
	public void moveUnit(String direction){
		if(canMove = true){
			Cell current = location;
			if(direction == "north"){
				if(location.getNorth().getTerrain() != "mountain"){
					if(location.getNorth().getHeldObject() == null){
						location.getNorth().setHeldObject(location.getHeldObject());
						location = location.getNorth();
						current.setHeldObject(null);
						movementLeft = movementLeft - 1;
					}
				}
			}
			if(direction == "northEast"){
				if(location.getNorthEast().getHeldObject() == null){
					location.getNorthEast().setHeldObject(location.getHeldObject());
					location = location.getNorthEast();
					current.setHeldObject(null);
					movementLeft=movementLeft - 1;
				}
			}
			if(direction == "southEast"){
				if(location.getSouthEast().getHeldObject() == null){
					location.getSouthEast().setHeldObject(location.getHeldObject());
					location = location.getSouthEast();
					current.setHeldObject(null);
					movementLeft = movementLeft - 1;
				}
			}
			if(direction == "south"){
				if(location.getSouth().getHeldObject() == null){
					location.getSouthEast().setHeldObject(location.getHeldObject());
					location = location.getSouth();
					current.setHeldObject(null);
					movementLeft = movementLeft - 1;
				}
			}
			if(direction == "southWest"){
				if(location.getSouthWest().getHeldObject() == null){
					location.getSouthWest().setHeldObject(location.getHeldObject());
					location = location.getSouthWest();
					current.setHeldObject(null);
					movementLeft = movementLeft - 1;
				}
			}
			if(direction == "northWest"){
				if(location.getNorthWest().getHeldObject() == null){
					location.getNorthWest().setHeldObject(location.getHeldObject());
					location = location.getNorthWest();
					current.setHeldObject(null);
					movementLeft = movementLeft - 1;
				}
			}
		}
	}

	protected void attackUnit(){

	}

	protected void attackCity(Unit attacker,City defender){

	}

	protected void calculateDamage(Unit attacker,Unit defender){
		if (attacker.getAttack() > defender.getDefence()){
			defender.setHealth((defender.getHealth()-(attacker.getAttack()-defender.getDefence())));
			if (attacker.getMelee() == true){
				attacker.setHealth((attacker.getHealth()-(defender.getAttack()-defender.getDefence())));
			}
		}
	}
//change these to have a loction to know what is in the held item, to then work out the damage values
	protected void calculateDamage(Unit attacker,City defender){
		if (attacker.getAttack() > defender.getDefence()){
			defender.setHealth((defender.getHealth()-(attacker.getAttack()-defender.getDefence())));
			if (attacker.getMelee() == true){
				attacker.setHealth((attacker.getHealth()-(defender.getAttack()-defender.getDefence())));
			}
		}
	}

	protected void calculateDamage(City attacker,Unit defender){
		if (attacker.getAttack() > defender.getDefence()){
			defender.setHealth((defender.getHealth()-(attacker.getAttack()-defender.getDefence())));
		}
	}

	private int getAttack(){
		return attack;
	}

	private int getDefence(){
		return defence;
	}

	private void setHealth(int health){
		this.health = health;
	}
	private int getHealth(){
		return health;
	}
	private boolean getMelee(){
		return melee;
	}

}
