package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import domain.Human;
import domain.HumanRepository;

//Text User Interface
public class TUI { 
	
	private static Scanner in = new Scanner(System.in);
	
	public static int printMenu() {
		int option;
		System.err.println("#######--OPTIONS--#######\n"
				+ " 1. Show Subscribers\n"
				+ " 2. Add Subscriber\n"
				+ " 3. Edit Subscriber\n"
				+ " 4. Delete Subscriber\n"
				+ " 5. Send Email\n"
				+ " 0. Exit\n"
				+ "#########################\n"
				+ "Choose OPTION:");
		
		option = in.nextInt();
		return option;
	}
	
	public static void printThanks() {
		System.out.println("Thank You for using our Software!!!");
	}
	
	public static Human readHumanData() {
		Human h = null;
		System.out.println("New Subscriber Full Name:");
		String fullName = in.next();
		System.out.println("New Subscriber Email:");
		String email = in.next();
		h = new Human (fullName, email);
		System.out.println("Successfully Saved:)");
		return h;
	}
	
	public static void showAllSubscribers() throws SQLException{
		ArrayList<Human> people = new ArrayList<Human>();
		HumanRepository hr = new HumanRepository();
		people = hr.all();
	    for (Human human : people) {
			System.out.println(human);
		}
	}
	
	public static Human editHuman() {
		Human h = null;
		System.out.println("Existent Subscriber Full Name:");
		String fullName = in.next();
		System.out.println("New Subscriber Email:");
		String email = in.next();
		h = new Human(fullName, email);
		System.out.println("Successfully Edited:)");
		return h;
	}
	
	public static String emailForDeletingHumanData() {
		String email = null;
		System.out.println("Write Subscriber Email for deleting: ");
		email = in.next();
		System.out.println("Successfully Deleted:)");
		return email;
	}	
	
	public static void prepareEmail(Human human, String theme, String text) throws SQLException {
		//The object of the email message
			Email email = EmailBuilder.startingBlank()
			    .from("Ann K.", "ann.programming@gmail.com")
			    .to(human.getFullName(), human.getEmail())
			    .cc("C. Bo <chocobo@candyshop.org>")
			    .withSubject( theme )
			    .withPlainText( text )
			    .buildEmail();
		
		//The mailer object 
		Mailer mailer = MailerBuilder
		  .withSMTPServer("smtp.gmail.com", 587, "ann.programming@gmail.com", "qaz1wsx2edc3")
		  .withTransportStrategy(TransportStrategy.SMTP_TLS)
		  .withDebugLogging(true) //debugging
		  .buildMailer();
		
		 mailer.sendMail(email);
	}
	
	public static void sendEmail() throws SQLException, InterruptedException {
		String theme = TUI.readEmailTheme();
		String text = TUI.readEmailText();
		
		 ArrayList<Human> people = new ArrayList<Human>();
		 HumanRepository hr = new HumanRepository();
			people = hr.all();
		    for (Human h : people) {
		    	 TUI.prepareEmail(h, theme, text);
		    	 Thread.sleep((long)Math.random() * 100000);
			}	 
	}
	
	public static String readEmailTheme() {
		System.out.println("Write Theme of the Email:");
		String theme = in.next();
		return theme;
	}
	
	public static String readEmailText() {
		System.out.println("Write Text of the Email:");
		String text = in.next();
		return text;
	}
	
	
}
