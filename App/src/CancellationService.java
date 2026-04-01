import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * ============================================================
 * CLASS - CancellationService
 * ============================================================
 *
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * Description:
 * This class is responsible for handling
 * booking cancellations.
 *
 * It ensures that:
 * - Cancelled room IDs are tracked
 * - Inventory is restored correctly
 * - Invalid cancellations are prevented
 *
 * A stack is used to model rollback behavior.
 *
 * @version 10.0
 */
public class CancellationService {

    /**
     * Stack that stores recently released room IDs.
     * LIFO order naturally models rollback behavior.
     */
    private Stack<String> releasedRoomIds;

    /**
     * Maps reservation ID to room type.
     * Required to restore the correct inventory bucket on cancellation.
     *
     * Key   -> Reservation ID  (e.g. "Single-1")
     * Value -> Room type       (e.g. "Single Room")
     */
    private Map<String, String> reservationRoomTypeMap;

    /**
     * Initializes cancellation tracking structures.
     */
    public CancellationService() {
        releasedRoomIds       = new Stack<>();
        reservationRoomTypeMap = new HashMap<>();
    }

    /**
     * Registers a confirmed booking.
     *
     * This method simulates storing confirmation
     * data that will later be required for cancellation.
     *
     * @param reservationId confirmed reservation ID
     * @param roomType      allocated room type
     */
    public void registerBooking(String reservationId, String roomType) {
        reservationRoomTypeMap.put(reservationId, roomType);
    }

    /**
     * Cancels a confirmed booking and
     * restores inventory safely.
     *
     * Steps:
     * 1. Validate that the reservation exists.
     * 2. Push the released room ID onto the rollback stack.
     * 3. Restore inventory count immediately.
     * 4. Remove the reservation from the active map.
     *
     * @param reservationId reservation to cancel
     * @param inventory     centralized room inventory
     */
    public void cancelBooking(String reservationId, RoomInventory inventory) {

        // Step 1: Validate reservation exists
        if (!reservationRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Cancellation failed: Reservation ID '"
                    + reservationId + "' not found.");
            return;
        }

        String roomType = reservationRoomTypeMap.get(reservationId);

        // Step 2: Push released room ID onto the rollback stack (LIFO)
        releasedRoomIds.push(reservationId);

        // Step 3: Restore inventory immediately
        int restored = inventory.getAvailableCount(roomType) + 1;
        inventory.updateAvailability(roomType, restored);

        // Step 4: Remove from active reservations
        reservationRoomTypeMap.remove(reservationId);

        // Strip " Room" for cleaner output: "Single Room" -> "Single"
        String shortType = roomType.replace(" Room", "");
        System.out.println("Booking cancelled successfully. "
                + "Inventory restored for room type: " + shortType);
    }

    /**
     * Displays recently cancelled reservations.
     *
     * This method helps visualize rollback order.
     * Most recently cancelled reservation is shown first (LIFO).
     */
    public void showRollbackHistory() {
        System.out.println("\nRollback History (Most Recent First):");

        // Peek through the stack without destroying it
        Stack<String> temp = new Stack<>();
        temp.addAll(releasedRoomIds);

        while (!temp.isEmpty()) {
            System.out.println("Released Reservation ID: " + temp.pop());
        }
    }
}