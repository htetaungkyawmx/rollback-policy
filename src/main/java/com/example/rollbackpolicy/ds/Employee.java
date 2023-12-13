package com.example.rollbackpolicy.ds;

import java.sql.Date;

public record Employee(
        int id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        Date hiredDate,
        double salary
) {

}



