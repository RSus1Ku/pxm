package tarjeta;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import miproyecto.ximbal2.PantallaCompleta;
import miproyecto.ximbal2.descripcionSitio;

import miproyecto.ximbal2.R;

/**
 * Created by joseph on 14/03/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private Context context;
    private List<MisDatos> miData;


    public CustomAdapter(Context context, List<MisDatos> miData) {
        this.context = context;
        this.miData = miData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       ///Infla el cardView
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.titulo.setText(miData.get(position).getNombre());

        //Descarga la imagen del link y la pone en el cardview

             Picasso.with(context).load("https://" + miData.get(position).getImg_link()).into(holder.imageView);




            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context,holder.titulo.getText().toString(),Toast.LENGTH_SHORT).show();

                    context.startActivity(new Intent(context, descripcionSitio.class)
                            .putExtra("id",  miData.get(position).getId())
                            .putExtra("nombre", miData.get(position).getNombre())
                            .putExtra("descripcion", miData.get(position).getDescripcion())
                            .putExtra("latitud", miData.get(position).getLatitud())
                            .putExtra("longitud", miData.get(position).getLongitud())
                            .putExtra("nombreEs", miData.get(position).getNombreEstablecimiento())
                            .putExtra("fotos", miData.get(position).getFotos())
                            .putExtra("idSitio", miData.get(position).getIdSitio())
                            .putExtra("infoSitio", miData.get(position).getInfo())
                    );
                }
            });

    }

    @Override
    public int getItemCount() {
        return miData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo;
        public ImageView imageView;

        public ViewHolder(View itemView)
        {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.txtTitulo);
            imageView = (ImageView) itemView.findViewById(R.id.ivLugar);
        }
    }
}
