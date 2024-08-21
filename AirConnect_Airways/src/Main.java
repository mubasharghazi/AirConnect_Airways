import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Welcome obj = new Welcome();
            Thread.sleep(3000);
            obj.disposeFrame();
            new Login();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error: " + e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}