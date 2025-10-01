import java.util.Scanner;

public class HotelBookingSystem {
    
    private static  int FLOORS = 3;
    private static  int ROOMS_PER_FLOOR = 5;
    
    public static void main(String[] args) {
        boolean[][] rooms = new boolean[FLOORS][ROOMS_PER_FLOOR]; // false = available, true = booked
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Hotel Room Booking System ---");
            System.out.println("1. View Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewRooms(rooms);
                    break;
                case 2:
                    bookRoom(rooms, scanner);
                    break;
                case 3:
                    System.out.println("Thank you for using the booking system!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);

        scanner.close();
    }

   
    public static void viewRooms(boolean[][] rooms) {
        System.out.println("\nRoom Status (A = Available, B = Booked):");
        for (int i = 0; i < rooms.length; i++) {
            System.out.print("Floor " + (i + 1) + ": ");
            for (int j = 0; j < rooms[i].length; j++) {
                System.out.print((rooms[i][j] ? "B " : "A "));
            }
            System.out.println();
        }
    }

  
    public static void bookRoom(boolean[][] rooms, Scanner scanner) {
        System.out.print("Enter floor number (1-" + rooms.length + "): ");
        int floor = scanner.nextInt();
        System.out.print("Enter room number (1-" + rooms[0].length + "): ");
        int room = scanner.nextInt();

        
        if (floor < 1 || floor > rooms.length || room < 1 || room > rooms[0].length) {
            System.out.println("Invalid floor or room number.");
            return;
        }

        if (rooms[floor - 1][room - 1]) {
            System.out.println("Room is already booked.");
        } else {
            rooms[floor - 1][room - 1] = true;
            System.out.println("Room booked successfully!");
        }
    }
}
