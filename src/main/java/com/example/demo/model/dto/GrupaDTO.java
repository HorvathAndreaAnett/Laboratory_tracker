package com.example.demo.model.dto;

import com.example.demo.model.Grupa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class GrupaDTO {

    private Integer id;

    private Integer number;

    private Set<UserDTO> userSet;

    public static GrupaDTO fromEntity(Grupa grupa) {
        return GrupaDTO.builder()
                .id(grupa.getId())
                .number(grupa.getNr())
                .build();
    }
}
