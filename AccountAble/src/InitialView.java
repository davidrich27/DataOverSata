import javax.swing.*;
import java.awt.*;

public class InitialView {
  JFrame frame;
  JPanel mainPanel;
  JPanel header, footer, content, right, left;
  JPanel loginPanel;

  JButton loginBtn, addAcctBtn, delAcctBtn, openAcctBtn;
  JTable acctTbl;

  JScrollPane scrollPane;

  public InitialView(){
    initUI();
  }

  private void initUI(){
    buildPanels();
    buildFrames();
  }

  private void buildPanels(){
    mainPanel = new JPanel(new BorderLayout(5, 5)); // top-level panel

    // mainPanel components
    header = new JPanel(new BorderLayout(5, 5));
      header.setBorder(BorderFactory.createTitledBorder(""));
      header.add(new JLabel("Welcome, User!"), BorderLayout.LINE_START);
    footer = new JPanel(new BorderLayout(5, 5));
      footer.setBorder(BorderFactory.createTitledBorder(""));
      footer.add(new JLabel("Developed by DATA OVER SATA"), BorderLayout.LINE_END);
    content = new JPanel(new BorderLayout(5, 5));
      content.setBorder(BorderFactory.createTitledBorder(""));

    // content components
    right = new JPanel(new BorderLayout(5, 5));
      right.setBorder(BorderFactory.createTitledBorder(""));
      buildTable();
      right.add(scrollPane, BorderLayout.CENTER);

    left = new JPanel();
      left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
      left.setBorder(BorderFactory.createTitledBorder(""));
      buildButtons();
      left.add(Box.createVerticalStrut(20));
      left.add(openAcctBtn);
      left.add(Box.createVerticalStrut(20));
      left.add(addAcctBtn);
      left.add(Box.createVerticalStrut(20));
      left.add(delAcctBtn);
      left.add(Box.createVerticalStrut(20));

    // header components
    loginPanel = new JPanel(new BorderLayout(5, 5));
      loginBtn = new JButton("Logout");
      loginPanel.add(loginBtn, BorderLayout.NORTH);
      loginPanel.add(new JLabel("Not You?"), BorderLayout.SOUTH);

    // Add all subpanels
    mainPanel.add(header, BorderLayout.NORTH);
    mainPanel.add(content, BorderLayout.CENTER);
    mainPanel.add(footer, BorderLayout.SOUTH);
      header.add(loginPanel, BorderLayout.EAST);
      content.add(left, BorderLayout.WEST);
      content.add(right, BorderLayout.CENTER);
  }
  private void buildFrames(){
    frame = new JFrame("RobynSoft");
    frame.pack();
    frame.add(mainPanel);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.setBackground(Color.GRAY);
  }

  // JTable Bullshit
  private void buildTable(){
    Object rowData[][] = { { "12345", "Master Account", 12.00, "This is all my Money."},
                       { "54321", "Offshore Savings", 9999999.99, "shhh...."} };
    Object columnNames[] = { "Account ID", "Account Name", "Account Balance", "Account Description"};
    acctTbl = new JTable(rowData, columnNames);
    //acctTbl.setModel(new MyTableModel());
    scrollPane = new JScrollPane(acctTbl);
  }

  private void buildButtons(){
    openAcctBtn = new JButton("Open Selected Account");
    addAcctBtn = new JButton("Add New Account");
    delAcctBtn = new JButton("Delete Selected Account");
  }

  public static void main(String[] args){
    InitialView launcher = new InitialView();
  }
}

/***** Makes Table NON_EDITABLE
// public class MyTableModel extends AbstractTableModel {
//   public boolean isCellEditable(int row, int column){
//     return false;
//   }
// }
