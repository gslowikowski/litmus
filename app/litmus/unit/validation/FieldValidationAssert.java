package litmus.unit.validation;

import org.fest.assertions.Assertions;

import java.util.List;

import static java.lang.String.format;
import static org.junit.Assert.assertTrue;
import static litmus.unit.validation.BuiltInValidation.REQUIRED;
import static litmus.util.ReflectionUtil.set;


public class FieldValidationAssert<T> {

	private T valid;
	private String fieldName;

	public FieldValidationAssert(T valid, String fieldName) {
		this.valid = valid;
		this.fieldName = fieldName;
	}

	public FieldValidationAssert<T> isRequired() {
		return withValue(null).isInvalidBecause(REQUIRED);
	}

	public FieldValidationAssert<T> shouldNotBe(Object value) {
		return withValue(value).isInvalid();
	}

	public FieldValidationAssert<T> withValue(Object value) {
		set(valid, fieldName, value);
		return this;
	}

	public FieldValidationAssert<T> isInvalid() {
		Assertions.assertThat(Validator.getErrorsForField(valid, fieldName))
				.as("expected validation error for field '" + fieldName + "' but it was valid.")
				.isNotEmpty();
		return this;
	}

	public FieldValidationAssert<T> isValid() {
		List<String> errorsForField = Validator.getErrorsForField(valid, fieldName);
		Assertions.assertThat(errorsForField)
				.as("expected to be valid, but errors found: " + errorsForField)
				.isEmpty();
		return this;
	}

	public FieldValidationAssert<T> isInvalidBecause(BuiltInValidation validation) {
		return isInvalidBecause(validation.getMessageKey());
	}

	public FieldValidationAssert<T> isInvalidBecause(String error) {
		List<String> errorsOnField = Validator.getErrorsForField(valid, fieldName);
		assertTrue(
				makeErrorMessage(fieldName, error, errorsOnField),
				errorsOnField.contains(error));
		return this;
	}

	private String makeErrorMessage(String field, String errorMessageKey, List<String> errorsOnField) {
		String result = format("Expected validation error '%s' not found on field '%s' of class %s.", errorMessageKey, field, valid.getClass().getCanonicalName());
		if (!errorsOnField.isEmpty()) {
			result += format("Other validation errors '%s' on field '%s'", errorsOnField, field);
		}
		return result;
	}


}