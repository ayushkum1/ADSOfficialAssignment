package core;

import java.util.Arrays;
import java.util.Objects;

import exceptions.RecordAlreadyExistsException;

public class PhoneDirectory {

	private Customer[] table;
	private int size;

	public PhoneDirectory(int size)

	{
		this.size = size;

		table = new Customer[size];

	}

	private int hashFunction(long phone) {
		return (int) (phone % size);
	}

	public void add(String name, String addr, long phoneno) throws RecordAlreadyExistsException{
		long key = phoneno;
		boolean exist = false;
		int bucketId = hashFunction(key);
		Customer cus = table[bucketId];
		while (cus != null) {
			if (cus.getPhone() == phoneno) {
				exist = true;
				break;
			}

			cus = cus.next;

		}
		if (!exist) {
			if (cus != null) {
				cus.name = name;
				cus.address = addr;
				System.out.println("User addded Sucessfully");
			} else {

				Customer s = new Customer();
				s.address = addr;
				s.name = name;
				s.phone = phoneno;

				s.next = table[bucketId];
				table[bucketId] = s;
				System.out.println("User addded Sucessfully");
			}
		} else {
			throw new RecordAlreadyExistsException("Phone No Already exits Please Enter another");
		}
	}

	public void remove(long phoneno) {

		long key = phoneno;
		int bucketId = hashFunction(key);

		if (table[bucketId] == null) {
			return;
		}

		if (table[bucketId].phone == phoneno) {
			table[bucketId] = table[bucketId].next;
			System.out.println(" deleted Successfully");
			return;
		}

		Customer prev = table[bucketId]; 
		Customer curr = prev.next; 
		while (curr != null && !(curr.phone == phoneno)) {
			prev = curr;
			curr = curr.next;

		}
		if (curr != null) {
			prev.next = curr.next;
			System.out.println(" deleted Successfully");
		}

	}

	public String searchByPhoneno(long phone) {
		long key;
		key = phone;
		int bucketId = hashFunction(key);
		Customer list = table[bucketId]; 
		while (list != null) {
			if (list.phone == phone)

				return list.toString();
			list = list.next;
		}

		return "Oops No Such User Exists!!";
	}

	public void searchByName(String name) {
		boolean flag = false;

		for (int i = 0; i < table.length; i++) {

			Customer list = table[i]; 
			while (list != null) {
				if (list.getName().equals(name)) {
					System.out.println(list.toString());
					flag = true;
				}
				list = list.next;

			}
		}
		if (!flag) {
			System.out.println("Oops No Such User Exists!!");
		}
	}

	public void print() {
		
		Arrays.asList(table).stream().filter(Objects::nonNull).forEach(System.out::println);
	
	}

}
