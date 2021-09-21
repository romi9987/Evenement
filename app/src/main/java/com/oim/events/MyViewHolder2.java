package com.oim.events;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MyViewHolder2 extends RecyclerView.ViewHolder{

    private TextView firstEventView;
    private TextView firstPlaceView;
    private TextView firstDateView;


    //itemView est la vue correspondante à 1 cellule
    public MyViewHolder2(View itemView) {
        super(itemView);

        //c'est ici que l'on fait nos findView

        firstEventView; = (TextView) itemView.findViewById(R.id.event_name);
        firstPlaceView = (TextView) itemView.findViewById(R.id.event_place);
        firstDateView = (TextView) itemView.findViewById(R.id.date);
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un InternObject

    public void bind(EventObject eventObject){
        firstEventView.setText(eventObject.getEventName());
        firstPlaceView.setText(eventObject.getEventPlace());
        firstDateView.setText(eventObject.getDate());
    }
}
