package com.example.admin.coach.vue;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.coach.R;
import com.example.admin.coach.controleur.Controle;
import com.example.admin.coach.modele.Profil ;

public class MainActivity extends AppCompatActivity {

    // === Propriétés
    private EditText txtPoids ;
    private EditText txtTaille ;
    private EditText txtAge ;
    private RadioButton rdHomme ;
    private TextView lblImg ;
    private ImageView imgSmiley ;

    private Controle controle ;

    // === Méthodes

    /**
     * --- Procédure ecouteCalcul()
     * Gestion de l'événement « clic sur "Calculer" »
     */
    private void ecouteCalcul() {
        ((Button) findViewById(R.id.btnCalc)).setOnClickListener(new Button.OnClickListener() { // findViewById récupère objets graphiques (ici, le bouton)
            /**
             * --- Procédure onClick
             * @param v
             */
            public void onClick(View v) {
               // Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
                Integer poids = 0 ;
                Integer taille = 0 ;
                Integer age = 0 ;

                try {
                    poids = Integer.parseInt(txtPoids.getText().toString()) ;
                    taille = Integer.parseInt(txtTaille.getText().toString()) ;
                    age = Integer.parseInt(txtAge.getText().toString()) ;
                } catch(Exception e) { }

                Integer sexe = 0 ; // Par défaut, femme = 0
                if (rdHomme.isChecked()) { // Si "Homme" selectionné, sexe = 1
                    sexe = 1 ;
                }

                if (poids == 0 || taille == 0 || age == 0) { // Si poids OU taille OU age = 0
                    Toast.makeText(MainActivity.this, "Veuillez entrer tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    afficheResult(poids, taille, age, sexe);
                }
            }

            });
    }

    /**
     * --- METHODE afficheResult
     * Affiche une image et modifie le message (couleur+texte) en fct de l'img
     * Formate l'affichage de l'img pour qu'il n'y ait qu'un chiffre après la virgule
     * @param poids (kg)
     * @param taille (cm)
     * @param age
     * @param sexe
     */
    private void afficheResult(Integer poids, Integer taille, Integer age,  Integer sexe) {
        controle.creerProfil(poids, taille, age, sexe , MainActivity.this ); // this fait référence à contexte
        String message = controle.getMessage() ;
        float img = controle.getImg() ;

        if (sexe == 0) { // Femme
            if (img < 15) { // Maigre
                imgSmiley.setImageResource(R.drawable.maigre); // Affiche l'image "Maigre"
                lblImg.setTextColor(Color.RED); // Message en rouge

            } else {
                if (img > 30) { // Grosse
                    imgSmiley.setImageResource(R.drawable.graisse); // Affiche l'image "graisse"
                    lblImg.setTextColor(Color.RED); // Message en rouge
                } else {
                    imgSmiley.setImageResource(R.drawable.normal);
                    lblImg.setTextColor(Color.GREEN); // Message en vert
                }
            }
        }
        if(sexe == 1) { // Homme
            if(img < 10){ // Maigre
                imgSmiley.setImageResource(R.drawable.maigre); // Affiche l'image "Maigre"
                lblImg.setTextColor(Color.RED); // Message en rouge
            } else {
                if (img > 25) { // Gros
                    imgSmiley.setImageResource(R.drawable.graisse); // Affiche l'image "graisse"
                    lblImg.setTextColor(Color.RED); // Message en rouge
                } else {
                    imgSmiley.setImageResource(R.drawable.normal); // Sinon, affiche l'image "normal"
                    lblImg.setTextColor(Color.GREEN); // Message en vert
                }
            }

        }
        Log.d("test","**********"+img+"="+message) ; // Affiche valeur IMG + message suivant le résultat
        lblImg.setText(String.format("%.01f", img)+ " : " + message) ; // Un seul chiffre après la virgule

    }

    /**
     * --- Procédure init()
     * Fait le lien entre les variables et les objets graphiques
     */
    private void init() {
        txtPoids = (EditText) findViewById(R.id.txtPoids) ;
        txtTaille = (EditText) findViewById(R.id.txtTaille) ;
        txtAge = (EditText) findViewById(R.id.txtAge) ;
        rdHomme = (RadioButton) findViewById(R.id.rdHomme) ;
        lblImg = (TextView) findViewById(R.id.lblImg) ;
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley) ;
        controle = controle.getInstance(this); /* Création du contrôleur. */
                                           /* Pas de new car constructeur privé et
                                              utilisation d'un singleton */
        this.ecouteCalcul() ; // Appel de la procédure ecouteCalcul
        // this.recupProfil();
    }

    @Override // Reperage des methodes abstraites

    /**
     * --- Methode abstraite onCreate
     * Se déclenche lors de la création de l'Activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Appel de la méthode onCreate de la classe-mère (prep. Activity)
        setContentView(R.layout.activity_main); /* Appel de la méthode setcontentView de la classe-mère */
                                                /* R -> dossier res */
        init(); // Appel de la procédure init


    }

    /**
     * --- Procédure recupProfil
     * Récupère le contenu du profil
     */
    private void recupProfil() {
        if (controle.getTaille() != null) {
            txtTaille.setText("" + controle.getTaille());
            txtPoids.setText("" + controle.getPoids()) ;
            txtAge.setText("" + controle.getAge()) ;
            rdHomme.setText("" + controle.getSexe()) ;
            findViewById(R.id.btnCalc).performClick() ;
        }

    }

}
