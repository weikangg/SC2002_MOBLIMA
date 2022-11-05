package entities;


public abstract class Account {
    public String username;
    public String email;
    public int mobile;
    public int age;
    public String password;
    public String accessLevel;
    
    // Gettors
    public String getUsername(){
        return this.username;
    }
    public String getEmail(){
        return this.email;
    }
    public int getMobile(){
        return this.mobile;
    }
    public int getAge(){
        return this.age;
    }
    public String getPassword(){
        return this.password;
    }
    public String getAccessLevel(){
        return this.accessLevel;
    }

    // Settors
    public void setUsername(String n){
        this.username = n;
        return;
    }
    public void setEmail(String e){
        this.email = e;
        return;
    }
    public void setMobile(int m){
        this.mobile = m;
        return;
    }
    public void setAge(int a){
        this.age = a;
        return;
    }
    public void setPassword(String p){
        this.password = p;
        return;
    }
    public void setAccessLevel(String accessLevel){
        this.accessLevel = accessLevel;
    }

}
