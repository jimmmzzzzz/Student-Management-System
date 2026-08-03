import dao.StudentDaoImpl;
import model.Student;
import service.StudentService;

import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService service = new StudentService(new StudentDaoImpl());

    public static void main(String[] args) {
        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> displayStudents(service.listAll());
                case "2" -> addStudentPrompt();
                case "3" -> editStudentPrompt();
                case "4" -> deleteStudentPrompt();
                case "5" -> searchStudentPrompt();
                case "6" -> {
                    System.out.println("Exiting system...");
                    return;
                }
                default -> System.out.println("Invalid selection. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n==========================================");
        System.out.println("       STUDENT MANAGEMENT SYSTEM          ");
        System.out.println("==========================================");
        System.out.println("1. List All Students");
        System.out.println("2. Add New Student");
        System.out.println("3. Edit Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Search Students");
        System.out.println("6. Exit");
        System.out.print("Option > ");
    }

    private static void displayStudents(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        System.out.println("\n" + "-".repeat(85));
        System.out.printf("%-5s | %-12s | %-12s | %-22s | %-12s | %-5s%n", 
                "ID", "First Name", "Last Name", "Email", "Major", "GPA");
        System.out.println("-".repeat(85));

        for (Student s : students) {
            System.out.printf("%-5d | %-12s | %-12s | %-22s | %-12s | %-5.2f%n",
                    s.getId(), s.getFirstName(), s.getLastName(), s.getEmail(), s.getMajor(), s.getGpa());
        }
        System.out.println("-".repeat(85));
    }

    private static void addStudentPrompt() {
        System.out.print("First Name: ");
        String fName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Major: ");
        String major = scanner.nextLine();
        System.out.print("GPA (0.0 - 4.0): ");
        
        try {
            double gpa = Double.parseDouble(scanner.nextLine());
            boolean success = service.registerStudent(fName, lName, email, major, gpa);
            System.out.println(success ? "Student added successfully." : "Failed to add student. Check input rules.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid GPA value.");
        }
    }

    private static void editStudentPrompt() {
        System.out.print("Enter Student ID to edit: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("New First Name (Enter to skip): ");
            String fn = scanner.nextLine();
            System.out.print("New Last Name (Enter to skip): ");
            String ln = scanner.nextLine();
            System.out.print("New Email (Enter to skip): ");
            String email = scanner.nextLine();
            System.out.print("New Major (Enter to skip): ");
            String major = scanner.nextLine();
            System.out.print("New GPA (Enter to skip): ");
            String gpaStr = scanner.nextLine();

            boolean success = service.updateStudentDetails(id, fn, ln, email, major, gpaStr);
            System.out.println(success ? "Student updated successfully." : "Update failed or ID not found.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
        }
    }

    private static void deleteStudentPrompt() {
        System.out.print("Enter Student ID to delete: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            boolean success = service.removeStudent(id);
            System.out.println(success ? "Student removed." : "Student ID not found.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
        }
    }

    private static void searchStudentPrompt() {
        System.out.print("Enter search query (name): ");
        String q = scanner.nextLine();
        displayStudents(service.search(q));
    }
}
