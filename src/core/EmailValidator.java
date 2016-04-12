package core;

/**
 * Created by Garth Toland on 10/04/2016.
 * Description:
 */
public class EmailValidator {

    public boolean validateEmail(String emailIn) {
        if(emailIn.contains("~") || emailIn.contains("#") || emailIn.contains(";")
                || emailIn.contains(":") || emailIn.contains("'") || emailIn.contains(",")
                || emailIn.contains("|") || emailIn.contains("]")  || emailIn.contains("[")
                || emailIn.contains("}")  || emailIn.contains("{")  || emailIn.contains(")")
                || emailIn.contains("(")  || emailIn.contains("*")  || emailIn.contains("/")
                || emailIn.contains("+")  || emailIn.contains("<")  || emailIn.contains(">")) {
            return false;
        } else
            return true;
    }
}
