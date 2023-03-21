package RawanAsaad;

import java.util.Scanner;

public class Project {


	    //Global Variables
	    //Scanner
	    static Scanner input = new Scanner(System.in);
	    //Counter : it count how many entry in the telephone directory
	    static int PeopleCounter = 0;
	    //Arrays that hold all the data
	    static String[] firstnameArray = new String[100];
	    static String[] lastnameArray = new String[100];
	    static String[] phoneArray = new String[100];
	    static String[] cityArray = new String[100];
	    static String[] addressArray = new String[100];

	    public static void main(String[] args) {
	        /*
	        The Main menu will be inside an infinite loop so it will keep
	        repeating even after the user enter a wrong input.

	        The user choice will be validated using a switch controller it will
	        check for all the cases, if it's not one of the choices
	        it will show the default message which is "You enterd a wrong choice"
	        and repeat the loop.

	        if the user entered a right choice it will call the approprite method.S
	         */
	        while (true) {
	            System.out.println("============== Main Menu Array ==============");
	            System.out.println("1- Add person details into the telephone book. ");
	            System.out.println("2- Update a telephone number by providing person first name. ");
	            System.out.println("3- Display the entire telephone book. ");
	            System.out.println("4- Search a telephone number based on person first name. ");
	            System.out.println("5- Exit the program.");
	            System.out.println("Choose from the menu above:  ");
	            int menuChoice = input.nextInt();
	            input.nextLine();
	            switch (menuChoice) {
	                case 1:
	                    AddPerson();
	                    break;
	                case 2:
	                    update();
	                    break;
	                case 3:
	                    DisplayPhoneBook();
	                    break;
	                case 4:
	                    Search();
	                    break;
	                case 5:
	                    System.out.println("The Program has been terminated.");
	                    System.exit(0);
	                    break;
	                default:
	                    System.out.println("You enterd a wrong choice");
	                    break;
	            }
	        }

	    }

	    /*
	    AddPerson Method purpose
	    is to allow the user to add a new person to the telphone directory
	    and then store them in their approprite array.
	    It will prompt the user to enter the person information.
	    It will also check if the user entered an already existing phone number
	    and prompt the user to enter another phone number if it exist.
	    It will increase the peopleCounter after a new person get added.

	    Every detail will get stored in it's approprite array and it will have
	    a matching index to indicate that it belong to the same contact.

	    so if we assume we entered a new person all of it details will get stored
	    in different array but all of it will have a matching index
	     */
	    public static void AddPerson() {

	        System.out.println("");
	        System.out.println("============== Add New Person ==============");
	        //The main variable for the user to enter
	        String FirstName, LastName, City, Phone, Address;

	        //Prompt the user to enter the firstName and not leave it a blank and then store it to its array
	        do {
	            System.out.println("Enter a First Name:  ");
	            FirstName = input.nextLine();
	            //it will check if it blank which means there is nothing inside it
	            //it will print a message
	            if (FirstName.isBlank()) {
	                System.out.println("Insert A First Name Don't Leave it Blank");
	            }
	        } while (FirstName.isBlank());
	        firstnameArray[PeopleCounter] = FirstName;

	        //Prompt the user to enter the lastName and store it to its array
	        //the last name here so we can differntiate 
	        //if multiple contact have the same first name
	        System.out.println("Enter a Last Name:  ");
	        LastName = input.nextLine();
	        lastnameArray[PeopleCounter] = LastName;

	        /*
	        Check if phone number exist using method CheckUniquePhone()
	        This method take the phone number and check if it exist
	        It will return a boolean value if it does not exist it will return true
	        if it exist it will return false

	        It will enter a do while loop and check if the phone exist
	        if it does the loop will repeat until the user enter a phone
	        that does not exist and it's not empty. And thats why it's a do while loop
	        so it will enter at least one even if the phone is unique
	        and check inside the loop
	         */
	        boolean isPhoneUnique;
	        do {
	            //Prompt the user to enter a phone
	            System.out.println("Enter a Phone:  ");
	            Phone = input.nextLine();
	            //This will check if the phone is unique
	            isPhoneUnique = CheckUniquePhone(Phone);
	            if (isPhoneUnique == false) {
	                System.out.println("Try Again, this phone is already used.");
	            }
	            //This will check if the user did not put anyting inside the phone variable
	            if (isPhoneUnique && Phone.isBlank()) {
	                isPhoneUnique = false;
	                System.out.println("Insert a Phone Number Don't Leave it Blank");
	            }

	        } while (isPhoneUnique == false);

	        // If the phone unique it will pass the loop and add it here
	        phoneArray[PeopleCounter] = Phone;

	        
	        //Prompt the user to enter the city and store it to its array
	        System.out.println("Enter a City:  ");
	        City = input.nextLine();
	        cityArray[PeopleCounter] = City;

	        //Prompt the user to enter the Address and store it to its array
	        System.out.println("Enter a Address:  ");
	        Address = input.nextLine();
	        addressArray[PeopleCounter] = Address;

	        // Increase number of people in the telephone book
	        PeopleCounter++;
	    }

	    /*
	    This method purpose is to update a telephone number for a contact
	    by providing it's first name

	    first this method will check if there is any contact got stored in the
	    telephone directory
	    it will show a message if there is not

	    otherwise it will continue
	    then it will prompt the user to enter the first name of the person that need
	    the update. It will call a method to search for it. if the first name does
	    not exist it will show a message and return to the main menu.

	    if the first name does exist it will show a list assuming that
	    there are maybe multiple contacts with the same first name
	    and the user will be prompted to choose one of them.

	    it will get prompted to enter a new phone number
	    it will check if the phone number is unique or its the same as the old one
	    if its it will update it
	    otherwise it will make the user re enter until it's unique
	     */
	    public static void update() {
	        //Check if telephone directory is empty
	        if (PeopleCounter == 0) {
	            System.out.println("There is No contact in the phone book");
	        } else {
	            //There are contacts in the telephone directory
	            System.out.println("");
	            System.out.println("============== Update Phone Number ==============");

	            //Prompt user to enter the first name to search for it
	            System.out.println("Enter The First Name: ");
	            String inputName = input.nextLine();

	            System.out.println("");
	            System.out.println("============== Search Result ==============");
	            //Retrive a list of indexes if the name exist
	            int[] Choose = findListOfName(inputName);

	            boolean found = false;
	            int inputUserChoose;
	            //if the list is not empty
	            if (Choose[0] != 404) {

	                //this loop to ensure that the user choose one contact from the list that it will be shown
	                do {
	                    System.out.println("Choose one from the listing above: ");
	                    inputUserChoose = input.nextInt();
	                    for (int i = 0; i < Choose.length; i++) {
	                        if (inputUserChoose == Choose[i]) {
	                            found = true;
	                        }
	                    }
	                } while (!found);
	                input.nextLine();

	                /*
	                it will first check if the phone number is the same as the
	                previouse one, which means no update has been happened.
	                if it's the same it will show a message it's the same.

	                if its not the same as the previous stored number it will
	                check if phone number exist using method CheckUniquePhone()
	                This method take the phone number and check if it exist
	                It will return a boolean value if it does not exist it will return true
	                if it exist it will return false

	                It will enter a do while loop and check if the phone exist
	                if it does the loop will repeat until the user enter a phone
	                that does not exist and it's not empty. And thats why it's a do while loop
	                so it will enter at least one even if the phone is unique
	                and check inside the loop
	                 */
	                //store oldPhone number and create variable newPhone to store it from the user
	                String newPhone, oldPhone = phoneArray[inputUserChoose];
	                boolean isPhoneUnique;
	                do {

	                    //Prompt user to input a phone number
	                    System.out.println("Enter a new Phone: ");
	                    newPhone = input.nextLine();
	                    //check if the new is the same as the old phone
	                    if (!newPhone.equalsIgnoreCase(oldPhone)) {
	                        //not the same so check if it's unique
	                        isPhoneUnique = CheckUniquePhone(newPhone);
	                        if (isPhoneUnique == false) {
	                            System.out.println("Try Again, this phone is already used.");
	                        }
	                    } else {
	                        //the same phone number so make it unique to exit the loop and show the message
	                        isPhoneUnique = true;
	                        System.out.println("This is the same as the previous number");
	                    }
	                    //This will check that the user did not put anything inside the newPhone Variable
	                    if (isPhoneUnique && newPhone.isBlank()) {
	                        isPhoneUnique = false;
	                        System.out.println("Insert a New Phone Number Don't Leave it Blank");
	                    }
	                } while (isPhoneUnique == false);

	                //update the phone number
	                phoneArray[inputUserChoose] = newPhone;
	                //This will print the updated information
	                System.out.println("Updated number for " + firstnameArray[inputUserChoose] + " " + lastnameArray[inputUserChoose] + " is: " + phoneArray[inputUserChoose]);
	            }
	        }
	    }

	    /*
	    This method purpose is to print all the contacts inside the telephone directory
	    in an orderd manner. It does not recive or return any value.
	    It will just print the whole telephone directory to the user.

	    first it will check if there are contacts in the telephone directory
	    if there is it will print it.
	    if there is not it will show message indicate that ther are no contacts.

	     */
	    public static void DisplayPhoneBook() {
	        //Check if telephone directory is empty
	        if (PeopleCounter == 0) {
	            System.out.println("There is No contact in the phone book");
	        } else {
	            //Telephone directory is not empty
	            System.out.println("");
	            System.out.println("============== Telephone Directory ==============");
	            //It will print a formated list as a header for every contat
	            System.out.printf("%-20s%-25s%-15s%-10s%-10s",
	                    "First Name", "Last Name", "Phone",  "City", "Address");
	            System.out.println();

	            //it will loop through all the contact and print all their details
	            for (int i = 0; i < PeopleCounter; i++) {

	                System.out.printf("%-20s", firstnameArray[i]);
	                System.out.printf("%-25s", lastnameArray[i]);
	                System.out.printf("%-15s", phoneArray[i]);
	                System.out.printf("%-10s", cityArray[i]);
	                System.out.printf("%-10s", addressArray[i]);
	                System.out.println();
	            }
	        }

	    }

	    /*
	    This method purpose is to find a phone number by giving the firstName
	    it will search if the first name exist than it will show it's number

	    it will first check if there are any contact inside the telephone directory
	    by checking the peopleCounter
	    if it's 0 that's means that there are no contact got added so it will show
	    a message that the telephone directory is empty

	    if it's not empty it will search through the list and print the found
	    values with it's phone number.

	    if it does not found any contact with the given first name it will
	    show a message that there are no contact with the given first name.
	     */
	    public static void Search() {

	        //Check if telephone directory is empty
	        if (PeopleCounter == 0) {
	            System.out.println("There is No contact in the phone book");
	        } else {
	            System.out.println("");
	            System.out.println("============== Search for Telephone ==============");
	            //initialize a flag variable to indicate if there is a found contact
	            //I assumed there is no contact with the given first name until it's found
	            boolean found = false;

	            //Prompt the user to enter the first name
	            System.out.println("Enter the first name ");
	            String inputFirstName = input.nextLine();

	            System.out.println("");
	            System.out.println("============== Search Result ==============");
	            //Search for the first name
	            for (int i = 0; i < PeopleCounter; i++) {
	                if (firstnameArray[i].equalsIgnoreCase(inputFirstName)) {
	                    //The name got found

	                    //flip the flag to true to indicate it got found
	                    found = true;
	                    //Print the details
	                    System.out.println("The number of " + firstnameArray[i] + " " + lastnameArray[i] + " is: " + phoneArray[i]);
	                }
	            }

	            if (!found) {
	                //if no contact get found print this message
	                System.out.println("There are no contact with the given First Name");
	            }
	        }
	    }

	    /*
	    This method purpose is to search and return a list of names and their indexes
	    based on an input from the user. Which is the first name.

	    When the user search to update ,the user will be prompted to enter the
	    first name. This method will get called and search for the first name
	    inside the firstName array.

	    if it get found it will return a list of indexes for the contacts that have
	    the same first name that got entered by the user.

	    if no contact with the given first name got found. the array will retrun
	    with it's first value as 404 so [0] will equal 404 to check if there is
	    value inside it or not.
	     */
	    public static int[] findListOfName(String inputName) {
	        //initilize varialbes

	        //the array that will store the indexes and returned
	        int Choose[] = new int[100];

	        //the counter for how many contact get found
	        int ChooseCounter = 0;

	        /*
	        a flag so the method know if a contact exist with the passed first name
	        I assumed there is no contact with the given name ,until it get found by the loop
	         */
	        boolean doesNameExist = false;

	        //a loop that will search for the first name
	        for (int i = 0; i < PeopleCounter; i++) {
	            if (firstnameArray[i].equalsIgnoreCase(inputName)) {
	                //if the first name got found

	                //it will print it
	                System.out.println(i + "." + firstnameArray[i] + " " + lastnameArray[i]);
	                //flip the flag to true so the method knows that a contact got found
	                doesNameExist = true;
	                //store the index of the contact
	                Choose[ChooseCounter] = i;
	                //increase the counter
	                ChooseCounter++;
	            }
	        }

	        //if the flag shows that there no contact got found, it will enter here
	        if (!doesNameExist) {
	            //Assign the first value as indication to no contact found
	            Choose[0] = 404;
	            //Print message to the user
	            System.out.println("The name that you entered does not exist");
	        }
	        return Choose;
	    }

	    /*
	    This method purpose it to search throgh the phone array
	    searching for a simmiller phone number that the user entered

	    it will take a string which will be a phone number
	    and it will return a boolean value that is

	    true if the phone does not exist inside the phone array
	    which makes it unique

	    false if the phone does exist inside the phone array
	    which makes it not unique
	     */
	    public static boolean CheckUniquePhone(String NewPhone) {
	        //assign the newPhone to a key
	        String Key = NewPhone;

	        // I Assumed that the number is unique unless it's found in the loop below.
	        boolean isItUnique = true;

	        //loop that search the phone array for an equal key
	        for (int i = 0; i < PeopleCounter; i++) {
	            if (phoneArray[i].equalsIgnoreCase(Key)) {
	                //The phone got found
	                isItUnique = false;
	                //Break out of the loop
	                break;
	            }
	        }
	        return isItUnique;
	    }

	}
