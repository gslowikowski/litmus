package testutil.functional.html;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import play.test.FunctionalTest;
import testutil.PlayAssertions;

import static org.jsoup.Jsoup.parse;
import static play.Play.configuration;
import static play.mvc.Http.Response;
import static testutil.functional.response.ResponseContentReader.readContent;

public class HtmlPage {

	private Response response;
	private Document doc;

	public HtmlPage(Response response) {
		this.response = response;
		PlayAssertions.assertThat(response).isOk().isHtml();
		this.doc = parse(readContent(response), configuration.getProperty("application.baseUrl"));
	}

	protected Response getResponse() {
		return response;
	}

	public String getMeta(String name) {
		return doc.head().getElementsByTag("meta").select("[name=" + name + "]").first().attr("content");
	}


	public String getTitle() {
		return doc.title();
	}


	public HtmlElement findById(String id) {
		Element element = doc.getElementById(id);
		if (element == null) {
			throw new IllegalArgumentException("Unable to find element by id '" + id + "'");
		}
		return new HtmlElement(element);
	}

	protected static Response GET(String url) {
		return FunctionalTest.GET(url);
	}

	// TODO override other Http methods from FunctionalTest here (GET, PUT, POST, DELETE)
}
