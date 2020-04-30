package brv.telegram.bots.core.restclients.cats;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SearchResultTest {

	@Test
	private void test() {
		SearchResult result = new SearchResult();
		result.setId("id");
		
		assertEquals("id", result.getId());
	}

}
