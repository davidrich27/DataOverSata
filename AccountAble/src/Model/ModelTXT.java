public class ModelTXT {
  UserAcctManager acctsManager;
  DataManager dataManager;

  public ModelTXT() {
    String userPath = "../../data/Users.txt";
    String acctPath = "../../data/Accounts.txt";
    String user_acctPath = "../../data/User_Account.txt";
    String idPath = "../../data/ID.txt";

    uaManager = new UserAcctManagerTXT();
    dataManager = new DataManagerTXT(userPath, acctPath, user_acctPath);
  }
}
