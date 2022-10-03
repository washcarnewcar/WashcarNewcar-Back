package me.washcar.wcnc.dto;

import lombok.Getter;

@Getter
public class StatusDto {
    private Long id;

    public StatusDto(Long id) {
        this.id = id;
    }
}
