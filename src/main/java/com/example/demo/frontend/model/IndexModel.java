package com.example.demo.frontend.model;

import com.example.demo.backend.model.Client;
import com.example.demo.backend.repository.ClientRepository;
import com.example.demo.frontend.controller.IndexController;
import com.example.demo.frontend.view.ClientPanelView;
import com.example.demo.frontend.view.CreateClientView;
import com.example.demo.frontend.view.IndexView;

import javax.swing.*;

public class IndexModel implements IndexController {
    @Override
    public void createStaff(JButton button) {

    }

    @Override
    public void createClient(JFrame frame, JButton button) {
        button.addActionListener(e -> {
            frame.dispose();
            new CreateClientView();
        });
    }

    @Override
    public void loginClient(IndexView indexView, JButton button) {
        try{
            button.addActionListener(e -> {
                Client client = new ClientRepository().login(indexView.getTextFieldClientEmail().getText(), indexView.getTextFieldClientPassword().getText());
                if(client.getEmail().equals(indexView.getTextFieldClientEmail().getText())){
                    if(client.getPassword().equals(indexView.getTextFieldClientPassword().getText())){
                        indexView.dispose();
                        new ClientPanelView(client);
                    }
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void loginStaff(JButton button) {

    }
}
