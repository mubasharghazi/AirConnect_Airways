import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class Notification {
    private JFrame frame;
    private JTextField flightIdField;
    private JTextField currentCityField;
    private JTextField destinationField;
    private JCheckBox delayedCheckBox;
    private JTextField delayTimeField;

    public Notification() {
        frame = new JFrame("AirConnect Airways - Flight Notification");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(Color.decode("#660033"));
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JLabel titleLabel = new JLabel("Flight Notification", SwingConstants.CENTER);
        titleLabel.setBounds(0, 20, screenSize.width, 40);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        frame.add(titleLabel);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setBounds((screenSize.width - 800) / 2, 100, 800, 400);

        JLabel flightIdLabel = new JLabel("Flight ID:");
        flightIdLabel.setBounds(50, 50, 200, 30);
        flightIdLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        flightIdLabel.setForeground(Color.decode("#660033"));

        flightIdField = new JTextField();
        flightIdField.setBounds(250, 50, 200, 30);
        flightIdField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        InputHinter.addHint(flightIdField,"Flight Id like 12,234");

        JLabel currentCityLabel = new JLabel("Departure City:");
        currentCityLabel.setBounds(50, 100, 200, 30);
        currentCityLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        currentCityLabel.setForeground(Color.decode("#660033"));

        currentCityField = new JTextField();
        currentCityField.setBounds(250, 100, 200, 30);
        currentCityField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        InputHinter.addHint(currentCityField,"like Lahroe, Sahiwal, Multan");

        JLabel destinationLabel = new JLabel("Destination:");
        destinationLabel.setBounds(50, 150, 200, 30);
        destinationLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        destinationLabel.setForeground(Color.decode("#660033"));

        destinationField = new JTextField();
        destinationField.setBounds(250, 150, 200, 30);
        destinationField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        InputHinter.addHint(destinationField,"like Lahroe, Sahiwal, Multan");

        JLabel delayedLabel = new JLabel("Delayed:");
        delayedLabel.setBounds(50, 200, 200, 30);
        delayedLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        delayedLabel.setForeground(Color.decode("#660033"));

        delayedCheckBox = new JCheckBox();
        delayedCheckBox.setBounds(250, 200, 30, 30);
        delayedCheckBox.setBackground(Color.WHITE);

        JLabel delayTimeLabel = new JLabel("Delay Time (minutes):");
        delayTimeLabel.setBounds(50, 250, 200, 30);
        delayTimeLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        delayTimeLabel.setForeground(Color.decode("#660033"));

        delayTimeField = new JTextField();
        delayTimeField.setBounds(250, 250, 200, 30);
        delayTimeField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        delayTimeField.setEnabled(false);

        JButton backButton = new JButton("Back to Home");
        backButton.setBounds(30,660,150,30);
        backButton.setFocusable(false);
        backButton.setBackground(Color.decode("#660033"));
        backButton.setForeground(Color.WHITE);
        frame.add(backButton);

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setBackground(Color.decode("#99004d"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setBackground(Color.decode("#660033"));
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminDashBoard();
            }
        });


        delayedCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delayTimeField.setEnabled(delayedCheckBox.isSelected());
                if (!delayedCheckBox.isSelected()) {
                    delayTimeField.setText("");
                }
            }
        });

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(250, 300, 200, 40);
        submitButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        submitButton.setBackground(Color.decode("#99004d"));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusable(false);
        submitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitButton.setBackground(Color.decode("#cc0066"));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitButton.setBackground(Color.decode("#99004d"));
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });

        panel.add(flightIdLabel);
        panel.add(flightIdField);
        panel.add(currentCityLabel);
        panel.add(currentCityField);
        panel.add(destinationLabel);
        panel.add(destinationField);
        panel.add(delayedLabel);
        panel.add(delayedCheckBox);
        panel.add(delayTimeLabel);
        panel.add(delayTimeField);
        panel.add(submitButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void handleSubmit() {
        int flightId;
        try {
            flightId = Integer.parseInt(flightIdField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid Flight ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String currentCity = currentCityField.getText();
        String destination = destinationField.getText();
        boolean delayed = delayedCheckBox.isSelected();
        int delayTime = 0;
        if (delayed) {
            try {
                delayTime = Integer.parseInt(delayTimeField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid delay time in minutes.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        try (Connection connection = DriverManager.getConnection(Database.url, Database.user, Database.password);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO notifications (flightId, current_city, destination, delay, delay_time) VALUES (?, ?, ?, ?, ?)")) {

            statement.setInt(1, flightId);
            statement.setString(2, currentCity);
            statement.setString(3, destination);
            statement.setBoolean(4, delayed);
            statement.setInt(5, delayTime);
            int rowAffected = statement.executeUpdate();
            if(rowAffected>0) {
                JOptionPane.showMessageDialog(frame, "Notification added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                int choice = JOptionPane.showConfirmDialog(frame,"Did you wants to add more notifications for users?",null,JOptionPane.YES_NO_OPTION);
                if(choice == 1){
                    frame.dispose();
                    new AdminDashBoard();
                }else{
                    flightIdField.setText("");
                    currentCityField.setText("");
                    destinationField.setText("");
                    delayTimeField.setText("");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error adding notification: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}