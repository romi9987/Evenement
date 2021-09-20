package com.oim.events;

import android.content.Context;

import java.io.Serializable;
import java.util.List;

// J'implémente l'interface Serializable pour pouvoir "véhiculer" un objet Capital dans un intent (putExtra)
public class InternObject implements Serializable {
    private String internName;
    private String internFirstName;
    private int id;
    // Le helper est déclaré static pour etre persistant pendant toute la durée de vie de l'application
    private static InternDao internDb;

    // J'ai besoin de 3 constructeurs :
    // Sans paramètre pour pouvoir instancier un objet vide
    public InternObject() {}

    // Un constructeur avec les 3 paramètres. Ce constructeur sera principalement utilisé pour
    // les objets Capital déjà en base de données, il me permet de véhiculer l'id de mon objet.
    public InternObject(int id, String internFirstName, String internName) {
        this.internName = internName;
        this.internFirstName = internFirstName;
        this.id = id;
    }

    // Ce dernier constructeur sans ID sera utilisé principalement pour l'ajout d'une nouvelle capitale
    public InternObject( String internFirstName, String internName) {
        this.internName = internName;
        this.internFirstName = internFirstName;
    }

    // Cette fonction me permet d'instancier mon Helper et ma BDD depuis l'activité principale
    // Ce n'est rien d'autre qu'un appel au constructeur de mon Helper
    public static void initHelper(Context pContext) {
        internDb = new InternDao(pContext);
    }

    // Fonction qui me retourne une List de capitales récupérées grace a mon Helper
    public static List<InternObject> getInternsList() {
        return internDb.getAllIntern();
    }

    // Fonction d'ajout d'une capital qui appel la fonction ajout du helper
    public static void addIntern(InternObject pIntern) {
        internDb.insertIntern(pIntern);
    }

    // Fonction de suppression d'une capitale qui appelle la fonction suppression du helper
    public static void deleteIntern(InternObject pIntern) {
        internDb.deleteIntern(pIntern);
    }

    // Fonction de MAJ d'une capitale qui appelle la fonction suppression du helper
    public static void updateIntern(InternObject pIntern) { internDb.updateIntern(pIntern);}

    // Getters et setters
    public String getInternName() {
        return internName;
    }

    public void setInternName(String internName) {
        this.internName = internName;
    }

    public String getInternFirstName() {
        return internFirstName;
    }

    public void setInternFirstName(String internFirstName) {
        this.internFirstName = internFirstName;
    }

    public int getId() {
        return id;
    }
}
