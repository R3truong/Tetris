/*
This is a subclass of Tetris Brick that is in the shape of a square brick
Richard Truong, Vackham Le
3/19/2022
 */
public class SquareBrick extends TetrisBrick
{
    int[][] init_pos = new int[4][2];

    public SquareBrick(int cols, int orient,int rows)
    {
        super(cols, orient, rows,6);

    }
    public int[][] initPosition(int cols)
    {  
        int[][] init_pos = new int[4][2];
        
        init_pos[0][0] = 4;
        init_pos[1][0] = 5;
        init_pos[2][1] = -1;
        init_pos[2][0] = 4;
        init_pos[3][1] = -1;
        init_pos[3][0] = 5;
        return init_pos;
    }
    
    public void rotate()
    {
        
    }
    public void unrotate()
    {
        
    }
}
