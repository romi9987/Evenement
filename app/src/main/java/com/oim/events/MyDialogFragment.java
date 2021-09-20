package com.oim.events;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.DialogFragment;
import android.widget.Toast;

public class MyDialogFragment extends DialogFragment {
    // Notre DialogFragment doit Surcharger la fonction onCreateDialog de la classe Dialog
    // (Penser à l'objet Bundle en paramètre)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Pour créer notre AlertDialog, on a besoin d'utiliser son Builder (en passant en paramètre l'activité qui
        // appelle le dialogue.
        // A ce stade on ne connait pas l'activité qui appelle le dialog, donc on utilise getActivity
        AlertDialog.Builder dBuilder = new AlertDialog.Builder(getActivity());

        // Facultatif : Je modifie le layout par défaut de l'alertDialog avec un layout custom (voir fragment_my_dialog.xml)
        dBuilder.setView(R.layout.fragment_my_dialog);

        // On définit le bouton de validation de notre alertDialog grâce à setPositiveButton
        // En paramètre, on envoie le texte du bouton, ainsi que le listener de notre AlertDialog. Ce listener est implémenté
        // Grace à l'interface (AU SENS JAVA et pas au sens graphique) DialogInterface
        dBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            // on définit ici les actions en cas de clic (onClick)
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(), "Invalid Form", Toast.LENGTH_SHORT).show();
            }
        });

        // Et on renvoie la création de notre AlertDialog (d'où l'appel à la fonction create() dans le return.
        return dBuilder.create();
    }
}
