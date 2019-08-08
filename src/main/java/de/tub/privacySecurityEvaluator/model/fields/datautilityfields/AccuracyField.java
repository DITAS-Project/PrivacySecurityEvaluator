package de.tub.privacySecurityEvaluator.model.fields.datautilityfields;

import com.fasterxml.jackson.annotation.JsonSetter;
import de.tub.privacySecurityEvaluator.model.Property;

public class AccuracyField extends Property<Double> {

	@JsonSetter("minimum")
	public void setMinimum(double d){setValue(d);}
}
