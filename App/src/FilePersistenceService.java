import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * ============================================================
 * CLASS - FilePersistenceService
 * ============================================================
 *
 * Use Case 12: Data Persistence & System Recovery
 *
 * Description:
 * This class handles saving and loading
 * room inventory state to and from a file.
 *
 * It ensures system state survives
 * application restarts.
 *
 * @version 12.0
 */
public class FilePersistenceService {

    /**
     * Saves room inventory state to a file.
     *
     * Each line follows the format:
     * roomType=availableCount
     *
     * @param inventory centralized room inventory
     * @param filePath  path to persistence file
     */
    public void saveInventory(RoomInventory inventory, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            Map<String, Integer> roomAvailability = inventory.getRoomAvailability();

            for (Map.Entry<String, Integer> entry : roomAvailability.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }

            System.out.println("Inventory saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    /**
     * Loads room inventory state from a file.
     *
     * Each line is expected in the format:
     * roomType=availableCount
     *
     * If the file is missing or corrupted,
     * the system starts fresh with default values.
     *
     * @param inventory centralized room inventory
     * @param filePath  path to persistence file
     */
    public void loadInventory(RoomInventory inventory, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            boolean validDataFound = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String roomType = parts[0].trim();
                    int count       = Integer.parseInt(parts[1].trim());
                    inventory.updateAvailability(roomType, count);
                    validDataFound = true;
                }
            }

            if (!validDataFound) {
                System.out.println("No valid inventory data found. Starting fresh.");
            }

        } catch (IOException e) {
            // File missing or unreadable — start fresh with default inventory
            System.out.println("No valid inventory data found. Starting fresh.");
        }
    }
}