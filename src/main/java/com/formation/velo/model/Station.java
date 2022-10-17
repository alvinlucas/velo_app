package com.formation.velo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

@Table(name = "stations")

public class Station implements Serializable {

    private static final long serialVersionUID = -7670709104974486420L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    private String adress;

    private String name;

    private Double latitude;

    private Double longitude;

    private String status;

    private int bikeStands;

    private int availableBikes;

    private int availableBikeStands;

    private String recordId;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((adress == null) ? 0 : adress.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
        result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + bikeStands;
        result = prime * result + availableBikes;
        result = prime * result + availableBikeStands;
        result = prime * result + ((recordId == null) ? 0 : recordId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Station other = (Station) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (adress == null) {
            if (other.adress != null)
                return false;
        } else if (!adress.equals(other.adress))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (latitude == null) {
            if (other.latitude != null)
                return false;
        } else if (!latitude.equals(other.latitude))
            return false;
        if (longitude == null) {
            if (other.longitude != null)
                return false;
        } else if (!longitude.equals(other.longitude))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (bikeStands != other.bikeStands)
            return false;
        if (availableBikes != other.availableBikes)
            return false;
        if (availableBikeStands != other.availableBikeStands)
            return false;
        if (recordId == null) {
            if (other.recordId != null)
                return false;
        } else if (!recordId.equals(other.recordId))
            return false;
        return true;
    }

  



    
}
