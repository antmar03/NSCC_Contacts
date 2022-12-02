package com.example.androidproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactAdapter extends FirestoreRecyclerAdapter<Contact, ContactAdapter.ContactViewHolder>{

    Context context;

    public ContactAdapter(@NonNull FirestoreRecyclerOptions<Contact> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ContactViewHolder holder, int position, @NonNull Contact Contact) {
        holder.firstNameText.setText(Contact.getFirstName());
        holder.lastNameText.setText(Contact.getLastName());
        holder.emailText.setText(Contact.getEmail());
        holder.addressText.setText(Contact.getAddress());
        holder.noteText.setText(Contact.getNote());
        holder.phoneText.setText(Contact.getPhone());
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView firstNameText,lastNameText,emailText,addressText,noteText, phoneText;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            firstNameText = itemView.findViewById(R.id.contact_fname_text_view);
            lastNameText = itemView.findViewById(R.id.contact_lname_text_view);
            emailText = itemView.findViewById(R.id.contact_email_text_view);
            addressText = itemView.findViewById(R.id.contact_address_text_view);
            noteText = itemView.findViewById(R.id.contact_note_text_view);
            phoneText = itemView.findViewById(R.id.phone_number_text_view);

        }
    }


}
