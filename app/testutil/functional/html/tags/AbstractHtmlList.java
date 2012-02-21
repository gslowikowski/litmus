package testutil.functional.html.tags;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import org.jsoup.nodes.Element;

import java.util.List;

import static com.google.common.collect.Iterables.find;
import static com.google.common.collect.Lists.transform;

public abstract class AbstractHtmlList extends AbstractHtmlTag {

	public AbstractHtmlList(Element element) {
		super(element);
	}


	public List<String> getTextFromItems() {
		return transform(element.children(), new Function<Element, String>() {
			public String apply(Element element) {
				return element.text();
			}
		});
	}

	public List<LI> getItems() {
		return transform(element.children(), new Function<Element, LI>() {
			public LI apply(Element element) {
				return new LI(element);
			}
		});
	}

	public LI getItemByVisibleText(final String text) {
		return find(getItems(), new Predicate<LI>() {
			public boolean apply(LI li) {
				return li.getText().equals(text);
			}
		});
	}


}
