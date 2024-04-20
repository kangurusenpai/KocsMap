package com.example.kocsmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextInputEditText text;
    RatingBar rating;
    ImageButton maps;
    Button upload;
    Button list;
    DatabaseReference dbreference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text=findViewById(R.id.textInputEditText);
        rating=findViewById(R.id.ratingBar);
        maps=findViewById(R.id.btnMap);
        upload=findViewById(R.id.btnTolt);
        list=findViewById(R.id.btnLista);

        dbreference= FirebaseDatabase.getInstance().getReference("reviews");

        if(getIntent().getStringExtra("desc")!= null)
            text.setText(getIntent().getStringExtra("desc"));
        rating.setRating(getIntent().getFloatExtra("rating",0));




        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToMaps();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Upload();
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List();
            }
        });

    }

    void GoToMaps(){
        Intent map=new Intent(MainActivity.this,MapsActivity.class);
        map.putExtra("desc",String.valueOf(text.getText()));
        map.putExtra("rating",rating.getRating());
        startActivity(map);
    }

    void Upload(){
            String description=String.valueOf(text.getText());
            if(!description.equals("")){
                HashMap<String, Object> review=new HashMap<>();
                review.put("description",description);
                review.put("rating",rating.getRating());
                String address=getIntent().getStringExtra("address");
                if(address!= null)
                {
                    review.put("address",address);

                    String key=dbreference.push().getKey();
                    review.put("key",key);

                    dbreference.child(key).setValue(review).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(MainActivity.this,"Review added",Toast.LENGTH_SHORT).show();
                            text.getText().clear();
                            rating.setRating(3f);
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this,"Didn't give location",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            else{
                Toast.makeText(MainActivity.this,"Didn't write description",Toast.LENGTH_SHORT).show();
                return;
            }
    }

    void List(){
        Intent listActivity=new Intent(MainActivity.this,ListActivity.class);
        startActivity(listActivity);
    }
}