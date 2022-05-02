package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.strictmode.CleartextNetworkViolation;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void ingresar(View view)
    {
        try {
            EditText auxTxtRut = findViewById(R.id.txtRut);
            EditText auxTxtNombre = findViewById(R.id.txtNombre);
            EditText auxTxtDeuda = findViewById(R.id.txtDeuda);
            Cliente auxCliente = new Cliente();
            auxCliente.setRut(auxTxtRut.getText().toString());
            auxCliente.setNombre(auxTxtNombre.getText().toString());
            auxCliente.setDeuda(Integer.valueOf(auxTxtDeuda.getText().toString()));
            DeudaCliente auxDeuda = new DeudaCliente(this);
            auxDeuda.insertarCliente(auxCliente);
            this.mensaje("Datos Guardados");
            auxTxtRut.setText("");
            auxTxtNombre.setText("");
            auxTxtDeuda.setText("");
            auxTxtRut.requestFocus();
        }
        catch (Exception ex)
        {
            this.mensaje("Datos No Guardados" + ex.getMessage());
        }
    }

    public void mensaje(String auxmensaje)
    {
        Toast.makeText(this,auxmensaje,Toast.LENGTH_LONG).show();
    }



    public void mostrar(View view)
    {
        DeudaCliente auxDeuda = new DeudaCliente(this);
        List<Cliente> auxLista = auxDeuda.consultaCliente();
        String[] auxString = new String[auxLista.size()];
        int pos = 0;
        Iterator iter = auxLista.iterator();
        while(iter.hasNext())
        {
            Cliente auxCliente = new Cliente();
            auxCliente = (Cliente) iter.next();
            auxString[pos] =auxCliente.getRut() + " " + auxCliente.getNombre() + " " + auxCliente.getDeuda();
            pos++;
        }


        ListView auxListViewCliente = findViewById(R.id.LstView);
        auxListViewCliente.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,auxString));

    }
} //Fin clase