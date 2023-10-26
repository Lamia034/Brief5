package com.bank.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Inheritance
public class Employee extends Person{
    @NonNull
    @Id
    @GeneratedValue
    private int registrationNbr;
    @NonNull
    private LocalDate dateOfRecrutment;
    @NonNull
    @OneToMany
    private List<Client> createdClient;
    @NonNull
    @ManyToOne
    private Agency agency;
    @OneToMany
    private List<AgencyEmployee> history;
}
