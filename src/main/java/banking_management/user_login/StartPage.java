package banking_management.user_login;

import java.util.Scanner;

class StartPage {
    public static void main(String args[]) {
        UserManagement system = new UserManagement();
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String signUpUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String signUpPassword = scanner.nextLine();
                    flag = system.SignUp(signUpUsername, signUpPassword);
                    if (!flag) {
                        boolean flag2 = true;
                        while (flag2) {
                            System.out.println("1. Log In");
                            System.out.println("2. Exit");
                            System.out.print("Enter your choice: ");
                            int innerchoice = scanner.nextInt();
                            scanner.nextLine();
                            switch (innerchoice) {
                                case 1:
                                    System.out.print("Enter username: ");
                                    String loginUsername = scanner.nextLine();
                                    System.out.print("Enter password: ");
                                    String loginPassword = scanner.nextLine();
                                    flag2 = system.LogIn(loginUsername, loginPassword);
                                    break;
                                case 2:
                                    System.out.println("Exiting...");
                                    System.exit(0);
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    flag = system.LogIn(loginUsername, loginPassword);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }
}