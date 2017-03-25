package miproyecto.ximbal2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Policy;

public class ActivityComentarios extends AppCompatActivity  {
    Button btn ;
    RatingBar rt;
    EditText etbox;

    int calif, idSitio;
    String comentario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);
        btn = (Button) findViewById(R.id.btnEnviar);
        rt = (RatingBar) findViewById(R.id.ratingBar);
        etbox = (EditText) findViewById(R.id.Edit);
        Intent intent = getIntent();
        idSitio = intent.getIntExtra("idSitio", 0 );

        rt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(ActivityComentarios.this, "Calificación : "+ (int)rt.getRating()+ " estrellas", Toast.LENGTH_SHORT ).show();

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comentario = etbox.getText().toString();
                String strURL ="http://ximbalpruebahoy.somee.com/services/ximbalmovil.asmx/";
                String strAccion = "agregarComentarioSitio";
                String UrlWebService = strURL + strAccion+ "?idUsuario="+2+"&comentario="+comentario+"&fecha="+"2017-03-18"+"&Idsitio="+idSitio;
                new JSONTask().execute(UrlWebService);

                 strURL ="http://ximbalpruebahoy.somee.com/services/ximbalmovil.asmx/";
                 strAccion = "agregarComentarioSitio";
                 UrlWebService = strURL + strAccion+ "?idUsuario="+2+"&calificacion="+(int)rt.getRating()+"&Idsitio="+idSitio;
                new JSONTask2().execute(UrlWebService);
            }
        });

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
                //Toast.makeText(Inicio.this, Resultado,Toast.LENGTH_SHORT).show();
                //Se mandan esos datos a otra actividad lista en formato string
                Toast.makeText(ActivityComentarios.this, "Comentario registrado con éxito", Toast.LENGTH_SHORT ).show();

            }catch (Throwable t){
                Log.e("Falla",t.toString());
                Toast.makeText(ActivityComentarios.this, "Ocurrio un error intentelo más tarde", Toast.LENGTH_SHORT ).show();

            }
        }
    }

    public class JSONTask2 extends AsyncTask<String,String,String>
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
                //Toast.makeText(Inicio.this, Resultado,Toast.LENGTH_SHORT).show();
                //Se mandan esos datos a otra actividad lista en formato string
                Toast.makeText(ActivityComentarios.this, "Comentario registrado con éxito", Toast.LENGTH_SHORT ).show();

            }catch (Throwable t){
                Log.e("Falla",t.toString());
                Toast.makeText(ActivityComentarios.this, "Ocurrio un error intentelo más tarde", Toast.LENGTH_SHORT ).show();

            }
        }
    }


}

