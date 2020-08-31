package com.darkonnen.countries.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.darkonnen.countries.models.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {
	// Todos los países
	List<Country> findAll();
	
	@Query("SELECT c.name, l.language, l.percentage FROM Country c JOIN c.languages l WHERE l.language =?1 ORDER BY l.percentage DESC")
	List<Object[]> findCountriesByLanguage(String language);
	
	@Query("SELECT c.name, l.language, l.percentage FROM Country c JOIN c.languages l WHERE l.percentage >?1 ORDER BY l.percentage DESC")
	List<Object[]> allLanguagesOverPercent(double percent);
	
	@Query("SELECT c.name, COUNT(cy) FROM Country c JOIN c.cities cy GROUP BY c.name ORDER BY COUNT(cy) DESC")
	List<Object[]> findCityCountPerCountry();
	
	@Query("SELECT c.name, cy.name, cy.population FROM Country c JOIN c.cities cy WHERE cy.country.name =?1 AND cy.population >?2 ORDER BY cy.population DESC")
	List<Object[]> allCitiesInCountryOverPop(String country, int population);
	
	// ¿Qué consulta ejecutarías para obtener todos los países con un superficie de área inferior a 501 y una población mayor a 100.000?
	@Query("SELECT c.name, c.surface_area, c.population FROM Country c WHERE c.surface_area <?1 AND c.population >?2")
	List<Object[]> allCountriesOverSurfaceAreaAndPop(Double surArea, int population);
	
	@Query("SELECT c.name, c.government_form, c.surface_area, c.life_expectancy FROM Country c WHERE c.government_form =?1 AND c.surface_area >?2 AND c.life_expectancy >?3")
	List<Object[]> allCountriesByGovTypeSurAreaOverLifeExpOver(String government, Double surArea, Double lifeExp);
	
	@Query("SELECT c.name, cy.name, cy.district, cy.population FROM Country c JOIN c.cities cy WHERE cy.country.name =?1 AND cy.district = ?2 AND cy.population >?3")
	List<Object[]> allCitiesByCountryDistrictOverPop(String country, String district, int population);
	
	@Query("SELECT c.region, COUNT(c) FROM Country c GROUP BY c.region ORDER BY COUNT(c) DESC")
	List<Object[]> allCountriesInAllRegions();
}