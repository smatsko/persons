package telran.java51.person.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee extends Person {
	
	private static final long serialVersionUID = 5039956826797153544L;
	
	String company;
	int salary;

	public Employee(Integer id, String name, LocalDate birthDate, Address address, String company, int salary) {
		super(id, name, birthDate, address);
		this.company = company;
		this.salary = salary;
	}

}