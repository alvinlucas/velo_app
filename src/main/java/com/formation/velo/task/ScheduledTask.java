package com.formation.velo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.formation.velo.service.ParkService;
import com.formation.velo.service.StationService;

import lombok.extern.java.Log;

@Component
@Log
public class ScheduledTask {
    
    private final StationService stationService;
    private final ParkService parkService;

    public ScheduledTask(StationService stationService,ParkService parkService){
        this.stationService = stationService;
        this.parkService = parkService;
        
    }

    @Scheduled(fixedRate = 60000)
    public void searchNextMatchByCompetition(){
        log.info("update stations 😉 ");
        stationService.saveRecords();
        parkService.getRecords();
    }

}
