import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EditFlightsDetails {

    public EditFlightsDetails() {
        JFrame frame = new JFrame("AirConnect Airways --- Edit Flight Details");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(Color.decode("#660033"));
        frame.setLayout(null);

        InputHinter hinter = new InputHinter();

        JPanel panel = new JPanel();
        panel.setBounds((screenSize.width - 600) / 2, (screenSize.height - 500) / 2, 600, 500);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);

        JLabel Title = new JLabel("Edit Flight Details", SwingConstants.CENTER);
        Title.setBounds(0, 5, 600, 40);
        Title.setFont(new Font("SansSerif", Font.BOLD, 24));
        Title.setForeground(Color.decode("#660033"));

        JLabel flightCodeLabel = new JLabel("Flight No");
        flightCodeLabel.setBounds(50, 50, 200, 30);
        flightCodeLabel.setForeground(Color.decode("#660033"));

        JTextField flightCodeInput = new JTextField();
        flightCodeInput.setBounds(50, 80, 200, 30);
        hinter.addHint(flightCodeInput, "Enter flight Number from list of flight like 1");

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(260, 80, 100, 30);
        searchButton.setBackground(Color.decode("#660033"));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusable(false);

        JLabel flightNameLabel = new JLabel("Flight Name");
        flightNameLabel.setBounds(50, 130, 200, 30);
        flightNameLabel.setForeground(Color.decode("#660033"));

        JTextField flightNameInput = new JTextField();
        flightNameInput.setBounds(50, 160, 200, 30);
        hinter.addHint(flightNameInput, "Enter flight name");

        JLabel fromLabel = new JLabel("From");
        fromLabel.setBounds(50, 190, 200, 30);
        fromLabel.setForeground(Color.decode("#660033"));

        String[] from = {"Lahore", "Karachi", "Islamabad", "Peshawar", "Sahiwal", "Multan"};
        JComboBox<String> fromCombo = new JComboBox<>(from);
        fromCombo.setBounds(50, 220, 200, 30);
        fromCombo.setForeground(Color.decode("#660033"));
        fromCombo.setBackground(Color.WHITE);

        JLabel toLabel = new JLabel("Destination");
        toLabel.setBounds(310, 190, 200, 30);
        toLabel.setForeground(Color.decode("#660033"));

        String[] to = {"Lahore", "Karachi", "Islamabad", "Peshawar", "Sahiwal", "Multan"};
        JComboBox<String> toCombo = new JComboBox<>(to);
        toCombo.setBounds(310, 220, 200, 30);
        toCombo.setForeground(Color.decode("#660033"));
        toCombo.setBackground(Color.WHITE);

        JLabel departureLabel = new JLabel("Departure Time");
        departureLabel.setBounds(50, 250, 200, 30);
        departureLabel.setForeground(Color.decode("#660033"));

        String[] hours = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] minutes = {"0", "1", "2", "3", "4", "5", "6"};
        String[] min = {"0", "1", "2", "3", "4", "6", "7", "8", "9"};
        String[] timeShift = {"AM", "PM"};

        JComboBox<String> hoursJComboBox = new JComboBox<>(hours);
        JComboBox<String> minutesJComboBox = new JComboBox<>(minutes);
        JComboBox<String> minJComboBox = new JComboBox<>(min);
        JComboBox<String> shiftJComboBox = new JComboBox<>(timeShift);

        hoursJComboBox.setBounds(50, 300, 50, 30);
        hoursJComboBox.setBackground(Color.WHITE);
        minutesJComboBox.setBounds(100, 300, 50, 30);
        minutesJComboBox.setBackground(Color.WHITE);
        minJComboBox.setBounds(150, 300, 50, 30);
        minJComboBox.setBackground(Color.WHITE);
        shiftJComboBox.setBounds(200, 300, 50, 30);
        shiftJComboBox.setBackground(Color.WHITE);

        JLabel hoursLabel = new JLabel("Hour");
        hoursLabel.setBounds(50, 270,50, 30);
        JLabel minutesLabel = new JLabel("Minutes");
        minutesLabel.setBounds(110, 270,50, 30);

        panel.add(hoursLabel);
        panel.add(minutesLabel);
        panel.add(hoursJComboBox);
        panel.add(minutesJComboBox);
        panel.add(minJComboBox);
        panel.add(shiftJComboBox);

        JLabel arrivalLabel = new JLabel("Arrival Time");
        arrivalLabel.setBounds(310, 250, 200, 30);
        arrivalLabel.setForeground(Color.decode("#660033"));

        JComboBox<String> HoursJComboBox = new JComboBox<>(hours);
        JComboBox<String> MinutesJComboBox = new JComboBox<>(minutes);
        JComboBox<String> MinJComboBox = new JComboBox<>(min);
        JComboBox<String> ShiftJComboBox = new JComboBox<>(timeShift);

        HoursJComboBox.setBounds(310, 300, 50, 30);
        HoursJComboBox.setBackground(Color.WHITE);
        MinutesJComboBox.setBounds(360, 300, 50, 30);
        MinutesJComboBox.setBackground(Color.WHITE);
        MinJComboBox.setBounds(410, 300, 50, 30);
        MinJComboBox.setBackground(Color.WHITE);
        ShiftJComboBox.setBounds(460, 300, 50, 30);
        ShiftJComboBox.setBackground(Color.WHITE);

        JLabel HoursLabel = new JLabel("Hour");
        HoursLabel.setBounds(310, 270, 50, 30);
        JLabel MinutesLabel = new JLabel("Minutes");
        MinutesLabel.setBounds(370, 270, 50, 30);

        panel.add(HoursLabel);
        panel.add(MinutesLabel);
        panel.add(HoursJComboBox);
        panel.add(MinutesJComboBox);
        panel.add(MinJComboBox);
        panel.add(ShiftJComboBox);

        JLabel seatCapacityLabel = new JLabel("Seat Capacity");
        seatCapacityLabel.setBounds(50, 330, 200, 30);
        seatCapacityLabel.setForeground(Color.decode("#660033"));

        JTextField seatCapacityInput = new JTextField();
        seatCapacityInput.setBounds(50, 360, 200, 30);
        hinter.addHint(seatCapacityInput, "Enter seat capacity");

        JLabel dateLabel = new JLabel("Date");
        dateLabel.setBounds(310, 330, 200, 30);
        dateLabel.setForeground(Color.decode("#660033"));

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setBounds(310, 360, 200, 30);
        dateChooser.getDateEditor().setEnabled(false);

        JButton updateFlightButton = new JButton("Update Flight");
        updateFlightButton.setBounds(200, 420, 200, 30);
        updateFlightButton.setBackground(Color.decode("#660033"));
        updateFlightButton.setForeground(Color.WHITE);
        updateFlightButton.setFocusable(false);

        updateFlightButton.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                updateFlightButton.setBackground(Color.decode("#99004d"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                updateFlightButton.setBackground(Color.decode("#660033"));
            }
        });

        JButton backButton = new JButton("Back to Home");
        backButton.setBounds(screenSize.width - 200, screenSize.height - 100, 150, 30);
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

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String flightCode = flightCodeInput.getText();
                int flightNo;
                try {
                    flightNo = Integer.parseInt(flightCode);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Enter a valid Flight Number!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try (Connection connection = DriverManager.getConnection(Database.url, Database.user, Database.password)) {
                    String query = "SELECT * FROM flights WHERE Fid = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, flightNo);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        flightNameInput.setText(resultSet.getString("flight_name"));
                        fromCombo.setSelectedItem(resultSet.getString("current_city"));
                        toCombo.setSelectedItem(resultSet.getString("destination"));

                        String departureTime = resultSet.getString("departure_time");
                        String[] departureParts = departureTime.split("[: ]");
                        hoursJComboBox.setSelectedItem(departureParts[0]);
                        minutesJComboBox.setSelectedItem(departureParts[1].substring(0, 1));
                        minJComboBox.setSelectedItem(departureParts[1].substring(1, 2));
                        shiftJComboBox.setSelectedItem(departureParts[2]);

                        String arrivalTime = resultSet.getString("arrival_time");
                        String[] arrivalParts = arrivalTime.split("[: ]");
                        HoursJComboBox.setSelectedItem(arrivalParts[0]);
                        MinutesJComboBox.setSelectedItem(arrivalParts[1].substring(0, 1));
                        MinJComboBox.setSelectedItem(arrivalParts[1].substring(1, 2));
                        ShiftJComboBox.setSelectedItem(arrivalParts[2]);

                        seatCapacityInput.setText(String.valueOf(resultSet.getInt("available_seats")));

                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("date"));
                        dateChooser.setDate(date);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Flight not found", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(frame, "Error:" + exception.getMessage());
                }
            }
        });

        updateFlightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try (Connection connection = DriverManager.getConnection(Database.url, Database.user, Database.password)) {
                    connection.setAutoCommit(false);

                    String flightName = flightNameInput.getText();
                    String flightCode = flightCodeInput.getText();

                    boolean nameChecker = Validators.validateName(flightName);
                    if (!nameChecker) {
                        JOptionPane.showMessageDialog(frame, "Enter a valid flight Name!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int flightNo;
                    try {
                        flightNo = Integer.parseInt(flightCode);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Enter a Valid ID!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String from = fromCombo.getSelectedItem().toString();
                    String to = toCombo.getSelectedItem().toString();

                    String hour1 = hoursJComboBox.getSelectedItem().toString();
                    String minutes1 = minutesJComboBox.getSelectedItem().toString();
                    String min1 = minJComboBox.getSelectedItem().toString();
                    String timeShift = shiftJComboBox.getSelectedItem().toString();
                    String departure_time = hour1 + ":" + minutes1 + min1 + " " + timeShift;

                    String hour2 = HoursJComboBox.getSelectedItem().toString();
                    String minutes2 = MinutesJComboBox.getSelectedItem().toString();
                    String min2 = MinJComboBox.getSelectedItem().toString();
                    String timeShift1 = ShiftJComboBox.getSelectedItem().toString();
                    String arrival_time = hour2 + ":" + minutes2 + min2 + " " + timeShift1;

                    String seatCapacity = seatCapacityInput.getText();
                    int seat_capacity;
                    try {
                        seat_capacity = Integer.parseInt(seatCapacity);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Please enter a valid seat capacity", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Date selectedDate = dateChooser.getDate();
                    String formattedDate = null;
                    if (selectedDate != null) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        formattedDate = dateFormat.format(selectedDate);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error: Date not Selected properly!", "Logic Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String query = "UPDATE flights SET flight_name=?, current_city=?, destination=?, departure_time=?, arrival_time=?, date=?, available_seats=? WHERE Fid=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, flightName);
                    preparedStatement.setString(2, from);
                    preparedStatement.setString(3, to);
                    preparedStatement.setString(4, departure_time);
                    preparedStatement.setString(5, arrival_time);
                    preparedStatement.setString(6, formattedDate);
                    preparedStatement.setInt(7, seat_capacity);
                    preparedStatement.setInt(8, flightNo);

                    int rowAffected = preparedStatement.executeUpdate();
                    System.out.println(rowAffected);
                    if (rowAffected > 0) {
                        connection.commit();
                        JOptionPane.showMessageDialog(frame, "Flight details updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        int choice = JOptionPane.showConfirmDialog(frame, "Do you want to add another flight?", "Add Another Flight", JOptionPane.YES_NO_OPTION);
                        if(choice == JOptionPane.YES_OPTION) {
                            flightNameInput.setText("");
                            flightCodeInput.setText("");
                            seatCapacityInput.setText("");
                        }else{
                            frame.dispose();
                            new AdminDashBoard();
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to update flight details", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(frame, "Error:" + exception.getMessage());
                }
            }
        });

        panel.add(Title);
        panel.add(flightCodeLabel);
        panel.add(flightCodeInput);
        panel.add(searchButton);
        panel.add(flightNameLabel);
        panel.add(flightNameInput);
        panel.add(fromLabel);
        panel.add(fromCombo);
        panel.add(dateLabel);
        panel.add(dateChooser);
        panel.add(toLabel);
        panel.add(toCombo);
        panel.add(departureLabel);
        panel.add(arrivalLabel);
        panel.add(updateFlightButton);
        panel.add(seatCapacityLabel);
        panel.add(seatCapacityInput);
        frame.add(panel);
        frame.setVisible(true);
    }
}
