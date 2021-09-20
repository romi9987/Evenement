package com.oim.events;

import static com.oim.events.DBHelper.TABLE_INTERN;
import static com.oim.events.DBHelper.TABLE_INTERN_COLUMN_ID;
import static com.oim.events.DBHelper.TABLE_INTERN_COLUMN_INTERN_FIRST_NAME;
import static com.oim.events.DBHelper.TABLE_INTERN_COLUMN_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class InternDao  implements Dao<InternObject> {

    private SQLiteDatabase dbWrite,dbRead;
    private DBHelper dbHelper;

    public InternDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        dbWrite = dbHelper.getWritableDatabase();
        dbRead = dbHelper.getReadableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // Méthode d'insertion de mon objet Intern dans la base de données.
    // En paramètre je peux envoyer un objet directement
    @Override
    public void insertIntern(InternObject pIntern) {
        open();
        // Content values contient les données qui seront insérées.
        // Son utilisation est obligatoire pour les requêtes.
        ContentValues content = new ContentValues();
        content.put(TABLE_INTERN_COLUMN_INTERN_FIRST_NAME, pIntern.getInternFirstName());
        content.put(TABLE_INTERN_COLUMN_NAME, pIntern.getInternName());

        // Ici j'utilise insertOrThrow qui me permet de dégager une exception si l'insert ne s'est pas bien passé.
        try {
            dbWrite.insertOrThrow(TABLE_INTERN, null, content);
            close();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Méthode qui me permet de récupérer directement une liste d'objet Intern
    @Override
    public ArrayList<InternObject> getAllIntern() {
        open();
        // Je déclare une nouvelle liste de type InternObject
        ArrayList<InternObject> interns = new ArrayList<>();
        // J'exécute ma requête et "stocke" le resultat de le curseur (Le curseur me permettra d'indexer tous les éléments
        //retournés par ma requete
        Cursor res = dbRead.rawQuery("SELECT * FROM " + TABLE_INTERN, null);
        // Ici je teste si ma table a bien  des enregistrements
        if(res.getCount() == 0) {
            // Si 0 --> Je ferme mon curseur et je renvoie une liste de capitales vide (et non pas null)
            res.close();
            // Si c'est le cas le return me fait directement sortir de la fonction
            return interns;
        }

        // Sinon, je repositionne mon curseur sur le premier enregistrement
        res.moveToFirst();
        // Puis tant qu'il y a des enregistrements
        while(!res.isAfterLast()) {
            // J'instancie un objet Capital avec l'enregistrement en cours de lecture
            InternObject intern = new InternObject(res.getInt(res.getColumnIndex(TABLE_INTERN_COLUMN_ID)),
                    res.getString(res.getColumnIndex(TABLE_INTERN_COLUMN_INTERN_FIRST_NAME)),
                    res.getString(res.getColumnIndex(TABLE_INTERN_COLUMN_NAME)));
            // Puis j'ajoute ma capitale à ma liste de capitales
            interns.add(intern);
            // Et je déplace le curseur sur l'enregistrement suivant.
            res.moveToNext();
        }

        // Une fois tous mes enregistrements récupérés, je peux fermer le curseur et retourner ma liste de capitales.
        res.close();
        close();
        return interns;
    }

    //renvoi un objet correspondant à l' id en base
    @Override
    public InternObject read(int id) {
        open();
        return null;
    }

    // Méthode de mise à jour d'un intern avec en paramètre un objet intern
    @Override
    public void updateIntern(InternObject pIntern){
        open();
        ContentValues values = new ContentValues();
        values.put(TABLE_INTERN_COLUMN_INTERN_FIRST_NAME, pIntern.getInternFirstName());
        values.put(TABLE_INTERN_COLUMN_NAME, pIntern.getInternName());
        // Après avoir alimenté contentValues, je peux lancer ma requete d'update, avec en paramètre de ma requete l'ID
        // de mon objet Capital (Attention, vous ne pouvez pas envoyer un int, vous devez envoyer une String --> String.valueOf(pCapital.getId())
        dbWrite.update(TABLE_INTERN, values, TABLE_INTERN_COLUMN_ID + " = ?", new String[] { String.valueOf(pIntern.getId())});
        close();
    }
    // Méthode de suppression d'un Intern
    @Override
    public  void deleteIntern(InternObject pIntern) {
        open();
        dbWrite.delete(TABLE_INTERN, TABLE_INTERN_COLUMN_ID + " = ?", new String[] { String.valueOf(pIntern.getId())});
        close();
    }
}
