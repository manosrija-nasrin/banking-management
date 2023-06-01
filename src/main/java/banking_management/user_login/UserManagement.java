package banking_management.user_login;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class UserManagement {
    private Map<String, String> users;
    private static final String FILE_PATH = "../../textfiles/user_details.txt";

    public UserManagement() {
        users = new HashMap<>();
        readFromFile();
    }

    private void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];
                    users.put(username, password);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String hashPassword(String password) {
        return Integer.toString(password.hashCode());
    }

    private void writeToFile(String username, String password) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(username + ":" + password);
            writer.newLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean SignUp(String username, String password) {
        if (users.containsKey(username)) {
            System.out.println("Username already used. Enter a different username.");
            return true;
        } else {
            String hashedPassword = hashPassword(password);
            users.put(username, hashedPassword);
            writeToFile(username, hashedPassword);
            System.out.println("Sign Up successful.");
            return false;
        }
    }

    public boolean LogIn(String username, String password) {
        if (users.containsKey(username)) {
            String storedPassword = users.get(username);
            String hashedPassword = hashPassword(password);
            if (storedPassword.equals(hashedPassword)) {
                System.out.println("Login successful. Welcome, " + username + "!");
                return false;
                // Connect here with other classes
            } else {
                System.out.println("Wrong password. Please try again.");
                return true;
            }
        } else {
            System.out.println("Invalid username. Please try again.");
            return true;
        }
    }
}