/******************** Importing Essential Libraries ************************/

import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Scanner;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;
/*************************** MENU OF EMS ****************************/

class MainMenu {
    public void menu() {
        clearScreen();
        printHeader();
        System.out.println("\n\nPress 1 : To Add an Employee Details");
        System.out.println("Press 2 : To See an Employee Details ");
        System.out.println("Press 3 : To Remove an Employee");
        System.out.println("Press 4 : To Update Employee Details");
        System.out.println("Press 5 : To Exit the EMS Portal");
        System.out.println("Press 6 : Help");
        System.out.println("Press 7 : About Developer");
        System.out.println("Press 8 : Instructions");
    }

    public void printHeader() {
        System.out.println("\t\t*******************************************");
        System.out.println("\t\t\t  EMPLOYEE MANAGEMENT SYSTEM");
        System.out.println("\t\t*******************************************");
        System.out.println("\t\t\t    --------------------");
        System.out.println("\t\t\t     ~$ Abhinav Dubey");
        System.out.println("\t\t\t    --------------------");
    }

    public void printFooter() {
        System.out.println("\n*****************************************");
        System.out.println("   Thank you for using Employee Manager");
        System.out.println("*****************************************");
    }

    public void invalidChoiceMessage() {
        System.out.println("\nInvalid choice! Please try again.");
    }

    public void waitForUser() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nPress Enter to continue...");
        sc.nextLine();
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void printInstructions() {
        System.out.println("\n*** INSTRUCTIONS ***");
        System.out.println("1. Make sure you have correct Employee ID when updating or viewing.");
        System.out.println("2. Enter all information accurately.");
        System.out.println("3. To exit the program safely, choose Option 5.");
    }

    public void printAboutDeveloper() {
        System.out.println("\n*** ABOUT DEVELOPER ***");
        System.out.println("This EMS application was developed by Abhinav Dubey.");
        System.out.println("Passionate about Java and building console-based applications.");
    }

    public void printHelpMenu() {
        System.out.println("\n*** HELP MENU ***");
        System.out.println("1 - Add Employee: Adds a new employee's record.");
        System.out.println("2 - View Employee: Displays an employee's details.");
        System.out.println("3 - Remove Employee: Deletes an employee's record.");
        System.out.println("4 - Update Employee: Modify existing details.");
        System.out.println("5 - Exit: Close the EMS.");
        System.out.println("6 - Help: Shows this help menu.");
        System.out.println("7 - About: Info about the developer.");
        System.out.println("8 - Instructions: Shows system usage instructions.");
    }

    public boolean confirmExit() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Are you sure you want to exit? (yes/no): ");
        String input = sc.nextLine().trim().toLowerCase();
        return input.equals("yes") || input.equals("y");
    }
}

/************************ To add details of Employee *********************/

class Employee_Add {

    public void createFile() {
        Scanner sc = new Scanner(System.in);

        EmployDetail emp = new EmployDetail();
        emp.getInfo();

        try {
            File f1 = new File("file" + emp.employ_id + ".txt");
            if (f1.createNewFile()) {
                FileWriter myWriter = new FileWriter("file" + emp.employ_id + ".txt");
                myWriter.write("Employee ID       : " + emp.employ_id + "\n" +
                               "Employee Name     : " + emp.name + "\n" +
                               "Father's Name     : " + emp.father_name + "\n" +
                               "Employee Contact  : " + emp.employ_contact + "\n" +
                               "Email Information : " + emp.email + "\n" +
                               "Employee Position : " + emp.position + "\n" +
                               "Employee Salary   : " + emp.employ_salary);
                myWriter.close();
                System.out.println("\nEmployee has been Added :)\n");

                System.out.print("\nPress Enter to Continue...");
                sc.nextLine();
            } else {
                System.out.println("\nEmployee already exists :(");
                System.out.print("\nPress Enter to Continue...");
                sc.nextLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean validateEmailFormat(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public boolean validatePhoneNumber(String phone) {
        return phone.matches("\\d{10}");
    }

    public EmployDetail promptAndValidateDetails() {
        Scanner sc = new Scanner(System.in);
        EmployDetail emp = new EmployDetail();

        System.out.print("Enter Employee's name --------: ");
        emp.name = sc.nextLine();

        System.out.print("Enter Employee's Father name -: ");
        emp.father_name = sc.nextLine();

        System.out.print("Enter Employee's ID ----------: ");
        emp.employ_id = sc.nextLine();

        do {
            System.out.print("Enter Employee's Email ID ----: ");
            emp.email = sc.nextLine();
            if (!validateEmailFormat(emp.email)) {
                System.out.println("Invalid email format. Try again.");
            }
        } while (!validateEmailFormat(emp.email));

        System.out.print("Enter Employee's Position ----: ");
        emp.position = sc.nextLine();

        do {
            System.out.print("Enter Employee contact Info --: ");
            emp.employ_contact = sc.nextLine();
            if (!validatePhoneNumber(emp.employ_contact)) {
                System.out.println("Invalid contact number. Must be 10 digits.");
            }
        } while (!validatePhoneNumber(emp.employ_contact));

        System.out.print("Enter Employee's Salary ------: ");
        emp.employ_salary = sc.nextLine();

        return emp;
    }

    public void createFileWithValidation() {
        EmployDetail emp = promptAndValidateDetails();

        if (employeeExists(emp.employ_id)) {
            System.out.println("\nEmployee already exists :(");
            return;
        }

        try {
            FileWriter myWriter = new FileWriter("file" + emp.employ_id + ".txt");
            myWriter.write("Employee ID       : " + emp.employ_id + "\n" +
                           "Employee Name     : " + emp.name + "\n" +
                           "Father's Name     : " + emp.father_name + "\n" +
                           "Employee Contact  : " + emp.employ_contact + "\n" +
                           "Email Information : " + emp.email + "\n" +
                           "Employee Position : " + emp.position + "\n" +
                           "Employee Salary   : " + emp.employ_salary);
            myWriter.close();
            System.out.println("\nEmployee has been added successfully.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public boolean employeeExists(String id) {
        File file = new File("file" + id + ".txt");
        return file.exists();
    }

    public void showEmployeeCount() {
        File dir = new File(".");
        String[] files = dir.list((d, name) -> name.startsWith("file") && name.endsWith(".txt"));
        if (files != null) {
            System.out.println("\nTotal number of employees: " + files.length);
        } else {
            System.out.println("\nNo employee data found.");
        }
    }
}
/************************* Taking Employee Details ************************/

class EmployDetail {
    String name;
    String father_name;
    String email;
    String position;
    String employ_id;
    String employ_salary;
    String employ_contact;

    // Collect information from user input
    public void getInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Employee's name --------: ");
        name = sc.nextLine();
        System.out.print("Enter Employee's Father name -: ");
        father_name = sc.nextLine();
        System.out.print("Enter Employee's ID ----------: ");
        employ_id = sc.nextLine();
        System.out.print("Enter Employee's Email ID ----: ");
        email = sc.nextLine();
        System.out.print("Enter Employee's Position ----: ");
        position = sc.nextLine();
        System.out.print("Enter Employee contact Info --: ");
        employ_contact = sc.nextLine();
        System.out.print("Enter Employee's Salary ------: ");
        employ_salary = sc.nextLine();
    }

    // Display employee data nicely
    public void displayDetails() {
        System.out.println("\n--------- Employee Information ---------");
        System.out.println("ID          : " + employ_id);
        System.out.println("Name        : " + name);
        System.out.println("Father Name : " + father_name);
        System.out.println("Email       : " + email);
        System.out.println("Contact     : " + employ_contact);
        System.out.println("Position    : " + position);
        System.out.println("Salary      : " + employ_salary);
        System.out.println("----------------------------------------");
    }

    // Return details as a formatted string (for saving to file)
    public String toDataString() {
        return "Employee ID       : " + employ_id + "\n" +
               "Employee Name     : " + name + "\n" +
               "Father's Name     : " + father_name + "\n" +
               "Employee Contact  : " + employ_contact + "\n" +
               "Email Information : " + email + "\n" +
               "Employee Position : " + position + "\n" +
               "Employee Salary   : " + employ_salary;
    }

    // Validate email format
    public boolean isValidEmail() {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    // Validate contact number (10-digit)
    public boolean isValidContact() {
        return employ_contact != null && employ_contact.matches("\\d{10}");
    }

    // Validate salary as numeric
    public boolean isValidSalary() {
        return employ_salary != null && employ_salary.matches("\\d+");
    }

    // Check if required fields are filled
    public boolean isComplete() {
        return name != null && !name.isEmpty() &&
               father_name != null && !father_name.isEmpty() &&
               employ_id != null && !employ_id.isEmpty() &&
               email != null && !email.isEmpty() &&
               position != null && !position.isEmpty() &&
               employ_contact != null && !employ_contact.isEmpty() &&
               employ_salary != null && !employ_salary.isEmpty();
    }

    // Convert to compact string (for logs or short display)
    public String toShortString() {
        return employ_id + " - " + name + " (" + position + ")";
    }

    // Check if employee matches a search keyword (name or ID)
    public boolean matchesKeyword(String keyword) {
        return employ_id.contains(keyword) ||
               name.toLowerCase().contains(keyword.toLowerCase()) ||
               email.toLowerCase().contains(keyword.toLowerCase());
    }
}
/************************ To Show details of Employee *********************/
class Employee_Show {

    // View specific employee details by ID
    public void viewFile(String id) throws IOException {
        File file = new File("file" + id + ".txt");
        if (!file.exists()) {
            System.out.println("\n[!] Employee with ID " + id + " does not exist.");
            return;
        }

        System.out.println("\n----------- Employee Record -----------");
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        }
        System.out.println("---------------------------------------");
    }

    // List all available employee IDs
    public void listAllEmployees() {
        File[] files = getEmployeeFiles();
        if (files.length == 0) {
            System.out.println("\n[!] No employee records found.");
            return;
        }

        System.out.println("\nList of All Employees:");
        for (File f : files) {
            String id = f.getName().replace("file", "").replace(".txt", "");
            System.out.println(" - Employee ID: " + id);
        }
    }

    // Count the number of employees
    public int countEmployees() {
        return getEmployeeFiles().length;
    }

    // Search for employees by keyword
    public void searchByKeyword(String keyword) {
        File[] files = getEmployeeFiles();
        boolean found = false;

        for (File f : files) {
            try (Scanner sc = new Scanner(f)) {
                while (sc.hasNextLine()) {
                    if (sc.nextLine().toLowerCase().contains(keyword.toLowerCase())) {
                        System.out.println("\nMatch Found in File: " + f.getName());
                        viewFile(f.getName().replace("file", "").replace(".txt", ""));
                        found = true;
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error reading file: " + f.getName());
            }
        }

        if (!found) {
            System.out.println("\n[!] No records matched the keyword: " + keyword);
        }
    }

    // Print summary of all employee records (e.g., name + position)
    public void printAllEmployeeSummaries() {
        File[] files = getEmployeeFiles();
        if (files.length == 0) {
            System.out.println("\n[!] No employee records found.");
            return;
        }

        System.out.println("\nEmployee Summaries:");
        for (File f : files) {
            try (Scanner sc = new Scanner(f)) {
                String summary = "";
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    if (line.toLowerCase().contains("employee name") || line.toLowerCase().contains("employee position")) {
                        summary += line + " | ";
                    }
                }
                System.out.println("- " + summary);
            } catch (Exception e) {
                System.out.println("Error reading summary from: " + f.getName());
            }
        }
    }

    // Helper method to retrieve employee record files
    private File[] getEmployeeFiles() {
        File dir = new File(".");
        File[] files = dir.listFiles((FilenameFilter) (d, name) ->
            name.startsWith("file") && name.endsWith(".txt"));
        return (files != null) ? files : new File[0];
    }
}

/***************************** To Remove Employee *************************/
class Employee_Remove {

    // Remove a specific employee by ID
    public void removeFile(String ID) {
        File file = new File("file" + ID + ".txt");
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("\nEmployee has been removed successfully.");
            } else {
                System.out.println("\n[!] Failed to remove the employee file.");
            }
        } else {
            System.out.println("\n[!] Employee does not exist.");
        }
    }

    // Remove all employee records
    public void removeAllEmployees() {
        File[] files = getEmployeeFiles();
        if (files.length == 0) {
            System.out.println("\n[!] No employee records found to delete.");
            return;
        }

        int deletedCount = 0;
        for (File file : files) {
            if (file.delete()) {
                deletedCount++;
            }
        }

        System.out.println("\n[✓] " + deletedCount + " employee record(s) removed successfully.");
    }

    // Remove employee records that match a keyword
    public void removeByKeyword(String keyword) {
        File[] files = getEmployeeFiles();
        boolean found = false;

        for (File file : files) {
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNextLine()) {
                    if (sc.nextLine().toLowerCase().contains(keyword.toLowerCase())) {
                        if (file.delete()) {
                            System.out.println("Removed file: " + file.getName());
                            found = true;
                        }
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("[!] Error processing file: " + file.getName());
            }
        }

        if (!found) {
            System.out.println("\n[!] No matching employee records found for keyword: " + keyword);
        }
    }

    // Archive an employee record (rename instead of delete)
    public void archiveEmployee(String ID) {
        File file = new File("file" + ID + ".txt");
        File archive = new File("archive_" + ID + ".txt");

        if (file.exists()) {
            if (file.renameTo(archive)) {
                System.out.println("\nEmployee record archived successfully.");
            } else {
                System.out.println("\n[!] Failed to archive the record.");
            }
        } else {
            System.out.println("\n[!] Employee does not exist.");
        }
    }

    // Check if an employee exists before deleting
    public boolean employeeExists(String ID) {
        File file = new File("file" + ID + ".txt");
        return file.exists();
    }

    // Helper method to retrieve all employee files
    private File[] getEmployeeFiles() {
        File dir = new File(".");
        File[] files = dir.listFiles((FilenameFilter) (d, name) ->
            name.startsWith("file") && name.endsWith(".txt"));
        return (files != null) ? files : new File[0];
    }
}

/************************ To Update details of Employee ********************/
class Employee_Update {

    // Update a specific value based on old and new text (basic function)
    public void updateFile(String employeeID, String oldText, String newText) throws IOException {
        File file = new File("file" + employeeID + ".txt");
        if (!file.exists()) {
            System.out.println("Employee not found.");
            return;
        }

        Scanner sc = new Scanner(file);
        StringBuilder fileContent = new StringBuilder();

        while (sc.hasNextLine()) {
            fileContent.append(sc.nextLine().replaceAll(oldText, newText)).append("\n");
        }
        sc.close();

        FileWriter writer = new FileWriter(file);
        writer.write(fileContent.toString());
        writer.close();
    }

    // Update a known field's value using field name
    public void updateField(String employeeID, String fieldName, String newValue) throws IOException {
        File file = new File("file" + employeeID + ".txt");
        if (!file.exists()) {
            System.out.println("Employee not found.");
            return;
        }

        Scanner sc = new Scanner(file);
        StringBuilder fileContent = new StringBuilder();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.startsWith(fieldName + ":")) {
                fileContent.append(fieldName).append(": ").append(newValue).append("\n");
            } else {
                fileContent.append(line).append("\n");
            }
        }
        sc.close();

        FileWriter writer = new FileWriter(file);
        writer.write(fileContent.toString());
        writer.close();

        System.out.println(fieldName + " updated successfully!");
    }

    // Show all fields in an employee record
    public void showAllFields(String employeeID) throws IOException {
        File file = new File("file" + employeeID + ".txt");
        if (!file.exists()) {
            System.out.println("Employee not found.");
            return;
        }

        Scanner sc = new Scanner(file);
        System.out.println("\nCurrent Employee Details:");
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }
        sc.close();
    }

    // Allow user to interactively select and update a field
    public void interactiveUpdate(String employeeID) throws IOException {
        Scanner input = new Scanner(System.in);

        if (!employeeExists(employeeID)) {
            System.out.println("Employee ID not found.");
            return;
        }

        System.out.println("\nAvailable fields you can update:");
        List<String> fields = getFieldNames(employeeID);
        for (String field : fields) {
            System.out.println("- " + field);
        }

        System.out.print("\nEnter the field name to update: ");
        String fieldToUpdate = input.nextLine().trim();

        if (!fields.contains(fieldToUpdate)) {
            System.out.println("Invalid field name.");
            return;
        }

        System.out.print("Enter the new value: ");
        String newValue = input.nextLine().trim();

        updateField(employeeID, fieldToUpdate, newValue);
    }

    // Helper: get all field names from employee file
    public List<String> getFieldNames(String employeeID) throws IOException {
        File file = new File("file" + employeeID + ".txt");
        List<String> fieldNames = new ArrayList<>();
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.contains(":")) {
                fieldNames.add(line.split(":")[0].trim());
            }
        }
        sc.close();
        return fieldNames;
    }

    // Check if employee file exists
    public boolean employeeExists(String employeeID) {
        File file = new File("file" + employeeID + ".txt");
        return file.exists();
    }
}

/************************ To Exit from the EMS Portal *********************/
class CodeExit {

    // Exits the application gracefully with confirmation and logging
    public void out() {
        if (confirmExit()) {
            clearScreen();
            saveBeforeExit();
            displayExitMessage();
            logSessionEnd();
            System.exit(0);
        } else {
            System.out.println("\nExit cancelled. Returning to EMS Portal...");
            waitForUser();
        }
    }

    // Confirms the user's intention to exit
    public boolean confirmExit() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nAre you sure you want to exit the EMS Portal? (y/n): ");
        String input = sc.nextLine().trim().toLowerCase();
        return input.equals("y") || input.equals("yes");
    }

    // Displays a goodbye message
    public void displayExitMessage() {
        System.out.println("\n*****************************************");
        System.out.println("  Thank you for using Employee Manager :)");
        System.out.println("*****************************************");
        System.out.println("\t\t/~ <0d3d by Abhinav Dubey\n");
    }

    // Logs session end time into a text file
    public void logSessionEnd() {
        try (FileWriter logWriter = new FileWriter("session_log.txt", true)) {
            String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            logWriter.write("Session ended at: " + timeStamp + "\n");
        } catch (IOException e) {
            System.out.println("Unable to write session log: " + e.getMessage());
        }
    }

    // Prepares the system before exit (optional: could save app state)
    public void saveBeforeExit() {
        System.out.println("\nSaving any unsaved data before exit...");
        // Placeholder for future saving logic
    }

    // Clears the console screen for neatness
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Waits for the user to press Enter
    public void waitForUser() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nPress Enter to continue...");
        sc.nextLine();
    }
}
/***************************** Main Class *******************************/
class EmployManagementSystem {

    private static final Scanner sc = new Scanner(System.in);
    private static final MainMenu menu = new MainMenu();
    private static final Employee_Show viewer = new Employee_Show();
    private static final String DATA_DIRECTORY = ".";  // Default directory

    public static void main(String[] args) {
        runEMS();
    }

    // Runs the main loop of the system
    public static void runEMS() {
        int choice;
        do {
            menu.menu();
            choice = getUserChoice();
            handleChoice(choice);
        } while (choice != 5);
    }

    // Gets user choice safely
    private static int getUserChoice() {
        try {
            System.out.print("\nPlease Enter your choice: ");
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            menu.invalidChoiceMessage();
            return -1;
        }
    }

    // Handles choice routing
    private static void handleChoice(int choice) {
        switch (choice) {
            case 1 -> addEmployee();
            case 2 -> viewEmployee();
            case 3 -> deleteEmployee();
            case 4 -> updateEmployee();
            case 5 -> exitEMS();
            default -> {
                menu.invalidChoiceMessage();
                menu.waitForUser();
            }
        }
    }

    // Adds new employee
    private static void addEmployee() {
        new Employee_Add().createFile();
        menu.waitForUser();
    }

    // Views employee details
    private static void viewEmployee() {
        System.out.print("Enter Employee ID: ");
        String id = sc.nextLine();
        try {
            viewer.viewFile(id);
        } catch (Exception e) {
            System.out.println("Error: Employee not found or invalid ID.");
        }
        menu.waitForUser();
    }

    // Deletes employee file
    private static void deleteEmployee() {
        System.out.print("Enter Employee ID to Remove: ");
        String id = sc.nextLine();
        new Employee_Remove().removeFile(id);
        menu.waitForUser();
    }

    // Updates employee details
    private static void updateEmployee() {
        System.out.print("Enter Employee ID to Update: ");
        String id = sc.nextLine();
        try {
            viewer.viewFile(id);
            System.out.print("\nEnter Current Detail to Replace: ");
            String oldData = sc.nextLine();
            System.out.print("Enter New Detail: ");
            String newData = sc.nextLine();
            new Employee_Update().updateFile(id, oldData, newData);
        } catch (Exception e) {
            System.out.println("Error updating employee: " + e.getMessage());
        }
        menu.waitForUser();
    }

    // Graceful exit from EMS
    private static void exitEMS() {
        new CodeExit().out();
    }

    // NEW: Lists all employee files
    public static void listAllEmployees() {
        File dir = new File(DATA_DIRECTORY);
        File[] files = dir.listFiles((d, name) -> name.startsWith("file") && name.endsWith(".txt"));
        System.out.println("\n--- All Employees ---");
        if (files == null || files.length == 0) {
            System.out.println("No employee records found.");
        } else {
            for (File f : files) {
                System.out.println(f.getName().replace("file", "").replace(".txt", ""));
            }
        }
        menu.waitForUser();
    }

    // NEW: Search employee file by name (simplistic, partial match)
    public static void searchEmployeeByName(String keyword) {
        File dir = new File(DATA_DIRECTORY);
        File[] files = dir.listFiles((d, name) -> name.startsWith("file") && name.endsWith(".txt"));
        boolean found = false;

        if (files != null) {
            for (File f : files) {
                try (Scanner fileReader = new Scanner(f)) {
                    while (fileReader.hasNextLine()) {
                        String line = fileReader.nextLine();
                        if (line.toLowerCase().contains("name") && line.toLowerCase().contains(keyword.toLowerCase())) {
                            System.out.println("\nMatch found in file: " + f.getName());
                            viewer.viewFile(f.getName().replace("file", "").replace(".txt", ""));
                            found = true;
                            break;
                        }
                    }
                } catch (Exception ignored) {}
            }
        }

        if (!found) {
            System.out.println("No employee with matching name found.");
        }

        menu.waitForUser();
    }
} 
/****************************** CODED BY ABHINAV DUBEY ************************/
