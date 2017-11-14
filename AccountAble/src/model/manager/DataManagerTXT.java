package model.manager;
// local packages
import model.basic.*;

import java.io.*;
import java.util.*;
import java.time.LocalDateTime;

public class DataManagerTXT {
  String userPath, acctPath, transPath, feeTypePath;
  String user_acctPath, acct_transPath, idPath;
  UserAcctManagerTXT uaManager;

  // CONSTRUCTOR
  public DataManagerTXT(){
    this.userPath = "../data/Users.txt";
    this.acctPath = "../data/Accounts.txt";
    this.transPath = "../data/Transaction.txt";
    this.feeTypePath = "../data/FeeType.txt";
    this.user_acctPath = "../data/User_Account.txt";
    this.acct_transPath = "../data/Account_Transaction.txt";
    this.idPath = "../data/ID.txt";
  }

  public DataManagerTXT(String userPath, String acctPath, String transPath, String feeTypePath, String user_acctPath, String acct_transPath, String idPath){
    this.userPath = userPath;
    this.acctPath = acctPath;
    this.transPath = transPath;
    this.feeTypePath = feeTypePath;
    this.user_acctPath = user_acctPath;
    this.acct_transPath = acct_transPath;
    this.idPath = idPath;
  }

  // GETTERS & SETTERS

  // DATA FORMATTERS
  // Account (int id, String name, String descr, double balance)
  public static Account DATA_TO_ACCT(String data){
    String[] dataArr = data.split(",");
      int id = Integer.parseInt(dataArr[0]);
      String name = dataArr[1].replaceAll(":::", ",");
      String descr = dataArr[2].replaceAll(":::", ",");
      double balance = Double.parseDouble(dataArr[3]);
      double feesBalance = Double.parseDouble(dataArr[4]);
      double availBalance = Double.parseDouble(dataArr[5]);
    return new Account(id, name, descr, balance, feesBalance, availBalance);
  }
  public static String ACCT_TO_DATA(Account acct){
    String data = acct.getID() + "," +
      acct.getName().replaceAll(",", ":::") + "," +
      acct.getDescr().replaceAll(",", ":::") + "," +
      acct.getBalance() + "," +
      acct.getFeesBalance() + "," +
      acct.getAvailBalance() + ",";
    return data;
  }
  // User (int id, String username, String pwd, String firstName, String lastName, String email, String phone, boolean admin)
  public static User DATA_TO_USER(String data){
    String[] dataArr = data.split(",");
    for(String str : dataArr){
      str.replaceAll(":::", ",");
    }
    Integer id = Integer.parseInt(dataArr[0]);
    String username = dataArr[1];
    String pwd = dataArr[2];
    String firstName = dataArr[3];
    String lastName = dataArr[4];
    String email = dataArr[5];
    String phone = dataArr[6];
    boolean admin = Boolean.parseBoolean(dataArr[7]);
    return new User(id, username, pwd, firstName, lastName, email, phone, admin);
  }
  public static String USER_TO_DATA(User user){
     String data = user.getID() + "," +
       user.getUsername().replaceAll(",", ":::") + "," +
       user.getPwd().replaceAll(",", ":::") + "," +
       user.getName()[0].replaceAll(",", ":::") + "," +
       user.getName()[1].replaceAll(",", ":::") + "," +
       user.getEmail().replaceAll(",", ":::") + "," +
       user.getPhone().replaceAll(",", ":::") + "," +
       user.getAdmin();
    return data;
  }
  // Transactions (int id, int acctId, int userId, int codeId, double subTotal, double feeTotal, double acctTotal, String otherParty, String descr, LocalDateTime date, boolean isExpense, boolean paidFee)
  public static Transaction DATA_TO_TRANSACTION(String data){
    String[] dataArr = data.split(",");
      int id = Integer.parseInt(dataArr[0]);
      int acctId = Integer.parseInt(dataArr[1]);
      int userId = Integer.parseInt(dataArr[2]);
      int codeId = Integer.parseInt(dataArr[3]);
      double subTotal = Double.parseDouble(dataArr[4]);
      double feeTotal = Double.parseDouble(dataArr[5]);
      double acctTotal = Double.parseDouble(dataArr[6]);
      String otherParty = dataArr[7].replaceAll(":::", ",");
      String descr = dataArr[8].replaceAll(":::", ",");
      LocalDateTime date = LocalDateTime.parse(dataArr[9]);
      boolean isExpense = Boolean.parseBoolean(dataArr[10]);
      boolean paidFee = Boolean.parseBoolean(dataArr[11]);
    return new Transaction(id, acctId, userId, codeId, subTotal, feeTotal, acctTotal, otherParty, descr, date, isExpense, paidFee);
  }
  public static String TRANSACTION_TO_DATA(Transaction trans){
    String data = trans.getID() + "," +
      trans.getAcctID() + "," +
      trans.getUserID() + "," +
      trans.getCodeID() + "," +
      trans.getSubTotal() + "," +
      trans.getFeeTotal() + "," +
      trans.getAcctTotal() + "," +
      trans.getOtherParty().replaceAll(",", ":::") + "," +
      trans.getDescr().replaceAll(",", ":::") + "," +
      trans.getDate().toString() + "," +
      trans.getIsExpense() + "," +
      trans.getPaidFee();
    return data;
  }
  // FeeType(int id, String name, String descr, double amt, boolean isPercent, boolean isAdditional, boolean isCustom)
  public static FeeType DATA_TO_FEETYPE(String data){
    String[] dataArr = data.split(",");
      int id = Integer.parseInt(dataArr[0]);
      String name = dataArr[1].replaceAll(":::", ",");
      String descr = dataArr[2].replaceAll(":::", ",");
      double amt = Double.parseDouble(dataArr[3]);
      boolean isPercent = Boolean.parseBoolean(dataArr[4]);
      boolean isAdditional = Boolean.parseBoolean(dataArr[5]);
      boolean isCustom = Boolean.parseBoolean(dataArr[6]);
    return new FeeType(id, name, descr, amt, isPercent, isAdditional, isCustom);
  }
  public static String FEETYPE_TO_DATA(FeeType feeType){
    String data = feeType.getID() + "," +
      feeType.getName().replaceAll(",", ":::") + "," +
      feeType.getDescr().replaceAll(",", ":::") + "," +
      feeType.getAmt() + "," +
      feeType.getIsPercent() + "," +
      feeType.getIsAdditional() + "," +
      feeType.getIsCustom();
    return data;
  }
  // Links (int id, int idA, int idB)
  public static Link DATA_TO_LINK(String data){
    String[] dataArr = data.split(",");
      int id = Integer.parseInt(dataArr[0]);
      int idA = Integer.parseInt(dataArr[1]);
      int idB = Integer.parseInt(dataArr[2]);
    return new Link(id, idA, idB);
  }
  public static String LINK_TO_DATA(Link link){
    String data = link.getId() + "," +
      link.getIdA() + "," +
      link.getIdB();
    return data;
  }

// *********************  READERS & WRITERS  *******************************
  // ID
  // Read in all current IDs
  public void readIDFileToManager(UserAcctManagerTXT manager){
    ArrayList<String> idList = readFileToList(idPath);
    if (idList.get(0) == null){
      manager.setIDs(0,0,0,0,0,0);
    } else {
      String[] ids = idList.get(0).split(",");
      System.out.println(ids[0]);
      int userID = Integer.parseInt(ids[0]);
      int acctID = Integer.parseInt(ids[1]);
      int transID = Integer.parseInt(ids[2]);
      int feeTypeID = Integer.parseInt(ids[3]);
      int user_acctID = Integer.parseInt(ids[4]);
      int acct_transID = Integer.parseInt(ids[5]);
      manager.setIDs(userID, acctID, transID, feeTypeID, user_acctID, acct_transID);
    }
  }
  public void writeManagerIDsToFile(UserAcctManagerTXT manager){
    int[] ids = manager.getIDs();
    String data = ids[0] + "," +
        ids[1] + "," +
        ids[2] + "," +
        ids[3] + "," +
        ids[4] + "," +
        ids[5];
    // Overwrite previous values
    writeLineToFile(data, idPath, true);
  }

  // USER
  // Reads in all Users from Users.txt
  public void readUserFileToManager(UserAcctManagerTXT manager){
    ArrayList<String> userList = readFileToList(userPath);
    // makes User entry for every line in txt file
    for (String userStr : userList){
      User tempUser = DATA_TO_USER(userStr);
      manager.addUser(tempUser);
    }
  }
  // Write single User to Users.txt (NO Overwrite)
  public boolean writeUserToFile(User user){
    String userStr = USER_TO_DATA(user);
    return writeLineToFile(userStr, userPath);
  }
  // Overwrite all Users to User.txt (Overwrite)
  public boolean writeAllUsersToFile(ArrayList<User> users){
    ArrayList<String> dataList = new ArrayList<String>();
    for (User user : users){
      dataList.add(USER_TO_DATA(user));
    }
    return writeListToFile(dataList, userPath);
  }
  public boolean writeManagerUsersToFile(UserAcctManagerTXT manager){
    return writeAllUsersToFile(manager.getAllUsers());
  }

  // ACCOUNT
  // Reads in all Accts from Accounts.txt
  public void readAcctFileToManager(UserAcctManagerTXT manager){
    ArrayList<String> acctList = readFileToList(acctPath);
    // makes Acct entry for every line in txt filePath
    for (String acctStr : acctList){
      Account tempAcct = DATA_TO_ACCT(acctStr);
      manager.addAcct(tempAcct);
    }
  }
  // Write single Acct to Accounts.txt (NO Overwrite)
  public boolean writeAcctToFile(Account acct){
    String acctStr = ACCT_TO_DATA(acct);
    return writeLineToFile(acctStr, acctPath);
  }
  // Overwrite all Accounts to Account.txt (Overwrite)
  public boolean writeAllAcctsToFile(ArrayList<Account> accts){
    ArrayList<String> dataList = new ArrayList<String>();
    for (Account acct : accts){
      dataList.add(ACCT_TO_DATA(acct));
    }
    return writeListToFile(dataList, acctPath);
  }
  public boolean writeManagerAcctsToFile(UserAcctManagerTXT manager){
    return writeAllAcctsToFile(manager.getAllAccts());
  }

  // TRANSACTION
  // Reads in all Transactions to Manager
  public void readTransFileToManager(UserAcctManagerTXT manager){
    ArrayList<String> transList = readFileToList(transPath);
    // makes Access entry for every line in txt filePath
    for (String transStr : transList){
      Transaction tempTrans = DATA_TO_TRANSACTION(transStr);
      manager.addTrans(tempTrans);
    }
  }
  // Write single Transaction to File (NO Overwrite)
  public boolean writeTransToFile(Transaction trans){
    String transStr = TRANSACTION_TO_DATA(trans);
    return writeLineToFile(transStr, transPath);
  }
  // Overwrite Transactions to Transaction (Overwrite)
  public boolean writeAllTransToFile(ArrayList<Transaction> transactions){
    ArrayList<String> dataList = new ArrayList<String>();
    for (Transaction trans : transactions){
      dataList.add(TRANSACTION_TO_DATA(trans));
    }
    return writeListToFile(dataList, transPath);
  }
  public boolean writeManagerTransToFile(UserAcctManagerTXT manager){
    return writeAllTransToFile(manager.getAllTransactions());
  }

  // FEE TYPES
  // Reads in all FeeTypes to Manager
  public void readFeeTypeFileToManager(UserAcctManagerTXT manager){
    ArrayList<String> feeTypeList = readFileToList(feeTypePath);
    // makes Access entry for every line in txt filePath
    for (String feeTypeStr : feeTypeList){
      FeeType tempFeeType = DATA_TO_FEETYPE(feeTypeStr);
      manager.addFeeType(tempFeeType);
    }
  }
  // Write single FeeType to File (NO Overwrite)
  public boolean writeFeeTypeToFile(FeeType feeType){
    String feeTypeStr = FEETYPE_TO_DATA(feeType);
    return writeLineToFile(feeTypeStr, feeTypePath);
  }
  // Overwrite All FeeType
  public boolean writeAllFeeTypesToFile(ArrayList<FeeType> feeTypes){
    ArrayList<String> dataList = new ArrayList<String>();
    for (FeeType feeType : feeTypes){
      dataList.add(FEETYPE_TO_DATA(feeType));
    }
    return writeListToFile(dataList, feeTypePath);
  }
  public boolean writeManagerFeeTypeToFile(UserAcctManagerTXT manager){
    return writeAllFeeTypesToFile(manager.getAllFeeTypes());
  }

  // USER_ACCT
  // Reads in all Transactions to Manager
  public void readUser_AcctFileToManager(UserAcctManagerTXT manager){
    ArrayList<String> user_acctList = readFileToList(user_acctPath);
    // makes Access entry for every line in txt filePath
    for (String user_acctStr : user_acctList){
      Link tempUser_Acct = DATA_TO_LINK(user_acctStr);
      manager.addUser_Acct(tempUser_Acct);
    }
  }
  // Write single Transaction to File (NO Overwrite)
  public boolean writeUser_AcctToFile(Link user_acct){
    String user_acctStr = LINK_TO_DATA(user_acct);
    return writeLineToFile(user_acctStr, user_acctPath);
  }
  // Overwrite Transactions to Transaction (Overwrite)
  public boolean writeAllUser_AcctsToFile(ArrayList<Link> user_accts){
    ArrayList<String> dataList = new ArrayList<String>();
    for (Link user_acct : user_accts){
      dataList.add(LINK_TO_DATA(user_acct));
    }
    return writeListToFile(dataList, user_acctPath);
  }
  public boolean writeManagerUser_AcctsToFile(UserAcctManagerTXT manager){
    return writeAllUser_AcctsToFile(manager.getAllUser_Accts());
  }

  // ACCT_TRANS
  // Reads in all Transactions to Manager
  public void readAcct_TransFileToManager(UserAcctManagerTXT manager){
    ArrayList<String> acct_transList = readFileToList(acct_transPath);
    // makes Access entry for every line in txt filePath
    for (String acct_transStr : acct_transList){
      Link tempAcct_Trans = DATA_TO_LINK(acct_transStr);
      manager.addAcct_Trans(tempAcct_Trans);
    }
  }
  // Write single Transaction to File (NO Overwrite)
  public boolean writeAcct_TransToFile(Link acct_trans){
    String acct_transStr = LINK_TO_DATA(acct_trans);
    return writeLineToFile(acct_transStr, acct_transPath);
  }
  // Overwrite Transactions to Transaction (Overwrite)
  public boolean writeAllAcct_TransToFile(ArrayList<Link> acct_trans){
    ArrayList<String> dataList = new ArrayList<String>();
    for (Link acct_tran : acct_trans){
      dataList.add(LINK_TO_DATA(acct_tran));
    }
    return writeListToFile(dataList, acct_transPath);
  }
  public boolean writeManagerAcct_TransToFile(UserAcctManagerTXT manager){
    return writeAllAcct_TransToFile(manager.getAllAcct_Trans());
  }

  //***************GENERIC READERS************************

  // Generic TextFile Reader
  public ArrayList<String> readFileToList(String filePath){
    // This will reference one line at a time
    String line = null;
    // This will hold every line in a list
    ArrayList<String> lineList = new ArrayList<String>();
    try {
        // FileReader reads text files in the default encoding.
        FileReader fileReader = new FileReader(filePath);
        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        // Loop through every line in file
        while((line = bufferedReader.readLine()) != null) {
            lineList.add(line);
        }
        // Always close files.
        bufferedReader.close();
        return lineList;
    }
    catch(FileNotFoundException ex) {
        System.out.println("Unable to open file '" + filePath + "'");
    }
    catch(IOException ex) {
        System.out.println("Error reading file '" + filePath + "'");
        // Or we could just do this: ex.printStackTrace();
    }
    return null;
  }
  // Generic List TextFile Writer (Overwrites)
  public boolean writeListToFile(ArrayList<String> lineList, String filePath){
    try {
      boolean overwrite = true;
			FileWriter fileWriter = new FileWriter(filePath, !overwrite);
      // Loop through every String in array and write to file
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      for (String line : lineList){
        bufferedWriter.write(line);
        bufferedWriter.newLine();
      }
			bufferedWriter.flush();
			bufferedWriter.close();
      return true;
    }
    catch (IOException e) {
			e.printStackTrace();
		}
    return false;
  }
  // Generic Single Line TextFile Writer (Doesn't Overwrite)
  public boolean writeLineToFile(String line, String filePath){
    try {
      boolean overwrite = false;
			FileWriter fileWriter = new FileWriter(filePath, !overwrite);
      // Loop through every String in array and write to file
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      bufferedWriter.write(line);
      bufferedWriter.newLine();
			bufferedWriter.flush();
			bufferedWriter.close();
      return true;
    }
    catch (IOException e) {
			e.printStackTrace();
		}
    return false;
  }
  public boolean writeLineToFile(String line, String filePath, boolean overwrite){
    try {
			FileWriter fileWriter = new FileWriter(filePath, !overwrite);
      // Loop through every String in array and write to file
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      bufferedWriter.write(line);
      bufferedWriter.newLine();
			bufferedWriter.flush();
			bufferedWriter.close();
      return true;
    }
    catch (IOException e) {
			e.printStackTrace();
		}
    return false;
  }

  // Print Demo
  public void printInfo(){

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
      uaManager.addAcct("Offshore, Account", "Shhh...", 10000.00);
      uaManager.addUser_Acct(1, 1);
      uaManager.addUser_Acct(2, 1);
      uaManager.addUser_Acct(2, 2);
      uaManager.addTrans(1, 2, 50105, 10.00, 100.00, 10.0, 10.0, "Some Dude", "Loaned a guy ten bucks", ldt, true, false);
      uaManager.addFeeType("Standard Credit Card Charge", "Use when making or recieving payments by Credit Card.", 4.00, true, true, false);
      uaManager.addAcct_Trans(4, 5);
      uaManager.addAcct_Trans(2, 3);

    String userPath = "../data/testUsers.txt";
    String acctPath = "../data/testAccounts.txt";
    String transPath = "../data/testTrans.txt";
    String feeTypePath = "../data/testFeeType.txt";
    String user_acctPath = "../data/testUser_acct.txt";
    String acct_transPath = "../data/testAcct_trans.txt";
    String idPath = "../data/testIDs.txt";

    DataManagerTXT dataManager = new DataManagerTXT(userPath, acctPath, transPath, feeTypePath, user_acctPath, acct_transPath, idPath);
    ArrayList<User> testUsers = uaManager.getAllUsers();
      dataManager.writeAllUsersToFile(testUsers);
    ArrayList<Account> testAccts = uaManager.getAllAccts();
      dataManager.writeAllAcctsToFile(testAccts);
    ArrayList<Transaction> testTrans = uaManager.getAllTransactions();
      dataManager.writeAllTransToFile(testTrans);
    ArrayList<FeeType> testFeeTypes = uaManager.getAllFeeTypes();
      dataManager.writeAllFeeTypesToFile(testFeeTypes);
    ArrayList<Link> user_accts = uaManager.getAllUser_Accts();
      dataManager.writeAllUser_AcctsToFile(user_accts);
    ArrayList<Link> acct_trans = uaManager.getAllAcct_Trans();
      dataManager.writeAllAcct_TransToFile(acct_trans);

    UserAcctManagerTXT uaManager2 = new UserAcctManagerTXT();
    //dataManager.readIDFileToManager(uaManager2);
    dataManager.readUserFileToManager(uaManager2);
    dataManager.readAcctFileToManager(uaManager2);
    dataManager.readTransFileToManager(uaManager2);
    dataManager.readFeeTypeFileToManager(uaManager2);
    dataManager.readUser_AcctFileToManager(uaManager2);
    dataManager.readAcct_TransFileToManager(uaManager2);
    uaManager2.printInfo(); */
  }
}
