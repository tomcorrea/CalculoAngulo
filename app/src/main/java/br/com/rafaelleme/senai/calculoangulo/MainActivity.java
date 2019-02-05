package br.com.rafaelleme.senai.calculoangulo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editValorAngulo;
    RadioButton rbseno, rbcosseno, rbtangente;
    Button btnCalcular;
    private int opcao = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editValorAngulo = findViewById(R.id.edtvalorangulo);
        rbseno = findViewById(R.id.rbseno);
        rbcosseno = findViewById(R.id.rbcosseno);
        rbtangente = findViewById(R.id.rbtangente);
        btnCalcular = findViewById(R.id.btnCalcular);

        rbseno.setOnClickListener(this);
        rbcosseno.setOnClickListener(this);
        rbtangente.setOnClickListener(this);
        btnCalcular.setOnClickListener(this);

    }

    public double CalcularSeno(double angulo) {
        return Math.sin(Math.toRadians(angulo));
    }

    public double CalcularCosseno(double angulo) {
        return Math.cos(Math.toRadians(angulo));
    }

    public double CalcularTangente(double angulo) {
        return Math.tan(Math.toRadians(angulo));
    }

    public void calcular() {
        AlertDialog digAlerta;
        double angulo, ValorCalculado;
        String titulo;
        angulo = Double.valueOf(editValorAngulo.getText().toString());

        if (opcao == 1) {
            titulo = "Cálculo de Seno";
            ValorCalculado = CalcularSeno(angulo);
        } else if (opcao == 2) {
            titulo = "Cálculo de Cosseno";
            ValorCalculado = CalcularCosseno(angulo);
        } else {
            titulo = "Cáculo de Tangoente";
            ValorCalculado = CalcularTangente(angulo);
        }

        digAlerta = new AlertDialog.Builder(this).create();
        digAlerta.setTitle(titulo);
        String valorFormatado = String.format("%.2f", ValorCalculado);
        digAlerta.setMessage(String.valueOf(valorFormatado));
        digAlerta.show();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rbseno:
                opcao = 1;
                rbcosseno.setChecked(false);
                rbtangente.setChecked(false);
                break;

            case R.id.rbcosseno:
                opcao = 2;
                rbseno.setChecked(false);
                rbtangente.setChecked(false);
                break;

            case R.id.rbtangente:
                opcao = 3;
                rbcosseno.setChecked(false);
                rbseno.setChecked(false);
                break;

            case R.id.btnCalcular:
                if(validar()) {
                    calcular();
                }
                break;
        }

    }

    private boolean validar(){
        if (editValorAngulo.getText().toString().equals("")) {
            editValorAngulo.setError("campo obrigatório!");
            return false;
        }else{
            double valorAngulo = Double.valueOf(editValorAngulo.getText().toString());
            if(valorAngulo >= 0 && valorAngulo <= 360){
                return true;
            }else{
                editValorAngulo.setError("O valor deve estar entre 0 e 360");
                return false;
            }
        }
    }
}
