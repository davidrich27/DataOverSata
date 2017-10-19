public class Model {
  UserAcctManager acctsManager;

  public Model() {
    acctsManager = new UserAcctManager();
  }

  public Model(User[] users, Account[] accts){
    acctsManager = new UserAcctManager();
  }
}
