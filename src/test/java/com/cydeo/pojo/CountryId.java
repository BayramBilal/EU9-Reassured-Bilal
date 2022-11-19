package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonIgnoreProperties (ignoreUnknown = true)
public class CountryId {
    @JsonProperty ("country_id")
    private String countryId;

    @JsonProperty ("country_name")
    private String countryName;

}
