package com.formation.velo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.formation.velo.model.Station;
import com.formation.velo.service.StationService;


@Controller
@RequestMapping("/api")
public class StationController {

private final StationService stationService;

public StationController(StationService stationService) {
    this.stationService = stationService;
}


@GetMapping("stations")
public ResponseEntity<List<Station>> getAll(){
    List<Station> stations = stationService.findAll();
    stationService.saveRecords();
    return ResponseEntity.ok(stations);
}

@GetMapping("station/{id}")
public ResponseEntity<Optional<Station>> getPersoneById(@PathVariable Integer id){
    Optional<Station> station = stationService.findById(id);
    
    return ResponseEntity.ok(station);
}

@PostMapping("station/add")
public ResponseEntity<Station> add(@RequestParam String address,@RequestParam String name,@RequestParam Double latitude,@RequestParam Double longitude, @RequestParam String status, @RequestParam Integer bike_stands, @RequestParam Integer available_bike_stands, @RequestParam Integer available_bikes, @RequestParam String recordid){

    Station station = stationService.save(Station.builder().address(address).name(name).latitude(latitude).longitude(longitude).status(status).bikeStands(bike_stands).availableBikeStands(available_bike_stands).availableBikes(available_bikes).recordId(recordid).build());
    return ResponseEntity.ok(station);
}



@DeleteMapping("station/delete/{id}")
public ResponseEntity<String> delete(@PathVariable Integer id){
    stationService.deleteById(id);
    return ResponseEntity.ok("deleted");
}

@PostMapping("station/update")
public ResponseEntity<String> update(@RequestBody Station station){
    stationService.save(station);
    return ResponseEntity.ok("updated");
}

}
