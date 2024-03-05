package telran.java51.person.dao;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import telran.java51.person.dto.CityDto;
import telran.java51.person.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    //Query("from Person where name  = :theName")
	//Stream<Person> findByName(@Param("theName") String Name);
	Stream<Person> findByNameIgnoreCase(String Name);

	Stream<Person> findByAddressCityIgnoreCase(String city);

	Stream<Person> findByBirthDateBetween(LocalDate fromDay, LocalDate toDay);

	long countByAddressCity(String city);

	@Query("SELECT new telran.java51.person.dto.CityDto(address.city,  COUNT(*)) FROM Person GROUP BY address.city")
	Stream<CityDto> getCitiesPopulation();

	
}
