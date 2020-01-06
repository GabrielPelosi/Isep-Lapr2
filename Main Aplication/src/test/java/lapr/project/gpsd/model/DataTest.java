/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Utilizador
 */
public class DataTest {
    
    public DataTest() {
    }

    /**
     * Test of getAno method, of class Data.
     */
    @Test
    public void testGetAno() {
        System.out.println("getAno");
        Data instance = new Data(2019,9,22);
        int expResult = 2019;
        int result = instance.getAno();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMes method, of class Data.
     */
    @Test
    public void testGetMes() {
        System.out.println("getMes");
        Data instance = new Data(2019,9,22);
        int expResult = 9;
        int result = instance.getMes();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDia method, of class Data.
     */
    @Test
    public void testGetDia() {
        System.out.println("getDia");
        Data instance =  new Data(2019,9,22);
        int expResult = 22;
        int result = instance.getDia();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDiaMes method, of class Data.
     */
    @Test
    public void testGetDiaMes() {
        System.out.println("getDiaMes");
        Data instance = new Data(2019,9,22);
        int expResult = 30;
        int result = instance.getDiaMes();
        assertEquals(expResult, result);
    }

    /**
     * Test of setData method, of class Data.
     */
    @Test
    public void testSetData() {
        System.out.println("setData");
        int ano = 2000;
        int mes = 2;
        int dia = 24;
        Data instance = new Data();
        instance.setData(ano, mes, dia);
    }

    /**
     * Test of toString method, of class Data.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Data instance= new Data(2019,9,22);
        Data d1= new Data(2019,9,22);
        String expResult = d1.toString();
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of toAnoMesDiaString method, of class Data.
     */
    @Test
    public void testToAnoMesDiaString() {
        System.out.println("toAnoMesDiaString");
        Data instance= new Data(2019,9,22);
        Data d1= new Data(2019,9,22);
        String expResult = d1.toAnoMesDiaString();
        String result = instance.toAnoMesDiaString();
        assertEquals(expResult, result);
    }

    /**
     * Test of determinarDiaDaSemana method, of class Data.
     */
    @Test
    public void testDeterminarDiaDaSemana() {
        System.out.println("determinarDiaDaSemana");
        Data instance= new Data(2019,9,22);
        Data d1= new Data(2019,9,22);
        String expResult = d1.determinarDiaDaSemana();
        String result = instance.determinarDiaDaSemana();
        assertEquals(expResult, result);
    }

    /**
     * Test of isMaior method, of class Data.
     */
    @Test
    public void testIsMaior() {
        System.out.println("isMaior");
        Data instance= new Data(2019,9,22);
        Data outraData= new Data(2019,5,22);
        boolean expResult = true;
        boolean result = instance.isMaior(outraData);
        assertEquals(expResult, result);
    }

    /**
     * Test of same method, of class Data.
     */
    @Test
    public void testSame() {
        System.out.println("same");
        Data instance= new Data(2019,9,22);
        Data outraData= new Data(2019,9,22);
        boolean expResult = true;
        boolean result = instance.same(outraData);
        assertEquals(expResult, result);
    }

    /**
     * Test of calcularDiferenca method, of class Data.
     */
    @Test
    public void testCalcularDiferenca_Data() {
        System.out.println("calcularDiferenca");
        Data instance= new Data(2019,9,22);
        Data outraData= new Data(2019,9,21);
        int expResult = 1;
        int result = instance.calcularDiferenca(outraData);
        assertEquals(expResult, result);
    }

    /**
     * Test of calcularDiferenca method, of class Data.
     */
    @Test
    public void testCalcularDiferenca_3args() {
        System.out.println("calcularDiferenca");
        int ano = 2019;
        int mes = 9;
        int dia = 21;
        Data instance =new Data(2019,9,22);
        int expResult = 1;
        int result = instance.calcularDiferenca(ano, mes, dia);
        assertEquals(expResult, result);
    }

    /**
     * Test of isAnoBissexto method, of class Data.
     */
    @Test
    public void testIsAnoBissexto() {
        System.out.println("isAnoBissexto");
        int ano = 2019;
        boolean expResult = false;
        boolean result = Data.isAnoBissexto(ano);
        assertEquals(expResult, result);
    }
    
}
