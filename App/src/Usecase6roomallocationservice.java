import java.util.LinkedList;
import java.util.Queue;

/**
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Description:
 * This class demonstrates how booking
 * requests are confirmed and rooms
 * are allocated safely.
 *
 * It consumes booking requests in FIFO
 * order and updates inventory immediately.
 *
 * @version 6.0
 */
public class UseCase6RoomAllocationService {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        // ---------------------------------------------------------
        // 1. Build the request queue (FIFO ordering)
        //    Room type strings must match RoomInventory keys exactly:
        //    "Single Room", "Double Room", "Suite Room"
        // ---------------------------------------------------------
        Queue<Reservation> bookingQueue = new LinkedList<>();
        bookingQueue.offer(new Reservation("Abhi",     "Single Room"));
        bookingQueue.offer(new Reservation("Subha",    "Single Room"));
        bookingQueue.offer(new Reservation("Vanmathi", "Suite Room"));

        // ---------------------------------------------------------
        // 2. Initialise shared inventory and allocation service
        // ---------------------------------------------------------
        RoomInventory         inventory         = new RoomInventory();
        RoomAllocationService allocationService = new RoomAllocationService();

        // ---------------------------------------------------------
        // 3. Process every request in FIFO order
        // ---------------------------------------------------------
        System.out.println("Room Allocation Processing");

        while (!bookingQueue.isEmpty()) {
            Reservation reservation = bookingQueue.poll();
            allocationService.allocateRoom(reservation, inventory);
        }
    }
}