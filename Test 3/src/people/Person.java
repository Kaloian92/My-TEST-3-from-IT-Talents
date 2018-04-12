package people;

import demo.Demo;

public class Person {
	private String name;

	public Person(String name) throws InvalidPersonException {
		setName(name);
	}

	// Getters
	public String getName() {
		return name;
	}

	// Setters
	private void setName(String name) throws InvalidPersonException {
		if (Demo.isStringValid(name)) {
			this.name = name;
		} else {
			throw new InvalidPersonException("Invalid Name");
		}

	}
}
