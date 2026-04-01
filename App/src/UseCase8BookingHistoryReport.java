/**
 * Use Case 8: Booking History & Reporting
 *
 * Description:
 * This class demonstrates how
 * confirmed bookings are stored
 * and reported.
 *
 * The system maintains an ordered
 * audit trail of reservations.
 *
 * @version 8.0
 */
public class UseCase8BookingHistoryReport {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        // ---------------------------------------------------------
        // 1. Create booking history and add confirmed reservations
        //    in insertion order (FIFO - matches UC6 queue order)
        // ---------------------------------------------------------
        BookingHistory history = new BookingHistory();
        history.addReservation(new Reservation("Abhi",     "Single Room"));
        history.addReservation(new Reservation("Subha",    "Double Room"));
        history.addReservation(new Reservation("Vanmathi", "Suite Room"));

        // ---------------------------------------------------------
        // 2. Generate report via the reporting service
        //    (reporting logic is separate from data storage)
        // ---------------------------------------------------------
        BookingReportService reportService = new BookingReportService();

        System.out.println("Booking History and Reporting");
        System.out.println();
        reportService.generateReport(history);
    }
}
