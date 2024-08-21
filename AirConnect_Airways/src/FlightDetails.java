import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class FlightDetails {

    public FlightDetails() {

        JFrame frame = new JFrame("AirConnect Airways --- Flights Details");
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

        JLabel Title = new JLabel("Flight Details", SwingConstants.CENTER);
        Title.setBounds(0, 5, 600, 40);
        Title.setFont(new Font("SansSerif", Font.BOLD, 24));
        Title.setForeground(Color.decode("#660033"));

        JLabel flightNameLabel = new JLabel("Flight Name");
        flightNameLabel.setBounds(50, 50, 200, 30);
        flightNameLabel.setForeground(Color.decode("#660033"));

        JTextField flightNameInput = new JTextField();
        flightNameInput.setBounds(50, 80, 200, 30);
        hinter.addHint(flightNameInput, "Enter flight name");

        JLabel flightCodeLabel = new JLabel("Flight ID");
        flightCodeLabel.setBounds(310, 50, 200, 30);
        flightCodeLabel.setForeground(Color.decode("#660033"));

        JTextField flightCodeInput = new JTextField();
        flightCodeInput.setBounds(310, 80, 200, 30);
        hinter.addHint(flightCodeInput, "Enter flight code");

        JLabel fromLabel = new JLabel("From");
        fromLabel.setBounds(50, 110, 200, 30);
        fromLabel.setForeground(Color.decode("#660033"));

        String[] from = {"Lahore", "Karachi", "Islamabad", "Peshawar", "Sahiwal","Multan"};
        JComboBox<String> fromCombo = new JComboBox<>(from);
        fromCombo.setBounds(50, 150, 200, 30);
        fromCombo.setForeground(Color.decode("#660033"));
        fromCombo.setBackground(Color.WHITE);

        JLabel toLabel = new JLabel("Destination");
        toLabel.setBounds(310, 110, 200, 30);
        toLabel.setForeground(Color.decode("#660033"));

        String[] to = {"Lahore", "Karachi", "Islamabad", "Peshawar", "Sahiwal","Multan"};
        JComboBox<String> toCombo = new JComboBox<>(to);
        toCombo.setBounds(310, 150, 200, 30);
        toCombo.setForeground(Color.decode("#660033"));
        toCombo.setBackground(Color.WHITE);

        JLabel departureLabel = new JLabel("Departure Time");
        departureLabel.setBounds(50, 200, 200, 30);
        departureLabel.setForeground(Color.decode("#660033"));

        // making a time setter for departure time
        String[] hours = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        String[] mintues = {"0","1","2","3","4","5","6"};
        String[] min = {"0","1","2","3","4","6","7","8","9"};
        String[] time_shift = {"AM","PM"};

        JComboBox<String> hoursJComboBox = new JComboBox<>(hours);
        JComboBox<String> minutesJComboBox = new JComboBox<>(mintues);
        JComboBox<String> minJComboBox = new JComboBox<>(min);
        JComboBox<String> shiftJComboBox = new JComboBox<>(time_shift);

        hoursJComboBox.setBounds(50,250,50,30);
        hoursJComboBox.setBackground(Color.WHITE);
        minutesJComboBox.setBounds(100,250,50,30);
        minutesJComboBox.setBackground(Color.WHITE);
        minJComboBox.setBounds(150,250,50,30);
        minJComboBox.setBackground(Color.WHITE);
        shiftJComboBox.setBounds(200,250,50,30);
        shiftJComboBox.setBackground(Color.WHITE);

        // labels
        JLabel hoursLabel = new JLabel("Hour");
        hoursLabel.setBounds(50,225,50,30);
        JLabel minutesLabel = new JLabel("Minutes");
        minutesLabel.setBounds(110,225,50,30);

        panel.add(hoursLabel);
        panel.add(minutesLabel);
        panel.add(hoursJComboBox);
        panel.add(minutesJComboBox);
        panel.add(minJComboBox);
        panel.add(shiftJComboBox);

        JLabel arrivalLabel = new JLabel("Arrival Time");
        arrivalLabel.setBounds(310, 200, 200, 30);
        arrivalLabel.setForeground(Color.decode("#660033"));

        // making a time setter for arrival time
        String[] Hours = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        String[] Mintues = {"0","1","2","3","4","5","6"};
        String[] Min = {"0","1","2","3","4","6","7","8","9"};
        String[] Time_shift = {"AM","PM"};

        JComboBox<String> HoursJComboBox = new JComboBox<>(Hours);
        JComboBox<String> MinutesJComboBox = new JComboBox<>(Mintues);
        JComboBox<String> MinJComboBox = new JComboBox<>(Min);
        JComboBox<String> ShiftJComboBox = new JComboBox<>(Time_shift);

        HoursJComboBox.setBounds(310,250,50,30);
        HoursJComboBox.setBackground(Color.WHITE);
        MinutesJComboBox.setBounds(360,250,50,30);
        MinutesJComboBox.setBackground(Color.WHITE);
        MinJComboBox.setBounds(410,250,50,30);
        MinJComboBox.setBackground(Color.WHITE);
        ShiftJComboBox.setBounds(460,250,50,30);
        ShiftJComboBox.setBackground(Color.WHITE);

        JLabel HoursLabel = new JLabel("Hour");
        HoursLabel.setBounds(310,225,50,30);
        JLabel MinutesLabel = new JLabel("Minutes");
        MinutesLabel.setBounds(370,225,50,30);

        panel.add(HoursLabel);
        panel.add(MinutesLabel);
        panel.add(HoursJComboBox);
        panel.add(MinutesJComboBox);
        panel.add(MinJComboBox);
        panel.add(ShiftJComboBox);


        JLabel seatCapacityLabel = new JLabel("Seat Capacity");
        seatCapacityLabel.setBounds(50, 290, 200, 30);
        seatCapacityLabel.setForeground(Color.decode("#660033"));

        JTextField seatCapacityInput = new JTextField();
        seatCapacityInput.setBounds(50, 330, 200, 30);
        hinter.addHint(seatCapacityInput, "Enter seat capacity");

        JLabel dateLabel = new JLabel("Date");
        dateLabel.setBounds(310, 290, 200, 30);

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setBounds(310, 330, 200, 30);
        dateChooser.getDateEditor().setEnabled(false);



        JButton addFlightButton = new JButton("Add Flight");
        addFlightButton.setBounds(200, 420, 200, 30);
        addFlightButton.setBackground(Color.decode("#660033"));
        addFlightButton.setForeground(Color.WHITE);
        addFlightButton.setFocusable(false);

        addFlightButton.addMouseListener(new MouseListener() {

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
                addFlightButton.setBackground(Color.decode("#99004d"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addFlightButton.setBackground(Color.decode("#660033"));
            }
        });

        JButton backButton = new JButton("Back to Home");
        backButton.setBounds(screenSize.width-200,screenSize.height-100,150,30);
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

        addFlightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try(Connection connection = DriverManager.getConnection(Database.url,Database.user,Database.password)) {
                    connection.setAutoCommit(false);

                    String flightName = flightNameInput.getText();
                    String flightCode = flightCodeInput.getText();

                    boolean nameChecker = Validators.validateName(flightName);
                    if(!nameChecker){
                        JOptionPane.showMessageDialog(frame,"Enter a valid flight Name!","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int flightID;
                    try{
                        flightID = Integer.parseInt(flightCode);
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(frame,"Enter a Valid ID!","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String from = fromCombo.getSelectedItem().toString();
                    String to = toCombo.getSelectedItem().toString();

                    // storing time into a one variable (proper time)
                    String hour1 = hoursJComboBox.getSelectedItem().toString();
                    String minutes1 = minutesJComboBox.getSelectedItem().toString();
                    String min1 = minJComboBox.getSelectedItem().toString();
                    String timeShift = shiftJComboBox.getSelectedItem().toString();
                    String departure_time = hour1 +":"+minutes1+ min1+"  "+timeShift;

                    String hour2 = HoursJComboBox.getSelectedItem().toString();
                    String minutes2 = MinutesJComboBox.getSelectedItem().toString();
                    String min2 = MinJComboBox.getSelectedItem().toString();
                    String timeShift1 = ShiftJComboBox.getSelectedItem().toString();
                    String arrival_time = hour2 + ":"+minutes2+min2+"  "+timeShift1;

                    String seatCapacity = seatCapacityInput.getText();
                    int seat_capacity = 0;
                    try {
                        seat_capacity = Integer.parseInt(seatCapacity);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Please enter a valid seat capacity", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String date = dateChooser.getDate().toString();

                    // Set the date format to "yyyy-MM-dd"
                    dateChooser.setDateFormatString("yyyy-MM-dd");

                    // Simulate selecting a date (for demonstration purposes)
                    dateChooser.setDate(new Date());

                    // Get the selected date from the JDateChooser
                    Date selectedDate = dateChooser.getDate();

                    String formattedDate = null;

                    // Check if a date was selected
                    if (selectedDate != null) {
                        // Create a SimpleDateFormat instance with the desired format
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        // Format the selected date
                        formattedDate = dateFormat.format(selectedDate);

                        // Print the formatted date
                        System.out.println("Formatted Date: " + formattedDate);

                    } else {
                        JOptionPane.showMessageDialog(frame,"Error: Date not Selected properly!","Logic Error",JOptionPane.ERROR_MESSAGE);
                    }

                    String query = "INSERT INTO flights(flight_name,flightID,current_city,destination,departure_time,arrival_time,date,available_seats) VALUES(?,?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, flightName);
                    preparedStatement.setInt(2, flightID);
                    preparedStatement.setString(3, from);
                    preparedStatement.setString(4, to);
                    preparedStatement.setString(5, departure_time);
                    preparedStatement.setString(6, arrival_time);
                    preparedStatement.setString(7,formattedDate);
                    preparedStatement.setInt(8,seat_capacity);

                    int rowAffected = preparedStatement.executeUpdate();
                    System.out.println(rowAffected);
                    if(rowAffected > 0) {
                        connection.commit();
                        JOptionPane.showMessageDialog(frame, "Flight details saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        int choice = JOptionPane.showConfirmDialog(frame, "Do you want to add another flight?", "Add Another Flight", JOptionPane.YES_NO_OPTION);
                        if(choice == JOptionPane.YES_OPTION) {
                            flightNameInput.setText("");
                            flightCodeInput.setText("");
                            fromCombo.setSelectedIndex(0);
                            toCombo.setSelectedIndex(0);
                            hoursJComboBox.setSelectedIndex(0);
                            HoursJComboBox.setSelectedIndex(0);
                            minutesJComboBox.setSelectedIndex(0);
                            MinutesJComboBox.setSelectedIndex(0);
                            minJComboBox.setSelectedIndex(0);
                            MinJComboBox.setSelectedIndex(0);
                            seatCapacityInput.setText("");
                        } else {
                            frame.dispose();
                            new AdminDashBoard();
                        }

                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to save flight details", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }catch (Exception exception){
                    JOptionPane.showMessageDialog(frame,"Error:" + exception.getMessage());
                }
            }
        });

        panel.add(Title);
        panel.add(flightNameLabel);
        panel.add(flightNameInput);
        panel.add(flightCodeLabel);
        panel.add(flightCodeInput);
        panel.add(fromLabel);
        panel.add(fromCombo);
        panel.add(dateLabel);
        panel.add(dateChooser);
        panel.add(toLabel);
        panel.add(toCombo);
        panel.add(departureLabel);
        panel.add(arrivalLabel);
        panel.add(addFlightButton);
        panel.add(seatCapacityLabel);
        panel.add(seatCapacityInput);
        frame.add(panel);
        frame.setVisible(true);
    }
}
