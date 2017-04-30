package model;

import java.util.ArrayList;

public class Player {
	
	public int playerNumber;
	public boolean turnEnded;
	public ArrayList<Unit> playerUnits;
	public ArrayList<City> cityList;
	public String econmicType;
	public int peaceCounter = 0;
	
	
	public Player(int number){
		playerNumber = number;
		turnEnded = false;
		playerUnits = new ArrayList<Unit>();
		cityList = new ArrayList<City>();
	}
	
	public void setEndTurn(Boolean variable){
		turnEnded = variable;
	}
	
	public void addUnits(Unit type){
		playerUnits.add(type);
	}
	
	public void removeUnit(Object o){
		playerUnits.remove(o);
	}
	public void addCity(City c){
		cityList.add(c);
	}
	// set the economy type that the player chooses
	public void setEco(String ecoType){
		econmicType = ecoType;
	}
	//gets the economy type
	public String getEco(){
		return econmicType;
	}
	//increases counter for peace
	public void addToPeaceCounter(){
		peaceCounter++;
	}
	//checks the win condition
	public String checkWin(){
		if (econmicType == "neo-liberal"){
			if(cityList.size()>10){
				return "winner";
			}
		}else if (econmicType == "peace-builing"){
			if(peaceCounter == 75){
				return "winner";
			}
		}
		return null;
	}
	

}
