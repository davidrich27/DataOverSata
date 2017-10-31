public class Launcher{
  static User[] users;
  static Account[] accts;

  public static void main(String[] args){
    demoData();

    ModelTXT model = new ModelTXT();                              // Current Model
    View view = new View();                                       // Current View
    Controller controller = new Controller(model, view);          // Current Controller

    controller.start();
  }

  private static void demoData() {
  }
}
