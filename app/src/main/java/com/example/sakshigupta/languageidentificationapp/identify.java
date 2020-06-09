package com.example.sakshigupta.languageidentificationapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.nl.languageid.LanguageIdentification;
import com.google.mlkit.nl.languageid.LanguageIdentifier;

import java.util.Locale;


public class identify extends AppCompatActivity {
Button iden;
TextView tv;
EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify);
        iden = findViewById(R.id.idenbtn);
        tv= findViewById(R.id.textview);
        et = findViewById(R.id.edittext);
        iden.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LanguageIdentifier languageIdentifier =
                        LanguageIdentification.getClient();
                languageIdentifier.identifyLanguage(et.getText().toString())
                        .addOnSuccessListener(
                                new OnSuccessListener<String>() {
                                    @Override
                                    public void onSuccess(@Nullable String languageCode) {
                                        if (!languageCode.equals("und")) {
                                            Locale loc = new Locale(languageCode);
                                            tv.setText("The text is in " + loc.getDisplayLanguage());
                                            //tv.setText(languageCode);

                                        } else {
                                            tv.setText("Can't identify language");
                                        }
                                    }
                                })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Model couldn’t be loaded or other internal error.
                                        // ...
                                    }
                                });
            }
        });

    }
}
