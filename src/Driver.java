/**
 *
 * @author : Albert Adamson
 * Assignment: Final Project
 * Date: 12/06/2023
 * Description: Basic Student Data Base
 * Output: Student Information
 * Input: Student Information
 * Note: Worked with William and Ben
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    private static ArrayList<Student> studentsList;
    private static Scanner input;

    public static void main(String[] args) {

        studentsList = new ArrayList<>();
        input = new Scanner(System.in);

        // Program Loop
        do {

            System.out.println("\nPlease Choose an Operation...\n\n1. Add New Student\n2. Remove Student\n3. Modify Student Information\n4. Print Student List\n5. Add grades\n6. Remove Grades\n7. Print Student Grades\n8. Get Student Rankings\n9. Quit");
            if(!input.hasNext()) {
                continue;
            }

            char choice = input.next().charAt(0);
            input.nextLine();
            switch (choice) {
                case '1':
                    // Add New Student
                    addStudent();
                    break;
                case '2':
                    // Remove a Student
                    removeStudent();
                    break;
                case '3':
                    // Modify Student Information
                    modifyStudentInformation();
                    break;
                case '4':
                    // Print all the students
                    printStudents();
                    break;
                case '5':
                    // Add Grades
                    addGrades();
                    break;
                case '6':
                    // Remove Grades
                    removeGrades();
                    break;
                case '7':
                    // Print Student Grades
                    printStudentGrades();
                    break;
                case '8':
                    // Get Student Rankings
                    printStudentRankings();
                    break;
                case '9':
                    // Stop the program
                    System.out.println("Stopping the Program");
                    System.exit(0);
            }
        } while(true);

    }
    /*
    Loop through the studentList Array and print out all the students
     */
    public static void printStudents() {
        for(int i = 0; i < studentsList.size(); i++) {
            System.out.print(i + ". " + studentsList.get(i));
            System.out.println();
            System.out.println();
        }
    }
    /*
    Choose a student from the array list, return the given index
    If the return value is negative the resulting function should quit
     */
    public static int chooseStudent() {
        printStudents();
        System.out.println();
        System.out.println("Please Choose a Student...");
        System.out.println("Type '-1' to Stop");

        // Force Integer Input
        while(!input.hasNextInt()){
            input.nextLine();
            printStudents();
            System.out.println("Please choose a student...");
        }

        int index = input.nextInt();
        input.nextLine(); // Capture the enter key

        // Make sure index is within the array size
        if(index >= studentsList.size()) {
            return chooseStudent(); // Start Function again using Recursion, return the result
        }

        return index;

    }
    /*
    Safe way to get another Int,
    parameters - String message - Error message that would print on each iteration
     */
    public static int getNextInt(String message) {
        System.out.println(message);

        // Make sure the user input contains an integer
        while(!input.hasNextInt()) {
            System.out.println(message);
            input.nextLine();
        }
        int nextInt = input.nextInt();
        input.nextLine(); // Capture the enter key

        return nextInt;

    }


    /*
    Remove a student from the array list
    */
    public static void removeStudent() {
        do {
            int index = chooseStudent();
            if(index < 0) {
                break;
            }
            studentsList.remove(index);
        } while (true);

    }
    /*
    Modify the information of the student
     */
    public static void modifyStudentInformation() {
        int index = chooseStudent();

        if(index < 0)
            return;

        Student chosenStudent = studentsList.get(index);
        String userInput;

        // Modify the First Name
        System.out.printf("First Name: %s\n", chosenStudent.getFirstName());
        System.out.println("New First Name (Leave Blank to Keep the Same): ");

        userInput = input.nextLine();
        if(!userInput.isEmpty())
            chosenStudent.setFirstName(userInput);

        // Modify the Last Name
        System.out.printf("Last Name: %s\n", chosenStudent.getLastName());
        System.out.println("New Last Name (Leave Blank to Keep the Same): ");

        userInput = input.nextLine();
        if(!userInput.isEmpty())
            chosenStudent.setLastName(userInput);

        // Modify the Birthday
        System.out.println("Current Birthday: " + chosenStudent.getBirthday());

        System.out.println("Would you like to change the birthday? y/n");
        userInput = input.nextLine();

        // Will Change the Birthday by default
        if(userInput.equals("n"))
            return; // No change to the birthday

        int year = getNextInt("Current Birth Year: " + chosenStudent.getBirthday().getYear());
        int month = getNextInt("Current Birth Month: " + chosenStudent.getBirthday().getMonthValue());
        int day = getNextInt("Current Birth Day: " + chosenStudent.getBirthday().getDayOfMonth());

        chosenStudent.setBirthday(year, month, day);

    }

    /*
    Gets user input to choose the student to add grades to
     */
    public static void addGrades() {
        int index = chooseStudent();

        // Stop Adding Grades
        if (index < 0)
            return;

        // Grade Loop
        do {
            System.out.printf("Add to %s's Grades. Type '-1' to quit\n", studentsList.get(index).getFullName());

            if (!input.hasNextFloat()) {
                input.nextLine(); // Reset buffer
                continue;
            }

            // Store the grade given
            float gradeAdded = input.nextFloat();
            input.nextLine(); // Reset the Buffer

            if (gradeAdded < 0)
                break;

            studentsList.get(index).addGrade(gradeAdded);
        } while (true);
    }
    /*
    Remove Grades of a Chosen Student
     */
    public static void removeGrades(){
        int index = chooseStudent();

        if(index < 0)
            return;

        // Get the Grade List (getGradesList returns a memory location, so modifications made will change in class)
        ArrayList<Float> gradesList = studentsList.get(index).getGradesList();

        // Get the chosen grade
        while (true) {
            // Print Student Grades
            System.out.println("Please Choose a Grade. Type '-1' when finished.");
            for(int i = 0 ; i < gradesList.size(); i++) {
                System.out.printf("Grade %d: %.2f\n", i, gradesList.get(i));
            }

            // While Loop to force Integer Input
            while(!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Please Choose a Grade...");
                for(int i = 0 ; i < gradesList.size(); i++) {
                    System.out.printf("Grade %d: %.2f\n", i, gradesList.get(i));
                }
            }

            // Store index info
            index = input.nextInt();
            input.nextLine(); // Reset Scanner Buffer

            if(index >= studentsList.size()) // No Students above the size list
                continue;
            if(index < 0) // -1 to stop
                return;

            gradesList.remove(index); // Remove the student from database
        }
    }
    /*
    Add new Students to the studentList Array List
     */
    public static void addStudent() {
        // Get Name
        String firstName, lastName;

        System.out.println("What is the Student's First Name?");
        firstName = input.nextLine();

        // If there is no first name an empty student class should be made
        if(firstName.isEmpty()) {
            // Creates a Blank Student
            System.out.println("Adding Blank Student");
            studentsList.add(new Student());
            return;
        }

        System.out.println("What is the Student's Last Name?");
        lastName = input.nextLine();

        // Birthday Query
        do {
            System.out.println("Would you like to include a birthday? y/n");
            String userInput = input.nextLine();

            if(userInput.isEmpty())
                continue;

            // Opt out of adding birthday
            if (userInput.charAt(0) == 'n') {
                studentsList.add(new Student(firstName, lastName));
                return;
            }
            break;
        } while(true);

        // Get the Year, Month, and Day
        // Get the year, there are no restrictions. Users could add a negative number if they really wanted too.
        int year = getNextInt("What is the birth year?");
        // Get the month
        int month = getNextInt("What is the birth month? (Please use the numeric representation)");
        while(month >= 13 || month <= 0) {
            month = getNextInt("Please choose a month between 1-12");
        }
        // Determine the max number of days within the chosen month
        int maxDay = getMaxDay(month);
        // Get the Day
        int day = getNextInt("What is the day?");
        while(day <= 0 || day > maxDay) {
            day = getNextInt("Please choose a day between 1 and " + maxDay);
        }

        // Add the Student
        studentsList.add(new Student(firstName, lastName, year, month, day));
        System.out.println("Successfully added the student!");
    }
    /*
    Function used to determine the max number of days within a month
     */
    public static int getMaxDay(int month){
        if(month > 12 || month < 0) {
            return 0;
        }
        switch (month) {
            case 2:
                return 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }
    /*
    Sort the students by their grade averages, then print out the student rankings from Top of class to bottom
    */
    public static void printStudentRankings() {
        System.out.println("Student Rankings\n");
        // Store Grade Averages in an Array
        double[] averages = new double[studentsList.size()];
        int[] studentNumbers = new int[studentsList.size()]; // Positions of the students

        // Load averages and positions into the arrays
        for(int i = 0; i < studentsList.size(); i++) {
            averages[i] = studentsList.get(i).getGradeAverage();
            // If the student has no grades (Not a Number) set average to 0
            if(Double.isNaN(averages[i]))
                averages[i] = 0;
            studentNumbers[i] = i;
        }

        // Sort the Grade Averages and move the studentNumbers in the same position
        for(int i = 0; i < averages.length - 1; i++) {
            int maximumPos = i;
            for(int e = i + 1; e < averages.length; e++) {
                if(averages[e] > averages[maximumPos])
                    maximumPos = e;
            }
            // Swap the ith element with the maximumPos
            double temp = averages[i];
            averages[i] = averages[maximumPos];
            averages[maximumPos] = temp;
            // Make same change with student numbers
            int tempNum = studentNumbers[i];
            studentNumbers[i] = studentNumbers[maximumPos];
            studentNumbers[maximumPos] = tempNum;
        }

        // Print out the resulting student list
        for(int i = 0; i < studentNumbers.length; i++) {
            System.out.printf("%d: %s\nGrade Average: %.2f\n\n", i+1, studentsList.get(studentNumbers[i]).getFullName(), averages[i]);
        }
    }
    /*
    Print all the grades of the chosen student
     */
    public static void printStudentGrades() {
        // Choose a student
        int index = chooseStudent();

        if(index < 0){
            printStudentGrades(); // Restart this function
            return;
        }

        System.out.println("Student: " + studentsList.get(index).getFullName() + "'s Grades...");
        for(float grade : studentsList.get(index).getGradesList()) {
            System.out.printf("%.2f, ", grade);
        }
        System.out.println();
    }

}
