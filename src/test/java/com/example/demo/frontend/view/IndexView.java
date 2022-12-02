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
    private JButton buttonClientCreate;
    private JButton buttonStaffCreate;
    private JButton buttonClientLogin;
    private JButton buttonStaffLogin;
    private JTextField textFieldClientEmail;
    private JTextField textFieldClientPassword;
    private JTextField textStaffEmail;
    private JTextField textStaffPassword;
    private String clientStatus;
    private JLabel clientLabel;



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

        setTextFieldClientEmail(new JTextField());
        getTextFieldClientEmail().setPreferredSize(new Dimension(250, 40));
        getTextFieldClientEmail().setBounds(70, 150, 80, 25);
        getTextFieldClientEmail().setText("email");
        this.add(getTextFieldClientEmail());

        setTextFieldClientPassword(new JTextField());
        getTextFieldClientPassword().setPreferredSize(new Dimension(250, 40));
        getTextFieldClientPassword().setBounds(70, 180, 80, 25);
        getTextFieldClientPassword().setText("password");
        this.add(getTextFieldClientPassword());

        setTextStaffEmail(new JTextField());
        getTextStaffEmail().setPreferredSize(new Dimension(250, 40));
        getTextStaffEmail().setBounds(300, 150, 80, 25);
        getTextStaffEmail().setText("email");
        this.add(getTextStaffEmail());

        setTextStaffPassword(new JTextField());
        getTextStaffPassword().setPreferredSize(new Dimension(250, 40));
        getTextStaffPassword().setBounds(300, 180, 80, 25);
        getTextStaffPassword().setText("password");
        this.add(getTextStaffPassword());

        setButtonClientLogin(new JButton());
        getButtonClientLogin().setText("login");
        getButtonClientLogin().setBounds(70, 220, 200, 200);
        getButtonClientLogin().setSize(75, 25);
        getButtonClientLogin().setFocusable(false);

        getIndexModel().loginClient(this, getButtonClientLogin());
        this.add(this.getButtonClientLogin());

        if(getClientStatus() != null){
            JLabel incorrectCredentials = new JLabel();
            incorrectCredentials.setText("Incorrect Credentials");
            incorrectCredentials.setBounds(40, 370, 80, 40);
            this.add(incorrectCredentials);
        }

        setButtonClientCreate(new JButton());
        getButtonClientCreate().setText("create");
        getButtonClientCreate().setBounds(70, 250, 200, 200);
        getButtonClientCreate().setSize(75, 25);
        getButtonClientCreate().setFocusable(false);
        getIndexModel().createClient(this, getButtonClientCreate());
        this.add(getButtonClientCreate());

        setButtonStaffLogin(new JButton());
        getButtonStaffLogin().setText("login");
        getButtonStaffLogin().setBounds(300, 220, 200, 200);
        getButtonStaffLogin().setSize(75, 25);
        getButtonStaffLogin().setFocusable(false);
        getIndexModel().loginStaff(this, getButtonStaffLogin());
        this.add(getButtonStaffLogin());

        setButtonStaffCreate(new JButton());
        getButtonStaffCreate().setText("create");
        getButtonStaffCreate().setBounds(300, 250, 200, 200);
        getButtonStaffCreate().setSize(75, 25);
        getButtonStaffCreate().setFocusable(false);
        getIndexModel().createStaff(this, getButtonStaffCreate());
        this.add(getButtonStaffCreate());

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
        getTextFieldClientEmail().setText("");
        getTextFieldClientPassword().setText("");
        getTextStaffEmail().setText("");
        getTextStaffPassword().setText("");
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
