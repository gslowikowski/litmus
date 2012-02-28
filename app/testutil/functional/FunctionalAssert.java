package testutil.functional;

import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;
import play.mvc.Http;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.StatusCode.*;

@SuppressWarnings("unchecked")
public abstract class FunctionalAssert<SelfType extends FunctionalAssert, ActualType> extends GenericAssert<SelfType, ActualType> {

	private Response response;

	protected FunctionalAssert(Class<SelfType> selfType, ActualType actual, Response response) {
		super(selfType, actual);
		this.response = response;
	}

	public SelfType isStatus(int httpStatusCode) {
		assertThat(response.getStatus()).isEqualTo(httpStatusCode);
		return (SelfType) this;
	}

	public SelfType isOk() {
		return isStatus(OK);
	}

	public SelfType isNotFound() {
		return isStatus(NOT_FOUND);
	}

	public SelfType isForbidden() {
		return isStatus(FORBIDDEN);
	}

	public SelfType isSuccess() {
		assertThat(response.isSuccess()).isTrue();
		return (SelfType) this;
	}

	public SelfType isNoSuccess() {
		assertThat(response.isSuccess()).isFalse();
		return (SelfType) this;
	}

	public SelfType isError() {
		assertThat(response.isError()).isTrue();
		return (SelfType) this;
	}

	public SelfType isNoError() {
		assertThat(response.isError()).isFalse();
		return (SelfType) this;
	}

	public SelfType isRedirect() {
		assertThat(response.isRedirect()).isTrue();
		return (SelfType) this;
	}

	public SelfType isNoRedirect() {
		assertThat(response.isRedirect()).isFalse();
		return (SelfType) this;
	}

	public SelfType isRedirectTo(String location) {
		isRedirect();
		hasHeaderValue("location", location);
		return (SelfType) this;
	}

	public SelfType hasHeader(String name) {
		assertThat(response.getHeader(name)).isNotNull();
		return (SelfType) this;
	}

	public SelfType doesNotHaveHeader(String name) {
		assertThat(response.getHeader(name)).isNull();
		return (SelfType) this;
	}

	public SelfType hasHeaderValue(String headerName, String headerValue) {
		assertThat(response.getHeader(headerName)).isEqualToIgnoringCase(headerValue);
		return (SelfType) this;
	}

	public SelfType hasFlashParam(String name) {
		FunctionalAssertions.assertThat(response.getFlashParam(name)).isNotNull();
		return (SelfType) this;
	}

	public SelfType hasFlashParam(String name, String value) {
		FunctionalAssertions.assertThat(response.getFlashParam(name)).isEqualTo(value);
		return (SelfType) this;
	}

	public SelfType doesNotHaveFlashParam(String name) {
		FunctionalAssertions.assertThat(response.getFlashParam(name)).isNull();
		return (SelfType) this;
	}

	public SelfType hasCookie(String name) {
		Http.Cookie cookie = response.getCookie(name);
		Assertions.assertThat(cookie).describedAs(String.format("cookie [%s] not found", name)).isNotNull();
		return (SelfType) this;
	}

	public SelfType hasCookieValue(String name, String value) {
		Http.Cookie cookie = response.getCookie(name);
		Assertions.assertThat(cookie).describedAs(String.format("cookie [%s] not found", name)).isNotNull();
		Assertions.assertThat(cookie.value).isEqualTo(value);
		return (SelfType) this;
	}

	public SelfType doesNotHaveCookie(String cookieName) {
		Assertions.assertThat(response.getCookie(cookieName)).isNull();
		return (SelfType) this;
	}

	public SelfType hasRenderArg(String name) {
		Assertions.assertThat(response.getRenderArgs().get(name)).isNotNull();
		return (SelfType) this;
	}

	public SelfType hasRenderArgValue(String name, Object expected) {
		Assertions.assertThat(response.getRenderArgs().get(name)).isEqualTo(expected);
		return (SelfType) this;
	}


	public SelfType doesNotHaveRenderArg(String name) {
		FunctionalAssertions.assertThat(response.getRenderArgs().get(name)).isNull();
		return (SelfType) this;
	}


	/* ************************************ To Do ************************************ */

	public SelfType isEncoded(String encoding) {
		assertThat(response.getEncoding()).isEqualToIgnoringCase(encoding);
		return (SelfType) this;
	}

	public SelfType isUtf8() {
		return isEncoded("utf-8");
	}


}