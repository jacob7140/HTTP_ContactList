package com.example.http_contactlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.UserViewHolder> {

    ArrayList<String> names;
    contactListAdapter mListener;


    public ContactListAdapter(ArrayList<String> names, FragmentActivity activity) {
        this.names = names;
        this.mListener = (contactListAdapter) activity;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_adapter, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        String name = names.get(position);
        holder.contactValues.setText(name);
        holder.contactNames = name;
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static class UserViewHolder extends RecyclerView.ViewHolder{
        TextView contactValues;
        View rootView;
        contactListAdapter listener;
        String contactNames;

       public UserViewHolder(@NonNull View itemView) {
           super(itemView);
           rootView = itemView;
           contactValues = itemView.findViewById(R.id.textViewContactDetails);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   listener.toContactDetails(contactNames);
               }
           });
       }

   }

   interface contactListAdapter{
        void toContactDetails(String contactItem);
   }
}
