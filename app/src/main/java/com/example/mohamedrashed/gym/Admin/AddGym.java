
package com.example.mohamedrashed.gym.Admin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamedrashed.gym.ModelClasses.GymModel;
import com.example.mohamedrashed.gym.R;
import com.example.mohamedrashed.gym.Search.SearchAreas;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class AddGym extends AppCompatActivity {
    // Requests code
    final private int imagesSelect = 11;

    // Variables in cases
    int aShortSauna = 0, aShortJacuzzi = 0, aShortSpa = 0;

    String imgURL = "";
    String genderAr = "رجال";
    String latLng;

    Uri imageUri = null;



    // Deceleration widget
    ImageView imageViewSelected;
    EditText gymName, gymAdress, gymPrice, gymTime, gymDescription, gymLatLng;
    String txtGymName, txtGymAdress, txtGymPrice, txtGymTime, txtGymDescription;
    TextView textViewAreaName;
    RadioGroup radioGroup;
    //RadioButton radioButtonMen, radioButtonMix, radioButtonWomen;
    CheckBox checkBoxSauna, checkBoxJacuzzi, checkBoxSpa;


    // Fire Base Control
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    private String genderChild = "";
    public static String areaChild = "NewEgypt";
    public static String areaName = "اختر المنقطة";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gym);

        imageViewSelected = findViewById(R.id.image_view_selected);

        gymName = findViewById(R.id.addgym_txt_gym_name);
        gymAdress = findViewById(R.id.addgym_gym_adress);
        gymPrice = findViewById(R.id.addgym_gym_price);
        gymTime = findViewById(R.id.addgym_gym_time);
        gymDescription = findViewById(R.id.addgym_gym_description);
        gymLatLng = findViewById(R.id.addgym_gym_latlng);

        textViewAreaName = findViewById(R.id.addgym_txt_area_name);

        radioGroup = findViewById(R.id.addgym_gym_radio_group);
        //radioButtonMen = findViewById(R.id.addgym_radio_men);
        //radioButtonMix = findViewById(R.id.addgym_radio_mix);
        //radioButtonWomen = findViewById(R.id.addgym_radio_women);

        checkBoxSauna = findViewById(R.id.addgym_sauna);
        checkBoxJacuzzi = findViewById(R.id.addgym_jacuzzi);
        checkBoxSpa = findViewById(R.id.addgym_spa);


    }


    public void selectImage(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, imagesSelect);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bitmap bitmap = null;
            imageUri = data.getData();
            Toast.makeText(this, "تم اختيار الصوره بنجاح", Toast.LENGTH_SHORT).show();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageViewSelected.setImageBitmap(bitmap);
            //upload(imageUri);

        } else {
            Toast.makeText(this, "لم يتم اختيار اى صوره", Toast.LENGTH_LONG).show();
        }

    }

    private void upload(Uri uri) {
        final String FBKey = ref.push().getKey();
        //final String imageName = UUID.randomUUID().toString() + ".jpg";
        storageReference.child("Images").child(FBKey).putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    storageReference.child("Images").child(FBKey).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            imgURL = task.getResult().toString();
                            //Picasso.get().load(imgURL).into(imageView);

                            GymModel gymModel = new GymModel(aShortSauna, aShortJacuzzi, aShortSpa,
                                    txtGymName, txtGymAdress, txtGymPrice, txtGymTime, txtGymDescription,
                                    genderAr, imgURL, FBKey, latLng);

                            ref.child("Gyms").child(areaChild).child(genderChild).child(FBKey).setValue(gymModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        finish();
                                    }
                                }
                            });

                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Task !Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void addGym(View view) {
        if (getStringData()) {
            if (!areaChild.equals("")) {
                if (!getGender().equals("")) {
                    getFeatures();
                    if (imageUri != null) {
                        //Toast.makeText(this, "Gym Added", Toast.LENGTH_SHORT).show();
                        //addGymOnFireBase();
                        if (!getLatLng().equals("")) {
                            upload(imageUri);
                        }
                    }
                    else {
                        Toast.makeText(this, "برجاء اختيار صورة للجيم", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(this, "برجاء اختيار نوع الجيم", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "برجاء اختيار المنقطة الخاصة بهذا الجيم", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Gym Not Added", Toast.LENGTH_SHORT).show();

        }
    }

    private void getFeatures() {
        if (checkBoxSauna.isChecked())
            aShortSauna = 1;

        if (checkBoxJacuzzi.isChecked())
            aShortJacuzzi = 1;

        if (checkBoxSpa.isChecked())
            aShortSpa = 1;
    }

    private String getGender() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.addgym_radio_women:
                        genderChild = "Women";
                        genderAr = "سيدات";
                        break;
                    case R.id.addgym_radio_men:
                        genderChild = "Men";
                        genderAr = "رجال";
                        break;
                    case R.id.addgym_radio_mix:
                        genderChild = "Mix";
                        genderAr = "ميكس";
                        break;
                    default:
                        genderChild = "";
                }
            }
        });
        return genderChild;
    }

    private String getLatLng() {
        String LatLng = gymLatLng.getText().toString();
        if (!LatLng.isEmpty()) {
            latLng = LatLng;
            return LatLng;
        }
        Toast.makeText(this, "برجاء ادخال خط الطول و العرض", Toast.LENGTH_SHORT).show();
        return "";
    }

    private boolean getStringData() {
        txtGymName = gymName.getText().toString();
        txtGymAdress = gymAdress.getText().toString();
        txtGymPrice = gymPrice.getText().toString();
        txtGymTime = gymTime.getText().toString();
        txtGymDescription = gymDescription.getText().toString();

        if (txtGymName.isEmpty() || txtGymAdress.isEmpty() || txtGymPrice.isEmpty() || txtGymTime.isEmpty() || txtGymDescription.isEmpty()) {
            Toast.makeText(this, "برجاء ملئ جميع البيانات", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void selectAreaForAddGym(View view) {
        startActivity(new Intent(getApplicationContext(), SearchAreas.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        textViewAreaName.setText(areaName);
    }
}
