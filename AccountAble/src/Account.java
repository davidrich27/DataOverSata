import java.util.*;

public class Account implements Comparable<Account>{
  private int id;
  private String name, descr;
  private double initBalance, balance;
  private Map<User,User> users;

  // Constructor
  public Account(int id, String name, String descr, double initBalance){
    this.id = id;
    this.name = name;
    this.descr = descr;
    this.initBalance = initBalance;
    this.balance = initBalance;
    users = new TreeMap<User, User>();
  }
  public Account(int id, String name, String descr, double initBalance, double balance){
    this.id = id;
    this.name = name;
    this.descr = descr;
    this.initBalance = initBalance;
    this.balance = balance;
    users = new TreeMap<User, User>();
  }

  public Account(int id){   // Temp search Acct
    this.id = id;
  }

  // Getters & Setters
  public int getID(){
    return id;
  }
  public String getName(){
    return name;
  }
  public void setName(String name){
    this.name = name;
  }
  public String getDescr(){
    return descr;
  }
  public void setDescr(String descr){
    this.descr = descr;
  }
  public double getBalance(){
    return balance;
  }
  public double getInitBalance(){
    return initBalance;
  }
  public Map<User, User> getUsers(){
    return users;
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

  // Data formatters :: id;name;descr;initBalance
  public static User DATA_TO_ACCT(String data){
    String[] dataArr = data.split(";");
    boolean admin = Boolean.parseBoolean(dataArr[4]);
    return new User(dataArr[0], dataArr[1], dataArr[2], dataArr[3], admin);
  }

  public static String ACCT_TO_DATA(Account acct){
    String data = acct.id + ";" +
      acct.name + ";" +
      acct.descr + ";" +
      Double.toString(acct.initBalance);
    return data;
  }

  // Print Demo
  public void printInfo(){
    System.out.println("Account ID: " + getID());
    System.out.println("Account Name: " + getName());
    System.out.println("Account Descr: " + getDescr());
    System.out.println("Account Balance: " + getBalance());
  }

  // Unit Test
  public static void main(String[] args){
    User testUser = new User("admin", "12345", "Johnny", "Rotten", true);
    Account testAcct = new Account(1, "Johnny's Account", "Don't Ask", 1000000.01);
    testAcct.printInfo();
  }
}
