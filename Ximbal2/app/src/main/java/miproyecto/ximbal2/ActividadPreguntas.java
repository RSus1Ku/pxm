package miproyecto.ximbal2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Random;

public class ActividadPreguntas extends AppCompatActivity {

    String[] Saludos = new String[3];
    private String[] arraySpinner;


    int c = 0;
    int index = 0;
    TextView label;
    Button btnYes;
    Button btnNo;
    Spinner spOpciones;
    Intent intent;
    String ResultadoConsulta;
    boolean dia, tarde, noche;
    boolean RecDesayuno, RecSitiosCulturales, RecAlmuerzo, RecCena,RecNoche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_preguntas);

        label = (TextView) findViewById(R.id.lblPreg);
        btnYes = (Button) findViewById(R.id.btnYes);
        Button btnInicio;
        btnNo = (Button) findViewById(R.id.btnNo);
        intent = new Intent(ActividadPreguntas.this, MapsActivity.class);
        definirHorarios();
        Saludos[0] = "Hola ¿Todo bien?";
        Saludos[1] = "Saludos ¿te puedo ayudar en algo?";

        llenarSpiner();
        saludar();

    }

    private void llenarSpiner() {
        this.arraySpinner = new String[] {
                "Paseo", "Comer", "Eventos"
        };
        spOpciones = (Spinner) findViewById(R.id.spOpciones);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arraySpinner);
        spOpciones.setAdapter(adapter);

    }

    private void definirHorarios() {
        Date fecha = new Date();

        if (fecha.getHours() >= 8  && fecha.getHours() <= 11.59) {
            RecDesayuno = true;
            dia = true;
        }
        else if (fecha.getHours() >= 12 &&  fecha.getHours() <= 14.59){
            RecSitiosCulturales = true;
            dia = true;
        }
        else if (fecha.getHours() >= 15 &&  fecha.getHours() <= 17.59){
            RecAlmuerzo = true;
            if(fecha.getHours() <= 5) {
                dia = true;
            }
            else
            {
                tarde = true;
            }
        }
        else if (fecha.getHours() >= 18 &&  fecha.getHours() <= 20.59){
            RecSitiosCulturales = true;
            if(fecha.getHours() <= 6) {
                tarde = true;
            }
            else
            {
                noche = true;
            }
        }
        else if (fecha.getHours() >= 21 &&  fecha.getHours() <= 23.58){
            RecCena = true;
            noche = true;
        }
        else if (fecha.getHours() >= 23.59 &&  fecha.getHours() <= 7.59){
            RecNoche = true;
            noche = true;
        }

    }


    private void saludar() {
        Random r = new Random();
        int rango = Saludos.length;
        int randonNum = r.nextInt(rango);
        if (randonNum == 2)
        {
            if (dia) {
                Saludos[2] = "buenos dias";
            }
            if (tarde) {
                Saludos[2] = "buenas tardes";
            }
            if (noche) {
            Saludos[2] = "buenas noches";
            }
        }
        label.setText(Saludos[randonNum]);
    }



}
