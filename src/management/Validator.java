package management;

public class Validator {
    public static boolean isValidEmail(String email) {

        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public static boolean isValidPassword(String password) {

        return password.length() >= 8 && password.matches(".*\\d.*") && password.matches(".*[a-zA-Z].*");
    }

    public static boolean isValidInteger(String str) {

        return str.matches("\\d+");
    }

    public static boolean isValidDouble(String priceInput) {
        return priceInput.matches("\\d+\\.\\d+");
    }
}
