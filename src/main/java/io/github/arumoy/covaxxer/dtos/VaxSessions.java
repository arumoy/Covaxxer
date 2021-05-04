package io.github.arumoy.covaxxer.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VaxSessions {
    private String session_id;
    private String date;
    private Double available_capacity;
    private Integer min_age_limit;
    private String vaccine;
    private String[] slots;
}
