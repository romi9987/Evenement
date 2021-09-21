package com.oim.events;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddIntern extends AppCompatActivity {

    // Bloc déclaratif
    private InternObject intern;
    // Ce booléen va me permettre de savoir si je suis en mode ajout ou édition
    private boolean emptyExtra;
    private EditText eInternName;
    private EditText eInternFirstName;
    private Button eButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_intern);
        // Je récupère mes vues
        eInternName = findViewById(R.id.internName);
        eInternFirstName = findViewById(R.id.internFirstName);
        eButton = findViewById(R.id.button);

        // Je récupère mon intent
        Intent intent = getIntent();

        // Je vais tester l'affectation de mon objet Capital avec l'extra récupéré.
        // J'utilise un bloc Try/catch pour gérer le cas où je n'ai pas d'extra.
        try {
            // Je lance donc mon affectation
            intern = (InternObject)intent.getSerializableExtra("internExtra");
            // Dans ce cas, j'ai bien un extra, je modifie mon booléen --> Mode Edition!
            emptyExtra = false;
            // Et j'alimente mes champs Text de mes EditText avec le contenu de mon extra (objet Capital)
            eInternName.setText(intern.getInternName());
            eInternFirstName.setText(intern.getInternFirstName());
            // Je modifie le texte du bouton pour signaler à l'utilisateur qu'on est sur un update, et pas un ajout.
            eButton.setText("UPDATE");
        } catch(NullPointerException error){
            // --> L'affectation a généré une erreur NullPointerException
            intern = new InternObject();
            // Et jé définis mon booléen --> pas d'extra --> mode ajout
            emptyExtra = true;
        }

    }

    // Fonction appelée lors d'un clic sur mon bouton
    public void addInternClicked(View view) {

        // A ce moment je récupère les champs d'édition de mes EditText.
        // Je rajoute toString() à la fin car getText sur un EditText me renvoie
        // un objet de type CharSequence au lieu de String
        String internName = eInternName.getText().toString();
        String internFirstName = eInternFirstName.getText().toString();

        // Je teste si ces champs sont vides
        if(!internName.isEmpty() && !internFirstName.isEmpty()) {
            // Si les champs ne sont pas vides
            // Je définis si je suis en mode ajout ou edition
            if(emptyExtra) {
                // --> emptyExtra = true --> Mode ajout
                // Donc appel de la fonction Ajout
                InternObject.addIntern(new InternObject(internFirstName, internName));
            } else {
                // --> emptyExtra = false --> mode édition
                // Donc appel de la fonction update
                InternObject.updateIntern(new InternObject(intern.getId(), internFirstName, internName));
            }
            // Puis je quitte mon activité
            finish();
            // Sinon, si champs de saisie vides
        } else {
            // J'appelle mon dialogFragment
            FragmentManager myFragmentManager = getFragmentManager();
            MyDialogFragment dFragment = new MyDialogFragment();
            dFragment.show(myFragmentManager, null);
        }
    }
}
