package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EmployeeView extends JFrame {
    private JPanel panel = new JPanel();

    private JButton createClientButton = new JButton("CREATE");
    private JButton updateClientButton = new JButton("UPDATE");
    private JButton viewClientButton = new JButton("VIEW");

    private JButton createAccountButton = new JButton("CREATE");
    private JButton updateAccountButton = new JButton("UPDATE");
    private JButton deleteAccountButton = new JButton("DELETE");
    private JButton viewAccountButton = new JButton("VIEW");

    private JButton clientsButton = new JButton("CLIENTS");
    private JButton accountsButton = new JButton("ACCOUNTS");

    private JButton transferButton = new JButton("TRANSFER");
    private JButton billButton = new JButton("BILL");

    private JButton backButton = new JButton("BACK");

    private JLabel clientLabel = new JLabel("CLIENT         ID            NAME                               SSN                           ADDRESS                      ");
    private JLabel accountLabel = new JLabel("ACCOUNT       ID            TYPE                              AMOUNT                        CLIENT                       DATE = NOW");
    private JLabel operationLabel = new JLabel("OPERATIONS                AMOUNT                          ACCOUNT1                       ACCOUNT2");

    private JTextField createName = new JTextField();
    private JTextField createSsn = new JTextField();
    private JTextField createAddress = new JTextField();

    private JTextField updateIdClient = new JTextField();
    private JTextField updateName = new JTextField();
    private JTextField updateAddress = new JTextField();

    private JTextField viewIdClient = new JTextField();
    private JTextField viewClient = new JTextField();

    private JTextField createType = new JTextField();
    private JTextField createAmount = new JTextField();
    private JTextField createClient = new JTextField();
    private JTextField createDate = new JTextField();

    private JTextField updateIdAccount = new JTextField();
    private JTextField updateType = new JTextField();
    private JTextField updateAmount = new JTextField();

    private JTextField deleteId = new JTextField();

    private JTextField viewIdAccount = new JTextField();
    private JTextField viewAccount = new JTextField();

    private JTextField client = new JTextField();
    private JTextField account1 = new JTextField();
    private JTextField account2 = new JTextField();
    private JTextField account = new JTextField("AccountId");
    private JTextField amount = new JTextField("Amount");

    private JTextArea textArea1 = new JTextArea();
    private JTextArea textArea2 = new JTextArea();

    public EmployeeView(){
        add(panel);
        panel.setLayout(null);
        this.setTitle("EMPLOYEE");
        setComponents();
        addComponents();
        jFrameSetup();
    }

    private void jFrameSetup(){
        setSize(800,1000);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    private void setComponents(){
        createClientButton.setBounds(650, 50, 100, 20);
        updateClientButton.setBounds(650, 90, 100, 20);
        viewClientButton.setBounds(650, 130, 100, 20);

        createAccountButton.setBounds(650, 170, 100, 20);
        updateAccountButton.setBounds(650, 210, 100, 20);
        deleteAccountButton.setBounds(650, 250, 100, 20);
        viewAccountButton.setBounds(650, 290, 100, 20);

        transferButton.setBounds(650, 360, 100, 20);
        billButton.setBounds(650, 400, 100, 20);

        clientsButton.setBounds(20, 800, 100, 20);
        accountsButton.setBounds(380, 800, 100, 20);
        backButton.setBounds(650, 800, 100, 20);

        clientLabel.setBounds(20, 20, 700, 20);
        accountLabel.setBounds(20, 150, 700, 20);
        operationLabel.setBounds(20, 330, 700, 20);

        createName.setBounds(140, 50, 100, 20);
        createSsn.setBounds(260, 50, 100, 20);
        createAddress.setBounds(380, 50, 100, 20);

        updateIdClient.setBounds(20, 90, 100, 20);
        updateName.setBounds(140, 90, 100, 20);
        updateAddress.setBounds(380, 90, 100, 20);

        viewIdClient.setBounds(20, 130, 100, 20);
        viewClient.setBounds(140, 130, 460, 20);

        createType.setBounds(140, 170, 100, 20);
        createAmount.setBounds(260, 170, 100, 20);
        createClient.setBounds(380, 170, 100, 20);
        createDate.setBounds(500, 170, 100, 20);

        updateIdAccount.setBounds(20, 210, 100, 20);
        updateType.setBounds(140, 210, 100, 20);
        updateAmount.setBounds(260, 210, 100, 20);

        deleteId.setBounds(20, 250, 100, 20);

        viewIdAccount.setBounds(20, 290, 100, 20);
        viewAccount.setBounds(140, 290, 460, 20);

        client.setBounds(140, 360, 100, 20);
        account1.setBounds(260, 360, 100, 20);
        account2.setBounds(380, 360, 100, 20);
        account.setBounds(140, 400, 100, 20);
        amount.setBounds(260, 400, 100, 20);

        textArea1.setBounds(20, 450, 300, 300);
        textArea2.setBounds(400, 450, 300, 300);
    }
    private void addComponents(){
        panel.add(createClientButton);
        panel.add(updateClientButton);
        panel.add(viewClientButton);

        panel.add(createAccountButton);
        panel.add(updateAccountButton);
        panel.add(deleteAccountButton);
        panel.add(viewAccountButton);
        panel.add(clientsButton);
        panel.add(accountsButton);
        panel.add(backButton);

        panel.add(transferButton);
        panel.add(billButton);

        panel.add(clientLabel);
        panel.add(accountLabel);
        panel.add(operationLabel);

        panel.add(createName);
        panel.add(createSsn);
        panel.add(createAddress);

        panel.add(updateIdClient);
        panel.add(updateName);
        panel.add(updateAddress);

        panel.add(viewIdClient);
        panel.add(viewClient);

        panel.add(createType);
        panel.add(createClient);
        panel.add(createAmount);

        panel.add(updateIdAccount);
        panel.add(updateType);
        panel.add(updateAmount);

        panel.add(deleteId);

        panel.add(viewIdAccount);
        panel.add(viewAccount);

        panel.add(client);
        panel.add(account1);
        panel.add(account2);
        panel.add(account);
        panel.add(amount);

        panel.add(textArea1);
        panel.add(textArea2);
    }

    public void setCreateClientListener(ActionListener a){
        createClientButton.addActionListener(a);
    }
    public void setUpdateClientListener(ActionListener a){
        updateClientButton.addActionListener(a);
    }
    public void setViewClientListener(ActionListener a){
        viewClientButton.addActionListener(a);
    }

    public void setCreateAccountListener(ActionListener a){
        createAccountButton.addActionListener(a);
    }
    public void setUpdateAccountListener(ActionListener a){
        updateAccountButton.addActionListener(a);
    }
    public void setDeleteAccountListener(ActionListener a){
        deleteAccountButton.addActionListener(a);
    }
    public void setViewAccountListener(ActionListener a){
        viewAccountButton.addActionListener(a);
    }

    public void setTransferListener(ActionListener a){
        transferButton.addActionListener(a);
    }
    public void setBilListener(ActionListener a){
        billButton.addActionListener(a);
    }

    public void setClientsButtonListener(ActionListener a){
        clientsButton.addActionListener(a);
    }
    public void setAccountsButtonListener(ActionListener a){
        accountsButton.addActionListener(a);
    }
    public void setBackButtonListener(ActionListener a){
        backButton.addActionListener(a);
    }

    public String getCreateName(){
        return createName.getText();
    }
    public String getCreateSsn(){
        return createSsn.getText();
    }
    public String getCreateAddress(){
        return createAddress.getText();
    }

    public String getUpdateClientId() {
        return updateIdClient.getText();
    }
    public String getUpdateName() {
        return updateName.getText();
    }
    public String getUpdateAddress() {
        return updateAddress.getText();
    }

    public JTextField getClientView(){
        return viewClient;
    }
    public String getViewClientId() {
        return viewIdClient.getText();
    }

    public String getViewAccountId(){ return viewIdAccount.getText(); }
    public JTextField getViewAccount() {
        return viewAccount;
    }

    public String getCreateType(){
        return createType.getText();
    }
    public String getCreateAmount(){
        return createAmount.getText();
    }
    public String getCreateAccountClient(){
        return createClient.getText();
    }

    public String getUpdateAccountId() {
        return updateIdAccount.getText();
    }
    public String getUpdateAccountType() {
        return updateType.getText();
    }
    public String getUpdateAccountAmount() {
        return updateAmount.getText();
    }

    public String getDeleteAccount(){
        return deleteId.getText();
    }

    public String getClientTransfer() {
        return client.getText();
    }
    public String getAccount1Transfer() {
        return account1.getText();
    }
    public String getAccount2Transfer() {
        return account2.getText();
    }

    public String getAccount3Transfer() {
        return account.getText();
    }
    public String getAmount3Transfer() {
        return amount.getText();
    }

    public JTextArea getTextArea1(){
        return textArea1;
    }
    public JTextArea getTextArea2(){
        return textArea2;
    }
}
