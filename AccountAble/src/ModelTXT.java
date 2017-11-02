public class ModelTXT {
  public UserAcctManagerTXT uaManager;
  public DataManagerTXT dataManager;

  public User login;

  public ModelTXT(String userPath, String acctPath, String accessPath, String idPath) {
    uaManager = new UserAcctManagerTXT();
    dataManager = new DataManagerTXT(userPath, acctPath, accessPath, idPath);

    loadAll();
  }
  public ModelTXT(){
    String userPath = "../data/Users.txt";
    String acctPath = "../data/Accounts.txt";
    String accessPath = "../data/Accesses.txt";
    String idPath = "../data/ID.txt";

    uaManager = new UserAcctManagerTXT();
    dataManager = new DataManagerTXT(userPath, acctPath, accessPath, idPath);

    loadAll();
  }

  // Load files, Save files, and Save All files
  public void loadAll(){
    dataManager.readIDFileToManager(uaManager);
    dataManager.readUserFileToManager(uaManager);
    dataManager.readAcctFileToManager(uaManager);
    dataManager.readAccessFileToManager(uaManager);
  }
  public void saveAll(){
    saveIDs();
    dataManager.writeManagerUsersToFile(uaManager);
    dataManager.writeManagerAcctsToFile(uaManager);
    dataManager.writeManagerAccessesToFile(uaManager);
  }

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
  public void saveAccess(Access access){
    uaManager.addAccess(access);
    dataManager.writeAccessToFile(access);
  }

  // Login
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

  // Add New
  public boolean addNewUser(String username, String pwd, String firstName, String lastName, String email, String phone, boolean admin){
    boolean success = uaManager.addUser(username, pwd, firstName, lastName, email, phone, admin);
    if (success == true){
      User tempUser = uaManager.getUserByUsername(username);
      saveUser(tempUser);
      saveIDs();
    }
    return success;
  }
  public boolean addNewAcct(String name, String descr, double initBalance, double balance){
    boolean success = uaManager.addAcct(name, descr, 0, 0);
    if (success == true){
      Account tempAcct = uaManager.getAcctByName(name);
      saveAcct(tempAcct);
      saveIDs();
    }
    return success;
  }
  public boolean addNewAccess(int userID, int acctID){
    boolean success = uaManager.addAccess(userID, acctID);
    if (success == true){
      Access tempAccess = uaManager.getAccessByUserAcctID(userID, acctID);
      saveAccess(tempAccess);
      saveIDs();
    }
    return success;
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

    model.addNewAcct("Master Account", "Where all the Money Goes...", 1000, 50);
    model.addNewAcct("Offshore Account", "Shhh...", 9999999.99, 9999999.99);

    model.addNewAccess(1, 1);
    model.addNewAccess(2, 1);

    model.printInfo();
    model.saveAll();

    boolean test = model.testLogin("davey123", "pwd");
    System.out.println(test);
  }
}
