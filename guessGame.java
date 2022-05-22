import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class guessGame extends WindowAdapter implements ActionListener{
    JFrame f = new JFrame("Number Guessing Game");
    JLabel l,topic,round,rvalue,score,svalue,result;
    JButton b,again;
    JTextField tf;
    int roundvalue=3;
    Random random = new Random();
    int rand = random.nextInt(101);

    guessGame()
    {
        l = new JLabel("Enter your choice : ");
        topic = new JLabel("NUMBER GUESSING GAME");
        tf = new JTextField();
        b = new JButton("Guess");
        again = new JButton("Play Again");
        round = new JLabel("Rounds Left : ");
        rvalue = new JLabel("3");
        score = new JLabel("Your Score : ");
        svalue = new JLabel();
        result = new JLabel();

        topic.setFont(new Font("SansSerif",Font.BOLD,20));
        l.setFont(new Font("Arial",Font.BOLD,14));
        svalue.setFont(new Font("SansSerif",Font.BOLD,40));
        rvalue.setFont(new Font("SansSerif",Font.BOLD,40));
        result.setFont(new Font("SansSerif",Font.BOLD,16));
        
        topic.setBounds(200,30,300,40);
        round.setBounds(460,260,100,40);
        score.setBounds(460,330,100,40);
        svalue.setBounds(460,360,100,40);
        rvalue.setBounds(460,290,100,40);
        result.setBounds(60,220,600,40);
        l.setBounds(180,100,150,40);
        tf.setBounds(320,108,150,24);
        b.setBounds(280,170,100,40);
        again.setBounds(280,360,100,40);

        again.setVisible(false);

        b.addActionListener(this);

        f.add(b);f.add(l);f.add(tf);f.add(topic);f.add(round);f.add(rvalue);
        f.add(score);f.add(svalue);f.add(result);f.add(again);
        f.addWindowListener(this);
        f.setSize(700,500);
        f.setLayout(null);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    { 
        try
        {
            if(e.getSource()==b)
            {
                roundvalue=roundvalue-1;
                String r = Integer.toString(roundvalue);
                int num = Integer.parseInt(tf.getText());
                int diff = rand-num;

                rvalue.setText(r);
                if(diff==0)
                {
                    result.setText("CONGRATULATIONS! You have guessed the exact number");
                    result.setForeground(Color.green);
                    if(roundvalue==2)
                    {
                        svalue.setText("100");
                        svalue.setForeground(Color.green);
                        newgame();
                    }
                    if(roundvalue==1)
                    {
                        svalue.setText("50");
                        svalue.setForeground(Color.blue);
                        newgame();
                    }
                    if(roundvalue==0)
                    {
                        svalue.setText("20");
                        svalue.setForeground(Color.orange);
                        newgame();
                    }
                }
                if(-10<diff && diff<0)
                {
                    result.setText("Too Close! The system's number is less than the given number!");
                    result.setForeground(Color.blue);
                }
                if(diff<-10)
                {
                    result.setText("Too Far! The system's number is less than the given number!");
                    result.setForeground(Color.orange);
                }
                if(0<diff && diff<10)
                {
                    result.setText("Too Close! The system's number is greater than the given number!");
                    result.setForeground(Color.blue);
                }
                if(diff>10)
                {
                    result.setText("Too Far! The system's number is greater than the given number!");
                    result.setForeground(Color.orange);
                }
                
                if(roundvalue==0 && diff!=0)
                {
                    rvalue.setText("0");
                    svalue.setText("0");
                    newgame();
                }
            }
            if(e.getSource()==again)
            {
                f.dispose();
                new guessGame();
            }
        }
        catch(java.lang.NumberFormatException n)
        {
            JOptionPane.showMessageDialog(null,"Please enter a valid number!", "Alert", JOptionPane.WARNING_MESSAGE);
            roundvalue=roundvalue+1;
        }
    }

    public void newgame()
    {
        again.setVisible(true);
        b.setEnabled(false);
        again.addActionListener(this);
    }

    public void windowClosing(WindowEvent e)
    {
        f.dispose();
    }

    public static void main(String args[]) {
    new guessGame();
    }
}
