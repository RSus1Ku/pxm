package miproyecto.ximbal2;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.annotation.DrawableRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.fitness.data.Value;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ximbalBO.DirectionFinder;
import ximbalBO.DirectionFinderListener;
import ximbalBO.Rutas;
import ximbalBO.ubicacion;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, DirectionFinderListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener, View.OnClickListener {
    public static final int PERMISO = 0;
    private GoogleMap mMap;
    private Button btnFindPath;
    private Button btnIr;
    private EditText etOrigin;
    private EditText etDestination;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private List<String> nombres = new ArrayList<>();
    private List<String> descripcion = new ArrayList<>();
    private List<String> direccion = new ArrayList<>();
    int indice;
    GoogleMap googleMap;
    private List<LatLng> Coordenadas = new ArrayList<>();
    JSONArray ResultadosEnArray = null;

    private ProgressDialog progressDialog;
    private int numeroSitios;
    private View popup = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Intent intent = getIntent();
        String Datos = intent.getStringExtra("ResultadosEnArray");



        try {
            ResultadosEnArray = new JSONArray(Datos);
            for (int i = 0; i < ResultadosEnArray.length(); i++) {
                JSONObject Objeto = ResultadosEnArray.getJSONObject(i);
                nombres.add(Objeto.getString("Nombre"));
                descripcion.add(Objeto.getString("Descripcion"));
                direccion.add(Objeto.getString("Direccion"));
                String[] latLng = Objeto.getString("Latitud").split(",");
                String[] latLng2 = Objeto.getString("Longitud").split(",");

                double latitude = Double.parseDouble(latLng[0]);
                double longitude = Double.parseDouble(latLng2[0]);
                LatLng location = new LatLng(latitude, longitude);
                Coordenadas.add(location);

            }
        } catch (Exception e) {
        }


                // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnFindPath = (Button) findViewById(R.id.btnFindPath);
        etDestination = (EditText) findViewById(R.id.etDestination);
        btnFindPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest(etDestination.getText().toString());
            }
        });

    }


    //comprobación de campos nullos (1)
    private void sendRequest(String destino) {
        ubicacion ub = new ubicacion(this);
        String origin = String.valueOf(ub.getLocation().longitude) + ","  + String.valueOf(ub.getLocation().latitude);
        String destination ;
        if(etDestination.getText().toString() != "") {
            destination = etDestination.getText().toString();
        }
        else
        {
            destination = destino;
        }
        if (origin.isEmpty()) {
            Toast.makeText(this, "Ingresa el origen!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (destination.isEmpty()) {
            Toast.makeText(this, "Ingresa el destino!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            new DirectionFinder(MapsActivity.this, origin, destination).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    //Permisos
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ubicacion ub = new ubicacion(this);

        LatLng miUbicacion = new LatLng(ub.getLocation().longitude, ub.getLocation().latitude
        ) ;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(miUbicacion, 18));
        originMarkers.add(mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.asistente))
                .title("ubicacion")
                .draggable(true)
                .position(miUbicacion)));

        for (int i = 0; i < ResultadosEnArray.length(); i++) {
            LatLng h = new LatLng(Coordenadas.get(i).longitude, Coordenadas.get(i).latitude);
            destinationMarkers.add(mMap.addMarker(new MarkerOptions()

                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker))
                    .title(nombres.get(i))
                    .position(h)));

            btnFindPath.setOnClickListener(this );
        }
//Marker acciones
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISO  );
        }

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter()
        {
            @Override
            public  View getInfoWindow (Marker marker)
            {
                return null;
            }
            @Override
            public  View getInfoContents (final Marker marker)
            {
                if ((popup == null)) {

                    popup = getLayoutInflater().inflate(R.layout.popupsmaps, null);

                }

                llenarInfoVista(marker);


                return (popup);
            }
        });


    }

    public  void llenarInfoVista( Marker marker )
    {
        TextView tvTitulo = (TextView) popup.findViewById(R.id.txtTitulo);
        final TextView tvDireccion = (TextView) popup.findViewById(R.id.txtDirecccion);
        ImageView ivImagen = (ImageView) popup.findViewById(R.id.imgPop);
        TextView tvDescripcion= (TextView)popup.findViewById(R.id.txtDescripcion);

        tvTitulo.setText("Mi ubicación");
        tvDescripcion.setText("");
        tvDireccion.setText("Aquí");
        ivImagen.setImageResource(R.drawable.asistente);

        boolean encontrado = false;
        String busqueda = marker.getTitle().toString().toLowerCase();
        for (int i = 0; i < nombres.size(); i ++ ) {
            String comparacion = nombres.get(i).toLowerCase();
            if (busqueda.equals(comparacion) ){
                tvTitulo.setText(nombres.get(i));
                tvDescripcion.setText(descripcion.get(i));
                tvDireccion.setText(direccion.get(i));
                buscarImagen(ivImagen, "https://media-cdn.tripadvisor.com/media/photo-s/02/1c/fe/97/getlstd-property-photo.jpg");
                encontrado = true;
            }
            if (encontrado == true)
            {
                break;
            }

        }
    }

    public  void buscarImagen(ImageView img, String url)
    {
        URL imageUrl = null;
        HttpURLConnection conn = null;

        try {

            imageUrl = new URL(url);
            conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2; // el factor de escala a minimizar la imagen, siempre es potencia de 2

            Bitmap imagen = BitmapFactory.decodeStream(conn.getInputStream(), new Rect(0, 0, 0, 0), options);
            img.setImageBitmap (imagen);

        } catch (Exception e) {

            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();

        }
    }


    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(this, "Espere por favor.",
                "Creando ruta..!", true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline:polylinePaths ) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Rutas> routes) {
        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (Rutas route : routes) {
           // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16));
            efectoMover(mMap , route.startLocation );
            ((TextView) findViewById(R.id.tvDuration)).setText(route.duration.text);
            ((TextView) findViewById(R.id.tvDistance)).setText(route.distancia.text);

                originMarkers.add(mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.asistente))
                        .title(route.startAddress)
                        .position(route.startLocation)));

            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker))
                    .title(route.endAddress)
                    .position(route.endLocation)
                    .anchor(0.5f,0.5f)
                   // .rotation(90.0f)
            ));

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLUE).
                    width(10);

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));
            etDestination.setText("");



        }
    }
    public void efectoMover(GoogleMap mMap, LatLng lugar)
    {
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10),2000,null );
        //Constructor de la camara
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(lugar)
                .zoom(17)
                .bearing(30)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        String Long = String.valueOf(marker.getPosition().latitude);
        String Lat = String.valueOf(marker.getPosition().longitude);

        etDestination.setText(Long.toString()+","+Lat.toString());
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        String destino = String.valueOf(marker.getPosition().longitude) + "," + String.valueOf(marker.getPosition().latitude);
        sendRequest(destino);
    }

    public void onClick(View v) {

    }
}
