package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;

public class EditActivity extends AppCompatActivity {

    EditText firstName, lastName, email, address, note, phone;
    Button editButton;


    String sFirstName,
    sLastName,
    sEmail,
    sAddress,
    sNote,
    docID,
    sPhone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        firstName = findViewById(R.id.first_name_edit_text);
        lastName = findViewById(R.id.last_name_edit_text);
        email = findViewById(R.id.email_edit_text);
        address = findViewById(R.id.address_edit_text);
        note = findViewById(R.id.notes_edit_text);
        phone = findViewById(R.id.phone_edit_text);

        sFirstName = getIntent().getStringExtra("fname");
        sLastName = getIntent().getStringExtra("lname");
        sEmail = getIntent().getStringExtra("email");
        sAddress = getIntent().getStringExtra("address");
        sNote = getIntent().getStringExtra("note");
        sPhone = getIntent().getStringExtra("phone");
        docID = getIntent().getStringExtra("docID");

        firstName.setText(sFirstName);
        lastName.setText(sLastName);
        email.setText(sEmail);
        address.setText(sAddress);
        note.setText(sNote);
        phone.setText(sPhone);


        editButton = findViewById(R.id.edit_button);

        editButton.setOnClickListener((v) -> addContact());


    }

    private boolean checkForErrors(String fName, String lName, String sAddress, String sEmail, String sPhone) {

        if(fName == null || fName.isEmpty()) {
            firstName.setError("First Name Required");
            return true;
        }
        if(lName == null || lName.isEmpty()) {
            lastName.setError("Last Name Required");
            return true;
        }
        if(sAddress == null || sAddress.isEmpty()) {
            address.setError("Address Required");
            return true;
        }
        if(sEmail == null || sEmail.isEmpty()) {
            email.setError("Email Required");
            return true;
        }

        if(sPhone == null || sPhone.isEmpty()) {
            phone.setError("Phone Required");
            return true;
        }

        return false;
    }

    public void addContact() {
        String fName,lName,sAddress,sEmail,sNotes, sPhone;
        fName = firstName.getText().toString();
        lName = lastName.getText().toString();
        sAddress = address.getText().toString();
        sEmail = email.getText().toString();
        sNotes = note.getText().toString();
        sPhone = phone.getText().toString();

        if(checkForErrors(fName, lName, sAddress, sEmail, sPhone)){
            return;
        }else{
            Contact contact = new Contact(fName, lName, sEmail, sAddress, sNotes, sPhone);
            saveToFirebase(contact);
        }




    }


    void saveToFirebase(Contact contact) {
        DocumentReference documentReference;
        documentReference = Utilities.getCollectionReferenceForNotes().document(docID);

        documentReference.set(contact).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(EditActivity.this, "Completed!!", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(EditActivity.this, "Could not edit contact", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}