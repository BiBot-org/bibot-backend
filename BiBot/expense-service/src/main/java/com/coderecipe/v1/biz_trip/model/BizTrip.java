package com.coderecipe.v1.biz_trip.model;

import com.coderecipe.v1.biz_trip.dto.BizTripDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "Biz_trip")
public class BizTrip {

    @Id
    @Column(name = "biz_trip_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "date")
    private Date date;

    @Column(name = "amount")
    private int amount;

    public static BizTrip of(BizTripDTO dto) {
        return BizTrip.builder()
            .id(dto.getId())
            .userId(dto.getUserId())
            .date(dto.getDate())
            .amount(dto.getAmount())
            .build();
    }

}
