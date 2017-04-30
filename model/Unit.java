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
	protected static int produceCost = 25;
	protected Cell location;
	protected String type;


	
	public void resetMovement(){
		movementLeft = maxMovement;
		canMove = true;
	}
	//decides where a unit needs to move and checks to see if it possible. 
	//minuses movement and then checks to make sure that no more movements can happen that turn
	public void moveUnit(String direction){
		if(canMove == true){
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
				if(location.getNorthEast().getTerrain() != "mountain"){
					if(location.getNorthEast().getHeldObject() == null){
						location.getNorthEast().setHeldObject(location.getHeldObject());
						location = location.getNorthEast();
						current.setHeldObject(null);
						movementLeft=movementLeft - 1;
					}
				}
			}
			if(direction == "southEast"){
				if(location.getSouthEast().getTerrain() != "mountain"){
					if(location.getSouthEast().getHeldObject() == null){
						location.getSouthEast().setHeldObject(location.getHeldObject());
						location = location.getSouthEast();
						current.setHeldObject(null);
						movementLeft = movementLeft - 1;
					}
				}
			}
			if(direction == "south"){
				if(location.getSouth().getTerrain() != "mountain"){
					if(location.getSouth().getHeldObject() == null){
						location.getSouth().setHeldObject(location.getHeldObject());
						location = location.getSouth();
						current.setHeldObject(null);
						movementLeft = movementLeft - 1;
					}
				}
			}
			if(direction == "southWest"){
				if(location.getSouthWest().getTerrain() != "mountain"){
					if(location.getSouthWest().getHeldObject() == null){
						location.getSouthWest().setHeldObject(location.getHeldObject());
						location = location.getSouthWest();
						current.setHeldObject(null);
						movementLeft = movementLeft - 1;
					}
				}
			}
			if(direction == "northWest"){
				if(location.getNorthWest().getTerrain() != "mountain"){
					if(location.getNorthWest().getHeldObject() == null){
						location.getNorthWest().setHeldObject(location.getHeldObject());
						location = location.getNorthWest();
						current.setHeldObject(null);
						movementLeft = movementLeft - 1;
					}
				}
			}
		}
		else{System.out.println("lol no moves");}
		if(movementLeft == 0){
			canMove = false;
			System.out.println("movement = 0");
		}
	}

	public void attackUnit(Unit attacker, Unit defender){

	}

	public void attackCity(Unit attacker,City defender){

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

	public String getType(){
		return type;
	}
	public Player getPlayer(){
		return player;
	}
	public int getMovementLeft(){
		return movementLeft;
	}
	public static int getProductionCost(){
		return produceCost;
	}

}
