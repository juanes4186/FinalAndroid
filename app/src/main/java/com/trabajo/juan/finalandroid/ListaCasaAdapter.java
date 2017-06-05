package com.trabajo.juan.finalandroid;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.trabajo.juan.finalandroid.modelo.Casa;

import java.util.ArrayList;
import retrofit2.Response;
/**
 * Created by juan on 2/06/17.
 */

public class ListaCasaAdapter extends RecyclerView.Adapter<ListaCasaAdapter.ViewHolder> {
    private ArrayList<Casa> dataset;
    private static final String TAG = "CASA";
    private Context context;

    public ListaCasaAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();

    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_casa, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Casa casa = dataset.get(position);

        holder.tipo.setText(casa.getTipo().toString());
        holder.nombre.setText(casa.getNombre().toString());
        holder.direccion.setText(casa.getDireccion().toString());
        holder.departamento.setText(casa.getDepartamento().toString());
        holder.municipio.setText(casa.getMunicipio().toString());


        if(holder.tipo.getText().equals("Casa de Justicia")){

            holder.tipo.setTextColor(Color.BLUE);
            holder.iv1.setImageResource(R.drawable.home);


        }
        else {
            holder.tipo.setTextColor(Color.RED);
            holder.iv1.setImageResource(R.drawable.home2);
        }


    }

    @Override
    public int getItemCount() {

        return dataset.size();
    }

    public void adicionarListaCasa(ArrayList<Casa> listaCasa) {
        dataset.addAll(listaCasa);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView tipo;
        public TextView nombre;
        public TextView direccion;
        public TextView municipio;
        public TextView departamento;

        public ImageView iv1;



        public ViewHolder(View itemView) {
            super(itemView);


            tipo = (TextView) itemView.findViewById(R.id.txt_tipo);
            nombre= (TextView) itemView.findViewById(R.id.txt_nombre);
            direccion=(TextView) itemView.findViewById(R.id.txt_direccion);
            municipio=(TextView) itemView.findViewById(R.id.txt_municipio);
            departamento=(TextView) itemView.findViewById(R.id.txt_departamento);
            iv1=(ImageView) itemView.findViewById(R.id.iv1);



        }




    }




}
