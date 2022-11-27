package com.example.demo.frontend.view;

import com.example.demo.backend.model.Client;
import com.example.demo.backend.model.Product;
import com.example.demo.backend.model.Requests;
import com.example.demo.frontend.model.ClientPanelModel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

@Getter
@Setter

public class ClientPanelView extends JFrame implements ActionListener, MouseListener {
    private final ClientPanelModel clientPanelModel = new ClientPanelModel();
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField passwordField;
    private JComboBox<String> productJComboBox;
    private JTextField quantityField;
    private JComboBox<String> requestJComboBox;
    private Client client;

    public ClientPanelView(Client client) {
        this.client = client;
        addMouseListener(this);

        JLabel welcomeLabel = new JLabel();
        welcomeLabel.setText("Welcome, " + client.getFirstName() + " " + client.getLastName());
        welcomeLabel.setBounds(40, 40, 250, 40);
        this.add(welcomeLabel);

        JLabel updateYourProfile = new JLabel();
        updateYourProfile.setText("Update Your Profile");
        updateYourProfile.setBounds(40, 90, 250, 40);
        this.add(updateYourProfile);


        JLabel firstNameLabel = new JLabel();
        firstNameLabel.setText("First Name");
        firstNameLabel.setBounds(40, 125, 250, 40);
        this.add(firstNameLabel);

        JLabel lastNameLabel = new JLabel();
        lastNameLabel.setText("Last Name");
        lastNameLabel.setBounds(120, 125, 250, 40);
        this.add(lastNameLabel);

        JLabel eMailLabel = new JLabel();
        eMailLabel.setText("E-Mail");
        eMailLabel.setBounds(210, 125, 250, 40);
        this.add(eMailLabel);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        passwordLabel.setBounds(280, 125, 250, 40);
        this.add(passwordLabel);

        this.firstNameField = new JTextField();
        this.firstNameField.setText(client.getFirstName());
        this.firstNameField.setBounds(40, 165, 65, 30);
        this.add(this.firstNameField);

        this.lastNameField = new JTextField();
        this.lastNameField.setText(client.getLastName());
        this.lastNameField.setBounds(120, 165, 65, 30);
        this.add(this.lastNameField);

        this.emailField = new JTextField();
        this.emailField.setText(client.getEmail());
        this.emailField.setBounds(210, 165, 65, 30);
        this.add(this.emailField);

        this.passwordField = new JTextField();
        this.passwordField.setText(client.getPassword());
        this.passwordField.setBounds(280, 165, 65, 30);
        this.add(this.passwordField);

        JButton update = new JButton();
        update.setText("update");
        update.setBounds(370, 165, 75, 30);
        update.setFocusable(false);
        clientPanelModel.updateProfile(this, update);
        this.add(update);

        JButton logout = new JButton();
        logout.setText("LOGOUT");
        logout.setBounds(380, 30, 100, 30);
        logout.setFocusable(false);
        clientPanelModel.logOut(this, logout);
        this.add(logout);

        JButton deleteAccount = new JButton();
        deleteAccount.setText("DELETE ACCOUNT");
        deleteAccount.setBounds(300, 470, 150, 30);
        deleteAccount.setFocusable(false);
        clientPanelModel.deleteAccount(this, deleteAccount);
        this.add(deleteAccount);

        JLabel createRequest = new JLabel();
        createRequest.setText("Create request");
        createRequest.setBounds(40, 200, 250, 40);
        this.add(createRequest);

        JLabel productNameLabel = new JLabel();
        productNameLabel.setText("Product Name");
        productNameLabel.setBounds(40, 240, 250, 40);
        this.add(productNameLabel);

        List<Product> products = clientPanelModel.allProducts();
        this.productJComboBox = new JComboBox<>();

        if (products.size() != 0) {
            for (Product product : products) {
                this.productJComboBox.addItem(product.getProductName());
            }
            this.productJComboBox.setBounds(40, 280, 85, 30);
            this.productJComboBox.addActionListener(this);
            this.add(this.productJComboBox);

            JLabel quantityLabel = new JLabel();
            quantityLabel.setText("Quantity");
            quantityLabel.setBounds(150, 240, 250, 40);
            this.add(quantityLabel);

            this.quantityField = new JTextField();
            this.quantityField.setBounds(150, 280, 65, 30);
            this.add(quantityField);

            JButton submit = new JButton();
            submit.setText("submit");
            submit.setFocusable(false);
            submit.setBounds(230, 280, 85, 30);
            clientPanelModel.createRequest(this, submit, this.productJComboBox);
            this.add(submit);
        } else {
            JLabel productsAreNotAvailable = new JLabel();
            productsAreNotAvailable.setBounds(40, 280, 300, 30);
            productsAreNotAvailable.setText("Products are not available");
            this.add(productsAreNotAvailable);
        }


        JLabel listOfRequestLabel = new JLabel();
        listOfRequestLabel.setText("List of Request");
        listOfRequestLabel.setBounds(40, 320, 250, 40);
        this.add(listOfRequestLabel);

        List<Requests> requests = clientPanelModel.allRequest(getClient());
        requestJComboBox = new JComboBox();

        if (requests.size() != 0) {
            for (Requests request : requests) {
                this.requestJComboBox.addItem("No." + request.getId_request() + " | " + request.getProduct() + " [ " + request.getStatus() + " ] Quantity: " + request.getQuality());
            }
            this.requestJComboBox.setBounds(40, 360, 250, 30);
            this.requestJComboBox.addActionListener(this);
            this.add(this.requestJComboBox);

            JButton deleteRequest = new JButton();
            deleteRequest.setText("delete request");
            deleteRequest.setBounds(320, 360, 140, 30);
            clientPanelModel.deleteRequest(this, deleteRequest, requestJComboBox);
            this.add(deleteRequest);
        } else {
            JLabel productsAreNotAvailable = new JLabel();
            productsAreNotAvailable.setBounds(40, 360, 350, 30);
            productsAreNotAvailable.setText("Requests are not available");
            this.add(productsAreNotAvailable);
        }


        this.setLayout(null);

        this.setTitle("Application Form");
        this.setSize(500, 800);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
