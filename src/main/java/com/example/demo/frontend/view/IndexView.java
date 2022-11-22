package com.example.demo.frontend.view;

import com.example.demo.backend.repository.ClientRepository;
import com.example.demo.backend.repository.ManagementRepository;
import com.example.demo.frontend.model.IndexModel;

import javax.swing.*;
import java.awt.*;

public class IndexView extends JFrame {

    private final IndexModel indexModel = new IndexModel();

    public JButton buttonClient;
    public JButton buttonStaff;

    public JTextField textFieldClientEmail;
    public JTextField textFieldClientPassword;

    public JTextField textFieldStaff;


    public IndexView() {

        setTextFieldClientEmail();
        setTextFieldClientPassword();
        setLayout();
        clientLabel();
        adminLabel();
        buttonClient();
        buttonStaff();
        phaseOneProperties();


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

    void setLayout() {
        this.setLayout(null);
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

    void buttonClient() {
        buttonClient = new JButton();
        buttonClient.setText("create");
        buttonClient.setBounds(77, 220, 200, 200);
        buttonClient.setSize(65, 25);
        buttonClient.setFocusable(false);
        indexModel.createClient(this, buttonClient);
        this.add(buttonClient);
    }

    void buttonStaff() {
        buttonStaff = new JButton();
        buttonStaff.setText("Login");
        buttonStaff.setBounds(300, 150, 200, 200);
        buttonStaff.setSize(65, 25);
        buttonStaff.setFocusable(false);
        this.add(buttonStaff);
    }

    void phaseOneProperties() {
        this.setTitle("Customer Management Application");
        this.setSize(500, 500);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
