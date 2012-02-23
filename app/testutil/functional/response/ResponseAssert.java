package testutil.functional.response;

import testutil.functional.AbstractFunctionalAssert;

import static play.mvc.Http.Response;

@SuppressWarnings("unchecked")
public class ResponseAssert extends AbstractFunctionalAssert<ResponseAssert, Response> {

	public ResponseAssert(Response actual) {
		super(ResponseAssert.class, actual, actual);
	}

}
