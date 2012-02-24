package testutil.functional.html;

import org.apache.commons.lang.NotImplementedException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import testutil.functional.Response;
import testutil.functional.html.exception.CannotInitializePageException;
import testutil.functional.html.exception.ElementNotFoundException;
import testutil.functional.html.tags.AbstractHtmlTag;

import static org.jsoup.Jsoup.parse;
import static play.Play.configuration;
import static testutil.PlayAssertions.assertThat;
import static testutil.functional.AbstractFunctionalTest.get;

public abstract class HtmlPage {

	private Response response;
	private Document doc;

	public HtmlPage(String url) {
		this(get(url));
	}

	public HtmlPage(Response response) {
		initResponse(response);
		initDocument(response);
	}

	private void initResponse(Response response) {
		this.response = response;
		assertThat(response).isOk().isHtml();
	}

	private void initDocument(Response response) {
		String baseUrl = configuration.getProperty("application.baseUrl");
		if (baseUrl == null) {
			throw new CannotInitializePageException("Please specify the application.baseUrl property in your application.conf!");
		} else {
			this.doc = parse(response.getContent(), baseUrl);
		}
	}

	protected Response getResponse() {
		return response;
	}

	public String getTitle() {
		return doc.title();
	}

	protected Element findById(String id) {
		Element element = doc.getElementById(id);
		if (element == null) {
			throw new ElementNotFoundException("Unable to find element by id '" + id + "'");
		}
		return element;
	}


	// TODO
	public AbstractHtmlTag find(String selector) {
		throw new NotImplementedException();
	}
}
