package unit.validation;

import litmus.unit.validation.ValidationTest;
import models.PastModel;
import org.junit.Test;

import static litmus.util.DateUtil.*;

public class PastValidationTest extends ValidationTest<PastModel> {

	@Override
	protected PastModel valid() {
		PastModel model = new PastModel();
		model.pastDate = yesterday();
		model.dateBefore31Dec2012 = asDate("2000-01-01");
		return model;
	}

	@Test
	public void pastDate() {
		assertThat("pastDate").isInvalidWhenEqualTo(tomorrow());
		assertThat("pastDate").mustBeInThePast();
	}

	@Test
	public void dateBeforeAGivenDate() {
		assertThat("dateBefore31Dec2012").mustBeBefore(asDate("2012-12-31"));
		assertThat("dateBefore31Dec2012").mustBeBefore("2012-12-31");
	}


}
