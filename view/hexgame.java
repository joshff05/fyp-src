package view;
import java.awt.*;

import javax.swing.*;

import sun.awt.RepaintArea;
import model.City;
import model.Map;
import model.Player;
import model.Settler;

import java.awt.event.*;

/**********************************
  This is the main class of a Java program to play a game based on hexagonal tiles.
  The mechanism of handling hexes is in the file hexmech.java.
  Written by: M.H.
  Date: December 2012
 ***********************************/

public class hexgame
{
  private hexgame() {
		initGame();
		createAndShowGUI();
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
				new hexgame();
				}
				});
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
	final static int HEXSIZE = 80;	//hex size in pixels//need to add in a scroller.70
	final static int BORDERS = 15;  
	final static int SCRSIZE = HEXSIZE * (32 + 1) + BORDERS*3; //screen size (vertical dimension).
	public model.Map game = new Map();
	public JPanel content = new JPanel();

//	int[][] board = new int[32][25];

	public void initGame(){
		
		hexmech.setXYasVertex(false); //RECOMMENDED: leave this as FALSE.

		hexmech.setHeight(HEXSIZE); //Either setHeight or setSize must be run to initialize the hex
		hexmech.setBorders(BORDERS);
		//model.Map game = new Map();
		
		game.fillMap();
		
		Player dan = new Player();
		Settler dan1 = new Settler(dan,game.mapBoard[5][5]);//need a rule to put settler at different part if blocked by a mountain controller
		dan1.moveUnit("north");//controller to check direction in which the player requires to move unit, then execute move command.
		dan1.foundCity("atlantis");
		
		System.out.println(game.toStringMap());
		System.out.println(game.mapBoard[4][5].getTerrain() + "," + game.mapBoard[4][5].getResource());
		System.out.println("food:" + game.mapBoard[5][5].getYieldFood());
		System.out.println("production:" + game.mapBoard[5][5].getYieldProduction());
		System.out.println("north: " + game.mapBoard[5][5].getNorth().getRow()+ "," + game.mapBoard[5][5].getNorth().getColumn());
		System.out.println("south East: " + game.mapBoard[5][5].getSouthEast().getRow()+ "," + game.mapBoard[5][5].getSouthEast().getColumn());
		System.out.println("held object: " + ((City) game.mapBoard[4][5].getHeldObject()).getName());
		System.out.println("production: " +  ((City) game.mapBoard[4][5].getHeldObject()).getProduction());
		System.out.println("food: " + ((City) game.mapBoard[4][5].getHeldObject()).getFood());
		
//		for (int i=0;i<32;i++) {
//			for (int j=0;j<25;j++) {
//				board[i][j]=EMPTY;
//			}
//		}
//
//		//set up board here
//		board[3][3] = (int)'A';
//		board[4][3] = (int)'Q';
//		board[4][4] = -(int)'B';
	}
	
	//add in a jcomponent taht has the ability to be dragged around the screen.

	private void createAndShowGUI()
	{
		DrawingPanel panel = new DrawingPanel();
		
		
		JFrame frame = new JFrame("Hex Testing 4");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	
		
		content.setLayout(new BorderLayout());
		content.setAutoscrolls(true);
		
		content.add(panel, BorderLayout.CENTER);
		JScrollPane scroller = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroller.add(content);
		scroller.setViewportView(content);
		scroller.setAutoscrolls(true);
		//content.add(scroll, BorderLayout.EAST);//add slider
		//this.add(panel);  -- cannot be done in a static context
		//for hexes in the FLAT orientation, the height of a 10x10 grid is 1.1764 * the width. (from h / (s+t))
		frame.add(scroller);
		frame.setSize( (int)(SCRSIZE/1.2), (int) (SCRSIZE/2.5));
		frame.setResizable(true);
		frame.setLocationRelativeTo( null );
		frame.setVisible(true);
	}


	class DrawingPanel extends JPanel
	{		
		//mouse variables here
		//Point mPt = new Point(0,0);
		private volatile int screenX = 0;
		private volatile int screenY = 0;
		private volatile int myX = 0;
		private volatile int myY = 0;
		
		//add into scroll panel
		//remove scrollbar and add it to a on click listener. 

		public DrawingPanel()
		{	
			setBackground(COLOURBACK);

			MyMouseListener ml = new MyMouseListener(); 
			MouseListener m2= new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					screenX = e.getXOnScreen();
					screenY = e.getYOnScreen();

					myX = getX();
					myY = getY();
					RepaintManager.currentManager(content);
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			};
			MouseMotionAdapter m3 = new MouseMotionAdapter() {
				
				public void mouseDragged(MouseEvent e) {
					int deltaX = e.getXOnScreen() - screenX;
					int deltaY = e.getYOnScreen() - screenY;

					setLocation(myX + deltaX, myY + deltaY);
					
					RepaintManager.currentManager(content);
					repaint();
				}
			};
			addMouseListener(ml);
			addMouseListener(m2);
			addMouseMotionListener(m3);
		}

		@Override
		public void paintComponent(Graphics g)
		{
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			super.paintComponent(g2);
			//draw grid
			for (int i=0;i<32;i++) {
				for (int j=0;j<25;j++) {
					String tempTerrain = game.mapBoard[i][j].getTerrain();
					String tempResource = "";
					if (game.mapBoard[i][j] != null){
						tempResource = game.mapBoard[i][j].getResource();
					}else{
						
					}
					
					hexmech.drawHex(i,j,g2,tempTerrain,tempResource);
				}
			}
			//fill in hexes
//			for (int i=0;i<32;i++) {
//				for (int j=0;j<25;j++) {					
//					//if (board[i][j] < 0) hexmech.fillHex(i,j,COLOURONE,-board[i][j],g2);
//					//if (board[i][j] > 0) hexmech.fillHex(i,j,COLOURTWO, board[i][j],g2);
//					hexmech.fillHex(i,j,board[i][j],g2);
//				}
//			}

			//g.setColor(Color.RED);
			//g.drawLine(mPt.x,mPt.y, mPt.x,mPt.y);
		}

		class MyMouseListener extends MouseAdapter	{
			  //inner class inside DrawingPanel 
			@Override
			public void mouseClicked(MouseEvent e) { 
				int x = e.getX(); 
				int y = e.getY(); 
				//mPt.x = x;
				//mPt.y = y;
				Point p = new Point( hexmech.pxtoHex(e.getX(),e.getY()) );
				if (p.x < 0 || p.y < 0 || p.x >= 32 || p.y >= 25) return;

				//DEBUG: colour in the hex which is supposedly the one clicked on
				//clear the whole screen first.
				/* for (int i=0;i<BSIZE;i++) {
					for (int j=0;j<BSIZE;j++) {
						board[i][j]=EMPTY;
					}
				} */

				//What do you want to do when a hexagon is clicked?
				System.out.println("pressed tile:" + game.mapBoard[p.x][p.y].getTerrain());
				//board[p.x][p.y] = (int)'X';
				repaint();
			}	
			
		} //end of MyMouseListener class 
		
		
	} // end of DrawingPanel class
	
	
	
	
}
