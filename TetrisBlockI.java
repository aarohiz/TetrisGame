import info.gridworld.actor.*; 
import info.gridworld.grid.*; 
import java.util.ArrayList; 
import java.awt.Color;

public class TetrisBlockI extends TetrisBlock
{
    public TetrisBlockI()
    {
        TetrisBug a; 
        a = new TetrisBug(Color.green); 
        a.putSelfInGrid(gr, new Location(2,5)); 
        blocks.add(a);
        
        TetrisBug b; 
        b = new TetrisBug(Color.yellow); 
        b.putSelfInGrid(gr, new Location(3,5)); 
        blocks.add(b);
    }

    public boolean canMoveDown() {
        setDirection(180);
        for (TetrisBug tb: blocks)
            tb.setDirection(180); 
        if (rotationPos == 0) 
            return blocks.get(2).canMove() ; 

        else if (rotationPos == 1) 
            return canMove() && blocks.get(0).canMove() && blocks.get(1).canMove() && blocks.get(2).canMove(); 
        else 
            return true; 
    }

    public void moveRight() {
        setDirection(90);
        for (TetrisBug tb : blocks)
            tb.setDirection(90);
        if (rotationPos == 0) {
            if (canMove() && blocks.get(0).canMove() && blocks.get(1).canMove() && blocks.get(2).canMove()) {
                move();
                for (TetrisBug tb : blocks)
                    tb.move();
                //move();

            }
        } else if (rotationPos == 1) {
            if (blocks.get(2).canMove()) {
            blocks.get(2).move();
            blocks.get(1).move();
            move();
            blocks.get(0).move();
            }
        }

    }

    public void moveLeft() {
        setDirection(270);
        for (TetrisBug tb : blocks)
            tb.setDirection(270);
        if (rotationPos == 0 && blocks.get(0).canMove() && blocks.get(1).canMove() && blocks.get(2).canMove()) {
            move();
            for (TetrisBug tb : blocks)
                tb.move();
        } else if (rotationPos == 1 && blocks.get(0).canMove()) {
            blocks.get(0).move();
            move();
            blocks.get(1).move();
            blocks.get(2).move();

        }
    } 

    public void moveDown() { 
        for (TetrisBug tb: blocks)
            tb.setDirection(180); 
        if (rotationPos == 0 ) { 
            blocks.get(2).move();
            blocks.get(1).move();
            move();
            blocks.get(0).move();
        } else if (rotationPos == 1 && canMove() && canMoveDown()) { 
            for (TetrisBug tb: blocks)
                tb.move();
            move(); 
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
            nextLocb = new Location(blocks.get(2).getLocation().getRow() - 3, 
                blocks.get(1).getLocation().getCol() + 2);
            nextLoca = new Location(blocks.get(1).getLocation().getRow() - 2, 
                blocks.get(2).getLocation().getCol() + 3);
            if (gr.isValid(nextLoc) && gr.get(nextLoc) == null && gr.isValid(nextLoca) && gr.get(nextLoca) == null && gr.isValid(nextLocb) && gr.get(nextLocb) == null) { 
                moveTo(nextLoc); 
                blocks.get(1).moveTo(nextLocb);
                blocks.get(2).moveTo(nextLoca);
                rotationPos = 1;// will be % 4 with 4 blocks 

            } 
        } else if (rotationPos == 1) { 
            nextLoc = new Location(getLocation().getRow() + 1, 
                getLocation().getCol() - 1); 
            nextLoca = new Location(blocks.get(2).getLocation().getRow() + 3, 
                blocks.get(1).getLocation().getCol() - 2);
            nextLocb = new Location(blocks.get(1).getLocation().getRow() + 2, 
                blocks.get(2).getLocation().getCol() - 3);
            if (gr.isValid(nextLoc) && gr.get(nextLoc) == null && gr.isValid(nextLoca) && gr.get(nextLoca) == null && gr.isValid(nextLocb) && gr.get(nextLocb) == null) { 
                moveTo(nextLoc); 
                blocks.get(1).moveTo(nextLocb);
                blocks.get(2).moveTo(nextLoca);
                rotationPos = 0;// will be % 4 with 4 blocks 

            } 
        } 
    }
}