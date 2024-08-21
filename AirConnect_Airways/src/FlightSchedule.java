import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class FlightSchedule {
    public FlightSchedule() {
        JFrame frame = new JFrame("AirConnect Airways");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setBackground(Color.decode("#660033"));
        frame.setLayout(null);

        JLabel label = new JLabel("Flight Schedule", SwingConstants.CENTER);
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
        tblData.setBackground(Color.WHITE); // Dark purple
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
                new Menu();
            }
        });

        try (Connection connection = DriverManager.getConnection(Database.url, Database.user, Database.password);
             Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM flights";
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData rsmd = resultSet.getMetaData();

            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];
            for (int i = 0; i < cols; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);

            while (resultSet.next()) {
                int Fid = resultSet.getInt("Fid");
                String Flight_Name = resultSet.getString("flight_name");
                int Flight_ID = resultSet.getInt("flightID");
                String Departure_City = resultSet.getString("current_city");
                String destination = resultSet.getString("destination");
                String Departure_Time = resultSet.getString("departure_time");
                String Arrival_Time = resultSet.getString("arrival_time");
                String date = resultSet.getString("date");
                int remaining_seats = resultSet.getInt("available_seats");
                model.addRow(new Object[]{Fid, Flight_Name, Flight_ID, Departure_City, destination, Departure_Time, Arrival_Time,date, remaining_seats});
            }

            // Set cell renderer to center-align data after adding rows
            for (int i = 0; i < tblData.getColumnCount(); i++) {
                tblData.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
//                tblData.getColumnModel().getColumn(i).setResizable(true);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        frame.setVisible(true);
    }
}
