package com.oim.events;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder{

    private TextView firstNameView;
    private TextView nameViewView;

    //itemView est la vue correspondante à 1 cellule
    public MyViewHolder(View itemView) {
        super(itemView);

        //c'est ici que l'on fait nos findView
        firstNameView = (TextView) itemView.findViewById(R.id.first_name);
        nameViewView = (TextView) itemView.findViewById(R.id.name);
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un InternObject
    public void bind(InternObject internObject){
        firstNameView.setText(internObject.getInternFirstName());
        nameViewView.setText(internObject.getInternName());
    }
}
