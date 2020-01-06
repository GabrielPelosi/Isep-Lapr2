package lapr.project.gpsd.model;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class PostalAddressTest {



//    /**
//     * Test of hashCode method, of class PostalAddress.
//     */
//    @Test
//    public void testHashCode() {
//        System.out.println("hashCode");
//        PostalAddress instance = new PostalAddress("rua das virtudes", "3456-234", "porto");
//        int expResult = 1472399331;
//        int result = instance.hashCode();
//        assertEquals(expResult, result);
//    }

    /**
     * Test of equals method, of class PostalAddress.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        PostalAddress instance = new PostalAddress("rua das virtudes", "3456-234", "porto");
        Object o = instance;
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }

}
