package com.example.imsproject;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recyclerviewadapter extends RecyclerView.Adapter<Recyclerviewadapter.ViewHolder> {

    private Context context;
    private ArrayList<String> submitters = new ArrayList<String>();
    private ArrayList<String> categories = new ArrayList<String>();
    private ArrayList<String> ideas = new ArrayList<String>();
    private ArrayList<String> types = new ArrayList<String>();
    private ArrayList<String> uids = new ArrayList<String>();
    private ArrayList<String> ideatags = new ArrayList<String>();

    public Recyclerviewadapter(Context context,ArrayList<String> submitters,ArrayList<String> categories,ArrayList<String> ideas,ArrayList<String> types,ArrayList<String> uids,ArrayList<String> ideatags){
        this.submitters = submitters;
        this.context = context;
        this.categories = categories;
        this.ideas = ideas;
        this.types = types;
        this.uids = uids;
        this.ideatags = ideatags;
    }

    @NonNull
    @Override
    public Recyclerviewadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,false);
        ViewHolder holder = new ViewHolder(view,context,ideatags);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Recyclerviewadapter.ViewHolder holder, int position) {
        holder.recyclersubmitter.setText(submitters.get(position));
        holder.recyclercategory.setText(categories.get(position));
        holder.recycleridea.setText(ideas.get(position));
        holder.recyclertype.setText(types.get(position));
        holder.recycleruid.setText(uids.get(position));
        holder.recyclerideatag.setText(ideatags.get(position));
    }

    @Override
    public int getItemCount() {
        return ideas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Context context;
        ArrayList<String> ideatags;
        TextView recyclersubmitter;
        TextView recyclercategory;
        TextView recycleridea;
        TextView recyclertype;
        TextView recycleruid;
        TextView recyclerideatag;
        RelativeLayout parentlayout;

        public ViewHolder(@NonNull View itemView,Context context,ArrayList<String> ideatags) {
            super(itemView);
            parentlayout = itemView.findViewById(R.id.parent_layout);
            recyclersubmitter = itemView.findViewById(R.id.recyclersubmitter);
            recyclercategory = itemView.findViewById(R.id.recyclercategory);
            recycleridea = itemView.findViewById(R.id.recycleridea);
            recyclertype = itemView.findViewById(R.id.recyclertype);
            recycleruid = itemView.findViewById(R.id.recyclersubmitteruid);
            recyclerideatag = itemView.findViewById(R.id.recyclerideatag);
            itemView.setOnClickListener(this);
            this.context = context;
            this.ideatags = ideatags;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context,ViewideaActivity.class);
            intent.putExtra("ideatag",ideatags.get(getAdapterPosition()));
            context.startActivity(intent);
        }
    }
}


