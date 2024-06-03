package eda.characters;

/**
 * Clase de dominio que mantiene información sobre los personajes
 */
public class Character {
	private String name;
	private String bio;
	private String species;
	private String gender;
	private String house;
	private int yearOfBirth;
	private boolean wizard;
	private String ancestry;
	private String eyeColour;
	private String hairColour;
	private String wand_wood;
	private boolean alive;

	public Character(String name, String bio, String species, String gender, String house, int yearOfBirth, boolean wizard, String ancestry, String eyeColour, String hairColour, String wand_wood, boolean alive) {
		super();
		this.name = name;
		this.bio = bio;
		this.species = species;
		this.gender = gender;
		this.house = house;
		this.yearOfBirth = yearOfBirth;
		this.wizard = wizard;
		this.ancestry = ancestry;
		this.eyeColour = eyeColour;
		this.hairColour = hairColour;
		this.wand_wood = wand_wood;
		this.alive = alive;
	}

	public String getName() {
		return name;
	}

	public String getBio() {
		return bio;
	}

	public String getSpecies() {
		return species;
	}

	public String getGender() {
		return gender;
	}

	public String getHouse() {
		return house;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public boolean isWizard() {
		return wizard;
	}

	public String getAncestry() {
		return ancestry;
	}

	public String getEyeColour() {
		return eyeColour;
	}

	public String getHairColour() {
		return hairColour;
	}

	public String getWand_wood() {
		return wand_wood;
	}

	public boolean isAlive() {
		return alive;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
