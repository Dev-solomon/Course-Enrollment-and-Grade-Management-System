import java.util.ArrayList; // Import the arrayList
import java.util.HashMap; // Import the Hashmap
import java.util.Scanner; // Import Scanner class

//  =================  Administrator Interface Class =============================
public class AdministratorInterface {
    public static void main(String[] args) {
        boolean menu = true;
        Scanner userInput = new Scanner(System.in); // collecting user input 


        // Interactive command-line interface implementation
        do {
            System.out.println("\n\n");
            System.out.println(" --- COURSE ENROLLMENT AND GRADE MANAGEMENT SYSTEM --- ");
            System.out.printf("  Choose 1 to 4 From the Options Below:  ");
            System.out.println("\n 1. Add New Course \n 2. Register New Student \n 3. Enroll Student in Course \n 4. Assign Student a Grade \n 5. Display Student Overrall Course Grades \n 6. Modify Student-Info \n 7. Modify Course-Info \n 8. View Total Courses and Students(In System) \n 9. Exit");  
            System.out.println("\n\n");
            String user_option = userInput.nextLine().toLowerCase(); 

            // switch-case statement to handle different user options
            switch ((user_option)) {
                case "1": // code block execution for option 1
                    System.out.print("Course Code: ");
                    String courseCode = userInput.nextLine().toLowerCase();
                    System.out.print("Course Name: ");
                    String courseName = userInput.nextLine().toLowerCase();
                    System.out.print("Course maxCapacity: ");
                    int courseMaxCapacity = Integer.valueOf(userInput.nextLine());
                    CourseManagement.addCourse(courseCode, courseName, courseMaxCapacity); // call method to add new course
                    break;
                case "2": // code block for option 2 
                    System.out.print("Student ID: ");
                    int studentID = Integer.valueOf(userInput.nextLine());
                    System.out.print("Student Name: ");
                    String studentName = userInput.nextLine().toLowerCase(); 

                    // check if ID is already used by an existing student
                    while(CourseManagement.checkStudentID(studentID)) { 
                        System.out.println(" *** Student with ID Already Exists! Use a New One *** ");
                        System.out.print("Student ID: ");
                        studentID = Integer.valueOf(userInput.nextLine());
                        System.out.print("Student Name: ");
                        studentName = userInput.nextLine().toLowerCase(); 
                    }
                    Student student = new Student(studentID, studentName); // new student instance created
                    CourseManagement.studentsList.add(student); // new student object added to studentsList array
                    System.out.println("New-Student Registration Successful!");
                    break;
                case "3": // code block for optio 3 
                    System.out.print("Student ID: ");
                    int sID = Integer.valueOf(userInput.nextLine());
                    System.out.print("Course Code: ");
                    String cCode = userInput.nextLine().toLowerCase();

                    while(CourseManagement.checkStudentID(sID) == false) { // check if such student exists
                        System.out.println(" *** No Student With Such ID, TRY AGAIN !!! *** ");
                        System.out.print("Student ID: ");
                        sID = Integer.valueOf(userInput.nextLine()); 
                        System.out.print("Course Code: ");
                        cCode = userInput.nextLine().toLowerCase(); 
                    }
                    // Check if course exists in coursesList
                    if(CourseManagement.checkCourseCode(cCode)) { // if course exist ? then code block execution
                        Student e_student = CourseManagement.getStudent(sID); // get the student object to be enrolled
                        Course course = CourseManagement.getCourse(cCode); // get the course object to enroll student into
                        CourseManagement.enrollStudent(e_student, course); // call method to enroll student in a course
                    } else {
                        //  message if no course with course code found
                        System.out.println(" *** No Such Course with course code Inputted *** ");
                    } 
                    break;
                case "4":  // code block for option 4
                    System.out.print("Student ID: ");
                    int student_ID = Integer.valueOf(userInput.nextLine()); 
                    System.out.print("Course Code: ");
                    String course_code = userInput.nextLine().toLowerCase();
                    System.out.print("Course Grade: ");
                    int courseGrade = Integer.valueOf(userInput.nextLine()); 

                    //  check to make sure student exists
                    while(CourseManagement.checkStudentID(student_ID) == false) {
                        System.out.println(" *** No Student With Such ID, TRY AGAIN !!! *** ");
                        System.out.print("Student ID: ");
                        student_ID = Integer.valueOf(userInput.nextLine()); 
                        System.out.print("Course Code: ");
                        course_code = userInput.nextLine().toLowerCase();
                        System.out.print("Course Grade: ");
                        courseGrade = Integer.valueOf(userInput.nextLine());
                    }
                    // check is course code exists
                    if(CourseManagement.checkCourseCode(course_code)) {
                        Student theStudent = CourseManagement.getStudent(student_ID);
                        Course course = CourseManagement.getCourse(course_code);
                        // assign new grade for student to a particular course
                        CourseManagement.assignGrade(theStudent, course, courseGrade); 
                    } else {
                        //  message if no course found
                        System.out.println(" *** No Such Course with course code Inputted *** ");
                    }
                    break;
                    case "5": // code block for option 5 
                        System.out.print("Student ID: ");
                        int studentId = Integer.valueOf(userInput.nextLine());  

                        // check if student exists or not
                        while(CourseManagement.checkStudentID(studentId) == false) {
                            System.out.println(" *** No Student With Such ID, TRY AGAIN !!! *** ");
                            System.out.print("Student ID: ");
                            studentId = Integer.valueOf(userInput.nextLine());  
                        }
                        // get student profile from studentsList in CourseManagement class
                        Student studentProfile = CourseManagement.getStudent(studentId); 
                        System.out.println("\n\n --- Student Name: " + studentProfile.getName().toUpperCase() + " And Grades --- ");
                        //  Display the students total grades for each course they are enrolled in
                        CourseManagement.calculateOverallGrade(studentProfile); 
                        break;
                    case "6": // code block for option 6 
                        System.out.println(" Select One: \n 1. Change student Name \n 2. Unenroll student from course ");
                        int option = Integer.valueOf(userInput.nextLine());
                        if (option == 1) { // code for changing student name
                            System.out.print("Enter Student ID: ");
                            int studentid = Integer.valueOf(userInput.nextLine());
                            System.out.print("Enter New Name: ");
                            String newName = userInput.nextLine().toLowerCase();
                            Student changestudentName = CourseManagement.getStudent(studentid); // get student object
                            changestudentName.setName(newName); // call method to update to new name
                        }
                        else if (option == 2){ // code to unenroll student from course
                            System.out.print("Enter Student ID: ");
                            int studentid = Integer.valueOf(userInput.nextLine());
                            System.out.print("Input course code to unenroll from:");
                            String ccode = userInput.nextLine().toLowerCase();
                            Student unenroll_student = CourseManagement.getStudent(studentid); // get student object
                            Course unenroll_course = CourseManagement.getCourse(ccode); // get course object
                            unenroll_student.unenrollInCourse(unenroll_course); // call method to uenroll student from Student class
                        }
                        break;
                    case "7": // code block for option 7
                    // menu for multiple options to update
                        System.out.println(" Select One(Change): \n 1. course-name \n 2. course-code \n 3. course max-capacity ");
                        int select = Integer.valueOf(userInput.nextLine());
                        if (select == 1) { // code to update course name
                            System.out.print("Enter course code: ");
                            String code = userInput.nextLine().toLowerCase();
                            System.out.print("Enter New Name: ");
                            String newCourseName = userInput.nextLine().toLowerCase();
                            // call method to update the course name to new one 
                            Course mycourse = CourseManagement.getCourse(code);
                            mycourse.setName(newCourseName);
                        }
                        else if (select == 2){ //  code to update course code
                            System.out.print("Enter course code: ");
                            String code1 = userInput.nextLine().toLowerCase();
                            System.out.print("Enter new Course code:");
                            String ccode = userInput.nextLine().toLowerCase();
                            // call method to update the course code to new one 
                            Course mycourse1 = CourseManagement.getCourse(code1);
                            mycourse1.setCourseCode(ccode);
                        }
                        else if (select == 3){ //  code to update course max-capacity
                            System.out.print("Enter course code: ");
                            String code2 = userInput.nextLine().toLowerCase();
                            System.out.print("Enter new max-capacity:");
                            int newCapacity = Integer.valueOf(userInput.nextLine());
                            // call method to update the course capacity to new one 
                            Course mycourse2 = CourseManagement.getCourse(code2);
                            mycourse2.setMaxCapacity(newCapacity);
                        }
                        break;
                    case "8": // code block for option 8
                        System.out.println("Total Registered Courses"); 
                        CourseManagement.viewTotalCourses(); // call method to view total courses and student total for each course
                        break;
                    case "9":
                        menu = false; // exit out of the AdminInterface
                        break;
                default:
                    System.out.println("Pick any Option or press 6 to exit."); // Default if user picks no option
                    break;
            }
            

        } while (menu);
    }
}

// ======================   Course-Management Class   ===================================
class CourseManagement {
    private static ArrayList<Course> coursesList = new ArrayList<>(); // for storing the total courses registered in system
    public static ArrayList<Student> studentsList = new ArrayList<>(); // for storing the total students registered in sysytem

    // this is function to add course to the coursesList
    public static void addCourse(String courseCode, String name, int maxCapacity) {
        Course newCourse = new Course(courseCode, name, maxCapacity); // create a new course object from Course class
        coursesList.add(newCourse); // Add the new course created
        System.out.print("Success Adding a New Course to the Management System");
    }

    // function to enroll a new student into a course
    public static void enrollStudent(Student student, Course course) {
        if (course.getMaxCapacity() > course.getTotalEnrolledStudents()) { // check if course capacity is already full
            student.enrollInCourse(course); // enroll student into course
            course.incrementTotalEnrolledStudents(); // incremement the total students in a course
        } else {
            System.out.println("Course " + course.getName() + " has reached its maximum capacity."); // course reached max
        }
    }

    //  function to assign a grade to a particular course for a student
    public static void assignGrade(Student student, Course course, int grade) {
        student.assignGrade(course, grade); // call it from the student object
    }

    // function to clalculate the overall grades for each courses for a student
    public static void calculateOverallGrade(Student student) {
        // Implementation to calculate overall grade for the student
        HashMap<String, ArrayList<Integer>> coursesGrades = student.getStudentGrades(); // get the student grades in student class
        for (String key : coursesGrades.keySet()) {
            // student.calculateTotalGrades(coursesGrades.get(key))
            System.out.println("COURSE: " + key.toUpperCase() + " && TOTAL_GRADE: " + student.calculateTotalGrades(coursesGrades.get(key)));
        }
    }

    // function for checking studentID
    public static boolean checkStudentID(int id) {
        for(Student student : studentsList ) {
            if (student.getId() == id) { 
                return true; // ID exists
            }
        }
        return false; // ID does not exist
    }
    // function for checking courseCode
    public static boolean checkCourseCode(String code) {
        for(Course course : coursesList ) {
            if (course.getCourseCode().equals(code)) { 
                return true; // course exists
            }
        }
        return false; // course does not exist
    }

    // function to get the course object using the course code and return
    public static Course getCourse(String course_code){
        for ( Course course : coursesList ) {
            if(course.getCourseCode().equals(course_code)){
                return course; // course found 
            } 
        } 
        return null; // no course like that in our system
    }

    // function to return a single student object using their ID
    public static Student getStudent(int studentID){
        for ( Student student : studentsList ) {
            if(student.getId() == studentID){
                return student; // student found
            }
        }
        return null; // no student registered in our system
    }

    //  function to view the total courses registered in management system and their total students in each course
    public static void viewTotalCourses(){
        int counter = 0;
        for (Course courses : coursesList) {
            counter += 1;
            System.out.println(counter + ".) " + courses.getName().toUpperCase() + " " + courses.getCourseCode() + " - Total_Students_Enrolled(" + courses.getTotalEnrolledStudents() + ")");
        } 
    }
     
}


// ==========================  Student Class  ======================== 
class Student {
    //  create private variables
    private int id;
    private String name;
    private ArrayList<Course> enrolledCourses;
    private HashMap<String, ArrayList<Integer>> studentGrades;

    // initialize variables for object
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.enrolledCourses = new ArrayList<>();
        this.studentGrades = new HashMap<>(); 
    }
    
    // get the student name
    public String getName() {
        return name;
    }

    // get student ID
    public int getId() {
        return id;
    }

    // set a new name for the student or modify their exiting name
    public void setName(String newName) {
        this.name = newName;
        System.out.println("New Name updated!");
    } 

    // get total grades for each course for a student
    public HashMap<String, ArrayList<Integer>> getStudentGrades() {
        return studentGrades;
    }

    //  return list of enrolled courses for a student
    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    // function to enroll student into a course
    public  void enrollInCourse(Course course) {
        enrolledCourses.add(course); // add to enrolledcourses List
        System.out.println(name.toUpperCase() + " has been enrolled in course: " + course.getName());
    }

    // function to unenroll a student from a course also deleting all their data in grades
    public  void unenrollInCourse(Course course) {  
        for (String k : studentGrades.keySet()) {
            if (k.equals(course.getName())) {
                studentGrades.remove(k); // remove grades data completely while unenrolling student
                System.out.println(name.toUpperCase() + " Grades Data Deleted for course: " + course.getName());
            }
        }
        enrolledCourses.remove(course); // unenroll from a course in enrolledcourses list
        course.decrementTotalEnrolledStudents(); // decrement the students enrolled in a course
        System.out.println(this.name.toUpperCase() + " has been Unenrolled from course: " + course.getName());
    }

    // function for assigning a grade to a student for a particular course
    public void assignGrade(Course course, int grade) {
        boolean courseFound = false; // is course found ?
        for (Course enrolledCourse : enrolledCourses) { //search cousre in enrolled courses List
            if (enrolledCourse.equals(course)) { 
                ArrayList<Integer> studentCourse = studentGrades.computeIfAbsent(enrolledCourse.getName(), k -> new ArrayList<>());
                studentCourse.add(grade); // add grade to course grades in studentGrades HashMap
                System.out.println("New Grade Added For (" + this.name.toUpperCase()  + ") to course: " + enrolledCourse.getName());
                courseFound = true;
                break; // No need to continue searching if the course is found
            }
        }
        if (!courseFound) { // if course not found in student enrolledcourses List
            // if student is not enrolled in course message display.
            System.out.println("Student- " + this.name.toUpperCase()  + " is not enrolled in course: " + course.getName());
        }
    }


    public static int calculateTotalGrades(ArrayList<Integer> grades) {
        // Initialize total sum
        int sum = 0;
        // Loop through the ArrayList and sum up the integers
        for (int number : grades) {
            sum += number;
        }
    
        // Return the total sum
        return sum;
    }
}

// =========================   Course Class  ===============================
class Course {
    // create variables for initialization
    private String courseCode;
    private String name;
    private int maxCapacity;
    private int totalEnrolledStudents;
    
    // constructor for initializing variables
    public Course(String courseCode, String name, int maxCapacity) {
        this.courseCode = courseCode;
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.totalEnrolledStudents = 0;
    }

    // get the course code of object
    public String getCourseCode() {
        return courseCode;
    }

    // get name of course for object
    public String getName() {
        return name;
    }

    // get max capacity
    public int getMaxCapacity() {
        return maxCapacity;
    }

    // update the course code to a new one
    public void setCourseCode(String newCode) {
        this.courseCode = newCode;
        System.out.println("New course code updated!");
    }

    // update the course name to a new one
    public void setName(String newName) {
        this.name = newName;
        System.out.println("New  course name updated!");
    }

    // update the max-capacity to a new one
    public void setMaxCapacity(int newCapacity) {
        this.maxCapacity = newCapacity;
        System.out.println("New course max-capacity updated!");
    }

    // get total enrollment for course object
    public int getTotalEnrolledStudents() {
        return totalEnrolledStudents;
    }

    // function to increment total enrolled student for course object
    public void incrementTotalEnrolledStudents() {
        totalEnrolledStudents += 1;
    }

    // function to decrement the total enrolled students for a course object
    public void decrementTotalEnrolledStudents() {
        totalEnrolledStudents -= 1;
    } 
    
    
}








 

 