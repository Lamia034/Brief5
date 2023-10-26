package com.bank.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Person implements Serializable {
    @NonNull
    protected String firstName;
    @NonNull
    protected String lastName;
    @NonNull
    protected LocalDate birthDay;
    @NonNull
    protected String phone;
    @NonNull
    protected String address;
}