import java.util.*;

public class UserAcctManager{
  private int userIDgen;
  private int acctIDgen;
  private Map<User, User> users;
  private Map<Account, Account> accts;
  //private List<Object> user_acct;    // linking table ([0] index is userID, [1] index is accountID)

  // Constructor
  public UserAcctManager(){
    users = new TreeMap<User, User>();
    accts = new TreeMap<Account, Account>();

    userIDgen = 0;
    acctIDgen = 0;
  }

  // Create NEW (returns FALSE if failed)
  public boolean addUser(String username, String pwd, String firstName, String lastName, Boolean admin){
    if (uniqueUsername(username) == true){
      User newUser = new User(username, pwd, firstName, lastName, admin);
      users.put(newUser, newUser);
      return true;
    }
    return false;
  }
  public boolean addAcct(String name, String descr, double initBalance){
    int id = acctIDgen++;
    if (uniqueAcctID(id) == true){
      Account newAcct = new Account(acctIDgen, name, descr, initBalance);
      accts.put(newAcct, newAcct);
      return true;
    }
    return false;
  }

  // Check Unique Username & AcctID
  public boolean uniqueUsername(String username){
    User tempUser = new User(username);
    return (!users.containsKey(tempUser));
  }
  public boolean uniqueAcctID(int acctID){
    Account tempAcct = new Account(acctID);
    return (!accts.containsKey(tempAcct));
  }

  // Test, Add & Remove Access
  public boolean hasAccess(String username, Integer acctID){
    User tempUser = new User(username);
    Account tempAcct = new Account(acctID);
    tempUser = users.get(tempUser);
    return tempUser.hasAccess(tempAcct);
  }
  public void addAccess(String username, Integer acctID){
    User tempUser = new User(username);
    tempUser = users.get(tempUser);
    Account tempAcct = new Account(acctID);
    tempAcct = accts.get(tempAcct);

    tempUser.addAccess(tempAcct);
    tempAcct.addAccess(tempUser);
  }
  public void removeAccess(String username, Integer acctID){
    User tempUser = new User(username);
    tempUser = users.get(tempUser);
    Account tempAcct = new Account(acctID);
    tempAcct = accts.get(tempAcct);

    tempUser.removeAccess(tempAcct);
    tempAcct.removeAccess(tempUser);
  }

  // Login
  public boolean login(String username, String pwd){
    User tempUser = new User(username);
    if (users.containsKey(tempUser)){
      tempUser = users.get(tempUser);
      return tempUser.login(username, pwd);
    }
    return false;
  }

  // Print Demo
  public void printInfo(){

  }

  // Unit Test
  public static void main(String[] args){

  }
}
