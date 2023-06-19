package com.example.taglineher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class DashboardActivity extends AppCompatActivity {
    CardView pacienteCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        pacienteCard = findViewById(R.id.paciente);
        pacienteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, PacienteActivity.class);
                startActivity(intent);
            }
        });
    }
}