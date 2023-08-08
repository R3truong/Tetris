/*
This is a subclass of Tetris Brick that is in the shape of a long brick
Richard Truong, Vackham Le
3/19/2022
 */
public class LongBricks extends TetrisBrick
{
    int[][] init_pos = new int[4][2];

    public LongBricks(int cols,int orient,int rows)
    {
        super(cols,orient,rows,5);

    }
    public int[][] initPosition(int cols)
    {  
        int[][] init_pos = new int[4][2];
        
        init_pos[0][0] = 3;
        init_pos[1][0] = 4;
        init_pos[2][0] = 5;
        init_pos[3][0] = 6;
        return init_pos;
    }
    
    public void rotate()
    {

        switch(orient)
        {
            case 0:
            position[0][0] +=2;
            position[0][1] -=2;
            position[1][0] +=1;
            position[1][1] -=1;
            position[3][0] -=1;
            position[3][1] +=1;
            orient+=1;
            return;
            case 1:
                unrotate();
            return;
        }
    }
    public void unrotate()
    {
            position[0][0] -=2;
            position[0][1] +=2;
            position[1][0] -=1;
            position[1][1] +=1;
            position[3][0] +=1;
            position[3][1] -=1;
            orient = 0;
            
    }
}
