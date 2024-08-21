import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu {
    Menu() {
        JFrame frame = new JFrame("AirConnect Airways");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(Color.decode("#660033"));

        JMenu menu1 = new JMenu("Flight Schedule");
        JMenu menu2 = new JMenu("Book Flight");
        JMenu menu3 = new JMenu("Cancel Ticket");
        JMenu menu4 = new JMenu("Boarding Pass");
        JMenu menu5 = new JMenu("Help");
        JMenu menu6 = new JMenu("Notifications");
        JMenu menu7 = new JMenu("Logout");
        JMenuBar bar = new JMenuBar();

        JMenuItem helpItem1 = new JMenuItem("User Manual");
        JMenuItem helpItem2 = new JMenuItem("Get your Ticket No");
        JMenuItem helpItem3 = new JMenuItem("About");


        helpItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GetTicket();
                frame.dispose();
            }
        });

        menu1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new FlightSchedule();
                frame.dispose();
            }
        });

        menu2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new BookFlight();
                frame.dispose();
            }
        });

        menu6.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new UserNotification();
                frame.dispose();
            }
        });

        menu3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new CancelTicket();
                frame.dispose();
            }
        });

        menu4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new BoardingPass();
                frame.dispose();
            }
        });

        menu7.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });

        menu5.add(helpItem1);
        menu5.add(helpItem2);
        menu5.add(helpItem3);
        bar.add(menu1);
        bar.add(menu2);
        bar.add(menu3);
        bar.add(menu4);
        bar.add(menu5);
        bar.add(menu6);
        bar.add(menu7);
        frame.setJMenuBar(bar);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

}