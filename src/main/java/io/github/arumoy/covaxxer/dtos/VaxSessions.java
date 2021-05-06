package io.github.arumoy.covaxxer.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VaxSessions {
    private String session_id;
    private String date;
    private Double available_capacity;
    private Integer min_age_limit;
    private String vaccine;
    private List<String> slots;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(date).append(" for ").append(available_capacity);
        slots.forEach(slot -> builder.append(slot).append(','));
        return builder.toString();
    }
}
