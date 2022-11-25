package com.example.demo.frontend.model;

import com.example.demo.backend.model.Client;
import com.example.demo.backend.model.Management;
import com.example.demo.backend.model.Product;
import com.example.demo.backend.model.Requests;
import com.example.demo.backend.repository.ManagementRepository;
import com.example.demo.backend.repository.ProductRepository;
import com.example.demo.backend.repository.RequestRepository;
import com.example.demo.frontend.controller.AdminPanelController;
import com.example.demo.frontend.view.AdminPanelView;
import com.example.demo.frontend.view.ClientPanelView;
import com.example.demo.frontend.view.IndexView;

import javax.swing.*;
import java.util.List;

public class AdminPanelModel implements AdminPanelController {
    private final ManagementRepository managementRepository = new ManagementRepository();
    private final ProductRepository productRepository = new ProductRepository();
    private final RequestRepository requestRepository = new RequestRepository();

    @Override
    public void updateProfile(AdminPanelView adminPanelView, JButton button) {
        try {
            button.addActionListener(e -> {
                Management management = adminPanelView.getManagement();

                management.setFirstName(adminPanelView.getFirstName().getText());
                management.setLastName(adminPanelView.getLastName().getText());
                management.setEmail(adminPanelView.getEmail().getText());
                management.setPassword(adminPanelView.getPassword().getText());

                management = managementRepository.updateManagementUser(management);
                new AdminPanelView(management);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void logOut(JFrame frame, JButton button) {
        button.addActionListener(e -> {
            frame.dispose();
            new IndexView();
        });
    }

    @Override
    public void deleteAccount(AdminPanelView adminPanelView, JButton button) {
        button.addActionListener(e -> {
            managementRepository.deleteManagementUser(adminPanelView.getManagement().getId());
            adminPanelView.dispose();
            new IndexView();
        });
    }

    @Override
    public List<Management> listOfManagers() {
        return managementRepository.getAllManagement();
    }

    @Override
    public String[] listOfAdmins(List<Management> managements) {
        String[] staff = new String[managements.size()];
        for (int i = 0; i < managements.size(); i++)
            staff[i] = managements.get(i).getFirstName() + " " + managements.get(i).getLastName();
        return staff;
    }

    @Override
    public List<Client> allClients() {
        return managementRepository.getAllCustomers();
    }

    @Override
    public String[] listOfClients(List<Client> clients) {
        String[] staff = new String[clients.size()];
        for (int i = 0; i < clients.size(); i++)
            staff[i] = clients.get(i).getFirstName() + " " + clients.get(i).getLastName();
        return staff;
    }

    @Override
    public List<Product> allProducts() {
        return managementRepository.getAllProducts();
    }

    @Override
    public String[] listOfProducts(List<Product> products) {
        String[] prod = new String[products.size()];
        for (int i = 0; i < products.size(); i++) prod[i] = products.get(i).getProductName();
        return prod;
    }

    @Override
    public List<Requests> allRequests() {
        return requestRepository.getAllRequest();
    }

    @Override
    public String[] listOfRequest(List<Requests> requests) {
        String[] prod = new String[requests.size()];
        for (int i = 0; i < requests.size(); i++)
            if(requests.get(i).getStatus().equals("PENDING")){
                prod[i] = requests.get(i).getProduct();
            }
        return prod;
    }

    @Override
    public void createProduct(AdminPanelView adminPanelView, JButton button) {
        button.addActionListener(e -> {
            Product product = new Product();
            Management management = adminPanelView.getManagement();

            product.setProductName(adminPanelView.getProductName().getText());
            product.setManufacturer(adminPanelView.getManufacture().getText());
            product.setCountryOfOrigin(adminPanelView.getCountry().getText());
            product.setQuantity(adminPanelView.getQuantity().getText());

            productRepository.createProduct(product);
            adminPanelView.dispose();
            new AdminPanelView(management);
        });
    }

    @Override
    public void deleteRequest(AdminPanelView adminPanelView, JButton button, Requests requests) {
        button.addActionListener(e -> {
            requestRepository.deleteRequest(requests.getId_request());
            Management management = adminPanelView.getManagement();
            adminPanelView.dispose();
            new AdminPanelView(management);
        });
    }

    @Override
    public Requests findRequest(List<Requests> requests, JComboBox jComboBox) {
        Requests request = new Requests();
        for(Requests sample : requests){
            StringBuilder s = new StringBuilder(sample.getProduct());
            if(s.toString().equals(jComboBox.getSelectedItem())){
                request = sample;
                break;
            }
        }
        return request;
    }

    @Override
    public void approved(AdminPanelView adminPanelView, Requests requests, JButton button, List<Product> products) {
        try {
            button.addActionListener(e -> {
                Integer request = requests.getQuality();
                for (Product sample : products) {
                    if (sample.getProductName().equals(requests.getProduct())) {
                        Integer quantity = productRepository.getQuantity(sample);
                        if (quantity > request) {
                            requests.setStatus("APPROVED");
                            requestRepository.updateRequest(requests);
                            int holder = Integer.parseInt(sample.getQuantity()) - request;
                            sample.setQuantity(Integer.toString(holder));
                            productRepository.updateProduct(sample);
                            adminPanelView.dispose();
                            new AdminPanelView(adminPanelView.getManagement());
                            break;
                        }
                    }
                }
            });
        }  catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Product findProduct(List<Product> products, JComboBox jComboBox) {
        Product product = new Product();
        for(Product sample : products){
            StringBuilder s = new StringBuilder(sample.getProductName());
            if(s.toString().equals(jComboBox.getSelectedItem())){
                product = sample;
                break;
            }
        }
        return product;
    }

    @Override
    public void deleteProduct(AdminPanelView adminPanelView, JButton button, Product product) {
        button.addActionListener(e -> {
            productRepository.delete(product.getProductId().intValue());
            Management management = adminPanelView.getManagement();
            adminPanelView.dispose();
            new AdminPanelView(management);
        });
    }


}
