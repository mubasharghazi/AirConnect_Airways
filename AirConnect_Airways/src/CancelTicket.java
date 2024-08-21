import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class CancelTicket {
    public CancelTicket() {
        JFrame frame = new JFrame("AirConnect Airways");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(Color.decode("#660033"));
        frame.setLayout(null);

        JLabel titleLabel = new JLabel("Cancel Ticket", SwingConstants.CENTER);
        titleLabel.setBounds(0, 80, screenSize.width, 40);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        JPanel panel = new JPanel();
        panel.setBounds((screenSize.width - 400) / 2, 200, 400, 300);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);

        JLabel ticketIdLabel = new JLabel("Ticket ID:");
        ticketIdLabel.setBounds(50, 50, 300, 30);
        ticketIdLabel.setFont(new Font("Serif", Font.BOLD, 18));
        ticketIdLabel.setForeground(Color.decode("#660033"));

        InputHinter obj = new InputHinter();

        JTextField ticketIdInput = new JTextField();
        ticketIdInput.setBounds(50, 90, 300, 30);
        obj.addHint(ticketIdInput, "Ticket Number(4 Digit) like. 1122,3454,5643");
        JButton cancelButton = new JButton("Cancel Ticket");
        cancelButton.setBounds(100, 150, 200, 40);
        cancelButton.setBackground(Color.decode("#660033"));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        cancelButton.setFocusable(false);


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

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ticketId = ticketIdInput.getText();
                int ticketNo;
                try {
                    ticketNo = Integer.parseInt(ticketId);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ticket ID must be a number", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (ticketId.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid Ticket ID", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try (Connection connection = DriverManager.getConnection(Database.url, Database.user, Database.password)) {
                        connection.setAutoCommit(false);
                        long cnic;
                        int flightId = 0;
                        try {
                            flightId = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter your Flight ID ?", "AirConnect Airways", JOptionPane.QUESTION_MESSAGE));
                            cnic = Long.parseLong(JOptionPane.showInputDialog(frame, "Enter your CNIC!\n if you wants to cancel your ticketNo = " + ticketNo, "Warning", JOptionPane.WARNING_MESSAGE));
                        } catch (Exception exception) {
                            JOptionPane.showMessageDialog(null, "Kindly Enter 13 digit CNIC!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        String query = "DELETE FROM bookings WHERE ticketNo = ? AND cnic = ? AND flightId = ?";

                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setInt(1, ticketNo);
                        preparedStatement.setLong(2, cnic);
                        preparedStatement.setInt(3, flightId);

                        int rowsAffected = preparedStatement.executeUpdate();
                        if (rowsAffected > 0) {
                            String query1 = "UPDATE flights set available_seats = available_seats + 1 WHERE flightId = ?";
                            PreparedStatement statement1 = connection.prepareStatement(query1);
                            statement1.setInt(1, flightId);

                            int rowsAffected1 = statement1.executeUpdate();
                            if (rowsAffected1 > 0) {
                                JOptionPane.showMessageDialog(frame, "Ticket Cancelled Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                            connection.commit();
                            frame.dispose();
                            new Menu();

                        } else {
                            JOptionPane.showMessageDialog(frame, "Ticket ID not found", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

        panel.add(ticketIdLabel);
        panel.add(ticketIdInput);
        panel.add(cancelButton);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.add(titleLabel);
        frame.add(panel);
        frame.setVisible(true);
    }
}