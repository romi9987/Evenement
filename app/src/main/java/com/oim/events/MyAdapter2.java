package com.oim.events;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter<MyViewHolder2> {

    List<EventObject> list2;

    //ajouter un constructeur prenant en entrée une liste
    public MyAdapter2(List<EventObject> list2) {
        this.list2 = list2;
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public MyViewHolder2 onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_cards,viewGroup,false);
        return new MyViewHolder2(view);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(MyViewHolder2 myViewHolder2, int position) {
        EventObject eventObject = list2.get(position);
        myViewHolder2.bind(eventObject);
    }

    @Override
    public int getItemCount() {
        return list2.size();
    }
}