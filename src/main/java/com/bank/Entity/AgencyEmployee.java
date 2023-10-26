package com.bank.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
public class AgencyEmployee {
    @Id
    @GeneratedValue
    @NonNull
    private long id;
    @NonNull
    @ManyToOne
    private Employee employee;
    @NonNull
    @ManyToOne
    private Agency agency;
    @NonNull
    private LocalDate transfer_date;
}