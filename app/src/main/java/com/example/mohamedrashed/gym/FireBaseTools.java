package com.example.mohamedrashed.gym;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public  class FireBaseTools {

    private static DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    public static void visits(String ref1, String ref2) {
        ref.child(ref1).child(ref2).child("visitors").push().setValue(getDate());
    }

    private static String getDate() {
        Calendar calendar = Calendar.getInstance();
        return  calendar.get(Calendar.DAY_OF_MONTH) +"/"+ (calendar.get(Calendar.MONTH) + 1 ) +"/"+ calendar.get(Calendar.YEAR);
    }
}
