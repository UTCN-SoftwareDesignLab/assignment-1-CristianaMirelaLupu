package controller;

import database.Constants;
import launcher.ComponentFactory;
import model.User;
import model.builder.UserBuilder;
import model.validation.Notification;
import repository.EntityNotFoundException;
import service.user.UserService;
import view.AdminView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminController {

    private final AdminView adminView;
    private final UserService userService;

    public AdminController(AdminView adminView, UserService userService){
        this.adminView = adminView;
        this.userService = userService;

        this.adminView.setCreateButtonListener(new CreateButtonListener());
        this.adminView.setViewButtonListener(new ViewButtonListener());
        this.adminView.setUpdateButtonListener(new UpdateButtonListener());
        this.adminView.setDeleteButtonListener(new DeleteButtonListener());
        this.adminView.setRefreshButtonListener(new RefreshButtonListener());
        this.adminView.setBackButtonListener(new BackButtonListener());
    }

    public class CreateButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {

            String username = adminView.getUsernameCreate();
            String password = adminView.getPasswordCreate();

            if(!username.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$" )){
                JOptionPane.showMessageDialog(adminView.getContentPane(), "Invalid email!");
            } else if(password.length() < 8 ){
                JOptionPane.showMessageDialog(adminView.getContentPane(), "Invalid password!");
            } else {
                Notification<User> employee = userService.findByNameAndPassword(username, password);

                if(!employee.hasErrors()){
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "Operation could not be performed!");
                } else {
                    User newEmployee = new UserBuilder()
                            .setUsername(username)
                            .setPassword(password)
                            .build();

                    userService.create(newEmployee, Constants.Roles.EMPLOYEE);
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "Successful creation!");
                }
            }
        }
    }

    public class ViewButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {
            String id = adminView.getIdView();
            User employee = null;
            try {
                employee = userService.findById(Long.parseLong(id));
            } catch (EntityNotFoundException e) {
                e.printStackTrace();
            }

            if(employee != null){
                    String text = employee.toString();
                    adminView.getFound().setText(text);
            } else {
                adminView.getFound().setText("");
                JOptionPane.showMessageDialog(adminView.getContentPane(), "Employee does not exist!");
            }
        }
    }

    public class UpdateButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {
            String id = adminView.getIdUpdate();
            String username = adminView.getUsernameUpdate();
            String password = adminView.getPasswordUpdate();

            if(!username.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$" )){
                JOptionPane.showMessageDialog(adminView.getContentPane(), "Invalid email!");
            } else if(password.length() < 8){
                JOptionPane.showMessageDialog(adminView.getContentPane(), "Invalid password!");
            } else {
                Notification<User> employee = userService.findByNameAndPassword(username, password);

                if(employee == null){
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "Employee does not exist!");
                } else {
                    try {
                        User updateEmployee = userService.findById(Long.parseLong(id));
                        updateEmployee.setUsername(username);
                        updateEmployee.setPassword(password);
                        userService.update(updateEmployee);
                        JOptionPane.showMessageDialog(adminView.getContentPane(), "Successful update!");
                    } catch (EntityNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public class DeleteButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {
            String id = adminView.getIdDelete();
            try {
                User employee = userService.findById(Long.parseLong(id));
                userService.delete(Long.parseLong(id));
                JOptionPane.showMessageDialog(adminView.getContentPane(), "Successful deletion!");
            } catch (EntityNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public class RefreshButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {

            List<User> users = userService.findAll();
            String s = "";

            for (User u: users){
                s = s + u.toString()+ "\n";
            }

            adminView.getTextArea().setText(s);
        }
    }

    public class BackButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent arg0) {

            ComponentFactory.instance(true).getAdminView().setVisible(false);
            ComponentFactory.instance(true).getLoginView().setVisible(true);
        }
    }
}
