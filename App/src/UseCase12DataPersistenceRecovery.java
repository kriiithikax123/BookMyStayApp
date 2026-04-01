/**
 * ============================================================
 * CLASS - UseCase12DataPersistenceRecovery
 * ============================================================
 *
 * Use Case 12: Data Persistence & System Recovery
 *
 * Description:
 * This class demonstrates how system state
 * can be restored after an application restart.
 *
 * Inventory data is loaded from a file
 * before any booking operations occur.
 *
 * @version 12.0
 */
public class UseCase12DataPersistenceRecovery {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        // ---------------------------------------------------------
        // 1. Define persistence file path
        // ---------------------------------------------------------
        String filePath = "inventory.txt";

        // ---------------------------------------------------------
        // 2. Initialize services
        // ---------------------------------------------------------
        RoomInventory          inventory          = new RoomInventory();
        FilePersistenceService persistenceService = new FilePersistenceService();

        // ---------------------------------------------------------
        // 3. Attempt to load persisted inventory on startup
        //    If file is missing, system starts fresh with defaults
        // ---------------------------------------------------------
        System.out.println("System Recovery");
        persistenceService.loadInventory(inventory, filePath);

        // ---------------------------------------------------------
        // 4. Display current inventory state after recovery
        // ---------------------------------------------------------
        System.out.println("\nCurrent Inventory:");
        System.out.println("Single: " + inventory.getAvailableCount("Single Room"));
        System.out.println("Double: " + inventory.getAvailableCount("Double Room"));
        System.out.println("Suite: "  + inventory.getAvailableCount("Suite Room"));

        // ---------------------------------------------------------
        // 5. Save current inventory state to file for next startup
        // ---------------------------------------------------------
        persistenceService.saveInventory(inventory, filePath);
    }
}