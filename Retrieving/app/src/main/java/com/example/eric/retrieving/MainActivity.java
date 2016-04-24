package com.example.eric.retrieving;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseListAdapter;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //Firebase
    Firebase rootRef;
    ArrayList<String> messages = new ArrayList<>();


    //UI
    TextView textView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootRef = new Firebase("application url here"); //Application reference to Firebase

        textView = (TextView) findViewById(R.id.textViewOne);
        listView = (ListView) findViewById(R.id.listViewOne);
    }

    @Override
    protected void onStart() {
        super.onStart();

        final ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, messages);
        listView.setAdapter(adapter);

        Firebase messagesRef = rootRef.child("messages");

        //ORDERED LIST
//        FirebaseListAdapter<String> adapter =
//                new FirebaseListAdapter<String>(this, String.class, android.R.layout.simple_list_item_1, messagesRef) {
//                    @Override
//                    protected void populateView(View view, String s, int i) {
//                        TextView textView = (TextView)view.findViewById(android.R.id.text1);
//                        textView.setText(s);
//                    }
//                };
//        listView.setAdapter(adapter);



        //UNORDERED LIST EVENTS
        messagesRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String message = dataSnapshot.getValue(String.class);
                Log.v("E_VALUE_ADDED", message);
                messages.add(message);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String message = dataSnapshot.getValue(String.class);
                Log.v("E_VALUE_CHANGED", message);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String message = dataSnapshot.getValue(String.class);
                Log.v("E_VALUE_REMOVED", message);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
