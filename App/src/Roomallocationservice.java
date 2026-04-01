import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RoomAllocationService {

    private Set<String> allocatedRoomIds;
    private Map<String, Set<String>> assignedRoomsByType;

    public RoomAllocationService() {
        allocatedRoomIds    = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
    }

    public void allocateRoom(Reservation reservation, RoomInventory inventory) {
        String roomType = reservation.getRoomType();

        if (inventory.getAvailableCount(roomType) <= 0) {
            System.out.println("No rooms available for type: " + roomType
                    + " (Guest: " + reservation.getGuestName() + ")");
            return;
        }

        String roomId = generateRoomId(roomType);
        allocatedRoomIds.add(roomId);
        assignedRoomsByType
                .computeIfAbsent(roomType, k -> new HashSet<>())
                .add(roomId);

        int updatedCount = inventory.getAvailableCount(roomType) - 1;
        inventory.updateAvailability(roomType, updatedCount);

        System.out.println("Booking confirmed for Guest: "
                + reservation.getGuestName()
                + ", Room ID: " + roomId);
    }

    private String generateRoomId(String roomType) {
        String prefix = roomType.replace(" Room", "");
        int count = assignedRoomsByType
                .getOrDefault(roomType, new HashSet<>())
                .size() + 1;
        String candidate = prefix + "-" + count;
        while (allocatedRoomIds.contains(candidate)) {
            count++;
            candidate = prefix + "-" + count;
        }
        return candidate;
    }
}