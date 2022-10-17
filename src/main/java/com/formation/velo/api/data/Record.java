package com.formation.velo.api.data;

import com.formation.velo.api.data.velo.Field;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Record{

   // private String datasetid;
   @SerializedName("recordid")
    private String recordId;
    @SerializedName("fields")
    private Field field;
 //   private Geometry geometry;
}
