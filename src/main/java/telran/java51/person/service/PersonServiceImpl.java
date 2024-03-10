package telran.java51.person.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.java51.person.dao.PersonRepository;
import telran.java51.person.dto.AddressDto;
import telran.java51.person.dto.CityDto;
import telran.java51.person.dto.PersonDto;
import telran.java51.person.dto.ChildDto;
import telran.java51.person.dto.EmployeeDto;
import telran.java51.person.dto.exceptions.PersonNotFoundException;
import telran.java51.person.model.Address;
import telran.java51.person.model.Child;
import telran.java51.person.model.Employee;
import telran.java51.person.model.Person;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService, CommandLineRunner {

	final PersonRepository personRepository;
	final ModelMapper modelMapper;

	@Override
	public Boolean addPerson(PersonDto personDto) {
		if (!personRepository.existsById(personDto.getId())) {

			if (personDto instanceof ChildDto childDto) {
				personRepository.save(modelMapper.map(childDto, Child.class));
				return true;
			}
			if (personDto instanceof EmployeeDto employeeDto) {
				personRepository.save(modelMapper.map(employeeDto, Employee.class));
				return true;
			}

			personRepository.save(modelMapper.map(personDto, Person.class));
			return true;
		}
		return false;
	}

	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		System.out.println(person.getClass().getSimpleName());
		if (person instanceof Child child) {
			return modelMapper.map(child, ChildDto.class);
		}
		if (person instanceof Employee employee) {
			return modelMapper.map(employee, EmployeeDto.class);
		}
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto deletePerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		personRepository.delete(person);
		return modelMapper.map(person, PersonDto.class);

	}

	@Override
	public Iterable<PersonDto> findPersonsByCity(String city) {
		return personRepository.findByAddressCityIgnoreCase(city).map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<PersonDto> findPersonsByName(String name) {
		return personRepository.findByNameIgnoreCase(name).map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public PersonDto updateAddress(Integer id, AddressDto addressDto) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		Address newAddress = modelMapper.map(addressDto, Address.class);
		person.setAddress(newAddress);
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public Iterable<PersonDto> findByAge(Integer fromAge, Integer toAge) {
		return personRepository
				.findByBirthDateBetween(LocalDate.now().minusYears(toAge + 1), LocalDate.now().minusYears(fromAge))
				.map(p -> modelMapper.map(p, PersonDto.class)).collect(Collectors.toList());
	}

	@Override
	public long getCityPopulation(String city) {
		return personRepository.countByAddressCity(city);
	}

	@Override
	public PersonDto updateName(Integer id, String name) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		person.setName(name);
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public Iterable<CityDto> getCitiesPopulation() {
		return personRepository.getCitiesPopulation().collect(Collectors.toList());
	}

	
	
	@Override
	public Iterable<ChildDto> getChildren() {
		return personRepository
				.findBy()
				.map(p -> modelMapper.map(p, ChildDto.class)).collect(Collectors.toList());
	}


	@Override
	public Iterable<EmployeeDto> findBySal(Integer fromSal, Integer toSal) {
		return personRepository
				.findBySalaryBetween(fromSal, toSal)
				.map(p -> modelMapper.map(p, EmployeeDto.class)).collect(Collectors.toList());
	}


	
	
	@Transactional
	@Override
	public void run(String... args) throws Exception {
		if (personRepository.count() == 0) {
			Person person = new Person(1000, "John", LocalDate.of(1985, 3, 11),
					new Address("Tel Aviv", "Ben Gvirol", 81));
			Child child = new Child(2000, "Mosche", LocalDate.of(2018, 7, 5), new Address("Ashkelon", "Bar Kohva", 21),
					"Shalom");
			Employee employee = new Employee(3000, "Sarah", LocalDate.of(1995, 11, 23),
					new Address("Rehovot", "Herzl", 7), "Motorola", 20_000);
			personRepository.save(person);
			personRepository.save(child);
			personRepository.save(employee);
		}

	}

}
