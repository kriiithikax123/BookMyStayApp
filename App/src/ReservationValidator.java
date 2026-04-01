/**
 * ============================================================
 * CLASS - ReservationValidator
 * ============================================================
 *
 * Use Case 9: Error Handling & Validation
 *
 * Description:
 * This class validates booking input
 * before it is processed by the system.
 *
 * All validation rules are centralized
 * to avoid duplication and inconsistency.
 *
 * @version 9.0
 */
public class ReservationValidator {

    /**
     * Validates booking input provided by the user.
     *
     * Checks:
     * - Guest name must not be null or empty.
     * - Room type must be one of the valid types
     *   (case-sensitive: Single Room, Double Room, Suite Room).
     * - Inventory must have availability for the requested room type.
     *
     * @param guestName name of the guest
     * @param roomType  requested room type
     * @param inventory centralized inventory
     * @throws InvalidBookingException if validation fails
     */
    public void validate(
            String guestName,
            String roomType,
            RoomInventory inventory
    ) throws InvalidBookingException {

        // Rule 1: Guest name must not be blank
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        // Rule 2: Room type must be valid (case-sensitive)
        if (!roomType.equals("Single Room")
                && !roomType.equals("Double Room")
                && !roomType.equals("Suite Room")) {
            throw new InvalidBookingException("Invalid room type selected.");
        }

        // Rule 3: Room must be available in inventory
        if (inventory.getAvailableCount(roomType) <= 0) {
            throw new InvalidBookingException(
                    "No rooms available for type: " + roomType);
        }
    }
}