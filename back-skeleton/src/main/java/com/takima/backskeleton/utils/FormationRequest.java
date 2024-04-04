package com.takima.backskeleton.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class FormationRequest {
    private String diplome;
    private String domaineEtudes;
    private String ecole;
    private String lieu;
    private LocalDate anneeDebut;
    private LocalDate anneeFin;
}
