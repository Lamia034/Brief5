package com.bank.Entity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.*;
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Inheritance
public class Client extends Person{
    @NonNull
    @Id
    @GeneratedValue
    private int code;
    @NonNull
    @ManyToOne
    public Employee employee;
    @NonNull
    @ManyToOne
    private Agency agency;
}
