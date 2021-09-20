package com.oim.events;

import java.util.ArrayList;

public interface Dao<T> {
    public void insertIntern(T obj); // ajout d'une nouvelle occurence en base
    public ArrayList<T> getAllIntern(); //renvoi tous les objets de la table correspondante
    public T read(int id); //renvoi un objet correspondant à l' id en base
    public void updateIntern(T obj); // Méthode de mise à jour d' un objet
    public  void deleteIntern(T obj);// Méthode de suppression d' un objet
}
