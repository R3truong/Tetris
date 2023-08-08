/*
This is the logic class for Tetris
Richard Truong, Vackham Le
3/19/2022
 */
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.*;
import java.io.*;
public class TetrisGame 
{
    private int rows = 20;
    private int cols = 12;
    private TetrisBrick falling_brick;
    Random randomGen = new Random();
    private int numBrickTypes = 7;
    private int[][] background;
    boolean state = true;
    int score;
    int highScores[] = new int[10];

    
    public TetrisGame(int rows, int cols)
    {
        background = new int[cols][rows];
        spawnBrick();
    }
    
    private void spawnBrick()
    {
        int orientation = 0;
        int  randomChoice = randomGen.nextInt(numBrickTypes); 
        switch (randomChoice)
        {
            case 0:
                falling_brick = new ElBrick(cols, orientation, rows);
                return;
            case 1:
                falling_brick = new StackBrick(cols, orientation, rows);
                return;
            case 2:
                falling_brick = new JayBrick(cols, orientation, rows);
                return;
            case 3:
                falling_brick = new SquareBrick(cols, orientation, rows);
                return;
            case 4:
                falling_brick = new EssBrick(cols, orientation, rows);
                return;
            case 5:
                falling_brick = new LongBricks(cols, orientation, rows);
                return;
            case 6:
                falling_brick = new ZeeBrick(cols, orientation, rows);
                return;
        }
    }
    
    public void makeMove(int moveCode)
    {
        switch(moveCode)
        {
            case 37:
                this.falling_brick.moveLeft();
                if(!this.validateMove())
                    this.falling_brick.moveRight();
                break;
            case 39:
                this.falling_brick.moveRight();
                if(!this.validateMove())
                    this.falling_brick.moveLeft();
                break;
            case 38:
                this.falling_brick.rotate();
                return;
        }
        
     
      
        if(falling_brick != null)
        {
            this.falling_brick.moveDown();
            if(!validateMove())
            {
                transferColor();
                this.falling_brick = null;
                spawnBrick();
            }
        }
        
    }
    
    private boolean validateMove()
    {
       for(int b = 0; b < falling_brick.numSegments; b++)
       {               
            if(getSegRow(b) > cols+1)
            {
                return false;
            }
            if(getSegCol(b) > this.cols-3 || getSegCol(b) < 0 )
            {
                return false;
            }
            if(background[getSegCol(b)][getSegRow(b)+1] != 0)
                return false;
       }
       return true;
       
    }
    
    private void initBoard()
    {
        
    }
    
    public void newGame()
    {
       background = new int[cols][rows];
       spawnBrick();

    }
    
    public int fetchBoardPosition(int row, int col)
    {
        return background[col][row];
    }
    
    private void transferColor()
    {
        for(int boardFill = 0; boardFill < falling_brick.numSegments; boardFill++)
        {
        //    System.out.println(falling_brick.position[boardFill][1]);
             System.out.println(falling_brick.colorNums);
            background[falling_brick.position[boardFill][0]]
                      [falling_brick.position[boardFill][1]] = falling_brick.colorNums;

        }
    }
    
    public void fullRowCheck()
    {

        boolean filledRows [] = new boolean[rows];
        for (int fullRow = 0; fullRow < rows; fullRow++)
        {
            filledRows[fullRow] = true;
            for (int fullCol = 0; fullCol < cols-3; fullCol++)
            {
                if (background[fullCol][fullRow] == 0)
                {
                    filledRows[fullRow] = false;
                }
            }

        }
        int clearedRows = 0;
        for(int row = 0; row < rows; row++)
        {
            if(filledRows[row])
            {
                incrementBrickDown(row);
                clearedRows++;
            }
        }
        rowScoring(clearedRows);            
    }
    
    public void rowScoring(int clearedRows)
    {
        switch(clearedRows)
        {
            case 0:
                return;
            case 1:
                score+=100;
                return;
            case 2:
                score+=300;
                return;
            case 3:
                score+=600;
                return;
            case 4:
                score+=1200;
                return;
    }
    }
            
    private void incrementBrickDown(int rowDown)
    {
       for(int i = rowDown; i > 0; i--)
           for(int col = 0; col < cols; col++)
           {
               background[col][i] = background[col][i-1];
           }
       for(int b = 0; b < cols; b++)
       {
           background[b][0] = 0;
       }
    }
    
    public boolean gameOverLogic()
    {
        for(int i = 0; i < cols; i++)
        {
            if(background[i][0] > 0)
            {
                if (score > highScores[9])
                {
                    highScores[9] = score;
                    sortScores();
                    saveToFile();
                }
                return true;
            }
        }
        return false;
    }
    
    public void sortScores()
    {
        Arrays.sort(highScores);
        int[] newArray = new int[highScores.length];

        for (int i = 0; i < highScores.length; i++) {
           newArray[highScores.length - 1 - i] = highScores[i];
        }

        highScores = newArray;
    }
    
    public void saveToFile()
    {
        String saveFile = ("scores.txt");
        File outFile = new File(saveFile);

        if(!outFile.canWrite())
        {
            System.out.print("Could not write in file, check permissions");
            return;

        }
        
        try
        {
            FileWriter outWriter = new FileWriter(outFile);
            for(int e = 0; e < 10&&highScores[e]>0; e++)
            {
                outWriter.write(highScores[e]+"\n");
            }
            outWriter.close();
        }
        catch(IOException ioe)
        {
            System.out.print("Error writing to scores.txt");
        }
    }
    
    public void retrieveFromFile(String fileScoreName)
    {
      highScores = new int[10];
      File highScoresFile = new File(fileScoreName);
      try{
      Scanner scanText = new Scanner(highScoresFile);
      for(int i = 0; i < 10&&scanText.hasNextLine(); i++)
      {
          highScores[i] = Integer.parseInt(scanText.nextLine());
      }   
      }

      catch(IOException ioe)
      {
          System.out.print("Something happened");
      }
        sortScores();
    }
    public String generateHighScoreMessage()
    {
        String myString = "High Scores: \n";
        for(int i = 0; i < 10&& highScores[i]>0; i++)
        {
            myString+=  i+1+".) "+highScores[i]+"\n";
        }
        return myString;
    }
    
      public void saveGame(String customName)
    {
        File save = new File(customName+".csv");
        
        try
        {
            FileWriter writeSave = new FileWriter(save);
            for (int r = 0; r < rows; r++)
            {
                for (int c = 0; c < cols; c++)
                {
                    writeSave.write(background[c][r] + ",");
                    System.out.println(background[c][r]);
                }
            }
            writeSave.close();
        }
        catch (IOException ioe)
        {
            System.out.print("Was not able to save");
        }
    }
      public void loadGame(String customName)
      {
          File load = new File(customName);
          
          try
          {
              Scanner scan = new Scanner(load).useDelimiter(",");
              for (int r = 0; r < rows; r++)
              {
                    for (int c = 0; c < cols; c++)
                    {
                        background[c][r] = scan.nextInt();
                    }
                    
              }
              scan.close();
          }          
          catch(IOException ioe)
          {
              System.out.print("Could not load file");
          }
      }
          

    
    public int getSegRow(int segment)
    {
        return falling_brick.position[segment][1];
    }
    
    public int getSegCol(int segment)
    {
        return falling_brick.position[segment][0];
    }
    
    public void getNumSeg()
    {
        
    }
    public int getFallingBrickColor()
    {
        return falling_brick.colorNums;
    }
    

    public TetrisBrick getFalling_brick() {
        return falling_brick;
    }
}
