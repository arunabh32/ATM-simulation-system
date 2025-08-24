import java.util.InputMismatchException;
import java.util.Scanner;

interface Dep{
    int newbalance();
}
interface Witdraw{
    int witbalance();
}
//abstarct class for account details 

abstract class Account  {

    protected String name;
    protected int accountno;
    protected int balance;
//constructor for account info
    public Account (String name ,int accountno,int balance){
        this.name=name;
        this.accountno=accountno;
        this.balance=balance;
    }//methods for the intial display requirements
        int Checkbalance()  {

            return balance;
        }
        String Displayinfo(){

            return name+" "+accountno;
        }

}
//concrete class for atm obejcts
class Atm extends Account implements Dep,Witdraw{
    int witamount;//amount to be withdrawn
    int depamount;//amount to be deposited 

    

    public Atm(String name ,int accountno,int balance){
//constructor for atm withdraw and depsoite info
        super(name,accountno,balance);
        this.depamount=0;
        this.witamount=0;

    }//implementation of the interfaces with data from account class:

    @Override

   public String Displayinfo(){

        return super.Displayinfo();
    }
    @Override

    public int Checkbalance(){

        return super.Checkbalance();
    }
    @Override

    public int newbalance(){

        balance+=depamount;
        return balance;
    }
    @Override

    public int witbalance(){
        if(witamount>balance){

            System.out.println("insufficient balance");
        }else{
            balance-=witamount;
            
        }return balance;
    }
   
}
public class Vex{
    public static void main (String[]args){

       try(Scanner sc= new Scanner(System.in)){

        try {
            System.out.println("Enter your name");
            String name = sc.nextLine();

            System.out.println("ENTER account number");
            int accountno=sc.nextInt();

            System.out.println("Enter your balance");
            int balance=sc.nextInt();

            Atm atm =new Atm(name,accountno,balance);
            System.out.println("Account created as "+atm.Displayinfo());

            try {
                System.out.println("Choice 1 :Deposite");
                System.out.println("Choice 2: Withdraw");
                System.out.println("Choice 3:Check balance");
                System.out.println("Enter choice");

                int choice=sc.nextInt();
                boolean running = true;
                while (running){
                    switch (choice) {

                        case 1 -> {
                            System.out.println("Enter the amount");
                            int depamount=sc.nextInt();
                            atm.depamount=depamount;
                            System.out.println("Your new balance is "+atm.newbalance());
                        }

                        case 2 -> {
                            System.out.println("Eneter amount");
                            int witamount=sc.nextInt();
                            atm.witamount=witamount;
                            System.out.println("Your new balance is "+ atm.witbalance());
                        }

                        case 3 -> {
                            atm.balance=balance;
                            System.out.println("your balance is "+atm.Checkbalance());
                        }

                        case 4 -> {
                            running = false;
                            System.out.println("Exiting");
                        }
                        
                        
                    }

                }
                
            } catch (InputMismatchException e) {

                System.out.println("Eroor");
            }
            
        } catch (InputMismatchException e) {

            System.out.println("Eneter valid choice");

        }


       }

    }


}


