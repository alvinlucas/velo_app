package com.formation.velo.api.data.park;


import retrofit2.Call;
import retrofit2.http.GET;

public interface OpenDataNantesParkClient {
    @GET("/api/records/1.0/search/?dataset=244400404_parkings-publics-nantes-disponibilites&q=&facet=grp_nom&facet=grp_statut&rows=126")
    Call<OpenDataParkingNantes> getRecords();
}
 