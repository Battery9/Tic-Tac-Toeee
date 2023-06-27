import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

public class GameEngine implements ActionListener {
    //All Variables needed
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel text = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1; //Just for determine the choice between 'X'(true) & 'O'.
    boolean gameOver;

    GameEngine() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        
        text.setBackground(Color.CYAN);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("Segoe Print", Font.BOLD, 50));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setText("Welcome to Tic-Tac-Toe!");
        text.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        button_panel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Ink Free", Font.BOLD, 70));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);

        }

        title_panel.add(text);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (player1) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(Color.BLUE);
                        buttons[i].setText("X");
                        player1 = false;
                        text.setText("O turn");
                        checkWinner();
                    }
                } else {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(Color.RED);
                        buttons[i].setText("O");
                        player1 = true;
                        text.setText("X turn");
                        checkWinner();
                    }
                }
            }
        }
    }

    public void startGame() {
        if (random.nextInt(2) == 0) {
            player1 = true;
            text.setText("Start with X");
        } else {
            text.setText("Start with O");
        }
    }

    public void checkWinner() {
        // Define winning combinations
        int[][] winningCombinations = {
                { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 },
                { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 },
                { 0, 4, 8 }, { 2, 4, 6 }
        };

        // Check for winning combinations
        for (int[] combination : winningCombinations) {
            int a = combination[0];
            int b = combination[1];
            int c = combination[2];

            if (buttons[a].getText().equals("X") &&
                    buttons[b].getText().equals("X") &&
                    buttons[c].getText().equals("X")) {
                xWins(a, b, c);
            } else if (buttons[a].getText().equals("O") &&
                    buttons[b].getText().equals("O") &&
                    buttons[c].getText().equals("O")) {
                oWins(a, b, c);
            }
        }

        gameOver = true;
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().equals("")) {
                gameOver = false;
                break;
            }
        }

        if (gameOver) {
            resetGame();
        }
    }

    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        text.setText("X Wins");

        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        text.setText("O Wins");

        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void resetGame() {
        text.setText("Reseting the Game...");

        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 9; i++) {
                    buttons[i].setText("");
                    buttons[i].setBackground(null);
                    buttons[i].setEnabled(true);
                }

                startGame();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}