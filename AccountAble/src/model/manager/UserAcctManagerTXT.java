package model.manager;
// local packages
import model.basic.*;

import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class UserAcctManagerTXT{
  private int userIDgen, acctIDgen, transIDgen, feeTypeIDgen, codeIDgen, user_acctIDgen, acct_transIDgen;   // Generates unique IDs for all arraylist entries
  private ArrayList<User> users;
  private ArrayList<Account> accts;
  private ArrayList<Transaction> trans;
  private ArrayList<FeeType> feeTypes;
  private ArrayList<Code> codes;
  private ArrayList<Link> user_acct;              // linking table for User to Account access
  private ArrayList<Link> acct_trans;             // linking table for which Accounts contain which Transaction
  private Account masterAcct;                    // an account that holds all transactions

  // CONSTRUCTOR
  public UserAcctManagerTXT(){
    users = new ArrayList<User>();
    accts = new ArrayList<Account>();
    trans = new ArrayList<Transaction>();
    feeTypes = new ArrayList<FeeType>();
    codes = new ArrayList<Code>();
    user_acct = new ArrayList<Link>();
    acct_trans = new ArrayList<Link>();
    userIDgen = 1000;
    acctIDgen = 1000;
    user_acctIDgen = 1000;
    acct_transIDgen = 1000;
    masterAcct = new Account(-0, "Master Account", "Running Total of All Transactions from All Accounts.");
  }
  // SETTERS & GETTERS
  // Sets current value for
  public void setIDs(int userIDgen, int acctIDgen, int transIDgen, int feeTypeIDgen, int codeIDgen, int user_acctIDgen, int acct_transIDgen){
    this.userIDgen = userIDgen;
    this.acctIDgen = acctIDgen;
    this.transIDgen = transIDgen;
    this.feeTypeIDgen = feeTypeIDgen;
    this.codeIDgen = codeIDgen;
    this.user_acctIDgen = user_acctIDgen;
    this.acct_transIDgen = acct_transIDgen;
  }
  public int[] getIDs(){
    int[] ids = new int[7];
    ids[0] = userIDgen;
    ids[1] = acctIDgen;
    ids[2] = transIDgen;
    ids[3] = codeIDgen;
    ids[3] = feeTypeIDgen;
    ids[4] = codeIDgen;
    ids[5] = user_acctIDgen;
    ids[6] = acct_transIDgen;
    return ids;
  }

  // RECONCILE & CALC FEES (Goes trans-by-trans and calculates the total balance, fee balance, and )
  public void reconcile(){
    // set masterAcct to zeroes
    masterAcct.setBalance(0);
    masterAcct.setFeesBalance(0);
    masterAcct.setAvailBalance(0);
    // set all other accts to zeroes
    for (Account acct : accts){
      acct.setBalance(0);
      acct.setFeesBalance(0);
      acct.setAvailBalance(0);
    }
    // run through all trans and add them to proper accts
    for (Transaction tran : trans){
      // Find associated account
      Account acct = getAcctByID(tran.getAcctID());
      // Add trans subTotal (before fees) to main balance
      Double subTotal = tran.getSubTotal();
      Double feeTotal = tran.getFeeTotal();
      // newBal = oldBal + transSubTotal
      acct.setBalance(acct.getBalance() + subTotal);
      masterAcct.setBalance(masterAcct.getBalance() + subTotal);
      // newAvailBal = oldAvailBal + transSubTotal + feeTotal
      acct.setAvailBalance(acct.getAvailBalance() + subTotal);
      masterAcct.setAvailBalance(masterAcct.getAvailBalance() + subTotal);
      // If fee not paid, add it to fee balance and available balance
      if (!tran.getPaidFee()){
        acct.setAvailBalance(acct.getAvailBalance() + feeTotal);
        masterAcct.setAvailBalance(masterAcct.getAvailBalance() + feeTotal);
        acct.setFeesBalance(acct.getFeesBalance() + feeTotal);
        masterAcct.setFeesBalance(masterAcct.getFeesBalance() + feeTotal);
      }
      // Update trans to have new postAcctBal (running balance)
      tran.setAcctBal(acct.getBalance());
    }
  }

  // Add NEW (returns FALSE if isUnique=F and Add fails) (Returns ID given to NEW object)
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
  public int addTrans(int acctId, int userId, int codeId, double subTotal, double feeTotal, double total, String otherParty, String descr, LocalDateTime dateEntry, LocalDate dateSale, boolean isExpense, boolean paidFee){
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
    Transaction newTrans = new Transaction(transIDgen, acctId, userId, codeId, subTotal, feeTotal, acctTotal, otherParty, descr, dateEntry, dateSale, isExpense, paidFee);
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
      Collections.sort(feeTypes);
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

  // NEW Codes
  public boolean addCode(Code code){
    if (isUnique(code, codes) == true){
      codes.add(code);
      Collections.sort(codes);
      return true;
    }
    return false;
  }
  public int addCode(String name, String descr){
    codeIDgen++;
    Code newCode = new Code(codeIDgen, name, descr);
    boolean success = addCode(newCode);
    if (success == true){
      return codeIDgen;
    }
    codeIDgen--;
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

  // EDIT (BY ID)
  public int editUserByID(int id, String username, String pwd, String firstName, String lastName, String email, String phone, boolean admin){
    User gotUser = getUserByID(id);
    if (gotUser == null){
      System.out.println("Error: User does not exist.");
      return -1;
    }
    gotUser.setUsername(username);
    gotUser.setPwd(pwd);
    gotUser.setName(firstName, lastName);
    gotUser.setEmail(email);
    gotUser.setPhone(phone);
    gotUser.setAdmin(admin);
    return id;
  }
  public int editAcctByID(int id, String name, String descr){
    Account gotAcct = getAcctByID(id);
    if (gotAcct == null){
      System.out.println("Error: Account does not exist.");
      return -1;
    }
    gotAcct.setName(name);
    gotAcct.setDescr(descr);
    return id;
  }
  public int editTransByID(int id, int acctId, int userId, int codeId, double subTotal, double feeTotal, double acctBal, String otherParty, String descr, LocalDateTime dateEntry, LocalDate dateSale, boolean isExpense, boolean paidFee){
    Transaction gotTrans = getTransByID(id);
    if (gotTrans == null){
      System.out.println("Error: Transaction does not exist.");
      return -1;
    }
    gotTrans.setAcctID(acctId);
    gotTrans.setUserID(userId);
    gotTrans.setCodeID(codeId);
    gotTrans.setSubTotal(subTotal);
    gotTrans.setFeeTotal(feeTotal);
    gotTrans.setAcctBal(acctBal);
    gotTrans.setOtherParty(otherParty);
    gotTrans.setDescr(descr);
    gotTrans.setDateEntry(dateEntry);
    gotTrans.setDateSale(dateSale);
    gotTrans.setIsExpense(isExpense);
    gotTrans.setPaidFee(paidFee);
    return id;
  }
  public int editFeeTypeByID(int id, String name, String descr, double amt, boolean isPercent, boolean isAdditional, boolean isCustom){
    FeeType gotFeeType = getFeeTypeByID(id);
    if (gotFeeType == null){
      System.out.println("Error: FeeType does not exist.");
      return -1;
    }
    gotFeeType.setName(name);
    gotFeeType.setDescr(descr);
    gotFeeType.setAmt(amt);
    gotFeeType.setIsPercent(isPercent);
    gotFeeType.setIsAdditional(isAdditional);
    gotFeeType.setIsCustom(isCustom);
    return id;
  }
  public int editCodeByID(int id, String name, String descr){
    Code gotCode = getCodeByID(id);
    if (gotCode == null){
      System.out.println("Error: Code does not exist.");
      return -1;
    }
    gotCode.setName(name);
    gotCode.setDescr(descr);
    return id;
  }

  // DELETE (BY ID)
  public int deleteUserByID(int id){
    User testUser = new User(id, "");
    int index = Collections.binarySearch(users, testUser);
    if (index < 0){
      return -1;
    }
    ArrayList<Integer> userLinks = getAllUser_AcctByUserID(id);
    for (Integer linkID : userLinks){
      deleteUser_AcctByID(linkID);
    }
    users.remove(index);
    return index;
  }
  public int deleteAcctByID(int id){
    // find account
    Account testAcct = new Account(id, "");
    int index = Collections.binarySearch(accts, testAcct);
    if (index < 0){
      return -1;
    }
    // delete all transactions associated with acct
    ArrayList<Integer> acctTrans = getAllTransByAcctID(id);
    for (Integer tranID : acctTrans){
      deleteTransByID(tranID);
    }
    // delete all user_acct links associated with acct
    ArrayList<Integer> acctLinks = getAllUser_AcctByAcctID(id);
    for (Integer linkID : acctLinks){
      deleteUser_AcctByID(linkID);
    }
    accts.remove(index);
    return index;
  }
  public int deleteTransByID(int id){
    Transaction testTrans = new Transaction(id);
    int index = Collections.binarySearch(trans, testTrans);
    if (index < 0){
      return -1;
    }
    trans.remove(index);
    return index;
  }
  public int deleteCodeByID(int id){
    Code testCode = new Code(id);
    int index = Collections.binarySearch(codes, testCode);
    if (index < 0){
      return -1;
    }
    codes.remove(index);
    return index;
  }
  public int deleteFeeTypeByID(int id){
    FeeType testFeeType = new FeeType(id);
    int index = Collections.binarySearch(feeTypes, testFeeType);
    if (index < 0){
      return -1;
    }
    feeTypes.remove(index);
    return index;
  }
  public int deleteUser_AcctByID(int user_acctID){
    Link testUser_Acct = new Link(user_acctID);
    int index = Collections.binarySearch(user_acct, testUser_Acct);
    if (index < 0){
      return -1;
    }
    user_acct.remove(index);
    return index;
  }
  public int deleteUser_AcctByUserAcctID(int userID, int acctID){
    Link tempLink = new Link(-1, userID, acctID);
    for (int i = 0; i < user_acct.size(); i++){
      if (user_acct.get(i).equals(tempLink)) {
        user_acct.remove(i);
        return i;
      }
    }
    return -1;
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
  public ArrayList<Code> getAllCodes(){
    return codes;
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
  // LINEAR SEARCHES!!!!
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
  // GET ALL USER_ACCT BY USERID
  public ArrayList<Integer> getAllUser_AcctByUserID(int userID){
    ArrayList<Integer> userLinks = new ArrayList<Integer>();
    for (Link link : user_acct){
      if (link.getIdA() == userID){
        userLinks.add(link.getId());
      }
    }
    return userLinks;
  }
  // GET ALL USER_ACCT BY ACCTID
  public ArrayList<Integer> getAllUser_AcctByAcctID(int acctID){
    ArrayList<Integer> acctLinks = new ArrayList<Integer>();
    for (Link link : user_acct){
      if (link.getIdB() == acctID){
        acctLinks.add(link.getId());
      }
    }
    return acctLinks;
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
  // GET ALL TRANSACTIONS BY ACCOUNT ID
  public ArrayList<Integer> getAllTransByAcctID(int acctID){
    ArrayList<Integer> transactions = new ArrayList<Integer>();
    for (Transaction tran : trans){
      if (tran.getAcctID().equals(acctID)){
        transactions.add(tran.getID());
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
  public Code getCodeByID(int id){
    Code code = new Code(id);
    int index = Collections.binarySearch(codes, code);
    if (index < 0){
      return null;
    }
    return codes.get(index);
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
  public Code getCodeByIndex(int index){
    return codes.get(index);
  }
  public Link getUser_AcctByIndex(int index){
    return user_acct.get(index);
  }
  public Link getAcct_TransByIndex(int index){
    return acct_trans.get(index);
  }

  // ACCESS TEST
  public boolean testUser_Acct(int userID, int acctID){
    Link testLink = new Link(-1, userID, acctID);
    return !isUnique(testLink, user_acct);
  }
  // UNIQUENESS TEST **(Note: Uses linear search - could implement binary search)
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
  public boolean isUnique(Code testCode, ArrayList<Code> array){
    for (Code code : array){
      if (code.equals(testCode)){
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
    System.out.println("ALL CODES:");
    for(Code code : codes){
      code.printInfo();
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
