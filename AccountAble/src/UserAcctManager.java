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

  // Create NEW
  public void NewUser(String username, String pwd, String firstName, String lastName, Boolean admin){
    userIDgen++;
    User newUser = new User(userIDgen, username, pwd, firstName, lastName, admin);
    users.put(newUser, newUser);
  }
  public void NewAcct(double initBalance, User creator){
    acctIDgen++;
    Account newAcct = new Account(acctIDgen, initBalance, creator);
    accts.put(newAcct, newAcct);
  }

  // Test, Add & Remove Access
  public boolean hasAccess(Integer userID, Integer acctID){
    User tempUser = new User(userID);
    Account tempAcct = new Account(acctID);
    tempUser = users.get(tempUser);
    return tempUser.hasAccess(tempAcct);
  }
  public void addAccess(Integer userID, Integer acctID){
    User tempUser = new User(userID);
    tempUser = users.get(tempUser);
    Account tempAcct = new Account(acctID);
    tempAcct = accts.get(tempAcct);

    tempUser.addAccess(tempAcct);
    tempAcct.addAccess(tempUser);
  }
  public void removeAccess(Integer userID, Integer acctID){
    User tempUser = new User(userID);
    tempUser = users.get(tempUser);
    Account tempAcct = new Account(acctID);
    tempAcct = accts.get(tempAcct);

    tempUser.removeAccess(tempAcct);
    tempAcct.removeAccess(tempUser);
  }
}
