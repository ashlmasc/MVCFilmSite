package com.skilldistillery.film.entities;

import java.util.List;
import java.util.Objects;


public class Film {
	private int id;
    private String title;
    private String description;
    private Integer releaseYear;
    private int languageId;
    private int rentalDuration;
    private double rentalRate;
    private Integer length;
    private double replacementCost;
    private String rating;
    private String specialFeatures;
    private String language;
    private List<String> categories;

	public Film() {

	}

	

	public Film(int id, String title, String description, Integer releaseYear, int languageId, int rentalDuration,
			double rentalRate, Integer length, double replacementCost, String rating, String specialFeatures,
			String language, List<String> categories) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		this.language = language;
		this.categories = categories;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Film ID: ").append(id).append("\nTitle: ").append(title).append("\nDescription: ")
				.append(description).append("\nRelease Year: ").append(releaseYear).append("\nLanguage ID: ")
				.append(languageId).append("\nRental Duration: ").append(rentalDuration).append(" days")
				.append("\nRental Rate: ").append("$").append(rentalRate).append("\nLength: ").append(length)
				.append(" minutes").append("\nReplacement Cost: ").append("$").append(replacementCost)
				.append("\nRating: ").append(rating).append("\nSpecial Features: ").append(specialFeatures)
				.append("\nLanguage: ").append(language).append("\nActors: ");
		

		if (categories != null && !categories.isEmpty()) {
			sb.append("\nCategory: ");
			for (String category : categories) {
				sb.append(category).append(", ");
			}
			sb.setLength(sb.length() - 2);
		} else {
			sb.append("\nCategories: No categories found");
		}

		return sb.toString();
	}



	@Override
	public int hashCode() {
		return Objects.hash(categories, description, id, language, languageId, length, rating, releaseYear,
				rentalDuration, rentalRate, replacementCost, specialFeatures, title);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(categories, other.categories) && Objects.equals(description, other.description)
				&& id == other.id && Objects.equals(language, other.language) && languageId == other.languageId
				&& Objects.equals(length, other.length) && Objects.equals(rating, other.rating)
				&& Objects.equals(releaseYear, other.releaseYear) && rentalDuration == other.rentalDuration
				&& Double.doubleToLongBits(rentalRate) == Double.doubleToLongBits(other.rentalRate)
				&& Double.doubleToLongBits(replacementCost) == Double.doubleToLongBits(other.replacementCost)
				&& Objects.equals(specialFeatures, other.specialFeatures) && Objects.equals(title, other.title);
	}

	

	
}
