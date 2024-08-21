import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.channels.UnresolvedAddressException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserInformation {
        private JDateChooser dateChooser;
    UserInformation (String name, String email, String getPassword) {
        JFrame frame = new JFrame("AirConnect Airways");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setBackground(Color.decode("#660033"));
        frame.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds((screenSize.width - 600) / 2, (screenSize.height - 450) / 2, 600, 450);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);

        InputHinter inputHinter = new InputHinter();

        JLabel loginTitle = new JLabel("User Information", SwingConstants.CENTER);
        loginTitle.setBounds(0, 10, 600, 40);
        loginTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        loginTitle.setForeground(Color.decode("#660033"));

        JLabel Label1 = new JLabel("CNIC Number");
        Label1.setBounds(50, 60, 200, 30);
        Label1.setForeground(Color.decode("#660033"));

        JTextField Input1 = new JTextField();
        Input1.setBounds(50, 100, 200, 30);
        inputHinter.addHint(Input1,"like. 12345678910");

        JLabel Label2 = new JLabel("Mobile Number");
        Label2.setBounds(350, 60, 200, 30);
        Label2.setForeground(Color.decode("#660033"));

        JTextField Input2 = new JTextField();
        Input2.setBounds(350, 100, 200, 30);
        inputHinter.addHint(Input2,"03XXXXXXXXX");

        JLabel Label3 = new JLabel("Gender");
        Label3.setBounds(50, 140, 200, 30);
        Label3.setForeground(Color.decode("#660033"));

        JRadioButton radio1 =new JRadioButton("Male");
        radio1.setBounds(50,180,100,30);
        radio1.setBackground(Color.WHITE);
        radio1.setForeground(Color.decode("#660033"));
        JRadioButton radio2 =new JRadioButton("Female");
        radio2.setBounds(150,180,100,30);
        radio2.setBackground(Color.WHITE);
        radio2.setForeground(Color.decode("#660033"));

        ButtonGroup group =new ButtonGroup();
        group.add(radio1);group.add(radio2);

        JLabel Label4 = new JLabel("Date of Birth");
        Label4.setBounds(350, 140, 200, 30);
        Label4.setForeground(Color.decode("#660033"));

        dateChooser = new JDateChooser();
        dateChooser.setBounds(350, 180, 200, 30);
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.getDateEditor().setEnabled(false);
        dateChooser.getDateEditor().getUiComponent().setFocusable(false);


        JLabel Label5 = new JLabel("Nationality");
        Label5.setBounds(50, 220, 200, 30);
        Label5.setForeground(Color.decode("#660033"));

        JTextField Input3 = new JTextField();
        Input3.setBounds(50, 260, 200, 30);

        JLabel Label6 = new JLabel("Address");
        Label6.setBounds(350, 220, 200, 30);
        Label6.setForeground(Color.decode("#660033"));

        JTextField Input4 = new JTextField();
        Input4.setBounds(350, 260, 200, 30);

        JButton button1 = new JButton("Save");
        button1.setBounds(200, 340, 200, 30);
        button1.setBackground(Color.decode("#660033"));
        button1.setForeground(Color.WHITE);
        button1.setFocusable(false);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try(Connection connection = DriverManager.getConnection(Database.url,Database.user,Database.password)){
                    connection.setAutoCommit(false);

                    long cnic = 0;
                    String cnicStr = Input1.getText();
                    String phoneStr = Input2.getText();
                    long phone = 0;
                    try{
                        phone = Long.parseLong(phoneStr);
                    }catch (NumberFormatException ex){
                        JOptionPane.showMessageDialog(null,"Please Enter a Valid Phone Number!","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    try {
                        cnic = Long.parseLong(cnicStr);

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid CNIC number.");
                        return;
                    }

                    boolean b = Validators.validateCNIC(cnicStr);
                    if(!b){
                        JOptionPane.showMessageDialog(null,"Kindly enter 13 digit cnic number!",null,JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    boolean b2 = Validators.validatePhoneNo(phoneStr);
                    if(!b2){
                        JOptionPane.showMessageDialog(null,"Enter a 11 digit phone number!",null,JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String country = Input3.getText();
                    String address = Input4.getText();

                    radio1.setActionCommand("male");
                    radio2.setActionCommand("female");

                    boolean countryChecker =Validators.validateName(country);
                    if(!countryChecker){
                        JOptionPane.showMessageDialog(frame,"Kindly enter your nationality!");
                        return;
                    }
                    String gender = group.getSelection().getActionCommand();

                    // Logic to handle date selection and formatting
                    Date selectedDate = dateChooser.getDate();
                    String dob = null;

                    if (selectedDate != null) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        dob = dateFormat.format(selectedDate);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error: Date not selected properly!", "Logic Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String query = "INSERT INTO usersdata(name,email,password,cnic,phone_number,nationality,gender,address,dob) VALUES(?,?,?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1,name);
                    preparedStatement.setString(2,email);
                    preparedStatement.setString(3,getPassword);
                    preparedStatement.setLong(4,cnic);
                    preparedStatement.setLong(5,phone);
                    preparedStatement.setString(6,country);
                    preparedStatement.setString(7,gender);
                    preparedStatement.setString(8,address);
                    preparedStatement.setString(9,dob);

                    int RowAffected = preparedStatement.executeUpdate();
                    if(RowAffected>0){
                        JOptionPane.showMessageDialog(frame,"You have register successfully!");
                        connection.commit();
                        frame.dispose();
                        new Menu();
                    }else{
                        JOptionPane.showMessageDialog(null,"Your data is not inserted!");
                    }
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(frame,"Error: "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(loginTitle);
        panel.add(Label1);
        panel.add(Input1);
        panel.add(Label2);
        panel.add(Input2);
        panel.add(Label3);
        panel.add(Label4);
        panel.add(radio1);
        panel.add(dateChooser);
        panel.add(radio2);
        panel.add(Label5);
        panel.add(Input3);
        panel.add(Label6);
        panel.add(Input4);
        panel.add(button1);
        frame.add(panel);
        frame.setVisible(true);
    }
}
