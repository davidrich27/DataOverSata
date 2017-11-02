import java.io.*;
import java.util.*;

public class DataManagerTXT {
  String userPath, acctPath, accessPath, idPath;
  UserAcctManagerTXT uaManager;

  // CONSTRUCTOR
  public DataManagerTXT(){
    this.userPath = "../data/Users.txt";
    this.acctPath = "../data/Accounts.txt";
    this.accessPath = "../data/Accesses.txt";
    this.idPath = "../data/ID.txt";
  }

  public DataManagerTXT(String userPath, String acctPath, String accessPath, String idPath){
    this.userPath = userPath;
    this.acctPath = acctPath;
    this.accessPath = accessPath;
    this.idPath = idPath;
  }

  // GETTERS & SETTERS
  public void setUserPath(String userPath){
    this.userPath = userPath;
  }
  public String getUserPath(){
    return this.userPath;
  }

  // DATA FORMATTERS
  // Account (id;name;descr;initBalance;balance)
  public static Account DATA_TO_ACCT(String data){
    String[] dataArr = data.split(";");
    int id = Integer.parseInt(dataArr[0]);
    double initBalance = Double.parseDouble(dataArr[3]);
    double balance = Double.parseDouble(dataArr[4]);
    return new Account(id, dataArr[1], dataArr[2], initBalance, balance);
  }
  public static String ACCT_TO_DATA(Account acct){
    String data = acct.getID() + ";" +
      acct.getName() + ";" +
      acct.getDescr() + ";" +
      acct.getInitBalance() + ";" +
      acct.getBalance();
    return data;
  }
  // User (id;username;pwd;firstName;lastName;email;phone;admin)
  public static User DATA_TO_USER(String data){
    String[] dataArr = data.split(";");
    int id = Integer.parseInt(dataArr[0]);
    boolean admin = Boolean.parseBoolean(dataArr[7]);
    return new User(id, dataArr[1], dataArr[2], dataArr[3], dataArr[4], dataArr[5], dataArr[6], admin);
  }
  public static String USER_TO_DATA(User user){
     String data = user.getID() + ";" +
       user.getUsername() + ";" +
       user.getPwd() + ";" +
       user.getName()[0] + ";" +
       user.getName()[1] + ";" +
       user.getEmail() + ";" +
       user.getPhone() + ";" +
       user.getAdmin();
    return data;
  }
  // Access (id;UserID;AcctID)
  // ** REQUIRES UserAcctManager to reference IDs **
  public static Access DATA_TO_ACCESS(String data, UserAcctManagerTXT manager){
    String[] dataArr = data.split(";");
    int id = Integer.parseInt(dataArr[0]);
    int userID = Integer.parseInt(dataArr[1]);
    int acctID = Integer.parseInt(dataArr[2]);
    User user = manager.getUserByID(userID);
    Account acct = manager.getAcctByID(acctID);
    if (acct != null && user != null){
      return new Access(id, user, acct);
    }
    return null;
  }
  public static String ACCESS_TO_DATA(Access access){
    String data = access.getID() + ";" +
      access.getUser().getID() + ";" +
      access.getAcct().getID();
    return data;
  }
  // Transactions (id;AccountID;Amt)

  // READERS & WRITERS
  // Read in all current IDs
  public void readIDFileToManager(UserAcctManagerTXT manager){
    ArrayList<String> idList = readFileToList(idPath);
    String[] ids = idList.get(0).split(";");
    int userID = Integer.parseInt(ids[0]);
    int acctID = Integer.parseInt(ids[1]);
    int accessID = Integer.parseInt(ids[2]);
    manager.setIDs(userID, acctID, accessID);
  }
  public void writeManagerIDsToFile(UserAcctManagerTXT manager){
    int[] ids = manager.getIDs();
    String data = ids[0] + ";" +
        ids[1] + ";" +
        ids[2];
    // Overwrite previous values
    writeLineToFile(data, idPath, true);
  }

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

  // Reads in all Accesses from Accesses.txt
  public void readAccessFileToManager(UserAcctManagerTXT manager){
    ArrayList<String> accessList = readFileToList(accessPath);
    // makes Access entry for every line in txt filePath
    for (String accessStr : accessList){
      Access tempAccess = DATA_TO_ACCESS(accessStr, manager);
      manager.addAccess(tempAccess);
    }
  }
  // Write single Access to Accesses.txt (NO Overwrite)
  public boolean writeAccessToFile(Access access){
    String accessStr = ACCESS_TO_DATA(access);
    return writeLineToFile(accessStr, accessPath);
  }
  // Overwrite all Accesses to Accesses.txt (Overwrite)
  public boolean writeAllAccessesToFile(ArrayList<Access> accesses, UserAcctManagerTXT manager){
    ArrayList<String> dataList = new ArrayList<String>();
    for (Access access : accesses){
      dataList.add(ACCESS_TO_DATA(access));
    }
    return writeListToFile(dataList, accessPath);
  }
  public boolean writeManagerAccessesToFile(UserAcctManagerTXT manager){
    return writeAllAccessesToFile(manager.getAllAccesses(), manager);
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
  public static void main(String[] args){
    UserAcctManagerTXT uaManager = new UserAcctManagerTXT();
      uaManager.addUser("davey123", "pwd", "Dave", "Rich", "dave@gmail.com", "(406)555-1209", false);
      uaManager.addUser("trish123", "pwd", "Patricia", "Duce", "p.Duce@gmail.com", "(406)555-1234", false);
      uaManager.addUser("admin", "pwd", "Robyn", "Berg", "robyn@gmail.com", "(406)777-4567", true);
      uaManager.addAcct("Master Account", "Where all the Money Goes...", 1000, 50);
      uaManager.addAcct("Offshore Account", "Shhh...", 9999999.99, 9999999.99);
      uaManager.addAccess(1, 1);
      uaManager.addAccess(2, 1);

    String userPath = "../../data/testUsers.txt";
    String acctPath = "../../data/testAccounts.txt";
    String accessPath = "../../data/testAccesses.txt";
    String idPath = "../../data/testIDs.txt";

    DataManagerTXT dataManager = new DataManagerTXT(userPath, acctPath, accessPath, idPath);
    User testUser = uaManager.getUserByIndex(1);
    dataManager.writeUserToFile(testUser);
    Account testAcct = uaManager.getAcctByIndex(1);
    dataManager.writeAcctToFile(testAcct);

    UserAcctManagerTXT uaManager2 = new UserAcctManagerTXT();
    dataManager.readUserFileToManager(uaManager2);
    dataManager.readAcctFileToManager(uaManager2);
    uaManager2.printInfo();
  }
}
