package miproyecto.ximbal2;

import android.*;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.OnBoomListener;
import com.nightonke.boommenu.OnBoomListenerAdapter;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.nightonke.boommenu.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ximbalDAO.Connexion;

public class MainActivity extends AppCompatActivity {
    private TextView textViewForAnimation;
    private TextView textViewForButton;
    private BoomMenuButton bmb;
    public String que;
    JSONArray ResultadosEnArrayEstablecimientos = null;
    JSONArray ResultadosEnArraySitios = null;

    JSONArray ResultadosEnArray1 = null;
    JSONArray ResultadosEnArray2= null;
    JSONArray ResultadosEnArray3 = null;
    JSONArray ResultadosEnArray4 = null;
    JSONArray ResultadosEnArray5 = null;
    JSONArray ResultadosEnArray6= null;
  //  JSONArray[] arreglo = (JSONArray[]) new Object[6];
    Object[] arreglo = new Object[6];
    Object [] Datos = new String[6];
    private List<String> nombres = new ArrayList<>();
    private List<Integer> id = new ArrayList<>();
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


       // textViewForButton = (TextView) findViewById(R.id.p1);
       // textViewForAnimation = (TextView) findViewById(R.id.p2);

        bmb = (BoomMenuButton) findViewById(R.id.bmbFragment);
        assert bmb != null;
        bmb.setButtonEnum(ButtonEnum.TextInsideCircle);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_7_3);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_7_3);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) addBuilder();

        // Use OnBoomListenerAdapter to listen part of methods
        bmb.setOnBoomListener(new OnBoomListenerAdapter() {
            @Override
            public void onBoomWillShow() {
                super.onBoomWillShow();
                // logic here
            }
        });
        //  arreglo[0] = ResultadosEnArray1;
        // arreglo[1] = ResultadosEnArray2;
        // arreglo[2] = ResultadosEnArray3;
        // arreglo[3] = ResultadosEnArray4;
        // arreglo[4] = ResultadosEnArray5;
        // arreglo[5] = ResultadosEnArray6

        ConsultarSitios();//LLenar JSONArray de sitios
        //ConsultarEstablecimientos();//LLenar JSONArray de establecimientos
      //  DividirCategorias();//Una vez llenos hay que filtrarlos para poder ennviarlos luego en el boom


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void addBuilder() {
        bmb.addBuilder(new TextInsideCircleButton.Builder()
                .normalText(BuilderManager.getTextResource())

                .normalImageRes(BuilderManager.getImageResource())
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {

                        switch (index){
                            case 1:
                                IrActivityMap(1);
                                break;
                            case 2:

                                break;
                            case 3:

                               break;
                            case 4:
                                 break;
                            case 5:
                                 break;
                            case 6:
                                break;
                            case 7:
                               break;
                        }


                    }
                }));


    }


//Busca en webservice los resultados de sitios disponibles Primera accion
    public void ConsultarSitios() {
        String parametros = "?nombre="+""+"&longitud="+"" +"&latitud="+"" +"&direccion="+""+ "&estatus="+"" + "&idEstablecimiento="+0+"&idsitio="+0;
        que = "1";
        enviarDatos("buscarSitioMovil", parametros);
    }
    //Busca en webservice los resultados de sitios disponibles
    public void ConsultarEstablecimientos() {
        String parametros = "?idTipoEstablecimiento="+0+"&nombre="+"" +"&id="+0;
        que="2";
            enviarDatos("buscarEstablecimientoMovil", parametros);
    }
    //Consulta en el webservices
    public void enviarDatos(String Accion , String Parametros)
    {
        String strURL ="http://www.ximbal.somee.com/Services/ximbalMovil.asmx/";
        String UrlWebService = strURL+Accion+ Parametros;
        if (que.equals("1")) {
            new JSONTask().execute(UrlWebService);
        }
        else
        {
            new JSONTask().execute(UrlWebService);
            progressDialog = new ProgressDialog (MainActivity.this);
            progressDialog.setTitle("Buscando información");
            progressDialog.setMessage("Por favor espere...");
            progressDialog.show();
        }

    }

    //LLenarArray
    public  void LlenarArray(String Datos, JSONArray ResultadoArray)
    {
        try {
            ResultadoArray = new JSONArray(Datos);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //Despues de llenar los arrays hay que dividirlos en categorias que son las
    //que se mostraran en los Booombotons Por eso vamos a dividirlos dependiendo su id
    public void DividirCategorias()
    {
        String d1 = "" ,  d2 = "" ,  d3 = "",   d4 = "",  d5 = "",  d6 = "";

        Datos[0] = d1;
        Datos[1] = d1;
        Datos[2] = d1;
        Datos[3] = d1;
        Datos[4] = d1;
        Datos[5] = d1;

        for (int i = 0 ; i < ResultadosEnArraySitios.length(); i++) {
            JSONObject ObjetoSitio = null;
            try {
                ObjetoSitio = ResultadosEnArraySitios.getJSONObject(i);
                for (int j = 0 ; j < ResultadosEnArraySitios.length(); j++)
                {
                    //divide las categorias por nombre (Se puede llenar un resultado con varios tipos de sitios)
                    int id = ObjetoSitio.getInt("IdEstablecimiento");
                    if (id == 1)
                    {
                      Datos[1] +=    ResultadosEnArraySitios.get(i).toString();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        //Llena los respectivos arrays
       for (int a = 0; a < arreglo.length; a++)
       {
           if (!Datos[a].equals("")) {
               LlenarArray((String) Datos[a], (JSONArray) arreglo[a]);
           }
       }

    }
    public  void IrActivityMap(int boom)
    {
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        //  i.putExtra("ResultadosEnArray",Resultado);
        intent.putExtra("ResultadosEnArray", (String)Datos[boom]);
    }



    public class JSONTask extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... Parametros)
        {
            //que = Parametros[1];
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
                String Line=""; //Lee linea por linea lo que devuelve el web service
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
         //       llenarArrayList(Resultado);
                Log.e("Salida", Resultado);

              //  if (que.equals("2"))
               // {
                    ResultadosEnArrayEstablecimientos= new JSONArray(Resultado);
                    Toast.makeText(MainActivity.this, Resultado,Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

               // }
               // else if (que.equals("1")) {
               //      ResultadosEnArraySitios= new JSONArray(Resultado);
               //      Toast.makeText(MainActivity.this, Resultado,Toast.LENGTH_LONG).show();
               //  }

              //  Toast.makeText(MainActivity.this, Resultado,Toast.LENGTH_LONG).show();
               // Toast.makeText(MainActivity.this, Resultado,Toast.LENGTH_SHORT).show();
                //Se mandan esos datos a otra actividad lista en formato string
               // Intent i = new Intent(MainActivity.this, MapsActivity.class);
              //  i.putExtra("ResultadosEnArray",Resultado);
             //   startActivity(i);
            }catch (Throwable t){
                Log.e("Falla",t.toString());
            }
        }


    }

/*    public class JSONTask2 extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... Parametros)
        {
            //que = Parametros[1];
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
                String Line=""; //Lee linea por linea lo que devuelve el web service
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
                //       llenarArrayList(Resultado);
                Log.e("Salida", Resultado);


                LlenarArray(Resultado, ResultadosEnArrayEstablecimientos);
                //Toast.makeText(MainActivity.this, Resultado,Toast.LENGTH_LONG).show();


            }catch (Throwable t){
                Log.e("Falla",t.toString());
            }
        }


    }*/
}
