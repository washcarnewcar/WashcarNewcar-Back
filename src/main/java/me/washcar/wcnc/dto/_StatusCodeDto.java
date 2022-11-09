package me.washcar.wcnc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class _StatusCodeDto {
    private int status;
    private String message;
}