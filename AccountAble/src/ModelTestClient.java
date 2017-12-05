import model.basic.*;
import model.manager.*;
import model.master.*;

import java.time.LocalDateTime;
import java.time.LocalDate;

public class ModelTestClient{
  public static void main(String[] args){
    ModelTXT model = new ModelTXT();

    model.printInfo();
    LocalDateTime ldt = LocalDateTime.now();
    LocalDate ld = LocalDate.now();
    // Add Users
    model.addNewUser("davey123", "pwd", "Dave", "Rich", "dave@gmail.com", "(406)555-1209", false);
    model.addNewUser("trish123", "pwd", "Patricia", "Duce", "p.Duce@gmail.com", "(406)555-1234", false);
    model.addNewUser("admin", "pwd", "Robyn", "Berg", "robyn@gmail.com", "(406)777-4567", true);
    model.addNewUser("csadmin", "csci323", "Default", "Default", "csadmin323@umt.edu", "(790)604-4060", true);
    // Add Accounts
    model.addNewAcct("Master Account", "Where all the Money Goes...");
    model.addNewAcct("Offshore Account", "Shhh...");
    model.addNewAcct("Trish and Cassen's Camp Account", "For the Kidz!");
    // Add Transactions(int acctId, int userId, int codeId, double transSubTotal, String otherParty, String descr, LocalDateTime date, boolean isExpense)
    model.addNewTrans(1, 1, 501, 1000.00, "University of Montana", "Initial Deposit", ldt, ld, false);
    model.addNewTrans(2, 2, 601, 10000.00, "Unknown", "Secret Stash", ldt.minusDays(3), ld, false);
    model.addNewTrans(2, 1, 401, 500.00, "Some Guy", "Owed a Dude 500 bucks", ldt.plusDays(5), ld, true);
    // Add FeeTypes(String name, String descr, double amt, boolean isPercent, boolean isAdditional, boolean isCustom)
    model.addNewFeeType("Standard Credit Card Fee", "Use when accepting money by VISA or Mastercard.", 4.00, true, true, false);
    model.addNewFeeType("Standard UM Charge", "Use for all income.", 8.00, true, true, false);
    // Add Codes(String name, String descr)
    model.addNewCode("50001", "Test Code 1");
    model.addNewCode("50002", "Test Code 2");
    // Add User_Account
    model.addNewUser_Acct(1, 1);
    model.addNewUser_Acct(2, 2);
    model.addNewUser_Acct(2, 3);
    model.addNewUser_Acct(3, 3);

    model.printInfo();
    model.saveAll();

    boolean test = model.testLogin("davey123", "pwd");  // Should be true
    System.out.println("Test Login: " + test);

    User user = model.uaManager.getUserByID(2);
    user.printInfo();
  }
}
