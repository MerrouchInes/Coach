package com.example.admin.coach.outils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 07/12/2016.
 */

public abstract class MesOutils {

    /**
     * --- Méthode convertStringToDate
     * Convertit une chaîne de caractères en une date
     * @param uneDate
     * @return
     */
    public static Date convertStringToDate(String uneDate) {
        String expectedPattern = "EEE MMM dd hh:mm:ss 'GMT' yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
        try {
            Date date = formatter.parse(uneDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     *  --- Méthode convertDateToString
     *  Convertit une date en une chaîne de caractères
     * @param uneDate
     * @return
     */
    public static String convertDateToString(Date uneDate){
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        return date.format(uneDate);
    }

}
