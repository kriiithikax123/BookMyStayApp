import java.util.Scanner;

/**
 * Use Case 9: Error Handling & Validation
 *
 * Description:
 * This class demonstrates how user input
 * is validated before booking is processed.
 *
 * The system:
 * - Accepts user input
 * - Validates input centrally
 * - Handles errors gracefully
 *
 * @version 9.0
 */
public class UseCase9ErrorHandlingValidation {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        // Display application header
        System.out.println("Booking Validation");

        Scanner scanner = new Scanner(System.in);

        // Initialize required components
        RoomInventory        inventory     = new RoomInventory();
        ReservationValidator validator     = new ReservationValidator();
        BookingRequestQueue  bookingQueue  = new BookingRequestQueue();

        try {
            // Read guest name from user
            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            // Read room type from user
            System.out.print("Enter room type (Single/Double/Suite): ");
            String input = scanner.nextLine();

            // Map short input to full room type key used in inventory
            String roomType = mapRoomType(input);

            // Validate all inputs against business rules
            validator.validate(guestName, roomType, inventory);

            // If validation passes, add to booking queue
            bookingQueue.addRequest(new Reservation(guestName, roomType));
            System.out.println("Booking accepted for Guest: " + guestName
                    + ", Room Type: " + roomType);

        } catch (InvalidBookingException e) {

            // Handle domain-specific validation errors
            System.out.println("Booking failed: " + e.getMessage());

        } finally {
            scanner.close();
        }
    }

    /**
     * Maps short user input to the full room type key
     * used in RoomInventory (case-sensitive).
     *
     * "Single" -> "Single Room"
     * "Double" -> "Double Room"
     * "Suite"  -> "Suite Room"
     *
     * Any other input is returned as-is so validation
     * can catch and report it correctly.
     *
     * @param input raw user input
     * @return mapped room type string
     */
    private static String mapRoomType(String input) {
        switch (input) {
            case "Single": return "Single Room";
            case "Double": return "Double Room";
            case "Suite":  return "Suite Room";
            default:       return input; // will fail validation → "Invalid room type selected."
        }
    }
}