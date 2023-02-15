package fr.kelig.countryclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Country {
    private String countryName;
    private String capitalName;
    private String currency;
}