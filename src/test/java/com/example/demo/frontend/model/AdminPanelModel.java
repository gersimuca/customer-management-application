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
import com.example.demo.frontend.view.IndexView;
import com.example.demo.frontend.view.responses.*;

import javax.persistence.PersistenceException;
import javax.swing.*;
import java.util.List;

public class AdminPanelModel implements AdminPanelController {
    private final ManagementRepository managementRepository = new ManagementRepository();
    private final ProductRepository productRepository = new ProductRepository();
    private final RequestRepository requestRepository = new RequestRepository();

    @Override
    public void updateProfile(AdminPanelView adminPanelView, JButton button) {
        button.addActionListener(e -> {
            Management management = adminPanelView.getManagement();

            management.setFirstName(adminPanelView.getFirstName().getText());
            management.setLastName(adminPanelView.getLastName().getText());
            management.setEmail(adminPanelView.getEmail().getText());
            management.setPassword(adminPanelView.getPassword().getText());

            try {
                management = managementRepository.updateManagementUser(management);
            } catch (PersistenceException pe) {
                pe.printStackTrace();
                new EmailExistError();
                return;
            }
            new AdminPanelView(management);
        });

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
    public List<Client> allClients() {
        return managementRepository.getAllCustomers();
    }


    @Override
    public List<Product> allProducts() {
        return managementRepository.getAllProducts();
    }

    @Override
    public List<Requests> allRequests() {
        return requestRepository.getAllRequest();
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

            try {
                productRepository.createProduct(product);
            } catch (PersistenceException pe) {
                pe.printStackTrace();
                new ProductAlreadyExistError();
                return;
            }
            adminPanelView.dispose();
            new AdminPanelView(management);
        });
    }

    @Override
    public void deleteRequest(AdminPanelView adminPanelView, JButton button, JComboBox listOfRequestForDeleteOrApprove) {
        button.addActionListener(e -> {
            Requests request = new Requests();
            String temp = listOfRequestForDeleteOrApprove.getSelectedItem().toString();
            StringBuilder name = new StringBuilder();
            for (int i = 3; i < temp.length(); i++) {
                if (temp.charAt(i) == ' ') break;
                name.append(temp.charAt(i));
            }
            request.setId_request((long) Integer.parseInt(name.toString()));

            request = requestRepository.findRequest(request);
            requestRepository.deleteRequest(request.getId_request());
            adminPanelView.dispose();
            new AdminPanelView(adminPanelView.getManagement());
        });
    }

    @Override
    public void approved(AdminPanelView adminPanelView, JComboBox comboBox, JButton button) {
        button.addActionListener(e -> {
            List<Product> products = productRepository.getAllProducts();
            Requests theRequest = new Requests();
            String temp = comboBox.getSelectedItem().toString();
            StringBuilder name = new StringBuilder();
            for (int i = 3; i < temp.length(); i++) {
                if (temp.charAt(i) == ' ') break;
                name.append(temp.charAt(i));
            }
            theRequest.setId_request((long) Integer.parseInt(name.toString()));
            theRequest = requestRepository.findRequest(theRequest);

            Integer quantityOfRequest = theRequest.getQuantity();
            for (Product sample : products) {
                if (sample.getProductName().equals(theRequest.getProductName())) {
                    Integer quantityOfProduct = productRepository.getQuantity(sample);
                    if (quantityOfProduct > quantityOfRequest) {
                        theRequest.setStatus("APPROVED");
                        requestRepository.updateRequest(theRequest);
                        int holder = Integer.parseInt(sample.getQuantity()) - quantityOfRequest;
                        sample.setQuantity(Integer.toString(holder));
                        productRepository.updateProduct(sample);
                        adminPanelView.dispose();
                        new AdminPanelView(adminPanelView.getManagement());
                        return;
                    }
                }
            }
            theRequest.setStatus("DENIED");
            requestRepository.updateRequest(theRequest);
            adminPanelView.dispose();
            new AdminPanelView(adminPanelView.getManagement());
        });


    }

    @Override
    public void deleteProduct(AdminPanelView adminPanelView, JButton button, JComboBox selectedProduct) {
        button.addActionListener(e -> {
            Product product = new Product();
            String temp = selectedProduct.getSelectedItem().toString();
            StringBuilder name = new StringBuilder();
            for (int i = 3; i < temp.length(); i++) {
                if (temp.charAt(i) == ' ') break;
                name.append(temp.charAt(i));
            }
            product.setProductId((long) Integer.parseInt(name.toString()));

            product = productRepository.findProduct(product);
            productRepository.delete(product.getProductId());
            adminPanelView.dispose();
            new AdminPanelView(adminPanelView.getManagement());
        });
    }

    @Override
    public void addProductQuantity(AdminPanelView adminPanelView, JComboBox selectedProduct, JButton button) {
        button.addActionListener(e -> {
            Integer quantityEntered = null;
            try {
                quantityEntered = Integer.parseInt(adminPanelView.getAddQuantityProductField().getText());
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
                new QuantityFieldCanNotContainLettersError();
                return;
            }

            if (quantityEntered < 0) {
                new QuantityCanNotBeNegativeError();
                return;
            }
            Product product = new Product();
            String temp = selectedProduct.getSelectedItem().toString();
            StringBuilder name = new StringBuilder();

            for (int i = 3; i < temp.length(); i++) {
                if (temp.charAt(i) == ' ') break;
                name.append(temp.charAt(i));
            }
            product.setProductId((long) Integer.parseInt(name.toString()));

            product = productRepository.findProduct(product);
            int sumQuantity = Integer.parseInt(product.getQuantity()) + quantityEntered;
            product.setQuantity(Integer.toString(sumQuantity));
            productRepository.updateProduct(product);

            adminPanelView.dispose();
            new AdminPanelView(adminPanelView.getManagement());
        });
    }

    @Override
    public void subtractProductQuantity(AdminPanelView adminPanelView, JComboBox selectedProduct, JButton button) {
        button.addActionListener(e -> {
            Integer quantityEntered = null;
            try {
                quantityEntered = Integer.parseInt(adminPanelView.getAddQuantityProductField().getText());
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
                new QuantityFieldCanNotContainLettersError();
                return;
            }

            if (quantityEntered < 0) {
                new QuantityCanNotBeNegativeError();
                return;
            }
            Product product = new Product();
            String temp = selectedProduct.getSelectedItem().toString();
            StringBuilder name = new StringBuilder();

            for (int i = 3; i < temp.length(); i++) {
                if (temp.charAt(i) == ' ') break;
                name.append(temp.charAt(i));
            }
            product.setProductId((long) Integer.parseInt(name.toString()));

            product = productRepository.findProduct(product);
            int sumQuantity = Integer.parseInt(product.getQuantity()) - quantityEntered;
            product.setQuantity(Integer.toString(sumQuantity));
            productRepository.updateProduct(product);

            adminPanelView.dispose();
            new AdminPanelView(adminPanelView.getManagement());
        });
    }


}
