package entities;

public class StaffAcc extends Account {
    //constructor
    public StaffAcc(String name, String email, int mobile, int age, String password, String accessLevel)
    {
        this.username = name;
        this.email = email;
        this.mobile = mobile;
        this.age = age;
        this.password = password;
        this.accessLevel = accessLevel;
    }

}
