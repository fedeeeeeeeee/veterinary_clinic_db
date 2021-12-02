package model;

import java.util.List;
import java.util.Optional;

/**
 * This interface represents a user (medical staff).
 */
public interface User extends Person {

	// l'username è la mail --> String getUsername();
	
	String getSalt();

	String getHashPassword();

	int getYearsOnTheJob();
	
	Optional<List<String>> getSpecializations();

	String getRuolo(); 
	
	void setRuolo(String ruolo); 
	
	void setSalt(String salt);
	
	void setHashPassword(String password);

	void setYearsOnTheJob(int years);

	void setSpecialization(String specialization);

}
