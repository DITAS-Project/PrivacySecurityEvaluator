package de.tub.privacySecurityEvaluator.model.fields.datautilityfields;

import com.fasterxml.jackson.annotation.JsonSetter;
import de.tub.privacySecurityEvaluator.model.Property;

public class AvailabilityField extends Property<Integer> {

	private int maximum;

	@JsonSetter("minimum")
	public void setMinimum(int i){setValue(i);}

}
