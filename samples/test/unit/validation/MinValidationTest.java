package unit.validation;

import litmus.unit.validation.ValidationTest;
import models.MinModel;
import org.junit.Test;

public class MinValidationTest extends ValidationTest<MinModel> {

	@Override
	protected MinModel valid() {
		MinModel model = new MinModel();
		model.minInt = 11;
		model.minDouble = 11D;
		model.minLong = 11L;
		model.minString = "11";
		return model;
	}

	@Test
	public void minDouble() {
		assertThat("minDouble").withValue(10.000000000001).isValid();
		assertThat("minDouble").mustNotBe(9.99999999999999);
		assertThat("minDouble").mustNotBeLessThan(10D);
		assertThat("minDouble").mustNotBeLessThan(10);
	}

	@Test
	public void minLong() {
		assertThat("minLong").withValue(11L).isValid();
		assertThat("minLong").mustNotBe(9L);
		assertThat("minLong").mustNotBeLessThan(10L);
		assertThat("minLong").mustNotBeLessThan(10);

	}

	@Test
	public void minInt() {
		assertThat("minInt").withValue(10).isValid();
		assertThat("minInt").mustNotBe(9);
		assertThat("minInt").mustNotBeLessThan(10);
	}

	@Test
	public void minString() {
		assertThat("minString").withValue("10").isValid();
		assertThat("minString").mustNotBe("9");
		assertThat("minString").mustNotBe("aaa");
		assertThat("minString").mustNotBeLessThan(10);
	}
}
