public class Model {
  UserAcctManager acctsManager;
  DataManager dataManager;

  public Model() {
    userPath = "../data/Users.txt";
    acctPath = "../data/Accounts.txt";
    user_acctPath = "../data/User_Account.txt";
    idPath = "../data/ID.txt";

    acctsManager = new UserAcctManager();
    dataManager = new DataManager(userPath, acctPath, user_acctPath);
  }

  public Model(User[] users, Account[] accts){
    acctsManager = new UserAcctManager();
  }
}
