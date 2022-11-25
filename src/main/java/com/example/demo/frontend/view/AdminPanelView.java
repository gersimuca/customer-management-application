package com.example.demo.frontend.view;

import com.example.demo.backend.model.Client;
import com.example.demo.backend.model.Management;
import com.example.demo.backend.model.Product;
import com.example.demo.frontend.model.AdminPanelModel;

import javax.swing.*;
import java.util.List;


public class AdminPanelView extends JFrame {
    private final AdminPanelModel adminPanelModel = new AdminPanelModel();

    private List<Management> managements;
    private List<Client> clients;
    private List<Product> products;
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

    public AdminPanelView(Management management) {
        this.management = management;
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

        labelAllAdmins();
        listOfAdmins();

        labelAllClients();
        listOfClients();

        labelAllProducts();
        listOfProducts();

        labelCreateProducts();
        labelProductName();
        labelManufacturer();
        labelQuantity();
        labelCountryOfOrigin();

        fieldProductName();
        fieldManufacturer();
        fieldQuantity();
        fieldCountryOfOrigin();

        createProductButton();

        setLayout();
        phaseOneProperties();
    }


    void welcome() {
        JLabel label = new JLabel();
        label.setText("Welcome, " + management.getFirstName() + " " + management.getLastName());
        label.setBounds(40, 40, 250, 40);
        this.add(label);
    }

    void updateLabel() {
        JLabel label = new JLabel();
        label.setText("Update Your Profile");
        label.setBounds(40, 90, 250, 40);
        this.add(label);
    }

    void updateButton() {
        JButton button = new JButton();
        button.setText("update");
        button.setBounds(370, 165, 75, 30);
        button.setFocusable(false);
        adminPanelModel.updateProfile(this, button);
        this.add(button);
    }

    void labelFirstName() {
        JLabel label = new JLabel();
        label.setText("First Name");
        label.setBounds(40, 125, 250, 40);
        this.add(label);
    }

    void labelLastName() {
        JLabel label = new JLabel();
        label.setText("Last Name");
        label.setBounds(120, 125, 250, 40);
        this.add(label);
    }

    void labelEmail() {
        JLabel label = new JLabel();
        label.setText("E-Mail");
        label.setBounds(210, 125, 250, 40);
        this.add(label);
    }

    void labelPassword() {
        JLabel label = new JLabel();
        label.setText("Password");
        label.setBounds(280, 125, 250, 40);
        this.add(label);
    }

    void fieldFirstName() {
        firstName = new JTextField();
        firstName.setText(management.getFirstName());
        firstName.setBounds(40, 165, 65, 30);
        this.add(firstName);
    }

    void fieldLastName() {
        lastName = new JTextField();
        lastName.setText(management.getLastName());
        lastName.setBounds(120, 165, 65, 30);
        this.add(lastName);
    }

    void fieldEmail() {
        email = new JTextField();
        email.setText(management.getEmail());
        email.setBounds(210, 165, 65, 30);
        this.add(email);
    }

    void fieldPassword() {
        password = new JTextField();
        password.setText(management.getPassword());
        password.setBounds(280, 165, 65, 30);
        this.add(password);
    }

    void buttonLogOut() {
        JButton button = new JButton();
        button.setText("LOGOUT");
        button.setBounds(380, 30, 100, 30);
        button.setFocusable(false);
        adminPanelModel.logOut(this, button);
        this.add(button);
    }

    void deleteAccountButton() {
        JButton button = new JButton();
        button.setText("DELETE ACCOUNT");
        button.setBounds(300, 870, 150, 30);
        button.setFocusable(false);
        adminPanelModel.deleteAccount(this, button);
        this.add(button);
    }

    void labelAllAdmins(){
        JLabel label = new JLabel();
        label.setText("STAFF");
        label.setBounds(40, 190, 250, 40);
        this.add(label);
    }

    void listOfAdmins(){
        this.managements = adminPanelModel.listOfManagers();
        String[] staff = adminPanelModel.listOfAdmins(this.getManagements());
        JComboBox admins = new JComboBox(staff);
        admins.setBounds(40, 220, 65, 30);
        this.add(admins);
    }

    void labelAllClients(){
        JLabel label = new JLabel();
        label.setText("CLIENTS");
        label.setBounds(120, 190, 250, 40);
        this.add(label);
    }

    void listOfClients(){
        this.clients = adminPanelModel.allClients();
        String[] staff = adminPanelModel.listOfClients(this.getClients());
        JComboBox admins = new JComboBox(staff);
        admins.setBounds(120, 220, 65, 30);
        this.add(admins);
    }

    void labelAllProducts(){
        JLabel label = new JLabel();
        label.setText("PRODUCTS");
        label.setBounds(200, 190, 250, 40);
        this.add(label);
    }

    void listOfProducts(){
        this.products = adminPanelModel.allProducts();
        String[] staff = adminPanelModel.listOfProducts(this.getProducts());
        JComboBox admins = new JComboBox(staff);
        admins.setBounds(200, 220, 65, 30);
        this.add(admins);
    }

    void labelCreateProducts() {
        JLabel label = new JLabel();
        label.setText("Create Products");
        label.setBounds(40, 250, 250, 40);
        this.add(label);
    }

    void labelProductName() {
        JLabel label = new JLabel();
        label.setText("Product Name");
        label.setBounds(40, 280, 250, 40);
        this.add(label);
    }

    void labelManufacturer() {
        JLabel label = new JLabel();
        label.setText("Manufacturer");
        label.setBounds(150, 280, 250, 40);
        this.add(label);
    }

    void labelQuantity() {
        JLabel label = new JLabel();
        label.setText("Quantity");
        label.setBounds(250, 280, 250, 40);
        this.add(label);
    }

    void labelCountryOfOrigin() {
        JLabel label = new JLabel();
        label.setText("Country of Origin");
        label.setBounds(330, 280, 250, 40);
        this.add(label);
    }

    void fieldProductName() {
        productName = new JTextField();
        productName.setBounds(40, 310, 65, 30);
        this.add(productName);
    }

    void fieldManufacturer() {
        manufacture = new JTextField();
        manufacture.setBounds(150, 310, 65, 30);
        this.add(manufacture);
    }

    void fieldQuantity() {
        quantity = new JTextField();
        quantity.setBounds(250, 310, 65, 30);
        this.add(quantity);
    }

    void fieldCountryOfOrigin() {
        country = new JTextField();
        country.setBounds(330, 310, 65, 30);
        this.add(country);
    }

    void createProductButton(){
        JButton button = new JButton();
        button.setText("create");
        button.setBounds(30, 355, 375, 30);
        button.setFocusable(false);
        adminPanelModel.createProduct(this, button);
        this.add(button);
    }




    void setLayout() {
        this.setLayout(null);
    }

    void phaseOneProperties() {
        this.setTitle("Application Form");
        this.setSize(500, 1000);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Management getManagement() {
        return management;
    }

    public void setManagement(Management management) {
        this.management = management;
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

    public List<Management> getManagements() {
        return managements;
    }

    public void setManagements(List<Management> managements) {
        this.managements = managements;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public JTextField getProductName() {
        return productName;
    }

    public void setProductName(JTextField productName) {
        this.productName = productName;
    }

    public JTextField getManufacture() {
        return manufacture;
    }

    public void setManufacture(JTextField manufacture) {
        this.manufacture = manufacture;
    }

    public JTextField getQuantity() {
        return quantity;
    }

    public void setQuantity(JTextField quantity) {
        this.quantity = quantity;
    }

    public JTextField getCountry() {
        return country;
    }

    public void setCountry(JTextField country) {
        this.country = country;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
