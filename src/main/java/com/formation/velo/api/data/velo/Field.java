package com.formation.velo.api.data.velo;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@lombok.Data
public class Field {

    @SerializedName("available_bike_stands")
    private int availableBikeStands;

    @SerializedName("available_bikes")    
    private int availableBikes;

    @SerializedName("bike_stands")

    private int bikeStands;

    private int number;

    private String address;

    private String name;

    private String status;
    
    private double[] position;

    @SerializedName("grp_disponible")
    private int grpDisponible;

    @SerializedName("grp_nom")
    private String grpNom;

    private double[] location;
}
