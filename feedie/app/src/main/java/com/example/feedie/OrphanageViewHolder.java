package com.example.feedie;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class OrphanageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView orphanageName, orphanageDescription, orphanageAddress, orphanageContact, orphanageEmail;
    public ImageView imageView;
    public ItemClickListener listener;

    public OrphanageViewHolder(@NonNull View itemView) {
        super(itemView);
        orphanageName = (TextView) itemView.findViewById(R.id.orphanage_name);
        orphanageDescription = (TextView) itemView.findViewById(R.id.orphanage_description);
        orphanageAddress = (TextView) itemView.findViewById(R.id.orphanage_address);
        orphanageContact = (TextView) itemView.findViewById(R.id.orphanage_contact);
        orphanageEmail = (TextView) itemView.findViewById(R.id.orphanage_email);
        imageView = (ImageView) itemView.findViewById(R.id.orphanage_image);
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view, getAdapterPosition(), false);
    }
}
