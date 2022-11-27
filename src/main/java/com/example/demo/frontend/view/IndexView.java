package com.example.demo.frontend.view;

import com.example.demo.frontend.model.IndexModel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Getter
@Setter
public class IndexView extends JFrame implements MouseListener {

    private final IndexModel indexModel = new IndexModel();

    public JButton buttonClientCreate;
    public JButton buttonStaffCreate;

    public JButton buttonClientLogin;
    public JButton buttonStaffLogin;

    public JTextField textFieldClientEmail;
    public JTextField textFieldClientPassword;

    public JTextField textStaffEmail;

    public JTextField textStaffPassword;
    public String clientStatus;
    public JLabel clientLabel;



    public IndexView() {
        addMouseListener(this);

        JLabel clientLabel = new JLabel();
        clientLabel.setText("Client");
        clientLabel.setBounds(80, 35, 200, 200);
        this.add(clientLabel);

        JLabel adminLabel = new JLabel();
        adminLabel.setText("Management and Staff");
        adminLabel.setBounds(275, 35, 200, 200);
        this.add(adminLabel);

        this.textFieldClientEmail = new JTextField();
        this.textFieldClientEmail.setPreferredSize(new Dimension(250, 40));
        this.textFieldClientEmail.setBounds(70, 150, 80, 25);
        this.textFieldClientEmail.setText("email");
        this.add(this.textFieldClientEmail);

        this.textFieldClientPassword = new JTextField();
        this.textFieldClientPassword.setPreferredSize(new Dimension(250, 40));
        this.textFieldClientPassword.setBounds(70, 180, 80, 25);
        this.textFieldClientPassword.setText("password");
        this.add( this.textFieldClientPassword);

        this.textStaffEmail = new JTextField();
        this.textStaffEmail.setPreferredSize(new Dimension(250, 40));
        this.textStaffEmail.setBounds(300, 150, 80, 25);
        this.textStaffEmail.setText("email");
        this.add(this.textStaffEmail);

        this.textStaffPassword = new JTextField();
        this.textStaffPassword.setPreferredSize(new Dimension(250, 40));
        this.textStaffPassword.setBounds(300, 180, 80, 25);
        this.textStaffPassword.setText("password");
        this.add(this.textStaffPassword);

        this.buttonClientLogin = new JButton();
        this.buttonClientLogin.setText("login");
        this.buttonClientLogin.setBounds(70, 220, 200, 200);
        this.buttonClientLogin.setSize(75, 25);
        this.buttonClientLogin.setFocusable(false);
        this.indexModel.loginClient(this, buttonClientLogin);
        this.add(this.buttonClientLogin);

        if(clientStatus != null){
            JLabel incorrectCredentials = new JLabel();
            incorrectCredentials.setText("Incorrect Credentials");
            incorrectCredentials.setBounds(40, 370, 80, 40);
            this.add(incorrectCredentials);
        }

        this.buttonClientCreate = new JButton();
        this.buttonClientCreate.setText("create");
        this.buttonClientCreate.setBounds(70, 250, 200, 200);
        this.buttonClientCreate.setSize(75, 25);
        this.buttonClientCreate.setFocusable(false);
        this.indexModel.createClient(this, this.buttonClientCreate);
        this.add(this.buttonClientCreate);

        this.buttonStaffLogin = new JButton();
        this.buttonStaffLogin.setText("login");
        this.buttonStaffLogin.setBounds(300, 220, 200, 200);
        this.buttonStaffLogin.setSize(75, 25);
        this.buttonStaffLogin.setFocusable(false);
        this.indexModel.loginStaff(this, this.buttonStaffLogin);
        this.add(this.buttonStaffLogin);

        this.buttonStaffCreate = new JButton();
        this.buttonStaffCreate.setText("create");
        this.buttonStaffCreate.setBounds(300, 250, 200, 200);
        this.buttonStaffCreate.setSize(75, 25);
        this.buttonStaffCreate.setFocusable(false);
        this.indexModel.createStaff(this, this.buttonStaffCreate);
        this.add(this.buttonStaffCreate);

        this.setLayout(null);
        this.setTitle("Customer Management Application");
        this.setSize(500, 500);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        textFieldClientEmail.setText("");
        textFieldClientPassword.setText("");
        textStaffEmail.setText("");
        textStaffPassword.setText("");
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
