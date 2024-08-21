import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class UserNotification {
    public UserNotification() {
        JFrame frame = new JFrame("AirConnect Airways - Notifications");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(Color.decode("#660033"));
        frame.setLayout(null);

        JLabel label = new JLabel("Flight Notifications", SwingConstants.CENTER);
        label.setBounds(0, 20, screenSize.width, 40);
        label.setFont(new Font("SansSerif", Font.BOLD, 24));
        label.setForeground(Color.WHITE);

        // Create a table model with non-editable cells
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tblData = new JTable(model);
        tblData.setRowHeight(30);
        tblData.setFont(new Font("Serif", Font.PLAIN, 16));
        tblData.setBackground(Color.WHITE);
        tblData.setForeground(Color.decode("#660033"));

        // Set cell renderer to center-align data
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JScrollPane scrollPane = new JScrollPane(tblData);
        scrollPane.setBounds(10, 20, 1300, 500);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setBounds(20, 100, screenSize.width - 40, screenSize.height - 210);
        panel.add(scrollPane);

        frame.add(label);
        frame.add(panel);

        JButton backButton = new JButton("Back to Home");
        backButton.setBounds(screenSize.width-200,screenSize.height-100,150,30);
        backButton.setFocusable(false);
        backButton.setBackground(Color.decode("#660033"));
        backButton.setForeground(Color.WHITE);
        frame.add(backButton);
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(Color.decode("#99004d"));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(Color.decode("#660033"));
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            new Menu();
        });

        try (Connection connection = DriverManager.getConnection(Database.url, Database.user, Database.password);
             Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM notifications";
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData rsmd = resultSet.getMetaData();

            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];
            for (int i = 0; i < cols; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int flightId = resultSet.getInt("flightId");
                String currentCity = resultSet.getString("current_city");
                String destination = resultSet.getString("destination");
                boolean delayed = resultSet.getBoolean("delay");
                int delayTime = resultSet.getInt("delay_time");
                model.addRow(new Object[]{id, flightId, currentCity, destination, delayed, delayTime});
            }

            // Set cell renderer to center-align data after adding rows
            for (int i = 0; i < tblData.getColumnCount(); i++) {
                tblData.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new UserNotification();
    }
}
