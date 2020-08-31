package com.darkonnen.countries.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.darkonnen.countries.models.City;
import com.darkonnen.countries.models.Country;
import com.darkonnen.countries.models.Language;
import com.darkonnen.countries.repositories.CityRepository;
import com.darkonnen.countries.repositories.CountryRepository;
import com.darkonnen.countries.repositories.LanguageRepository;

@Service
public class ApiService {
	private CityRepository cityRepository;
	private CountryRepository countryRepository;
	private LanguageRepository languageRepository;
	
	public ApiService(CityRepository cityRepository, CountryRepository countryRepository, LanguageRepository languageRepository) {
		this.cityRepository = cityRepository;
		this.countryRepository = countryRepository;
		this.languageRepository = languageRepository;
	}
	
	public List<City> allCities() {
		return cityRepository.findAll();
	}
	
	public List<Country> allCountries() {
		return countryRepository.findAll();
	}
	
	public List<Object[]> allCountriesInAllRegions() {
		return countryRepository.allCountriesInAllRegions();
	}
	
	public List<Object[]> findCountriesByLanguage(String language) {
		return countryRepository.findCountriesByLanguage(language);
	}
	
	public List<Object[]> allLanguagesOverPercent(double percent) {
		return countryRepository.allLanguagesOverPercent(percent);
	}
	
	public List<Object[]> findCityCountPerCountry() {
		return countryRepository.findCityCountPerCountry();
	}
	
	public List<Object[]> allCitiesInCountryOverPop(String country, int population) {
		return countryRepository.allCitiesInCountryOverPop(country, population);
	}	
	
	public List<Object[]> allCountriesOverSurfaceAreaAndPop(Double surArea, int population) {
		return countryRepository.allCountriesOverSurfaceAreaAndPop(surArea, population);
	}
	
	public List<Object[]> allCountriesByGovTypeSurAreaOverLifeExpOver(String government, Double surArea, Double lifeExp) {
		return countryRepository.allCountriesByGovTypeSurAreaOverLifeExpOver(government, surArea, lifeExp);
	}
	
	public List<Object[]> allCitiesByCountryDistrictOverPop(String country, String district, int population) {
		return countryRepository.allCitiesByCountryDistrictOverPop(country, district, population);
	}
	
	public List<Language> allLanguages() {
		return languageRepository.findAll();
	}
}