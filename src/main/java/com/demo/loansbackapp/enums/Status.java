package com.demo.loansbackapp.enums;


import lombok.Getter;

@Getter
public enum Status {

    OPEN("Deschis"),
    IN_PROGRESS("Activ"),
    PAID("Achitat"),
    CANCELED("Respins");

    Status(String name) {
        this.name = name;
    }
    private String name;
}
