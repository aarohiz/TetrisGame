import info.gridworld.actor.*; 
import info.gridworld.grid.*; 
import java.util.ArrayList; 
import java.awt.Color; 

public class TetrisBlockL extends TetrisBlock
{
    public TetrisBlockL() { 
        rotationPos = 0;
        TetrisBug a; 
        a = new TetrisBug(Color.yellow); 
        a.putSelfInGrid(gr, new Location(0, 7)); 
        blocks.add(a);

        TetrisBug b; 
        b = new TetrisBug(Color.green); 
        b.putSelfInGrid(gr, new Location(0, 6)); 
        blocks.add(b);

    } 

    public void moveRight() {
        setDirection(90);
        for (TetrisBug tb : blocks)
            tb.setDirection(90);
        if (rotationPos == 0 && blocks.get(1).canMove()) {

            for(int x = 1; x < blocks.size(); x++)
                blocks.get(x).move();
            blocks.get(0).move();
            move();

        } else if (rotationPos == 1) {
            if (canMove() && blocks.get(1).canMove() && blocks.get(1).canMove()) {
                move();

                for (TetrisBug tb : blocks)
                    tb.move();
            }
        }
    } 

    /** 
     * Sets the direction of the TetrisBlock and its TetrisBugs to 270 (left) If 
     * they can move, they will move one cell (to the left) 
     */ 
    public void moveLeft() {
        setDirection(270);
        for (TetrisBug tb : blocks)
            tb.setDirection(270);
        if (rotationPos == 0 && blocks.get(0).canMove()) {

            blocks.get(0).move();
            move();
            blocks.get(2).move();
            blocks.get(1).move();

        } else if (rotationPos == 1 && blocks.get(0).canMove() && blocks.get(1).canMove() && blocks.get(2).canMove()) {
            
                

                for (TetrisBug tb : blocks)
                    tb.move();
                move();
        }
    } 

    public boolean canMoveDown(){ 
        setDirection(180);
        for (TetrisBug tb : blocks)
            tb.setDirection(180);
        if (rotationPos == 0)
        {
            if (canMove() && blocks. get(1). canMove() && blocks. get(2). canMove()) 
                return true; 
            else 
                return false;
        }

        else if (rotationPos == 1) 
            return canMove() && blocks.get(0).canMove(); 
        else 
            return true; 
    } 
    public void moveDown()
    {
        setDirection(180);
        for (TetrisBug tb: blocks)
            tb.setDirection(180);
        
        if (rotationPos == 0 ) { 
            move(); 
            for (TetrisBug tb: blocks)
                tb.move(); 
        } else if (rotationPos == 1 && canMove() && canMoveDown()) { 
            
            move(); 
            blocks.get(0).move();
            blocks.get(2).move();
            blocks.get(1).move();
        } 
    }
   

    public void rotate()
    {
        Location nextLoc; 
        Location nextLoca;
        Location nextLocb;
 
        if (rotationPos == 0) { 
            
            nextLoc = new Location(getLocation().getRow() - 1, 
                getLocation().getCol() + 1); 
                nextLoca = new Location(blocks.get(2).getLocation().getRow() - 1, 
                blocks.get(2).getLocation().getCol() - 1);
                nextLocb = new Location(blocks.get(1).getLocation().getRow() - 2, 
                blocks.get(1).getLocation().getCol() - 2);
           if (gr.isValid(nextLoc) && gr.isValid(nextLoca) && gr.get(nextLoca) == null && gr.isValid(nextLocb) && gr.get(nextLocb) == null) { 
                blocks.get(2).moveTo(nextLoca);
                blocks.get(1).moveTo(nextLocb);
                
                moveTo(nextLoc); 
                rotationPos = 1;// will be % 4 with 4 blocks 
            } 
        } else if (rotationPos == 1) { 
            nextLoc = new Location(getLocation().getRow() + 1, 
                getLocation().getCol() - 1); 
                nextLoca = new Location(blocks.get(2).getLocation().getRow() + 1, 
                blocks.get(2).getLocation().getCol() + 1);
                nextLocb = new Location(blocks.get(1).getLocation().getRow() + 2, 
                blocks.get(1).getLocation().getCol() + 2);
            if (gr.isValid(nextLoc) && gr.get(nextLoc) == null && gr.isValid(nextLoca) && gr.isValid(nextLocb) && gr.get(nextLocb) == null) { 
                moveTo(nextLoc); 
                blocks.get(1).moveTo(nextLocb);
                blocks.get(2).moveTo(nextLoca);
                rotationPos = 0;// will be % 4 with 4 blocks 
        } 
    } 
}
}