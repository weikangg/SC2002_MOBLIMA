package utils;

public class PasswordStrengthChecker {
    
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
            System.out.println("Password Strength: Strong");
            return "Strong";
        }

        else if (hasLower && hasUpper && hasDigit && pwlen>=6){
            return "Medium";
        }

        return "Weak";

    }













}
