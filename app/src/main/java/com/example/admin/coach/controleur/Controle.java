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
    public final static Controle getInstance(Context contexte) {
        if (Controle.instance == null) { // On met == car on fait une comparaison
            Controle.instance = new Controle() ;
            recupSerialize(contexte); // Au moment du démarrage de l'app, on appelle ce qui a été sérialisé
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
     * Récupère ce qui a été sérialisé
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

    /**
     * --- GETTER getTaille
     * @return "taille" de la classe Profil
     */
    public Integer getTaille() {
        if (profil == null) {
        // Si profil null, on ne retourne rien
            return null;
        } else {
            return profil.getTaille() ;
        }
    }

    /**
     * --- GETTER getPoids
     * @return "poids" de la classe Profil
     */
    public Integer getPoids() {
        if (profil == null) {
            // Si profil null, on ne retourne rien
            return null;
        } else {
            return profil.getPoids() ;
        }
    }

    /**
     * --- GETTER getAge
     * @return "age" de la classe Profil
     */
    public Integer getAge() {
        if (profil == null) {
            // Si profil null, on ne retourne rien
            return null;
        } else {
            return profil.getAge() ;
        }
    }

    /**
     * --- GETTER getSexe
     * @return "sexe" de la classe Profil
     */
    public Integer getSexe() {
        if (profil == null) {
            // Si profil null, on ne retourne rien
            return null;
        } else {
            return profil.getSexe() ;
        }
    }


}
