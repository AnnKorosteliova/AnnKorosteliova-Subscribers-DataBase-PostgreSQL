package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//MANAGE PERSISTENT DATA
public class HumanRepository {
	
	private final static String url = "jdbc:postgresql://localhost/subscribers?user=postgres&password=Ana079294811";
	private Connection conn; 
	
	public HumanRepository() throws SQLException {
		conn = DriverManager.getConnection(url);
	}
	
	public void create(Human human) throws SQLException {
		Statement st = conn.createStatement();
		st.executeUpdate("INSERT INTO public.people(\r\n" + 
				"	full_name, email)\r\n" + 
				"	VALUES ( '" + human.getFullName() + "' , '" + human.getEmail() + "' );"); 
	}
	
	public void delete(String email) throws SQLException {
		Statement st = conn.createStatement();
		st.executeUpdate("DELETE FROM public.people\r\n" + 
				"	WHERE email = '" + email + "';"); 
	}
	
	public void update(Human human) throws SQLException {
		Statement st = conn.createStatement();
		st.executeUpdate("UPDATE public.people\r\n" + 
				"	SET email= '" + human.getEmail() + 
				"	'WHERE full_name = '" + human.getFullName() + "';"); 
	}
	
	public ArrayList<Human> all() throws SQLException{
		ArrayList<Human> people = new ArrayList<Human>();
		Statement st = conn.createStatement();
		ResultSet result =  st.executeQuery("SELECT * \r\n" + 
				"	FROM public.people;");
		
		while(result.next()) { //false if no more data / no more rows
			people.add( new Human(result.getString("full_name"), result.getString("email")) );
		}
		return people;
	}
}
