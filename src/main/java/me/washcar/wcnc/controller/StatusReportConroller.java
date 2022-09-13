package me.washcar.wcnc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import me.washcar.wcnc.Status.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusReportConroller {

    @GetMapping("ping")
    public Pong pingpong(){
        Pong pong = new Pong();
        pong.setMsg("pong");
        return pong;
    }
}
