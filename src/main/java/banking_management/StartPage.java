package banking_management

public class StartPage {
    public static void main(String args[]) {
        UserManagement system = new UserManagement();
        Scanner scanner = new Scanner(System.in);
        Console console = System.console();
        if (console == null) {
            System.out.println("Console not active!");
            System.exit(0);
        }
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
                    char[] userInput = console.readPassword();
                    String signUpPassword = new String(userInput);
                    System.out.print("Confirm password: ");
                    char[] userInput1 = console.readPassword();
                    String confirmSignUpPassword = new String(userInput1);
                    if (signUpPassword.equals(confirmSignUpPassword)) {
                        flag = system.SignUp(signUpUsername, signUpPassword);
                    } else {
                        System.out.println("Wrong confirmation password. Try again.");
                    }
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
                                    char[] innerUserInput = console.readPassword();
                                    String loginPassword = new String(innerUserInput);
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
                    char[] innerUserInput = console.readPassword();
                    String loginPassword = new String(innerUserInput);
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
        scanner.close();
    }
}
