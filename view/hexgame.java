package view;
import java.awt.*;

import javax.swing.*;

import sun.awt.RepaintArea;
import model.*;

import java.awt.event.*;
import java.io.IOException;

/**********************************
  This is the main class of a Java program to play a game based on hexagonal tiles.
  The mechanism of handling hexes is in the file hexmech.java.
  Written by: M.H.
  Date: December 2012
 ***********************************/

public class hexgame
{
	public hexgame(String ecoType) {
		initGame(ecoType);
		createAndShowGUI();
	}

	//constants and global variables
	final static Color COLOURBACK =  Color.WHITE;
	final static Color COLOURCELL =  Color.ORANGE;	 
	final static Color COLOURGRID =  Color.BLACK;	 
	final static Color COLOURONE = new Color(255,255,255,200);
	final static Color COLOURONETXT = Color.BLUE;
	final static Color COLOURTWO = new Color(0,0,0,200);
	final static Color COLOURTWOTXT = new Color(255,100,255);
	final static int EMPTY = 0;
	final static int BSIZE = 25; //board size.
	final static int HEXSIZE = 50;	//hex size in pixels//need to add in a scroller.70
	final static int BORDERS = 15;  
	final static int SCRSIZE = 0; //screen size (vertical dimension).
	public model.Map game = new Map();
	public JPanel content = new JPanel();
	public JPanel actionBar = new JPanel();
	Player dan = new Player(0);
	public Player currentPlayer;

	

	public void initGame(String ecoType){

		hexmech.setXYasVertex(false); //RECOMMENDED: leave this as FALSE.

		hexmech.setHeight(HEXSIZE); //Either setHeight or setSize must be run to initialize the hex
		hexmech.setBorders(BORDERS);
		//model.Map game = new Map();

		game.fillMap();


		currentPlayer = dan;
		currentPlayer.setEco(ecoType);
		Settler dan1 = new Settler(currentPlayer,game.mapBoard[5][5]);
		currentPlayer.addUnits(dan1);
		

	}

	//creates the gui for the frame of the game


	private void createAndShowGUI()
	{
		DrawingPanel panel = new DrawingPanel();

		JFrame frame = new JFrame("Economic Game");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );


		actionBar.setSize(200, 1040);
		actionBar.setLocation(1400, 0);
		actionBar.setBackground(Color.GRAY);

		content.setLayout(new BorderLayout());
		content.setAutoscrolls(true);
		content.add(actionBar,BorderLayout.CENTER);
		content.add(panel, BorderLayout.CENTER);


		JScrollPane scroller = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroller.add(content);
		scroller.setViewportView(content);
		scroller.setAutoscrolls(true);

		frame.add(scroller);
		frame.setSize(1620,1040);
		frame.setResizable(true);
		frame.setLocationRelativeTo( null );
		frame.setVisible(true);
	}


	class DrawingPanel extends JPanel
	{		
		

		public DrawingPanel()
		{	
			setBackground(COLOURBACK);
			MyMouseListener ml = new MyMouseListener(); 
			addMouseListener(ml);
		
		}
		// draws the map and everyting that is on it like terrain and unts
		@Override
		public void paintComponent(Graphics g)
		{
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			g2.setClip(0, 0, 1400, 1040);
			super.paintComponent(g2);
			//draw grid
			for (int i=0;i<32;i++) {
				for (int j=0;j<19;j++) {
					String tempTerrain = game.mapBoard[i][j].getTerrain();
					String tempResource = "";
					String tempUnit = "";

					if (game.mapBoard[i][j].getResource() != null){
						tempResource = game.mapBoard[i][j].getResource();
					}
					if(game.mapBoard[i][j].getHeldObject() != null){
						//tempUnit = (String) game.mapBoard[i][j].getHeldObject();
						//int startIndex = tempUnit.indexOf("@");
						String cityType = game.mapBoard[i][j].getHeldObject().toString();
						String[] cityName = cityType.split("@");
						if(cityName[0].equals("model.City")){
							tempUnit = ((City) game.mapBoard[i][j].getHeldObject()).getType();	
						}else{
							if(((Unit) game.mapBoard[i][j].getHeldObject()).getType() != null){

								tempUnit = ((Unit) game.mapBoard[i][j].getHeldObject()).getType();
							}
						}
					}

					hexmech.drawHex(i,j,g2,tempTerrain,tempResource,tempUnit);
				}
			}
		
		}

		class MyMouseListener extends MouseAdapter	{
			//inner class inside DrawingPanel 
			@Override
			public void mouseClicked(MouseEvent e) { 
				final int x = e.getX(); 
				final int y = e.getY(); 
				
				Point p = new Point( hexmech.pxtoHex(e.getX(),e.getY()) );
				if (p.x < 0 || p.y < 0 || p.x >= 32 || p.y >= 25) return;

				
				final int X = p.x;
				final int Y = p.y;
				//What do you want to do when a hexagon is clicked?
				if(game.mapBoard[p.x][p.y].getHeldObject() != null){
					String cityType = game.mapBoard[p.x][p.y].getHeldObject().toString();
					String[] cityName = cityType.split("@");

					if(cityName[0].equals("model.City")){
						if(((City) game.mapBoard[p.x][p.y].getHeldObject()).getPlayer() == currentPlayer){
							System.out.println("city options");
							//create building - > choose which building
							//create unit - > choose unit
							actionBar.removeAll();
							actionBar.setBackground(Color.BLUE);
							final City currentCity = (City) game.mapBoard[p.x][p.y].getHeldObject();

							final JTextArea popDetails = new JTextArea();
							final int pop = ((City) game.mapBoard[X][Y].getHeldObject()).getPopulation();
							String population = "Population:" + String.valueOf(pop);
							popDetails.insert(population, 0);

							JButton endTurn = new JButton("End turn");
							endTurn.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									currentPlayer.setEndTurn(true);
									if(currentPlayer.turnEnded == true){
										currentPlayer.setEndTurn(false);

										if(currentPlayer.checkWin() == "winner"){
											removeAll();//add in winner
											setVisible(false);// make new winner frame

										}else{
											for(int i=0;i<currentPlayer.playerUnits.size();i++){
												currentPlayer.playerUnits.get(i).resetMovement();
											}
											for(int i=0;i<currentPlayer.cityList.size();i++){
												currentPlayer.cityList.get(i).updatePopulation();
												currentPlayer.cityList.get(i).addTofoodBank();
												currentPlayer.cityList.get(i).addToProdBank();
												
												
												System.out.println("turn 2lol");
												repaint();
												actionBar.removeAll();
												actionBar.setBackground(Color.GRAY);

												setVisible(false);
												setVisible(true);
											}
										}

										
									}

								}
							});

							JButton buildUnit = new JButton("build unit");
							final JTextArea pDetails = new JTextArea();
							final int productionBank = currentCity.getProductionBank();
							String production = "Amount of production left:" + String.valueOf(productionBank);
							pDetails.insert(production, 0);

							buildUnit.addActionListener(new ActionListener() { 
								//buttons for the different units
								@Override
								public void actionPerformed(ActionEvent e) {
									actionBar.removeAll();
									JButton archer = new JButton("Archer");
									archer.addActionListener(new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent e) {

											if(productionBank >= Unit.getProductionCost()){
												currentCity.subtractProdBank(Unit.getProductionCost());
												Archer a3 = new Archer(currentPlayer, game.mapBoard[X][Y].getNorth());
												currentPlayer.addUnits(a3);
												game.mapBoard[X][Y].getNorth().setHeldObject(a3);

												setVisible(false);
												setVisible(true);
											}
										}
									});

									JButton Catapult = new JButton("Catapult");
									Catapult.addActionListener(new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent e) {
											if(productionBank >= Unit.getProductionCost()){
												currentCity.subtractProdBank(Unit.getProductionCost());
												Catapult a3 = new Catapult(currentPlayer, game.mapBoard[X][Y].getNorth());
												currentPlayer.addUnits(a3);
												game.mapBoard[X][Y].getNorth().setHeldObject(a3);

												setVisible(false);
												setVisible(true);
											}

										}
									});

									JButton ChariotArcher = new JButton("Chariot Archer");
									ChariotArcher.addActionListener(new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent e) {
											if(productionBank >= Unit.getProductionCost()){
												currentCity.subtractProdBank(Unit.getProductionCost());
												ChariotArcher a3 = new ChariotArcher(currentPlayer, game.mapBoard[X][Y].getNorth());
												currentPlayer.addUnits(a3);
												game.mapBoard[X][Y].getNorth().setHeldObject(a3);

												setVisible(false);
												setVisible(true);
											}

										}
									});

									JButton HunterGather = new JButton("Hunter Gather");
									HunterGather.addActionListener(new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent e) {
											if(productionBank >= Unit.getProductionCost()){
												currentCity.subtractProdBank(Unit.getProductionCost());
												HunterGather a3 = new HunterGather(currentPlayer, game.mapBoard[X][Y].getNorth());
												currentPlayer.addUnits(a3);
												game.mapBoard[X][Y].getNorth().setHeldObject(a3);

												setVisible(false);
												setVisible(true);
											}

										}
									});

									JButton knight = new JButton("Knight");
									knight.addActionListener(new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent e) {
											if(productionBank >= Unit.getProductionCost()){
												currentCity.subtractProdBank(Unit.getProductionCost());
												Knight a3 = new Knight(currentPlayer, game.mapBoard[X][Y].getNorth());
												currentPlayer.addUnits(a3);
												game.mapBoard[X][Y].getNorth().setHeldObject(a3);

												setVisible(false);
												setVisible(true);
											}

										}
									});

									JButton settler = new JButton("Settler");
									settler.addActionListener(new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent e) {
											if(productionBank >= Unit.getProductionCost()){//here
												currentCity.subtractProdBank(Unit.getProductionCost());
												Settler a3 = new Settler(currentPlayer, game.mapBoard[X][Y].getNorth());
												currentPlayer.addUnits(a3);
												game.mapBoard[X][Y].getNorth().setHeldObject(a3);

												setVisible(false);
												setVisible(true);
											}

										}
									});

									JButton Spearman = new JButton("Spearman");
									Spearman.addActionListener(new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent e) {
											if(productionBank >= Unit.getProductionCost()){
												currentCity.subtractProdBank(Unit.getProductionCost());
												Spearman a3 = new Spearman(currentPlayer, game.mapBoard[X][Y].getNorth());
												currentPlayer.addUnits(a3);
												game.mapBoard[X][Y].getNorth().setHeldObject(a3);

												setVisible(false);
												setVisible(true);
											}

										}
									});


									JButton warrior = new JButton("Warrior");
									warrior.addActionListener(new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent e) {
											if(productionBank >= Unit.getProductionCost()){
												currentCity.subtractProdBank(Unit.getProductionCost());
												Warrior a3 = new Warrior(currentPlayer, game.mapBoard[X][Y].getNorth());
												currentPlayer.addUnits(a3);
												game.mapBoard[X][Y].getNorth().setHeldObject(a3);

												setVisible(false);
												setVisible(true);
											}

										}
									});

									actionBar.add(archer);
									actionBar.add(Catapult);
									actionBar.add(ChariotArcher);
									actionBar.add(HunterGather);
									actionBar.add(knight);
									actionBar.add(settler);
									actionBar.add(Spearman);
									actionBar.add(warrior);
									actionBar.add(pDetails);

									setVisible(false);
									setVisible(true);

								}
							});
							JButton buildIng = new JButton("build building");
							buildIng.addActionListener(new ActionListener() {
								//imporves the production and food. prison maybe?
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub

								}
							});
							actionBar.add(buildIng);
							actionBar.add(buildUnit);
							actionBar.add(endTurn);
							actionBar.add(popDetails);

							setVisible(false);
							setVisible(true);
						}
					}else if(((Unit) game.mapBoard[p.x][p.y].getHeldObject()).getType() != null){
						if(((Unit) game.mapBoard[p.x][p.y].getHeldObject()).getPlayer() == currentPlayer){
							System.out.println("unit options");
							actionBar.setBackground(Color.GREEN);
							actionBar.removeAll();

							final JTextArea unitDetails = new JTextArea();
							int movementLeft = ((Unit) game.mapBoard[p.x][p.y].getHeldObject()).getMovementLeft();
							String movements = "Amount of movement left:" + String.valueOf(movementLeft);
							unitDetails.insert(movements, 0);

							JButton moveUnit = new JButton("move unit");
							moveUnit.addActionListener(new ActionListener() {
								//buttons for moving the units
								@Override
								public void actionPerformed(ActionEvent e) {

									actionBar.removeAll();
									JButton moveNorth = new JButton("move north");
									moveNorth.addActionListener(new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent e) {
											Point beta = new Point(hexmech.pxtoHex(x, y));
											Unit toMove = (Unit) game.mapBoard[beta.x][beta.y].getHeldObject();
											System.out.println("moveNorth");
											toMove.moveUnit("north");
											repaint();
											actionBar.removeAll();
											actionBar.setBackground(Color.GRAY);
											setVisible(false);
											setVisible(true);

										}
									});

									JButton moveNorthEast = new JButton("move north East");
									moveNorthEast.addActionListener(new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent e) {
											Point beta = new Point(hexmech.pxtoHex(x, y));
											Unit toMove = (Unit) game.mapBoard[beta.x][beta.y].getHeldObject();
											System.out.println("moveNorthEast");
											toMove.moveUnit("northEast");
											repaint();
											actionBar.removeAll();
											actionBar.setBackground(Color.GRAY);
											setVisible(false);
											setVisible(true);

										}
									});

									JButton moveNorthWest = new JButton("move north West");
									moveNorthWest.addActionListener(new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent e) {
											Point beta = new Point(hexmech.pxtoHex(x, y));
											Unit toMove = (Unit) game.mapBoard[beta.x][beta.y].getHeldObject();
											System.out.println("moveNorthWest");
											toMove.moveUnit("northWest");
											repaint();
											actionBar.removeAll();
											actionBar.setBackground(Color.GRAY);
											setVisible(false);
											setVisible(true);

										}
									});

									JButton moveSouth = new JButton("move south");
									moveSouth.addActionListener(new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent e) {
											Point beta = new Point(hexmech.pxtoHex(x, y));
											Unit toMove = (Unit) game.mapBoard[beta.x][beta.y].getHeldObject();
											System.out.println("moveSouth");
											toMove.moveUnit("south");
											repaint();
											actionBar.removeAll();
											actionBar.setBackground(Color.GRAY);
											setVisible(false);
											setVisible(true);

										}
									});

									JButton moveSouthEast = new JButton("move south east");
									moveSouthEast.addActionListener(new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent e) {
											Point beta = new Point(hexmech.pxtoHex(x, y));
											Unit toMove = (Unit) game.mapBoard[beta.x][beta.y].getHeldObject();
											System.out.println("moveSE");
											toMove.moveUnit("southEast");
											repaint();
											actionBar.removeAll();
											actionBar.setBackground(Color.GRAY);
											setVisible(false);
											setVisible(true);

										}
									});

									JButton moveSouthWest = new JButton("move south West");
									moveSouthWest.addActionListener(new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent e) {
											Point beta = new Point(hexmech.pxtoHex(x, y));
											Unit toMove = (Unit) game.mapBoard[beta.x][beta.y].getHeldObject();
											System.out.println("moveSW");
											toMove.moveUnit("southWest");
											repaint();
											actionBar.removeAll();
											actionBar.setBackground(Color.GRAY);
											setVisible(false);
											setVisible(true);

										}
									});




									actionBar.add(moveNorth);
									actionBar.add(moveNorthEast);
									actionBar.add(moveNorthWest);
									actionBar.add(moveSouth);
									actionBar.add(moveSouthEast);
									actionBar.add(moveSouthWest);
									actionBar.add(unitDetails);

									setVisible(false);
									setVisible(true);

								}
							});

							JButton attackUnit = new JButton("attack unit");
							attackUnit.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									//TODO

								}
							});
							//special abilities
							if(((Unit)game.mapBoard[p.x][p.y].getHeldObject()).getType() == "settler"){
								JButton settleCity = new JButton("settle City");
								settleCity.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {
										((Settler) game.mapBoard[X][Y].getHeldObject()).foundCity("q");

										actionBar.removeAll();
										setVisible(false);
										setVisible(true);
									}
								});
								actionBar.add(settleCity);
								setVisible(false);
								setVisible(true);
							}



							actionBar.add(moveUnit);
							actionBar.add(attackUnit);
							setVisible(false);
							setVisible(true);

						}else{
							System.out.println("not your unit");
							actionBar.setBackground(Color.BLACK);
							actionBar.removeAll();
							setVisible(false);
							setVisible(true);
						}
					}
				}
				System.out.println("pressed tile:" + game.mapBoard[p.x][p.y].getTerrain());

			}	

		} //end of MyMouseListener class 
		

	} // end of DrawingPanel class




}
