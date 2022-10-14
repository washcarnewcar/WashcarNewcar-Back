package me.washcar.wcnc.controller;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.ReservationDto;
import me.washcar.wcnc.service.ReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @RequestMapping(value = "/search/{slug}/menu/{menuNumber}", method = RequestMethod.GET)
    public ReservationDto.menuInfo menuInfo(@PathVariable String slug, @PathVariable String menuNumber){
        return reservationService.menuInfo(slug, menuNumber);
    }

    @GetMapping("/search/{slug}/menu/{menuNumber}/available")
    public ReservationDto.menuAvailableDate menuAvailable(@PathVariable String slug, @PathVariable String menuNumber){
        return reservationService.menuAvailableDate(slug, menuNumber);
    }

    @GetMapping("/search/{slug}/menu/{menuNumber}/available/{date}")
    public ReservationDto.menuAvailableTime menuAvailableTime(@PathVariable String slug, @PathVariable String menuNumber, @PathVariable String date){
        return reservationService.menuAvailableTime(slug, menuNumber, date);
    }

    @RequestMapping(value = "/search/{slug}/menu/{menuNumber}", method = RequestMethod.POST)
    public ReservationDto.reservation reservation(@PathVariable String slug, @PathVariable String menuNumber){
        return reservationService.reservation(slug, menuNumber);
    }
}
