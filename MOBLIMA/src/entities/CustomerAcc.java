package entities;

public class CustomerAcc extends Account {

    //constructor
    public CustomerAcc(String name, String email, int mobile, int age, String password, String accessLevel)
    {
        super(name,email,mobile,age,password,accessLevel);
    }

/*    public ArrayList<CustomerAcc> readCustomerFile(){
        //read in account.csv file, then put the names, email, mobile and age into constructor and create an array of customer information

    }

    public CustomerAcc createAcc(){
        String name;
        String email;
        int mobile;
        int age;

        //ask for user information and use try to check if name or email or mobile exists
        
        
        
        //to add exceptions
        System.out.println("Enter username: ");
        name = scan.nextLine();
         
        System.out.println("Enter your email:");
        email = scan.nextLine();

        System.out.println("Enter mobile:");
        mobile = scan.nextInt();

        System.out.println("Enter age:");
        age = scan.nextInt();

        CustomerAcc acc = new CustomerAcc(name, email, mobile, age);

        //add into csv file
        //for each account created, create a file inside tranaction
    }
*/
    /*User's history will be update as the user uses the app. History will not be saved into csv file. If app shuts down, history will be gone
    public void updateHistory(ArrayList<Ticket> tix, String movieName, String tranDateTime, String cinID){
        //creates transaction id
        String id =  cinID + tranDateTime;
        //create transaction history
        Transaction x = new Transaction(id, movieName, tranDateTime, tix);
        //add the new transaction to history
        bookingHistory.addFirst(x);
        System.out.println("Transaction Complete!");
    }*/

    /*public void showBookingHistory(){
        //prints out booking history
        for(int i = 0; i < bookingHistory.size(); i++)
        {
        	bookingHistory.get(i).printTransaction();
        }
        return;
    }*/
}
