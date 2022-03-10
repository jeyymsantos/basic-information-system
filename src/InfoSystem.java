
/*
*	Subject			: Intermediate Programming
*	School			: National U - Baliwag
*	System Name 	: Information System (Console)
*	Developed by	: Jeyym Santos
*	Course/Section	: ITE201
*/

public class InfoSystem {

	// Main Method, where the program starts.
	public static void main(String[] args) {
		
		// Creates an instance of the object -> menu
		menu frame = new menu();
		
		// Sets the size of the frame
		frame.setSize(400, 450);
		
		// Makes the frame be displayed on the center screen
		frame.setLocationRelativeTo(null);
		
		// Shows the frame on the screen
		frame.setVisible(true);
		
	}

}
