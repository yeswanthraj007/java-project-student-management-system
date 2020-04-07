import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.math.RoundingMode;
import java.math.BigDecimal;
import java.util.Random;

abstract class person         
{
    String firstName;
    String lastName;
}
class Student extends person   
{
    
    String id;
    List<String> courses;
    BigDecimal tuition;
    Scanner input = new Scanner(System.in);
    public Student(String fName, String lastName) {
        this.firstName = fName;
        this.lastName = lastName;
    }

    public Student() {

    }
    BigDecimal getTuition() { return tuition; }

    void setTuition(BigDecimal money) {
        this.tuition = money;
    }

    String getName() { return firstName + " " +  lastName; }
    void setFirstName(String firstName) { this.firstName = firstName; }
    void setLastName(String lastName) { this.lastName = lastName; }
    String getId() { return id; }
    void setId(String id) { this.id = id; }
    List<String> getCourses() { return courses; }
    void setCourses(List<String> courses) { this.courses = courses; }

    String randomString()
    {
        String AB = "1234567890qwertyuiopasdfghjklzxcvbnm/*-+";
        Random random = new Random();
        int great = AB.length();
        int temp;
        String codeword = "";
        for (int i = 0; i < 4; i++)
        {
            temp = (int) (random.nextFloat() * great);
            codeword = codeword.concat(Character.toString(AB.charAt(temp)));
        }
        return codeword;
    }

    void regID()
    {
        String grade;
        boolean checked = false;

        while (!checked)
        {
            System.out.println("Enter your year in integer digit");
            grade = input.nextLine();
            if (grade.length() == 1 && Integer.parseInt(grade) > 0 && Integer.parseInt(grade) < 5)
            {
                setId(grade.concat(randomString()));
                checked = true;
            } else {
                System.out.println("Wrong input! Try again");
            }
        }

    }

    void coursemoney()
    {
        String answer;
        BigDecimal payment;
        BigDecimal moneyleft;

        while (getTuition().compareTo(BigDecimal.ZERO) > 0)
        {
            System.out.println("Your Current Balance is: " + getTuition());
            System.out.println("Do you want to pay your fee now?");

            answer = input.nextLine();

            if (answer.toLowerCase().equals("yes"))
            {
                System.out.println("How much would you like to pay right now ?");

                if (input.hasNextBigDecimal())
                {
                    payment = input.nextBigDecimal();
                    payment = payment.setScale(2, RoundingMode.HALF_UP);
                    input.nextLine();
                    if ((payment.compareTo(BigDecimal.ZERO) > 0) && payment.compareTo(getTuition()) <= 0)
                    {
                        moneyleft = getTuition().subtract(payment);
                        setTuition(moneyleft);
                    } 
                    else if (payment.compareTo(getTuition()) > 0) {
                        System.out.println("Given value > Tution Fee");
                    } 
                    else if (payment.compareTo(BigDecimal.ZERO) < 0) {
                        System.out.println("You have entered the -ve value please enter +ve values.");
                    }

                }
                 else {
                    input.nextLine();
                    System.out.println("Wrong Input");
                }

            } 
            else if (answer.toLowerCase().equals("no")) {
                break;
            } else {
                System.out.println("Wrong inputenter either yes or no !");
            }
        }
    }

    boolean duplic(List<String> list, String word)
    {
        for (String temp : list)
        {
            if (word.equals(temp))
            {
                System.out.println("You have already enrolled in that course");
                return false;
            }
        }
        return true;
    }

    private void addCourses()
    {
        List<String> classes = new LinkedList<>();
        setCourses(classes);

        String answer;
        int nextCourse;
        BigDecimal size;
        BigDecimal cost;

        System.out.println("Do you want to add any courses? yes or no");
        answer = input.nextLine();
        while (!answer.toLowerCase().equals("no"))
        {
            if (answer.toLowerCase().equals("yes"))
            {
                System.out.println("choose the courses you want to get enrolled? " +"Choose the number for the courses");
                System.out.println("1.Programming");
                System.out.println("2.Cyber Security");
                System.out.println("3.Animation");
                System.out.println("4.Game development");
                System.out.println("5.Artificial Intelligence");

                if (input.hasNextInt())
                {
                    nextCourse = input.nextInt();
                    input.nextLine();
                    chooseCourses(classes, nextCourse);

                } else {
                    System.out.println("Entered wrong input: Enter a number 1 - 5 for each class");
                    input.nextLine();
                }

            } else {
                System.out.println("Entered wrong input: Enter either yes or no next time");
            }

            System.out.println("Do you want to add any other courses yet?");
            answer = input.nextLine();
        }
        size = new BigDecimal(classes.size());
        cost = new BigDecimal(94500);

        cost = cost.multiply(size);
        setTuition(cost);
    }

    void chooseCourses(List<String> classes, int courseNumber)
    {
        switch (courseNumber)
        {
            case 1:
                if (duplic(classes, "Programming"))
                    classes.add("Programming");
                break;
            case 2:
                if (duplic(classes, "Cyber Security"))
                    classes.add("Cyber Security");
                break;
            case 3:
                if (duplic(classes, "Animation"))
                    classes.add("Animation");
                break;
            case 4:
                if (duplic(classes, "Game development"))
                    classes.add("Game development");
                break;
            case 5:
                if (duplic(classes, "Artificial Intelligence"))
                    classes.add("Artificial Intelligence");
                break;
            default:
                System.out.println("Entered wrong input");
                break;
        }
    }

    void displayInfo(Student[] studentList)
    {
        for (Student student : studentList)
        {
            System.out.println("Details:");
            System.out.println("Student Name: " + student.getName());
            System.out.println("Student ID: " + student.getId());
            
            if (student.getCourses().size() > 0) {
                System.out.println("Student's Current Courses:" + student.getCourses());
            } else {
                System.out.println("Student's Current Courses: Respective student haven't enrolled any courses yet!");
            }
            System.out.println("Student's Current Balance: Rupees " + student.getTuition());
   
        }

    }

    public static void main(String[] args) 
    {
        try {
            int size;
            Scanner input = new Scanner(System.in);
            System.out.println("Enter number of students you want to add?");
            size = input.nextInt();
            input.nextLine();

            Student[] students = new Student[size];
            Student student;
            String firstName = "";
            String lastName = "";
            
            for (int i = 0; i < size; i++)
            {
                student = new Student(firstName, lastName);
                students[i] = student;

                System.out.println("Enter first name of the respective Student "+ (i+1));
                firstName = input.nextLine();
                student.setFirstName(firstName);

                System.out.println("Enter last name of the respective student");
                lastName = input.nextLine();
                student.setLastName(lastName);

                student.regID();
                student.addCourses();
                student.coursemoney();

                if (i == size - 1)
                    student.displayInfo(students);
            }
        } catch (NegativeArraySizeException e) {
            System.out.println("You are not allowed to wnter negative number for size");

        }
    }
}
