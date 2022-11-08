package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcvSinhVien;
    List<SinhVien> list;
    DatabaseReference database;
    SinhVienAdapter sinhVienAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvSinhVien = findViewById(R.id.list_sinhvien);
        LinearLayoutManager  linearLayoutManager = new LinearLayoutManager(this);
        rcvSinhVien.setLayoutManager(linearLayoutManager);

        list = new ArrayList<>();
        sinhVienAdapter = new SinhVienAdapter(list);
        rcvSinhVien.setAdapter(sinhVienAdapter);
    }

    private void getListSinhVienFire(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRef = database.getReference("QuanLySinhVien");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    SinhVien sinhVien = dataSnapshot.getValue(SinhVien.class);
                    list.add(sinhVien);

                }
                sinhVienAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Thất bại !", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
