package com.example.http_contactlist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactListFragment extends Fragment {

    private final OkHttpClient client = new OkHttpClient();

    private static final String ARG_PARAM_APP_Contacts = "ARG_PARAM_APP_Contacts";

   private String aToken;

    public ContactListFragment() {
        // Required empty public constructor
    }


    public static ContactListFragment newInstance(String name) {
        ContactListFragment fragment = new ContactListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_APP_Contacts, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            aToken = getArguments().getString(ARG_PARAM_APP_Contacts);

        }
    }

    ContactListAdapter adapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    //ArrayList<String> name = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Contacts");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);

        recyclerView = view.findViewById(R.id.contactListRecyclerView);

        Request request = new Request.Builder().url("https://www.theappsdr.com/contacts").build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String body = response.body().string();
                    JSONArray array = new JSONArray(response);
                    String[] sentences = new String[array.length()];
                    for (int i = 0, i < array.length(); i++){
                        sentences[i] = array.getString(i);
                    }
                    Log.d("data", "onResponse: " + body);
                    Contact contact = new Contact(body);

                    name.clear();
                    name.addAll(Response);

                } else {
                    Log.d("data", "onResponse: Response failed");
                }

            }
        });


      return view;
    }

    String getContacts(){

    }
}