package telran.java51.person.service;

import telran.java51.person.dto.AddressDto;
import telran.java51.person.dto.PersonDto;

public interface PersonService {
	Boolean addPerson(PersonDto personDto);

	PersonDto findPersonById(Integer id);
	
	PersonDto deletePerson(Integer id);

	Iterable<PersonDto> findPersonsByCity(String city);

	Iterable<PersonDto> findPersonsByName(String name);
	
	PersonDto updateAddress(Integer id, AddressDto addressDto);

	PersonDto updateName(Integer id, String name);

	Iterable<PersonDto> findByAge(Integer fromAge, Integer toAge);
	
}
