import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BoardingPass {
    BoardingPass () {
        JFrame frame = new JFrame("Boarding Pass");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(Color.decode("#660033"));
        frame.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(300, 100, 800, 550);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);

        JLabel Title = new JLabel("Boarding Pass", SwingConstants.CENTER);
        Title.setBounds(55, 10, 600, 40);
        Title.setFont(new Font("SansSerif", Font.BOLD, 24));
        Title.setForeground(Color.decode("#660033"));

        JLabel Label1 = new JLabel(" CNIC");
        Label1.setBounds(50, 60, 200, 30);
        Label1.setForeground(Color.decode("#660033"));

        JButton Button1 = new JButton ("Get Pass");
        Button1.setBounds(570, 100, 200, 30);
        Button1.setBackground(Color.decode("#660033"));
        Button1.setForeground(Color.WHITE);

        JTextField Input1 = new JTextField();
        Input1.setBounds(50, 100, 200, 30);


        JLabel ticketLabel = new JLabel("Ticket No");
        ticketLabel.setBounds(350,60,200,30);

        JTextField ticketInput = new JTextField();
        ticketInput.setBounds(350, 100, 200, 30);

        JLabel Label2 = new JLabel("Name");
        Label2.setBounds(50, 140, 200, 30);
        Label2.setForeground(Color.decode("#660033"));

        JLabel Label3 = new JLabel();
        Label3.setBounds(50, 180, 200, 30);

        JLabel Label4 = new JLabel("Arrival City");
        Label4.setBounds(50, 220, 200, 30);
        Label4.setForeground(Color.decode("#660033"));

        JLabel Label5 = new JLabel();
        Label5.setBounds(50, 260, 200, 30);

        JLabel Label6 = new JLabel("Nationality");
        Label6.setBounds(350, 140, 200, 30);
        Label6.setForeground(Color.decode("#660033"));

        JLabel Label7 = new JLabel();
        Label7.setBounds(350, 180, 200, 30);

        JLabel Label8 = new JLabel("Departure City");
        Label8.setBounds(350, 220, 200, 30);
        Label8.setForeground(Color.decode("#660033"));

        JLabel Label9 = new JLabel();
        Label9.setBounds(350, 260, 200, 30);

        JLabel Label10 = new JLabel ("Flight Name");
        Label10.setBounds(50, 300, 200, 30);
        Label10.setForeground(Color.decode("#660033"));

        JLabel Label11 = new JLabel();
        Label11.setBounds(50, 340, 200, 30);

        JLabel Label12 = new JLabel ("Flight ID");
        Label12.setBounds(350, 300, 200, 30);
        Label12.setForeground(Color.decode("#660033"));

        JLabel Label13 = new JLabel();
        Label13.setBounds(350, 340, 200, 30);

        JLabel label14 = new JLabel("Gender");
        label14.setBounds(50, 380, 200, 30);

        JLabel label15 = new JLabel();
        label15.setBounds(50,420,200,30);

        JLabel label16 = new JLabel("Flight Date");
        label16.setBounds(350, 380, 200, 30);

        JLabel label17 = new JLabel();
        label17.setBounds(350,420,200,30);

        JButton backButton = new JButton("Back to Home");
        backButton.setBounds(330,500,200,30);
        backButton.setFocusable(false);
        backButton.setBackground(Color.decode("#660033"));
        backButton.setForeground(Color.WHITE);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == backButton){
                    new Menu();
                    frame.dispose();
                }
            }
        });

        Button1.setFocusable(false);
        Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = Input1.getText();
                long cnic = 0;
                int ticketId = 0;
                try {
                    cnic = Long.parseLong(input);
                    String ticket = ticketInput.getText();
                    ticketId = Integer.parseInt(ticket);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Error: Kindly Enter valid CNIC or TicketID!", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Return early if input is invalid
                }

                boolean cnicChecker = Validators.validateCNIC(input);
                if(!cnicChecker){
                    JOptionPane.showMessageDialog(null,"Kindly enter 13 digit cnic!","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try (Connection connection = DriverManager.getConnection(Database.url, Database.user, Database.password)) {
                    String query = "SELECT * FROM bookings WHERE ticketNo = ? AND cnic = ?";
                    PreparedStatement statement = connection.prepareStatement(query);

                    statement.setInt(1, ticketId);
                    statement.setLong(2, cnic);

                    ResultSet resultSet = statement.executeQuery();
                    if(resultSet.next()){
                        Label3.setText(resultSet.getString("name"));
                        Label3.setFont(new Font("Roboto",Font.BOLD,15));
                        Label3.setForeground(Color.darkGray);
                        Label5.setText(resultSet.getString("destination"));
                        Label5.setFont(new Font("Roboto",Font.BOLD,15));
                        Label7.setText(resultSet.getString("nationality"));
                        Label7.setFont(new Font("Roboto",Font.BOLD,15));
                        Label9.setText(resultSet.getString("current_city"));
                        Label9.setFont(new Font("Roboto",Font.BOLD,15));
                        Label11.setText(resultSet.getString("flight_name"));
                        Label11.setFont(new Font("Roboto",Font.BOLD,15));
                        Label13.setText(resultSet.getString("flightId"));
                        Label13.setFont(new Font("Roboto",Font.BOLD,15));
                        label15.setText(resultSet.getString("gender"));
                        label15.setFont(new Font("Roboto",Font.BOLD,15));
                        label17.setText(resultSet.getString("date"));
                        label17.setFont(new Font("Roboto",Font.BOLD,15));

                    }else{
                        JOptionPane.showMessageDialog(null, "Record not found error!","No Found",JOptionPane.ERROR_MESSAGE);
                        Input1.setText("");
                        ticketInput.setText("");
                        Label3.setText("");
                        Label5.setText("");
                        Label7.setText("");
                        Label9.setText("");
                        Label11.setText("");
                        Label13.setText("");
                        label15.setText("");
                        label17.setText("");

                    }

                }catch (Exception exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        });

        panel.add(backButton);
        panel.add(label14);
        panel.add(label15);
        panel.add(label16);
        panel.add(label17);
        panel.add(ticketLabel);
        panel.add(ticketInput);
        panel.add(Title);
        panel.add(Label1);
        panel.add(Button1);
        panel.add(Input1);
        panel.add(Label2);
        panel.add(Label3);
        panel.add(Label4);
        panel.add(Label5);
        panel.add(Label6);
        panel.add(Label7);
        panel.add(Label8);
        panel.add(Label9);
        panel.add(Label10);
        panel.add(Label11);
        panel.add(Label12);
        panel.add(Label13);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
    }
}