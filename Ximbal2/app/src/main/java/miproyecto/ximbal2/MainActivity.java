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
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ximbalDAO.Connexion;

public class MainActivity extends AppCompatActivity {
    private TextView textViewForAnimation;
    private TextView textViewForButton;
    private BoomMenuButton bmb;
    JSONArray ResultadosEnArray = null;
    private List<String> nombres = new ArrayList<>();
    private List<Integer> id = new ArrayList<>();
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);



        textViewForButton = (TextView) findViewById(R.id.p1);
        textViewForAnimation = (TextView) findViewById(R.id.p2);

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
                               enviarDatos(1);
                                break;
                            case 2:
                                enviarDatos(2);
                                break;
                            case 3:
                                enviarDatos(3);
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

    public void BuscarTipoEstablecimiento(int tipo)
    {
        String strAccion = "buscarEstablecimientoMovil";
        String strURL ="http://www.ximbal.somee.com/Services/ximbalMovil.asmx/";
        String UrlWebService = strURL+strAccion+"?idTipoEstablecimiento="+0+"&nombre="+"" +"&id="+0;
        new JSONTask().execute(UrlWebService);

        progressDialog = new ProgressDialog (MainActivity.this);
        progressDialog.setTitle("Buscando información");
        progressDialog.setMessage("Por favor espere...");
        progressDialog.show();
    }
    public void enviarDatos(int tipo)
    {
        String strAccion = "buscarSitioMovil";
        String strURL ="http://www.ximbal.somee.com/Services/ximbalMovil.asmx/";
        String UrlWebService = strURL+strAccion+"?nombre="+""+"&longitud="+"" +"&latitud="+"" +"&direccion="+""+ "&estatus="+"" + "&idEstablecimiento="+tipo+"&idsitio="+0;
        new JSONTask().execute(UrlWebService);

        progressDialog = new ProgressDialog (MainActivity.this);
        progressDialog.setTitle("Buscando información");
        progressDialog.setMessage("Por favor espere...");
        progressDialog.show();
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
                llenarArrayList(Resultado);
                Log.e("Salida", Resultado);
               // Toast.makeText(MainActivity.this, Resultado,Toast.LENGTH_SHORT).show();
                //Se mandan esos datos a otra actividad lista en formato string
               // Intent i = new Intent(MainActivity.this, MapsActivity.class);
              //  i.putExtra("ResultadosEnArray",Resultado);
             //   startActivity(i);
            }catch (Throwable t){
                Log.e("Falla",t.toString());
            }
            progressDialog.dismiss();
        }

        public void llenarArrayList( String Datos)
        {
            try {
                ResultadosEnArray = new JSONArray(Datos);
                for (int i = 0; i < ResultadosEnArray.length(); i++) {
                    JSONObject Objeto = ResultadosEnArray.getJSONObject(i);
                    nombres.add(Objeto.getString("Nombre"));
                    id.add(Objeto.getInt("IdEstablecimiento"));
                }
            } catch (Exception e) {
            }

        }
    }
}
