package model.basic;

import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class Transaction implements Comparable<Transaction>{
  private int id, acctId, userId, codeId;
  private double subTotal, feeTotal;
  private double acctBal;
  private String otherParty, descr;
  private LocalDateTime dateEntry;
  private LocalDate dateSale;
  private boolean isExpense, paidFee;

  // CONSTRUCTORS
  public Transaction(int id, int acctId, int userId, int codeId, double subTotal, double feeTotal, double acctBal, String otherParty, String descr, LocalDateTime dateEntry, LocalDate dateSale, boolean isExpense, boolean paidFee){
    this.id = id;
    this.acctId = acctId;
    this.userId = userId;
    this.codeId = codeId;
    this.subTotal = subTotal;
    this.feeTotal = feeTotal;
    this.acctBal = acctBal;
    this.otherParty = otherParty;
    this.descr = descr;
    this.dateEntry = dateEntry;
    this.dateSale = dateSale;
    this.isExpense = isExpense;
    this.paidFee = paidFee;
  }
  public Transaction(int id){  // Dummy Search Instance
    this.id = id;
  }

  // SETTERS & GETTERS
  public Integer getID(){
    return id;
  }
  public void setID(int id){
    this.id = id;
  }
  public Integer getAcctID(){
    return acctId;
  }
  public void setAcctID(int acctId){
    this.acctId = acctId;
  }
  public Integer getUserID(){
    return userId;
  }
  public void setUserID(int userId){
    this.userId = userId;
  }
  public Integer getCodeID(){
    return codeId;
  }
  public void setCodeID(int codeId){
    this.codeId = codeId;
  }
  public Double getSubTotal(){
    return subTotal;
  }
  public void setSubTotal(double subTotal){
    this.subTotal = subTotal;
  }
  public Double getFeeTotal(){
    return feeTotal;
  }
  public void setFeeTotal(double feeTotal){
    this.feeTotal = feeTotal;
  }
  public Double getTotal(){
    return subTotal + feeTotal;
  }
  public Double getAcctBal(){
    return acctBal;
  }
  public void setAcctBal(double acctBal){
    this.acctBal = acctBal;
  }
  public Double getPrevAcctBal(){
    return acctBal - subTotal;
  }
  public String getOtherParty(){
    return otherParty;
  }
  public void setOtherParty(String otherParty){
    this.otherParty = otherParty;
  }
  public String getDescr(){
    return descr;
  }
  public void setDescr(String descr){
    this.descr = descr;
  }
  public LocalDateTime getDateEntry(){
    return dateEntry;
  }
  public void setDateEntry(LocalDateTime date){
    this.dateEntry = dateEntry;
  }
  public LocalDate getDateSale(){
    return dateSale;
  }
  public void setDateSale(LocalDate date){
    this.dateSale = dateSale;
  }
  public Boolean getIsExpense(){
    return isExpense;
  }
  public void setIsExpense(boolean isExpense){
    this.isExpense = isExpense;
  }
  public Boolean getPaidFee(){
    return paidFee;
  }
  public void setPaidFee(boolean paidFee){
    this.paidFee = paidFee;
  }

  // COMPARATORS
  public int compareTo(Transaction that){     // Default comparator: By ID
    return this.getID().compareTo(that.getID());
  }
  public static Comparator<Transaction> BY_DATESALE_EARLIEST(){  // Compares two Trans by Date (Most Recent First)
    return new Comparator<Transaction>() {
      public int compare(Transaction a, Transaction b) {
        return a.getDateSale().compareTo(b.getDateSale());
      }
    };
  }
  public static Comparator<Transaction> BY_DATESALE_OLDEST(){  // Compares two Trans by Date (Oldest First)
    return new Comparator<Transaction>() {
      public int compare(Transaction a, Transaction b) {
        int testTime = a.getDateSale().compareTo(b.getDateSale());
        return testTime*(-1);
      }
    };
  }
  public static Comparator<Transaction> BY_ACCOUNT(){  // Compares two Trans by Acct, then Date
    return new Comparator<Transaction>() {
      public int compare(Transaction a, Transaction b) {
        int testAcct = a.getAcctID().compareTo(b.getAcctID());
        if (testAcct != 0){
          return testAcct;
        }
        int testDate = a.getDateSale().compareTo(b.getDateSale());
        return testDate;
      }
    };
  }
  public boolean equals(Transaction that){
    return this.getID().equals(that.getID());
  }
  // PRINT DEMO
  public void printInfo(){
    System.out.println("TRANSACTION DETAILS:");
    System.out.println("ID: " + id + ", Account ID: " + acctId + ", User ID: " + userId + ", Code ID: " + codeId);
    System.out.println("SubTotal: " + subTotal + ", Fee: " + feeTotal + ", Total: " + getTotal() + ", Account Balance: " + acctBal);
    System.out.println("Other Party: " + otherParty + ", Descr: " + descr);
    System.out.println("Date of Sale: " + dateSale + ", Date of Entry: " + dateEntry + ", Is it an Expense?: " + isExpense + ", Has the fee been paid?: " + paidFee);
    System.out.println("---");
  }

  // UNIT TEST
  public static void main(String[] args){
  }
}
