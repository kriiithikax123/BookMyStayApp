public class UseCase4RoomSearch {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        // Create room objects for details
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom  = new SuiteRoom();

        // Create centralized inventory
        RoomInventory inventory = new RoomInventory();

        // Create search service
        RoomSearchService searchService = new RoomSearchService();

        // Display search results
        System.out.println("Room Search");
        System.out.println();

        // Perform read-only search
        searchService.searchAvailableRooms(
            inventory,
            singleRoom,
            doubleRoom,
            suiteRoom
        );
    }
}
