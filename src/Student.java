import java.util.ArrayList;
import java.time.Period;
import java.time.LocalDate;
import java.text.DecimalFormat;

public class Student {
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private final ArrayList<Float> grades = new ArrayList<>();
    private final DecimalFormat format = new DecimalFormat("#.00");
    public enum status {
        GOOD_STATUS,
        NEED_ATTENTION,
        CANNOT_MOVE_TO_NEXT_LEVEL
    }
    // Create a Student without a birthday
    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = null; // Default Birthday is a null
    }
    // Create a Student with all information
    public Student(String firstName, String lastName, int birthYear, int birthMonth, int birthDay) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = LocalDate.of(birthYear, birthMonth, birthDay);

    }
    // Empty Constructor
    public Student() {
        this.firstName = "";
        this.lastName = "";
        this.birthday = null; // Blank birthday
    }

    // Getters
    public int getAge() {
        // Blank birthday case
        if(birthday == null) {
            return 0;
        }
        // Age function
        return Period.between(birthday, LocalDate.now()).getYears();
    }
    public String getFullName(){ return firstName + " " + lastName; }
    public String getFirstName() { return firstName; }
    public String getLastName() {return lastName;}
    public LocalDate getBirthday() {return birthday;}
    public ArrayList<Float> getGradesList() { return grades; }

    // Add Grade to the Array List
    public void addGrade(float grade) { this.grades.add(grade); }

    /*
    Loop through the grades and find the average
    Returns the Average as a Double
     */
    public double getGradeAverage() {
        // Average is all the elements added up then divided by number of elements
        double gradeTotal = 0;
        for ( float grade : this.grades) { // Iterate through Array List
            gradeTotal += grade;
        }
        return gradeTotal / this.grades.size();
    }

    // Find the Student's Current Status to continue to the next grade
    // Returns the status as an enum
    public status getStatus() {
        // Find the Grade Average
        double gradeAverage = getGradeAverage();

        int numBelow50 = 0;

        // Find number of grades below 50
        for (float grade : this.grades) {
            if (grade < 50)
                numBelow50++;
        }

        // If there are 3 or more below 50 then the student is automatically not able to move to the next level
        if(numBelow50 >= 3)
            return status.CANNOT_MOVE_TO_NEXT_LEVEL;

        // Check the Grade Averages
        if( gradeAverage >= 75)
            return status.GOOD_STATUS;
        else if ( gradeAverage >= 60)
            return status.NEED_ATTENTION;
        else
            return status.CANNOT_MOVE_TO_NEXT_LEVEL;
    }

    // Setters
    public void setBirthday(int year, int month, int day) { this.birthday = LocalDate.of(year, month, day); }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    /*
    Add to the grades using a float array. Does not update the array, it just adds grades to the end of the array.
     */
    public void setGrades(float[] grades) {
        for(float grade : grades) {
            this.grades.add(grade);
        }
    }

    public String toString() {
        return this.firstName + " " + this.lastName + "\nBirthday: " + this.birthday + " Age: " + getAge() + "\nGrade Average: " + format.format(getGradeAverage()) + "\nStatus: " + getStatus();
    }

}
