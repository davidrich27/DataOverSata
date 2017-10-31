import java.util.*;

public class UserAcctManagerTXT{
  private int userIDgen, acctIDgen, accessIDgen, transIDgen, feesIDgen;
  private ArrayList<User> users;
  private ArrayList<Account> accts;
  private ArrayList<Access> accesses;    // linking table from User to Account

  // Constructor
  public UserAcctManagerTXT(){
    users = new ArrayList<User>();
    accts = new ArrayList<Account>();
    accesses = new ArrayList<Access>();
    userIDgen = 0;
    acctIDgen = 0;
    accessIDgen = 0;
  }

  // Sets current value for
  public void setIDs(int userID, int acctID, int accessID){
    userIDgen = userID;
    acctIDgen = acctID;
    accessIDgen = accessID;
  }
  public int[] getIDs(){
    int[] ids = new int[3];
    ids[0] = userIDgen;
    ids[1] = acctIDgen;
    ids[2] = accessIDgen;
    return ids;
  }

  // Add NEW (returns FALSE if not Unique/fails)
  // NEW Users
  public boolean addUser(User newUser){
    if (isUserUnique(newUser) == true){
      users.add(newUser);
      Collections.sort(users);
      return true;
    }
    return false;
  }
  public boolean addUser(String username, String pwd, String firstName, String lastName, String email, String phone, boolean admin){
    userIDgen++;
    User newUser = new User(userIDgen, username, pwd, firstName, lastName, email, phone, admin);
    boolean success = addUser(newUser);
    if (success == true){
      return true;
    }
    userIDgen--;
    return false;
  }
  // NEW Accounts
  public boolean addAcct(Account newAcct){
    if (isAcctUnique(newAcct) == true){
      accts.add(newAcct);
      Collections.sort(accts);
      return true;
    }
    return false;
  }
  public boolean addAcct(String name, String descr, double initBalance, double balance){
    acctIDgen++;
    Account newAcct = new Account(acctIDgen, name, descr, initBalance, balance);
    boolean success = addAcct(newAcct);
    if (success == true){
      return true;
    }
    acctIDgen--;
    return true;
  }
  // NEW Accesses
  public boolean addAccess(Access access){
    if (isAccessUnique(access) == true){
      accesses.add(access);
      Collections.sort(accesses);
      return true;
    }
    return false;
  }
  public boolean addAccess(User user, Account acct){
    accessIDgen++;
    Access newAccess = new Access(accessIDgen, user, acct);
    boolean success = addAccess(newAccess);
    if (success == true){
      return true;
    }
    accessIDgen--;
    return false;
  }
  public boolean addAccess(int userID, int acctID){
    User user = getUserByID(userID);
    Account acct = getAcctByID(acctID);
    return addAccess(user, acct);
  }
  public boolean addAccess(int id, int userID, int acctID){
    User user = getUserByID(userID);
    Account acct = getAcctByID(acctID);
    Access newAccess = new Access(id, user, acct);
    return addAccess(newAccess);
  }

  // GET ALL
  public ArrayList<User> getAllUsers(){
    return users;
  }
  public ArrayList<Account> getAllAccts(){
    return accts;
  }
  public ArrayList<Access> getAllAccesses(){
    return accesses;
  }

  // GET BY USERNAME
  public User getUserByUsername(String username){
    User testUser = new User(-1, username);
    Collections.sort(users, User.BY_USERNAME());
    int index = Collections.binarySearch(users, testUser, User.BY_USERNAME());
    Collections.sort(users);
    if (index < 0){
      return null;
    }
    return users.get(index);
  }
  public Account getAcctByName(String name){
    Account testAcct = new Account(-1, name);
    Collections.sort(accts, Account.BY_NAME());
    int index = Collections.binarySearch(accts, testAcct, Account.BY_NAME());
    Collections.sort(accts);
    if (index < 0){
      return null;
    }
    return accts.get(index);
  }

  // GET BY USER&ACCT, OR ALL USERS OR ALL ACCOUNTS
  public Access getAccessByUserAcct(User user, Account acct){
    Access testAccess = new Access(-1, user, acct);
    int index = Collections.binarySearch(accesses, testAccess);
    if (index < 0){
      return null;
    }
    return accesses.get(index);
  }
  public Access getAccessByUserAcctID(int userID, int acctID){
    User user = getUserByID(userID);
    Account acct = getAcctByID(acctID);
    if (user != null && acct != null){
      return getAccessByUserAcct(user, acct);
    }
    return null;
  }

  // GET ALL ACCOUNTS BY USER (doesn't use binary search...)
  // O(n) process
  public ArrayList<Account> getAllAcctByUser(User user){
    ArrayList<Account> allowedAccts = new ArrayList<Account>();
    for (Access access : accesses){
      if (access.getUser() == user){
        Account allowedAcct = access.getAcct();
        allowedAccts.add(allowedAcct);
      }
    }
    return allowedAccts;
  }
  // GET ALL USERS BY ACCOUNT
  public ArrayList<User> getAllUserByAcct(Account acct){
    ArrayList<User> allowedUsers = new ArrayList<User>();
    for (Access access : accesses){
      if (access.getAcct() == acct){
        User allowedUser = access.getUser();
        allowedUsers.add(allowedUser);
      }
    }
    return allowedUsers;
  }

  // GET BY ID
  public User getUserByID(int id){
    User testUser = new User(id, "");
    int index = Collections.binarySearch(users, testUser);
    if (index < 0){
      return null;
    }
    return users.get(index);
  }
  public Account getAcctByID(int id){
    Account testAcct = new Account(id, "");
    int index = Collections.binarySearch(accts, testAcct);
    if (index < 0){
      return null;
    }
    return accts.get(index);
  }
  public Access getAccessByID(int id){
    Access testAccess = new Access(id);
    Collections.sort(accesses, Access.BY_ID());
    int index = Collections.binarySearch(accesses, testAccess, Access.BY_ID());
    if (index < 0){
      return null;
    }
    return accesses.get(index);
  }

  // GET BY INDEX
  public User getUserByIndex(int index){
    return users.get(index);
  }
  public Account getAcctByIndex(int index){
    return accts.get(index);
  }
  public Access getAccessByIndex(int index){
    return accesses.get(index);
  }

  // REMOVE
  public boolean removeUserByID(int id){
    User rmUser = new User(id, "");
    int index = Collections.binarySearch(users, rmUser);
    if (index < 0){
      return false;
    }
    users.remove(index);
    return true;
  }
  public boolean removeAcctByID(int id){
    Account rmAcct = new Account(id, "");
    int index = Collections.binarySearch(accts, rmAcct);
    if (index < 0){
      return false;
    }
    accts.remove(index);
    return true;
  }
  public boolean removeAccess(User userID, Account acctID){
    Access rmAccess = getAccessByUserAcct(userID, acctID);
    int index = Collections.binarySearch(accesses, rmAccess);
    if (index < 0){
      return false;
    }
    accesses.remove(index);
    return true;
  }

  // ACCESS TEST
  public boolean hasAccess(User user, Account acct){
    Access testAccess = new Access(-1, user, acct);
    return !isAccessUnique(testAccess);
  }
  public boolean hasAccess(int userID, int acctID){
    User user = getUserByID(userID);
    Account acct = getAcctByID(acctID);
    if (user != null && acct != null){
      return hasAccess(user, acct);
    }
    return false;
  }

  // UNIQUENESS TESTS
  public boolean isUserUnique(User user){
    if (getUserByID(user.getID()) != null){
      return false;
    }
    if (getUserByUsername(user.getUsername()) != null){
      return false;
    }
    return true;
  }
  public boolean isAcctUnique(Account acct){
    if (getAcctByID(acct.getID()) != null){
      return false;
    }
    if (getAcctByName(acct.getName()) != null){
      return false;
    }
    return true;
  }
  public boolean isAccessUnique(Access access){
    if (getAccessByID(access.getID()) != null){
      return false;
    }
    if (getAccessByUserAcct(access.getUser(), access.getAcct()) != null){
      return false;
    }
    return true;
  }

  // Print Demo
  public void printInfo(){
    System.out.println("CURRENT ID VALUES: " + userIDgen + ", " + acctIDgen + ", " + acctIDgen);
    System.out.println("ALL USERS:");
    for(User user : users){
      user.printInfo();
    }
    System.out.println("ALL ACCOUNTS:");
    for(Account acct : accts){
      acct.printInfo();
    }
    System.out.println("ALL ACCESSES:");
    for(Access access : accesses){
      access.printInfo();
    }
    System.out.println("");
  }

  // Unit Test
  public static void main(String[] args){
    UserAcctManagerTXT uaManager = new UserAcctManagerTXT();
      uaManager.addUser("davey123", "pwd", "Dave", "Rich", "dave@gmail.com", "(406)555-1209", false);
      uaManager.addUser("trish123", "pwd", "Patricia", "Duce", "p.Duce@gmail.com", "(406)555-1234", false);
      uaManager.addUser("admin", "pwd", "Robyn", "Berg", "robyn@gmail.com", "(406)777-4567", true);
      uaManager.addAcct("Master Account", "Where all the Money Goes...", 1000, 50);
      uaManager.addAcct("Offshore Account", "Shhh...", 9999999.99, 9999999.99);
      uaManager.addAccess(1, 1);
      uaManager.addAccess(2, 1);
      uaManager.addAccess(2, 2);

    uaManager.printInfo();

    // Find user by Username
    System.out.println("SEARCH BY USERNAME:");
    String username = "davey123";
    User foundUser = uaManager.getUserByUsername("davey123");
    if (foundUser != null){
      System.out.println(username + " found!");
      foundUser.printInfo();
    } else {
      System.out.println(username + " not found...");
    }
    // Find user by ID
    System.out.println("SEARCH BY ID:");
    int id = 1;
    foundUser = uaManager.getUserByID(id);
    if (foundUser != null){
      System.out.println(id + " found!");
      foundUser.printInfo();
    } else {
      System.out.println(id + " not found...");
    }
    // Find user by Index
    System.out.println("SEARCH BY INDEX:");
    uaManager.getUserByIndex(1).printInfo();

    // Test adding duplicate User
    System.out.println(uaManager.addUser("davey123", "pwd", "Dave", "Rich", "dave@gmail.com", "(406)555-1209", false));

    // Test account access
    System.out.println("Does (1,1) has access? " + uaManager.hasAccess(1,1));
    System.out.println("Does (1,3) has access? " + uaManager.hasAccess(1,3));
  }
}
