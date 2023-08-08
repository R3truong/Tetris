/*
This is a subclass of Tetris Brick that is in the shape of a L brick
Richard Truong, Vackham Le
3/19/2022
 */
public class ElBrick extends TetrisBrick
{
    int[][] init_pos = new int[4][2];

    public ElBrick(int cols, int orient,int rows)
    {
        super(cols,orient, rows,2);
    }
    public int[][] initPosition(int cols)
    {  
        int[][] init_pos = new int[4][2];
        
        init_pos[0][0] = 4;
        init_pos[1][0] = 5;
        init_pos[2][0] = 6;
        init_pos[3][0] = 6;
        init_pos[3][1] = -1;
        return init_pos;
    }
    
    public void rotate()
    {
        switch(orient)
        {
            case 0:
                position[0][0] +=2; //4
                position[0][1] -=2; //-2
                position[1][0] +=1;  //6
                position[1][1] -=1; //-1
                position[3][0] +=1; // 7
                position[3][1] +=1; // 0
                orient+=1;
                return;
            case 1:
                position[0][0] +=2;
                position[0][1] +=2;
                position[1][0] +=1; 
                position[1][1] +=1;
                position[3][0] -=1;
                position[3][1] +=1;
                orient+=1;
                return;
            case 2:
                position[0][0] -=1;
                position[0][1] +=1;
                position[3][0] +=1;
                position[3][1] +=1;
                orient+=1;
                return;
            case 3:
                unrotate();
                return;
    }
    }
    public void unrotate()
    {
                position[0][0] -=3; 
                position[0][1] -=1;
                position[1][0] -=2; 
                position[3][0] -=1; 
                position[3][1] -=3;
                orient = 0;
    }
}
