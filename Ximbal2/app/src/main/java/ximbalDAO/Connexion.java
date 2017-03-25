package ximbalDAO;

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

/**
 * Created by joseph on 07/03/2017.
 */

public class Connexion {

    /*String strAccion = "buscarTipoEstablecimientoMovil";
    String strURL = "http://ximbal.somee.com/Services/WSDisfruta.asmx/";
    String UrlWebService = strURL+strAccion+"?idtipoEstablecimiento="+""+"&nombre="+"";
    new  Connexion().excure(UrlWebService);*/
    public class JSONTask extends AsyncTask<String,String,String>
    {
        public String resultadoConsulta;
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
                resultadoConsulta = Resultado;

            }catch (Throwable t){
                Log.e("Falla",t.toString());
            }
        }
    }
}
