package com.example.demo.frontend.view;

import com.example.demo.backend.model.Client;
import com.example.demo.frontend.model.ClientPanelModel;

import javax.swing.*;


public class ClientPanelView extends JFrame {
    private final ClientPanelModel clientPanelModel = new ClientPanelModel();
    private Client client;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField email;
    private JTextField password;

    public ClientPanelView(Client client){
        this.client = client;
        welcome();

        updateLabel();
        updateButton();

        labelFirstName();
        labelLastName();
        labelEmail();
        labelPassword();

        fieldFirstName();
        fieldLastName();
        fieldEmail();
        fieldPassword();

        buttonLogOut();
        deleteAccountButton();

        setLayout();
        phaseOneProperties();
    }


    void welcome() {
        JLabel label = new JLabel();
        label.setText("Welcome, " + client.getFirstName() + " " + client.getLastName());
        label.setBounds(40, 40, 250, 40);
        this.add(label);
    }

    void updateLabel(){
        JLabel label = new JLabel();
        label.setText("Update Your Profile");
        label.setBounds(40, 90, 250, 40);
        this.add(label);
    }

    void updateButton(){
        JButton button = new JButton();
        button.setText("update");
        button.setBounds(370, 165, 75, 30);
        button.setFocusable(false);
        clientPanelModel.updateProfile(this, button);
        this.add(button);
    }

    void labelFirstName(){
        JLabel label = new JLabel();
        label.setText("First Name");
        label.setBounds(40, 125, 250, 40);
        this.add(label);
    }

    void labelLastName(){
        JLabel label = new JLabel();
        label.setText("Last Name");
        label.setBounds(120, 125, 250, 40);
        this.add(label);
    }

    void labelEmail(){
        JLabel label = new JLabel();
        label.setText("E-Mail");
        label.setBounds(210, 125, 250, 40);
        this.add(label);
    }

    void labelPassword(){
        JLabel label = new JLabel();
        label.setText("Password");
        label.setBounds(280, 125, 250, 40);
        this.add(label);
    }

    void fieldFirstName(){
        firstName = new JTextField();
        firstName.setText(client.getFirstName());
        firstName.setBounds(40, 165, 65, 30);
        this.add(firstName);
    }

    void fieldLastName(){
        lastName = new JTextField();
        lastName.setText(client.getLastName());
        lastName.setBounds(120, 165, 65, 30);
        this.add(lastName);
    }

    void fieldEmail(){
        email = new JTextField();
        email.setText(client.getEmail());
        email.setBounds(210, 165, 65, 30);
        this.add(email);
    }

    void fieldPassword(){
        password = new JTextField();
        password.setText(client.getPassword());
        password.setBounds(280, 165, 65, 30);
        this.add(password);
    }

    void buttonLogOut(){
        JButton button = new JButton();
        button.setText("LOGOUT");
        button.setBounds(380, 30, 100, 30);
        button.setFocusable(false);
        clientPanelModel.logOut(this, button);
        this.add(button);
    }

    void deleteAccountButton(){
        JButton button = new JButton();
        button.setText("DELETE ACCOUNT");
        button.setBounds(300, 370, 150, 30);
        button.setFocusable(false);
        clientPanelModel.deleteAccount(this, button);
        this.add(button);
    }


    void setLayout() {
        this.setLayout(null);
    }

    void phaseOneProperties() {
        this.setTitle("Application Form");
        this.setSize(500, 500);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public JTextField getFirstName() {
        return firstName;
    }

    public void setFirstName(JTextField firstName) {
        this.firstName = firstName;
    }

    public JTextField getLastName() {
        return lastName;
    }

    public void setLastName(JTextField lastName) {
        this.lastName = lastName;
    }

    public JTextField getEmail() {
        return email;
    }

    public void setEmail(JTextField email) {
        this.email = email;
    }

    public JTextField getPassword() {
        return password;
    }

    public void setPassword(JTextField password) {
        this.password = password;
    }
}
