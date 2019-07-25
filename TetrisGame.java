import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.util.ArrayList;
import java.awt.Color;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Rock;
import info.gridworld.grid.BoundedGrid;
import java.util.Random;
import javax.swing. *;
import java.awt.*;
public class TetrisGame {
    public static ActorWorld world = new ActorWorld(new BoundedGrid(19, 14));
    //creates new world of dimensions 19 by 14
    public static TetrisBlock currentBlock;
    //tracks currentBlock
    public static int score;
    //tracks current score
    public static Grid<Actor> gr; 

    private static JFrame control;

    //menu stuff
    public static void main(String[] args) {
        for(int i = 0; i < 19; i++)
        {
            world.add(new Location(i,0),new Rock());
            world.add(new Location(i,1),new Rock());
            world.add(new Location(i,12),new Rock());
            world.add(new Location(i,13),new Rock());
            //adds row of rocks at 0,1,12,13
        }

        //MENU STUFF
        control = new JFrame();
        control.setTitle("Tetris 2.0");
        control.setSize(800,800);
        control.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel[] blank = new JLabel[7];
        for( int index = 0; index < blank.length; index++ )
        {
            blank[index] = new JLabel( "" );
        } // end for
        JLabel greeting = new JLabel("Hello, welcome to Tetris!!");
        JLabel how = new JLabel("Here's how you play:");
        JLabel start = new JLabel( "   Click Run to begin." );
        JLabel pause = new JLabel( "Click Stop to pause." );
        JLabel left = new JLabel( "   Move LEFT: ←" );
        JLabel right = new JLabel( "Move RIGHT: →" );
        JLabel clockwise = new JLabel( "   TURN : ↑" );

        JLabel fall = new JLabel( "   FALL: ↓" );
        JLabel speed = new JLabel( "   Adjust Speed for Difficulty." );
        JLabel quit = new JLabel( "Quit: ESC" );

        control.setLayout( new GridLayout( 0, 2, 10, 10 ) );

        control.getContentPane().add(greeting);
        control.getContentPane().add(how);
        control.getContentPane().add( start );
        control.getContentPane().add( pause );
        control.getContentPane().add( blank[0] );
        control.getContentPane().add( blank[1] );
        control.getContentPane().add( left );
        control.getContentPane().add( right );
        control.getContentPane().add( clockwise );

        control.getContentPane().add( blank[2] );
        control.getContentPane().add( blank[3] );
        control.getContentPane().add( fall );
        control.getContentPane().add( blank[4] );
        control.getContentPane().add( blank[5] );
        control.getContentPane().add( blank[6] );
        control.getContentPane().add( speed );
        control.getContentPane().add( quit );

        control.setLocationRelativeTo( null );
        control.setVisible( true );
        control.pack();

        control.setVisible(true);
        //MENU STUFF ENDS

        nextTetrisBlock();
        java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager()
        .addKeyEventDispatcher(new java.awt.KeyEventDispatcher() {
                public boolean dispatchKeyEvent(java.awt.event.KeyEvent event) {
                    String key = javax.swing.KeyStroke.getKeyStrokeForEvent(event).toString();
                    if (key.equals("pressed UP"))
                        currentBlock.rotate();
                    if (key.equals("pressed RIGHT"))
                        currentBlock.moveRight();
                    if (key.equals("pressed DOWN"))
                        currentBlock.act();
                    if (key.equals("pressed LEFT"))
                        currentBlock.moveLeft();
                    world.show();
                    return true;
                }
            });
        world.show();
    }

    /**
     * Calls removeCompleteRows and chooses a new TetrisBlock at random
     */
    public static void nextTetrisBlock() {
        gr = TetrisGame.world.getGrid(); //gets grid
        removeCompleteRows();
        score +=3;
        if (gr.get(new Location(0, 5)) != null )
        { 
            javax.swing.JOptionPane.showMessageDialog(null, "Score: " 
                + TetrisGame.score, "GAME OVER!", 0); 
            System.exit(0); 
        } 
        TetrisBlock randomBlock = new TetrisBlock();
        //choose random block
        int randNum =(int)(Math.random()*4)+1; 

        switch(randNum){
            case 1:
            randomBlock = new TetrisBlockO();
            break;
            case 2:
            randomBlock = new TetrisBlockL();
            break;
            case 3:
            randomBlock = new TetrisBlockI();
            break;
            case 4:
            randomBlock = new TetrisBlockZ();
            break;
        }
        currentBlock = randomBlock;

    }

    /**
     * checks each row 1 through 18 (skip row 0) for full rows
     * if a row is full, then remove the actor from each cell in that row
     * and ask each actor located above the just deleted row to act and
     * update the score++
     */
    public static void removeCompleteRows() 
    {
        int columnsFilled; 
        Grid grid = world.getGrid(); 

        Object x;
        //loops through rows only after each column has finished 
        for(int row = 0; row < 18; row++) {  //needed >= 
            columnsFilled = 0;    //need to reinitialize this every iteration 
            for(int col = 0; col <= grid.getNumCols() - 1; col++) { //needed <= 

                if (grid.get(new Location(row,col)) != null) { 
                    columnsFilled++; 
                } 
            } 
            if (columnsFilled == 14) { 
                for(int col = 2; col < 12; col++)
                { 

                    //world.remove(new Location(row,col)); 
                    grid.remove(new Location(row, col));

                } 
                score+=10;
                for(int test = row; test > 0; test--)
                {
                    for (int colTest = 2; colTest < 12; colTest ++)
                    {
                        if( grid.get(new Location (test, colTest)) != null){

                            x = grid.get(new Location (test, colTest));
                            grid.remove(new Location (test, colTest));
                            grid.remove(new Location (test + 1, colTest));
                            grid.put(new Location (test + 1 , colTest), x);
                        } 
                    } 

                }
            }
            columnsFilled = 0; 
        }
    }

    // x = grid.get(new Location (test, colTest));
    // grid.remove(new Location (test, colTest));
    // grid.remove(new Location (test + 1, colTest));
    // grid.put(new Location (test + 1 , colTest), x);
}