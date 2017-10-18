import java.util.*;

public class Account implements Comparable<Account>{
  private int id;
  private double initBalance;
  private double balance;
  private User creator;
  private Map<User, User> users;

  // Constructor
  public Account(int id, double initBalance, User creator){
    this.initBalance = initBalance;
    this.balance = initBalance;
    this.creator = creator;
    users = new TreeMap<User, User>();
  }
  public Account(int id){   // Constructor for tempAcct w/ search params.
    this.id = id;
  }

  // Getters & Setters
  public int getID(){
    return id;
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
    System.out.println("Balance: " + getBalance());
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

  // unit test
  public static void main(String[] args){
    User testUser = new User(1, "admin", "12345", "Johnny", "Rotten", true);
    Account testAcct = new Account(1, 1000000.01, testUser);
    testAcct.printInfo();
  }
}
