import java.util.*;

public class Account implements Comparable<Account>{
  private int id;
  private String name, descr;
  private double initBalance, balance;
  private User creator;
  private Map<User, User> users;


  // Constructor
  public Account(int id, String name, String descr, double initBalance, User creator){
    this.id = id;
    this.name = name;
    this.descr = descr;
    this.initBalance = initBalance;
    this.balance = initBalance;
    this.creator = creator;
    users = new TreeMap<User, User>();
    addAccess(creator);
  }
  public Account(int id){   // Constructor for tempAcct w/ search params.
    this.id = id;
  }

  // Getters & Setters
  public int getID(){
    return id;
  }
  public String getName(){
    return name;
  }
  public String getDescr(){
    return descr;
  }
  public double getBalance(){
    return balance;
  }
  public User getCreator(){
    return creator;
  }
  public Map<User, User> getUsers(){
    return users;
  }

  // Print Demo
  public void printInfo(){
    System.out.println("Account ID: " + getID());
    System.out.println("Account Name: " + getName());
    System.out.println("Account Descr: " + getDescr());
    System.out.println("Account Balance: " + getBalance());
    System.out.println("Account Creator: " + creator.getName()[0] + " " + creator.getName()[1]);
  }

  // Comparators
  public int compareTo(Account that){   // Compares two Accounts by ID
    if (this.getID() > that.getID()){
      return +1;
    } else if (this.getID() < that.getID()){
      return -1;
    } else {
      return 0;
    }
  }

  // Test, Add & Remove User Access
  public boolean hasAccess(User user){   // Tells whether User is in Users List
    return users.containsKey(user);
  }
  public void addAccess(User user){
    users.put(user, user);
  }
  public void removeAccess(User user){
    users.remove(user);
  }

  // Unit Test
  public static void main(String[] args){
    User testUser = new User("admin", "12345", "Johnny", "Rotten", true);
    Account testAcct = new Account(1, "Johnny's Account", "Don't Ask", 1000000.01, testUser);
    testAcct.printInfo();
  }
}
