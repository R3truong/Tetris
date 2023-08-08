/*
This is the graphic class for Tetris
Richard Truong, Vackham Le
3/19/2022
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class TetrisDisplay extends JPanel
{
    private TetrisGame game;
    private int start_x = 100;
    private int start_y = 60;
    private int cell_size = 15;
    private int wall_width = 15;
    private int well_size = 11;
    private Timer timer;
    private Color colorChoice;
    private Color color[];
    private boolean Pause = true;
    private int well_width = 10;

    
    
    public TetrisDisplay(TetrisGame gam)
        {
            game = gam;
            int delay = 300;
            timer = new Timer(delay, new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                cycleMove();
            }
          });
            this.addKeyListener(new KeyAdapter(){
                public void keyPressed(KeyEvent ke)
                {
                    translateKey(ke);
                }
            });
            this.setFocusable(true);
            this.setFocusTraversalKeysEnabled(false);
            
            timer.start();

            color = new Color []{Color.WHITE,Color.RED,Color.YELLOW,Color.BLUE,Color.GREEN,Color.PINK,Color.ORANGE,Color.CYAN};
        }
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            drawWell(g);
            drawBrick(g);
            drawBackground(g);
            scoreShow(g);
            gameOver(g);
            
        }
        private void drawWell (Graphics g)
        {    
            g.setColor(Color.black);
            // left wall
            g.fillRect(start_x-wall_width, start_y-wall_width, wall_width, cell_size*wall_width);
            // right wall
            g.fillRect(start_x+(well_width*cell_size), start_y-wall_width, wall_width, cell_size*wall_width);
            // bottom wall
            g.fillRect(start_x-wall_width, start_y-wall_width+cell_size*wall_width, cell_size+(well_size*wall_width), wall_width);
            
        }
        

        private void drawBrick(Graphics g)
        {
           
         
        
            for(int b = 0; b < game.getFalling_brick().numSegments; b++)
            {

            g.setColor(color[game.getFalling_brick().getColorNums()]);
            g.fillRect(start_x + (game.getSegCol(b)*cell_size), start_y+(game.getSegRow(b)*cell_size), cell_size, cell_size);
            }
            for(int b = 0; b < game.getFalling_brick().numSegments; b++)
            {
            g.setColor(Color.BLACK);
            g.drawRect(start_x + (game.getSegCol(b)*cell_size), start_y+(game.getSegRow(b)*cell_size), cell_size, cell_size);
            }
            
        }

        private void cycleMove()
        {
            game.makeMove(0);
            game.getFalling_brick();
            game.fullRowCheck();
            repaint();
        }
        
        private void drawBackground(Graphics g)
        {
            for(int row = 0; row < wall_width; row++)
                for(int col = 0; col < well_width; col++)
                    
                {
                    int cell_color = game.fetchBoardPosition(row+1, col);
                    if(cell_color > 0)
                    {
                     g.setColor(color[cell_color]);
                     g.fillRect(start_x + (col*cell_size), start_y+(row*cell_size), cell_size, cell_size);
                     g.setColor(Color.BLACK);
                     g.drawRect(start_x + (col*cell_size), start_y+(row*cell_size), cell_size, cell_size);
                    }
                }
        }
        
        private void translateKey(KeyEvent ke)
        {
            int code = ke.getKeyCode();
            System.err.print("\n Key Pressed "+code);
            
            switch(code)
            {
                
                case KeyEvent.VK_LEFT:
           //         game.getFalling_brick().moveLeft();
                    game.makeMove(code);
                    break;
                case KeyEvent.VK_RIGHT:
            //        game.getFalling_brick().moveRight();
                    game.makeMove(code);
                    break;
                case KeyEvent.VK_UP:
                    game.makeMove(code);
                    repaint();
                    break;
                case KeyEvent.VK_N:
                    game.newGame();
                    break;
                case KeyEvent.VK_SPACE:
                    System.out.print("Pause");
                    if(Pause)
                    {
                        timer.stop();
                        System.out.print("Stop");
                        Pause = false;
                    }
                    else
                    {
                        Pause = true;
                        System.out.print("Start");
                        timer.start();
                    }
                    
            }
            

        }
         public void scoreShow(Graphics g)
        {
            Font scoreFontSize = new Font("Arial", 0, 30);
            Graphics2D g2 = (Graphics2D) g;
            g2.setFont(scoreFontSize);
            String score = " Score: " + game.score;
            g2.drawString(score, start_x, start_y-30);
        }
         public void gameOver(Graphics g)
         {
             if(game.gameOverLogic())
             {
                  timer.stop();
                  Font gameOverFont = new Font("Arial", 0, 50);
                  Graphics2D g2 = (Graphics2D) g;
                  g2.setFont(gameOverFont);
                  g.setColor(Color.RED);
                  String score = "Game Over";
                  g2.drawString(score, start_x-50, start_y+110);
             }
         }
}
