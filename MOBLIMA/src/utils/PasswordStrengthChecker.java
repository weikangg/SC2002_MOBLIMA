package utils;
/**
 * Util class that checks the strength of password entered during account creation
 * Ensures password strength is strong before creating the account
 * @author Aloysius
 * @version 3.0
 * @since 08-11-2022
 */
public class PasswordStrengthChecker {
    /**
     * Function to check the strength of password entered
     * @param pw password that needs to be evaluated
     * @return "Strong", "Medium" or "Weak" depending on criteria met for passwords
     */
    public static String passwordStrength(String pw){
        int pwlen = pw.length();
        boolean hasLower=false, hasUpper=false, hasDigit=false,hasSpecChar=false;
        char[] password = pw.toCharArray();
        for (int i=0;i<pwlen;i++){
            if (Character.isUpperCase(password[i])){
                hasUpper=true;
            }
            if (Character.isLowerCase(password[i])){
                hasLower=true;
            }
            if (Character.isDigit(password[i])){
                hasDigit=true;
            }
            if (password[i]=='$'||password[i]=='!'||password[i]=='#'||password[i]=='*'||password[i]=='%'||password[i]=='@'||password[i]=='^'){
                hasSpecChar=true;
            }
        }
        
        if (hasDigit && hasLower && hasUpper && hasSpecChar && (pwlen>=8)){
            return "Strong";
        }

        else if (hasLower && hasUpper && hasDigit && pwlen>=6){
            return "Medium";
        }

        return "Weak";

    }













}
