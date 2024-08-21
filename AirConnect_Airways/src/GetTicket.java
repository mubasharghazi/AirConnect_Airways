import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetTicket {
    GetTicket() {
        JFrame frame = new JFrame("AirConnect Airways");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(Color.decode("#660033"));
        frame.setLayout(null);

        JLabel titleLabel = new JLabel("Get Ticket", SwingConstants.CENTER);
        titleLabel.setBounds(0, 90, screenSize.width, 40);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        JPanel panel = new JPanel();
        panel.setBounds((screenSize.width - 400) / 2, 200, 400, 350);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);

        JLabel cnicLabel = new JLabel("CNIC:");
        cnicLabel.setBounds(50, 40, 300, 30);
        cnicLabel.setFont(new Font("Serif", Font.BOLD, 18));
        cnicLabel.setForeground(Color.decode("#660033"));

        JTextField input1 = new JTextField();
        input1.setBounds(50, 80, 300, 30);
        InputHinter.addHint(input1, "Enter 13 digit CNIC");

        JLabel label2 = new JLabel("Flight Id:");
        label2.setBounds(50, 120, 300, 30);
        label2.setFont(new Font("Serif", Font.BOLD, 18));
        label2.setForeground(Color.decode("#660033"));

        JTextField input2 = new JTextField();
        input2.setBounds(50, 160, 300, 30);
        InputHinter.addHint(input2, "Enter Flight Id:");

        JButton button = new JButton("Get TicketNo");
        button.setBounds(100, 220, 200, 40);
        button.setBackground(Color.decode("#660033"));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 18));
        button.setFocusable(false);

        JButton backButton = new JButton("Back to Home");
        backButton.setBounds(35, 675, 120, 30);
        backButton.setFocusable(false);
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.decode("#660033"));
        frame.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Menu();
            }
        });

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setBackground(Color.lightGray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setBackground(Color.WHITE);
            }
        });

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input1Text = input1.getText();
                long cnic;
                try {
                    cnic = Long.parseLong(input1Text);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Kindly Enter a valid CNIC", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean b = Validators.validateCNIC(input1Text);
                if (!b) {
                    JOptionPane.showMessageDialog(frame, "CNIC must be 13 digits", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String flightText = input2.getText();
                int flightID;
                try {
                    flightID = Integer.parseInt(flightText);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(frame, "Enter a valid flight ID", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try (Connection connection = DriverManager.getConnection(Database.url, Database.user, Database.password)) {
                    connection.setAutoCommit(false);

                    Statement statement = connection.createStatement();
                    String query = "SELECT * FROM bookings WHERE cnic = " + cnic + " AND flightId = " + flightID;
                    ResultSet resultSet = statement.executeQuery(query);
                    if (resultSet.next()) {
                        int ticketNo = resultSet.getInt("ticketNo");
                        JOptionPane.showMessageDialog(frame, "Your Ticket No is " + ticketNo + " of flight no " + flightID, "Success", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                        new Menu();
                    } else {
                        JOptionPane.showMessageDialog(frame, "No ticket found for the given CNIC and flight ID", "Error", JOptionPane.ERROR_MESSAGE);
                        input1.setText("");
                        input2.setText("");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(input2);
        panel.add(label2);
        panel.add(cnicLabel);
        panel.add(input1);
        panel.add(button);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.add(titleLabel);
        frame.add(panel);
        frame.setVisible(true);
    }
}
