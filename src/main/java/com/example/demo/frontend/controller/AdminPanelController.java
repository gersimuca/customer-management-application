package com.example.demo.frontend.controller;

import com.example.demo.backend.model.Management;
import com.example.demo.frontend.view.AdminPanelView;

import javax.swing.*;
import java.util.List;

public interface AdminPanelController {
    void updateProfile(AdminPanelView adminPanelView, JButton button);
    void logOut(JFrame frame, JButton button);
    void deleteAccount(AdminPanelView adminPanelView, JButton button);

    String[] listOfAdmins();
    String[] listOfClients();
}
