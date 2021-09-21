package com.oim.events;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class AddEvent extends AppCompatActivity {

    // Bloc déclaratif
    private EventObject event;
    // Ce booléen va me permettre de savoir si je suis en mode ajout ou édition
    private boolean emptyExtra2;
    private EditText eEventName;
    private EditText eDate;
    private EditText eEventPlace;
    private EditText eInternObject;
    private Button eButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        // Je récupère mes vues
        eEventName = findViewById(R.id.eventName);
        eDate = findViewById(R.id.date);
        eEventPlace = findViewById(R.id.eventPlace);
        eInternObject = findViewById(R.id.internObject);
        eButton2 = findViewById(R.id.button2);

        // Je récupère mon intent
        Intent intent = getIntent();

        // Je vais tester l'affectation de mon objet Capital avec l'extra récupéré.
        // J'utilise un bloc Try/catch pour gérer le cas où je n'ai pas d'extra.
        try {
            // Je lance donc mon affectation
            event = (EventObject)intent.getSerializableExtra("cityExtra");
            // Dans ce cas, j'ai bien un extra, je modifie mon booléen --> Mode Edition!
            emptyExtra2 = false;
            // Et j'alimente mes champs Text de mes EditText avec le contenu de mon extra (objet Capital)
            eEventName.setText(event.getEventName());
            eDate.setText(event.getDate());
            eEventPlace.setText(event.getEventPlace());
            eInternObject.setText(event.getInternObject());
            // Je modifie le texte du bouton pour signaler à l'utilisateur qu'on est sur un update, et pas un ajout.
            eButton2.setText("UPDATE");
        } catch(NullPointerException error){
            // --> L'affectation a généré une erreur NullPointerException
            event = new EventObject();
            // Et jé définis mon booléen --> pas d'extra --> mode ajout
            emptyExtra2 = true;
        }

    }

    // Fonction appelée lors d'un clic sur mon bouton
    public void addEventClicked(View view) {

        // A ce moment je récupère les champs d'édition de mes EditText.
        // Je rajoute toString() à la fin car getText sur un EditText me renvoie
        // un objet de type CharSequence au lieu de String
        String eventName = eEventName.getText().toString();
        Date date = eDate.getText().toString();
        String eventPlace = eEventPlace.getText().toString();
        String internObject = eInternObject.getText().toString();


        // Je teste si ces champs sont vides
        if(!eventName.isEmpty() && !date.isEmpty() && !eventPlace.isEmpty() && !internObject.isEmpty()) {
            // Si les champs ne sont pas vides
            // Je définis si je suis en mode ajout ou edition
            if(emptyExtra2) {
                // --> emptyExtra = true --> Mode ajout
                // Donc appel de la fonction Ajout
                EventObject.addEvent(new EventObject(eventName, date, eventPlace, internObject));
            } else {
                // --> emptyExtra = false --> mode édition
                // Donc appel de la fonction update
                EventObject.updateEvent(new EventObject(event.getEvent_id(), eventName, date, eventPlace, internObject));
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

