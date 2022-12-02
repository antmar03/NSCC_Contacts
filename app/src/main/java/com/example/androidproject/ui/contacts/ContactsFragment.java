package com.example.androidproject.ui.contacts;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidproject.Contact;
import com.example.androidproject.ContactAdapter;
import com.example.androidproject.Utilities;
import com.example.androidproject.databinding.FragmentAddContactBinding;
import com.example.androidproject.databinding.FragmentContactsBinding;
import com.example.androidproject.ui.contacts.ContactsViewModel;
import com.example.androidproject.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

public class ContactsFragment extends Fragment {

    private ContactsViewModel mViewModel;

    public static ContactsFragment newInstance() {
        return new ContactsFragment();
    }

    private FragmentContactsBinding binding;
    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {



        binding = FragmentContactsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        recyclerView = root.findViewById(R.id.recyclerView);
        setupRecyclerView();
        //now you can use findViewById using the following syntax
        //TextView textView = root.findViewById(R.id.textView);



        return root;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ContactsViewModel.class);
        // TODO: Use the ViewModel
    }

    void setupRecyclerView() {
        Query query = Utilities.getCollectionReferenceForNotes();
        FirestoreRecyclerOptions<Contact> options = new FirestoreRecyclerOptions.Builder<Contact>()
                .setQuery(query, Contact.class).build();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        contactAdapter = new ContactAdapter(options,getContext());
        recyclerView.setAdapter(contactAdapter);
    }
    @Override
    public void onStart() {
        super.onStart();
        contactAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        contactAdapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        contactAdapter.notifyDataSetChanged();
    }


}