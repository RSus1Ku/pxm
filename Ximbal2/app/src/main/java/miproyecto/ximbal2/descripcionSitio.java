package miproyecto.ximbal2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tarjeta.MisDatos;
import tarjeta.MisDatosNew;

public class descripcionSitio extends Activity {

    private List<MisDatosNew> listaDatos;
    private List<MisComentarios> listacomentarios;
    private CustomAdapterComentarios adapterComentarios;
    private CustomAdapterPantallafull adapter;
    private RecyclerView recyclerView;

    ImageView img;
    JSONArray ResultadosEnArray = null;
    MisDatos datos;
    private int id, idSitio;
    private String nombre, infoSitio;
    private String descripcion;
    private String Direccion;
    private String Latitud;
    private String Longitud;
    private String nombreEstablecimiento;
    private String img_link;
    String fotos;

    ImageView ivPerfil;
    TextView txtNumFav, txtTitulo, txtDescripcion, txtPublicado, txtComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_descripcion_sitio);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(descripcionSitio.this, ActivityComentarios.class);
                intent.putExtra("idSitio", idSitio);

                startActivity(intent);

                Snackbar.make(view, "Mensajes", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton rutas = (FloatingActionButton) findViewById(R.id.ubi);
        rutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        img_link = intent.getStringExtra("foto");
        nombre = intent.getStringExtra("nombre");
        descripcion = intent.getStringExtra("descripcion");
        Latitud = intent.getStringExtra("latitud");
        Longitud = intent.getStringExtra("longitud");
        nombreEstablecimiento = intent.getStringExtra("nombreEs");
        fotos = intent.getStringExtra("fotos");
        idSitio = intent.getIntExtra("idSitio", 0);
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
                if (object.getInt("IdSitio") == idSitio) {
                    foto = object.getString("Foto");
                }

                MisDatosNew datos = new MisDatosNew(

                        foto);

                listaDatos.add(datos);
            }
        } catch (Exception e) {

        }

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(descripcionSitio.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);

        adapter = new CustomAdapterPantallafull(descripcionSitio.this, listaDatos);
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

                int ididi = object.getInt("idSitio");
                if (ididi == idSitio) {
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
        } catch (Exception e) {

        }

        LinearLayoutManager comentarios
                = new LinearLayoutManager(descripcionSitio.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(comentarios);

        adapterComentarios = new CustomAdapterComentarios(descripcionSitio.this, listacomentarios);
        recyclerView.setAdapter(adapterComentarios);

    }


}