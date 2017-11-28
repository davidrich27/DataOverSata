package model.basic;

import java.util.*;

public class User implements Comparable<User> {
  private int id;
  private String username, pwd;
  private String firstName, lastName;
  private String email, phone;
  private boolean admin;

  // Constructors
  public User(int id, String username, String pwd, String firstName, String lastName, String email, String phone, boolean admin){
    this.id = id;
    this.username = username.toLowerCase();
    this.pwd = pwd;
    this.admin = admin;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
  }
  // DUMMY SEARCH INSTANCES
  public User(int id, String username){
    this.id = id;
    this.username = username.toLowerCase();
  }

  // Setters & Getters
  public int getID(){
    return id;
  }
  public void setID(int id){
    this.id = id;
  }
  public String getUsername(){
    return username;
  }
  public void setUsername(String username){
    this.username = username;
  }
  public boolean testPwd(String test){
    return (test.equals(pwd));
  }
  public String getPwd(){
    return pwd;
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
  public String getFirstName(){
    return firstName;
  }
  public void setFirstName(String firstName){
    this.firstName = firstName;
  }
  public String getLastName(){
    return lastName;
  }
  public void setLastName(String lastName){
    this.lastName = lastName;
  }
  public String getEmail(){
    return email;
  }
  public void setEmail(String email){
    this.email = email;
  }
  public String getPhone(){
    return phone;
  }
  public void setPhone(String phone){
    this.phone = phone;
  }
  public boolean getAdmin(){
    return admin;
  }
  public void setAdmin(boolean admin){
    this.admin = admin;
  }

  // Comparators
  public int compareTo(User that){    // DEFAULT: Compares two Users by ID
    Integer thisID = this.getID();
    Integer thatID = that.getID();
    return thisID.compareTo(thatID);
  }
  public static Comparator<User> BY_NAME(){  // Compares two User by LastName, FirstName
    return new Comparator<User>() {
      public int compare(User a, User b) {
        String[] aName = a.getName();
        String[] bName = b.getName();
        int testFirstName = aName[0].compareTo(bName[0]);
        int testLastName = aName[1].compareTo(bName[1]);
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
  public static Comparator<User> BY_USERNAME(){  // Compares two User by LastName, FirstName
    return new Comparator<User>() {
      public int compare(User a, User b) {
        String aUser = a.getUsername();
        String bUser = b.getUsername();
        return aUser.compareTo(bUser);
      }
    };
  }

  // Equals (if User has same ID or Username, then equal)
  public boolean equals(User that){
    if (this.id == that.id){
      return true;
    }
    System.out.println("Is " + this.username + " equal to " + that.username + "?");
    if (this.username.equals(that.username)){
      return true;
    }
    return false;
  }

  // Login
  public boolean login(String username, String pwd){
    username = username.toLowerCase();
    if (this.username.equals(username)){
      return testPwd(pwd);
    }
    return false;
  }

  // Print Demo
  public void printInfo(){
    System.out.println("USER DETAILS: ");
    System.out.println("ID: " + getID() + ", Username: " + getUsername() + ", Password: " + getPwd());
    System.out.println("Name: " + getName()[0] + " " + getName()[1]);
    System.out.println("Email: " + getEmail() + ", Phone: " + getPhone());
    System.out.println("Is Admin? " + getAdmin());
    System.out.println("---");
  }

  // Unit Test
  public static void main(String[] args){
    User testUser = new User(1, "John123", "12345", "Johnny", "Rotten", "jr@pistols.com", "(406)393-2091", true);
    testUser.printInfo();
    // Test equality and compare
    User testUser2 = new User(-1, "john123");
    System.out.println("Are they equal? " + testUser.equals(testUser2));
    System.out.println("How do they compare (default ID)? " + testUser.compareTo(testUser2));
    System.out.println("");

    ArrayList<User> testList = new ArrayList<User>();
      User tempUser;
      tempUser = new User(7, "davey123", "pwd", "Dave", "Rich", "dave@gmail.com", "(406)555-1209", false);
      testList.add(tempUser);
      tempUser = new User(22, "trish123", "pwd", "Patricia", "Duce", "p.Duce@gmail.com", "(406)555-1234", false);
      testList.add(tempUser);
      tempUser = new User(32, "admin", "pwd", "Robyn", "Berg", "robyn@gmail.com", "(406)777-4567", true);
      testList.add(tempUser);
    // Sorting Tests
    System.out.println("INPUT ORDER:");
    for (User user : testList){
      user.printInfo();
    }
    System.out.println("DEFAULT SORT (ID):");
    Collections.sort(testList);
    for (User user : testList){
      user.printInfo();
    }
    System.out.println("USERNAME SORT:");
    Collections.sort(testList, User.BY_USERNAME());
    for (User user : testList){
      user.printInfo();
    }
    System.out.println("NAME SORT:");
    Collections.sort(testList, User.BY_NAME());
    for (User user : testList){
      user.printInfo();
    }
  }
}
