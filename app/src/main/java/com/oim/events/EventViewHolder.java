package com.oim.events;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class EventViewHolder extends RecyclerView.ViewHolder{

    private TextView nameEventView;
    private TextView placeView;
    private TextView dateView;


    //itemView est la vue correspondante à 1 cellule
    public EventViewHolder(View itemView) {
        super(itemView);

        //c'est ici que l'on fait nos findView

        nameEventView = (TextView) itemView.findViewById(R.id.event_name);
        placeView = (TextView) itemView.findViewById(R.id.event_place);
        dateView = (TextView) itemView.findViewById(R.id.date);
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un InternObject

    public void bind(EventObject eventObject){
        nameEventView.setText(eventObject.getEventName());
        placeView.setText(eventObject.getEventPlace());
        dateView.setText(eventObject.getDate());
    }
}
