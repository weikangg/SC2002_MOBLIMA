// package view;
// import java.util.InputMismatchException;
// import java.util.Scanner;
// import java.io.File;

// /* 
//  * Main app to run to choose either Customer or Staff App
//  */

// public class mainApp {

//     public static void main(String[] args){

//         File directory = new File("SC2002_OOP\\MOBLIMA").getAbsoluteFile();
//         if (directory.exists()) System.setProperty("user.dir", directory.getAbsolutePath());
//         directory = new File("MOBLIMA").getAbsoluteFile();
//         if (directory.exists()) System.setProperty("user.dir", directory.getAbsolutePath());
        
//         // System.out.println(System.getProperty("user.dir"));
        
//         Scanner scan = new Scanner(System.in); //Scanner Object Instantiation
//         int input = -1; //User Input
        
//         System.out.println("======================= MOBLIMA APP =======================\n" + 
//                             "Please select an option:\n" +        
//                             "(1) Staff App           \n" +
//                             "(2) Customer App        \n" + 
//                             "(3) Exit \n " + 
//                             "===========================================================");
//         System.out.print("Enter your choice: ");


//         try { //Attempt User Input
//             input = scan.nextInt(); 
//             scan.nextLine();
//         } catch (InputMismatchException e) { //Catch bad inputs
//             scan.nextLine(); //Flush input
//             System.out.println("Please enter a valid selection (1-3) only.");
//         }

//         while(input < 1 || input > 3){ //Attempt User Input Again
            
//             System.out.println("Please enter a valid selection (1-3) only."); //Error Msg
//             System.out.println("");
//             System.out.print("Enter your choice: ");

//             try {
//                 input = scan.nextInt(); 
//                 scan.nextLine();
//             } catch (Exception e) {
//                 scan.nextLine();
//             }

//         }

//         switch(input){ //Switch User Input
//             case 1: 
//                 adminApp.getInstance().displayLoginMenu(); //Start Admin Module
//                 break;
//             case 2:
//                 customerApp.getInstance().customerMenu(); //Start User Module
//                 break;
//             case 3:
//                 scan.close();
//                 System.out.println("Thank you for using MOBLIMA!");
//                 System.out.println("Have a nice day.");
//                 System.exit(0);
//             default:
//                 scan.close();
//                 System.out.println("Thank you for using MOBLIMA!");
//                 System.out.println("Have a nice day.");
//                 System.exit(0);
//         }

//     }

// }
