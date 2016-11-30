package com.example.admin.coach.modele;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.coach.outils.MySQLiteOpenHelper;

/**
 * Created by admin on 30/11/2016.
 */

public class AccessLocal {

    // === Propriétés
    private String nomBase = "bdCoach.sqlite" ;
    private Integer versionBase = 1 ;
    private MySQLiteOpenHelper accesBD ;
    private SQLiteDatabase bd ;
    private String req ;

    /**
     * === CONSTRUCTEUR DE LA CLASSE ACCESSLOCAL
     * @param contexte
     */
    public AccessLocal(Context contexte) {
        this.accesBD = new MySQLiteOpenHelper(contexte, nomBase, versionBase) ;
    }

    /**
     * --- Méthode ajout
     * Ajout d'un profil dans la table Profil
     * @param profil
     */
    public void ajout(Profil profil) {
        this.bd = accesBD.getWritableDatabase() ; // Accès en écriture sur la BD
        this.req += " (\"" + profil.getDateMesure()
                    + "\"," + profil.getPoids()
                    + "," + profil.getTaille()
                    + "," + profil.getAge()
                    + "," + profil.getSexe() + ")"; // Requête d'insertion
        bd.execSQL(req); // Exécution de la requête
    }

    /**
     * --- Méthode recupDernier
     * @return
     */
    public void recupDernier() {
        Profil profil = null ;
        this.bd = accesBD.getReadableDatabase() ; // Accès en lecture seule sur la BD
        String req = " (\"" + profil.getDateMesure()
                + "\"," + profil.getPoids()
                + "," + profil.getTaille()
                + "," + profil.getAge()
                + "," + profil.getSexe() + ")";
    }
}
