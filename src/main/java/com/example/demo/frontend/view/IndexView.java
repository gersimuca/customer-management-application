package com.example.demo.frontend.view;

import com.example.demo.frontend.model.IndexModel;

import javax.swing.*;
import java.awt.*;

public class IndexView extends JFrame {

    private final IndexModel indexModel = new IndexModel();

    public JButton buttonClientCreate;
    public JButton buttonStaffCreate;

    public JButton buttonClientLogin;
    public JButton buttonStaffLogin;

    public JTextField textFieldClientEmail;
    public JTextField textFieldClientPassword;

    public JTextField textStaffEmail;

    public JTextField textStaffPassword;


    public IndexView() {

        clientLabel();
        adminLabel();

        setTextFieldClientEmail();
        setTextFieldClientPassword();

        setTextFieldStaffEmail();
        setTextFieldStaffPassword();

        buttonClientLogin();
        buttonClientCreate();

        buttonStaffLogin();
        buttonStaffCreate();

        setLayout();
        phaseOneProperties();
    }

    void clientLabel() {
        JLabel clientText = new JLabel();
        clientText.setText("Client");
        clientText.setBounds(80, 35, 200, 200);
        this.add(clientText);
    }
    void adminLabel() {
        JLabel adminText = new JLabel();
        adminText.setText("Management and Staff");
        adminText.setBounds(275, 35, 200, 200);
        this.add(adminText);
    }

    void setTextFieldClientEmail() {
        textFieldClientEmail = new JTextField();
        textFieldClientEmail.setPreferredSize(new Dimension(250, 40));
        textFieldClientEmail.setBounds(70, 150, 80, 25);
        textFieldClientEmail.setText("email");
        this.add(textFieldClientEmail);
    }

    void setTextFieldClientPassword() {
        textFieldClientPassword = new JTextField();
        textFieldClientPassword.setPreferredSize(new Dimension(250, 40));
        textFieldClientPassword.setBounds(70, 180, 80, 25);
        textFieldClientPassword.setText("password");
        this.add(textFieldClientPassword);
    }

    void setTextFieldStaffEmail() {
        textStaffEmail = new JTextField();
        textStaffEmail.setPreferredSize(new Dimension(250, 40));
        textStaffEmail.setBounds(300, 150, 80, 25);
        textStaffEmail.setText("email");
        this.add(textStaffEmail);
    }

    void setTextFieldStaffPassword() {
        textStaffPassword = new JTextField();
        textStaffPassword.setPreferredSize(new Dimension(250, 40));
        textStaffPassword.setBounds(300, 180, 80, 25);
        textStaffPassword.setText("password");
        this.add(textStaffPassword);
    }



    void buttonClientLogin(){
        buttonClientLogin = new JButton();
        buttonClientLogin.setText("login");
        buttonClientLogin.setBounds(70, 220, 200, 200);
        buttonClientLogin.setSize(75, 25);
        buttonClientLogin.setFocusable(false);
        indexModel.loginClient(this, buttonClientLogin);
        this.add(buttonClientLogin);
    }

    void buttonClientCreate() {
        buttonClientCreate = new JButton();
        buttonClientCreate.setText("create");
        buttonClientCreate.setBounds(70, 250, 200, 200);
        buttonClientCreate.setSize(75, 25);
        buttonClientCreate.setFocusable(false);
        indexModel.createClient(this, buttonClientCreate);
        this.add(buttonClientCreate);
    }

    void buttonStaffLogin(){
        buttonStaffLogin = new JButton();
        buttonStaffLogin.setText("login");
        buttonStaffLogin.setBounds(300, 220, 200, 200);
        buttonStaffLogin.setSize(75, 25);
        buttonStaffLogin.setFocusable(false);
        indexModel.loginStaff(this, buttonStaffLogin);
        this.add(buttonStaffLogin);
    }

    void buttonStaffCreate() {
        buttonStaffCreate = new JButton();
        buttonStaffCreate.setText("create");
        buttonStaffCreate.setBounds(300, 250, 200, 200);
        buttonStaffCreate.setSize(75, 25);
        buttonStaffCreate.setFocusable(false);
        indexModel.createStaff(this, buttonStaffCreate);
        this.add(buttonStaffCreate);
    }
    void setLayout() {
        this.setLayout(null);
    }
    void phaseOneProperties() {
        this.setTitle("Customer Management Application");
        this.setSize(500, 500);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JTextField getTextFieldClientEmail() {
        return textFieldClientEmail;
    }

    public JTextField getTextFieldClientPassword() {
        return textFieldClientPassword;
    }

    public JTextField getTextStaffEmail() {
        return textStaffEmail;
    }

    public JTextField getTextStaffPassword() {
        return textStaffPassword;
    }
}
