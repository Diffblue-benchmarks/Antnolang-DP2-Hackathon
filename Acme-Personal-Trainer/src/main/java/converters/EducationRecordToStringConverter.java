/*
 * EducationRecordToStringConverter.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class EducationRecordToStringConverter implements Converter<EducationRecord, String> {

	@Override
	public String convert(final EducationRecord educationRecord) {
		String result;

		if (educationRecord == null)
			result = null;
		else
			result = String.valueOf(educationRecord.getId());

		return result;
	}

}
