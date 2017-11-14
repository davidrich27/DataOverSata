package model.manager;
// local packages
import model.basic.*;

import java.util.*;
import java.time.LocalDateTime;

public class UserAcctManagerTXT{
  private int userIDgen, acctIDgen, transIDgen, feeTypeIDgen, user_acctIDgen, acct_transIDgen;   // Generates unique IDs for all arraylist entries
  private ArrayList<User> users;
  private ArrayList<Account> accts;
  private ArrayList<Transaction> trans;
  private ArrayList<FeeType> feeTypes;
  private ArrayList<Link> user_acct;              // linking table for User to Account access
  private ArrayList<Link> acct_trans;             // linking table for which Accounts contain which Transaction
  private Account masterAcct;                    // an account that holds all transactions

  // Constructor
  public UserAcctManagerTXT(){
    users = new ArrayList<User>();
    accts = new ArrayList<Account>();
    trans = new ArrayList<Transaction>();
    feeTypes = new ArrayList<FeeType>();
    user_acct = new ArrayList<Link>();
    acct_trans = new ArrayList<Link>();
    userIDgen = 0;
    acctIDgen = 0;
    user_acctIDgen = 0;
    acct_transIDgen = 0;
    masterAcct = new Account(-1, "Master Account", "Running Total of All Transactions from All Accounts.");
  }

  // Sets current value for
  public void setIDs(int userIDgen, int acctIDgen, int transIDgen, int user_acctIDgen, int acct_transIDgen, int feeType_transIDgen){
    this.userIDgen = userIDgen;
    this.acctIDgen = acctIDgen;
    this.transIDgen = transIDgen;
    this.user_acctIDgen = user_acctIDgen;
    this.acct_transIDgen = acct_transIDgen;
  }
  public int[] getIDs(){
    int[] ids = new int[6];
    ids[0] = userIDgen;
    ids[1] = acctIDgen;
    ids[2] = transIDgen;
    ids[3] = feeTypeIDgen;
    ids[4] = user_acctIDgen;
    ids[5] = acct_transIDgen;
    return ids;
  }

  // Add NEW (returns FALSE if isUnique=F and Add fails)
  // NEW Users
  public boolean addUser(User newUser){
    if (isUnique(newUser, users) == true){
      users.add(newUser);
      Collections.sort(users);
      return true;
    }
    return false;
  }
  public int addUser(String username, String pwd, String firstName, String lastName, String email, String phone, boolean admin){
    userIDgen++;
    User newUser = new User(userIDgen, username, pwd, firstName, lastName, email, phone, admin);
    boolean success = addUser(newUser);
    if (success == true){
      return userIDgen;
    }
    userIDgen--;
    return -1;
  }

  // NEW Accounts
  public boolean addAcct(Account newAcct){
    if (isUnique(newAcct, accts) == true){
      accts.add(newAcct);
      Collections.sort(accts);
      return true;
    }
    return false;
  }
  public int addAcct(String name, String descr, double balance, double feesBalance, double availBalance){
    acctIDgen++;
    Account newAcct = new Account(acctIDgen, name, descr, balance, feesBalance, availBalance);
    boolean success = addAcct(newAcct);
    if (success == true){
      return acctIDgen;
    }
    acctIDgen--;
    return -1;
  }

  // NEW Transactions
  public boolean addTrans(Transaction newTrans){
    if (isUnique(newTrans, trans) == true){
      trans.add(newTrans);
      Collections.sort(trans);
      return true;
    }
    return false;
  }
  // NOT FOR OLD TRANSACTIONS!!!
  public int addTrans(int acctId, int userId, int codeId, double subTotal, double feeTotal, double total, String otherParty, String descr, LocalDateTime date, boolean isExpense, boolean paidFee){
    transIDgen++;
    // Add Transaction To Account Totals
    Account acct = getAcctByID(acctId);
    double acctTotal = 0;
    if (isExpense == true){
      acct.setBalance(acct.getBalance() - subTotal);
      acct.setFeesBalance(acct.getFeesBalance() + feeTotal);
      acctTotal = acct.getAvailBalance() - total;
      acct.setAvailBalance(acctTotal);
    } else {
      acct.setBalance(acct.getBalance() + subTotal);
      acct.setFeesBalance(acct.getFeesBalance() + feeTotal);
      acctTotal = acct.getAvailBalance() + total;
      acct.setAvailBalance(acctTotal);
    }
    // Create New Transaction
    Transaction newTrans = new Transaction(transIDgen, acctId, userId, codeId, subTotal, feeTotal, acctTotal, otherParty, descr, date, isExpense, paidFee);
    boolean success = addTrans(newTrans);
    if (success == true){
      return transIDgen;
    }
    acctIDgen--;
    return -1;
  }

  // NEW FeeTypes
  public boolean addFeeType(FeeType newFeeType){
    if (isUnique(newFeeType, feeTypes) == true){
      feeTypes.add(newFeeType);
      Collections.sort(trans);
      return true;
    }
    return false;
  }
  public int addFeeType(String name, String descr, double amt, boolean isPercent, boolean isAdditional, boolean isCustom){
    feeTypeIDgen++;
    FeeType newFeeType = new FeeType(feeTypeIDgen, name, descr, amt, isPercent, isAdditional, isCustom);
    boolean success = addFeeType(newFeeType);
    if (success == true){
      return feeTypeIDgen;
    }
    feeTypeIDgen--;
    return -1;
  }

  // NEW User_Acct Links
  public boolean addUser_Acct(Link newLink){
    if (isUnique(newLink, user_acct) == true){
      user_acct.add(newLink);
      Collections.sort(user_acct);
      return true;
    }
    return false;
  }
  public int addUser_Acct(int userID, int acctID){
    user_acctIDgen++;
    Link newUser_Acct = new Link(user_acctIDgen, userID, acctID);
    boolean success = addUser_Acct(newUser_Acct);
    if (success == true){
      return user_acctIDgen;
    }
    user_acctIDgen--;
    return -1;
  }
  public int addUser_Acct(User user, Account acct){
    int userID = user.getID();
    int acctID = acct.getID();
    return addUser_Acct(userID, acctID);
  }
  public boolean addUser_Acct(int id, int userID, int acctID){
    Link newUser_Acct = new Link(id, userID, acctID);
    return addUser_Acct(newUser_Acct);
  }

  // NEW Acct_Trans Links
  public boolean addAcct_Trans(Link newLink){
    if (isUnique(newLink, acct_trans) == true){
      acct_trans.add(newLink);
      Collections.sort(acct_trans);
      return true;
    }
    return false;
  }
  public int addAcct_Trans(int acctID, int tranID){
    acct_transIDgen++;
    Link newAcct_Trans = new Link(acct_transIDgen, acctID, tranID);
    boolean success = addAcct_Trans(newAcct_Trans);
    if (success == true){
      return acct_transIDgen;
    }
    acct_transIDgen--;
    return -1;
  }
  public int addAcct_Trans(Account acct, Transaction tran){
    int acctID = acct.getID();
    int tranID = tran.getID();
    return addUser_Acct(acctID, tranID);
  }
  public boolean addAcct_Trans(int id, int acctID, int tranID){
    Link newAcct_Tran = new Link(id, acctID, tranID);
    return addAcct_Trans(newAcct_Tran);
  }

  // GET ALL
  public ArrayList<User> getAllUsers(){
    return users;
  }
  public ArrayList<Account> getAllAccts(){
    return accts;
  }
  public ArrayList<Transaction> getAllTransactions(){
    return trans;
  }
  public ArrayList<FeeType> getAllFeeTypes(){
    return feeTypes;
  }
  public ArrayList<Link> getAllUser_Accts(){
    return user_acct;
  }
  public ArrayList<Link> getAllAcct_Trans(){
    return acct_trans;
  }
  public Account getMasterAccount(){
    return masterAcct;
  }

  // GET BY USERNAME
  public User getUserByUsername(String username){
    User testUser = new User(-1, username);
    Collections.sort(users, User.BY_USERNAME());
    int index = Collections.binarySearch(users, testUser, User.BY_USERNAME());
    if (index < 0){
      return null;
    }
    User target = users.get(index);
    Collections.sort(users);
    return target;
  }
  public Account getAcctByName(String name){
    Account testAcct = new Account(-1, name);
    Collections.sort(accts, Account.BY_NAME());
    int index = Collections.binarySearch(accts, testAcct, Account.BY_NAME());
    if (index < 0){
      return null;
    }
    Account target = accts.get(index);
    Collections.sort(accts);
    return target;
  }
  // Linear search
  public FeeType getFeeTypeByName(String name){
    for (FeeType feeType : feeTypes){
      if (feeType.getName() == name){
        return feeType;
      }
    }
    return null;
  }

  // GET ALL ACCOUNTS BY USER (doesn't use binary search / O(n) search)
  public ArrayList<Integer> getAllAcctByUserID(int userID){
    ArrayList<Integer> allowedAccts = new ArrayList<Integer>();
    for (Link link : user_acct){
      if (link.getIdA() == userID){
        Integer allowedAcct = link.getIdB();
        allowedAccts.add(allowedAcct);
      }
    }
    return allowedAccts;
  }
  // GET ALL USERS BY ACCOUNT
  public ArrayList<Integer> getAllUserByAcct(int acctID){
    ArrayList<Integer> allowedUsers = new ArrayList<Integer>();
    for (Link link : user_acct){
      if (link.getIdB() == acctID){
        Integer allowedUser = link.getIdA();
        allowedUsers.add(allowedUser);
      }
    }
    return allowedUsers;
  }
  // GET USER_ACCT BY USERID & ACCTID
  public Link getUser_AcctByUserAcctID(int userID, int acctID){
    Link tempLink = new Link(-1, userID, acctID);
    for (Link link : user_acct){
      if (link.equals(tempLink)) {
        return tempLink;
      }
    }
    return null;
  }
  // GET ACCT_TRANS BY ACCTID & TRANSID
  public Link getAcct_TransByAcctTransID(int acctID, int transID){
    Link tempLink = new Link(-1, acctID, transID);
    for (Link link : acct_trans){
      if (link.equals(tempLink)) {
        return tempLink;
      }
    }
    return null;
  }
  // GET ALL TRANSACTIONS BY ACCOUNT
  public ArrayList<Integer> getAllTransByAcct(int acctID){
    ArrayList<Integer> transactions = new ArrayList<Integer>();
    for (Link link : acct_trans){
      if (link.getIdA() == acctID){
        Integer tran = link.getIdB();
        transactions.add(tran);
      }
    }
    return transactions;
  }

  // GET BY ID (log(N) search)
  public User getUserByID(int id){
    User testUser = new User(id, "");
    int index = Collections.binarySearch(users, testUser);
    if (index < 0){
      return null;
    }
    return users.get(index);
  }
  public Account getAcctByID(int id){
    Account testAcct = new Account(id, "");
    int index = Collections.binarySearch(accts, testAcct);
    if (index < 0){
      return null;
    }
    return accts.get(index);
  }
  public Transaction getTransByID(int id){
    Transaction testTran = new Transaction(id);
    int index = Collections.binarySearch(trans, testTran);
    if (index < 0){
      return null;
    }
    return trans.get(index);
  }
  public FeeType getFeeTypeByID(int id){
    FeeType testFeeType = new FeeType(id);
    int index = Collections.binarySearch(feeTypes, testFeeType);
    if (index < 0){
      return null;
    }
    return feeTypes.get(index);
  }
  public Link getUser_AcctByID(int id){
    Link testLink = new Link(id);
    int index = Collections.binarySearch(user_acct, testLink);
    if (index < 0){
      return null;
    }
    return user_acct.get(index);
  }
  public Link getAcct_TransByID(int id){
    Link testLink = new Link(id);
    int index = Collections.binarySearch(acct_trans, testLink);
    if (index < 0){
      return null;
    }
    return acct_trans.get(index);
  }

  // GET BY INDEX
  public User getUserByIndex(int index){
    return users.get(index);
  }
  public Account getAcctByIndex(int index){
    return accts.get(index);
  }
  public Transaction getTransByIndex(int index){
    return trans.get(index);
  }
  public FeeType getFeeTypeByIndex(int index){
    return feeTypes.get(index);
  }
  public Link getUser_AcctByIndex(int index){
    return user_acct.get(index);
  }
  public Link getAcct_TransByIndex(int index){
    return acct_trans.get(index);
  }

  // REMOVE

  // ACCESS TEST
  public boolean testUser_Acct(int userID, int acctID){
    Link testLink = new Link(-1, userID, acctID);
    return !isUnique(testLink, user_acct);
  }
  // UNIQUENESS TEST
  public boolean isUnique(User testUser, ArrayList<User> array){
    for (User user : array){
      if (user.equals(testUser)){
        return false;
      }
    }
    return true;
  }
  public boolean isUnique(Account testAcct, ArrayList<Account> array){
    for (Account acct : array){
      if (acct.equals(testAcct)){
        return false;
      }
    }
    return true;
  }
  public boolean isUnique(Transaction testTran, ArrayList<Transaction> array){
    for (Transaction tran : array){
      if (tran.equals(testTran)){
        return false;
      }
    }
    return true;
  }
  public boolean isUnique(FeeType testFeeType, ArrayList<FeeType> array){
    for (FeeType feeType : array){
      if (feeType.equals(testFeeType)){
        return false;
      }
    }
    return true;
  }
  public boolean isUnique(Link testLink, ArrayList<Link> array){
    for (Link link : array){
      if (link.equals(testLink)){
        return false;
      }
    }
    return true;
  }

  // PRINT DEMO
  public void printInfo(){
    System.out.println("CURRENT ID VALUES: " + userIDgen + ", " + acctIDgen + ", " + transIDgen + ", " + feeTypeIDgen + ", " + user_acctIDgen + ", " + acct_transIDgen);
    System.out.println("ALL USERS:");
    for(User user : users){
      user.printInfo();
    }
    System.out.println("<----------->");
    System.out.println("ALL ACCOUNTS:");
    for(Account acct : accts){
      acct.printInfo();
    }
    System.out.println("<----------->");
    System.out.println("ALL TRANSACTIONS:");
    for(Transaction tran : trans){
      tran.printInfo();
    }
    System.out.println("<----------->");
    System.out.println("ALL FEE TYPES:");
    for(FeeType feeType : feeTypes){
      feeType.printInfo();
    }
    System.out.println("<----------->");
    System.out.println("ALL USER-ACCOUNT LINKS:");
    for(Link link : user_acct){
      link.printInfo();
    }
    System.out.println("<----------->");
    System.out.println("ALL ACCOUNT-TRANSACTION LINKS:");
    for(Link link : acct_trans){
      link.printInfo();
    }
    System.out.println("");
  }

  // UNIT TEST
  public static void main(String[] args){ /*
    LocalDateTime ldt = LocalDateTime.now();
    UserAcctManagerTXT uaManager = new UserAcctManagerTXT();
      uaManager.addUser("davey123", "pwd", "Dave", "Rich", "dave@gmail.com", "(406)555-1209", false);
      uaManager.addUser("trish123", "pwd", "Patricia", "Duce", "p.Duce@gmail.com", "(406)555-1234", false);
      uaManager.addUser("admin", "pwd", "Robyn", "Berg", "robyn@gmail.com", "(406)777-4567", true);
      uaManager.addUser("davey123", "pwd", "Dave", "Rich", "dave@gmail.com", "(406)555-1209", false);
      uaManager.addAcct("Master Account", "Where all the Money Goes...", 10.00);
      uaManager.addAcct("Offshore Account", "Shhh...", 10000.00);
      uaManager.addUser_Acct(1, 1);
      uaManager.addUser_Acct(2, 1);
      uaManager.addUser_Acct(2, 2);
      uaManager.addTrans(1, 2, 50105, 10.00, 100.00, 10.0, 10.0, "Some Dude", "Loaned a guy ten bucks", ldt, true, false);
      uaManager.addFeeType("Standard Credit Card Charge", "Use when making or recieving payments by Credit Card.", 4.00, true, true, false);

    uaManager.printInfo();

    // Find user by Username
    System.out.println("SEARCH BY USERNAME:");
    String username = "davey123";
    User foundUser = uaManager.getUserByUsername("davey123");
    if (foundUser != null){
      System.out.println(username + " found!");
      foundUser.printInfo();
    } else {
      System.out.println(username + " not found...");
    }
    // Find user by ID
    System.out.println("SEARCH BY ID:");
    int id = 1;
    foundUser = uaManager.getUserByID(id);
    if (foundUser != null){
      System.out.println(id + " found!");
      foundUser.printInfo();
    } else {
      System.out.println(id + " not found...");
    }
    // Find user by Index
    System.out.println("SEARCH BY INDEX:");
    uaManager.getUserByIndex(1).printInfo();

    // Test adding duplicate User
    System.out.println(uaManager.addUser("davey123", "pwd", "Dave", "Rich", "dave@gmail.com", "(406)555-1209", false));

    // Test account access
    System.out.println("Does (1,1) has access? " + uaManager.testUser_Acct(1,1)); // Should be true
    System.out.println("Does (1,3) has access? " + uaManager.testUser_Acct(1,3)); // Shoud be false
    System.out.println("Does (99, 99) has access? " + uaManager.testUser_Acct(99, 99)); // Should be false

    // Get user by Username
    foundUser = uaManager.getUserByUsername("davey123");
    foundUser.printInfo();
    foundUser = uaManager.getUserByUsername("trish123");
    foundUser.printInfo();
    foundUser = uaManager.getUserByUsername("admin");
    foundUser.printInfo();
    */
  }
}
