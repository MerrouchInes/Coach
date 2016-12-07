package com.example.admin.coach.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.coach.outils.MySQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
     * --- Récupération de la date du jour

    Date laDate = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String laDateDuJour = dateFormat.format(laDate);*/

    /**
     * --- Méthode recupDernier
     * @return un objet de type Profil
     */
    public Profil recupDernier() {
        Profil profil = null ;
        this.bd = accesBD.getReadableDatabase() ; // Accès en lecture seule sur la BD
        String req = "SELECT * FROM profil ORDER BY datemesure DESC" ;
        // Curseur qui permet d'accéder au résultat de la requête
        Cursor curseur = bd.rawQuery(req, null)  ;
        if(curseur.moveToFirst()) { // S'il y a au moins une ligne dans le curseur, valorisation
            Date dateMesure = new Date() ;
            Integer poids = curseur.getInt(1);
            Integer taille = curseur.getInt(2);
            Integer age = curseur.getInt(3);
            Integer sexe = curseur.getInt(4) ;

            profil = new Profil(poids, taille, age, sexe, dateMesure);
        }
        curseur.close(); // Fermeture du curseur
        return profil;
    }
}
