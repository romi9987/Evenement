package com.oim.events;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class internAdapter extends RecyclerView.Adapter<InternViewHolder> {

    List<InternObject> list;

    //ajouter un constructeur prenant en entrée une liste
    public internAdapter(List<InternObject> list) {
        this.list = list;
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public InternViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.intern_cell_cards,viewGroup,false);
        return new InternViewHolder(view);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(InternViewHolder internViewHolder, int position) {
        InternObject internObject = list.get(position);
        internViewHolder.bind(internObject);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}