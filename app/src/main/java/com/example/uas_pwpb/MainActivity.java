package com.example.uas_pwpb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerviewAdapter.OnUserClickListener{
    RecyclerView recyclerView;
    LinearLayout btnInput;
    RecyclerView.LayoutManager layoutManager;
    List<Student> listPersonInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvData);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        btnInput = findViewById(R.id.btnInputData);
        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, InputActivity.class);
                startActivity(i);
            }
        });
        setupRecyclerView();
    }
    private void setupRecyclerView() {
        DatabaseHelper db = new DatabaseHelper(MainActivity.this);
        listPersonInfo = db.selectUserData();
        RecyclerviewAdapter adapter = new RecyclerviewAdapter(MainActivity.this, listPersonInfo, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }



    @Override
    public void onUserClick(Student currentPerson, String action) {
        if(action.equals("Edit")){
            Intent i = new Intent(MainActivity.this, UpdateActivity.class);
            i.putExtra(UpdateActivity.CURRENT_PERSON, currentPerson);
            startActivity(i);
        }
        if(action.equals("Delete")){
            DatabaseHelper db = new DatabaseHelper(MainActivity.this);
            db.delete(currentPerson.getName());
            setupRecyclerView();
        }
        if(action.equals("View")) {
            Intent i = new Intent(MainActivity.this, DetailActivity.class);
            i.putExtra(DetailActivity.CURRENT_PERSON, currentPerson);
            startActivity(i);
        }
    }
}
