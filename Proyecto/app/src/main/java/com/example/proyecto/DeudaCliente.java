package com.example.proyecto;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DeudaCliente extends SQLiteOpenHelper
{
    private static final int VERSION_BASEDATOS = 1;
    private static final String NOMBRE_BASEDATOS = "proyecto.db";
    private static final String TABLA_CLIENTE = "CREATE TABLE cliente (rut TEXT PRIMARY KEY, nombre TEXT, deuda INTEGER);";

    public DeudaCliente(Context context)
    {
        super(context,NOMBRE_BASEDATOS,null,VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(TABLA_CLIENTE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int old_version,int new_version)
    {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLA_CLIENTE + "';");
    }


    //Insertar CLiente
    public void insertarCliente(Cliente cliente)
    {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null)
        {
            db.execSQL("INSERT INTO cliente (rut,nombre,deuda) VALUES ('"
                                                         + cliente.getRut() + "','"
                                                         + cliente.getNombre() + "','"
                                                         + cliente.getDeuda() + "');");
        }
        db.close();
    }
    //FIN Insertar Cliente


    //Actualizar CLiente
    public void actualizarCliente(Cliente cliente)
    {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null)
        {
            db.execSQL("UPDATE  cliente SET nombre = '" + cliente.getNombre()
                                                        + "' WHERE rut = '" + cliente.getNombre()
                                                        + "' WHERE deuda = '" + cliente.getNombre());
        }
        db.close();
    }
    //FIN Actualizar Cliente


    //Eliminar CLiente
    public void eliminarCliente(String rut)
    {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null)
        {
            db.execSQL("DELETE FROM cliente"
                    + " WHERE rut = '" + rut);
        }
        db.close();
    }
    //FIN Eliminar Cliente


    //Eliminar Todos los CLiente
    public void eliminarTodosCliente()
    {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null)
        {
            db.execSQL("DELETE FROM cliente;");
        }
        db.close();
    }
    //FIN Eliminar Todos los Cliente


    //Consulta CLiente
    public List<Cliente> consultaCliente()
    {
        SQLiteDatabase db = getReadableDatabase();

        List<Cliente> auxListaCliente = new ArrayList<>();

        Cursor auxCursor =db.rawQuery( "SELECT rut,nombre,deuda FROM cliente",null);

        auxCursor.moveToFirst();

        do {
            Cliente auxCliente = new Cliente();
            auxCliente.setRut(auxCursor.getString(0));
            auxCliente.setNombre((auxCursor.getString(1)));
            auxCliente.setDeuda(Integer.valueOf((auxCursor.getString(2))));
            auxListaCliente.add(auxCliente);
        }while (auxCursor.moveToNext());

        db.close();
        auxCursor.close();
        return auxListaCliente;
    }
    //FIN Consulta Cliente


    //Busqueda CLiente
    public Cliente busquedaCliente(String rut)
    {
        SQLiteDatabase db = getReadableDatabase();

        Cliente auxCliente = new Cliente();

        Cursor auxCursor =db.rawQuery( "SELECT rut,nombre,deuda FROM cliente"
                                            + " WHERE rut = '" + rut + "';",null);

        auxCursor.moveToFirst();

        if(auxCursor != null) {
            auxCliente.setRut(auxCursor.getString(0));
            auxCliente.setNombre((auxCursor.getString(1)));
            auxCliente.setDeuda((auxCursor.getInt(2)));
        }
        else
        {
            auxCliente.setRut("");
            auxCliente.setNombre("");
            auxCliente.setDeuda(Integer.valueOf(""));
        }

        db.close();
        auxCursor.close();
        return auxCliente;
    }
    //FIN Busqueda Cliente
}
