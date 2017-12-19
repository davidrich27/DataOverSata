import model.basic.*;
import model.manager.*;
import model.master.*;
import model.security.*;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.security.spec.InvalidKeySpecException;
import java.security.SecureRandom;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.xml.bind.DatatypeConverter;

// encryption libraries
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class ModelTestClient{
  public static void main(String[] args)
  throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeySpecException, PasswordHasher.InvalidHashException, PasswordHasher.CannotPerformOperationException{
    passwordTest1();
  }

  static void replaceTest() {
    String testStr = "Hey this is the replace-word!";
    testStr = testStr.replace("replace-word", "answer");
    System.out.println(testStr);
  }

  static void convertByteStrByte() {
    try{

      KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
      SecretKey myDesKey = keygenerator.generateKey();

    } catch(NoSuchAlgorithmException e){
      e.printStackTrace();
    }
  }

  static void encryptionTest1() {

    try{

		    KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
        SecretKey myDesKey = keygenerator.generateKey();
        System.out.println("My DES Key is: " + myDesKey);

		    Cipher desCipher;

		    // Create the cipher
		    desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

		    // Initialize the cipher for encryption
		    desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);

		    //sensitive information
		    byte[] text = "No body can see me".getBytes();

		    System.out.println("Text [Byte Format] : " + text);
		    System.out.println("Text : " + new String(text));

		    // Encrypt the text
		    byte[] textEncrypted = desCipher.doFinal(text);

		    System.out.println("Text Encryted : " + textEncrypted);

		    // Initialize the same cipher for decryption
		    desCipher.init(Cipher.DECRYPT_MODE, myDesKey);

		    // Decrypt the text
		    byte[] textDecrypted = desCipher.doFinal(textEncrypted);

		    System.out.println("Text Decryted : " + new String(textDecrypted));

		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}catch(NoSuchPaddingException e){
			e.printStackTrace();
		}catch(InvalidKeyException e){
			e.printStackTrace();
		}catch(IllegalBlockSizeException e){
			e.printStackTrace();
		}catch(BadPaddingException e){
			e.printStackTrace();
		}

  }

  static void passwordTest1()
  throws NoSuchAlgorithmException, InvalidKeySpecException, PasswordHasher.InvalidHashException, PasswordHasher.CannotPerformOperationException{
    String pwd1 = "csci323";
    String pwd2 = "this is a test";

    String pwd1Hash = PasswordHasher.createHash(pwd1);
    System.out.println("pwd1 is: " + pwd1);
    System.out.println("pwd1Hash is: " + pwd1Hash);

    boolean test1 = PasswordHasher.verifyPassword(pwd1, pwd1Hash);
    System.out.println("testing pwd1 against pwd1Hash: " + test1);

    boolean test2 = PasswordHasher.verifyPassword(pwd2, pwd1Hash);
    System.out.println("testing pwd2 against pwd1Hash: " + test2);
  }

  static void modelTest1()
  throws NoSuchAlgorithmException, InvalidKeySpecException, PasswordHasher.InvalidHashException, PasswordHasher.CannotPerformOperationException {
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
