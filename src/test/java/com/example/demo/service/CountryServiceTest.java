package com.example.demo.service;

import com.example.demo.dto.CountryDTO;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {

    @Mock
    CountryRepository countryRepository;

    @InjectMocks
    CountryService countryService;

    private Country savedCountry;
    private CountryDTO validDTO;

    @BeforeEach
    void setUp(){
        validDTO = new CountryDTO("India", "Mumbai");

        savedCountry = new Country();
        savedCountry.setCountryId(1L);
        savedCountry.setCountryName("India");
        savedCountry.setCityName("Mumbai");
    }

    @Test
    @DisplayName("addCountry: should save and return country when no duplicate exists")
    void addCountry_WhenNoDuplicatesExists_shouldSaveAndReturnCountry() {
        when(countryRepository.findByCountryNameAndCityName("India", "Mumbai"))
                .thenReturn(Optional.empty());

        when(countryRepository.save(any(Country.class)))
                .thenReturn(savedCountry);

        Country result = countryService.addCountry(validDTO);

        assertThat(result).isNotNull();
        assertThat(result.getCountryName()).isEqualTo("India");
        assertThat(result.getCityName()).isEqualTo("Mumbai");
        assertThat(result.getCountryId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("addCountry: should throw DuplicateResourceException when country+city already exists")
    void addCountry_whenDuplicateExists_shouldThrowDuplicateResourceException(){
        when(countryRepository.findByCountryNameAndCityName("India", "Mumbai"))
                .thenReturn(Optional.of(savedCountry));

        assertThatThrownBy(() -> countryService.addCountry(validDTO))
                .isInstanceOf(DuplicateResourceException.class);

        verify(countryRepository,never()).save(any());
    }

    @Test
    @DisplayName("getById: should return CountryDTO when country exists")
    void getById_whenCountryExists_shouldReturnCountryDTO(){
        when(countryRepository.findById(1L))
                .thenReturn(Optional.of(savedCountry));

        CountryDTO result = countryService.getById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getCountryName()).isEqualTo("India");
        assertThat(result.getCityName()).isEqualTo("Mumbai");
    }

    @Test
    @DisplayName("getById: should return ResourceNotFoundException when country does not exists")
    void getById_whenCountryDoesNotExists_shouldThrowResourceNotFoundException(){
        when(countryRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> countryService.getById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    @DisplayName("getAllCountry: should return list of CountryDTOs mapped from entities")
    void getAllCountry_shouldReturnListOfAllDTOs(){
        Country country2 = new Country();
        country2.setCountryId(2L);
        country2.setCountryName("USA");
        country2.setCityName("New York");

        when(countryRepository.findAll())
                .thenReturn(List.of(savedCountry,country2));

        List<CountryDTO> result = countryService.getAllCountry();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getCountryName()).isEqualTo("India");
        assertThat(result.get(1).getCountryName()).isEqualTo("USA");
    }

    @Test
    @DisplayName("getAllCountry: should return empty list when no countries exists")
    void getAllCountry_whenNoCountriesExists_shouldReturnEmptyList(){

        when(countryRepository.findAll())
                .thenReturn(List.of());

        List<CountryDTO> result = countryService.getAllCountry();

        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("addCountry: should call repository save() once on success")
    void addCountry_onSuccess_shouldCallSaveExactlyOnce(){

        when(countryRepository.findByCountryNameAndCityName(anyString(),anyString()))
                .thenReturn(Optional.empty());
        when(countryRepository.save(any(Country.class)))
                .thenReturn(savedCountry);

        countryService.addCountry(validDTO);

        verify(countryRepository,times(1)).save(any(Country.class));
    }

    @Test
    @DisplayName("addCountry: should not call findById or findAll during add operation")
    void addCountry_shouldOnlyCallExpectedRepositoryMethods(){

        when(countryRepository.findByCountryNameAndCityName(anyString(),anyString()))
                .thenReturn(Optional.empty());
        when(countryRepository.save(any(Country.class)))
                .thenReturn(savedCountry);

        countryService.addCountry(validDTO);

        verify(countryRepository,never()).findById(any());
        verify(countryRepository,never()).findAll();
    }

}
