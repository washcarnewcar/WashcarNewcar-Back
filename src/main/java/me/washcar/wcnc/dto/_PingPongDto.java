package me.washcar.wcnc.dto;

import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class _PingPongDto {

    private String message;
    private ZonedDateTime zonedDateTime;

    public _PingPongDto() {
        this.message = "pong";
        this.zonedDateTime = ZonedDateTime.now();
    }
}