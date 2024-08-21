import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login {

    Login() {
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

        InputHinter inputHinter = new InputHinter();

        JLabel loginTitle = new JLabel("Login", SwingConstants.CENTER);
        loginTitle.setBounds(0, 10, 300, 40);
        loginTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        loginTitle.setForeground(Color.decode("#660033"));

        JLabel Label1 = new JLabel("Email");
        Label1.setBounds(50, 60, 200, 30);
        Label1.setForeground(Color.decode("#660033"));

        JTextField Input1 = new JTextField();
        Input1.setBounds(50, 100, 200, 30);
        inputHinter.addHint(Input1,"ex. abc@gmail.com");

        JLabel Label2 = new JLabel("Password");
        Label2.setBounds(50, 140, 200, 30);
        Label2.setForeground(Color.decode("#660033"));

        JPasswordField Input2 = new JPasswordField();
        Input2.setBounds(50, 180, 200, 30);
        InputHinter.addHint(Input2,"12345678");

        JButton button1 = new JButton("Login");
        button1.setBounds(50, 240, 200, 30);
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

                    String email = Input1.getText();
                    String password = new String(Input2.getPassword());

                    boolean b1 = Validators.validatePassword(password);

                    if(b1){
                    }else {
                        JOptionPane.showMessageDialog(null,"Kindly Enter 8 character password!",null,JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String query = String.format("SELECT * FROM users WHERE email='" + email +"' AND password='"+password+"'");
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(null, "Login Successful!");
                        frame.dispose();
                        new Menu();
                    }else{
                        JOptionPane.showMessageDialog(null,"Error: email or password not correct!","AirConnect Airways",JOptionPane.ERROR_MESSAGE);
                        Input2.setText("");
                        Input1.setText("");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }

            }
        });

        JLabel signUpText = new JLabel("Sign Up", SwingConstants.CENTER);
        signUpText.setBounds(50, 400, 200, 30);
        signUpText.setForeground(Color.decode("#660033"));
        signUpText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        signUpText.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new SignUp();
            }
        });

        JLabel adminLabel = new JLabel("...");
        adminLabel.setBackground(Color.WHITE);
        adminLabel.setForeground(Color.WHITE);
        adminLabel.setBounds(1300,585,200,200);

        adminLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String guest = JOptionPane.showInputDialog(frame, "What is this?", "Are You Know?", JOptionPane.QUESTION_MESSAGE);
                if (guest != null && guest.equals("admin ki baat")) {
                    new AdminPanel();
                    frame.dispose();
                } else if (guest != null) {
                    JOptionPane.showMessageDialog(frame, "You are not authorized to access this panel", "Access Denied", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.add(adminLabel);
        panel.add(loginTitle);
        panel.add(Label1);
        panel.add(Input1);
        panel.add(Label2);
        panel.add(Input2);
        panel.add(button1);
        panel.add(signUpText);
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}