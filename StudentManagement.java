import java.util.ArrayList; // import the arraylist class
import java.util.Scanner; //import the scanner class 

public class StudentManagement {  

    public static void main(String[] args) {
        // initialize variables for use
        Scanner userInput = new Scanner(System.in); // new object from scanner class
        String user_input; 
        int ui; // user_input to integer value

        // a do while loop to run the menu once and then stop until the user decides to exit
        do {
            // The Student Management Menu Display
            System.out.print("\n");
            System.out.println(" ---------------------------------------- ");
            System.out.println(" |   STUDENT RECORD MANAGEMENT SYSTEM   | ");
            System.out.println(" |   Choose a number from the options   |  ");
            System.out.println(" ---------------------------------------- ");

            System.out.println("\n 1. Add new student \n 2. Update student Info \n 3. View student details \n 4. Total student records \n 5. Delete a student \n 6. Exit ");
            System.out.print("Enter Menu Number ===>: ");
            user_input = userInput.nextLine();  //collect the input choice of the user

            while(user_input.isBlank()){
                System.out.print("\n");
                System.out.println("*** SORRY! MAKE SURE YOU CHOOSE A MENU OPTION ***");
                System.out.println("\n 1. Add new student \n 2. Update student Info \n 3. View student details \n 4. Total student records \n 5. Delete a student \n 6. Exit ");
                System.out.print("Enter Number ===>: ");
                user_input = userInput.nextLine(); //collect the input choice of the user

            }
             ui = Integer.valueOf(user_input);

            //switch statement to control the user's choice and code execution
            switch(ui) {
                case 1:
                    Student.addStudent(); 
                    break;
                case 2:
                    Student.updateStudent();
                    break;
                case 3:
                    int studentDetail = Student.studentDetails();
                     if (studentDetail == 1)
                        System.out.println("--- No student found! ---");
                    break;
                case 4:
                    Student.studentsTotal();
                    break;
                case 5:
                    int studentDelete = Student.deleteStudent();
                    if (studentDelete == 1)
                    System.out.println("--- Student ID Invalid, Try Again! ---");
                    break;
                case 6:
                    System.out.println("Program on Exit");
                    break;
                default:
                    System.out.print("Input a valid Menu Option");
                    break;
            } 


        }while(ui != 6);


    }
}


class Student {
    static ArrayList<Student> studentList = new ArrayList<>(); // List to store all students
    static int total_students = 0; // counter to count the number of all students
    private static Scanner userInput = new Scanner(System.in); // collecting user input

    // initialize variables for use
    int id;
    String name;
    int age;
    double grade;

    //constructor to initialize student object
    public Student(int id, String name, int age, double grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    } 

    // function for checking studentID
    private static boolean checkStudentID(int id) {
        for(Student student : studentList) {
            if (student.id == id) { 
                return true; // ID exists
            }
        }
        return false; // ID does not exist
    }

    // function to add a new student
    public static void addStudent() {
        System.out.print("Student's Name: ");
        String name = userInput.nextLine();
        System.out.print("Student ID: ");
        int id = Integer.valueOf(userInput.nextLine());
        // check and validate if student id exists
        while(checkStudentID(id)){
            System.out.print("\n\n");
            System.out.println("=== STUDENT ID ALREADY EXISTS, ENTER A NEW ONE ===");
            System.out.print("Student ID: ");
            id = Integer.valueOf(userInput.nextLine());
        }
        System.out.print("Enter student age: ");
        int age = Integer.valueOf(userInput.nextLine());
        System.out.print("Enter student grade: ");
        double grade =  Double.valueOf(userInput.nextLine());

        // create new student profile and add to studentList
        studentList.add(new Student(id, name, age, grade));
        total_students += 1;
        System.out.println("*** New Student Profile Successful - !!! ***");        
    }

    // function to update a student information
    public static void updateStudent() {
        System.out.print("Enter Student-ID to update: ");
        int studentID = Integer.valueOf(userInput.nextLine());

        for(int i = 0; i < studentList.size(); i++){
            if (studentList.get(i).id == studentID) { 
                System.out.println("Choose option to update");
                System.out.println("\n 1. Name \n 2. Age \n 3. Grade");
                int user_option = Integer.valueOf(userInput.nextLine());

                switch(user_option){
                    case 1:
                        System.out.print("Enter new name: ");
                        String newName = userInput.nextLine();
                        studentList.get(i).name = newName;
                        break;
                    case 2: 
                        System.out.print("Enter new age: ");
                        int newAge = Integer.valueOf(userInput.nextLine());
                        studentList.get(i).age = newAge;
                        break;
                    case 3:
                        System.out.print("Enter new grade: ");
                        double newGrade = Double.valueOf(userInput.nextDouble());
                        studentList.get(i).grade = newGrade;
                        break;
                    default:
                        System.out.print("Enter a valid Option");
                        break;

                }
                System.out.println("*** Student Info-Update Successful ***");
            }
        }
    }

    // function to view a single student detail
    public static int studentDetails() {
        System.out.print("Enter the student-Id to view details: ");
        int studentID = Integer.valueOf(userInput.nextLine());
        
        for(int i = 0; i < studentList.size(); i++){
            if(studentList.get(i).id == studentID){
                // display the details of the single student
                System.out.print("\n");
                System.out.println("--- Student Details ---");
                System.out.println("ID:- " + studentList.get(i).id);
                System.out.println("Name:- " + studentList.get(i).name);
                System.out.println("Age:- " + studentList.get(i).age);
                System.out.println("Grade:- " + studentList.get(i).grade); 
                return studentList.get(i).id;
            } 
        } 
        return 1;
    }

    // function to view all the students in the records management system
    public static void studentsTotal(){
        if(studentList.isEmpty()){
            System.out.println("No Students are in record");
        } else {
            // Display details (ID and name) of all students
            System.out.print("\n");
            System.out.println("|-------------------------------|");
            System.out.println("|        Students Record        |");
            System.out.println("|-------------------------------|");
            for (Student student : studentList) {
                System.out.println("     ID: " + student.id + "     Name: " + student.name);
            }

            
        }
    }

    // function to delete a single student
    public static int deleteStudent(){
        System.out.print("Enter ID to delete: ");
        int studentID = Integer.valueOf(userInput.nextLine());

        for(int i = 0; i < studentList.size(); i++){
            if(studentList.get(i).id == studentID){
                // delete the student details
                studentList.remove(i);
                total_students -= 1;
                System.out.println("*** Student Deleted Successful ***"); 
                return 0;         
            } 
        } 
        return 1;
    }  
}

