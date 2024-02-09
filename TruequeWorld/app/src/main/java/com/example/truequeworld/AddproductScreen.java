package com.example.truequeworld;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;



public class AddproductScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct_screen);

        Spinner spinner = findViewById(R.id.Pricespinner);
        final EditText editText = findViewById(R.id.editText);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos == 0) { // Si se selecciona el primer elemento del Spinner
                    editText.setVisibility(View.GONE); // Oculta el EditText
                } else {
                    editText.setVisibility(View.VISIBLE); // Muestra el EditText
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No es necesario implementar nada aquí
            }
        });
    }
}
