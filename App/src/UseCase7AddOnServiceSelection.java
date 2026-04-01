/**
 * Use Case 7: Add-On Service Selection
 *
 * Description:
 * This class demonstrates how optional
 * services can be attached to a confirmed
 * booking.
 *
 * Services are added after room allocation
 * and do not affect inventory.
 *
 * @version 7.0
 */
public class UseCase7AddOnServiceSelection {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        // ---------------------------------------------------------
        // 1. Confirmed reservation ID from UC6
        // ---------------------------------------------------------
        String reservationId = "Single-1";

        // ---------------------------------------------------------
        // 2. Create add-on services
        // ---------------------------------------------------------
        AddOnService breakfast     = new AddOnService("Breakfast",      500.0);
        AddOnService spa           = new AddOnService("Spa",            750.0);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 250.0);

        // ---------------------------------------------------------
        // 3. Attach services to the reservation
        // ---------------------------------------------------------
        AddOnServiceManager serviceManager = new AddOnServiceManager();
        serviceManager.addService(reservationId, breakfast);
        serviceManager.addService(reservationId, spa);
        serviceManager.addService(reservationId, airportPickup);

        // ---------------------------------------------------------
        // 4. Display total add-on cost
        // ---------------------------------------------------------
        double totalCost = serviceManager.calculateTotalServiceCost(reservationId);

        System.out.println("Add-On Service Selection");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}