import info.gridworld.actor.*; 
import info.gridworld.grid.*; 
import java.util.ArrayList; 
import java.awt.Color; 

public class TetrisBlockO extends TetrisBlock
{ 
    public TetrisBlockO() { 

        TetrisBug a; 
        a = new TetrisBug(Color.yellow); 
        a.putSelfInGrid(gr, new Location(1, 4)); 
        blocks.add(a);

        TetrisBug b; 
        b = new TetrisBug(Color.green); 
        b.putSelfInGrid(gr, new Location(0, 4)); 
        blocks.add(b);

    } 

    public boolean canMoveDown() {
        for (TetrisBug tb: blocks)
            tb.setDirection(180); 
        if (rotationPos == 0) 
            return canMove() && blocks.get(1).canMove(); 
        else 
            return true;
    } 

    public void moveRight() {
        setDirection(90);
        for (TetrisBug tb : blocks)
            tb.setDirection(90);
        if (canMove() && blocks.get(0).canMove())
        {
            move();
            for(TetrisBug tb: blocks)
                tb.move();
        }
    }

    public void moveLeft() {
        setDirection(270);
        for (TetrisBug tb : blocks)
            tb.setDirection(270);
        if (rotationPos == 0) {
            if (blocks.get(1).canMove() && blocks.get(2).canMove()) {
                for(int x = 1; x < blocks.size(); x++)
                    blocks.get(x).move();
                blocks.get(0).move();
                move();

            } 
        }
    }

    public void rotate()
    {}

} 