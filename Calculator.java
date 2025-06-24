import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        System.out.println("Calculator Designed by SHYAM MOHAN P");
        System.out.println("You can perform + - * / expression with two numbers");
        System.out.println("Type 'c' to close.\n\n");
            System.out.print("Enter expression (e.g., 5+3): ");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine().replace(" ", "");

            if (input.equalsIgnoreCase("c")) {
                System.out.println("Calculator is closed.");
                break;
            }

            char operator = 0;
            int index = -1;

            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (c == '+' || c == '-' || c == '*' || c == '/') {
                    operator = c;
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                System.out.println("Error: invalid operator.");
                continue;
            }

            try {
                double num1 = Double.parseDouble(input.substring(0, index));
                double num2 = Double.parseDouble(input.substring(index + 1));
                double result = 0;

                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/':
                        if (num2 == 0) {
                            System.out.println("Error: Division by zero");
                            continue;
                        }
                        result = num1 / num2;
                        break;
                    default:
                        System.out.println("Error: invalid operator");
                        continue;
                }

                System.out.println("= " + result + "\n");
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid numbers");
            }
        }

        scanner.close();
    }
}
