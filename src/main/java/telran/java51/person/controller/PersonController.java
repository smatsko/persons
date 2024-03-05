package telran.java51.person.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java51.person.dto.AddressDto;
import telran.java51.person.dto.CityDto;
import telran.java51.person.dto.PersonDto;
import telran.java51.person.service.PersonService;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
	final PersonService personService;

	@PostMapping
	public Boolean addPesron(@RequestBody PersonDto personDto) {
		return personService.addPerson(personDto);
	}

	@GetMapping("/{id}")
	public PersonDto findPersonById(@PathVariable Integer id) {
		return personService.findPersonById(id);
	}

	@DeleteMapping("/{id}")
	public PersonDto deletePerson(@PathVariable Integer id) {
		return personService.deletePerson(id);
	}

	@PutMapping("/{id}/address")
	public PersonDto updateAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto) {
		return personService.updateAddress(id, addressDto);
	}
	
	@GetMapping("/name/{name}")
	@Transactional(readOnly = true)
	public Iterable<PersonDto> findPersonByName(@PathVariable("name") String name) {
		return personService.findPersonsByName(name);
	}

	@GetMapping("/city/{city}")
	@Transactional(readOnly = true)
	public Iterable<PersonDto> findPersonByCity(@PathVariable String city) {
		return personService.findPersonsByCity(city);
	}

	@PutMapping("/{id}/name/{name}") 
	public PersonDto updateName(@PathVariable Integer id, @PathVariable String name) {
			return personService.updateName(id, name);
		}

	@GetMapping("/ages/{fromAge}/{toAge}") 
	@Transactional(readOnly = true)
	public Iterable<PersonDto> findPersonByCity(@PathVariable Integer fromAge, @PathVariable Integer toAge) {
		return personService.findByAge(fromAge, toAge);
				
	}
	

	@GetMapping("/population/city") 
	@Transactional(readOnly = true)
	public Iterable<CityDto> getCityPopulationsList () {
       	return  personService.getCitiesPopulation();	
	}
	
	@GetMapping("/population/{city}") 
	public CityDto getCityPopulation (@PathVariable String city) {
       	return (new CityDto(city, personService.getCityPopulation(city)));	
	}


	
	
}
