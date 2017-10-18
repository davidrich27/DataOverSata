import java.util.*;

public class User implements Comparable<User> {
  private int id;
  private String username, pwd;
  private String firstName, lastName;
  private boolean admin;
  private Map<Account, Account> accts;
  private boolean isRetired;

  // Constructor
  public User(int id, String username, String pwd, String firstName, String lastName, Boolean admin){
    this.id = id;
    this.username = username;
    this.pwd = pwd;
    this.admin = admin;
    this.firstName = firstName;
    this.lastName = lastName;
    accts = new TreeMap<Account, Account>();
    isRetired = false;
  }
  public User(int id){  // Constructor for tempUser w/ search params.
    this.id = id;
  }

  // Setters & Getters
  public int getID(){
    return id;
  }
  public String getUsername(){
    return username;
  }
  public void setUsername(String username){
    this.username = username;
  }
  public boolean testPwd(String test){
    if (test == pwd){
      return true;
    }
    return false;
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
  public Boolean getAdmin(){
    return admin;
  }
  public Map<Account, Account> getAccts(){
    return accts;
  }

  // Print Demo
  public void PrintInfo(){
    System.out.println("ID: " + getID());
    System.out.println("Username: " + getUsername());
    System.out.println("Name: " + getName()[0] + " " + getName()[1]);
    System.out.println("Is Admin? " + getAdmin());
  }

  // Comparators
  public int compareTo(User that){    // Compares two Users by ID.
    if (this.getID() > that.getID()){
      return +1;
    } else if (this.getID() < that.getID()){
      return -1;
    } else {
      return 0;
    }
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

  // unit test
  public static void main(String[] args){
    User testUser = new User(1, "admin", "12345", "Johnny", "Rotten", true);
    testUser.PrintInfo();
  }
}
