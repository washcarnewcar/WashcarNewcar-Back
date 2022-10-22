package me.washcar.wcnc.controller.provider;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.provider.ReservationCheck;
import me.washcar.wcnc.service.provider.ReservationCheckService;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ReservationCheckController {

    private final ReservationCheckService reservationCheckService;

    @GetMapping("/provider/{slug}/request")
    public ReservationCheck.listDto list(@PathVariable String slug) {
        return reservationCheckService.list(slug);
    }

    @RequestMapping(value = "/provider/{slug}/request/{reservationNumber}", method = RequestMethod.GET)
    public ReservationCheck.detailDto detail(
            @PathVariable String slug,
            @PathVariable long reservationNumber
    ) {
        return reservationCheckService.detail(slug, reservationNumber);
    }

    @RequestMapping(value = "/provider/{slug}/request/{reservationNumber}", method = RequestMethod.POST)
    public ReservationCheck.responseDto response(
            @PathVariable String slug,
            @PathVariable long reservationNumber,
            @RequestBody String body
    ) {
        return reservationCheckService.response(slug, reservationNumber, body);
    }
}
