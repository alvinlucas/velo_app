package com.formation.velo.service.impl;

import com.formation.velo.api.data.OpenData;
import com.formation.velo.api.data.velo.OpenDataNantesClient;
import com.formation.velo.model.Station;
import com.formation.velo.repository.StationRepository;
import com.formation.velo.service.StationService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import org.springframework.stereotype.Service;

import java.util.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    public StationServiceImpl(StationRepository repository) {
        this.stationRepository = repository;
    }
    

    @Override
    public List<Station> findAll() {
        return stationRepository.findAll();
    }

    @Override
    public Optional<Station> findById(Integer id) {
        return stationRepository.findById(id);
    }

    @Override
    public Station save(Station station) {
        return stationRepository.save(station);
    }

    @Override
    public void deleteById(Integer id) {
        stationRepository.deleteById(id);
    }

    @Override
    public void delete(Station station) {
        stationRepository.delete(station);
    }


    @Override
    public void saveRecords() {
        // 1. appel opendate
        String baseUrl = "https://data.nantesmetropole.fr/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();

        OpenDataNantesClient client = retrofit.create(OpenDataNantesClient.class);
        Call<OpenData> openDataVeloNantesCall = client.getRecords();
        try{

            OpenData openDataVeloNantes = openDataVeloNantesCall.execute().body();
            assert openDataVeloNantes != null;
            System.out.printf("Stations to create or update" + openDataVeloNantes.getRecords().length);

            Arrays.stream(openDataVeloNantes.getRecords()).forEach(record ->{
                Optional<Station> stationToUpdate = findByRecordId(record.getRecordId());
                if (stationToUpdate.isPresent()){
                    stationToUpdate.get().setBikeStands(record.getField().getBikeStands());
                    stationToUpdate.get().setAvailableBikeStands(record.getField().getAvailableBikeStands());
                    stationToUpdate.get().setAvailableBikes(record.getField().getAvailableBikeStands());
                    stationToUpdate.get().setStatus(record.getField().getStatus());

                    save(stationToUpdate.get());
                }else{

                    Station newStation = Station.builder()
                    . recordId(record.getRecordId())
                    .name(record.getField().getName())
                    .availableBikes(record.getField().getAvailableBikes())
                    .bikeStands(record.getField().getBikeStands())
                    .availableBikeStands(record.getField().getAvailableBikeStands())
                    .latitude(record.getField().getPosition()[0])
                    .longitude(record.getField().getPosition()[1])
                    .status(record.getField().getStatus())
                    .build();

                    save(newStation);
                }
            });
        // 2. save records dans stations
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        
    }


    @Override
    public Optional<Station> findByRecordId(String recordID) {
        return stationRepository.findByRecordId(recordID);
    }
    
}

