import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SignUp {

    SignUp() {
        JFrame frame = new JFrame("AirConnect Airways");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(Color.decode("#660033"));
        frame.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds((screenSize.width - 300) / 2, (screenSize.height - 450) / 2, 300, 450);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);


        JLabel signUpTitle = new JLabel("Sign Up", SwingConstants.CENTER);
        signUpTitle.setBounds(0, 10, 300, 40);
        signUpTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        signUpTitle.setForeground(Color.decode("#660033"));

        JLabel Label1 = new JLabel("Name");
        Label1.setBounds(50, 60, 200, 30);
        Label1.setForeground(Color.decode("#660033"));

        JTextField Input1 = new JTextField();
        Input1.setBounds(50, 100, 200, 30);
        InputHinter.addHint(Input1,"Ex. Ghazi, Awaiz, Ali");

        JLabel Label2 = new JLabel("Email");
        Label2.setBounds(50, 140, 200, 30);
        Label2.setForeground(Color.decode("#660033"));

        JTextField Input2 = new JTextField();
        Input2.setBounds(50, 180, 200, 30);
        InputHinter.addHint(Input2,"Ex. abc@gmail.com");

        JLabel Label3 = new JLabel("Password");
        Label3.setBounds(50, 220, 200, 30);
        Label3.setForeground(Color.decode("#660033"));

        JPasswordField Input3 = new JPasswordField();
        Input3.setBounds(50, 260, 200, 30);
        InputHinter.addHint(Input3,"12345678");

        JLabel Label4 = new JLabel("Confirm Password");
        Label4.setBounds(50, 300, 200, 30);
        Label4.setForeground(Color.decode("#660033"));

        JPasswordField Input4 = new JPasswordField();
        Input4.setBounds(50, 340, 200, 30);
        InputHinter.addHint(Input4,"12345678");

        JButton button1 = new JButton("Sign Up");
        button1.setBounds(50, 380, 200, 30);
        button1.setBackground(Color.decode("#660033"));
        button1.setForeground(Color.WHITE);
        button1.setFocusable(false);

        button1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button1.setBackground(Color.decode("#99004d"));
            }

            public void mouseExited(MouseEvent evt) {
                button1.setBackground(Color.decode("#660033"));
            }
        });

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try(Connection connection = DriverManager.getConnection(Database.url,Database.user,Database.password)){
                    connection.setAutoCommit(false);

                    String name = Input1.getText();
                    boolean validateName = Validators.validateName(name);
                    if(!validateName){
                        JOptionPane.showMessageDialog(null,"Kindly Enter a Valid Name!",null,JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String email = Input2.getText();
                    String getPassword = Input3.getText();
                    String  confirmPassword = Input4.getText();


                    if(name.equals("") && email.equals("") && getPassword.equals("") && confirmPassword.equals("")){
                        JOptionPane.showMessageDialog(frame,"You have Not Register Yet!",null,JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    boolean b1 = Validators.validatePassword(getPassword);
                    boolean b2 = Validators.validatePassword(confirmPassword);

                    if(b1 && b2){
                    }else {
                        JOptionPane.showMessageDialog(null,"Kindly Enter 8 character password!",null,JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // insert user data into database
                    String query = "INSERT INTO users(name,email,password) VALUES(?,?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);

                    // check if password and confirm password are same
                    int RowAffected;
                    if(getPassword.equals(confirmPassword)) {

                        try {
                            preparedStatement.setString(1, name);
                            preparedStatement.setString(2, email);
                            preparedStatement.setString(3, getPassword);
                            RowAffected = preparedStatement.executeUpdate();
                        }catch (SQLException ex){
                            JOptionPane.showMessageDialog(null,"Error: Duplicate Email Address!");
                            return;
                        }

                        if(RowAffected>0){
                                connection.commit();
                                JOptionPane.showMessageDialog(frame, "You have register successfully!");
                                frame.dispose();
                                new UserInformation(name,email,getPassword);

                        }else{
                            JOptionPane.showMessageDialog(null,"Your data is not inserted!");
                            Input3.setSelectedTextColor(Color.RED);
                            Input4.setSelectedTextColor(Color.RED);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Kindly Enter Correct Password!");
                    }
                }catch (SQLException exception){
                    System.out.println(exception.getMessage());
                }
            }
        });

        JLabel loginText = new JLabel("Back to Login", SwingConstants.CENTER);
        loginText.setBounds(50, 420, 200, 30);
        loginText.setForeground(Color.decode("#660033"));
        loginText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        loginText.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new Login();
            }
        });

        panel.add(signUpTitle);
        panel.add(Label1);
        panel.add(Input1);
        panel.add(Label2);
        panel.add(Input2);
        panel.add(Label3);
        panel.add(Input3);
        panel.add(Label4);
        panel.add(Input4);
        panel.add(button1);
        panel.add(loginText);
        frame.add(panel);
        frame.setVisible(true);
    }
}