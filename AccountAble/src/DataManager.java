import java.io.*;

public class DataManager {
  String userPath, acctPath, user_acctPath;

  // Constructor
  public ReaderWriter(String userPath, String acctPath, String user_acctPath){
    this.userPath = userPath;
    this.acctPath = acctPath;
    this.user_acctPath = user_acctPath;
  }

  // Readers & Writers
  // Reads in all Users from Users.txt
  public void readUserFileToManager(UserAcctManager manager){
    ArrayList<String> userList = readFileToList(userPath);
    // makes User entry for every line in txt file
    for (String userStr : userList){
      User tempUser = User.DATA_TO_USER(userStr);
      manager.addUser(tempUser);
    }
    return userMap;
  }
  // Write single User to Users.txt
  public boolean writeUserToFile(User user){
    Sting userStr = User.USER_TO_DATA(user);
    return writeLineToFile(userStr, userPath);
  }
  // Overwrite all Users to User.txt
  public boolean writeAllUserToFile(ArrayList<User> users){
    return false;
  }

  // Reads in all Accts from Accounts.txt
  public void readAcctFileToManager(UserAcctManager manager){
    ArrayList<String> acctList = readFileToList(acctPath);
    // makes Acct entry for every line in txt filePath
    for (String acctStr : acctList){
      Account tempAcct = Account.DATA_TO_ACCT(acctStr);
      manager.addAcct(tempAcct);
    }
  }
  // Write single Acct to Accounts.txt
  public boolean writeAcctToFile(Account acct){
    Sting acctStr = Account.ACCT_TO_DATA(acct);
    return writeLineToFile(acctStr, acctPath);
  }
  // Overwrite all Accounts to Account.txt
  public boolean writeAllAcctToFile(ArrayList<Account> accts){
    return false;
  }

  // Generic TextFile Reader
  public List<String> readFileToList(filePath){
    // This will reference one line at a time
    String line = null;
    // This will hold every line in a list
    List<String> lineList = new ArrayList<String>();
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
			fileWriter.flush();
			fileWriter.close();
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
			fileWriter.flush();
			fileWriter.close();
      return true;
    }
    catch (IOException e) {
			e.printStackTrace();
		}
    return false;
  }
}
