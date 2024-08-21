import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class ViewBookings {
    public ViewBookings() {
        JFrame frame = new JFrame("AirConnect Airways - View Bookings");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setBackground(Color.decode("#660033"));
        frame.setLayout(null);

        JLabel label = new JLabel("Bookings", SwingConstants.CENTER);
        label.setBounds(0, 20, screenSize.width, 40);
        label.setFont(new Font("SansSerif", Font.BOLD, 24));
        label.setForeground(Color.decode("#660033"));

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
        for (int i = 0; i < tblData.getColumnCount(); i++) {
            tblData.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(tblData);
        scrollPane.setBounds(20, 100, screenSize.width - 40, screenSize.height - 200);
        frame.add(scrollPane);
        frame.add(label);

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

        try (Connection connection = DriverManager.getConnection(Database.url, Database.user, Database.password);
             Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM bookings";
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData rsmd = resultSet.getMetaData();

            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];
            for (int i = 0; i < cols; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);

            while (resultSet.next()) {
                int ticketNo = resultSet.getInt("ticketNo");
                String name = resultSet.getString("name");
                String nationality = resultSet.getString("nationality");
                String gender = resultSet.getString("gender");
                int flightId = resultSet.getInt("flightId");
                String flight_name = resultSet.getString("flight_name");
                String email = resultSet.getString("email");
                String cnic = resultSet.getString("cnic");
                String destination = resultSet.getString("destination");
                String date = resultSet.getString("date");
                String current_city = resultSet.getString("current_city");
                String address = resultSet.getString("address");
                model.addRow(new Object[]{ticketNo, cnic, email, name, gender, nationality, address, current_city, destination, flight_name, flightId, date});
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
}