package lapr.project.gpsd.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class RegisterAreaTest {

    /**
     * Test of novaArea method, of class RegisterArea.
     */
    @Test
    public void testNovaArea() {
        System.out.println("novaArea");
        String nome = "porto";
        PostalCode pc = new PostalCode("4000-16");
        int radius = 23;
        double valor = 2.5;
        String es = "XPTO";
        Set<Area> list = new HashSet<>();
        RegisterArea instance = new RegisterArea(list);
        Area expResult = new Area(nome, pc, radius, valor, es);
        Area result = instance.novaArea(nome, pc, radius, valor, es);
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of validaArea method, of class RegisterArea.
     */
    @Test
    public void testValidaArea() {
        System.out.println("validaArea");
        String nome = "porto";
        PostalCode pc = new PostalCode("4000-16");
        int radius = 23;
        double valor = 2.5;
        String es = "XPTO";
        Area oArea = new Area(nome, pc, radius, valor, es);
        Set<Area> list = new HashSet<>();
        RegisterArea instance = new RegisterArea(list);
        boolean expResult = true;
        boolean result = instance.validaArea(oArea);
        assertEquals(expResult, result);
    }

    /**
     * Test of registaArea method, of class RegisterArea.
     */
    @Test
    public void testRegistaArea() {
        System.out.println("registaArea");
        String nome = "porto";
        PostalCode pc = new PostalCode("4000-16");
        int radius = 23;
        double valor = 2.5;
        String es = "XPTO";
        Area oArea = new Area(nome, pc, radius, valor, es);
        Set<Area> list = new HashSet<>();
        RegisterArea instance = new RegisterArea(list);
        boolean expResult = true;
        boolean result = instance.registaArea(oArea);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAreas method, of class RegisterArea.
     */
    @Test
    public void testGetAreas() {
        System.out.println("getAreas");
        Set<Area> list = new HashSet<>();
        RegisterArea instance = new RegisterArea(list);
        String nome = "porto";
        PostalCode pc = new PostalCode("4000-16");
        int radius = 23;
        double valor = 2.5;
        String es = "XPTO";
        Area oArea = new Area(nome, pc, radius, valor, es);
        List<Area> expResult = new ArrayList<>();
        //expResult.add(oArea);
        List<Area> result = instance.getAreas();
        assertEquals(expResult, result);
    }

    /**
     * Test of nameExists method, of class RegisterArea.
     */
    @Test
    public void testNameExists() {
        System.out.println("nameExists");
        String name = "porto";
        Set<Area> list = new HashSet<>();
        RegisterArea instance = new RegisterArea(list);
        boolean expResult = false;
        boolean result = instance.nameExists(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAreaByName method, of class RegisterArea.
     */
    @Test
    public void testGetAreaByName() {
        System.out.println("getAreaByName");
        String name = "porto";
        Set<Area> list = new HashSet<>();
        RegisterArea instance = new RegisterArea(list);
        PostalCode pc = new PostalCode("4000-16");
        Area expResult = null;
        Area result = instance.getAreaByName(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of getGeographicAreasName method, of class RegisterArea.
     */
    @Test
    public void testGetGeographicAreasName() {
        System.out.println("getGeographicAreasName");
        Set<Area> list = new HashSet<>();
        RegisterArea instance = new RegisterArea(list);
        List<Area> expResult = new ArrayList<>();
        String nome = "porto";
        PostalCode pc = new PostalCode("4000-16");
        int radius = 23;
        double valor = 2.5;
        String es = "XPTO";
        Area oArea = new Area(nome, pc, radius, valor, es);
        //expResult.add(oArea);
        List<String> result = instance.getGeographicAreasName();
        assertEquals(expResult, result);
    }
    
}
