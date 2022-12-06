package Controller;

import Database.Controller;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerTest {
    @Test
    void totalProfitAfKontigent() {
        Controller controller = new Controller();
        controller.addMember("nichlas", LocalDate.of(2012,10,10), null, null, 0, true, true, true);
        controller.addMember("Amir", LocalDate.of(1998,10,10), null, null, 0, true, true, true);
        controller.addMember("Dennis", LocalDate.of(1950,10,10), null, null, 0, true, true, true);
        controller.addMember("Samim", LocalDate.of(2012,10,10), null, null, 0, false, true, true);
        int exspectedTotalFee = 4300;
        int totalFee = controller.getTotalProfit();
        assertEquals(exspectedTotalFee, totalFee);
    }
    @Test
    void totalProfitAfKontigentTOM() {
        Controller controller = new Controller();
        int exspectedTotalFee = 0;
        int totalFee = controller.getTotalProfit();
        assertEquals(exspectedTotalFee, totalFee);
    }
}

