package me.washcar.wcnc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import me.washcar.wcnc.Status.*;

@Controller
public class StatusReportConroller {

    @GetMapping("ping")
    @ResponseBody
    public Pong pingpong(){
        Pong pong = new Pong();
        pong.setMsg("pong");
        return pong;
    }
}
