import javax.swing.*;
import java.awt.*;

public class LogoutWindow {
    JFrame frame;
    public void disposeWindow(){
        frame.dispose();
    }
    LogoutWindow(){

        frame = new JFrame("AirConnect Airways");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setBackground(Color.decode("#660033"));
        frame.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setForeground(Color.WHITE);
        panel.setBounds(250,100,800,500);

        JLabel label = new JLabel("Thanks for using our system!");
        label.setBounds(130,50,200,30);

        panel.add(label);
        frame.add(panel);
        frame.setVisible(true);
    }
}