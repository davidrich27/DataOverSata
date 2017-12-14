package model.basic;

import java.util.*;

public class Link implements Comparable<Link>{    // Note: Maybe convert to Generics?
  private Integer id;
  private Integer idA, idB;

  public Link(int id, int idA, int idB){
    this.id = id;
    this.idA = idA;
    this.idB = idB;
  }
  public Link(int id){  // Dummy Search Instance
    this.id = id;
    this.idA = -1;
    this.idB = -1;
  }
  // SETTERS & GETTERS
  public Integer getId(){
    return id;
  }
  public void setId(int id){
    this.id = id;
  }
  public Integer getIdA(){
    return idA;
  }
  public void setIdA(int idA){
    this.idA = idA;
  }
  public Integer getIdB(){
    return idB;
  }
  public void setIdB(int idB){
    this.idB = idB;
  }
  // COMPARATORS
  public int compareTo(Link that){    // Default: Sort By Link ID
    return this.id.compareTo(that.getId());
  }
  public static Comparator<Link> BY_A(){  // Compares two User by idA, idB
    return new Comparator<Link>() {
      public int compare(Link link1, Link link2) {
        int testA = link1.getIdA().compareTo(link2.getIdA());
        if (testA != 0){
          return testA;
        } else {
          int testB = link1.getIdB().compareTo(link2.getIdB());
          return testB;
        }
      }
    };
  }
  public static Comparator<Link> BY_B(){  // Compares two User by idB, idA
    return new Comparator<Link>() {
      public int compare(Link link1, Link link2) {
        int testB = link1.getIdB().compareTo(link2.getIdB());
        if (testB != 0){
          return testB;
        } else {
          int testA = link1.getIdA().compareTo(link2.getIdA());
          return testA;
        }
      }
    };
  }
  public boolean equals(Link that){       // If IDs are equal, or if both idA and idB are equal, then Links are equal
    boolean testID = this.getId().equals(that.getId());
    if (testID){
      return true;
    }
    boolean testA = this.getIdA().equals(that.getIdA());
    boolean testB = this.getIdB().equals(that.getIdB());
    if (testA && testB){
      return true;
    }
    return false;
  }
  // Print Demo
  public void printInfo(){
    System.out.println("LINK DETAILS: ");
    System.out.println("Link ID : " + id + ", Link Pair : " + idA + " - " + idB);
  }
  // Unit Test
  public static void main(String[] args){
    ArrayList<Link> testList = new ArrayList<Link>();
      Link testLink = new Link(1, 4, 3);
      testList.add(testLink);
      testLink = new Link(2, 3, 4);
      testList.add(testLink);
      testLink = new Link(5, 12, 1);
      testList.add(testLink);
      testLink = new Link(7, 12, 1);
      testList.add(testLink);
      testLink = new Link(2, 1, 1);
      testList.add(testLink);
    System.out.println("INPUT ORDER: ");
    for (Link link : testList){
      link.printInfo();
    }
    // Equality Test
    System.out.print("Are 2 and 3 equal? "); // Should be true
    System.out.println(testList.get(2).equals(testList.get(3)));
    System.out.print("Are 1 and 4 equal? "); // Should be true
    System.out.println(testList.get(1).equals(testList.get(4)));
    System.out.print("Are 0 and 1 equal? "); // Should be false
    System.out.println(testList.get(0).equals(testList.get(1)));
    // Sort Test
    Collections.sort(testList);
    System.out.println("DEFAULT ORDER (BY ID): ");
    for (Link link : testList){
      link.printInfo();
    }
    Collections.sort(testList, Link.BY_A());
    System.out.println("ORDER BY A: ");
    for (Link link : testList){
      link.printInfo();
    }
    Collections.sort(testList, Link.BY_B());
    System.out.println("ORDER BY B: ");
    for (Link link : testList){
      link.printInfo();
    }
  }
}
