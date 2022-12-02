package com.example.androidproject.ui.add_contact;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidproject.Contact;
import com.example.androidproject.MainActivity;
import com.example.androidproject.R;
import com.example.androidproject.Utilities;
import com.example.androidproject.databinding.FragmentAddContactBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

public class AddContact extends Fragment {


    private AddContactViewModel mViewModel;
    EditText firstName, lastName, email, address, note;
    Button addButton;

    public static AddContact newInstance() {
        return new AddContact();
    }


    private FragmentAddContactBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {



        binding = FragmentAddContactBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        //now you can use findViewById using the following syntax
        //TextView textView = root.findViewById(R.id.textView);

        firstName = root.findViewById(R.id.first_name_edit_text);
        lastName = root.findViewById(R.id.last_name_edit_text);
        email = root.findViewById(R.id.email_edit_text);
        address = root.findViewById(R.id.address_edit_text);
        note = root.findViewById(R.id.notes_edit_text);

        addButton = root.findViewById(R.id.add_button);

        addButton.setOnClickListener((v)-> addContact());

        return root;
    }

    private boolean checkForErrors(String fName, String lName, String sAddress, String sEmail) {

        if(fName == null || fName.isEmpty()) {
            firstName.setError("First Name Required");
            return true;
        }
        if(lName == null || lName.isEmpty()) {
            firstName.setError("Last Name Required");
            return true;
        }
        if(sAddress == null || sAddress.isEmpty()) {
            firstName.setError("Address Required");
            return true;
        }
        if(sEmail == null || sEmail.isEmpty()) {
            firstName.setError("Email Required");
            return true;
        }
        return false;
    }

    public void addContact() {
        String fName,lName,sAddress,sEmail,sNotes;
        fName = firstName.getText().toString();
        lName = lastName.getText().toString();
        sAddress = address.getText().toString();
        sEmail = email.getText().toString();
        sNotes = note.getText().toString();

        if(checkForErrors(fName, lName, sAddress, sEmail)){
            return;
        }else{
            Contact contact = new Contact(fName, lName, sEmail, sAddress, sNotes);
            saveToFirebase(contact);
        }




    }

    void saveToFirebase(Contact contact) {
        DocumentReference documentReference;
        documentReference = Utilities.getCollectionReferenceForNotes().document();

        documentReference.set(contact).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Completed!!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "Could not add contact", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddContactViewModel.class);

        // TODO: Use the ViewModel
    }

}