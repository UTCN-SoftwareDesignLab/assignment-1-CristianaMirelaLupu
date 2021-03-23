package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AdminView extends JFrame{

    private JPanel panel = new JPanel();
    private JButton createButton = new JButton("CREATE");
    private JButton viewButton = new JButton("VIEW");
    private JButton updateButton = new JButton("UPDATE");
    private JButton deleteButton = new JButton("DELETE");
    private JButton refreshButton = new JButton("REFRESH");
    private JButton backButton = new JButton("BACK");

    private JLabel idLabel = new JLabel("ID");
    private JLabel usernameLabel = new JLabel("USERNAME");
    private JLabel passwordLabel = new JLabel("PASSWORD");

    private JTextField usernameCreate = new JTextField();
    private JTextField passwordCreate = new JTextField();

    private JTextField idRead = new JTextField();
    private JTextField found = new JTextField();

    private JTextField idUpdate = new JTextField();
    private JTextField usernameUpdate = new JTextField();
    private JTextField passwordUpdate = new JTextField();

    private JTextField idDelete = new JTextField();

    private JTextArea textArea = new JTextArea();

    public AdminView(){
        add(panel);
        panel.setLayout(null);
        this.setTitle("ADMIN");
        setComponents();
        addComponents();
        jFrameSetup();
    }

    private void jFrameSetup(){
        setSize(800,600);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    private void setComponents(){
        createButton.setBounds(650, 50, 100, 20);
        viewButton.setBounds(650, 90, 100, 20);
        updateButton.setBounds(650, 130, 100, 20);
        deleteButton.setBounds(650, 170, 100, 20);
        refreshButton.setBounds(650, 210, 100, 20);
        backButton.setBounds(650, 250, 100, 20);

        idLabel.setBounds( 20, 20, 100, 20);
        usernameLabel.setBounds( 140, 20, 100, 20);
        passwordLabel.setBounds( 260, 20, 100, 20);

        usernameCreate.setBounds(140, 50, 100, 20);
        passwordCreate.setBounds(260, 50, 100, 20);

        idRead.setBounds(20, 90, 100, 20);
        found.setBounds(140, 90, 460, 20);

        idUpdate.setBounds(20, 130, 100, 20);
        usernameUpdate.setBounds(140, 130, 100, 20);
        passwordUpdate.setBounds(260, 130, 100, 20);

        idDelete.setBounds(20, 170, 100, 20);

        textArea.setBounds(20, 250, 600, 300);
    }
    private void addComponents(){
        panel.add(createButton);
        panel.add(viewButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(refreshButton);
        panel.add(backButton);

        panel.add(idLabel);
        panel.add(usernameLabel);
        panel.add(passwordLabel);

        panel.add(usernameCreate);
        panel.add(passwordCreate);

        panel.add(idUpdate);
        panel.add(usernameUpdate);
        panel.add(passwordUpdate);

        panel.add(idRead);
        panel.add(found);
        panel.add(idDelete);
        panel.add(textArea);
    }

    public void setCreateButtonListener(ActionListener a){
        createButton.addActionListener(a);
    }
    public void setViewButtonListener(ActionListener a){
        viewButton.addActionListener(a);
    }
    public void setUpdateButtonListener(ActionListener a){
        updateButton.addActionListener(a);
    }
    public void setDeleteButtonListener(ActionListener a){
        deleteButton.addActionListener(a);
    }

    public void setRefreshButtonListener(ActionListener a){
        refreshButton.addActionListener(a);
    }
    public void setBackButtonListener(ActionListener a){
        backButton.addActionListener(a);
    }

    public String getUsernameCreate(){
        return usernameCreate.getText();
    }
    public String getPasswordCreate(){
        return passwordCreate.getText();
    }

    public String getIdView(){
        return idRead.getText();
    }

    public JTextField getFound(){
        return this.found;
    }

    public String getIdUpdate(){
        return idUpdate.getText();
    }
    public String getUsernameUpdate(){
        return usernameUpdate.getText();
    }
    public String getPasswordUpdate(){
        return passwordUpdate.getText();
    }

    public String getIdDelete(){
        return idDelete.getText();
    }

    public JTextArea getTextArea(){
        return textArea;
    }
}
