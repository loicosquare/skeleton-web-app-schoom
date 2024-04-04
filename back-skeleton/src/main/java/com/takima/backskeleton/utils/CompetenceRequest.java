package com.takima.backskeleton.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class CompetenceRequest {
    public int niveau;
    public String competence;
}
