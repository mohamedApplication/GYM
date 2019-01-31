package com.example.mohamedrashed.gym;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public  class FireBaseTools {

    private static DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    private static FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    public static void visits(String ref1, String ref2) {
        ref.child(ref1).child(ref2).child("visitors").push().setValue(getDate());
    }

    private static String getDate() {
        Calendar calendar = Calendar.getInstance();
        return  calendar.get(Calendar.DAY_OF_MONTH) +"/"+ (calendar.get(Calendar.MONTH) + 1 ) +"/"+ calendar.get(Calendar.YEAR);
    }


    public static String getParentKey(String name) {
        final String[] key = new String[1];
        ref.child("News").orderByChild("title").equalTo("Amir Mohamed").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                key[0] = dataSnapshot.getKey();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return key[0];
    }
}
