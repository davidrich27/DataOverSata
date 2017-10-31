public class Controller{
  ModelTXT model;
  View view;
  User loggedIn;
  ArrayList<Account> accts;

  public Controller(ModelTXT model, View view){
    this.model = model;
    this.view = view;
  }
}
