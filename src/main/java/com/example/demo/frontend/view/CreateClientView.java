package com.example.demo.frontend.view;

import com.example.demo.frontend.model.CreateClientModel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Getter
@Setter

public class CreateClientView extends JFrame implements MouseListener {
    private JTextField firstName;
    private JTextField lastName;
    private JTextField email;
    private JTextField password;


    private final CreateClientModel createClientModel = new CreateClientModel();

    public CreateClientView() {
        addMouseListener(this);
        JLabel firstNameLabel = new JLabel();
        firstNameLabel.setText("First Name: ");
        firstNameLabel.setBounds(40, 50, 70, 40);
        this.add(firstNameLabel);

        JLabel lastNameLabel = new JLabel();
        lastNameLabel.setText("Last Name: ");
        lastNameLabel.setBounds(40, 100, 70, 40);
        this.add(lastNameLabel);

        JLabel eMailLabel = new JLabel();
        eMailLabel.setText("E-Mail: ");
        eMailLabel.setBounds(40, 150, 70, 40);
        this.add(eMailLabel);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password: ");
        passwordLabel.setBounds(40, 200, 70, 40);
        this.add(passwordLabel);

        this.firstName = new JTextField();
        firstName.setText("...");
        firstName.setBounds(150, 60, 150, 20);
        this.add(firstName);

        lastName = new JTextField();
        lastName.setText("...");
        lastName.setBounds(150, 110, 150, 20);
        this.add(lastName);

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
        createClientModel.backToMain(this, backButton);
        this.add(backButton);

        JButton button = new JButton();
        button.setText("submit");
        button.setBounds(220, 250, 200, 200);
        button.setSize(85, 30);
        button.setFocusable(false);
        createClientModel.createClient(this, button);
        this.add(button);

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
        firstName.setText("");
        lastName.setText("");
        email.setText("");
        password.setText("");
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
