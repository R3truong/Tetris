/*
This is the super class that has all the attributes for the other brick types
Richard Truong, Vackham Le
3/19/2022
 */
import java.util.*;
public abstract class TetrisBrick 
{
    protected int numSegments = 4;
    protected int[][] position = new int[numSegments][2];
    protected int colorNums;
    protected int orient = 0;
    
    public TetrisBrick(int cols, int orient,int rows,int color)
    {
        position = initPosition(cols);
        Random rand = new Random();
        int brickColorsGen = 6;
        colorNums = color;
    }
    
    public void moveDown()
    {
        for (int i = 0; i < numSegments; i++)
        {
            position[i][1]++;
        }
    }

    public int getColorNums() {
        return colorNums;
    }
    
    public abstract int[][] initPosition(int cols);
    
    public void moveUp()
    {
        for (int i = 0; i < numSegments; i++)
        {
            position[i][1]--;
        }
    }

    public abstract void rotate();
    
    public abstract void unrotate();
    
    
    public void moveLeft()
    {
        
        for (int i = 0; i < numSegments; i++)
        {
            position[i][0]--;
        } 
            
    }
    public void moveRight()
    {
        for (int i = 0; i < numSegments; i++)
        {
            position[i][0]++;
        } 
    }
    
}
