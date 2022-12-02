package com.example.demo.frontend.view;

import com.example.demo.frontend.model.CreateAdminModel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Getter
@Setter
public class CreateAdminView extends JFrame implements MouseListener {
    private JTextField firstName;
    private JTextField lastName;
    private JTextField password;
    private JTextField email;
    private final CreateAdminModel createAdminModel = new CreateAdminModel();

    public CreateAdminView() {
        addMouseListener(this);
        JLabel firstNameLabel = new JLabel();
        firstNameLabel.setText("First Name: ");
        firstNameLabel.setBounds(40, 50, 70, 40);
        this.add(firstNameLabel);

        JLabel lastNameLabel = new JLabel();
        lastNameLabel.setText("Last Name: ");
        lastNameLabel.setBounds(40, 100, 70, 40);
        this.add(lastNameLabel);

        JLabel emailLabel = new JLabel();
        emailLabel.setText("E-Mail: ");
        emailLabel.setBounds(40, 150, 70, 40);
        this.add(emailLabel);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password: ");
        passwordLabel.setBounds(40, 200, 70, 40);
        this.add(passwordLabel);

        setFirstName(new JTextField());
        getFirstName().setText("...");
        getFirstName().setBounds(150, 60, 150, 20);
        this.add(getFirstName());

        setLastName(new JTextField());
        getLastName().setText("...");
        getLastName().setBounds(150, 110, 150, 20);
        this.add(getLastName());

        setEmail(new JTextField());
        getEmail().setText("...");
        getEmail().setBounds(150, 160, 150, 20);
        this.add(getEmail());

        setPassword(new JTextField());
        getPassword().setText("...");
        getPassword().setBounds(150, 210, 150, 20);
        this.add(getPassword());

        JButton backButton = new JButton();
        backButton.setText("back");
        backButton.setBounds(130, 250, 200, 200);
        backButton.setSize(85, 30);
        backButton.setFocusable(false);
        getCreateAdminModel().backToMain(this, backButton);
        this.add(backButton);

        JButton submitButton = new JButton();
        submitButton.setText("submit");
        submitButton.setBounds(220, 250, 200, 200);
        submitButton.setSize(85, 30);
        submitButton.setFocusable(false);
        getCreateAdminModel().createAdmin(this, submitButton);
        this.add(submitButton);

        this.setLayout(null);
        this.setTitle("Application Form");
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
        getFirstName().setText("");
        getLastName().setText("");
        getEmail().setText("");
        getPassword().setText("");
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
