package com.example.taglineher;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AlterarPacienteActivity extends AppCompatActivity {
    private SQLiteDatabase bancoDados;
    public Button buttonAlterar;
    public EditText editTextNome;
    public EditText editTextEmail;
    public EditText editTextTelefone;
    public Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_paciente);

        buttonAlterar = (Button) findViewById(R.id.buttonAlterar);
        editTextNome = (EditText) findViewById(R.id.editTextNome);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextTelefone = (EditText) findViewById(R.id.editTextTelefone);

        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);

        carregarDados();

        buttonAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alterar();
            }
        });

    }

    public void carregarDados(){
        try {
            bancoDados = openOrCreateDatabase("hospital.db", MODE_PRIVATE, null);
            Cursor cursor = bancoDados.rawQuery("SELECT id, nome, email, telefone FROM paciente WHERE id = " + id.toString(), null);
            cursor.moveToFirst();
            editTextNome.setText(cursor.getString(1));
            editTextEmail.setText(cursor.getString(2));
            editTextTelefone.setText(cursor.getString(3));

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void alterar(){
        String valueNome;
        String valueEmail;
        String valueTelefone;
        valueNome = editTextNome.getText().toString();
        valueEmail = editTextEmail.getText().toString();
        valueTelefone = editTextTelefone.getText().toString();
        try{
            bancoDados = openOrCreateDatabase("hospital.db", MODE_PRIVATE, null);
            String sql = "UPDATE paciente SET nome=?, telefone=?, email=? WHERE id=?";
            SQLiteStatement stmt = bancoDados.compileStatement(sql);
            stmt.bindString(1,valueNome);
            stmt.bindString(2,valueEmail);
            stmt.bindString(3,valueTelefone);
            stmt.bindLong(2,id);
            stmt.executeUpdateDelete();
            bancoDados.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
    }
}