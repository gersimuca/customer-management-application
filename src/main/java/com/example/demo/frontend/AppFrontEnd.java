package com.example.demo.frontend;

import com.example.demo.backend.model.Management;
import com.example.demo.backend.repository.ManagementRepository;

import javax.swing.*;
import java.util.List;

public class AppFrontEnd extends JFrame {

    public final ManagementRepository managementRepository = new ManagementRepository();
    public JButton buttonClient;
    public JButton buttonStaff;

    public AppFrontEnd() {

        setLayout();
        setIcon();
        clientLabel();
        adminLabel();
        buttonClient();
        buttonStaff();
        phaseOneProperties();


    }

    void setLayout() {
        this.setLayout(null);
    }

    void setIcon() {
        // set icon
        ImageIcon icon = new ImageIcon("src/main/java/com/example/demo/frontend/images/1725018.jpg");
        this.setIconImage(icon.getImage());
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
        buttonClient.setText("Login");
        buttonClient.setBounds(67, 150, 200, 200);
        buttonClient.setSize(65, 25);
        buttonClient.setFocusable(false);
        buttonClientListener(buttonClient);
        this.add(buttonClient);
    }

    void buttonClientListener(JButton button) {
        buttonClient.addActionListener(e -> {
            List<Management> managements = managementRepository.getAllManagement();
            System.out.println(managements);
        });
    }

    void buttonStaff() {
        buttonStaff = new JButton();
        buttonStaff.setText("Login");
        buttonStaff.setBounds(300, 150, 200, 200);
        buttonStaff.setSize(65, 25);
        buttonStaff.setFocusable(false);
        buttonStaff.addActionListener(e -> System.out.println("XXX"));
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
