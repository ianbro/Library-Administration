/**
 * 
 */
package application.administration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import dbModels.Book;
import dbModels.auth.Card;
import dbModels.auth.Person;

/**
 * @author Ian
 * @date Jun 28, 2015
 * @project library_system
 * @todo TODO
 */
public abstract class CheckoutManagement {

	public static File RECORDS = new File("json/checkout_records.json");
	public static File DUE_DATES = new File("json/due_dates.json");
	
	@SuppressWarnings("unchecked")
	public static boolean checkoutBook(Person person, String isbn) throws FileNotFoundException, ParseException, SQLException{
		boolean retVal = false;
		
		Scanner jsonReader = new Scanner(RECORDS);
		String json = "";
		while(jsonReader.hasNext()){
			json = json.concat(jsonReader.nextLine());
		}
		
		JSONParser parser = new JSONParser();
		JSONObject wholeJSON = (JSONObject) parser.parse(json);
		JSONArray personsBooks = (JSONArray) wholeJSON.get(String.valueOf(person.card.id));
		if(personsBooks != null){
			if(personsBooks.size() < 5){
				personsBooks.add(isbn);
				
				updateDueDate(isbn);
				
				retVal = true;
			} else{
				retVal = false;
			}
		} else{
			JSONArray newPersonBooks = new JSONArray();
			newPersonBooks.add(isbn);
			updateDueDate(isbn);
			wholeJSON.put(String.valueOf(person.card.id), newPersonBooks);
			retVal = true;
		}
		System.out.println(wholeJSON.get(String.valueOf(person.card.id)));
		
		PrintWriter jsonWriter = new PrintWriter(RECORDS);
		
		jsonWriter.print(wholeJSON.toJSONString());
		jsonWriter.close();
		
		return retVal;
	}

	/**
	 * @param isbn
	 * @throws SQLException
	 */
	private static void updateDueDate(String isbn) throws SQLException {
		java.util.Date nowDate = new java.util.Date();
		@SuppressWarnings("deprecation")
		String nowString = (nowDate.getYear() + 1900) + "-" + (nowDate.getMonth() + 1) + "-" + nowDate.getDate();
		Date now = Date.valueOf(nowString);
		Date dueDate = setDueDate(now);
		
		Book book = Book.get(isbn);
		book.dueDate = dueDate;
		ArrayList<Object[]> updates = new ArrayList<Object[]>();
		Object[] dueDateUpdate = {"dueDate", dueDate};
		updates.add(dueDateUpdate);
		book.update(updates);
	}
	
	@SuppressWarnings("unchecked")
	public static boolean returnBook(Person person, String isbn) throws FileNotFoundException, ParseException{
		boolean retVal = false;
		
		Scanner jsonReader = new Scanner(RECORDS);
		String json = "";
		while(jsonReader.hasNext()){
			json = json.concat(jsonReader.nextLine());
		}
		
		JSONParser parser = new JSONParser();
		JSONObject wholeJSON = (JSONObject) parser.parse(json);
		JSONArray personsBooks = (JSONArray) wholeJSON.get(String.valueOf(person.card.id));
		System.out.println(personsBooks);
		
		if(personsBooks == null){
			return false;
		}
		else if(personsBooks.size() > 0 && personsBooks.contains(isbn)){
			personsBooks.remove(isbn);
			wholeJSON.put(String.valueOf(person.card.id), personsBooks);
			retVal = true;
		}
		else {
			retVal = false;
		}
		System.out.println(wholeJSON.get(String.valueOf(person.card.id)));
		
		PrintWriter jsonWriter = new PrintWriter(RECORDS);
		jsonWriter.print(wholeJSON.toJSONString());
		jsonWriter.close();
		
		return retVal;
	}
	
	public static Object[] checkLateDate(Person p) throws FileNotFoundException, ParseException, SQLException{
		Card card = p.card;
		ArrayList<String> lateBooks = new ArrayList<String>();
		
		card.retrieveBooksCheckedOut();
		
		Object[] books = card.books.toArray();
		
		java.util.Date now = new java.util.Date();
		@SuppressWarnings("deprecation")
		String nowString = (now.getYear() + 1900) + "-" + (now.getMonth() + 1) + "-" + now.getDate();
		for(Object bookTemp: books){
			Book book = (Book)bookTemp;
			if(book.dueDate.before(Date.valueOf(nowString))){
				lateBooks.add(book.isbn);
			}
		}
		return lateBooks.toArray();
	}
	
	private static Date setDueDate(Date start){
		int year = start.getYear() + 1900;
		int month = start.getMonth() + 1;
		int date = start.getDate();
		
		int tempMonth = month+1;
		if(tempMonth > 12){
			month = tempMonth-12;
			year ++;
		}
		else{
			month = tempMonth;
		}
		
		Date retVal = Date.valueOf(String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(date));
		
		return retVal;
	}
}
