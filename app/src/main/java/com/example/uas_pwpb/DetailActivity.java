package com.example.uas_pwpb;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    public static String CURRENT_PERSON = "extra_person";
    TextView txtNo, txtName, txtGender, txtBirth, txtAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Student student = getIntent().getParcelableExtra(CURRENT_PERSON);
        initialize();
        txtName.setText(student.getName());
        txtGender.setText(student.getJenkel());
    }

    private void initialize() {
        txtNo = findViewById(R.id.txtNo);
        txtName = findViewById(R.id.txtNama);
        txtGender = findViewById(R.id.txtJenkel);
        txtBirth = findViewById(R.id.txtTglLahir);
        txtAddress = findViewById(R.id.txtAlamat);
    }
}
