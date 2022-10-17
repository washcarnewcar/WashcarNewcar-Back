package me.washcar.wcnc.controller;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.Reservation;
import me.washcar.wcnc.dto.VehicleData;
import me.washcar.wcnc.service.VehicleDataService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class VehicleDataController {

    private final VehicleDataService vehicleDataService;

    @GetMapping("/car/brand")
    public VehicleData.brandListDto menuInfo(){
        return vehicleDataService.brandList();
    }

    @GetMapping("/car/brand/{brandNumber}")
    public VehicleData.modelListDto menuInfo(@PathVariable int brandNumber){
        return vehicleDataService.modelList(brandNumber);
    }
}
