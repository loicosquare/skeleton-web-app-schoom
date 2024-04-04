package com.takima.backskeleton.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SocialMediaRequest {
    public String type;
    public String lien;
}
