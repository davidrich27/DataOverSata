public class ModelTXT {
  UserAcctManager acctsManager;
  DataManager dataManager;

  public ModelTXT() {
    userPath = "../../data/Users.txt";
    acctPath = "../../data/Accounts.txt";
    user_acctPath = "../../data/User_Account.txt";
    idPath = "../../data/ID.txt";

    acctsManager = new UserAcctManagerTXT();
    dataManager = new DataManagerTXT(userPath, acctPath, user_acctPath);
  }
}
