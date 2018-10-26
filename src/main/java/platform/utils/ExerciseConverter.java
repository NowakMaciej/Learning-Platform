package platform.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import platform.dto.ExerciseDto;
import platform.service.ExerciseService;

public class ExerciseConverter implements Converter<String, ExerciseDto>{
	@Autowired
	ExerciseService exerciseService;

	@Override
	public ExerciseDto convert(String source) {
		return this.exerciseService.findExerciseById(Long.parseLong(source));
	}

}
