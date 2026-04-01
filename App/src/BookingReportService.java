/**
 * ============================================================
 * CLASS - BookingReportService
 * ============================================================
 *
 * Use Case 8: Booking History & Reporting
 *
 * Description:
 * This class generates reports
 * from booking history data.
 *
 * Reporting logic is separated
 * from data storage.
 *
 * @version 8.0
 */
public class BookingReportService {

    /**
     * Displays a summary report
     * of all confirmed bookings.
     *
     * @param history booking history
     */
    public void generateReport(BookingHistory history) {
        System.out.println("Booking History Report");

        for (Reservation reservation : history.getConfirmedReservations()) {
            // Strip " Room" suffix for clean display: "Single Room" -> "Single"
            String roomType = reservation.getRoomType().replace(" Room", "");
            System.out.println("Guest: " + reservation.getGuestName()
                    + ", Room Type: " + roomType);
        }
    }
}