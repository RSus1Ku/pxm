package ximbalDAO;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import miproyecto.ximbal2.Inicio;
import miproyecto.ximbal2.MapsActivity;

/**
 * Created by joseph on 07/03/2017.
 */

public class Connexion  {
    Context contexto;
    String consulta;
    public String resul;
    public Connexion(String consulta, Context context) {
        this.consulta = consulta;
        this.contexto = context;
    }

    public String Consultar()
    {
      new JSONTask().execute(consulta);
       return  resul;
    }

    public class JSONTask extends AsyncTask<String,String,String>
    {

        @Override
        protected String doInBackground(String... Parametros)
        {
            HttpURLConnection conexion = null;
            BufferedReader reader = null;
            try
            {
                URL url = new URL(Parametros[0]);
                conexion = (HttpURLConnection)url.openConnection();
                conexion.connect();
                InputStream stream = conexion.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String Line=""; //Lee linea por linea lo qye devuelve el web service
                while ((Line= reader.readLine()) != null)
                {
                    buffer.append(Line);
                }
                return  buffer.toString();//Retorna Datos manupulables en onPostExecute

            }catch (MalformedURLException e)
            {
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                if (conexion!=null){
                    conexion.disconnect();
                }
                try {
                    if(reader!=null)
                    {
                        reader.close();
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            return null;

        }
        @Override
        protected  void onPostExecute (String Resultado)
        {
            //Se obtinen los datos del resultaado
            super.onPostExecute(Resultado);
            try{
                Log.e("Salida", Resultado);
                Toast.makeText(contexto, Resultado,Toast.LENGTH_SHORT).show();
                //Se mandan esos datos a otra actividad lista en formato string
                //Intent i = new Intent(contexto, MapsActivity.class);


            }catch (Throwable t){
                Log.e("Falla",t.toString());
            }
        }
    }
}