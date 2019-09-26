package com.example.uas_pwpb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {
    public static String CURRENT_PERSON = "extra_person";
    EditText edtName, edtGender;
    LinearLayout btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Student student = getIntent().getParcelableExtra(CURRENT_PERSON);
        initialize();
        edtName.setText(student.getName());
        edtGender.setText(student.getJenkel());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(UpdateActivity.this);
                Student currentPerson = new Student();
                currentPerson.setName(edtName.getText().toString());
                currentPerson.setJenkel(edtGender.getText().toString());
                db.update(currentPerson);
                Intent i = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void initialize() {
        edtName = findViewById(R.id.edtUNama);
        edtGender = findViewById(R.id.edtUJenkel);
        btnSave = findViewById(R.id.btnSimpanData);
    }
}
