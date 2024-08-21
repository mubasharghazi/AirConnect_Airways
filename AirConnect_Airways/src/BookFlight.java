import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Random;

public class BookFlight {
    private final JDateChooser dateChooser;
    private DefaultTableModel model;
    private final JScrollPane scrollBar;

    public BookFlight() {
        JFrame frame = new JFrame("AirConnect Airways");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(Color.decode("#660033"));
        frame.setLayout(null);

        scrollBar = new JScrollPane();

        JPanel panel = new JPanel();
        panel.setBounds(100, 80, 1200, 700);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);

        JLabel title = new JLabel("Book Flight", SwingConstants.CENTER);
        title.setBounds(160, 5, 600, 40);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(Color.decode("#660033"));

        InputHinter inputHinter = new InputHinter();

        JLabel label1 = new JLabel("CNIC");
        label1.setBounds(50, 70, 200, 30);
        label1.setForeground(Color.decode("#660033"));

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(50, 130, 200, 30);
        emailLabel.setForeground(Color.decode("#660033"));

        JTextField emailInput = new JTextField();
        emailInput.setBounds(50, 160, 200, 30);
        inputHinter.addHint(emailInput, "Enter Your Email here");

        panel.add(emailInput);
        panel.add(emailLabel);

        JButton button1 = new JButton("Get Your Info");
        button1.setBounds(100, 50, 130, 30);
        button1.setBackground(Color.decode("#660033"));
        button1.setForeground(Color.WHITE);

        JTextField input1 = new JTextField();
        input1.setBounds(50, 103, 200, 30);
        inputHinter.addHint(input1, "Enter Your CNIC like 1234567891011");

        JLabel label2 = new JLabel("Name");
        label2.setBounds(50, 195, 200, 30);
        label2.setForeground(Color.decode("#660033"));

        JTextField input2 = new JTextField();
        input2.setBounds(50, 230, 200, 30);
        inputHinter.addHint(input2, "Enter Your Name like Ghazi, Awaiz");

        JLabel label4 = new JLabel("Gender");
        label4.setBounds(50, 260, 200, 30);
        label4.setForeground(Color.decode("#660033"));

        JRadioButton radio1 = new JRadioButton("Male");
        radio1.setBounds(50, 285, 100, 30);
        radio1.setBackground(Color.WHITE);
        radio1.setForeground(Color.decode("#660033"));
        JRadioButton radio2 = new JRadioButton("Female");
        radio2.setBounds(150, 285, 100, 30);
        radio2.setBackground(Color.WHITE);
        radio2.setForeground(Color.decode("#660033"));

        radio1.setActionCommand("male");
        radio2.setActionCommand("female");

        ButtonGroup group = new ButtonGroup();
        group.add(radio1);
        group.add(radio2);

        JLabel label6 = new JLabel("Nationality");
        label6.setBounds(50, 320, 200, 30);
        label6.setForeground(Color.decode("#660033"));

        JTextField input4 = new JTextField();
        input4.setBounds(50, 350, 200, 30);
        inputHinter.addHint(input4, "Enter Your Nationality like Pakistani");

        JLabel label8 = new JLabel("Address");
        label8.setBounds(50, 390, 200, 30);
        label8.setForeground(Color.decode("#660033"));

        JTextField input5 = new JTextField();
        input5.setBounds(50, 420, 200, 30);
        inputHinter.addHint(input5, "Enter Your Proper Address");

        JLabel label10 = new JLabel("From");
        label10.setBounds(310, 80, 200, 30);
        label10.setForeground(Color.decode("#660033"));

        JButton button2 = new JButton("Get Flight Info");
        button2.setBounds(385, 60, 130, 30);
        button2.setBackground(Color.decode("#660033"));
        button2.setForeground(Color.WHITE);

        String[] from = {"Lahore", "Karachi", "Islamabad", "Peshawar", "Sahiwal", "Multan"};

        JComboBox<String> combo1 = new JComboBox<>(from);
        combo1.setBounds(310, 120, 200, 30);
        combo1.setForeground(Color.decode("#660033"));
        combo1.setBackground(Color.WHITE);

        JLabel label11 = new JLabel("To");
        label11.setBounds(310, 160, 200, 30);
        label11.setForeground(Color.decode("#660033"));

        String[] to = {"Lahore", "Karachi", "Islamabad", "Peshawar", "Sahiwal", "Multan"};

        JComboBox<String> combo2 = new JComboBox<>(to);
        combo2.setBounds(310, 200, 200, 30);
        combo2.setForeground(Color.decode("#660033"));
        combo2.setBackground(Color.WHITE);

        JLabel label12 = new JLabel("Flight Name");
        label12.setBounds(310, 245, 200, 30);
        label12.setForeground(Color.decode("#660033"));

        JTextField input6 = new JTextField();
        input6.setBounds(310, 290, 200, 30);
        input6.setEditable(false);

        JLabel label14 = new JLabel("Flight ID");
        label14.setBounds(310, 320, 200, 30);
        label14.setForeground(Color.decode("#660033"));

        JTextField input7 = new JTextField();
        input7.setBounds(310, 350, 200, 30);
        input7.setEditable(false);
        JLabel label16 = new JLabel("Date");
        label16.setBounds(310, 380, 200, 30);
        label16.setForeground(Color.decode("#660033"));

        dateChooser = new JDateChooser();
        dateChooser.setBounds(310, 420, 200, 30);
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.getDateEditor().setEnabled(false);

        JButton button3 = new JButton("Book Now");
        button3.setBounds(300, 520, 200, 30);
        button3.setBackground(Color.decode("#660033"));
        button3.setForeground(Color.WHITE);

        JButton backButton = new JButton("Back to Home");
        backButton.setBounds(730,550,150,30);
        backButton.setFocusable(false);
        backButton.setBackground(Color.decode("#660033"));
        backButton.setForeground(Color.WHITE);
        panel.add(backButton);

        button1.setFocusable(false);
        button2.setFocusable(false);
        button3.setFocusable(false);

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button1.setBackground(Color.decode("#99004d"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button1.setBackground(Color.decode("#660033"));
            }
        });

        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button2.setBackground(Color.decode("#99004d"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button2.setBackground(Color.decode("#660033"));
            }
        });
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
                new Menu();
            }
        });

        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button3.setBackground(Color.decode("#99004d"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button3.setBackground(Color.decode("#660033"));
            }
        });

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try (Connection connection = DriverManager.getConnection(Database.url, Database.user, Database.password)) {
                    String input1Text = input1.getText();
                    long cnic;
                    try {
                        cnic = Long.parseLong(input1Text);
                        boolean a = Validators.validateCNIC(input1Text);
                        if(!a){
                            JOptionPane.showMessageDialog(null,"Kindly enter 13 digit CNIC!",null,JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid CNIC number.");
                        return;
                    }

                    String query1 = "SELECT * FROM usersdata WHERE cnic=?";
                    PreparedStatement statement = connection.prepareStatement(query1);
                    statement.setLong(1, cnic);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        input2.setText(resultSet.getString("name"));
                        String gender = resultSet.getString("gender");
                        if (gender.equals("male")) {
                            radio1.setSelected(true);
                        } else {
                            radio2.setSelected(true);
                        }
                        input4.setText(resultSet.getString("nationality"));
                        input5.setText(resultSet.getString("address"));
                        emailInput.setText(resultSet.getString("email"));
                        input2.setEditable(false);
                        input4.setEditable(false);
                        input5.setEditable(false);
                        emailInput.setEditable(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "No user found with this CNIC.","User Not Found Error",JOptionPane.ERROR_MESSAGE);
                        input2.setText("");
                        group.clearSelection();
                        emailInput.setText("");
                        input4.setText("");
                        input5.setText("");
                        input2.setEditable(true);
                        input2.setEditable(true);
                        emailInput.setEditable(true);
                        input4.setEditable(true);
                        input5.setEditable(true);

                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame,ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fromLocation = (String) combo1.getSelectedItem();
                String toLocation = (String) combo2.getSelectedItem();

                if (dateChooser == null || dateChooser.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "Please Select Flight Date!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateText = sdf.format(dateChooser.getDate());

                try (Connection connection = DriverManager.getConnection(Database.url, Database.user, Database.password)) {
                    String query2 = "SELECT * FROM flights WHERE current_city=? AND destination=? AND date=?";
                    PreparedStatement statement = connection.prepareStatement(query2);
                    statement.setString(1, fromLocation);
                    statement.setString(2, toLocation);
                    statement.setString(3,dateText);
                    ResultSet resultSet = statement.executeQuery();

                    model = new DefaultTableModel(new String[]{"FlightID", "Flight Name", "From", "To", "Departure Time", "Arrival Time" ,"Available Seats"}, 0){
                        @Override
                        public boolean isCellEditable(int row,int colum){
                            return false;
                        }
                    };
                    JTable table = new JTable(model);

                    while (resultSet.next()) {
                        String flightId = resultSet.getString("flightID");
                        String flightName = resultSet.getString("flight_name");
                        String from = resultSet.getString("current_city");
                        String to = resultSet.getString("destination");
                        String departure_time = resultSet.getString("departure_time");
                        String arrival_time = resultSet.getString("arrival_time");
                        String seats = resultSet.getString("available_seats");
                        model.addRow(new Object[]{flightId, flightName, from, to,departure_time,arrival_time,seats});
                    }

                    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                        @Override
                        public void valueChanged(ListSelectionEvent event) {
                            if (!event.getValueIsAdjusting()) {
                                int selectedRow = table.getSelectedRow();
                                if (selectedRow != -1) {
                                    input6.setText(table.getValueAt(selectedRow, 1).toString());
                                    input7.setText(table.getValueAt(selectedRow, 0).toString());
                                }
                            }
                        }
                    });

                    scrollBar.setViewportView(table);
                    scrollBar.setBounds(550, 70, 600, 450);
                    scrollBar.setBackground(Color.WHITE);
                    scrollBar.setVisible(true);
                    panel.add(scrollBar);

                } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());                }
            }
        });

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input1Text = input1.getText();
                long cnic;
                try {
                    cnic = Long.parseLong(input1Text);
                    boolean a = Validators.validateCNIC(input1Text);
                    if(!a){
                        JOptionPane.showMessageDialog(null,"Enter a 13 digit CNIC!",null,JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid CNIC number.",null,JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String emailText = emailInput.getText();
                String nameText = input2.getText();

                boolean nameChecker = Validators.validateName(nameText);
                if(!nameChecker){
                    JOptionPane.showMessageDialog(null,"Kindly Enter Proper Name!",null,JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String gender = group.getSelection().getActionCommand();
                String nationalityText = input4.getText();

                boolean nationalityChecker = Validators.validateName(nationalityText);
                if(!nationalityChecker){
                    JOptionPane.showMessageDialog(null,"Kindly Enter Proper Nationality!",null,JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String addressText = input5.getText();
                String fromLocation = (String) combo1.getSelectedItem();
                String toLocation = (String) combo2.getSelectedItem();
                String flightNameText = input6.getText();
                String flightIdText = input7.getText();

                if(flightIdText.equals("") || flightIdText.equals("")){
                    JOptionPane.showMessageDialog(null,"Please select a flight from available flights","Kindly",JOptionPane.ERROR_MESSAGE);
                    return;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateText = sdf.format(dateChooser.getDate());

                Random random = new Random();
                int ticketNo = 0;
                try {
                    ticketNo = random.nextInt(10000);
                }catch (Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),null,JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try (Connection connection = DriverManager.getConnection(Database.url, Database.user, Database.password)) {
                    connection.setAutoCommit(false);
                    String query3 = "INSERT INTO bookings (ticketNo, cnic, email, name, gender, nationality, address, current_city, destination, flight_name, flightId, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?)";
                    PreparedStatement statement = connection.prepareStatement(query3);
                    statement.setInt(1, ticketNo);
                    statement.setLong(2, cnic);
                    statement.setString(3, emailText);
                    statement.setString(4, nameText);
                    statement.setString(5, gender);
                    statement.setString(6, nationalityText);
                    statement.setString(7, addressText);
                    statement.setString(8, fromLocation);
                    statement.setString(9, toLocation);
                    statement.setString(10, flightNameText);
                    statement.setString(11, flightIdText);
                    statement.setString(12, dateText);

                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "Flight booked successfully!\n Your Ticket No. is "+ticketNo,null,JOptionPane.INFORMATION_MESSAGE);
                        try{
                            query3 = "UPDATE flights SET available_seats = available_seats - 1 WHERE flightId = ?";
                            PreparedStatement statement1 = connection.prepareStatement(query3);
                            int flightID = 0;
                            try{
                                flightID = Integer.parseInt(flightIdText);
                            }catch (Exception e2){
                                System.out.println(e2.getMessage());
                            }
                            statement1.setInt(1,flightID);

                            int rowsAffected = statement1.executeUpdate();
                            if(rowsAffected>0){
                                JOptionPane.showMessageDialog(null,"Thanks for Booking!");
                            }

                        }catch (Exception e1){
                            JOptionPane.showMessageDialog(null,"Error: " + e1.getMessage(),null,JOptionPane.ERROR_MESSAGE);
                        }


                        connection.commit();
                        int choice = JOptionPane.showConfirmDialog(null,"Did you want to Book more flights?",null,JOptionPane.YES_NO_OPTION);

                        if(choice == 1){
                            frame.dispose();
                            new Menu();
                        }else{
                            input1.setText("");
                            input1.setEditable(true);
                            emailInput.setText("");
                            emailInput.setEditable(true);
                            input2.setText("");
                            input2.setEditable(true);
                            input4.setText("");
                            input4.setEditable(true);
                            input5.setText("");
                            input5.setEditable(true);
                            input6.setText("");
                            input7.setText("");
                            group.clearSelection();
                        }
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame,"Error: "+ex.getMessage());
                }
            }
        });

        panel.add(title);
        panel.add(button1);
        panel.add(input1);
        panel.add(label1);
        panel.add(label2);
        panel.add(input2);
        panel.add(radio1);
        panel.add(radio2);
        panel.add(label4);
        panel.add(label6);
        panel.add(input4);
        panel.add(label8);
        panel.add(input5);
        panel.add(label10);
        panel.add(combo1);
        panel.add(label11);
        panel.add(combo2);
        panel.add(label12);
        panel.add(input6);
        panel.add(label14);
        panel.add(input7);
        panel.add(label16);
        panel.add(dateChooser);
        panel.add(button3);
        panel.add(button2);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
    }

}
