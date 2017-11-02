public class ModelTXT {
	UserAcctManagerTXT uaManager;
	DataManagerTXT dataManager;

	public ModelTXT(String userPath, String acctPath, String accessPath, String idPath) {
		uaManager = new UserAcctManagerTXT();
		dataManager = new DataManagerTXT(userPath, acctPath, accessPath, idPath);

		load();
	}

	public ModelTXT(String configPath) {

	}

	public ModelTXT() {
		String userPath = "../../data/Users.txt";
		String acctPath = "../../data/Accounts.txt";
		String accessPath = "../../data/Accesses.txt";
		String idPath = "../../data/ID.txt";

		uaManager = new UserAcctManagerTXT();
		dataManager = new DataManagerTXT(userPath, acctPath, accessPath, idPath);

		load();
	}

	// Load files, Save files, and Save All files
	public void load() {
		dataManager.readIDFileToManager(uaManager);
		dataManager.readUserFileToManager(uaManager);
		dataManager.readAcctFileToManager(uaManager);
		dataManager.readAccessFileToManager(uaManager);
	}

	public void saveAll() {
		dataManager.writeManagerIDsToFile(uaManager);
		dataManager.writeManagerUsersToFile(uaManager);
		dataManager.writeManagerAcctsToFile(uaManager);
		dataManager.writeManagerAccessesToFile(uaManager);
	}

	public void saveUser(User user) {
		uaManager.addUser(user);
		dataManager.writeUserToFile(user);
	}

	public void saveAcct(Account acct) {
		uaManager.addAcct(acct);
		dataManager.writeAcctToFile(acct);
	}

	public void saveAccess(Access access) {
		uaManager.addAccess(access);
		dataManager.writeAccessToFile(access);
	}

	// Login
	public boolean login(String username, String pwd) {
		User tempUser = uaManager.getUserByUsername(username);
		if (tempUser != null) {
			return tempUser.testPwd(pwd);
		}
		return false;
	}

	// Add New User
	public boolean addNewUser(User user) {
		boolean success = uaManager.addUser(user);
		saveUser(user);
		return success;
	}

	public void printInfo() {
		System.out.println("User Account Manager: ");
		uaManager.printInfo();
		System.out.println("Data Manager: ");
		dataManager.printInfo();
	}

	public static void main(String[] args) {
		ModelTXT model = new ModelTXT();

		model.printInfo();
	}
}
