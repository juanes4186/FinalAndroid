package com.trabajo.juan.finalandroid.api;

import com.trabajo.juan.finalandroid.modelo.Casa;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by juan on 2/06/17.
 */

public interface ApiService {
    @GET("bab7-q7ke.json")
    Call<ArrayList<Casa>> obtenerListaCasa();
}
