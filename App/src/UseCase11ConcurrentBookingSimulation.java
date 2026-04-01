/**
 * Use Case 11: Concurrent Booking Simulation
 *
 * Description:
 * This class simulates multiple users
 * attempting to book rooms at the same time.
 *
 * It highlights race conditions and
 * demonstrates how synchronization
 * prevents inconsistent allocations.
 *
 * @version 11.0
 */
public class UseCase11ConcurrentBookingSimulation {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        // ---------------------------------------------------------
        // 1. Initialize shared resources
        // ---------------------------------------------------------
        BookingRequestQueue   bookingQueue      = new BookingRequestQueue();
        RoomInventory         inventory         = new RoomInventory();
        RoomAllocationService allocationService = new RoomAllocationService();

        // ---------------------------------------------------------
        // 2. Populate the shared queue with booking requests
        //    (simulating multiple guests arriving concurrently)
        // ---------------------------------------------------------
        bookingQueue.addRequest(new Reservation("Abhi",     "Single Room"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Double Room"));
        bookingQueue.addRequest(new Reservation("Kural",    "Suite Room"));
        bookingQueue.addRequest(new Reservation("Subha",    "Single Room"));

        // ---------------------------------------------------------
        // 3. Create booking processor tasks
        // ---------------------------------------------------------
        Thread t1 = new Thread(
                new ConcurrentBookingProcessor(
                        bookingQueue, inventory, allocationService
                )
        );

        Thread t2 = new Thread(
                new ConcurrentBookingProcessor(
                        bookingQueue, inventory, allocationService
                )
        );

        // ---------------------------------------------------------
        // 4. Start concurrent processing
        // ---------------------------------------------------------
        System.out.println("Concurrent Booking Simulation");

        t1.start();
        t2.start();

        try {
            // Wait for both threads to finish before reporting
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        // ---------------------------------------------------------
        // 5. Display remaining inventory after all allocations
        // ---------------------------------------------------------
        System.out.println("\nRemaining Inventory:");
        System.out.println("Single: " + inventory.getAvailableCount("Single Room"));
        System.out.println("Double: " + inventory.getAvailableCount("Double Room"));
        System.out.println("Suite: "  + inventory.getAvailableCount("Suite Room"));
    }
}