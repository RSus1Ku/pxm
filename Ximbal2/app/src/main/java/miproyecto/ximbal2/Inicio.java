package miproyecto.ximbal2;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
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
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tarjeta.CustomAdapter;
import tarjeta.MisDatos;
import ximbalBO.ubicacion;
import ximbalDAO.Connexion;

public class Inicio extends Activity {
    private TextView textViewForAnimation;
    private TextView textViewForButton;
    private BoomMenuButton bmb;
    private List<Integer> id = new ArrayList<>();
    private ProgressDialog progressDialogo;
    String EnNotificacion , Ubicacion= "1", UbicacionAnterior= "1", ResultadoConsulta , Topsitios;
    int distanciaUbicacionAnterior = 0, distancia;
    List<Integer> distancias = new ArrayList<>();
    //Inicio Cardviews instancias
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private List<MisDatos> listaDatos;
    JSONArray ResultadosEnArray = null;
    private List<String> nombres = new ArrayList<>();
    private List<String> descripcion = new ArrayList<>();
    private List<String> direccion = new ArrayList<>();
    String DatosFotos;
    private List<String> Coordenadas = new ArrayList<>();
    private List<String> fotos = new ArrayList<>();
    private List<Integer> Idfotos = new ArrayList<>();

    //Fin cardViews instancias
    Connexion connexion ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_inicio);
        Intent intent = getIntent();
         DatosFotos = intent.getStringExtra("Fotos");

        try {
            ResultadosEnArray = new JSONArray(DatosFotos);
            for (int i = 0; i < ResultadosEnArray.length(); i++) {
                JSONObject Objeto = ResultadosEnArray.getJSONObject(i);
                fotos.add(Objeto.getString("Foto"));
                Idfotos.add(Objeto.getInt("IdSitio"));



            }
        } catch (Exception e) {
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerVista);
        recyclerView.setHasFixedSize(false);

        listaDatos = new ArrayList<>();
        String strURL ="http://ximbalpruebahoy.somee.com/services/ximbalmovil.asmx/";
        String strAccion = "devolverTopSitios";
        String UrlWebService = strURL + strAccion+ "?i="+0;
        new JSONPrueba().execute(UrlWebService);




         strURL ="http://ximbalpruebahoy.somee.com/services/ximbalmovil.asmx/";
         strAccion = "buscarSitioMovil";
         UrlWebService = strURL +  strAccion+"?nombre="+""+"&longitud="+"" +"&latitud="+"" +"&direccion="+""+ "&estatus="+"" + "&idEstablecimiento="+0+"&idsitio="+0;

        new JSON().execute(UrlWebService);


        bmb = (BoomMenuButton) findViewById(R.id.bmbFragment);
        assert bmb != null;
        bmb.setButtonEnum(ButtonEnum.TextInsideCircle);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_7_3);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_7_3);
        bmb.setNormalColor(R.color.accentColor);

        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {

            addBuilder();
        }

        // Use OnBoomListenerAdapter to listen part of methods
        bmb.setOnBoomListener(new OnBoomListenerAdapter() {
            @Override
            public void onBoomWillShow() {
                super.onBoomWillShow();

                // logic here
            }
        });

        //time time = new time();
       // time.execute();


    }

    public  void ProcesoNotificaciones(String texto)
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Vibrator vi = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vi.vibrate(300);
        //cambiar icono
        builder.setSmallIcon(R.drawable.persona);
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("ResultadosEnArray", ResultadoConsulta);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        //cambiar icono
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.asistente));
        builder.setContentTitle("Hay lugares cerca para visitar");
        builder.setContentText(texto);
        builder.setAutoCancel(true);
        builder.setSubText("ver más");
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());


    }
    //Se obtienen todos los datos en webServices
  public  void hacer()
  {
      try {
          ResultadosEnArray = new JSONArray(ResultadoConsulta);
          for (int i = 0; i < ResultadosEnArray.length(); i++) {
              JSONObject Objeto = ResultadosEnArray.getJSONObject(i);
              nombres.add(Objeto.getString("Nombre"));
              descripcion.add(Objeto.getString("Descripcion"));
              direccion.add(Objeto.getString("Direccion"));
              Coordenadas.add(Objeto.getString("Longitud") + "," + Objeto.getString("Latitud") );

          }
      } catch (Exception e) {
      }
  }
    public  void llenarCustom() throws JSONException {
        JSONArray array = new JSONArray(Topsitios);

        for (int i=0; i<array.length(); i++){

            JSONObject object = array.getJSONObject(i);

            String foto = "";
            for( int j = 0 ; j < Idfotos.size() ; j++)
            {
                if(Idfotos.get(j)  == object.getInt("idSitio"))
                {
                   foto = fotos.get(j) ;
                }
            }


            MisDatos datos = new MisDatos(
                    object.getInt("numero"),
                    object.getInt("Idestablecimiento"),
                    object.getString("Nombre"),
                    object.getString("Descripcion"),
                    object.getString("Direccion"),
                    object.getString("Latitud"),
                    object.getString("Longitud"),
                    object.getString("nombreEstablecimiento"),
                    foto,
                    DatosFotos,
                    object.getInt("idSitio"),
                    Topsitios

            );
            listaDatos.add(datos);
        }

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(Inicio.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);

        adapter = new CustomAdapter(Inicio.this, listaDatos);
        recyclerView.setAdapter(adapter);
    }



//hilo para notificaciones
    public void hilo()
    {
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void ejecutar()
    {
        time time = new time();
        time.execute();
    }

    //Valida si el usuario ya se movio de lugar
    public void Ubicar() {
        if ( Ubicacion.equals(UbicacionAnterior )) {
            ubicacion ub = new ubicacion(this);
            Ubicacion = String.valueOf(ub.getLocation().longitude).substring(0, 5) + "," + String.valueOf(ub.getLocation().latitude).substring(0, 6);
        }
        else
        {
            String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="+Ubicacion+"&destinations="+UbicacionAnterior+"&mode=walking&language=fr-FR&avoid=tolls&key=%20AIzaSyCRNIxP3vRzmRUYdhjmLSSrn4yGgHLcW2c";
            new GeoTask(Inicio.this).execute(url);
        }
    }
    public class time extends AsyncTask<Void,Integer,Boolean>
    {

        @Override
        protected Boolean doInBackground(Void... params) {
            for(int i = 1 ; i <= 3; i++)
            {
                hilo();
            }
            return true;
        }
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            ejecutar();
            Ubicar();
            if (! Ubicacion.equals(UbicacionAnterior))
            {
                UbicacionAnterior = Ubicacion;
            }
            //cambiar este parametro indica cuantos metros ya se movio para hacer la busqueda
            if ( distanciaUbicacionAnterior > 15 ) {

                hacer();

                for (int i = 0 ; i < Coordenadas.size(); i ++) {
                    String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + Ubicacion + "&destinations=" + Coordenadas.get(0) + "&mode=walking&language=fr-FR&avoid=tolls&key=%20AIzaSyCRNIxP3vRzmRUYdhjmLSSrn4yGgHLcW2c";
                    new GeoTaska(Inicio.this).execute(url);
                    //Cambiar a menor
                    if (distancia < 5) {
                        EnNotificacion += nombres.get(i) + "\n";
                    }
                }
                ubicacion ub = new ubicacion(Inicio.this);
                Ubicacion = String.valueOf(ub.getLocation().longitude).substring(0, 5) + "," + String.valueOf(ub.getLocation().latitude).substring(0, 6);
                ProcesoNotificaciones(EnNotificacion);
            }

        }
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
    //Creacion del boommenu
    private void addBuilder() {
        bmb.addBuilder(new TextInsideCircleButton.Builder()
                .normalText(BuilderManager.getTextResource())

                .normalImageRes(BuilderManager.getImageResource())
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {

                        if (index == 0) {
                            Intent intent = new Intent(Inicio.this, ActividadPreguntas.class);
                            startActivity(intent);
                        }
                        else if (index == 2) {
                            enviarDesdeButton(0);
                            progressDialogo = new ProgressDialog (Inicio.this);
                            progressDialogo.setTitle("Buscando información");
                            progressDialogo.setMessage("Por favor espere...");
                            progressDialogo.show();

                        }
                        else {
                            progressDialogo = new ProgressDialog (Inicio.this);
                            progressDialogo.setTitle("Buscando información");
                            progressDialogo.setMessage("Por favor espere...");
                            progressDialogo.show();
                            enviarDesdeButton(index);

                        }



                    }
                })
                .pieceColor(Color.TRANSPARENT)
                .imageRect(new Rect(Util.dp2px(20), Util.dp2px(20), Util.dp2px(60), Util.dp2px(50)))
        );
    }
   public void enviarDesdeButton(int index)
{
    String strURL ="http://ximbalpruebahoy.somee.com/services/ximbalmovil.asmx/";
    String strAccion = "buscarSitioMovil";
    String UrlWebService = strURL +  strAccion+"?nombre="+""+"&longitud="+"" +"&latitud="+"" +"&direccion="+""+ "&estatus="+"" + "&idEstablecimiento="+index+"&idsitio="+0;
    new JSONTask().execute(UrlWebService);
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
                Intent i = new Intent(Inicio.this, MapsActivity.class);
                i.putExtra("ResultadosEnArray",Resultado);
                startActivity(i);
            }catch (Throwable t){
                Log.e("Falla",t.toString());
            }
            progressDialogo.dismiss();
        }
    }

    public class JSON extends AsyncTask<String,String,String>
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
               ResultadoConsulta = Resultado;


            }catch (Throwable t){
                Log.e("Falla",t.toString());
            }
        }
    }
    //Busca en el webservice los  datos de los lugares para llenar el cardview
    public class JSONPrueba extends AsyncTask<String,String,String>
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
                Topsitios = Resultado;

                llenarCustom();



            }catch (Throwable t){
                Log.e("Falla",t.toString());
            }
        }
    }

//tarea asyc que revissa la distancia entre puntos para mandar notificaciones
    public class GeoTaska extends AsyncTask<String, Void, String> {
        ProgressDialog pd;
        Context mContext;
        Double duration;

        //constructor is used to get the context.
        public GeoTaska(Context mContext) {
            this.mContext = mContext;

        }
        //This function is executed before before "doInBackground(String...params)" is executed to dispaly the progress dialog
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
         //   pd=new ProgressDialog(mContext);
          //  pd.setMessage("Loading");
          //  pd.setCancelable(false);
          //  pd.show();
        }
        //This function is executed after the execution of "doInBackground(String...params)" to dismiss the dispalyed progress dialog and call "setDouble(Double)" defined in "MainActivity.java"
        @Override
        protected void onPostExecute(String aDouble) {

               // Toast.makeText(mContext, "Error4!Please Try Again wiht proper values", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url=new URL(params[0]);
                HttpURLConnection con= (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                int statuscode=con.getResponseCode();
                if(statuscode==HttpURLConnection.HTTP_OK)
                {
                    BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
                    StringBuilder sb=new StringBuilder();
                    String line=br.readLine();
                    while(line!=null)
                    {
                        sb.append(line);
                        line=br.readLine();
                    }
                    String json=sb.toString();
                    Log.d("JSON",json);
                    JSONObject root=new JSONObject(json);
                    JSONArray array_rows=root.getJSONArray("rows");
                    Log.d("JSON","array_rows:"+array_rows);
                    JSONObject object_rows=array_rows.getJSONObject(0);
                    Log.d("JSON","object_rows:"+object_rows);
                    JSONArray array_elements=object_rows.getJSONArray("elements");
                    Log.d("JSON","array_elements:"+array_elements);
                    JSONObject  object_elements=array_elements.getJSONObject(0);
                    Log.d("JSON","object_elements:"+object_elements);
                    JSONObject object_duration=object_elements.getJSONObject("duration");
                    JSONObject object_distance=object_elements.getJSONObject("distance");

                    Log.d("JSON","object_duration:"+object_duration);
                    distancia = object_distance.getInt("value");

                }
            } catch (MalformedURLException e) {
                Log.d("error", "error1");
            } catch (IOException e) {
                Log.d("error", "error2");
            } catch (JSONException e) {
                Log.d("error","error3");
            }


            return null;
        }
    }


}