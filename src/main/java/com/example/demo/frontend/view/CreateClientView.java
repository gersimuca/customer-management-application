package com.example.demo.frontend.view;

import com.example.demo.frontend.model.CreateClientModel;

import javax.swing.*;

public class CreateClientView extends JFrame {
    private JTextField firstName;
    private JTextField lastName;
    private JTextField password;
    private JTextField email;

    private final CreateClientModel createClientModel = new CreateClientModel();
    public CreateClientView() {
        clientLabelFirsName();
        clientLabelLastName();
        clientLabelEmail();
        clientLabelPassword();

        setFieldFirstName();
        setFieldLastname();
        setFieldEmail();
        setFieldPassword();

        submitButton();

        setLayout();
        phaseOneProperties();

    }

    void clientLabelFirsName() {
        JLabel label = new JLabel();
        label.setText("First Name: ");
        label.setBounds(40, 50, 70, 40);
        this.add(label);
    }
    void clientLabelLastName(){
        JLabel label = new JLabel();
        label.setText("Last Name: ");
        label.setBounds(40, 100, 70, 40);
        this.add(label);
    }
    void clientLabelEmail(){
        JLabel label = new JLabel();
        label.setText("E-Mail: ");
        label.setBounds(40, 150, 70, 40);
        this.add(label);
    }
    void clientLabelPassword(){
        JLabel label = new JLabel();
        label.setText("Password: ");
        label.setBounds(40, 200, 70, 40);
        this.add(label);
    }



    void setFieldFirstName() {
        this.firstName = new JTextField();
        firstName.setText("...");
        firstName.setBounds(150, 60, 150, 20);
        this.add(firstName);
    }

    void setFieldLastname() {
        lastName = new JTextField();
        lastName.setText("...");
        lastName.setBounds(150, 110, 150, 20);
        this.add(lastName);
    }

    void setFieldEmail() {
        password = new JTextField();
        password.setText("...");
        password.setBounds(150, 160, 150, 20);
        this.add(password);
    }

    void setFieldPassword() {
        email = new JTextField();
        email.setText("...");
        email.setBounds(150, 210, 150, 20);
        this.add(email);
    }

    void submitButton() {
        JButton button = new JButton();
        button.setText("submit");
        button.setBounds(175, 250, 200, 200);
        button.setSize(85, 30);
        button.setFocusable(false);
        createClientModel.createClient(this, button);
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

    public JTextField getFirstName() {
        return firstName;
    }

    public JTextField getLastName() {
        return lastName;
    }

    public JTextField getPassword() {
        return password;
    }

    public JTextField getEmail() {
        return email;
    }
}
