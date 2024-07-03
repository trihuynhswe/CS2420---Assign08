package assign08;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpellCheckerTest {
	SpellChecker sp = new SpellChecker(new File("src/assign08/dictionary.txt"));
	String shortStory = "src/assign08/short_story.txt";
	String misspellList = "src/assign08/MisspellList.txt";
	String oiqucnqpc = "src/assign08/oiqucnqpc.txt";
	File shortStoryTest = new File("src/assign08/dictionary.txt");

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void testAddToDictionaryAndContains() {
		List<String> preTest = sp.spellCheck(new File(oiqucnqpc));
		assertEquals(1, preTest.size());
		sp.addToDictionary("oiqucnqpc");
		List<String> postTest = sp.spellCheck(new File(oiqucnqpc));
		assertEquals(0, postTest.size());
		
	}

	@Test
	void testRemoveFromDictionary() {
		sp.addToDictionary("asdfqweroiu");
		sp.addToDictionary("asdfqweroiua");
		sp.addToDictionary("asdfqweroiuaa");
		assertEquals(sp.countWordsBetween("asdfqweroiu", "asdfqweroiuaa"),3);
		sp.removeFromDictionary("asdfqweroiua");
		assertEquals(sp.countWordsBetween("asdfqweroiu", "asdfqweroiuaa"),2);
	}

	@Test
	void testSpellCheckNoMispell() {
		List<String> test = sp.spellCheck(new File(shortStory));
		assertEquals(0,test.size());
	}
	
	@Test
	void testSpellCheckWithMispell() {
		sp.removeFromDictionary("a");
		List<String> test = sp.spellCheck(new File(shortStory));
		assertEquals(15,test.size());
	}
	
	@Test
	void testSpellCheckWithMispellFile() {
		List<String> test = sp.spellCheck(new File(misspellList));
		assertEquals(15,test.size());
	}
	
	

	@Test
	void testCountWordsBetween() {
		sp.addToDictionary("asdflkj");
		sp.addToDictionary("asdflkja");
		assertEquals(sp.countWordsBetween("asdflkj", "asdflkja"), 2);
	}
	
}
