package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

import domain.Human;
import domain.HumanRepository;


//FOR THE SUBSCRIBERS PROJECT
public class GUIApplication {

	public static void main(String[] args) throws SQLException {
		
		HumanRepository hr = new HumanRepository();
		List<Human> subscribers_emails = new ArrayList<Human>();
		subscribers_emails = hr.all();
		
		JFrame window = new JFrame("Simple GUI App");
		window.setSize(600, 600);
		
		window.getContentPane().setLayout(new GridBagLayout());
		
		GridBagConstraints constrains = new GridBagConstraints();
		constrains.fill = GridBagConstraints.HORIZONTAL;
		constrains.anchor = GridBagConstraints.FIRST_LINE_START;
		constrains.ipadx = 30;
		constrains.ipady = 20;
		
		JList listEmail = new JList(subscribers_emails.toArray());
		constrains.gridx = 0;
		constrains.gridy = 0;
		constrains.gridheight = 3;
		window.getContentPane().add(listEmail,constrains);
		
		JTextField emailField = new JTextField();
		constrains.gridx = 1;
		constrains.gridy = 0;
		constrains.gridheight = 1;
		window.getContentPane().add(emailField,constrains);
		
		JTextField nameField = new JTextField();
		constrains.gridx = 1;
		constrains.gridy = 1;
		constrains.gridheight = 1;
		window.getContentPane().add(nameField,constrains);
		
		JButton btnAdd = new JButton("ADD");
		constrains.gridx = 1;
		constrains.gridy = 2;
		constrains.gridheight = 1;
		window.getContentPane().add(btnAdd,constrains);
		
		window.setVisible(true);
		
		btnAdd.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String email = emailField.getText();
				String name = nameField.getText();
				System.out.println(email + " " + name); 
			}
		});
		
            btnAdd.addActionListener( new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String email = emailField.getText();
				String name = nameField.getText();				
					try {
						hr.create(new Human(name, email ));
						System.out.println("Done!");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}			
		});	
	}
}
