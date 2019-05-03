package de.tub.privacySecurityEvaluator.model.fields.datautilityfields;

import com.fasterxml.jackson.annotation.JsonSetter;
import de.tub.privacySecurityEvaluator.model.Property;

public class TimelinessField extends Property<Double> {


	@JsonSetter("maximum")
	public void setMaximum(double d){setValue(d);}
}
