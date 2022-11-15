
package com.formation.velo.service.impl;

import com.formation.velo.model.Park;
import com.formation.velo.api.data.OpenData;
import com.formation.velo.api.data.park.OpenDataNantesParkClient;
import com.formation.velo.api.data.park.OpenDataParkingNantes;
import com.formation.velo.repository.ParkRepository;
import com.formation.velo.service.ParkService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.util.Arrays;

import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.Call;


@Service

public class ParkServiceImpl implements ParkService {

    private final ParkRepository parkRepository;

    public ParkServiceImpl(ParkRepository repository) {
        this.parkRepository = repository;
    }

    @Override
    public List<Park> findAll() {
        return parkRepository.findAll();
    }

    @Override
    public Optional<Park> findById(Integer id) {
        return parkRepository.findById(id);
    }

    @Override
    public Park save(Park park) {
        return parkRepository.save(park);
    }

    @Override
    public void deleteById(Integer id) {
        parkRepository.deleteById(id);
    }

    @Override
    public void delete(Park park) {
        parkRepository.delete(park);
    }
    @Override
    public void getRecords() {
        //appel api
        String baseUrl = "https://data.nantesmetropole.fr/";

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();

        OpenDataNantesParkClient client = retrofit.create(OpenDataNantesParkClient.class);

        Call<OpenDataParkingNantes> openDataParkingNantesCall = client.getRecords();

        try {

            OpenDataParkingNantes openDataParkingNantes = openDataParkingNantesCall.execute().body();
            System.out.printf("Park to create or update" + openDataParkingNantes.getRecords().length);

                       

            //Save records dans park
            
            Arrays.stream(openDataParkingNantes.getRecords()).forEach(record -> {
                Optional<Park> parkToUpdate = findByRecordId(record.getRecordId());
                if(parkToUpdate.isPresent()){
                    parkToUpdate.get()
                    .setGrpDisponible(record.getField().getGrpDisponible());
                    parkToUpdate.get()
                    .setGrpIdentifiant(record.getField().getGrpIdentifiant());
                    parkToUpdate.get()
                    .setGrpNom(record.getField().getGrpNom());
                    save(parkToUpdate.get());
                }else {
                    // on crée la park
                    Park newPark = Park.builder()
                            .recordId(record.getRecordId())
                            .grpDisponible(record.getField().getGrpDisponible())
                            .grpIdentifiant(record.getField().getGrpIdentifiant())
                            .grpNom(record.getField().getGrpNom())
                            .latitude(record.getField().getLocation()[0])
                            .longitude(record.getField().getLocation()[1])
                            .build();
                    // on save
                    save(newPark);
                }
            });
        } catch(IOException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public Optional<Park> findByRecordId(String recordId) {
        return parkRepository.findByRecordId(recordId);
    }

}