package miproyecto.ximbal2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by joseph on 20/03/2017.
 */
public class CustomAdapterComentarios extends RecyclerView.Adapter<CustomAdapterComentarios.ViewHolder> {
    private Context context;
    private List<MisComentarios> miData;

    public CustomAdapterComentarios(Context context, List<MisComentarios> miData) {
        this.context = context;
        this.miData = miData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ///Infla el cardView
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comentarioview, parent, false);
        return new  ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Se añaden los valores al cardView
        holder.titulo.setText(miData.get(position).getNombreUsuario());
        holder.Comentario.setText(miData.get(position).getComentario());

        //Descarga la imagen del link y la pone en el cardview
        //Ronald si ves este mensaje posiblemente me encuetre muerto
        //ok no le puse el https ´por que se añade añ link pero no en todas las fotos funciona depende de donde se descarguen
        Picasso.with(context).load( "https://cdn.pixabay.com/photo/2012/04/26/19/43/profile-42914_960_720.png").into(holder.imagen);

        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Hola",Toast.LENGTH_SHORT).show();


            }
        });
    }

    @Override
    public int getItemCount() {
    return  miData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo;
        public ImageView imagen;
        public TextView Comentario;


        public ViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.txtUsuario);
            imagen = (ImageView) itemView.findViewById(R.id.ivPerfil);
            Comentario = (TextView) itemView.findViewById(R.id.txtComentario);


        }
    }
}

