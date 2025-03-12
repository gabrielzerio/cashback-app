package com.example.cashback;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Cashback extends AppCompatActivity {
    private EditText editTextValor;
    private Button btnCalcular;
    private TableLayout tableLayout;
    private EditText editTextOutraPorc;
    private TextView txtPorcPerso;

    private TextView txtValorPorcPerso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cashback);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnCalcular = findViewById(R.id.btnCalcular);
        tableLayout = findViewById(R.id.tableLayout);
        editTextValor = findViewById(R.id.editTextValor);
        editTextOutraPorc = findViewById(R.id.editTextoPorcPerso);
        txtPorcPerso = findViewById(R.id.txtPorcPerso);
        txtValorPorcPerso = findViewById(R.id.txtValorPorcPerso);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularCashback();
            }
        });

    }

    private void calcularCashback() {
        String valorTexto = editTextValor.getText().toString();
        String valorPorcPerso = editTextOutraPorc.getText().toString();

        if (valorTexto.isEmpty()) {
            Toast.makeText(this, "Digite um valor!", Toast.LENGTH_SHORT).show();
            return;
        }

        double valor =  Double.parseDouble(valorTexto);
        double cont = 1;
        for (int i = 1; i < tableLayout.getChildCount(); i++) {

            TableRow row = (TableRow) tableLayout.getChildAt(i);
            TextView txtResultado = (TextView) row.getChildAt(1);

            double percentual = cont / 100;
            double cashback = valor * percentual;
            double valorFinal = valor - cashback;

            txtResultado.setText(String.format("R$ %.2f / R$ %.2f", cashback, valorFinal));
            cont++;
        }

        if(!valorPorcPerso.isEmpty()){
        double porcPerso = Double.parseDouble(valorPorcPerso) / 100 ;
        double cashBack = valor * porcPerso;
        double valorFinalPerso = valor - cashBack;
        txtPorcPerso.setText(editTextOutraPorc.getText().toString() + "%");
        txtValorPorcPerso.setText(String.format("R$ %.2f / R$ %.2f", cashBack, valorFinalPerso));
        }
    }
}