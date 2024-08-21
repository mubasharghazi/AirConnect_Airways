import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminDashBoard {
    AdminDashBoard() {

        JFrame frame = new JFrame("AirConnect Airways");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  // getting screen size
        frame.setSize(screenSize.width, screenSize.height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // maximize both height and width
        frame.getContentPane().setBackground(Color.decode("#660033"));

        JMenu menu1 = new JMenu("Flights");
        JMenu menu2 = new JMenu("Bookings");
        JMenu menu3 = new JMenu("Data");
        JMenu menu4 = new JMenu("Notifications");
        JMenu menu5 = new JMenu("Logout");
        JMenuBar bar = new JMenuBar();

        JMenuItem item1 = new JMenuItem("Enter Flights");
        JMenuItem item2 = new JMenuItem("Edit Flights");
        JMenuItem item3 = new JMenuItem("View Flight Schedule");

        menu1.add(item1);
        menu1.add(item2);
        menu1.add(item3);

        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new FlightDetails();
            }
        });
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new EditFlightsDetails();
            }
        });
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new ViewFlights();
            }
        });

        JMenuItem item4 = new JMenuItem("View Bookings");
        JMenuItem item5 = new JMenuItem("Edit passengers data");
        JMenuItem item6 = new JMenuItem("View Booking of specific flightId");

        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new ViewBookings();
            }
        });


        menu2.add(item4);
        menu2.add(item5);
        menu2.add(item6);

        JMenuItem item7 = new JMenuItem("View all users data");
        JMenuItem item8 = new JMenuItem("view specific user data");
        JMenuItem item9 = new JMenuItem("view all passengers");

        menu3.add(item7);
        menu3.add(item8);
        menu3.add(item9);

        JMenuItem item10 = new JMenuItem("Flight delay or not?");
        menu4.add(item10);

        item10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Notification();
            }
        });

        menu1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

            }
        });


        menu5.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                frame.dispose();
            }
        });

        bar.add(menu1);
        bar.add(menu2);
        bar.add(menu3);
        bar.add(menu4);
        bar.add(menu5);
        frame.setJMenuBar(bar);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
}