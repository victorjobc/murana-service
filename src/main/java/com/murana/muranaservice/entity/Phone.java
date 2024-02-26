package com.murana.muranaservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String number;
    private String cityCode;
    private String countryCode;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User properUser;

    public Phone(String number, String cityCode, String countryCode){
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }

}