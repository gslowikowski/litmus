package unit.validation;

import litmus.unit.validation.ValidationTest;
import models.FutureModel;
import org.junit.Test;

import static litmus.util.DateUtil.*;

public class FutureValidationTest extends ValidationTest<FutureModel> {
	@Override
	protected FutureModel valid() {
		FutureModel model = new FutureModel();
		model.futureDate = dateInFuture();
		model.dateAfter1Jan2100 = asDate("2222-01-01");
		return model;
	}

	@Test
	public void futureDate() {
		assertThat("futureDate").shouldNotBe(dateInPast());
		assertThat("futureDate").shouldBeInFuture();
	}

	@Test
	public void dateAfterAGivenDate() {
		assertThat("dateAfter1Jan2100").shouldBeAfter(asDate("2100-01-01"));
		assertThat("dateAfter1Jan2100").shouldBeAfter("2100-01-01");
	}

}
