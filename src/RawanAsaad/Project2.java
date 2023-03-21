package RawanAsaad;
import java.util.Scanner;
public class Project2 {


	    //Global Variables
	    //Scanner
	    static Scanner input = new Scanner(System.in);
	    //Counter : it count how many entry in the telephone directory
	    static int PeopleCounter = 0;
	    //Object Array that hold all the data for the telephone directory
	    static Person[] peopleArray = new Person[100];

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
	            System.out.println("============== Main Menu ==============");
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
	    and then store them in an object and store this object in mintainable object array.

	    It will prompt the user to enter the person information.
	    It will also check if the user entered an already existing phone number
	    and prompt the user to enter another phone number if it exist.
	    It will increase the peopleCounter after a new person get added.

	    this will create a new object from class person and store all the details inside
	    this object after that it will store the object inside the peopleArray
	     */
	    public static void AddPerson() {
	        System.out.println("");
	        System.out.println("============== Add New Person ==============");

	        //The main variable for the user to enter
	        String FirstName, LastName, City, Phone, Address;

	        //Prompt the user to enter the firstName and not leave it a blank
	        do {
	            System.out.println("Enter a First Name:  ");
	            FirstName = input.nextLine();
	            //it will check if it blank which means there is nothing inside it
	            //it will print a message
	            if (FirstName.isBlank()) {
	                System.out.println("Insert A First Name Don't Leave it Blank");
	            }
	        } while (FirstName.isBlank());

	        //Prompt the user to enter the lastName
	        //the last name here so we can differntiate 
	        //if multiple contact have the same first name
	        System.out.println("Enter a Last Name:  ");
	        LastName = input.nextLine();
	        
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
	        
	        //Prompt the user to enter the city
	        System.out.println("Enter a City:  ");
	        City = input.nextLine();
	        
	        //Prompt the user to enter the Address
	        System.out.println("Enter a Address:  ");
	        Address = input.nextLine();

	        //Create new object of class person and store all the entered data into it
	        Person newPerson = new Person(FirstName, LastName, City, Phone, Address);

	        //Add the new object to the global array
	        peopleArray[PeopleCounter] = newPerson;

	        //increase the counter
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
	                String newPhone, oldPhone = peopleArray[inputUserChoose].getPhone();
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
	                peopleArray[inputUserChoose].setPhone(newPhone);
	                //This will print the updated information
	                System.out.println("Updated number for "
	                        + peopleArray[inputUserChoose].getFirstName() + " "
	                        + peopleArray[inputUserChoose].getLastName() + " is: "
	                        + peopleArray[inputUserChoose].getPhone());
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
	                    "First Name", "Last Name", "Phone", "City", "Address");
	            System.out.println();

	            //it will loop through all the contact and print all their details
	            for (int i = 0; i < PeopleCounter; i++) {

	                System.out.printf("%-20s", peopleArray[i].getFirstName());
	                System.out.printf("%-25s", peopleArray[i].getLastName());
	                System.out.printf("%-15s", peopleArray[i].getPhone());
	                System.out.printf("%-10s", peopleArray[i].getCity());
	                System.out.printf("%-10s", peopleArray[i].getAddress());
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
	                if (peopleArray[i].getFirstName().equalsIgnoreCase(inputFirstName)) {
	                    //The name got found

	                    //flip the flag to true to indicate it got found
	                    found = true;
	                    //Print the details
	                    System.out.println("The number of " + peopleArray[i].getFirstName() + " "
	                            + peopleArray[i].getLastName() + " is: " + peopleArray[i].getPhone());
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
	    first name. This method will get called and search for the first name inside
	    the object array, then I will be looking inside each object inside the array.

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
	            if (peopleArray[i].getFirstName().equalsIgnoreCase(inputName)) {
	                //if the first name got found

	                //it will print it
	                System.out.println(i + "." + peopleArray[i].getFirstName() + " " + peopleArray[i].getLastName());
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
	    This method purpose it to search throgh the object array through
	    each object phone number searching for a simmiller phone number
	    that the user entered

	    it will take a string which will be a phone number
	    and it will return a boolean value that is

	    true if the phone does not exist in any object
	    which makes it unique

	    false if the phone does exist in an object
	    which makes it not unique
	     */
	    public static boolean CheckUniquePhone(String NewPhone) {
	        //assign the newPhone to a key
	        String Key = NewPhone;

	        // I Assumed that the number is unique unless it's found in the loop below.
	        boolean isItUnique = true;

	        //loop that search the phone array for an equal key
	        for (int i = 0; i < PeopleCounter; i++) {
	            if (peopleArray[i].getPhone().equalsIgnoreCase(Key)) {
	                //The phone got found
	                isItUnique = false;
	                //Break out of the loop
	                break;
	            }
	        }
	        return isItUnique;
	    }

	}

	/*
	This is the class that i will used to create object for each contact
	in the telephone directory system
	 */
	class Person {

	    private String FirstName, LastName, City, Phone, Address;

	    //Constructor
	    public Person(String NewFirstName, String NewLastName, String NewCity,
	            String NewPhone, String NewAddress) {
	        FirstName = NewFirstName;
	        LastName = NewLastName;
	        City = NewCity;
	        Phone = NewPhone;
	        Address = NewAddress;


	    }

	    //Setters & getters
	    public String getFirstName() {
	        return FirstName;
	    }

	    public void setFirstName(String changeFirstName) {
	        FirstName = changeFirstName;
	    }

	    public String getLastName() {
	        return LastName;
	    }

	    public void setLastName(String changeLastName) {
	        LastName = changeLastName;
	    }

	    public String getCity() {
	        return City;
	    }

	    public void setCity(String changeCity) {
	        City = changeCity;
	    }

	    public String getPhone() {
	        return Phone;
	    }

	    public void setPhone(String changePhone) {
	        Phone = changePhone;
	    }

	    public String getAddress() {
	        return Address;
	    }

	    public void setAddress(String changeAddress) {
	        Address = changeAddress;
	    }


	}
