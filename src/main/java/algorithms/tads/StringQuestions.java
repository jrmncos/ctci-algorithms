package algorithms.tads;

import java.util.HashMap;

public class StringQuestions {
	
	/*
	 * Implement an algorithm to determine if a string has all unique characters
	 */
	//Book solution
	public boolean uniqueCharacters(String st) {
		if(st.length() > 256) return false;
		
		boolean[] char_set = new boolean[256];
		for(int i=0; i < st.length(); i++) {
			int val = st.charAt(i);
			if(char_set[val])
				return false;
			char_set[val] = true;
		}
		return true;
	}
	
	/*
	 * Given two strings, write a method to decide if one is a permutation of the other
	 */
	/*
	 * Approach: If st1 is a permutation to st2 => St1 and St2 must have the same number of repetitions in each of their characters
	 */
	public boolean isAPermutation(String st1, String st2) {
		if(st1.length() != st2.length() || st1.length() == 0 || st2.length() == 0) return false;
		
		HashMap<Character, Integer> cc1 = charactersCount(st1);
		HashMap<Character, Integer> cc2 = charactersCount(st2);
		
		return sameCharactersCount(cc1, cc2);
	}
	
	private HashMap<Character, Integer> charactersCount(String st) {
		HashMap<Character, Integer> characterCount = new HashMap<>();
		
		for(int i=0; i < st.length(); i++) {
			char val = st.charAt(i);
			if(!characterCount.containsKey(val))
				characterCount.put(val, 1);
			else
				characterCount.put(val, characterCount.get(val) + 1);
		}
		return characterCount;
	}
	
	private boolean sameCharactersCount(HashMap<Character, Integer> freq1, HashMap<Character, Integer> freq2) {
		for(Character key: freq1.keySet()) {
			if(freq1.get(key) != freq2.get(key))
				return false;
		}
		return true;
	}
	
	/*
	 * Implement a method to perform basic string compression using the counts of repeated characters
	 * For example, the string aabcccccaaa would become a2b1c5a3
	 * If the compressed string would not become smaller than the original string, your method should return the original string.
	 */
	/*
	 * Approach: Keep on a map the number of repetitions of each character
	 * only when the repetitions are consecutive
	 * I ignore the second constraint 
	 */
	public String compression(String st) {
		StringBuilder compression = new StringBuilder();
		int last = st.charAt(0);
		int count = 1;
		
		for(int i = 1; i < st.length(); i++) {
			char val = st.charAt(i);
			if(val == last) {
				count++;
			}
			else {
				compression.append(val);
				compression.append(count);
				last = val;
			}
		}
		return compression.toString();
	}

	/*
	 * Assume you have a method isSubstring which checks if one word is a substring of another
	 * Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using only one call to isSubstring
	 * waterbottle is a rotation of erbottlewat
	 */
	/*
	 * Traverse the two String until match the characters
	 */
	public boolean isARotation(String s1, String s2) {
		if(s1.length() != s2.length()) return false;
		
		StringBuilder stb = new StringBuilder();
		int i = 0;
		while(i < s1.length()) {
			if(s1.charAt(i) == s1.charAt(i)) {
				return subString(s1.substring(0, i), s2) 
						&& stb.toString().compareTo(s2.substring(i, s2.length()-1)) == 0;
			}
			i++;
			stb.append(s1.charAt(i));
		}
		return false;
	}
	
	private boolean subString(String s1, String s2) {
		return true;
	}
	
	/*
	 * Book solution: s1 = xy = waterbottle
	 * x = wat
	 * y = erbottle
	 * s2 = yx = erbottlewat
	 * =>
	 * s1s1 = waterbottlewaterbottle
	 * s2 always be a substring of s1s1
	 */
	
	
}
