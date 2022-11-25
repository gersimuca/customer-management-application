package com.example.demo.frontend.view;

import com.example.demo.backend.model.Client;
import com.example.demo.backend.model.Product;
import com.example.demo.backend.model.Requests;
import com.example.demo.frontend.model.ClientPanelModel;

import javax.swing.*;
import java.util.List;


public class ClientPanelView extends JFrame {
    private final ClientPanelModel clientPanelModel = new ClientPanelModel();
    private Client client;
    private List<Requests> requests;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField email;
    private JTextField password;

    private JTextField productName;
    private JTextField quantity;

    private JComboBox listOfRequest;

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

        labelCreateRequest();
        labelProductName();
        labelQuantity();

        fieldProductName();
        fieldQuantity();

        buttonCreateRequest();

        labelListOfProducts();
        listOfRequest();
        deleteRequest();

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
        button.setBounds(300, 470, 150, 30);
        button.setFocusable(false);
        clientPanelModel.deleteAccount(this, button);
        this.add(button);
    }

    void labelCreateRequest() {
        JLabel label = new JLabel();
        label.setText("Create request");
        label.setBounds(40, 200, 250, 40);
        this.add(label);
    }

    void labelProductName(){
        JLabel label = new JLabel();
        label.setText("Product Name");
        label.setBounds(40, 240, 250, 40);
        this.add(label);
    }

    void labelQuantity(){
        JLabel label = new JLabel();
        label.setText("Quantity");
        label.setBounds(150, 240, 250, 40);
        this.add(label);
    }

    void fieldProductName(){
        productName = new JTextField();
        productName.setBounds(40, 280, 85, 30);
        this.add(productName);
    }

    void fieldQuantity(){
        quantity = new JTextField();
        quantity.setBounds(150, 280, 65, 30);
        this.add(quantity);
    }

    void buttonCreateRequest(){
        JButton button = new JButton();
        button.setText("submit");
        button.setFocusable(false);
        button.setBounds(230, 280, 85, 30);
        clientPanelModel.createRequest(this, button);
        this.add(button);
    }

    void labelListOfProducts(){
        JLabel label = new JLabel();
        label.setText("List of Request");
        label.setBounds(40, 320, 250, 40);
        this.add(label);
    }

    void listOfRequest(){
        this.requests = clientPanelModel.allRequest(this.getClient());
        String[] staff = clientPanelModel.listOfRequests(this.getRequests());
        listOfRequest = new JComboBox<>(staff);
        listOfRequest.setBounds(40,360,250,30);
        this.add(listOfRequest);
    }

    void deleteRequest(){
        JButton button = new JButton();
        button.setText("delete request");
        Requests requests1 = clientPanelModel.findRequest(requests, listOfRequest);
        clientPanelModel.deleteRequest(this, button, requests1);
        button.setBounds(320,360,140,30);
        this.add(button);
    }




    void setLayout() {
        this.setLayout(null);
    }

    void phaseOneProperties() {
        this.setTitle("Application Form");
        this.setSize(500, 800);
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


    public JTextField getProductName() {
        return productName;
    }

    public void setProductName(JTextField productName) {
        this.productName = productName;
    }

    public JTextField getQuantity() {
        return quantity;
    }

    public void setQuantity(JTextField quantity) {
        this.quantity = quantity;
    }

    public List<Requests> getRequests() {
        return requests;
    }

    public void setRequests(List<Requests> requests) {
        this.requests = requests;
    }

    public JComboBox getListOfRequest() {
        return listOfRequest;
    }

    public void setListOfRequest(JComboBox listOfRequest) {
        this.listOfRequest = listOfRequest;
    }
}
