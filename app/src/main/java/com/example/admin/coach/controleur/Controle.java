package com.example.admin.coach.controleur;

import com.example.admin.coach.modele.Profil;
import com.example.admin.coach.vue.MainActivity;

/**
 * Created by admin on 14/11/2016.
 */

public final class Controle { // On rend la classe finale pour empêcher tout héritage

    // === Proprietes
    private static Controle instance = null;
    private static Profil profil ;

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
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 0 pour une femme, 1 pour un homme
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe) {
        this.profil = new Profil (poids, taille, age, sexe) ;
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
