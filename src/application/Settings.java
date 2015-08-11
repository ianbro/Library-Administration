package application;

import java.util.HashMap;

public class Settings {
	
	// schema name
	// db_username
	// db_password
	// db_host
	// db_port	
	// url root
	static String[][] DATABASES = {
			{
				"library",
				"root",
				"saline54",
				"localhost",
				"3306",
				"jdbc:mysql:"
			},
	};
	
	static int DB_INDEX = 0; //index of which database to use(starts at 0)
}
