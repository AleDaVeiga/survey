package com.survey.surveyapi.model.base;

import static java.util.Optional.ofNullable;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import com.survey.surveyapi.dto.BaseEntityDTO;

public class BaseEntityHelper {
	public static <T extends BaseEntity, Y extends BaseEntityDTO> void changeRelationship(T oldEntity, Y newEntity, Consumer<T> setEntityBase, Function<Long, Optional<T>> findEntity) {
		if (ofNullable(newEntity).isPresent() && ofNullable(newEntity.getId()).isPresent()) {
			if (ofNullable(oldEntity).isPresent()) {
				if (!newEntity.getId().equals(oldEntity.getId())) {
					setEntityBase.accept(findEntity.apply(newEntity.getId()).get());
				}
			} else {
				setEntityBase.accept(findEntity.apply(newEntity.getId()).get());
			}
		} else {
			setEntityBase.accept(null);
		}
	}
}
