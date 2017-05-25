package Board;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;


/**
 * This cell is one of 81 units on the whole board.  It keeps track of what possible numbers can still go in it, and
 * has the ability to look at groups of other cells to find out what options they might still have.
 * @author dbuis
 *
 */
public class Cell{
	/**
	 * 10 item array of booleans.  [0] denotes if this cell is solved.  [1-9] are initialized as true, as in, this number could go here.
	 * When that number is eliminated, the corresponding boolean is switched to false.
	 */
	private boolean[] solveArray;
	
	//Representation of coordinates of cell
	private Point coords;
	private Rectangle bounds;
	
	//what square group of 9 is this cell in.  
	private int block;
	public int solvedValue = 0;
	
	/**
	 * generic constructor
	 * @param x
	 * @param y
	 */
	public Cell(int x, int y){
		solveArray = new boolean[] {false, true, true, true, true, true, true, true, true, true};
		setCoords(new Point(x,y));
		setBlock(calculateBlock(x,y));
		
	}
	
	


	/**
	 * is this cell solved?
	 * @return
	 */
	public boolean isSolved(){
		return solveArray[0];
	}




	/**
	 * is given value valid for this cell?
	 * @param valueToCheck
	 */
	public boolean checkValue(int valueToCheck){
		return solveArray[valueToCheck];
	}




	/**
	 * Used to set a final solved value for the cell
	 * @param solvedValue
	 */
	public void setSolvedValue(int solvedValue, boolean isTemp){
		if(!isTemp){
			scrubSolveArray(); //prep array for solved status
		}
		solveArray[0]=true; //set cell solved to true
		solveArray[solvedValue]=true; //set final value to true
		this.solvedValue=solvedValue;
	}
	




	/**
	 * Used to eliminate a value from the cell.
	 * @param eliminatedValue
	 */
	public void eliminateValue(int eliminatedValue){
		solveArray[eliminatedValue]=false;
	}




	public void uneliminateValue(int uneliminatedValue){
		solveArray[uneliminatedValue]=true;
	}




	/**
	 * Checks to see if other Cell has the EXACT same candidates remaining as this cell
	 * @param otherCell
	 * @return
	 */
	public boolean sameCandidatesRemain(Cell otherCell){
		
		for (int i=1; i<=9; i++){
			if (!this.checkValue(i)==otherCell.checkValue(i)){
				return false;
			}
		}
		
		return true;
		
	}




	/**
	 * A simple method to see how many candidates remain in the cell
	 * @return
	 */
	
	public int howManyCandidatesRemain(){
		//initialize count at 0
		int returnVal=0;
		
		//iterate 1-9
		for (int i=1; i<=9; i++){
			//if candidate is still an option, increment
			if(this.checkValue(i)){
				returnVal++;
			}
		}
		
		return returnVal;
	}




	public void resetCell(){
		solveArray = new boolean[] {false, true, true, true, true, true, true, true, true, true};
	}




	/**
	 * scrubs solve array ONLY upon solve of cell.  Sets all values to false.  that's probably a repeat for most of them,
	 * but in cases such as a hidden single there will be multiple true values that need to be eliminated.
	 */
		private void scrubSolveArray() {
			for (int i=0;i<10;i++){
				solveArray[i]=false;
			}
			
		}




	/**
	 * Use coordinates to determine what portion of the board this cell resides in.
	 * 
	 * Upper left is 0, then proceeding as reading a book, lower right is 8
	 * @param x
	 * @param y
	 * @return
	 */
	private int calculateBlock(int x, int y) {
		
		//1-3 should give value of 1, etc.
		int horizontalVal = (int) Math.ceil((x+1)/3.0);
		
		
		//1-3 should give value of 0, 4-6 -> 3, 7-9 ->6
		int verticalVal = 3*(int)Math.floor(y/3.0);

		
		return horizontalVal+verticalVal-1;
	}


	public String toString(){
		return "Cell in Block "+this.block+" ("+this.coords.x+","+this.coords.y+").  Solved? "+this.isSolved()+" "+this.solvedValue;
	}
	
	public void extendedToString(){
		System.out.println(this.toString());
		
		for (int i=1;i<=9;i++){
			System.out.println(i+"? : "+ this.checkValue(i));
		}
	}
	
	public boolean equals(Cell otherCell){
		if(this.coords.x==otherCell.coords.x && this.coords.y==otherCell.coords.y){
			return true;
		}
		
		return false;
		
	}
	
	public int getBlock() {
		return block;
	}




	private void setBlock(int block) {
		this.block = block;
	}




	public Point getCoords() {
		return coords;
	}




	private void setCoords(Point coords) {
		this.coords = coords;
	}




	public void draw(Graphics g, int x, int y, int width) {
		
		//draw background coler
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, width);
        
        //draw border
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, width);
        
        //draw bold borders
        drawBorder(g, x, y, width);
        
        //define default font
        Font font = g.getFont();
        
        //if solved, use a larger font
        if (this.isSolved()){
        	Font solvedFont = font.deriveFont((float)(60));
        	g.setFont(solvedFont);
        	
        	AffineTransform affinetransform = new AffineTransform();     
        	FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
        	String solvedString = ""+solvedValue;
        	int textwidth = (int)(font.getStringBounds(solvedString, frc).getWidth());
        	int textheight = (int)(font.getStringBounds(solvedString, frc).getHeight());
        	
        	g.drawString(solvedString, (int)(x+width*0.4-textwidth/2.0), (int)(y+width*0.7+textheight/2.0));
        	g.setFont(font);
        	
        }else{
        	int increment1 = (int) (width*0.3)+5;
        	int increment2 = (int) (width*0.5)+5;
        	int increment3 = (int) (width*0.7)+5;
        	g.setFont(font);
        	
        	//if 1 is still valid, draw a small 1 up and left of center
        	if(checkValue(1)){
        		g.drawString("1", x+increment1-7, y+increment1);	
        	}
        	if(checkValue(2)){
        		g.drawString("2", x+increment2-7, y+increment1);  		
        	}
        	if(checkValue(3)){
        		g.drawString("3", x+increment3-7, y+increment1);
        	}
        	if(checkValue(4)){
        		g.drawString("4", x+increment1-7, y+increment2); 		
        	}
        	if(checkValue(5)){
        		g.drawString("5", x+increment2-7, y+increment2);	
        	}
        	if(checkValue(6)){
        		g.drawString("6", x+increment3-7, y+increment2);
        	}
        	if(checkValue(7)){
        		g.drawString("7", x+increment1-7, y+increment3);	
        	}
        	if(checkValue(8)){
        		g.drawString("8", x+increment2-7, y+increment3);      		
        	}
        	if(checkValue(9)){
        		g.drawString("9", x+increment3-7, y+increment3);
        	}
        		
        	
        }

 
        }
 
    private void drawBorder(Graphics g, int x, int y, int width) {
       if(coords.x==0 || coords.x==3){
    	   drawLeftBorder(g,x,y,width);
       } else if (coords.x==8 || coords.x==5){
    	   drawRightBorder(g,x,y,width);
       }
       
       if(coords.y==0 || coords.y==3){
    	   drawTopBorder(g,x,y,width);
       } else if (coords.y==8 || coords.y==5){
    	   drawBottomBorder(g,x,y,width);
       }
       
        
    }
 
    private void drawTopBorder(Graphics g, int x, int y, int width) {
        g.drawLine(x, y + 1, x + width, y + 1);
        g.drawLine(x, y + 2, x + width, y + 2);
    }
 
    private void drawRightBorder(Graphics g, int x, int y, int width) {
        g.drawLine(x + width - 1, y, x + width - 1, y + width);
        g.drawLine(x + width - 2, y, x + width - 2, y + width);
    }
 
    private void drawBottomBorder(Graphics g, int x, int y, int width) {
        g.drawLine(x, y + width - 1, x + width, y + width - 1);
        g.drawLine(x, y + width - 2, x + width, y + width - 2);
    }
 
    private void drawLeftBorder(Graphics g, int x, int y, int width) {
        g.drawLine(x + 1, y, x + 1, y + width);
        g.drawLine(x + 2, y, x + 2, y + width);
    }


	public boolean contains(Point point) {
		return bounds.contains(point);
	}
	
	public void setBounds(Rectangle bounds){
		this.bounds=bounds;
	}
 
  
   
	

}
