package com.example.androidproject;

import android.annotation.SuppressLint;
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

import java.util.Locale;

public class ContactAdapter extends FirestoreRecyclerAdapter<Contact, ContactAdapter.ContactViewHolder>{

    Context context;

    public ContactAdapter(@NonNull FirestoreRecyclerOptions<Contact> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ContactViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Contact Contact) {
        holder.firstNameText.setText(Contact.getFirstName());
        holder.lastNameText.setText(Contact.getLastName());
        holder.emailText.setText(Contact.getEmail());
        holder.addressText.setText(Contact.getAddress());
        holder.noteText.setText(Contact.getNote());
        holder.phoneText.setText(Contact.getPhone());
        holder.initialText.setText(String.valueOf(Contact.getFirstName().toUpperCase().charAt(0)));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("fname", Contact.getFirstName());
                intent.putExtra("lname", Contact.getLastName());
                intent.putExtra("email", Contact.getEmail());
                intent.putExtra("address", Contact.getAddress());
                intent.putExtra("phone", Contact.getPhone());
                intent.putExtra("note", Contact.getNote());

                String docID = getSnapshots().getSnapshot(position).getId();
                intent.putExtra("docID", docID);
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView firstNameText,lastNameText,emailText,addressText,noteText, phoneText, initialText;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            firstNameText = itemView.findViewById(R.id.contact_fname_text_view);
            lastNameText = itemView.findViewById(R.id.contact_lname_text_view);
            emailText = itemView.findViewById(R.id.contact_email_text_view);
            addressText = itemView.findViewById(R.id.contact_address_text_view);
            noteText = itemView.findViewById(R.id.contact_note_text_view);
            phoneText = itemView.findViewById(R.id.phone_number_text_view);
            initialText = itemView.findViewById(R.id.contact_initial_text_view);

        }
    }


}
