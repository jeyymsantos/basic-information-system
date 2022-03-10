
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class DatabaseModel{

	// General Declaration of SQL Connection
	String connection = "jdbc:mysql://localhost:3306/ite201infosystem";
	String username = "anggandako";
	String password = "totoongtotoo";
	
	// Declaration of Global Variables
	Connection conn;
	PreparedStatement stmnt;
	ResultSet rs;
	String query;
	
	// Adds Data to the Database	
	public void AddData(String studName, String studID, String studSex) {

		// Checks the StudID if it exists or not 
		if(!CheckStudID(studID)) {
			try {
				conn = DriverManager.getConnection(connection, username, password);
				query = "INSERT INTO tblStudents(studName, studID, studSex) VALUES (?, ?, ?)";
				
				stmnt = conn.prepareStatement(query);
				stmnt.setString(1, studName);
				stmnt.setString(2, studID);
				stmnt.setString(3, studSex);
				
				stmnt.execute();
				conn.close();
				
				JOptionPane.showMessageDialog(null, "Student is saved successfully!");
			} catch (Exception e1) {
				System.out.println(e1);
			}
		
		// If the StudID exists, it means that it is already taken and must not be used as an ID again
		}else {
			JOptionPane.showMessageDialog(null, "Sorry! ID already taken!",  "Oops! Check the error!", JOptionPane.ERROR_MESSAGE);	
		}
	}
	
	// Checks the Student ID if it exists or not
	public boolean CheckStudID(String studID) {
		try {
			conn = DriverManager.getConnection(connection, username, password);
			query = "SELECT * FROM tblStudents WHERE studID = ?";
			
			stmnt = conn.prepareStatement(query);
			stmnt.setString(1, studID);
			
			rs = stmnt.executeQuery();
			
			while(rs.next()) {
				conn.close();
				return true;
			}			
		} catch (Exception e1) {
			System.out.println(e1);
		}
		return false;
	}
	
	// Displays the data in the JTable
	public void DisplayData(JTable tblStudents) {
		try {
			conn = DriverManager.getConnection(connection, username, password);
			query = "SELECT * FROM tblStudents";
			stmnt = conn.prepareStatement(query);
			rs = stmnt.executeQuery();
			
			tblStudents.setModel(DbUtils.resultSetToTableModel(rs));
			conn.close();

		} catch (Exception e1) {
			System.out.println(e1);
		}
	}
	
	// Reflects the selected row from the JTable to the JTextFields
	public void ShowData(String value, JTextField studID, JTextField studName, JComboBox<String> studSex) {
		try {
			conn = DriverManager.getConnection(connection, username, password);
			query = "SELECT * FROM tblStudents WHERE studID = ?";
			
			stmnt = conn.prepareStatement(query);
			stmnt.setString(1, value);
			
			rs = stmnt.executeQuery();
			
			while(rs.next()) {
				studID.setText(rs.getString(1));
				studName.setText(rs.getString(2));
				studSex.setSelectedItem(rs.getString(3));
			}
			
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// Reflects the matched data from the given value on the Search TextFields
	public void KeyDisplayData(String search, JTable tblStudents) {
		try {
			conn = DriverManager.getConnection(connection, username, password);
			query = "SELECT * FROM tblStudents WHERE studID = ? OR studName = ? OR studSex = ?";
			
			stmnt = conn.prepareStatement(query);
			stmnt.setString(1, search);
			stmnt.setString(2, search);
			stmnt.setString(3, search);
			
			rs = stmnt.executeQuery();
			tblStudents.setModel(DbUtils.resultSetToTableModel(rs));
			
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// Update Data to the Database
	public void UpdateData(String txtStudName, String txtStudID, String txtCombo) {
		
		// Checks the StudID if it exists or not before updating data
		if(CheckStudID(txtStudID)) {
			try {
				conn = DriverManager.getConnection(connection, username, password);
				query = "UPDATE tblStudents SET studName=?, studSex=? WHERE studID = ?";
				stmnt = conn.prepareStatement(query);
				
				stmnt.setString(1, txtStudName);
				stmnt.setString(2, txtCombo);
				stmnt.setString(3, txtStudID);
				
				stmnt.executeUpdate();
				conn.close();
				
				JOptionPane.showMessageDialog(null, "Student " + txtStudID + "  is updated successfully!");
				
			} catch (Exception e1) {
				System.out.println(e1.toString());
			}
			
		// Displays a dialog to inform that the ID given does not exist thus, unable to update data
		}else {
			JOptionPane.showMessageDialog(null, "Sorry! That ID does not exist!",  "Oops! Check the error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// Deletes data from the Database
	public void DeleteData(String txtStudID) {
		
		// Checks the StudID if it exists or not before updating data
		if(CheckStudID(txtStudID)) {
			try {
				conn = DriverManager.getConnection(connection, username, password);
				query = "DELETE FROM tblStudents WHERE studID = ?";
				stmnt = conn.prepareStatement(query);
				
				stmnt.setString(1, txtStudID);
				
				stmnt.execute();
				conn.close();
				
				JOptionPane.showMessageDialog(null, "Student " + txtStudID + "  is deleted successfully!");
				
			} catch (Exception e1) {
				System.out.println(e1.toString());
			}
			
		// Displays a dialog to inform that the ID given does not exist thus, unable to update data
		}else {
			JOptionPane.showMessageDialog(null, "Sorry! That ID does not exist!",  "Oops! Check the error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// Checks the TextFields if it has values
	public boolean MissingFields(String txtStudName, String txtStudID) {
		if(txtStudName.equals("") || txtStudID.equals("")) {
			JOptionPane.showMessageDialog(null, "Please ensure that all fields have valid input!", "Oops! Check the error!", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		return false;
	}
	
	// Clears the TextFields, Set the ComboBox to Default value, and setting the focus to the ID field.
	public void ClearFields(JTextField txtStudName, JTextField txtStudID, JComboBox<String> comboSex) {
		txtStudName.setText("");
		txtStudID.setText("");
		comboSex.setSelectedIndex(0);
		txtStudID.requestFocus();
	}
	

}
