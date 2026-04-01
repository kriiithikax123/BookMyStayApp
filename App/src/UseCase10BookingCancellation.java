/**
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * Description:
 * This class demonstrates how confirmed
 * bookings can be cancelled safely.
 *
 * Inventory is restored and rollback
 * history is maintained.
 *
 * @version 10.0
 */
public class UseCase10BookingCancellation {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        // ---------------------------------------------------------
        // 1. Initialize shared inventory
        // ---------------------------------------------------------
        RoomInventory inventory = new RoomInventory();

        // ---------------------------------------------------------
        // 2. Initialize cancellation service and register
        //    a confirmed booking from UC6 (Single-1 / Single Room)
        // ---------------------------------------------------------
        CancellationService cancellationService = new CancellationService();
        cancellationService.registerBooking("Single-1", "Single Room");

        // ---------------------------------------------------------
        // 3. Perform cancellation and rollback
        // ---------------------------------------------------------
        System.out.println("Booking Cancellation");
        cancellationService.cancelBooking("Single-1", inventory);

        // ---------------------------------------------------------
        // 4. Display rollback history (LIFO - most recent first)
        // ---------------------------------------------------------
        cancellationService.showRollbackHistory();

        // ---------------------------------------------------------
        // 5. Confirm restored inventory count
        // ---------------------------------------------------------
        System.out.println("\nUpdated Single Room Availability: "
                + inventory.getAvailableCount("Single Room"));
    }
}