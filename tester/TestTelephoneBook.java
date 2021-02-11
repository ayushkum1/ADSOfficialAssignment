package tester;

import java.util.Scanner;

import core.PhoneDirectory;
import exceptions.InvalidDataException;
import exceptions.RecordAlreadyExistsException;
import utils.ValidationUtils;

public class TestTelephoneBook {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try (Scanner sc = new Scanner(System.in);) {
			boolean exit = false;
			System.out.println("Enter the size of array");
			int size = sc.nextInt();
			PhoneDirectory phoneDirectory = new PhoneDirectory(size);
			while(!exit) {
				System.out.println("*****Welcome to TelephoneBook********");
				System.out.println("-----------------------------------------------------------");
				System.out.println("Menu");
				System.out.println(
						"1.Add user to phoneBook \n2.Delete User from Book \n3.Serach User by Phoneno"
						+ " \n4.Search User by Name \n5.Display All User \n6.Logout");
				System.out.println("Enter your Choice");
				int ch = sc.nextInt();
				switch (ch) {
				case 1:
					try {
						System.out.println("Enter name addr phoneno");
						sc.nextLine();
						String name = sc.nextLine(); 
						String addr = sc.nextLine();
						long phoneno = ValidationUtils.validatePhoneNumber(sc.nextLong());
						phoneDirectory.add(name, addr, phoneno);
					}
					catch (InvalidDataException | RecordAlreadyExistsException e) {
						System.out.println(e.getMessage());
					}
					
					break;
					
				case 2:
					try {
						System.out.println("Enter Phone No to Delete");
						long phone1 = ValidationUtils.validatePhoneNumber(sc.nextLong());
						phoneDirectory.remove(phone1);
					}
					catch (InvalidDataException e) {
						System.out.println(e.getMessage());
					}
					
					break;

				case 3:
					try {
						System.out.println("Enter Phone No to Search");
						long phone = ValidationUtils.validatePhoneNumber(sc.nextLong());
						System.out.println(phoneDirectory.searchByPhoneno(phone));	
					}
					catch (InvalidDataException e) {
						System.out.println(e.getMessage());
					}
					break;
				
				case 4:
					System.out.println("Enter name to Search");
					sc.nextLine();
					String name1 = sc.nextLine();
					phoneDirectory.searchByName(name1);
					break;
				
				case 5:
					System.out.println("TelephoneBook Contains Users");
					phoneDirectory.print();
					break;
				
				case 6:
					exit = true;
					System.exit(0);
					break;

				default:
					System.out.println("Invalid Choice Please try again !!");
					break;

				}
			} 
		} catch (Exception e) {
			
			System.out.println(e.getMessage());

		}
	}
}