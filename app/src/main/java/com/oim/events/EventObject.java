package com.oim.events;

import android.content.Context;

import java.io.Serializable;
import java.util.List;

public class EventObject implements Serializable {
    private String eventName;
    private String date;
    private String eventPlace;
    private int event_id;
    private InternObject internObject;
    // Le helper est déclaré static pour etre persistant pendant toute la durée de vie de l'application
    private static EventDao eventDb;

    // J'ai besoin de 3 constructeurs :
// Sans paramètre pour pouvoir instancier un objet vide
    public EventObject() {}

    // Un constructeur avec les 3 paramètres. Ce constructeur sera principalement utilisé pour
// les objets Capital déjà en base de données, il me permet de véhiculer l'id de mon objet.
    public EventObject(int event_id, String eventName, String date, String eventPlace, InternObject internObject) {
        this.eventName = eventName;
        this.date = date;
        this.eventPlace = eventPlace;
        this.internObject = internObject;
        this.event_id = event_id;
    }

    // Ce dernier constructeur sans ID sera utilisé principalement pour l'ajout d'une nouvelle capitale
    public EventObject( String eventName, String date, String eventPlace, InternObject internObject) {
        this.eventName = eventName;
        this.date = date;
        this.eventPlace = eventPlace;
        this.internObject = internObject;
    }

    // Cette fonction me permet d'instancier mon Helper et ma BDD depuis l'activité principale
// Ce n'est rien d'autre qu'un appel au constructeur de mon Helper
    public static void initHelper(Context pContext) {
        eventDb = new EventDao(pContext);
    }

    // Fonction qui me retourne une List de capitales récupérées grace a mon Helper
    public static List<EventObject> getEventsList() {
        return eventDb.getAll();
    }

    // Fonction d'ajout d'une capital qui appel la fonction ajout du helper
    public static void addEvent(EventObject pEvent) {
        eventDb.insert(pEvent);
    }

    // Fonction de suppression d'une capitale qui appelle la fonction suppression du helper
    public static void deleteEvent(EventObject pEvent) {
        eventDb.delete(pEvent);
    }

    // Fonction de MAJ d'une Event qui appelle la fonction suppression du helper
    public static void updateEvent(EventObject pEvent) { eventDb.update(pEvent);}

// Getters et setters


    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public InternObject getInternObject() {
        return internObject;
    }

    public void setInternObject(InternObject internObject) {
        this.internObject = internObject;
    }

}