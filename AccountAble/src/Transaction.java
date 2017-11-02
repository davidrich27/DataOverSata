public class Transaction{
  int id;
  Account account;
  double amount;

  public Transaction(int id, Account account, double amount){
    this.id = id;
    this.account = account;
    this.amount = amount;
  }
}
