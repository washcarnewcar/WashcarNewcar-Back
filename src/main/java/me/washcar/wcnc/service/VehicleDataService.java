package me.washcar.wcnc.service;

import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.dto.VehicleData;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VehicleDataService {
    //TODO 자동차브랜드리스트-서비스
    public VehicleData.brandListDto brandList(){
        return new VehicleData.brandListDto();
    }
    //TODO 자동차모델리스트-서비스
    public VehicleData.modelListDto modelList(int brandNumber){
        return new VehicleData.modelListDto();
    }
}
