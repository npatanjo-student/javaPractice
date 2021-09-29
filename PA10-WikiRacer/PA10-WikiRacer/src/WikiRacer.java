import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * AUTHOR: Nate Patanjo
 * FILE: WikiRacer.java
 * ASSIGNMENT: Wiki Racer PA 10
 * COURSE: CSC 210
 * PURPOSE: The purpose of this assignment is to go from one
 * link to another only traveling through the wiki links
 */
public class WikiRacer {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	/*
	 * Do not edit this main function
	 */
	public static void main(String[] args) {
		List<String> ladder = findWikiLadder(args[0], args[1]);
		System.out.println(ladder);
	}

	/**
	 * Find wiki ladder.
	 *
	 * @param start the start
	 * @param end the end
	 * @return the list
	 */
	/*
	 * Do not edit the method signature/header of this function
	 * TODO: Fill this function in.
	 */
	private static List<String> findWikiLadder(String start, String end) {
		MaxPQ queue = new MaxPQ();
		Set<String> setVisited = new HashSet<String>();
		List<String> startList = new ArrayList<String>();
		startList.add(start);
		queue.enqueue(startList, getPriority(start, end));
		while (!queue.isEmpty()) {
			System.out.println(queue.toString());
			List<String> tmp = queue.dequeue();
			Set<String> linkSet = new HashSet<String>(WikiScraper.findWikiLinks(tmp.get(tmp.size()-1)));
			if (linkSet.contains(end)) {
				tmp.add(end);
				return tmp;
			}
			linkSet.parallelStream().forEach(link -> {
				WikiScraper.findWikiLinks(link);
			});
			for (String page : linkSet) {
				if (!setVisited.contains(page)) {
					List<String> copy = new ArrayList<String>(tmp);
					copy.add(page);
					queue.enqueue(copy, getPriority(page, end));
					setVisited.add(page);
				}
			}
		}
		return new ArrayList<String>();
	}
	
	/**
	 * Gets the priority.
	 *
	 * @param start the start
	 * @param end the end
	 * @return the priority
	 */
	private static int getPriority(String start, String end) {
		Set<String> startSet = new HashSet<String>(WikiScraper.findWikiLinks(start));
		Set<String> endSet = new HashSet<String>(WikiScraper.findWikiLinks(end));
		if (startSet.contains(end)) {
			return 1000;
		}
		startSet.retainAll(endSet);
		return startSet.size();

	}

}
