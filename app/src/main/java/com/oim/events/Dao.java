package com.oim.events;

import java.util.ArrayList;

public interface Dao<T> {
    public void open();
    public void close();
    public void insert(T obj); // ajout d'une nouvelle occurence en base
    public ArrayList<T> getAll(); //renvoi tous les objets de la table correspondante
    public T read(int id); //renvoi un objet correspondant à l' id en base
    public void update(T obj); // Méthode de mise à jour d' un objet
    public  void delete(T obj);// Méthode de suppression d' un objet
}
