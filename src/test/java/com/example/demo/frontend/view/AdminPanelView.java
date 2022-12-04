package com.example.demo.frontend.view;

import com.example.demo.backend.model.Client;
import com.example.demo.backend.model.Management;
import com.example.demo.backend.model.Product;
import com.example.demo.backend.model.Requests;
import com.example.demo.frontend.model.AdminPanelModel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Element;
import javax.swing.text.TableView;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.TimerTask;

@Getter
@Setter
public class AdminPanelView extends JFrame implements ActionListener {
    private final AdminPanelModel adminPanelModel = new AdminPanelModel();
    private Management management;
    private Product product;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField email;
    private JTextField password;
    private JTextField productName;
    private JTextField manufacture;
    private JTextField quantity;
    private JTextField country;
    private JComboBox<String> productForDelete;
    private JComboBox<String> requestForDeleteOrUpdate;

    private JComboBox<String> listOfAdminsJComboBox;
    private JComboBox<String> listOfProductsJComboBox;
    private JComboBox<String> listOfClientsJComboBox;
    private JComboBox<String> listOfRequestJComboBox;
    private JComboBox<String> listOfRequestForDeleteOrApproveJComboBox;
    private JComboBox<String> listOfProductsForDeleteJComboBox;
    private JComboBox<String> listOfProductsToAddQuantity;
    private JTextField addQuantityProductField;

    public AdminPanelView(Management management) {
        setManagement(management);
        JLabel welcomeLabel = new JLabel();
        welcomeLabel.setText("Welcome, " + management.getFirstName() + " " + management.getLastName());
        welcomeLabel.setBounds(40, 40, 250, 40);
        this.add(welcomeLabel);

        JLabel updateLabel = new JLabel();
        updateLabel.setText("Update Your Profile");
        updateLabel.setBounds(40, 90, 250, 40);
        this.add(updateLabel);

        JButton updateButton = new JButton();
        updateButton.setText("update");
        updateButton.setBounds(370, 165, 75, 30);
        updateButton.setFocusable(false);
        getAdminPanelModel().updateProfile(this, updateButton);
        this.add(updateButton);

        JLabel firstNameLabel = new JLabel();
        firstNameLabel.setText("First Name");
        firstNameLabel.setBounds(40, 125, 250, 40);
        this.add(firstNameLabel);

        JLabel lastNameLabel = new JLabel();
        lastNameLabel.setText("Last Name");
        lastNameLabel.setBounds(120, 125, 250, 40);
        this.add(lastNameLabel);

        JLabel emailLabel = new JLabel();
        emailLabel.setText("E-Mail");
        emailLabel.setBounds(210, 125, 250, 40);
        this.add(emailLabel);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        passwordLabel.setBounds(280, 125, 250, 40);
        this.add(passwordLabel);

        setFirstName(new JTextField());
        getFirstName().setText(management.getFirstName());
        getFirstName().setBounds(40, 165, 65, 30);
        this.add(getFirstName());

        setLastName(new JTextField());
        getLastName().setText(management.getLastName());
        getLastName().setBounds(120, 165, 65, 30);
        this.add(getLastName());

        setEmail(new JTextField());
        getEmail().setText(management.getEmail());
        getEmail().setBounds(210, 165, 65, 30);
        this.add(getEmail());

        setPassword(new JTextField());
        getPassword().setText(management.getPassword());
        getPassword().setBounds(280, 165, 65, 30);
        this.add(getPassword());

        JButton logoutButton = new JButton();
        logoutButton.setText("LOGOUT");
        logoutButton.setBounds(380, 30, 100, 30);
        logoutButton.setFocusable(false);
        getAdminPanelModel().logOut(this, logoutButton);
        this.add(logoutButton);

        JButton deleteAccountButton = new JButton();
        deleteAccountButton.setText("DELETE ACCOUNT");
        deleteAccountButton.setBounds(300, 900, 150, 30);
        deleteAccountButton.setFocusable(false);
        getAdminPanelModel().deleteAccount(this, deleteAccountButton);
        this.add(deleteAccountButton);

        JLabel staffLabel = new JLabel();
        staffLabel.setText("STAFF");
        staffLabel.setBounds(40, 190, 250, 40);
        this.add(staffLabel);

        List<Management> managements = getAdminPanelModel().listOfManagers();
        setListOfAdminsJComboBox(new JComboBox());
        if (managements.size() != 0) {
            for (Management staff : managements) {
                getListOfAdminsJComboBox().addItem("No." + staff.getId() + " | "
                        + staff.getFirstName() + " "
                        + staff.getLastName()
                        + " [ " + staff.getEmail() + " ] ");
            }
            if (getListOfAdminsJComboBox().getSelectedIndex() == -1) {
                JLabel listOfAdminsAreNotAvailable = new JLabel();
                listOfAdminsAreNotAvailable.setBounds(40, 220, 65, 30);
                listOfAdminsAreNotAvailable.setText("Staff are not available");
                this.add(listOfAdminsAreNotAvailable);
            } else {
                getListOfAdminsJComboBox().setBounds(40, 220, 200, 30);
                getListOfAdminsJComboBox().addActionListener(this);
                this.add(getListOfAdminsJComboBox());
            }
        }

        JLabel productsLabel = new JLabel();
        productsLabel.setText("PRODUCTS");
        productsLabel.setBounds(260, 190, 250, 40);
        this.add(productsLabel);

        List<Product> products = getAdminPanelModel().allProducts();
        setListOfProductsJComboBox(new JComboBox<>());
        if (products.size() != 0) {
            for (Product theProduct : products) {
                getListOfProductsJComboBox().addItem("No." + theProduct.getProductId() + " | "
                        + theProduct.getProductName() + " [ "
                        + theProduct.getQuantity() + " ] ");
            }
            if (getListOfProductsJComboBox().getSelectedIndex() == -1) {
                JLabel listOfProductsAreNotAvailable = new JLabel();
                listOfProductsAreNotAvailable.setBounds(120, 200, 65, 30);
                listOfProductsAreNotAvailable.setText("Products are not available");
                this.add(listOfProductsAreNotAvailable);
            } else {
                getListOfProductsJComboBox().setBounds(250, 220, 200, 30);
                getListOfProductsJComboBox().addActionListener(this);
                this.add(getListOfProductsJComboBox());
            }
        }

        JLabel clientsLabel = new JLabel();
        clientsLabel.setText("CLIENTS");
        clientsLabel.setBounds(40, 260, 250, 40);
        this.add(clientsLabel);


        List<Client> clients = getAdminPanelModel().allClients();
        setListOfClientsJComboBox(new JComboBox<>());
        if (clients.size() != 0) {
            for (Client client : clients) {
                getListOfClientsJComboBox().addItem("No." + client.getId_client() + " | "
                        + client.getFirstName() + " "
                        + client.getLastName()
                        + " [ " + client.getEmail() + " ] ");
            }
            if (getListOfClientsJComboBox().getSelectedIndex() == -1) {
                JLabel listOfClientsAreNotAvailable = new JLabel();
                listOfClientsAreNotAvailable.setBounds(200, 260, 200, 30);
                listOfClientsAreNotAvailable.setText("Clients are not available");
                this.add(listOfClientsAreNotAvailable);
            } else {
                getListOfClientsJComboBox().setBounds(40, 290, 200, 30);
                getListOfClientsJComboBox().addActionListener(this);
                this.add(getListOfClientsJComboBox());
            }
        }

        JLabel requestLabel = new JLabel();
        requestLabel.setText("REQUESTS");
        requestLabel.setBounds(280, 260, 250, 40);
        this.add(requestLabel);

        List<Requests> requests = adminPanelModel.allRequests();
        setListOfRequestJComboBox(new JComboBox<>());
        if (requests.size() != 0) {
            for (Requests request : requests) {
                getListOfRequestJComboBox().addItem("No." + request.getId_request() + " | "
                        + request.getProductName() + " "
                        + " [ " + request.getQuantity() + " ] ");
            }
            if (getListOfRequestJComboBox().getSelectedIndex() == -1) {
                JLabel listOfRequestAreNotAvailable = new JLabel();
                listOfRequestAreNotAvailable.setBounds(250, 290, 200, 30);
                listOfRequestAreNotAvailable.setText("Requests are not available");
                this.add(listOfRequestAreNotAvailable);
            } else {
                getListOfRequestJComboBox().setBounds(250, 290, 200, 30);
                getListOfRequestJComboBox().addActionListener(this);
                this.add(getListOfRequestJComboBox());
            }
        }

        JLabel createProductsLabel = new JLabel();
        createProductsLabel.setText("Create Products");
        createProductsLabel.setBounds(40, 330, 250, 40);
        this.add(createProductsLabel);

        JLabel productNameLabel = new JLabel();
        productNameLabel.setText("Product Name");
        productNameLabel.setBounds(40, 360, 250, 40);
        this.add(productNameLabel);

        JLabel manufacturerLabel = new JLabel();
        manufacturerLabel.setText("Manufacturer");
        manufacturerLabel.setBounds(150, 360, 250, 40);
        this.add(manufacturerLabel);

        JLabel quantityLabel = new JLabel();
        quantityLabel.setText("Quantity");
        quantityLabel.setBounds(250, 360, 250, 40);
        this.add(quantityLabel);

        JLabel countryOfOriginLabel = new JLabel();
        countryOfOriginLabel.setText("Country of Origin");
        countryOfOriginLabel.setBounds(330, 360, 250, 40);
        this.add(countryOfOriginLabel);

        setProductName(new JTextField());
        getProductName().setBounds(40, 390, 65, 30);
        this.add(getProductName());

        setManufacture(new JTextField());
        getManufacture().setBounds(150, 390, 65, 30);
        this.add(getManufacture());

        setQuantity(new JTextField());
        getQuantity().setBounds(250, 390, 65, 30);
        this.add(getQuantity());

        setCountry(new JTextField());
        getCountry().setBounds(330, 390, 65, 30);
        this.add(getCountry());

        JButton createButton = new JButton();
        createButton.setText("create");
        createButton.setBounds(30, 425, 375, 30);
        createButton.setFocusable(false);
        getAdminPanelModel().createProduct(this, createButton);
        this.add(createButton);

        JLabel label = new JLabel();
        label.setText("REQUESTS");
        label.setBounds(30, 480, 250, 40);
        this.add(label);

        setListOfRequestForDeleteOrApproveJComboBox(new JComboBox<>());
        if (requests.size() != 0) {
            for (Requests request : requests) {
                if (request.getStatus().equals("PENDING")) {
                    getListOfRequestForDeleteOrApproveJComboBox().addItem("No." + request.getId_request() + " | "
                            + request.getProductName() + " "
                            + " [ " + request.getQuantity() + " ] ");
                }
            }

            if (getListOfRequestForDeleteOrApproveJComboBox().getSelectedIndex() == -1) {
                JLabel listOfRequestAreNotAvailable = new JLabel();
                listOfRequestAreNotAvailable.setBounds(30, 520, 180, 40);
                listOfRequestAreNotAvailable.setText("NO NEW REQUEST BE MADE");
                this.add(listOfRequestAreNotAvailable);
            } else {
                getListOfRequestForDeleteOrApproveJComboBox().setBounds(30, 520, 250, 40);
                getListOfRequestForDeleteOrApproveJComboBox().addActionListener(this);
                this.add(getListOfRequestForDeleteOrApproveJComboBox());

                JButton deleteRequestButton = new JButton();
                deleteRequestButton.setText("delete request");
                deleteRequestButton.setBounds(320, 520, 150, 40);
                getAdminPanelModel().deleteRequest(this, deleteRequestButton, getListOfRequestForDeleteOrApproveJComboBox());
                this.add(deleteRequestButton);

                JButton approveButton = new JButton();
                approveButton.setText("APPROVE");
                approveButton.setBounds(320, 570, 150, 40);
                approveButton.setFocusable(false);
                getAdminPanelModel().approved(this, getListOfRequestForDeleteOrApproveJComboBox(), approveButton);
                this.add(approveButton);
            }
        }

        JLabel seeProductsLabel = new JLabel();
        seeProductsLabel.setText("PRODUCTS");
        seeProductsLabel.setBounds(30, 610, 250, 40);
        this.add(seeProductsLabel);

        setListOfProductsForDeleteJComboBox(new JComboBox<>());
        if (products.size() != 0) {
            for (Product theProduct : products) {
                getListOfProductsForDeleteJComboBox().addItem("No." + theProduct.getProductId() + " | "
                        + theProduct.getProductName() + " [ "
                        + theProduct.getQuantity() + " ] ");
            }
            if (getListOfProductsForDeleteJComboBox().getSelectedIndex() == -1) {
                JLabel listOfProductsAreNotAvailable = new JLabel();
                listOfProductsAreNotAvailable.setBounds(30, 720, 65, 30);
                listOfProductsAreNotAvailable.setText("PRODUCT NOT LISTED");
                this.add(listOfProductsAreNotAvailable);
            } else {
                getListOfProductsForDeleteJComboBox().setBounds(30, 650, 200, 30);
                getListOfProductsForDeleteJComboBox().addActionListener(this);
                this.add(getListOfProductsForDeleteJComboBox());

                JButton button = new JButton();
                button.setText("delete product");
                button.setBounds(320, 650, 150, 40);
                getAdminPanelModel().deleteProduct(this, button, getListOfProductsForDeleteJComboBox());
                this.add(button);
            }
        }

        JLabel addQuantityProductsLabel = new JLabel();
        addQuantityProductsLabel.setText("ADD PRODUCT PRODUCTS");
        addQuantityProductsLabel.setBounds(30, 760, 250, 40);
        this.add(addQuantityProductsLabel);

        setListOfProductsToAddQuantity(new JComboBox<>());
        if (products.size() != 0) {
            for (Product theProduct : products) {
                getListOfProductsToAddQuantity().addItem("No." + theProduct.getProductId() + " | "
                        + theProduct.getProductName() + " [ "
                        + theProduct.getQuantity() + " ] ");
            }
            if (getListOfProductsToAddQuantity().getSelectedIndex() == -1) {
                JLabel listOfProductsAreNotAvailable = new JLabel();
                listOfProductsAreNotAvailable.setBounds(30, 800, 65, 30);
                listOfProductsAreNotAvailable.setText("PRODUCT NOT LISTED");
                this.add(listOfProductsAreNotAvailable);
            } else {
                getListOfProductsToAddQuantity().setBounds(30, 800, 150, 30);
                getListOfProductsToAddQuantity().addActionListener(this);
                this.add(getListOfProductsToAddQuantity());

                JLabel addQuantity = new JLabel();
                addQuantity.setText("Add Quantity");
                addQuantity.setBounds(200, 760, 250, 30);
                this.add(addQuantity);

                setAddQuantityProductField(new JTextField());
                getAddQuantityProductField().setBounds(200, 800, 90, 30);
                getAddQuantityProductField().setText("0");
                this.add(getAddQuantityProductField());

                JButton buttonPlus = new JButton();
                buttonPlus.setText("+");
                buttonPlus.setBounds(320, 800, 70, 30);
                buttonPlus.setFocusable(false);
                getAdminPanelModel().addProductQuantity(this, getListOfProductsToAddQuantity(), buttonPlus);
                this.add(buttonPlus);

                JButton buttonMinus = new JButton();
                buttonMinus.setText("-");
                buttonMinus.setBounds(400, 800, 70, 30);
                buttonMinus.setFocusable(false);
                getAdminPanelModel().subtractProductQuantity(this, getListOfProductsToAddQuantity(), buttonMinus);
                this.add(buttonMinus);
            }

        }

        this.setLayout(null);
        this.setTitle("Application Form");
        this.setSize(500, 1200);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
