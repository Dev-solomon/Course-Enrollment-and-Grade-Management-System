import java.util.Scanner; // Import the Scanner class
import java.util.Dictionary; //Import the Dictionary class
import java.util.Hashtable; // Import Hashtable class
import java.util.Enumeration; //Import Enumeration class

// Programming Assignment Unit_2 --- A Library System
public class Library {
    // Initialize a dictionary of bookslist
    public static Dictionary<String, Integer> booksList = new Hashtable<>();
    public static void main(String[] args) { 

        boolean indicator = true ; // To stop or continue browsing library system
        LibraryFuncs lib = new LibraryFuncs(); // initialize Library functions 
        Scanner userOption = new Scanner(System.in); 

        do {
            System.out.println("\n\n");
            System.out.println(" --- WELCOME TO OUR LIBRARY SYSTEM --- ");
            System.out.printf("  Choose 1 to 4 From the Options Below:  ");
            System.out.println("\n 1. Add a Book \n 2. Borrow a Book \n 3. Return a Book \n 4. Exit");  
            System.out.println("\n\n");
            String user_option = userOption.nextLine().toLowerCase();

            switch (user_option) {
                case "1":
                    lib.AddBook();
                    break;
                case "2":
                    lib.BorrowBook();
                    break;
                case "3":
                    lib.ReturnBook();
                    break;
                case "4":
                    System.exit(0);
                case "5": // CASE 5 IS FOR TEST PURPOSES INCASE YOU WANT TO VIEW BOOKS AND THEIR NUMBERS AVAILABLE
                    System.out.println(" **** LISTS OF BOOKS AVAILABLE ****");
                    Enumeration<String> books = booksList.keys();
                    while(books.hasMoreElements()){
                        String book = books.nextElement();
                        System.out.println("Book_Title: " + book + " (" + booksList.get(book) + " copies)." );
                    }
                    break;
                default:
                    System.out.println("Pick any Option or press 4 to exit.");
                    break;  
            } 
        } while (indicator);        
    } 
    
}


// a class containing all the functions the users can do in the library system.
class LibraryFuncs{

    // function to add a book to the library
    public void AddBook() {
        // Collect book user wants to add to library
        Scanner bookToAdd = new Scanner(System.in);
        System.out.printf("Enter the book's title: ");
        String book_title = bookToAdd.nextLine().toLowerCase();
        System.out.printf("Enter the book's author: ");
        String book_author = bookToAdd.nextLine().toLowerCase();
        System.out.printf("Enter the book's quantity: ");
        String book_quantity = bookToAdd.nextLine().toLowerCase();

        // check if the input value is empty
        while (book_title.isBlank() || book_author.isBlank() || book_quantity.isBlank()) {
            System.out.println("Sorry! Input all fields or press 4 to exit.");
            System.out.printf("Enter the book's title: ");
            book_title = bookToAdd.nextLine().toLowerCase();
            System.out.printf("Enter the book's author: ");
            book_author = bookToAdd.nextLine().toLowerCase();
            System.out.printf("Enter the book's quantity: ");
            book_quantity = bookToAdd.nextLine().toLowerCase();    
            // check condition to exit program
            if (book_title == "4" || book_author == "4" || book_quantity == "4")
            {
                System.exit(0);
            }      
        }
        // check if book already exists in Library list
        if (Library.booksList.get(book_title) == null){
            Library.booksList.put(book_title, Integer.parseInt(book_quantity));
            System.out.println("New Book: " + book_title + " Has Been Added to the Library!"); 
        } else {
            int book_update = Library.booksList.get(book_title) + Integer.parseInt(book_quantity);
            Library.booksList.put(book_title, book_update);
            System.out.println("You have added " + book_quantity + " quantity of " + book_title + " to the Library"); 
        }     
        
    }


    // function to borrow a book from the library
    public void BorrowBook() {
        Scanner bookToBorrow = new Scanner(System.in);
        System.out.printf("Enter book's title: ");
        String book_title = bookToBorrow.nextLine().toLowerCase();
        System.out.printf("Enter number of books to borrow: ");
        String book_number = bookToBorrow.nextLine().toLowerCase();

        //  check if the values are empty
        while (book_title.isBlank() || book_number.isBlank()) {
            System.out.println("Sorry! Input all fields or press 4 to exit.");
            System.out.printf("Enter book's title: ");
            book_title = bookToBorrow.nextLine().toLowerCase();
            System.out.printf("Enter number of books to borrow: ");
            book_number = bookToBorrow.nextLine().toLowerCase(); 
            // check condition to exit program
            if (book_title == "4" || book_number == "4")
            {
                System.exit(0);
            }      
        }

        // checking if requested number of books are available in the library
        if (Library.booksList.get(book_title) == null) {
            System.out.println("WE DO NOT HAVE SUCH A BOOK TITLE IN OUR LIBRARY");
        } else if (Library.booksList.get(book_title) < Integer.parseInt(book_number)){
            System.out.println("WE DON'T HAVE UP TO " + book_number + " BOOKS OF " + book_title + " IN THE LIBRARY!"); 
        } else
        {
            int book_number_left = Library.booksList.get(book_title) - Integer.parseInt(book_number);
            Library.booksList.put(book_title, book_number_left);
            System.out.println(" **** Book Collection Successful **** ");  
        }

    }


    // return a book
    public void ReturnBook() {
        Scanner bookToReturn = new Scanner(System.in);
        System.out.printf("Enter book's title: ");
        String book_title = bookToReturn.nextLine().toLowerCase();
        System.out.printf("Enter number of books to borrow: ");
        String book_number = bookToReturn.nextLine().toLowerCase();

        //  check if the values are empty
        while (book_title.isBlank() || book_number.isBlank()) {
            System.out.println("Sorry! Input all fields or press 4 to exit.");
            System.out.printf("Enter book's title: ");
            book_title = bookToReturn.nextLine().toLowerCase();
            System.out.printf("Enter number of books to borrow: ");
            book_number = bookToReturn.nextLine().toLowerCase(); 
            // check condition to exit program
            if (book_title == "4" || book_number == "4")
            {
                System.exit(0);
            }      
        }

        // check if  book belongs to library system 
        if (Library.booksList.get(book_title) == null ){ 
            System.out.println("This book does not belong to our Library, Thank You."); 
        } else 
        {
            int book_added = Library.booksList.get(book_title) + Integer.parseInt(book_number);
            Library.booksList.put(book_title, book_added);
            System.out.println(" **** You returned " + Integer.parseInt(book_number) + " copies of " + book_title + " **** "); 
        }
    }


}