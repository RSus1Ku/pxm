package miproyecto.ximbal2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import tarjeta.MisDatos;
import tarjeta.MisDatosNew;

/**
 * Created by joseph on 15/03/2017.
 */
public class CustomAdapterPantallafull extends RecyclerView.Adapter<CustomAdapterPantallafull.ViewHolder>{
    private Context context;
    private List<MisDatosNew> miData;

    public CustomAdapterPantallafull(Context context, List<MisDatosNew> miData) {
        this.context = context;
        this.miData = miData;
    }

    @Override
    public CustomAdapterPantallafull.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ///Infla el cardView
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewfullscreen, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomAdapterPantallafull.ViewHolder holder, int position) {
        //Se añaden los valores al cardView
        //holder.titulo.setText(miData.get(position).getFotos());
        //Descarga la imagen del link y la pone en el cardview
        //Ronald si ves este mensaje posiblemente me encuetre muerto
        //ok no le puse el https ´por que se añade añ link pero no en todas las fotos funciona depende de donde se descarguen
         Picasso.with(context).load("https://" + miData.get(position).getFotos()).into(holder.imagen);

        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Hola",Toast.LENGTH_SHORT).show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return miData.size();
    }
    //Se obtienen los componentes del CardView para poder modificarlos

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView titulo;
            public ImageView imagen;

            public ViewHolder(View itemView) {
                super(itemView);
                titulo = (TextView) itemView.findViewById(R.id.txtTitulo);
                imagen = (ImageView) itemView.findViewById(R.id.ivLuga);

            }
        }
    }

