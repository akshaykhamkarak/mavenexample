package com.mindtree.mavenexample;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int choice = 0;

		RegistorVisitor[] inputarray = new RegistorVisitor[0];
		do {
			// showing the options
			displayMenu();
			System.out.println("Enter your choice:");

			choice = sc.nextInt();
			switch (choice) {
			case 1:
				// add new record in array
				inputarray = newRecord(inputarray);
				break;
			case 2:
				// Display visitor
				displayVisitor(inputarray);
				break;
			case 3:
				int agefrom = 0, ageto = 0;
				System.out.println("Please give range of the age:");
				System.out.println("starting age:");
				agefrom = sc.nextInt();

				System.out.println("Ending age:");
				ageto = sc.nextInt();

				RegistorVisitor[] specificdata = searchVisitorByGivenRangeWithSortedOrder(inputarray, agefrom, ageto);
				if (specificdata != null) {
					displaySortedVisiterRecord(specificdata);
				} else
					System.out.println("No record found");
				break;
			case 4:
				int id = 0;
				System.out.println("Enter the visitor id which you want to update the age:");
				id = sc.nextInt();
				inputarray = updateSpecificRecordAsgivenId(inputarray, id);
				displayUpdatedAgeRecord(inputarray, id);

				break;
			case 5:

				inputarray = deleteSpecificVisitorRecord(inputarray);
				displaySortedVisiterRecord(inputarray);

				break;
			case 6:
				System.out.println("Exit from menu");
				break;
			default:
				System.out.println("Wrong choice");
				break;
			}
		} while (choice != 6);
	}

	private static RegistorVisitor[] deleteSpecificVisitorRecord(RegistorVisitor[] inputarray) {

		int idfordeleteoperation = 0;
		System.out.println("Please enter the id which record you want to delete:");
		idfordeleteoperation = sc.nextInt();
		int index = 0;
		boolean isvalid = validateId(inputarray, idfordeleteoperation);
		if (isvalid == true) {
			RegistorVisitor temp[] = new RegistorVisitor[inputarray.length - 1];
			for (int i = 0; i < inputarray.length; i++) {
				if (inputarray[i].getId() != idfordeleteoperation) {
					temp[index] = inputarray[i];
					index++;
				}
			}
			return temp;
		}

		return inputarray;
	}

	private static void displayUpdatedAgeRecord(RegistorVisitor[] inputarray, int id) {
		// TODO Auto-generated method stub
		System.out.println("=================Visitor in given age====================");
		for (int i = 0; i < inputarray.length; i++) {
			if (id == inputarray[i].getId()) {
				System.out.println(inputarray[i].getId());
				System.out.println(inputarray[i].getName());
				System.out.println(inputarray[i].getGender());
				System.out.println(inputarray[i].getAge());
				System.out.println("=====================================================");
			}
		}
	}

	private static RegistorVisitor[] updateSpecificRecordAsgivenId(RegistorVisitor[] inputarray, int id) {
		// TODO Auto-generated method stub
		boolean isvalid = validateId(inputarray, id);
		if (isvalid == true) {
			byte newage = 0;
			System.out.println("Enter the new age");
			newage = sc.nextByte();
			for (int i = 0; i < inputarray.length; i++) {
				if (inputarray[i].getId() == id) {
					inputarray[i].setAge(newage);
				}
			}
			return inputarray;
		}
		return inputarray;
	}

	private static boolean validateId(RegistorVisitor[] inputarray, int id) {
		// TODO Auto-generated method stub
		for (int i = 0; i < inputarray.length; i++) {
			if (id == inputarray[i].getId())
				return true;
		}
		System.out.println("wrong Id");
		return false;
	}

	private static void displaySortedVisiterRecord(RegistorVisitor[] inputarray) {
		// TODO Auto-generated method stub
		System.out.println("=================Visitor in given age====================");
		for (int i = 0; i < inputarray.length; i++) {
			System.out.println(inputarray[i].getId());
			System.out.println(inputarray[i].getName());
			System.out.println(inputarray[i].getGender());
			System.out.println(inputarray[i].getAge());
			System.out.println("=====================================================");
		}

	}

	private static RegistorVisitor[] searchVisitorByGivenRangeWithSortedOrder(RegistorVisitor[] inputarray, int agefrom,
			int ageto) {
		// TODO Auto-generated method stub

		int getcount = countforarraylength(inputarray, agefrom, ageto);

		RegistorVisitor temp[] = new RegistorVisitor[getcount];
		int index = 0;
		for (int i = 0; i < inputarray.length; i++) {
			if (inputarray[i].getAge() >= agefrom && inputarray[i].getAge() <= ageto) {
				temp[index] = inputarray[i];
				index++;
			}
		}
		temp = sortByName(temp);
		if (index > 0) {
			return temp;
		}
		return null;
	}

	private static int countforarraylength(RegistorVisitor[] inputarray, int agefrom, int ageto) {
		// TODO Auto-generated method stub

		int count = 0;
		for (int i = 0; i < inputarray.length; i++) {
			if (inputarray[i].getAge() >= agefrom && inputarray[i].getAge() <= ageto) {
				count++;
			}
		}
		return count;
	}

	private static RegistorVisitor[] sortByName(RegistorVisitor[] temp) {
		// TODO Auto-generated method stub
		RegistorVisitor tempvar = new RegistorVisitor();
		for (int i = 0; i < temp.length - 1; i++) {
			for (int j = i + 1; j < temp.length; j++) {
				if (temp[i].getName().compareTo(temp[j].getName()) > 0) {
					tempvar = temp[i];
					temp[i] = temp[j];
					temp[j] = tempvar;

				}
			}
		}
		return temp;
	}

	private static void displayVisitor(RegistorVisitor[] inputarray) {
		// TODO Auto-generated method stub
		System.out.println("================== Record ====================");
		for (int i = 0; i < inputarray.length; i++) {
			System.out.println(inputarray[i].getId());
			System.out.println(inputarray[i].getName());
			System.out.println(inputarray[i].getGender());
			System.out.println(inputarray[i].getAge());
			System.out.println("======================================");
		}

	}

	private static RegistorVisitor[] newRecord(RegistorVisitor[] inputarray) {
		// TODO Auto-generated method stub
		int count = 0;
		System.out.println("How many visitor you want to registor");
		count = sc.nextInt();
		int index = 0;
		RegistorVisitor temp[] = new RegistorVisitor[inputarray.length + count];
		for (int i = 0; i < inputarray.length; i++) {
			temp[index] = inputarray[i];
			index++;
		}
		int id = 0;
		byte age = 0;
		String name = "", grnder = "";

		for (int i = index; i < temp.length; i++) {
			System.out.println("Enter the visitor id:");
			id = sc.nextInt();
			System.out.println("Enter the name");
			name = sc.next();
			System.out.println("Enter the age");
			age = sc.nextByte();
			System.out.println("please select grnder\n1.Male\n2.Female\n3.Other");
			String grnderchoose[] = { " ", "Male", "Female", "Other" };
			int choices = sc.nextInt();
			grnder = grnderchoose[choices];

			temp[i] = new RegistorVisitor(id, name, age, grnder);

		}

		return temp;
	}

	private static void displayMenu() {
		// TODO Auto-generated method stub
		System.out.println("Maven");
		System.out.println("=============Menu driven application for visitor==============");
		System.out.println("\t1.Register visitor\n\t2.Display Existing Record\n"
				+ "\t3.Search in range rcord in sorted order\n\t4.Update visitor age\n\t5.Delete visitor record"
				+ "\n\t6.Exit from menu");
		System.out.println("==============================================================");
	}
}
