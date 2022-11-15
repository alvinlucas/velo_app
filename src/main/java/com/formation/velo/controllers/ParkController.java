package com.formation.velo.controllers;

import com.formation.velo.model.Park;
import com.formation.velo.service.ParkService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class ParkController {
    private final ParkService parkService;

    public ParkController(ParkService parkService) {
        this.parkService = parkService;
    }

    @GetMapping("parks")
    public ResponseEntity<List<Park>> getAll(){
        parkService.getRecords();
        List<Park> parks = parkService.findAll();

        return ResponseEntity.ok(parks);
    }

    @GetMapping("parks/{id}")
    public ResponseEntity<Optional<Park>> getParkById(@PathVariable Integer id){
        Optional<Park> park = parkService.findById(id);

        return ResponseEntity.ok(park);
    }

    @PostMapping("parks/add")
    public ResponseEntity<Park> add(@RequestParam Integer grpDisponible, @RequestParam String grpIdentifiant, @RequestParam String grpNom, @RequestParam String recordId){

        Park park = parkService.save(Park.builder().grpDisponible(grpDisponible).grpIdentifiant(grpIdentifiant).grpNom(grpNom).recordId(recordId).build());
        return ResponseEntity.ok(park);
    }

    @DeleteMapping("parks/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        parkService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }

    @PostMapping("parks/update")
    public ResponseEntity<String> update(@RequestBody Park park){
        parkService.save(park);
        return ResponseEntity.ok("updated");
    }
}
