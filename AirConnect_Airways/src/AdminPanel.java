import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminPanel {
    public AdminPanel() {

        JFrame frame = new JFrame("AirConnect Airways --- Admin Panel");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setBackground(Color.WHITE);
        frame.getContentPane().setBackground(Color.WHITE);

        ImageIcon background = new ImageIcon("C:\\path\\to\\your\\background.jpg");
        JLabel image = new JLabel(background);
        image.setBounds(0, 0, screenSize.width, screenSize.height);

        // admin login text fields and labels
        JLabel adminLogin = new JLabel("Admin Login", SwingConstants.CENTER);
        adminLogin.setBounds(0, 120, screenSize.width, 40);
        adminLogin.setFont(new Font("SansSerif", Font.BOLD, 24));
        adminLogin.setForeground(Color.decode("#660033"));

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(600, 180, 200, 30);
        usernameLabel.setForeground(Color.decode("#660033"));

        JTextField usernameInput = new JTextField();
        usernameInput.setBounds(600, 220, 200, 30);
        usernameInput.setBackground(Color.decode("#f2f2f2")); // light grey

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(600, 250, 200, 30);
        passwordLabel.setForeground(Color.decode("#660033"));

        JPasswordField passwordInput = new JPasswordField();
        passwordInput.setBounds(600, 300, 200, 30);
        passwordInput.setBackground(Color.decode("#f2f2f2")); // light grey

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(620, 400, 150, 30);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.decode("#660033"));
        loginButton.setFocusable(false);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameInput.getText();
                String password = new String(passwordInput.getPassword());
                try(Connection connection = DriverManager.getConnection(Database.url,Database.user,Database.password)) {
                    String query = String.format("SELECT * FROM admin");
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    String user = null;
                    String Password = null;
                    if (resultSet.next()) {
                        user = resultSet.getString("username");
                        Password = resultSet.getString("password");
                    }

                    if(username.equals(user) && password.equals(Password)) {
                        JOptionPane.showMessageDialog(frame, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                        new AdminDashBoard();
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                        usernameInput.setText("");
                        passwordInput.setText("");
                    }
                }catch (Exception e1){
                    JOptionPane.showMessageDialog(null,"Error: "+e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
        }
        });

        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(Color.decode("#99004d"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(Color.decode("#660033"));
            }
        });

        frame.add(adminLogin);
        frame.add(usernameLabel);
        frame.add(usernameInput);
        frame.add(passwordLabel);
        frame.add(passwordInput);
        frame.add(loginButton);
        frame.add(image);
        frame.setVisible(true);
    }
}