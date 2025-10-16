import java.util.Scanner;
public class practical {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueCalculation = true;

        System.out.println("=== Simple Java Calculator ===");

        while (continueCalculation) {
            try {
                System.out.print("Enter first number: ");
                double num1 = Double.parseDouble(scanner.nextLine());

                System.out.print("Enter an operator (+, -, *, /, %): ");
                String operator = scanner.nextLine();

                if (!operator.matches("[+\\-*/%]")) {
                    System.out.println("Invalid operator. Try again.");
                    continue;
                }

                System.out.print("Enter second number: ");
                double num2 = Double.parseDouble(scanner.nextLine());

                double result;

                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            System.out.println("Error: Division by zero is not allowed.");
                            continue;
                        }
                        result = num1 / num2;
                        break;
                    case "%":
                        if (num2 == 0) {
                            System.out.println("Error: Modulus by zero is not allowed.");
                            continue;
                        }
                        result = num1 % num2;
                        break;
                    default:
                        System.out.println("Unexpected error.");
                        continue;
                }

                System.out.printf("Result: %.2f %s %.2f = %.2f%n", num1, operator, num2, result);

                System.out.print("Do you want to perform another calculation? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();
                if (!response.equals("yes")) {
                    continueCalculation = false;
                    System.out.println("Thank you for using the calculator.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter valid numbers.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        scanner.close();
    }


    
}