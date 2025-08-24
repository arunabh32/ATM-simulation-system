

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
public class Vex extends Application {
    private Atm atm;

    @Override
    public void start(Stage primaryStage){
        //initial input

        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name ");

        TextField accountField = new TextField();
        accountField.setPromptText("enter  account number");

        TextField balanceField = new TextField();
        balanceField.setPromptText("enter initial balance  ");

        Button createBtn = new Button("Create Account");
        Label infoLabel = new Label();
        //Transaction controls
        TextField amountField = new TextField();
        amountField.setPromptText("Enter amount");
//buttons
        Button depositBtn = new Button("Deposit");
        Button WithdrawBtn = new Button("Withdraw");
        Button checkBtn = new Button("Check Balance");
        Label resultlabel = new Label();





        //Createing account actions

        createBtn.setOnAction(e ->{
            
            try {

                String name = nameField.getText();

                int accNo = Integer.parseInt(accountField.getText());
                int balance = Integer.parseInt(balanceField.getText());
                atm = new Atm(name, accNo, balance);

            } catch (NumberFormatException ex) {
                infoLabel.setText("error");


            }





        });
//Deposite function 

         depositBtn.setOnAction(e ->{
            if (atm!=null){
                 try {

                    int amount =Integer.parseInt(amountField.getText());
                    atm.depamount = amount;
                    resultlabel.setText("New balanceis :"+atm.newbalance());


                
            } catch (NumberFormatException ex) {
                resultlabel.setText("Enter a valid number");
            }

            }

           
        });
        //withdraw functions 
        WithdrawBtn.setOnAction(e ->{
            if (atm != null){
                try {
                    int amount = Integer.parseInt(amountField.getText());
                    atm.witamount = amount;
                    int newBal = atm.witbalance();

                    if (newBal==-1){
                        resultlabel.setText("Insufficient Balance");
                    }else{
                        resultlabel.setText("New Balance is :"+newBal);
                    }

                    
                } catch (NumberFormatException ex) {
                    resultlabel.setText("Enter valid amount");

                }
            }
        });

        //balance checking function

        checkBtn.setOnAction(e ->{
            if (atm!= null){
                resultlabel.setText("Balance:"+ atm.Checkbalance());

            }

        });
        VBox layout = new VBox(10, nameField,accountField,balanceField,createBtn,depositBtn,WithdrawBtn,checkBtn,infoLabel,amountField,resultlabel);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding:15;");

        Scene scene = new Scene(layout,400,400);
        scene.getStylesheets().add("style.css");

        primaryStage.setTitle("Atm Simulator!");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
    public static void main(String[] args) {
        launch(args);
    }

}