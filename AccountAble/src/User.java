import java.util.*;

public class User implements Comparable<User> {
  private String username, pwd;
  private String firstName, lastName;
  private boolean admin, isRetired;
  private Map<Account, Account> accts;

  // Constructor
  public User(String username, String pwd, String firstName, String lastName, Boolean admin){
    this.username = username;
    this.pwd = pwd;
    this.admin = admin;
    this.firstName = firstName;
    this.lastName = lastName;
    accts = new TreeMap<Account, Account>();
    isRetired = false;
  }
  public User(String username){  // Constructor for tempUser w/ search params.
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
  public boolean isRetired(){
    return isRetired;
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
