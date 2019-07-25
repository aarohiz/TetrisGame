import info.gridworld.actor.*; 
import info.gridworld.grid.*; 
import java.util.ArrayList; 
import java.awt.Color; 
import java.util.Random;

public class TetrisBug extends Bug 
{ 
    protected Color color;
    public TetrisBug(Color x) 
    { 
        super();
        Color color = null;
        switch(new Random(). nextInt(5)){
            case 0:
                color= Color.CYAN;
                break;
            case 1:
                color = Color.CYAN;
                break;
            case 2:
                color = Color.CYAN;
                break;
            case 3:
                color = Color.CYAN;
                break;
            case 4:
                color = Color.CYAN;
                break;
            default:
                color = Color.CYAN;
                break;}
                setColor(color);
        setDirection(180); 
    } 

    public void move() 
    { 
        Grid<Actor> gr = getGrid(); 
        if (gr == null) 
            return; 
        Location loc = getLocation(); 
        Location next = loc.getAdjacentLocation(getDirection()); 
        if (gr.isValid(next)) 
            moveTo(next); 
        else 
            removeSelfFromGrid(); 
    } 

    public void act() 
    { 
        //this is empty for a reason. 
    } 
} 