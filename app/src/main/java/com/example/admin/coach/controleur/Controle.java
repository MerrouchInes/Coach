package com.example.admin.coach.controleur;

import android.content.Context;

import com.example.admin.coach.modele.Profil;
import com.example.admin.coach.vue.MainActivity;
import com.example.admin.coach.outils.Serializer ;

/**
 * Created by admin on 14/11/2016.
 */

public final class Controle { // On rend la classe finale pour empêcher tout héritage

    // === Proprietes
    private static Controle instance = null;
    private static Profil profil ;
    private static String nomFic = "saveprofil" ;

    // === CONSTRUCTEUR DE LA CLASSE CONTROLE
    private Controle() {
        super() ;
    }

    /**
     * --- GETTER getInstance()
     * @return une instance de la classe Controle
     */
    public final static Controle getInstance() {
        if (Controle.instance == null) { // On met == car on fait une comparaison
            Controle.instance = new Controle() ;
        }
        return Controle.instance ;
    }

    /**
     * --- METHODE creerProfil
     * Permet de gerer un profil
     * @param poids en kg
     * @param taille en cm
     * @param age
     * @param sexe 0 pour une femme, 1 pour un homme
     * @param contexte nécessaire pour la sérialisation
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context contexte) {
        this.profil = new Profil (poids, taille, age, sexe) ;
        Serializer.serialize(nomFic, profil, contexte) ;
    }

    /**
     * PROCEDURE recupSerialize
     * @param contexte
     */
    private static void recupSerialize(Context contexte) {
        profil = (Profil)Serializer.deSerialize(nomFic, contexte) ;
    }

     /**
     * --- GETTER getImg
     * @return l'img récupérée dans le profil
     */
    public float getImg() {
        return profil.getImg ();
    }


    /**
     * --- GETTER getMessage()
     * @return le message récupéré dans Profil
     */
    public String getMessage(){
        return profil.getMessage() ;
    }


}
