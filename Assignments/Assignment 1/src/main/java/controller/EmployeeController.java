package controller;

import launcher.ComponentFactory;
import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import repository.EntityNotFoundException;
import service.account.AccountService;
import service.client.ClientService;
import view.EmployeeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class EmployeeController {
    private final EmployeeView employeeView;
    private final ClientService clientService;
    private final AccountService accountService;

    public EmployeeController(EmployeeView employeeView, ClientService clientService, AccountService accountService){
        this.employeeView = employeeView;
        this.clientService = clientService;
        this.accountService = accountService;

        this.employeeView.setCreateClientListener(new CreateClientButtonListener());
        this.employeeView.setUpdateClientListener(new UpdateClientButtonListener());
        this.employeeView.setViewClientListener(new ViewClientButtonListener());

        this.employeeView.setCreateAccountListener(new CreateAccountButtonListener());
        this.employeeView.setUpdateAccountListener(new UpdateAccountButtonListener());
        this.employeeView.setDeleteAccountListener(new DeleteAccountButtonListener());
        this.employeeView.setViewAccountListener(new ViewAccountButtonListener());

        this.employeeView.setTransferListener(new TransferButtonListener());
        this.employeeView.setBilListener(new BillButtonListener());

        this.employeeView.setClientsButtonListener(new ClientsButtonListener());
        this.employeeView.setAccountsButtonListener(new AccountsButtonListener());
        this.employeeView.setBackButtonListener(new BackButtonListener());
    }

    public class CreateClientButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

            String name = employeeView.getCreateName();
            String ssn = employeeView.getCreateSsn();
            String address = employeeView.getCreateAddress();

            if ((ssn.length() != 13)) {
                 System.out.println("Invalid SSN!");
               }

            Client client = null;

            try {
                 client = clientService.findBySsn(ssn);
            } catch (EntityNotFoundException e) {
                e.printStackTrace();
            }

            if(client != null){
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Operation could not be performed!");
            } else {
                Client newClient = new ClientBuilder()
                        .setName(name)
                        .setSsn(ssn)
                        .setAddress(address)
                        .build();

                clientService.create(newClient);
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Successful creation!");
            }
        }
    }

    public class UpdateClientButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            String id = employeeView.getUpdateClientId();
            String name = employeeView.getUpdateName();
            String address = employeeView.getUpdateAddress();

            Client client = null;
            try {
                client = clientService.findById(Long.parseLong(id));
            } catch (EntityNotFoundException e) {
                e.printStackTrace();
            }
            if (client == null) {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Client does not exist!");
            } else {

                try {
                    Client updateClient = clientService.findById(Long.parseLong(id));
                    updateClient.setName(name);
                    updateClient.setAddress(address);
                    clientService.update(updateClient);
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Successful update!");
                } catch (EntityNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class ViewClientButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {
            String id = employeeView.getViewClientId();

            Client client = null;
            try {
                client = clientService.findById(Long.parseLong(id));
            } catch (EntityNotFoundException e) {
                e.printStackTrace();
            }

            if(client != null){
                String text = client.toString();
                employeeView.getClientView().setText(text);
            }else{
                employeeView.getClientView().setText("");
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Client does not exist!");
            }
        }
    }

    public class CreateAccountButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {
            String type = employeeView.getCreateType();
            Float amount = Float.parseFloat(employeeView.getCreateAmount());
            Long clientId = Long.parseLong(employeeView.getCreateAccountClient());

            if (type != "debit" || type != "credit") {
                System.out.println("Invalid account!");
            }

            Client client = null;

            try {
                client = clientService.findById(clientId);
            } catch (EntityNotFoundException e) {
                e.printStackTrace();
            }

            if (client == null) {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "The client does not exist!");
            } else {
                Account newAccount = new AccountBuilder()
                        .setType(type)
                        .setAmount(amount)
                        .setCreationDate(Date.valueOf(LocalDate.now()))
                        .setClient(clientId)
                        .build();

                accountService.create(newAccount);
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Successful creation!");
            }
        }
    }

    public class UpdateAccountButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {
            String id = employeeView.getUpdateAccountId();
            String type = employeeView.getUpdateAccountType();
            Float amount= Float.parseFloat(employeeView.getUpdateAccountAmount());

            if (type != "debit" || type != "credit") {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Invalid account!");
            }

            Account updateAccount = null;
            try {
                updateAccount = accountService.findById(Long.parseLong(id));
            } catch (EntityNotFoundException e) {
                e.printStackTrace();
            }

            if (updateAccount == null) {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account does not exist!");
            }else{
                updateAccount.setType(type);
                updateAccount.setAmount(amount);

                accountService.update(updateAccount);
            }
        }
    }

    public class DeleteAccountButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {
            String id = employeeView.getDeleteAccount();
            accountService.delete(Long.parseLong(id));
        }
    }

    public class ViewAccountButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {
            String id = employeeView.getViewAccountId();
            Account account = null;
            try {
                account = accountService.findById(Long.parseLong(id));
            } catch (EntityNotFoundException e) {
                e.printStackTrace();
            }

            if(account != null){
                String text = account.toString();
                employeeView.getViewAccount().setText(text);
            }
        }
    }

    public class TransferButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {
            String amount = employeeView.getClientTransfer();
            String account1Id = employeeView.getAccount1Transfer();
            String account2Id = employeeView.getAccount2Transfer();

            Account account1 = null;
            try {
                account1 = accountService.findById(Long.parseLong(account1Id));
            } catch (EntityNotFoundException e) {
                e.printStackTrace();
            }
            Account account2 = null;
            try {
                account2 = accountService.findById(Long.parseLong(account2Id));
            } catch (EntityNotFoundException e) {
                e.printStackTrace();
            }

            if(Float.parseFloat(amount) <= account1.getAmount()){
                    account1.setAmount(account1.getAmount() - Float.parseFloat(amount));
                    account2.setAmount(account2.getAmount() + Float.parseFloat(amount));
                    accountService.update(account1);
                    accountService.update(account2);
            } else {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Insufficient money!");
            }
        }
    }

    public class BillButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {
            String amount = employeeView.getAmount3Transfer();
            String account3Id = employeeView.getAccount3Transfer();

            Account account3 = null;
            try {
                account3 = accountService.findById(Long.parseLong(account3Id));
            } catch (EntityNotFoundException e) {
                e.printStackTrace();
            }

            if(Float.parseFloat(amount) <= account3.getAmount()){

                account3.setAmount(account3.getAmount() - Float.parseFloat(amount));
                accountService.update(account3);
            } else {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Insufficient money!");
            }
        }
    }

    public class ClientsButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {

            List<Client> clients = clientService.findAll();
            String s = "";

            for (Client c: clients){
                s = s + c.toString()+ "\n";
            }

            employeeView.getTextArea1().setText(s);
        }
    }

    public class AccountsButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {

            List<Account> accounts = accountService.findAll();
            String s = "";

            for (Account a: accounts){
                s = s + a.toString()+ "\n";
            }

            employeeView.getTextArea2().setText(s);
        }
    }

    public class BackButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {

            ComponentFactory.instance(true).getEmployeeView().setVisible(false);
            ComponentFactory.instance(true).getLoginView().setVisible(true);
        }
    }

}
