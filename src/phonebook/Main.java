package phonebook;
import people.Person;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import people.Vector;

public class Main {
	
	static JTextField nameText;
	static JTextField surnameText;
	static JTextField telephoneText;
	static JTextField addressText;
	static JTextField ageText;
	static String name;
	static String surname;
	static String telephone;
	static Person addPerson;
	static Vector group = new Vector();
	static JTable jt;
	
	public static void main(String[] args) throws FileNotFoundException {
	        
	        JFrame f = new JFrame();
	        
	        group.loadFromFile();
			
			String column [] = {"NAME","SURNAME","TELEPHONE"};
	        
	        DefaultTableModel model = new DefaultTableModel(column, 0);
	    	jt = new JTable(model); 
	        jt.setBounds(30,40,200,300);          
	        JScrollPane sp=new JScrollPane(jt);   
	       
	        
	        for (int i = 0; i < group.getPeopleList().size(); i++) {
	        	Object row [] = {group.getPeopleList().get(i).getName(), group.getPeopleList().get(i).getSurname(), group.getPeopleList().get(i).getTelephoneNumber()};
	        	model.addRow(row);
	        }
	        
	        ImageIcon add = new ImageIcon("./icons/add.png");
	        ImageIcon remove = new ImageIcon("./icons/remove.png");
	        ImageIcon modify = new ImageIcon("./icons/modify.png");
	        ImageIcon save = new ImageIcon("./icons/save.png");
	        ImageIcon cancel = new ImageIcon("./icons/cancel.png");
	        JButton addButton = new JButton(add);
	        addButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JFrame newFrame = new JFrame("ADD PERSON");
	                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	                JLabel nameLabel = new JLabel("Name: ");
	                nameText = new JTextField(32);
	                JLabel surnameLabel = new JLabel("Surname: ");
	                surnameText = new JTextField(32);
	                JLabel telephoneLabel = new JLabel("Telephone:");
	                telephoneText = new JTextField(32);
	                JLabel addressLabel = new JLabel("Address:");
	                addressText = new JTextField(32);
	                JLabel ageLabel = new JLabel("Age:");
	                ageText = new JTextField(32);
	                JPanel p = new JPanel(new GridLayout(3, 2));
	                p.add(nameLabel);
	                p.add(nameText);
	                p.add(surnameLabel);
	                p.add(surnameText);
	                p.add(telephoneLabel);
	                p.add(telephoneText);
	                p.add(addressLabel);
	                p.add(addressText);
	                p.add(ageLabel);
	                p.add(ageText);
	                
	                JToolBar buttonPanel = new JToolBar();
	                JButton addConfermButton = new JButton(save);
	                addConfermButton.addActionListener(new ActionListener() {
	    	            @Override
	    	            public void actionPerformed(ActionEvent e) {
	    	            	String name = nameText.getText();
	    	            	String surname = surnameText.getText();
	    	            	String telephone = telephoneText.getText();
	    	            	String address = addressText.getText();
	    	            	int age = 0;
	    	            	try {
	    	            		age = Integer.parseInt(ageText.getText());
	    	            	} catch (NumberFormatException g) {
	    		                JOptionPane.showMessageDialog(null, "The \"Age\" attribute must be a natural number!");
	    		                return;
	    		                
	    	            	}
	    	            	nameText.setText(" ");
	    	            	surnameText.setText(" ");
	    	            	telephoneText.setText(" ");
	    	            	addressText.setText(" ");
	    	            	ageText.setText(" ");
	    	            	addPerson = new Person(name, surname, address, telephone, age);
	    	            	try {
								group.addPerson(addPerson);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
	    	            	Object row [] = {name, surname, telephone};
	    	            	model.addRow(row);
	    	            	newFrame.setVisible(false);
	    	            }
	                });
	                
	                JButton addCancelButton = new JButton(cancel);
	                addCancelButton.addActionListener(new ActionListener() {
	    	            @Override
	    	            public void actionPerformed(ActionEvent e) {
	    	            	nameText.setText(" ");
	    	            	surnameText.setText(" ");
	    	            	telephoneText.setText(" ");
	    	            	newFrame.setVisible(false);
	    	            }
	                });
	                
	                buttonPanel.add(addConfermButton);
	                buttonPanel.add(addCancelButton);
	                Box container = Box.createVerticalBox();
	                container.add(p);
	                container.add(buttonPanel);
	                newFrame.add(container);
	                newFrame.setSize(600, 400);
	                newFrame.setLocationRelativeTo(null);
	                newFrame.setVisible(true);
	            }});
	        
	        JButton removeButton = new JButton(remove);
	        removeButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	int rowID = jt.getSelectedRow();
	            	if (rowID == -1){
	            		JOptionPane.showMessageDialog(null, "To remove a contact, a row in the table must be selected!");
		                return;
	            	}
	            	int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the contact: " + jt.getValueAt(rowID, 0).toString().toUpperCase() + " " + jt.getValueAt(rowID, 1).toString().toUpperCase() + "?");
	            	
	            	if (result == 0){
		            	try {
							group.removePerson(group.getPeopleList().get(rowID));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
		            	model.removeRow(rowID);
	            	}
	            }
	        });
	        JButton modifyButton = new JButton(modify);
	        modifyButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	int rowID = jt.getSelectedRow();
	            	if (rowID == -1){
	            		JOptionPane.showMessageDialog(null, "To edit a contact, a row in the table must be selected!");
		                return;
	            	}
	            	
	            	JFrame newFrame = new JFrame("MODIFY PERSON");
	                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	            	
	                String name = (String) jt.getValueAt(rowID, 0);
	            	JLabel nameLabel = new JLabel("Name: ");
	            	nameText = new JTextField(32);
	            	nameText.setText(name);
	            	String surname = (String) jt.getValueAt(rowID, 1);
	            	JLabel surnameLabel = new JLabel("Surname: ");
	            	surnameText = new JTextField(32);
	            	surnameText.setText(surname);
	            	String telephone = (String) jt.getValueAt(rowID, 2);
	            	JLabel telephoneLabel = new JLabel("Telephone: ");
	            	telephoneText = new JTextField(32);
	            	telephoneText.setText(telephone);
	            	JLabel addressLabel = new JLabel("Address:");
	                addressText = new JTextField(32);
	                addressText.setText(group.getPeopleList().get(rowID).getAddress());
	                JLabel ageLabel = new JLabel("Age:");
	                ageText = new JTextField(32);
	                ageText.setText("" + group.getPeopleList().get(rowID).getAge());
	            	
	            	
	            	JPanel p = new JPanel(new GridLayout(3, 2));
	                p.add(nameLabel);
	                p.add(nameText);
	                p.add(surnameLabel);
	                p.add(surnameText);
	                p.add(telephoneLabel);
	                p.add(telephoneText);
	                p.add(addressLabel);
	                p.add(addressText);
	                p.add(ageLabel);
	                p.add(ageText);
	                JToolBar buttonPanel = new JToolBar();
	                JButton addConfermButton = new JButton(save);
	                addConfermButton.addActionListener(new ActionListener() {
	    	            @Override
	    	            public void actionPerformed(ActionEvent e) {
	    	            	String name = nameText.getText();
	    	            	String surname = surnameText.getText();
	    	            	String telephone = telephoneText.getText();
	    	            	String address = addressText.getText();
	    	            	int age = -1;
		    	            try {
		    	            	age = Integer.parseInt(ageText.getText());
		    	            } catch (NumberFormatException g) {
		    		            JOptionPane.showMessageDialog(null, "The \"Age\" attribute must be a natural number!");
		    		            return;
		    	            }
	    	            	group.getPeopleList().get(rowID).setName(name);
	    	            	group.getPeopleList().get(rowID).setSurname(surname);
	    	            	group.getPeopleList().get(rowID).setTelephoneNumber(telephone);
	    	            	group.getPeopleList().get(rowID).setAddress(address);
	    	            	group.getPeopleList().get(rowID).setAge(age);
	    	            	try {
								group.saveinFile();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
	    	            	model.removeRow(rowID);
	    	            	Object row [] = {name, surname, telephone};
	    	            	model.insertRow(rowID, row);
	    	            	newFrame.setVisible(false);
	    	            }});
	                JButton addCancelButton = new JButton(cancel);
	                addCancelButton.addActionListener(new ActionListener() {
	    	            @Override
	    	            public void actionPerformed(ActionEvent e) {
	    	            	nameText.setText(" ");
	    	            	surnameText.setText(" ");
	    	            	telephoneText.setText(" ");
	    	            	newFrame.setVisible(false);
	    	            }
	                });
	                buttonPanel.add(addConfermButton);
	                buttonPanel.add(addCancelButton);
	                Box container = Box.createVerticalBox();
	                container.add(p);
	                container.add(buttonPanel);
	                newFrame.add(container);
	                newFrame.setSize(600, 400);
	                newFrame.setLocationRelativeTo(null);
	                newFrame.setVisible(true);
	            }
	        	});
	        JToolBar buttonBar = new JToolBar();
	        buttonBar.setFloatable(false);
	        buttonBar.add(addButton);
	        buttonBar.add(removeButton);
	        buttonBar.add(modifyButton);
	        
	        Box container = Box.createVerticalBox();
	        container.add(sp);
	        container.add(buttonBar);
	        f.add(container);
	        
	        
	        f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        f.setUndecorated(false);  
	        f.setVisible(true);   
	        
	        
	    
	} 
	

}
