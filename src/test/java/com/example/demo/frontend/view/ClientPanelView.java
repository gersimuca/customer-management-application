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
        setClient(client);
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

        setFirstNameField(new JTextField());
        getFirstNameField().setText(client.getFirstName());
        getFirstNameField().setBounds(40, 165, 65, 30);
        this.add(getFirstNameField());

        setLastNameField(new JTextField());
        getLastNameField().setText(client.getLastName());
        getLastNameField().setBounds(120, 165, 65, 30);
        this.add(getLastNameField());

        setEmailField(new JTextField());
        getEmailField().setText(client.getEmail());
        getEmailField().setBounds(210, 165, 65, 30);
        this.add(getEmailField());

        this.passwordField = new JTextField();
        this.passwordField.setText(client.getPassword());
        this.passwordField.setBounds(280, 165, 65, 30);
        this.add(this.passwordField);

        JButton update = new JButton();
        update.setText("update");
        update.setBounds(370, 165, 75, 30);
        update.setFocusable(false);
        getClientPanelModel().updateProfile(this, update);
        this.add(update);

        JButton logout = new JButton();
        logout.setText("LOGOUT");
        logout.setBounds(380, 30, 100, 30);
        logout.setFocusable(false);
        getClientPanelModel().logOut(this, logout);
        this.add(logout);

        JButton deleteAccount = new JButton();
        deleteAccount.setText("DELETE ACCOUNT");
        deleteAccount.setBounds(300, 470, 150, 30);
        deleteAccount.setFocusable(false);
        getClientPanelModel().deleteAccount(this, deleteAccount);
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
        setProductJComboBox(new JComboBox<>());

        if (products.size() != 0) {
            for (Product product : products) {
                getProductJComboBox().addItem(product.getProductName());
            }
            getProductJComboBox().setBounds(40, 280, 85, 30);
            getProductJComboBox().addActionListener(this);
            this.add(getProductJComboBox());

            JLabel quantityLabel = new JLabel();
            quantityLabel.setText("Quantity");
            quantityLabel.setBounds(150, 240, 250, 40);
            this.add(quantityLabel);

            setQuantityField(new JTextField());
            getQuantityField().setBounds(150, 280, 65, 30);
            this.add(getQuantityField());

            JButton submit = new JButton();
            submit.setText("submit");
            submit.setFocusable(false);
            submit.setBounds(230, 280, 85, 30);
            getClientPanelModel().createRequest(this, submit, getProductJComboBox());
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

        List<Requests> requests = getClientPanelModel().allRequest(getClient());
        setRequestJComboBox(new JComboBox());

        if (requests.size() != 0) {
            for (Requests request : requests) {
                getRequestJComboBox().addItem("No." + request.getId_request() + " | " + request.getProductName() + " [ " + request.getStatus() + " ] Quantity: " + request.getQuantity());
            }
            getRequestJComboBox().setBounds(40, 360, 250, 30);
            getRequestJComboBox().addActionListener(this);
            this.add(getRequestJComboBox());

            JButton deleteRequest = new JButton();
            deleteRequest.setText("delete request");
            deleteRequest.setBounds(320, 360, 140, 30);
            getClientPanelModel().deleteRequest(this, deleteRequest, getRequestJComboBox());
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
