/*
This class calls both the Tetris Game and the Tetris Display so it may display it in the window
Richard Truong, Vackham Le
3/19/2022
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class TetrisWindow extends JFrame
{
    private int win_width = 400;
    private int win_height = 400;
    private TetrisDisplay display;
    private TetrisGame game;
    private int game_rows = 20;
    private int game_cols = 12;
    public TetrisWindow()
    {
        this.setTitle("Tetris     Richard Truong, Vackham Le");
        this.setSize(win_width, win_height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game = new TetrisGame(game_rows,game_cols);
        display = new TetrisDisplay(game);
        initMenuBar();
        game.retrieveFromFile("scores.txt");
        this.add(display);
        this.setVisible(true);
    }
    
    public void initMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();        
        JMenu leaderboard = new JMenu("Leaderboard");
        JMenuItem showScores = new JMenuItem("Show High Scores");
        showScores.addActionListener(new ActionListener()
         {
         public void actionPerformed(ActionEvent ac)
         {
             JOptionPane.showMessageDialog(null,game.generateHighScoreMessage());
         }
         });
        leaderboard.add(showScores);
        menuBar.add(leaderboard);
        JMenu gameOptions = new JMenu("Game Options");
        JMenuItem saveGame = new JMenuItem("Save Game");
        saveGame.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ac)
            {
                String saveName = JOptionPane.showInputDialog("Your Save File Name: ");
                game.saveGame(saveName);
                JOptionPane.showMessageDialog(null, "The game has been successfully saved!");
            }
        });
        JMenuItem loadGame = new JMenuItem("Load Game");
        loadGame.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ac)
            {
                JFileChooser fc = new JFileChooser();
                int returnValue = fc.showOpenDialog(null);
                if(returnValue == JFileChooser.APPROVE_OPTION)
                {
                    File selectedFile = fc.getSelectedFile();
                    String fileName = fc.getSelectedFile().getName();
                    game.loadGame(fileName);
                }
                JOptionPane.showMessageDialog(null, "The game has been successfully loaded!");
            }
        });
        gameOptions.add(loadGame);
        gameOptions.add(saveGame);
        menuBar.add(gameOptions);
        this.setJMenuBar(menuBar);
        

        
    }
    

    public static void main(String[] args) 
    {
      TetrisWindow tetris = new TetrisWindow();
        
    }
}
