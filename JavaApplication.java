package main;

import java.sql.SQLException;

import domain.HumanRepository;

public class JavaApplication {

	private static HumanRepository hr;	
	
	public static void main(String[] args) throws SQLException, InterruptedException {
		hr = new HumanRepository();	
		
		outer:
		while(true) {
			switch(TUI.printMenu()) {
			 case 1: 
			    TUI.showAllSubscribers(); 
			 break;
			 case 2: 
				hr.create( TUI.readHumanData() ); 
		     break;
			 case 3:
			    hr.update( TUI.editHuman() );	 
		     break;
			 case 4: 
			    hr.delete( TUI.emailForDeletingHumanData() );	 
			 break;
			 case 5: 				 
				TUI.sendEmail();
		     break;
			 case 0: 
			    TUI.printThanks();	
			 break outer;
			}		
		}		
	}

}
