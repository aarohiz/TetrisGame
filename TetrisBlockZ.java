import info.gridworld.actor.*; 
import info.gridworld.grid.*; 
import java.util.ArrayList; 
import java.awt.Color;

public class TetrisBlockZ extends TetrisBlock
{
    public TetrisBlockZ()
    {

        TetrisBug a; 
        a = new TetrisBug(Color.yellow); 
        a.putSelfInGrid(gr, new Location(0, 4)); 
        blocks.add(a);

        TetrisBug b; 
        b = new TetrisBug(Color.green); 
        b.putSelfInGrid(gr, new Location(1, 6)); 
        blocks.add(b);

    }

    public boolean canMoveDown() {
        setDirection(180);
        for (TetrisBug tb: blocks)
            tb.setDirection(180); 
        if (rotationPos == 0) 
            return blocks.get(2).canMove() && blocks.get(1).canMove() && canMove(); 

        else if (rotationPos == 1) 
            return blocks.get(1).canMove() &&  blocks.get(2).canMove(); 
        else 
            return true; 
    }

    public void moveRight() {
        setDirection(90);
        for (TetrisBug tb : blocks)
            tb.setDirection(90);
        if (rotationPos == 0) {
            if (blocks.get(0).canMove() && blocks.get(2).canMove()) {

                blocks.get(2).move();
                move();
                blocks.get(0).move();
                blocks.get(1).move();
            }
        } else if (rotationPos == 1) {
            if (blocks.get(1).canMove() && blocks.get(0).canMove() && blocks.get(2).canMove()) {
                blocks.get(1).move();
                blocks.get(2).move();
                move();
                blocks.get(0).move();
            }
        }

    }

    public void moveDown()
    {
        for (TetrisBug tb: blocks)
            tb.setDirection(180); 
        if (rotationPos == 0 ) { 
            move(); 
            for (TetrisBug tb: blocks)
                tb.move(); 
        } else
        {
            blocks.get(1).move();

            blocks.get(2).move();
            move();
            blocks.get(0).move(); 
        }
    } 

    public void moveLeft() {
        setDirection(270);
        for (TetrisBug tb : blocks)
            tb.setDirection(270);
        if (rotationPos == 0 && canMove() && blocks.get(1).canMove() ) {
            blocks.get(1).move();

            blocks.get(0).move();
            move();
            blocks.get(2).move();
        } else if ( blocks.get(0).canMove() && canMove()) {

            blocks.get(0).move();
            move();
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
            
            nextLoc =  new Location(blocks.get(0).getLocation().getRow(), 
                blocks.get(0).getLocation().getCol() + 1);
            nextLoca = new Location(blocks.get(1).getLocation().getRow() + 2, 
                blocks.get(1).getLocation().getCol() + 1);
            if (gr.isValid(nextLoc) && gr.get(nextLoc) == null && gr.isValid(nextLoca) && gr.get(nextLoca) == null ){

                blocks.get(0).moveTo(nextLoc);
                blocks.get(1).moveTo(nextLoca);
                rotationPos = 1;// will be % 4 with 4 blocks 

            } 
        } else if (rotationPos == 1) { 
            nextLoc = new Location(blocks.get(0).getLocation().getRow(), 
                blocks.get(0).getLocation().getCol() - 1);
            nextLoca = new Location(blocks.get(1).getLocation().getRow() - 2, 
                blocks.get(1).getLocation().getCol() - 1);
            if (gr.isValid(nextLoc)  && gr.isValid(nextLoca)) { 

                blocks.get(0).moveTo(nextLoc);
                blocks.get(1).moveTo(nextLoca);
                rotationPos = 0;// will be % 4 with 4 blocks 

            } 
        } 
    }
}
