package assignment2;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.lang.*;

import java.util.ArrayList;

public class Assignment2 extends Application implements EventHandler<ActionEvent> {
    private  Scene home,addScene,depositScene,withdrawScene,listScene,transferScene;
    Stage window;  // represents main Stage globally
    Button btnAddMenu,btnDepositMenu,btnWithdrawMenu,btnTransferMenu,btnListMenu,btnAdd,btnHome,btnAddHome,btnListHome,btnDeposit, btnDepositHome, btnWithdraw;
    TextField custName,custAccNum,custBalance,txtAccountNum, txtDeposit, txtWithdrawAccount, txtWithdraw;
    TextArea accountList;
    TextField tfFrom, tfTo, tfAmount;
    Button btnTransfer, btnBack;
    VBox transferLayout;

    private Bank trustyBank = new Bank();


    public void init(){

    }

    public void start(Stage primaryStage){
        window = primaryStage;

        // setting up Home Scene
        Label lblHomeMenu = new Label("Welcome to Trusty Bank. Please select an option from below");
        btnAddMenu = new Button("Add");btnAddMenu.setOnAction(this);btnAddMenu.setMaxWidth(Double.MAX_VALUE);
        btnDepositMenu = new Button("Deposit");btnDepositMenu.setOnAction(this);btnDepositMenu.setMaxWidth(Double.MAX_VALUE);
        btnWithdrawMenu = new Button("Withdraw");btnWithdrawMenu.setOnAction(this);btnWithdrawMenu.setMaxWidth(Double.MAX_VALUE);
        btnTransferMenu = new Button("Transfer");btnTransferMenu.setOnAction(this);btnTransferMenu.setMaxWidth(Double.MAX_VALUE);
        btnListMenu = new Button("List");btnListMenu.setOnAction(this);btnListMenu.setMaxWidth(Double.MAX_VALUE);
        VBox homeLayout = new VBox();
        homeLayout.setAlignment(Pos.CENTER);
        homeLayout.getChildren().addAll(lblHomeMenu,btnAddMenu,btnDepositMenu,btnWithdrawMenu,btnTransferMenu,btnListMenu);
        home = new Scene(homeLayout,500,500);


        // setting up Add Scene
        Label lblName =new Label("Name:");
        custName = new TextField();
        Label lblAccNum =new Label("Account#:");
        custAccNum=new TextField();
        Label lblBalance =new Label("Balance:");
        custBalance = new TextField();
        btnAdd = new Button("Add Account");btnAdd.setOnAction(this);
        btnAddHome = new Button("Back");btnAddHome.setOnAction(this);
        VBox addLayout =new VBox();
        addLayout.getChildren().addAll(lblName,custName,lblAccNum,custAccNum,lblBalance,custBalance,btnAdd,btnAddHome);
        addScene = new Scene(addLayout,500,500);

        // setting up Deposit Scene
        Label lblAccountNum = new Label("Account Number: ");
        txtAccountNum = new TextField();
        Label lblDeposit = new Label("Deposit Amount: ");
        txtDeposit = new TextField();
        btnDeposit = new Button("Deposit");btnDeposit.setOnAction(this);
        btnDepositHome = new Button("Back");btnDepositHome.setOnAction(this);
        VBox depositLayout = new VBox();
        depositLayout.getChildren().addAll(lblAccountNum, txtAccountNum,lblDeposit,txtDeposit,btnDeposit,btnDepositHome);
        depositScene = new Scene(depositLayout, 500,500);

        // setting up Withdraw Scene
        Label lblWithdrawAccount = new Label("Account Number: ");
        txtWithdrawAccount = new TextField();
        Label lblWithdraw = new Label("Withdraw Amount: ");
        txtWithdraw = new TextField();
        btnWithdraw = new Button("Withdraw");btnWithdraw.setOnAction(this);
        VBox withdrawLayout = new VBox();
        btnHome = new Button("Back"); btnHome.setOnAction(this);
        withdrawLayout.getChildren().addAll(lblWithdrawAccount,txtWithdrawAccount,lblWithdraw,txtWithdraw,btnWithdraw,btnHome);
        withdrawScene = new Scene(withdrawLayout,500,500);

        // setting up Transfer scene
        Label lblFrom = new Label("From Account #:");
        Label lblTo = new Label("To Account #:");
        Label lblAmount = new Label("Tranfer Amount:");
        tfFrom = new TextField();
        tfTo = new TextField();
        tfAmount = new TextField();

        btnTransfer = new Button("Transfer");
        btnBack = new Button("Back");
        btnTransfer.setOnAction(this);
        btnBack.setOnAction(this);

        transferLayout = new VBox();
        transferLayout.getChildren().addAll(lblFrom, tfFrom, lblTo, tfTo, lblAmount, tfAmount, btnTransfer, btnBack);

        transferScene = new Scene(transferLayout, 500, 500);

        // setting up List Scene
        Label lblShow = new Label("List of accounts...");
        accountList = new TextArea();
        accountList.setEditable(false);
        accountList.setPrefSize(400, 300);
        btnListHome = new Button("Back");btnListHome.setOnAction(this);btnListHome.setMaxWidth(Double.MAX_VALUE);
        VBox listLayout =new VBox();
        listLayout.getChildren().addAll(lblShow,accountList,btnListHome);
        listScene = new Scene (listLayout,500,500);

        window.setScene(home);
        window.show();
    }

    public void stop(){

    }


    public void handleAdd(){
        long accNum = Long.valueOf(custAccNum.getText());
        String owner = custName.getText();
        double bal = Double.valueOf(custBalance.getText());
        trustyBank.addAccount(accNum,bal,owner);
    }
    public void handleDeposit() {
        long accNum = Long.valueOf(txtAccountNum.getText());
        double amt = Double.valueOf(txtDeposit.getText());
        trustyBank.depositAccount(accNum,amt);
    }



    public void handleTransfer() {
        System.out.println(tfAmount.getText() + " " + tfFrom.getText() + " " + tfTo.getText());
        boolean ok = trustyBank.transfer(Long.valueOf(tfFrom.getText()), Long.valueOf(tfTo.getText()), Double.valueOf(tfAmount.getText()));
        if (ok) {
            System.out.println("Successful transfer money");
        } else {
            System.out.println("Failed to transfer money");
        }
    }

    public void handleWithdraw() {
        boolean ok = trustyBank.withdrawAccount(Long.valueOf(txtWithdrawAccount.getText()), Double.valueOf(txtWithdraw.getText()));
        if (ok) {
            System.out.println("Successful withdraw money");
        } else {
            System.out.println("Failed to withdraw money");
        }

    }

    public void handleList() {
        accountList.setText(trustyBank.printAccounts());
    }


    public void handle(ActionEvent e){

        if (e.getSource()==btnAddMenu){
            System.out.println("add Menu btn pressed (on menu scene)");
            window.setScene(addScene);
        } else if (e.getSource()==btnListMenu){
            System.out.println("list accounts btn pressed (on menu scene)");
            this.handleList();
            window.setScene(listScene);
        } else if (e.getSource()==btnHome||e.getSource()==btnAddHome||e.getSource()==btnListHome||e.getSource()==btnBack|| e.getSource() == btnDepositHome){
            System.out.println("add account btn pressed (on add scene or list scene)");
            window.setScene(home);
        } else if (e.getSource() == btnTransferMenu) {
            window.setScene(transferScene);
        } else if (e.getSource()==btnDepositMenu){
            System.out.println("Deposit Menu btn pressed");
            window.setScene(depositScene);
        } else if (e.getSource()==btnWithdrawMenu){
            System.out.println("Withdraw menu btn pressed");
            window.setScene(withdrawScene);
        } else if (e.getSource()==btnTransfer) {

            this.handleTransfer();
        } else if (e.getSource() == btnWithdraw) {
            this.handleWithdraw();

        } else if(e.getSource()==btnAdd){
            this.handleAdd();
        } else if(e.getSource()==btnDeposit){
            this.handleDeposit();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}