package com.example.admin.coach.modele;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by admin on 14/11/2016.
 */

public class Profil implements Serializable {
// L'implémentation de Serializable rend la classe sérialisable

    // === Proprietes

    // CONSTANTES
    private static final Integer minFemme = 15; // Maigre si en dessous de 15%
    private static final Integer maxFemme = 30; // Gros si au dessus de 30%
    private static final Integer minHomme = 10; // Maigre si en dessous de 10%
    private static final Integer maxHomme = 25; // Gros si au dessus de 25%

    // VARIABLES
    private Integer poids;
    private Integer taille; // en cm
    private Integer age;
    private Integer sexe; // S = 0 pour une femme, et 1 pour un homme
    private float img;
    private String message;


    /**
     * === CONSTRUCTEUR DE LA CLASSE PROFIL
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    public Profil(int poids,int taille, int age,int sexe ) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;



        calculIMG();    /* Appel de ces méthodes à la fin du constructeur */
        resultIMG();    /* pour que la valorisation se fasse dès le début */
    }


    // === Méthodes

    // --- PROCEURE calculIMG : Calcule l'IMG
    private void calculIMG() {
        // Formule:(1,2 * Poids/Taille²) + (0,23 * age) - (10,83 * S) - 5,4
        float laTaille = ((float)this.taille)/100 ;
        img = (float)((1.2 * poids/(laTaille*laTaille)) + (0.23 * age) - (10.83 * sexe) - 5.4);
       // Log.d("img","****************pods="+poids+" taille="+taille+" age="+age+" sexe="+sexe);

    }

    // --- PROCEDURE resultIMG
        /* Valorise message en fonction de l'IMG calculé par la fonction */
    private void resultIMG() {
        if (sexe == 0) { // Si la personne est une femme ...
            if (img <= minFemme) { // img inférieur à la moyenne
                message = "Trop faible";
            } else if (img <= maxFemme) { // img normal
                message = "Normal";
            } else {
                message = "Trop élevé"; // img supérieur à la moyenne
            }
        }

        else { // ... sinon, la personne est un homme
            if (img <= minHomme) { // img inférieur à la moyenne
                message = "Trop faible";
            } else if (img <= maxHomme) { // img normal
                message = "Normal";
            } else {
                message = "Trop élevé"; // img supérieur à la moyenne
            }
        }
    }

    // --- GETTERS

    /**
     * getPoids
     * @return le poids en kg de la personne
     */
    public int getPoids() {
        return poids;
    }

    /**
     * getTaille
     * @return la taille en cm de la personne
     */
    public int getTaille() {
        return taille;
    }

    /**
     * getAge
     * @return l'âge de la personne
     */
    public int getAge() {
        return age;
    }

    /**
     * getSexe
     * @return le sexe de la personne (0 = F, 1 = M)
     */
    public int getSexe() {
        return sexe;
    }

    /**
     * getImg
     * @return le résultat du calcul de l'img
     */
    public float getImg() {
        return img;
    }

    /**
     * getMessage
     * @return le message correspondant à la valeur de l'img
     */
    public String getMessage() {
        return message;
    }


}
