public class Transaction{
  private int id, int acctID;
  private double amount;

  public Transaction(int id, Account acctID, double amount){
    this.id = id;
    this.acctID = acctID;
    this.amount = amount;
  }

  public int getID(){
    return id;
  }
  public void setID(int id){
    this.id = id;
  }
  public int getAcctID(){
    return acctID;
  }
  public void setAcctID(int acctID){
    this.acctID = acctID;
  }
  public double getAmount(){
    return amount;
  }
  public void setAmount(double amount){
    this.amount = amount;
  }
}
