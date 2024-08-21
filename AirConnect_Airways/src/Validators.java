public class Validators {

    public static boolean validateName(String name) {
        // Check if the name is not null, not empty and contains only letters and spaces
        return name != null && !name.isEmpty() && name.matches("[a-zA-Z\\s]+");
    }

    public static boolean validatePassword(String password) {
        // Check if the password is not null and has 8 characters
        return password != null && password.length() == 8;
    }

    public static boolean validateCNIC(String cnicNo){
        return cnicNo.length() == 13;
    }

    public static boolean validatePhoneNo(String number){
        return number.length() == 11;
    }

}
