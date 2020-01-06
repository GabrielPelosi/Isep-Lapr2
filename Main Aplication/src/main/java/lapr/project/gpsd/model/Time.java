/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import java.io.Serializable;

/**
 *
 * @author rickropes
 */
public class Time implements Serializable {
//aaaaa
    String tempo;
    int hh;
    int mm;

    public Time(String tempo) {
        String[] temp = tempo.split(":");
        if (temp.length >= 1 || temp.length == 2) {
            hh = Integer.parseInt(temp[0].trim());
            if (temp.length == 2) {
                mm = Integer.parseInt(temp[1].trim());
            } else {
                mm = 0;
            }
        }
        converter();
    }

    public Time(int hh, int mm) {
        this.hh = hh;
        this.mm = mm;
        converter();
    }

    public int getHh() {
        return hh;
    }

    public int getMm() {
        return mm;
    }

    /**
     * Converte o tempo em segundos e devolve o valor.
     *
     * @return Os segundos.
     */
    private int converterToSec() {
        return (hh * 3600) + (mm * 60);
    }

    /**
     * Dá valores a hh, mm e ss e coloca-os com valores correctos.
     */
    private void converter() {
        if(!(hh == 24 && mm == 00)){
            int num = 0;
            num += converterToSec();
            hh = (int) num / 3600 % 24;
            num = num % 3600;
            mm = (int) num / 60;
        }
    }

    /**
     * Devolve true se o tempo for maior do que o tempo recebida por parâmetro. Se o tempo for menor ou igual ao tempo recebida por parâmetro, devolve false.
     *
     * @param outraTime o outro tempo com a qual se compara o tempo
     * @return true se o tempo for maior do que o tempo recebida por parâmetro, caso contrário devolve false
     */
    public boolean isMaior(Time tm) {
        return converterToSec() > tm.converterToSec();
    }

    /**
     * Devolve true se o tempo for maior do que o tempo recebida por parâmetro. Se o tempo for menor ou igual ao tempo recebida por parâmetro, devolve false.
     *
     * @param hh as horas
     * @param mm os minutos
     * @param ss os segundos
     * @return true or false.
     */
    public boolean isMaior(int hh, int mm) {
        return converterToSec() > (hh * 3600) + (mm * 60);
    }

    public int diferencaS(Time tm) {
        return Math.abs(converterToSec() - tm.converterToSec());
    }

    public Time diferencaT(Time tm) {
        int min = diferencaS(tm);
        Time t = new Time(0, min);
        return t;

    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hh, mm);
    }

    /**
     * Retorna o tempo no formato HH:MM:SS AM (ou PM).
     *
     * @return String do tempo em formato HH:MM:SS AM (ou PM).
     */
    public String toStringPM() {
        if (hh == 12) {
            return String.format("%02d:%02d PM", hh, mm);
        } else if (hh == 00) {
            return String.format("%02d:%02d AM", hh + 12, mm);
        } else if (hh < 12) {
            return String.format("%02d:%02d AM", hh, mm);
        } else {
            return String.format("%02d:%02d PM", hh - 12, mm);
        }
    }
}