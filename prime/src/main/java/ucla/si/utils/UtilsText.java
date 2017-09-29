package ucla.si.utils;

import java.util.Iterator;

public class UtilsText {

	public String transformarHtml(String html) {

		return html.replaceAll("\\<.*?>", "");

	}

}
