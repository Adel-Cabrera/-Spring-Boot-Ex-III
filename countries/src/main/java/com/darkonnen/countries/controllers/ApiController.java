package com.darkonnen.countries.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.darkonnen.countries.services.ApiService;

@RestController
@RequestMapping("/")
public class ApiController {
	private ApiService apiService;

	public ApiController(ApiService apiService) {
		this.apiService = apiService;
	}
	@RequestMapping(value="countries/all", method=RequestMethod.GET, produces="application/json")
	public List<Object[]> countriesByLanguage(@RequestParam("language") String language) {
		return apiService.findCountriesByLanguage(language);
	}

	@RequestMapping(value="countries/cities", method=RequestMethod.GET, produces="application/json")
	public List<Object[]> allCitiesInCountryOverPop(@RequestParam("country") String country, @RequestParam("population") int population) {
		return apiService.allCitiesInCountryOverPop(country, population);
	}
	
	@RequestMapping(value="countries", method=RequestMethod.GET, produces="application/json")
	public List<Object[]> allCountriesOverSurfaceAreaAndPop(@RequestParam("surfaceArea") Double surArea, @RequestParam("population") int population) {
		return apiService.allCountriesOverSurfaceAreaAndPop(surArea, population);
	}
	
	@RequestMapping(value="countries/languages", method=RequestMethod.GET, produces="application/json")
	public List<Object[]> allLanguagesOverPercent(@RequestParam("percent") double percent) {
		return apiService.allLanguagesOverPercent(percent);
	}
	
	@RequestMapping(value="countries/cities/count", method=RequestMethod.GET, produces="application/json")
	public List<Object[]> cityCountByCountry() {
		return apiService.findCityCountPerCountry();
	}
		
	@RequestMapping(value="countries/government", method=RequestMethod.GET, produces="application/json")
	List<Object[]> allCountriesByGovTypeSurAreaOverLifeExpOver(@RequestParam("type") String government, @RequestParam("surfaceArea")Double surArea, @RequestParam("lifeExpectancy") Double lifeExp) {
		return apiService.allCountriesByGovTypeSurAreaOverLifeExpOver(government, surArea, lifeExp);
	}
	
	@RequestMapping(value="countries/cities/districts", method=RequestMethod.GET, produces="application/json")
	List<Object[]> allCitiesByCountryDistrictOverPop(@RequestParam("country") String country, @RequestParam("district") String district, @RequestParam("population") int population) {
		return apiService.allCitiesByCountryDistrictOverPop(country, district, population);
	}
	
	@RequestMapping(value="countries/regions", method=RequestMethod.GET, produces="application/json")
	List<Object[]> allCountriesInAllRegions() {
		return apiService.allCountriesInAllRegions();
	}
}