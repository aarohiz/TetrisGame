
import info.gridworld.actor.*; 
import info.gridworld.grid.*; 
import java.util.ArrayList; 
import java.awt.Color; 

public class TetrisBlock extends TetrisBug { 
    protected int rotationPos; 
    protected ArrayList<TetrisBug> blocks;
    protected Grid<Actor> gr; 
    protected boolean canDown;
    public Location nextLoc;
    /** 
     * default constructor 
     */ 
    public TetrisBlock() { 
        super(Color.blue); 
        rotationPos = 0; 
        canDown = true;
        gr = TetrisGame.world.getGrid(); 

        
        putSelfInGrid(gr, new Location(1, 5)); 
        blocks = new ArrayList<TetrisBug>(); 
        TetrisBug a; 

        a = new TetrisBug(Color.blue); 
        a.putSelfInGrid(gr, new Location(0, 5)); 
        blocks.add(a); 

    } 

    /** 
     * TetrisBlock and its TetrisBugs must face down (direction 180) If they can 
     * move down, they will. Otherwise, it will ask TetrisGame to create a new 
     * TetrisBlock since this one is stuck at the bottom. 
     */ 
    public void act() { 
        setDirection(180); 
        for (TetrisBug tb : blocks)
        {
            tb.setDirection(180); 
        }

        if (canMoveDown()) 
            moveDown(); 
        else 
        { 
            if (!TetrisGame.currentBlock.canMoveDown()) 
            {
                canDown = true;
                TetrisGame.nextTetrisBlock(); 
            }
        } 

    }

    /** 
     * Move the TetrisBlock and its TetrisBugs one cell. (they should already be 
     * facing down) Note: The order in which all the TetrisBugs move is important 
     * and depends on the current rotationPos. 
     */ 
    public void moveDown() { 
        for (TetrisBug tb: blocks)
            tb.setDirection(180); 
        if (rotationPos == 0 ) { 
            move(); 
            for (TetrisBug tb: blocks)
                tb.move(); 
        } else if (rotationPos == 1 && canMove() && canMoveDown()) { 
            for (TetrisBug tb: blocks)
                tb.move();
            move(); 
        } 
    } 

    /** 
     * Returns true if the TetrisBlock and its TetrisBugs can move (they should 
     * already be facing down) Otherwise, returns false. 
     */ 
    public boolean canMoveDown() {
        for (TetrisBug tb: blocks)
            tb.setDirection(180); 
        if (rotationPos == 0) 
            return canMove(); 

        else if (rotationPos == 1) 
            return canMove() && blocks.get(0).canMove(); 
        else 
            return true; 
    } 

    /** 
     * Sets the direction of the TetrisBlock and its TetrisBugs to 90 (right) If 
     * they can move, they will move one cell (to the right) 
     */ 

    public void moveRight() {
        setDirection(90);

        for (TetrisBug tb : blocks)
            tb.setDirection(90);
        if (rotationPos == 0) {
            if (canMove() && blocks.get(0).canMove() ) {
                move();
                for (TetrisBug tb : blocks)
                    tb.move();
                //move();

            }
        } else if (rotationPos == 1) {
            if (canMove()&& blocks.get(0).canMove()) {
                move();
                //blocks.get(0).move();
                for (TetrisBug tb : blocks)
                    tb.move();
            }
        }
        for (TetrisBug tb : blocks)
            tb.setDirection(90);
    } 

    /** 
     * Sets the direction of the TetrisBlock and its TetrisBugs to 270 (left) If 
     * they can move, they will move one cell (to the left) 
     */ 
    public void moveLeft() {
        setDirection(270);
        for (TetrisBug tb : blocks)
            tb.setDirection(270);
        if (rotationPos == 0) {
            if (canMove() && blocks.get(0).canMove()) {
                for(int x = 1; x < blocks.size(); x++)
                    blocks.get(x).move();
                blocks.get(0).move();
                move();

                //move();

                //}
            } else if (rotationPos == 1) {
                if (canMove()) {
                    move();
                    //blocks.get(0).move();
                    for (TetrisBug tb : blocks)
                        tb.move();
                }
            }
        }
    }

    /** 
     * If the TetrisBlock and its TetrisBugs can rotate, then they will all move 
     * to their proper location for the given rotation designated by 
     * rotationPos... Update rotationPos. 
     */ 
    public void rotate() { 
        Location nextLoc; 

        if (rotationPos == 0) { 
            // only one block must move 
            nextLoc = new Location(getLocation().getRow() - 1, 
                getLocation().getCol() + 1); 
            if (gr.isValid(nextLoc) && gr.get(nextLoc) == null) { 
                moveTo(nextLoc); 
                rotationPos = (rotationPos + 1) % 2;// will be % 4 with 4 blocks 
            } 
        } else if (rotationPos == 1) { 
            nextLoc = new Location(getLocation().getRow() + 1, 
                getLocation().getCol() - 1); 
            if (gr.isValid(nextLoc) && gr.get(nextLoc) == null) { 
                moveTo(nextLoc); 
                rotationPos = (rotationPos - 1) % 2;// will be % 4 with 4 blocks 
            } 
        } 
    } 

    
} 
