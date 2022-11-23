package com.example.demo.frontend.controller;

import com.example.demo.frontend.view.IndexView;

import javax.swing.*;

public interface IndexController {
    void createStaff(JFrame frame, JButton button);
    void createClient(JFrame frame, JButton button);
    void loginClient(IndexView indexView,JButton button);
    void loginStaff(IndexView indexView, JButton button);
}
