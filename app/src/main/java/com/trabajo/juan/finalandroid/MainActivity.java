package com.trabajo.juan.finalandroid;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.trabajo.juan.finalandroid.api.ApiService;
import com.trabajo.juan.finalandroid.modelo.Casa;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private static final String TAG = "CASA";
    private RecyclerView recyclerView;
    private int offset;
    private boolean aptoParaCargar;
    private ListaCasaAdapter listaCasaAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {





        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listaCasaAdapter = new ListaCasaAdapter(this);
        recyclerView.setAdapter(listaCasaAdapter);

        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (aptoParaCargar) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, " Llegamos al final.");

                            aptoParaCargar = false;
                            offset += 5;
                            obtenerLista();
                        }
                    }
                }
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.datos.gov.co/resource/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        aptoParaCargar = true;
        offset = 0;
        obtenerLista();




    }

    private void obtenerLista() {

        ApiService service = retrofit.create(ApiService.class);
        Call<ArrayList<Casa>> casaRespuestaCall = service.obtenerListaCasa();

        casaRespuestaCall.enqueue(new Callback<ArrayList<Casa>>() {
            @Override
            public void onResponse(Call<ArrayList<Casa>> call, Response<ArrayList<Casa>> response) {
                if(response.isSuccessful()){
                   ArrayList lista = response.body();
                   listaCasaAdapter.adicionarListaCasa(lista);

                }
                else
                {
                    Log.e(TAG, "onResponse: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Casa>> call, Throwable t) {
                Log.e(TAG," onFailure: "+t.getMessage());
            }
        });

    }

    public void acercade(View view) {
        Intent i = new Intent(this, acerca_de.class);
        startActivity(i);
    }


}
