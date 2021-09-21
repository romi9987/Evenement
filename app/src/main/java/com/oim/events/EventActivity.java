package com.oim.events;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public class EventActivity extends AppCompatActivity {

    // Bloc déclaratif
    private RecyclerView recyclerView;
    private List<EventObject> eventsList;
    private MyAdapter myAdapter;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res = getResources();
        // Je crée une instance de mon Helper.
        EventObject.initHelper(this);
        recyclerView = findViewById(R.id.events);

        int displayMode = res.getConfiguration().orientation;

        // Je teste le nombre d'enregistrements présents dans ma base de données.
        // Si aucun enregistrement --> j'ajoute des capitales à ma BDD
        if(EventObject.getEventsList().size() == 0) {
            ajouterEvents();
        }

        // Je définis l'adaptateur de mon RecyclerView
        // Vu que ce bloc est répété trois fois, il pourrait très bien aller dans une fonction
        eventsList = EventObject.getEventsList();
        myAdapter = new MyAdapter(eventsList);
        recyclerView.setAdapter(myAdapter);

        // Je gère l'orientation de mon device
        if (displayMode == 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        }

        // Ici j'ajoute un listener sur les items de mon RecyclerView (grâce à la classe RecyclerTouchListener)
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Nothing to do!
            }
            @Override
            public void onLongClick(View view, int position) {
                // Si appui long sur un item, j'affiche ma boite de dialogue, avec en paramètre la position de mon item
                // dans la liste
                showActionsDialog(position);
            }
        }));
    }

    // Grace à onResume, je recharge mon adaptateur lorsque l'activité retrouve le focus
    @Override
    public void onResume() {
        super.onResume();
        eventsList = EventObject.getEventsList();
        myAdapter = new MyAdapter(eventsList);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.item2:            //where 'icon' would be your item ID from menu.xml.
                Intent addItem2 = new Intent(this, AddEvent.class);
                startActivity(addItem2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // Fonction de suppression d'une capitale
    private void deleteCapital(int position) {
        EventObject.deleteEvent(eventsList.get(position));
        eventsList = EventObject.getEventsList();
        myAdapter = new MyAdapter(eventsList);
        recyclerView.setAdapter(myAdapter);
    }

    // Affichage d'une boite de dialogue
    private void showActionsDialog(final int position) {
        // Plutot que de préparer une string à convertir en CharSequence pour les options de ma boite de dialogue,
        // J'instancie directement un CharSequence avec les deux options proposées à l'utilisateur : edit ou delete
        CharSequence userOptions[] = new CharSequence[]{"Edit", "Delete"};
        // J'instancie le builder de mon AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Je définis le titre de mon AlertDialog
        builder.setTitle("Choose Option");
        // Et je définis les options proposées à l'utlisateur + j'instancie le listener correspondant à ces options
        builder.setItems(userOptions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int userChoice) {
                // Si l'utilisateur choisit la première option (donc Edit)
                if(userChoice == 0) {
                    // Je créé un nouvel intent
                    Intent updateIntent = new Intent(getApplicationContext(), AddEvent.class);
                    // J'y ajoute un extra.
                    // Grâce à l'interface Serializable, je peux véhiculer directement mon objet Capital
                    updateIntent.putExtra("cityExtra", eventsList.get(position));
                    // Et je lance l'activité
                    startActivity(updateIntent);
                } else {
                    // Si deuxième choix, j'appelle la fonction de suppression de ma capitale
                    deleteCapital(position);
                }
            }
        });
        // Et surtout JE N'OUBLIE PAS D'AFFICHER MA FENETRE DE DIALOGUE
        builder.show();
    }

    // Fonction qui me permet d'ajouter des villes dans la base de données si celle-ci ne contient aucun enregistrement.
    private void ajouterEvent() {
        EventObject.addEvent(new EventObject("pique-nique", null, "foret", null));

    }
}