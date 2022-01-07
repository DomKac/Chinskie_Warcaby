import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class ButtonExample {


    public static void main(String[] args) {
        JFrame f=new JFrame("Button Example");
        final JTextField tf=new JTextField();
        tf.setBounds(50,50, 150,20);
        JButton b=new JButton("Click Here");
        b.setBounds(50,100,95,30);
        ActionListener wyb_pionek = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(b.getBackground() != Color.BLUE) {
                    b.setBackground(Color.BLUE);
                }
                else{
                    b.setBackground(Color.CYAN);
                }
            }
        };
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tf.setText("Welcome to Javatpoint.");
            }
        });
        b.addActionListener(wyb_pionek);
        f.add(b);f.add(tf);
        f.setSize(400,400);
        f.setLayout(null);
        f.setVisible(true);
    }
}
