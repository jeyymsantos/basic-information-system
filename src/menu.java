
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class menu extends JFrame{
	
	// To supress the warning of the class in terms of serialization
	private static final long serialVersionUID = 1L;
	
	JMenuBar menubar = new JMenuBar();
	JMenu File = new JMenu("File");
	JMenuItem frmStudentsMenu = new JMenuItem("Students");
	JMenuItem frmCourse = new JMenuItem("Course");
	JMenuItem frmExit = new JMenuItem("Exit");
		
	JMenu Transaction = new JMenu("Transaction");
	JMenuItem frmAssessment = new JMenuItem("Assessment");
	
	ExitDialog exit = new ExitDialog();
	CallStudent openStudent = new CallStudent();
	
	// Declare Base MenuBar, Menu & MenuItem
	public menu() {
		super("Jeyym's Information System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set Menubar and Design
		setJMenuBar(menubar);
		menubar.setBackground(new Color(53, 64, 142));
		File.setForeground(Color.white);
		Transaction.setForeground(Color.white);
		
		//Menu/File
		menubar.add(File);
		File.setMnemonic('F');
		
		File.add(frmStudentsMenu);
		frmStudentsMenu.setMnemonic('S');
		frmStudentsMenu.addActionListener(openStudent);
		
		File.add(frmCourse);
		frmCourse.setMnemonic('C');
		
		File.add(frmExit);
		frmExit.setMnemonic('X');
		frmExit.addActionListener(exit);
		
		//Menu/Transaction
		menubar.add(Transaction);
		Transaction.setMnemonic('T');
		
		Transaction.add(frmAssessment);
		frmAssessment.setMnemonic('P');
		
		// Set Content
		setContent();
	}
	
	void setContent() {
		
		// Set the Panel to Add the Content
		JPanel content = new JPanel();
		setContentPane(content);
		content.setBackground(new Color(216, 182, 56));
		content.setLayout(null);
		
		// Welcome Message
		JLabel message = new JLabel("Welcome to my Information System!");
		message.setFont(new Font("Times New Roman", Font.BOLD, 20));
		message.setBounds(37, 200, 350, 30);
		content.add(message);
		
		// Developer Message
		JLabel developer = new JLabel("Developer: Jeyym Santos");
		developer.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		developer.setBounds(95, 225, 350, 30);
		content.add(developer);
		
		// NU Baliwag Logo
		JLabel imgLabel = new JLabel();
		imgLabel.setIcon(new ImageIcon(new ImageIcon("assets/nu baliwag.png").getImage().getScaledInstance(100, 125, Image.SCALE_DEFAULT)));
		imgLabel.setBounds(135, 75, 100, 125);
		content.add(imgLabel);
	}
	
	public class ExitDialog implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int ans = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
			if(ans == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
			
		}
	}
	
	public class CallStudent implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			frmStudents unggoy = new frmStudents();
			unggoy.setSize(550, 675);
			
			setVisible(false);
			
			unggoy.setVisible(true);
			unggoy.setLocationRelativeTo(null);
			
		}
		
	}
		
}
