package com.example.demo.frontend.controller;

import com.example.demo.frontend.view.CreateAdminView;
import com.example.demo.frontend.view.CreateClientView;

import javax.swing.*;

public interface CreateAdminController {
    void createAdmin(CreateAdminView createAdminView, JButton button);
    void backToMain(JFrame frame, JButton button);
}
