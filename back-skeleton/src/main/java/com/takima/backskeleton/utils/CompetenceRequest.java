package com.takima.backskeleton.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CompetenceRequest {
    public int niveau;
    public String competence;
}
