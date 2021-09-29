import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * AUTHOR: Nate Patanjo
 * FILE: WikiScraper.java
 * ASSIGNMENT: Wiki Racer PA 10
 * COURSE: CSC 210
 * PURPOSE: The purpose of this assignment is to go from one
 * link to another only traveling through the wiki links
 */
public class WikiScraper {
	
	/** The cache. */
	private static Map<String, Set<String>> cache = new HashMap<String,Set<String>>();
	
	/**
	 * Find wiki links.
	 *
	 * @param link the link
	 * @return the sets the
	 */
	/*
	 * TODO: Comment this function
	 */
	public static Set<String> findWikiLinks(String link) {
		if (cache.containsKey(link)) {
			return cache.get(link);
		}
		String html = fetchHTML(link);
		Set<String> links = scrapeHTML(link, html);
		return links;
	}
	
	/**
	 * Fetch HTML.
	 *
	 * @param link the link
	 * @return the string
	 */
	/*
	 * TODO: Comment this function. What does it do at
	 * a high level. I don't expect you to read/understand
	 * the StringBuffer and while loop. But from the spec
	 * and your understanding of this assignment, what is
	 * the purpose of this function.
	 */
	private static String fetchHTML(String link) {
		StringBuffer buffer = null;
		try {
			URL url = new URL(getURL(link));
			InputStream is = url.openStream();
			int ptr = 0;
			buffer = new StringBuffer();
			while ((ptr = is.read()) != -1) {
			    buffer.append((char)ptr);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return buffer.toString(); 
	}
	
	/**
	 * Gets the url.
	 *
	 * @param link the link
	 * @return the url
	 */
	/*
	 * TODO: Comment this function. What does it do.
	 */
	private static String getURL(String link) {
		return "https://en.wikipedia.org/wiki/" + link;
	}
	
	/**
	 * Scrape HTML.
	 *
	 * @param link the link
	 * @param html the html
	 * @return the sets the
	 */
	/*
	 * TODO: Fill this in with your code from the drill. Change
	 * this comment to accurately document this function.
	 */
	private static Set<String> scrapeHTML(String link, String html) {
		Set<String> retSet = new HashSet<String>();
		while (html.contains("href=\"/wiki/")) {
			html = html.substring(html.indexOf("href=\"/wiki/") + 12);
			String addStr = html.substring(0, html.indexOf("\""));
			if (!addStr.contains(":") && !addStr.contains("#")) {
				retSet.add(addStr);
			}
		}
		memoize(link, retSet);
		return retSet;
	}
	
	/**
	 * Memoize. Adds to dictionary containing already visited 
	 * links
	 *
	 * @param name the name
	 * @param retSet the ret set
	 */
	private static void memoize(String name, Set<String> retSet) {
		cache.put(name, retSet);
		
	}
	
}
