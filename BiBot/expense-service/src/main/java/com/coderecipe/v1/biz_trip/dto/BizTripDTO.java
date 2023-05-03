package com.coderecipe.v1.biz_trip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizTripDTO {

    private int id;
    private UUID userId;
    private Date date;
    private int amount;
}
