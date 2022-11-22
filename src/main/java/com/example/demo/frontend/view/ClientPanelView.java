package com.example.demo.frontend.view;

import com.example.demo.backend.model.Client;

import javax.swing.*;

public class ClientPanelView extends JFrame {

    private Client client;
    private JLabel request;

    public ClientPanelView(Client client){
        this.client = client;
        welcome();
        createRequest();
        createRequestButton();
        setJComboBox();
        setLayout();
        phaseOneProperties();
    }


    void welcome() {
        JLabel label = new JLabel();
        label.setText("Welcome, " + client.getFirstName() + " " + client.getLastName());
        label.setBounds(40, 80, 250, 40);
        this.add(label);
    }

    void createRequest(){
        request = new JLabel();
        request.setText("Create Request");
        request.setBounds(40, 120, 250, 40);
        this.add(request);
    }

    void createRequestButton(){
        JButton button = new JButton();
        button.setText("+");
        button.setBounds(200, 120, 250, 40);
        button.setFocusable(false);
        this.add(button);
    }

    void setJComboBox(){
        String[] products = new String[] {"Drinks", "Food"};
        JComboBox box = new JComboBox(products);
        box.setBounds(200, 220, 250, 40);
        this.add(box);
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
}
