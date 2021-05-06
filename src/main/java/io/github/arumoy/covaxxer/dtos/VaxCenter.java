package io.github.arumoy.covaxxer.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VaxCenter {
    private Integer center_id;
    private String name;
    private String address;
    private String pincode;
    private String from;
    private String to;
    private String fee_type;
    private List<VaxFees> vaccine_fees;
    private List<VaxSessions> sessions;
}
