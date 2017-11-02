import java.util.*;

public class Access implements Comparable<Access>{
  private int id;
  private User user;
  private Account acct;

  // Constructors
  public Access(int id, User user, Account acct){
    this.id = id;
    this.user = user;
    this.acct = acct;
  }
  // Dummy Search Constructors
  public Access(User user, Account acct){
    this.user = user;
    this.acct = acct;
  }
  public Access(int id){
    this.id = id;
  }

  // Setters & Getters
  public int getID(){
    return id;
  }
  public void setID(int id){
    this.id = id;
  }
  public User getUser(){
    return user;
  }
  public void setUser(User user){
    this.user = user;
  }
  public Account getAcct(){
    return acct;
  }
  public void setAcct(Account acct){
    this.acct = acct;
  }

  // Default compareTo: first User, second Acct
  public int compareTo(Access that){
    int testUser = this.getUser().compareTo(that.getUser());
    if (testUser == 0){
      int testAcct = this.getAcct().compareTo(that.getAcct());
      return testAcct;
    }
    return testUser;
  }
  // Compare: first Acct, then User
  public static Comparator<Access> BY_ACCOUNT_FIRST(){
    return new Comparator<Access>() {
      public int compare(Access a, Access b) {
        int testAcct = a.getAcct().compareTo(b.getAcct());
        if (testAcct == 0){
          int testUser = a.getUser().compareTo(b.getUser());
          return testUser;
        }
        return testAcct;
      }
    };
  }
  // Compare: by ID
  public static Comparator<Access> BY_ID(){
    return new Comparator<Access>() {
      public int compare(Access a, Access b) {
        Integer aID = a.getID();
        Integer bID = b.getID();
        return aID.compareTo(bID);
      }
    };
  }
  // Compare: User only
  public static Comparator<Access> BY_USER_ONLY(){
    return new Comparator<Access>() {
      public int compare(Access a, Access b) {
        int testUser = a.getUser().compareTo(b.getUser());
        return testUser;
      }
    };
  }
  // Compare: Acct only
  public static Comparator<Access> BY_ACCOUNT_ONLY(){
    return new Comparator<Access>() {
      public int compare(Access a, Access b) {
        int testAcct = a.getAcct().compareTo(b.getAcct());
        return testAcct;
      }
    };
  }

  // Equals (if same User and Account OR same ID, then equal)
  public boolean equals(Access that){
    if (this.getUser().equals(that.getUser())){
      if (this.getAcct().equals(that.getAcct())){
        return true;
      }
    }
    if (this.getID() == that.getID()){
      return true;
    }
    return false;
  }

  // Print Demo
  public void printInfo(){
    System.out.println("User(" + user.getID() + ", " + user.getUsername() + ") has access to Account(" + acct.getID() + ", " + acct.getName() + ").");
  }

  // Unit Test
  public static void main(String[] args){
    User testUser = new User(1, "John123", "12345", "Johnny", "Rotten", "jr@pistols.com", "(406)393-2091", true);
    Account testAcct = new Account(1, "Johnny's Account", "Don't Ask", 1000000.01);
    Access testAccess = new Access(1, testUser, testAcct);
    testAccess.printInfo();
  }
}
