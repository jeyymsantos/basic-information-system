
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;

public class frmStudents extends JFrame{

	// To supress the warning of the class in terms of serialization
	private static final long serialVersionUID = 1L;
	
	// ID Row Declaration
	JLabel lblStudID;
	JTextField txtStudID;
	
	// Name Row Declaration 
	JLabel lblStudName;
	JTextField txtStudName;
	
	// Sex Row Declaration
	JLabel lblSex;
	JComboBox<String> comboSex;
	
	// Table Row Declaration
	JTable tblStudents;
	JScrollPane scrollPane;
	
	// Search Row Declaration
	JLabel lblSearch;
	JTextField txtSearch;
	
	// JButtons Declaration
	JButton btnSave;
	JButton btnUpdate;
	JButton btnPopulate;
	JButton btnDelete;
	
	// Create an Instance of DatabaseModel to access the reusable codes
	DatabaseModel model = new DatabaseModel();
	
	public frmStudents() {
		super("Students' Form");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Sets the Content Layout
		JPanel content = new JPanel();
		setContentPane(content);
		content.setBackground(new Color(216, 182, 56));
		content.setLayout(null);
		
		// NU Baliwag Logo
		JLabel imgLabel = new JLabel();
		imgLabel.setIcon(new ImageIcon(new ImageIcon("assets/nu baliwag.png").getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT)));
		imgLabel.setBounds(43, 23, 50, 60);
		content.add(imgLabel);
		
		//Row for Title & Design
		JLabel lblTitle = new JLabel("STUDENT RECORDS");
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblTitle.setBounds(95, 35, 400, 30);
		content.add(lblTitle);
		
		//Row for Student ID
		lblStudID = new JLabel("Student ID");
		lblStudID.setBounds(100, 100, 200, 30);
		content.add(lblStudID);
		
		txtStudID = new JTextField(8);
		txtStudID.setBounds(220, 100, 200, 30);
		content.add(txtStudID);
		
		//Row for Student Name
		lblStudName = new JLabel("Student Name");
		lblStudName.setBounds(100, 135, 200, 30);
		content.add(lblStudName);
		
		txtStudName = new JTextField(8);
		txtStudName.setBounds(220, 135, 200, 30);
		content.add(txtStudName);
		
		// Row for Sex
		lblSex = new JLabel("Sex");
		lblSex.setBounds(100, 170, 200, 30);
		content.add(lblSex);
		
		String options[] = {"Male", "Female"};
		comboSex = new JComboBox<>(options);
		comboSex.setBounds(220, 170, 200, 30);
		content.add(comboSex);
		
		// Row for Table
		tblStudents = new JTable();
		scrollPane = new JScrollPane(tblStudents);
		scrollPane.setBounds(85, 240, 350, 280);
		content.add(scrollPane);
		
		tblStudents.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
				  
				  // Gets the Selected Row to Display the Data on the TextFields
				  int x = tblStudents.getSelectedRow();
				  String tblID = tblStudents.getModel().getValueAt(x, 0).toString();
			      model.ShowData(tblID,
			    		  		 txtStudID, 
			    		  		 txtStudName, 
			    		  		 comboSex);  
			  }
			});
		
		// Row for Search
		lblSearch = new JLabel("Search");
		lblSearch.setBounds(100, 205, 200, 30);
		content.add(lblSearch);
		
		txtSearch = new JTextField(8);
		txtSearch.setBounds(220, 205, 200, 30);
		content.add(txtSearch);
		
		txtSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent patola) {
				
				// Displays all the data on the JTable if there is no input
				if(txtSearch.getText().equals("")) {
					model.DisplayData(tblStudents);
				
				// Displays the matching data for every key presses
				}else {
					model.KeyDisplayData(txtSearch.getText(), tblStudents);						
				}
			}
		});
		
		//////////////////////
		// Row for Buttons //
		////////////////////
		btnSave = new JButton("Save");
		btnSave.setBounds(50, 550, 100, 30);
		btnSave.setBackground(new Color(17, 117, 73));
		btnSave.setForeground(Color.white);
		content.add(btnSave);
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Validates the TextFields if it has null values
				if(!model.MissingFields(txtStudName.getText(), txtStudID.getText())) {
					
					// Adds the validated data to the database using the model
					model.AddData(txtStudName.getText(),
							  txtStudID.getText(),
							  comboSex.getSelectedItem().toString());
				
					//Updates the data on the JTable
					model.DisplayData(tblStudents);
					
					// Clear the TextFields and sets the focus back to Student ID using the model
					model.ClearFields(txtStudName, txtStudID, comboSex);
				}
				
			}
		});		
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(160, 550, 100, 30);
		btnUpdate.setBackground(new Color(53, 64, 142));
		btnUpdate.setForeground(Color.white);
		content.add(btnUpdate);
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Validates the TextFields if it has null values
				if(!model.MissingFields(txtStudName.getText(), txtStudID.getText())) {
					
					// Asks the user to Confirm the Delete Action
					int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to update?", "Update Student", JOptionPane.YES_NO_OPTION);
					if(confirm == JOptionPane.YES_OPTION) {
	
						// Updates the validated data to the database using the model
						model.UpdateData(txtStudName.getText(),
							  txtStudID.getText(),
							  comboSex.getSelectedItem().toString());
					}
					
					// Updates the data on the JTable
					model.DisplayData(tblStudents);
					
					// Clear the TextFields and sets the focus back to Student ID using the model
					model.ClearFields(txtStudName, txtStudID, comboSex);
				}	
			}
		});
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(270, 550, 100, 30);
		btnDelete.setBackground(new Color(187, 40, 40));
		btnDelete.setForeground(Color.white);
		content.add(btnDelete);
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				// Checks the StudID field if it is empty or not
				if(!txtStudID.getText().equals("")) {
				
					// Asks the user to Confirm the Delete Action
					int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Delete Student", JOptionPane.YES_NO_OPTION);
					if(confirm == JOptionPane.YES_OPTION) {
						// Deletes the data in the JTable
						model.DeleteData(txtStudID.getText());	
					}
					
					// Clear the fields and Displays Updated Table
					model.ClearFields(txtStudName, txtStudID, comboSex);
					model.DisplayData(tblStudents);	
				}
				
				// Displays an error dialog for a null ID
				else {
					JOptionPane.showMessageDialog(null, "Please enter a Student ID to delete!", "Oops! Check the error!", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		
		btnPopulate = new JButton("View List");
		btnPopulate.setBounds(380, 550, 100, 30);
		btnPopulate.setBackground(new Color(154, 67, 136));
		btnPopulate.setForeground(Color.white);
		content.add(btnPopulate);
		
		btnPopulate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				// Displays the data in the JTable
				model.DisplayData(tblStudents);
				JOptionPane.showMessageDialog(null, "Your table is now populated with data!");
			}
		});
	}
}
