package leetcode;

/**
 * Design a data structure that supports the following two operations:
 * 
 * <pre>
 * void addWord(word)
 * bool search(word)
 * </pre>
 * 
 * search(word) can search a literal word or a regular expression string containing only letters a-z
 * or .. A . means it can represent any one letter.
 * 
 * <pre>
 * For example:
 * 
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * </pre>
 * 
 * Note: You may assume that all words are consist of lowercase letters a-z.
 *
 */
public class AddAndSearchWordDataStructureDesign {

	public class WordDictionary {
		WordNode root = new WordNode();

		/**
		 * Adds a word into the data structure.
		 */
		public void addWord(String word) {
			char chars[] = word.toCharArray();
			addWord(chars, 0, root);
		}

		private void addWord(char[] chars, int index, WordNode parent) {
			char c = chars[index];
			int idx = c - 'a';
			WordNode node = parent.children[idx];
			if (node == null) {
				node = new WordNode();
				parent.children[idx] = node;
			}
			if (chars.length == index + 1) {
				node.isLeaf = true;
				return;
			}
			addWord(chars, ++index, node);
		}

		/**
		 * Returns if the word is in the data structure. A word could contain the dot character '.'
		 * to represent any one letter.
		 */
		public boolean search(String word) {
			return search(word.toCharArray(), 0, root);
		}

		private boolean search(char[] chars, int index, WordNode parent) {
			if (index == chars.length) {
				if (parent.isLeaf) {
					return true;
				}
				return false;
			}
			WordNode[] childNodes = parent.children;
			char c = chars[index];
			if (c == '.') {
				for (int i = 0; i < childNodes.length; i++) {
					WordNode n = childNodes[i];
					if (n != null) {
						boolean b = search(chars, index + 1, n);
						if (b) {
							return true;
						}
					}
				}
				return false;
			}
			WordNode node = childNodes[c - 'a'];
			if (node == null) {
				return false;
			}
			return search(chars, ++index, node);
		}

		private class WordNode {
			boolean isLeaf;
			WordNode[] children = new WordNode[26];
		}
	}
}
