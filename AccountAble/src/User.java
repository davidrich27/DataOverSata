import java.util.*;

public class User implements Comparable<User> {
  private String username, pwd;
  private String firstName, lastName;
  private boolean admin;
  private Map<Account, Account> accts;

  // Constructors
  public User(String username, String pwd, String firstName, String lastName, Boolean admin){
    this.username = username;
    this.pwd = pwd;
    this.admin = admin;
    this.firstName = firstName;
    this.lastName = lastName;
    accts = new TreeMap<Account, Account>();
  }

  public User(String username){ // Temp search User
    this.username = username;
  }

  // Setters & Getters
  public String getUsername(){
    return username;
  }
  public void setUsername(String username){
    this.username = username;
  }
  public boolean testPwd(String test){
    return (test == pwd);
  }
  public void setPwd(String pwd){
    this.pwd = pwd;
  }
  public String[] getName(){
    String[] name = {firstName, lastName};
    return name;
  }
  public void setName(String firstName, String lastName){
    this.firstName = firstName;
    this.lastName = lastName;
  }
  public boolean getAdmin(){
    return admin;
  }
  public Map<Account, Account> getAccts(){
    return accts;
  }

  // Comparators
  public int compareTo(User that){    // Compares two Users by Username
    String thisUser = this.getUsername();
    String thatUser = that.getUsername();
    int test = thisUser.compareTo(thatUser);
    return test;
  }
  public static Comparator<User> byName(){  // Compares two User by LastName, FirstName
    return new Comparator<User>() {
      public int compare(User a, User b) {
        String[] aName = a.getName();
        String[] bName = b.getName();
        int testFirstName = aName[1].compareTo(bName[1]);
        int testLastName = aName[0].compareTo(bName[0]);
        if (testLastName != 0){
          return testLastName;
        } else if (testFirstName != 0) {
          return testFirstName;
        } else {
          return a.compareTo(b);
        }
      }
    };
  }

  // Test, Add & Remove User Access
  public boolean hasAccess(Account acct){   // Tells whether Acct is in Accts List
    return accts.containsKey(acct);
  }
  public void addAccess(Account acct){
    accts.put(acct, acct);
  }
  public void removeAccess(Account acct){
    accts.remove(acct);
  }

  // Login
  public boolean login(String username, String pwd){
    if (this.username == username){
      return testPwd(pwd);
    }
    return false;
  }

  // Data formatters :: username;pwd;firstName;lastName;admin
  public static User DATA_TO_USER(String data){
    String[] dataArr = data.split(";");
    boolean admin = Boolean.parseBoolean(dataArr[4]);
    return new User(dataArr[0], dataArr[1], dataArr[2], dataArr[3], admin);
  }

  public static String USER_TO_DATA(User user){
     String data = user.username + ";" +
       user.pwd + ";" +
       user.firstName + ";" +
       user.lastName + ";" +
       Boolean.toString(user.admin);
    return data;
  }

  // Print Demo
  public void PrintInfo(){
    System.out.println("Username: " + getUsername());
    System.out.println("Name: " + getName()[0] + " " + getName()[1]);
    System.out.println("Is Admin? " + getAdmin());
  }

  // Unit Test
  public static void main(String[] args){
    User testUser = new User("admin", "12345", "Johnny", "Rotten", true);
    testUser.PrintInfo();
  }
}
