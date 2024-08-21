import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
public class Welcome {
    JFrame frame;

    public void disposeFrame(){
        frame.dispose();
    };

    Welcome() {

        frame = new JFrame("AirConnect Airways");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(Color.WHITE);

        ImageIcon logo = new ImageIcon("C:\\Users\\Mubashar Ghazi\\Downloads\\photo.jpg");
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setBounds((screenSize.width - logo.getIconWidth()) / 2, (screenSize.height - logo.getIconHeight()) / 2 - 100, logo.getIconWidth(), logo.getIconHeight());

        JLabel airline = new JLabel("AIRCONNECT AIRWAYS", SwingConstants.CENTER);
        airline.setBounds(0, (screenSize.height - logo.getIconHeight()) / 2 + 330, screenSize.width, 30);
        airline.setForeground(Color.decode("#660033"));
        airline.setFont(new Font("Serif", Font.BOLD, 24));


        frame.add(airline);
        frame.add(logoLabel);
        frame.setLayout(null);
        frame.setVisible(true);

    }

}