package com.example.uas_pwpb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InputActivity extends AppCompatActivity {
    EditText edtName, edtGender;
    LinearLayout btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        initialize();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(InputActivity.this);
                Student currentPerson = new Student();
                SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
                String tglnow = date.format(new Date());
                currentPerson.setDate(tglnow);
                currentPerson.setName(edtName.getText().toString());
                currentPerson.setJenkel(edtGender.getText().toString());
                db.insert(currentPerson);
                Intent i = new Intent(InputActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void initialize() {
        edtName = findViewById(R.id.edtNama);
        edtGender = findViewById(R.id.edtJenkel);
        btnSave = findViewById(R.id.btnSimpanData);
    }
}
