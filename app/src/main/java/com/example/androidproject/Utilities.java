package com.example.androidproject;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Utilities {

    public static CollectionReference getCollectionReferenceForNotes() {
        return FirebaseFirestore.getInstance().collection("app").document("contacts").collection("my_contacts");
    }



}
