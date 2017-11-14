package model.master;
// Local Packages
import model.basic.*;
import model.manager.*;

import java.util.*;
import java.time.LocalDateTime;

public class ModelTXT {
  public UserAcctManagerTXT uaManager;
  public DataManagerTXT dataManager;

  public User login;

  // Custom Paths
  public ModelTXT(String userPath, String acctPath, String transPath, String feeTypePath, String user_acctPath, String acct_transPath, String idPath) {
    uaManager = new UserAcctManagerTXT();
    dataManager = new DataManagerTXT(userPath, acctPath, transPath, feeTypePath, user_acctPath, acct_transPath, idPath);

    loadAll();
  }
  // Default Paths
  public ModelTXT(){
    String userPath = "../data/Users.txt";
    String acctPath = "../data/Accounts.txt";
    String transPath = "../data/Transactions.txt";
    String feeTypePath = "../data/FeeTypes.txt";
    String user_acctPath = "../data/User_Account.txt";
    String acct_transPath = "../data/Account_Transaction.txt";
    String idPath = "../data/ID.txt";

    uaManager = new UserAcctManagerTXT();
    dataManager = new DataManagerTXT(userPath, acctPath, transPath, feeTypePath, user_acctPath, acct_transPath, idPath);

    loadAll();
  }

  // LOAD FILES
  public void loadAll(){
    dataManager.readIDFileToManager(uaManager);
    dataManager.readUserFileToManager(uaManager);
    dataManager.readAcctFileToManager(uaManager);
    dataManager.readTransFileToManager(uaManager);
    dataManager.readFeeTypeFileToManager(uaManager);
    dataManager.readUser_AcctFileToManager(uaManager);
    dataManager.readAcct_TransFileToManager(uaManager);
  }
  // SAVE ALL TO FILES
  public void saveAll(){
    saveIDs();
    dataManager.writeManagerIDsToFile(uaManager);
    dataManager.writeManagerUsersToFile(uaManager);
    dataManager.writeManagerAcctsToFile(uaManager);
    dataManager.writeManagerTransToFile(uaManager);
    dataManager.writeManagerFeeTypeToFile(uaManager);
    dataManager.writeManagerUser_AcctsToFile(uaManager);
    dataManager.writeManagerAcct_TransToFile(uaManager);
  }
  // SAVE SINGLE ENTRY
  public void saveIDs(){
    dataManager.writeManagerIDsToFile(uaManager);
  }
  public void saveUser(User user){
    uaManager.addUser(user);
    dataManager.writeUserToFile(user);
  }
  public void saveAcct(Account acct){
    uaManager.addAcct(acct);
    dataManager.writeAcctToFile(acct);
  }
  public void saveTrans(Transaction trans){
    uaManager.addTrans(trans);
    dataManager.writeTransToFile(trans);
  }
  public void saveFeeType(FeeType feeType){
    uaManager.addFeeType(feeType);
    dataManager.writeFeeTypeToFile(feeType);
  }
  public void saveUser_Acct(Link user_acct){
    uaManager.addUser_Acct(user_acct);
    dataManager.writeUser_AcctToFile(user_acct);
  }

  // LOGIN
  public boolean testLogin(String username, String pwd){
    User tempUser = uaManager.getUserByUsername(username);
    if (tempUser != null){
      System.out.println("Found user...");
      return tempUser.testPwd(pwd);
    }
    return false;
  }
  public User getUserByUsername(String username){
    return uaManager.getUserByUsername(username);
  }

  // CREATE NEW
  public boolean addNewUser(String username, String pwd, String firstName, String lastName, String email, String phone, boolean admin){
    int userId = uaManager.addUser(username, pwd, firstName, lastName, email, phone, admin);
    if (userId > -1){
      User tempUser = uaManager.getUserByID(userId);
      saveUser(tempUser);
      saveIDs();
      return true;
    }
    return false;
  }
  public boolean addNewAcct(String name, String descr){
    int acctId = uaManager.addAcct(name, descr, 0, 0, 0);
    if (acctId > -1){
      Account tempAcct = uaManager.getAcctByID(acctId);
      saveAcct(tempAcct);
      saveIDs();
      return true;
    }
    return false;
  }
  public boolean addNewFeeType(String name, String descr, double amt, boolean isPercent, boolean isAdditional, boolean isCustom){
    int feeTypeId = uaManager.addFeeType(name, descr, amt, isPercent, isAdditional, isCustom);
    if (feeTypeId > -1){
      FeeType tempFeeType = uaManager.getFeeTypeByID(feeTypeId);
      saveFeeType(tempFeeType);
      saveIDs();
      return true;
    }
    return false;
  }
  // For FEES
  public boolean addNewTrans(ArrayList<FeeType> feeTypes, int acctId, int userId, int codeId, double transSubTotal, String otherParty, String descr, LocalDateTime date, boolean isExpense, boolean paidFee){
    double[] totals = calcFees(transSubTotal, feeTypes, isExpense);
    int transID = uaManager.addTrans(acctId, userId, codeId, totals[0], totals[1], totals[2], otherParty, descr, date, isExpense, paidFee);
    if (transID > -1){
      Transaction trans = uaManager.getTransByID(transID);
      saveTrans(trans);
      saveIDs();
      return true;
    }
    return false;
  }
  // For NO FEES
  public boolean addNewTrans(int acctId, int userId, int codeId, double transSubTotal, String otherParty, String descr, LocalDateTime date, boolean isExpense){
    int transID = uaManager.addTrans(acctId, userId, codeId, transSubTotal, 0, transSubTotal, otherParty, descr, date, isExpense, true);
    if (transID > -1){
      Transaction trans = uaManager.getTransByID(transID);
      saveTrans(trans);
      saveIDs();
      return true;
    }
    return false;
  }
  public boolean addNewUser_Acct(int userID, int acctID){
    int user_acctID = uaManager.addUser_Acct(userID, acctID);
    if (user_acctID > -1){
      Link tempUser_Acct = uaManager.getUser_AcctByID(user_acctID);
      saveUser_Acct(tempUser_Acct);
      saveIDs();
      return true;
    }
    return false;
  }
  public boolean addNewAcct_Trans(int acctID, int transID){
    int acct_transId = uaManager.addAcct_Trans(acctID, transID);
    if (acct_transId > -1){
      Link tempAcct_Trans = uaManager.getAcct_TransByID(acct_transId);
      saveUser_Acct(tempAcct_Trans);
      saveIDs();
      return true;
    }
    return false;
  }
  // CALCULATE FEES : return array (transSubTotal, feeTotal, transTotal, acctTotal)
  public double[] calcFees(double subTotal, ArrayList<FeeType> feeTypes, boolean isExpense){
    double transSubTotal = subTotal;
    double feeTotal = 0;
    double transTotal = 0;
    if (isExpense){
      for (FeeType feeType : feeTypes){
        double fee = 0;
        if (feeType.getIsPercent()){
          double multiplier = feeType.getAmt();
          fee = subTotal * multiplier * .01;
          feeTotal += fee;
        } else {
          fee = feeType.getAmt();
          feeTotal += fee;
        }
      }
      transTotal = transSubTotal + feeTotal;
    } else {
      for (FeeType feeType : feeTypes){
        double fee = 0;
        if (feeType.getIsPercent()){
          double multiplier = feeType.getAmt();
          fee = subTotal * multiplier * .01;
          feeTotal += fee;
        } else {
          fee = feeType.getAmt();
          feeTotal += fee;
        }
      }
      transTotal = transSubTotal - feeTotal;
    }
    double[] results = {transSubTotal, feeTotal, transTotal};
    return results;
  }

  public void printInfo(){
    System.out.println("User Account Manager: ");
    uaManager.printInfo();
    System.out.println("Data Manager: ");
    dataManager.printInfo();
  }

  public static void main(String[] args){
    ModelTXT model = new ModelTXT();

    model.printInfo();

    model.addNewUser("davey123", "pwd", "Dave", "Rich", "dave@gmail.com", "(406)555-1209", false);
    model.addNewUser("trish123", "pwd", "Patricia", "Duce", "p.Duce@gmail.com", "(406)555-1234", false);
    model.addNewUser("admin", "pwd", "Robyn", "Berg", "robyn@gmail.com", "(406)777-4567", true);
    model.addNewUser("csadmin", "cs323", "Default", "Default", "csadmin323@umt.edu", "(790)604-4060", true);

    // model.addNewAcct("Master Account", "Where all the Money Goes...", 1000, 50);
    // model.addNewAcct("Offshore Account", "Shhh...", 9999999.99, 9999999.99);
    //
    // model.addNewAccess(1, 1);
    // model.addNewAccess(2, 1);

    model.printInfo();
    model.saveAll();

    boolean test = model.testLogin("davey123", "pwd");
    System.out.println(test);
  }
}
