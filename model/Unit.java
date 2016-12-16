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
	protected int cellX;
	protected int cellY;
	//may need to change costs and stats for balance
	//could a city be classed as a stationary unit.
	//need to get cells instead of x and y values
	protected void moveUnit(){

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
