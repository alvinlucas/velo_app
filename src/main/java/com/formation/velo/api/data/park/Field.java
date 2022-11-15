package com.formation.velo.api.data.park;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@lombok.Data
public class Field {

    @SerializedName("grp_disponible")
    private int grpDisponible;

    @SerializedName("grp_nom")
    private String grpNom;

    private double[] location;

}
