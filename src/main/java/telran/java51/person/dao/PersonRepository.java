package telran.java51.person.dao;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import telran.java51.person.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    //Query("from Person where name  = :theName")
	//Stream<Person> findByName(@Param("theName") String Name);
	Stream<Person> findByNameIgnoreCase(String Name);

	Stream<Person> findByAddressCityIgnoreCase(String city);

	Stream<Person> findByBirthDateBetween(LocalDate fromDay, LocalDate toDay);

	
}
