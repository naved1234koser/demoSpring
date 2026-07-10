package com.example.demo.dto;

import com.example.demo.validator.ValidCountryName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {

    @NotBlank(message = "Country name must not be blank")
    @Size(min = 2, max = 100, message = "Country name must be between 2 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Country name must only consist of letters and spaces")
    @ValidCountryName(message = "Country name is not allowed")
    private String countryName;

    @NotBlank(message = "City name must not be blank")
    @Size(min = 2, max = 100, message = "City name must be between 2 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "City name must only consist of letters and spaces")
    private String cityName;
}
