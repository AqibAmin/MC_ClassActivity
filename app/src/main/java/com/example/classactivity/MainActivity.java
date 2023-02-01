package com.example.classactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText name, rakat;
    EditText date;
    Spinner type;
    ArrayAdapter<String> spinnerAdapter;
    CheckBox withJamat;
    Button btnSave;
    DBHandler db;
    ListView listView;
    ImageView gitLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.txt_name);
        rakat = findViewById(R.id.txt_rakat);
        date = findViewById(R.id.txt_date);
        withJamat = findViewById(R.id.ckb_jamat);
        btnSave = findViewById(R.id.add_namaz);
        type = findViewById(R.id.sinner_type);
        gitLink=findViewById(R.id.git_link);
        ArrayList<String> types = new ArrayList<String>();
        types.add("Farz");
        types.add("Nawafil");
        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(spinnerAdapter);
        db = new DBHandler(this);
        listView = findViewById(R.id.lstview);
        RefreshGrid();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nm = name.getText().toString();
                String rk = rakat.getText().toString();
                boolean jamat = withJamat.isChecked();
                String dt = date.getText().toString();
                if (nm.isEmpty() || rk.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter valid data", Toast.LENGTH_SHORT).show();
                    return;
                }
                Namaz student = new Namaz(0,type.getSelectedItem().toString(),nm, rk, jamat,dt);
                db.insertStudent(student);
                RefreshGrid();
            }
        });
        gitLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("https://github.com/AqibAmin/MadrasaRegister");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });

    }
    public void RefreshGrid(){
        List<Namaz> records = db.selectAllRecords();
        ArrayAdapter arrayAdapter = new ArrayAdapter<Namaz>(MainActivity.this, android.R.layout.simple_list_item_1,records);
        listView.setAdapter(arrayAdapter);
    }

}