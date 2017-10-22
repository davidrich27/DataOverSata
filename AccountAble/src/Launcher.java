public class Launcher{
  static User[] users;
  static Account[] accts;

  public static void main(String[] args){
    demoData();

    Model model = new Model(users, accts);                        // Current Model
    View view = new View();                                       // Current View
    Controller controller = new Controller(model, view);          // Current Controller
  }

  private static void demoData() {
 /*   users = {
      new User("admin", "12345", "Robyn", "Berg", true),
      new User("underling", "password1", "Johnny", "Rotten", false)
    };
    accts = {
      new Account(10001, "Johnny's Account", "The Account of John.", 1000000.01, users[1]),
      new Account(12345, "Master Account", "This is where all the Money is.", 12.00, users[0]),
      new Account(54321, "Offshore Savings", "Shhh...", 999999999.99, users[0])
    };*/
  }
}
