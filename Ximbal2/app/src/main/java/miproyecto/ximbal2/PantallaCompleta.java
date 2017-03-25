package miproyecto.ximbal2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

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
import tarjeta.MisDatosNew;

public class PantallaCompleta extends AppCompatActivity {
    private List<MisDatosNew> listaDatos;
    private List<MisComentarios> listacomentarios;
    private CustomAdapterComentarios adapterComentarios;
    private CustomAdapterPantallafull adapter;
    private RecyclerView recyclerView;

    ImageView img ;
    JSONArray ResultadosEnArray = null;
    MisDatos datos;
    private int id,idSitio;
    private String nombre, infoSitio;
    private String descripcion;
    private String Direccion;
    private String Latitud;
    private String Longitud;
    private String nombreEstablecimiento;
    private String img_link;
    String fotos;

    ImageView ivPerfil;
    TextView txtNumFav ,txtTitulo, txtDescripcion , txtPublicado, txtComentario  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_completa);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        img_link = intent.getStringExtra("foto");
        nombre = intent.getStringExtra("nombre");
        descripcion = intent.getStringExtra("descripcion");
        Latitud= intent.getStringExtra("latitud");
        Longitud =  intent.getStringExtra("longitud");
        nombreEstablecimiento = intent.getStringExtra("nombreEs");
        fotos = intent.getStringExtra("fotos");
        idSitio = intent.getIntExtra("idSitio",0);
        infoSitio = intent.getStringExtra("infoSitio");

        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtNumFav = (TextView) findViewById(R.id.no_of_time_fav);
        txtComentario = (TextView) findViewById(R.id.no_of_comment);
        txtDescripcion = (TextView) findViewById(R.id.txtDescripcion);
        txtPublicado = (TextView) findViewById(R.id.txtPublicado);
        ivPerfil = (ImageView) findViewById(R.id.ivPerfil);


        txtTitulo.setText(nombre);
        txtDescripcion.setText(descripcion);
        recyclerView = (RecyclerView) findViewById(R.id.recycler2);
        recyclerView.setHasFixedSize(false);
        listaDatos = new ArrayList<>();

        try {
            JSONArray array = new JSONArray(fotos);

            for (int i = 0; i < array.length(); i++) {

                JSONObject object = array.getJSONObject(i);
                String foto = "";
                if ( object.getInt("IdSitio") == idSitio)
                {
                    foto = object.getString("Foto");
                }

                MisDatosNew datos = new MisDatosNew(

                        foto);

                listaDatos.add(datos);
            }
        }catch (Exception e){

        }

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(PantallaCompleta.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);

        adapter = new CustomAdapterPantallafull(PantallaCompleta.this, listaDatos);
        recyclerView.setAdapter(adapter);





        ///Comentarios
        recyclerView = (RecyclerView) findViewById(R.id.recyclerCometario);
        recyclerView.setHasFixedSize(false);
        listacomentarios = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(infoSitio);

            for (int i = 0; i < array.length(); i++) {

                JSONObject object = array.getJSONObject(i);
                String cometario = "";
                String Usuario = "";
                String FotoUsuario = "";

                int ididi =  object.getInt("idSitio");
                if ( ididi == idSitio)
                {
                    cometario = object.getString("comentario");
                    Usuario = object.getString("nombreUsuario");
                    FotoUsuario = object.getString("FotoUsuario");

                }

                MisComentarios datos = new MisComentarios(

                        cometario,
                        FotoUsuario,
                        Usuario,
                        "",
                        0

                );

                listacomentarios.add(datos);
            }
        }catch (Exception e){

        }

        LinearLayoutManager comentarios
                = new LinearLayoutManager(PantallaCompleta.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(comentarios);

        adapterComentarios = new CustomAdapterComentarios (PantallaCompleta.this, listacomentarios);
        recyclerView.setAdapter(adapterComentarios);

    }




}