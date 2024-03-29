package telran.java51.person.service;

import telran.java51.person.dto.AddressDto;
import telran.java51.person.dto.CityDto;
import telran.java51.person.dto.EmployeeDto;
import telran.java51.person.dto.PersonDto;
import telran.java51.person.dto.ChildDto;

public interface PersonService {
	Boolean addPerson(PersonDto personDto);

	PersonDto findPersonById(Integer id);
	
	PersonDto deletePerson(Integer id);

	Iterable<PersonDto> findPersonsByCity(String city);

	Iterable<PersonDto> findPersonsByName(String name);
	
	PersonDto updateAddress(Integer id, AddressDto addressDto);

	PersonDto updateName(Integer id, String name);

	Iterable<PersonDto> findByAge(Integer fromAge, Integer toAge);

	long getCityPopulation(String city);

	Iterable<CityDto> getCitiesPopulation();

	Iterable<ChildDto> getChildren();

	Iterable<EmployeeDto> findBySal(Integer fromSal, Integer toSal);
	
}
