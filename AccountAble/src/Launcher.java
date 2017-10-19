public class Launcher{
  public static void main(String[] args){
    Model model = new Model();                        // Current Model
    View view = new View();                           // Current View
    Controller controller = new Controller(model, view);         // Current Controller (empty)
  }
}
