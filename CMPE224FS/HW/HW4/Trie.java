import java.util.*;

public class Trie {
    private Node rootNode;


    //This is a constructor for the Trie class needed for the main method.
    public Trie() {
        rootNode = new Node();
    }

    //This is addWord which adds a word to the Trie and increments the prefix counter.
    public void addWord(String word) {
        Node currentNode = rootNode;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (currentNode.getChildNodes()[index] == null) {
                currentNode.getChildNodes()[index] = new Node();
            }
            currentNode = currentNode.getChildNodes()[index];
            currentNode.incrementPrefixCounter();
        }
        currentNode.setEnd(true);
        wordsList.add(word);
    }

    //This is addWordForLCP used only for the longest common prefix method which adds a word to the Trie without incrementing the prefix counter.
    void addWordForLCP(String key) {
        int length = key.length();
        int index;

        Node pCrawl = rootNode;

        for (int level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';
            if (pCrawl.childNodes[index] == null)
                pCrawl.childNodes[index] = new Node();

            pCrawl = pCrawl.childNodes[index];
        }
        pCrawl.isEnd = true;
    }

    //This is search which searches for a word in the Trie and returns true if it is found and false if it is not found.
    public boolean Search(String word) {
        Node currentNode = rootNode;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (currentNode.getChildNodes()[index] == null) {
                return false;
            }
            currentNode = currentNode.getChildNodes()[index];
        }
        return currentNode.isEnd();
    }

    //This is displaySearchResults which displays the results of the search method.
    public void displaySearchResults(String word) {
        boolean isFound = Search(word);
        System.out.println(isFound ? "True" : "False");
    }

    //This is removeWord which removes a word from the Trie and decrements the prefix counter.
    public void removeWord(String word) {
        if (!Search(word)) {
            System.out.println("Word does not exist in the Trie.");
            return;
        }
        Node currentNode = rootNode;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            currentNode = currentNode.getChildNodes()[index];
            currentNode.incrementPrefixCounter();
        }
        currentNode.setEnd(false);
        wordsList.remove(word);
    }

    //This is countPrefix which counts the number of words in the Trie with a given prefix.
    public int countPrefix(String prefix) {
        if (!isValidString(prefix)) {
            return 0;
        }

        Node currentNode = rootNode;

        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (index < 0 || index >= 26 || currentNode.getChildNodes()[index] == null) {
                return 0;
            }
            currentNode = currentNode.getChildNodes()[index];
        }

        return currentNode.getPrefixCounter();
    }

    //This is calculateWordCount which calculates the number of words in the Trie.
    private int calculateWordCount(Node node) {
        int count = 0;
        if (node.isEnd()) {
            count++;
        }
        for (Node child : node.getChildNodes()) {
            if (child != null) {
                count += calculateWordCount(child);
            }
        }
        return count;
    }

    //This is calculateTotalWordCount which calculates the total number of words in the Trie.
    public int calculateTotalWordCount() {
        return calculateWordCount(rootNode);
    }

    //This is isEmpty which returns true if the Trie is empty and false if it is not empty.
    public boolean isEmpty() {
        return calculateTotalWordCount() == 0;
    }

    //This is clear which clears the Trie.
    public void clear() {
        rootNode = new Node();
        wordsList.clear();
    }

    List<String> wordsList = new ArrayList<>();

    //This is containsPrefix which returns true if the Trie contains a given prefix and false if it does not contain the prefix.
    public boolean containsPrefix(String prefix) {
        Node currentNode = rootNode;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (currentNode.getChildNodes()[index] == null) {
                return false;
            }
            currentNode = currentNode.getChildNodes()[index];
        }
        return true;
    }

    //This is displayPrefixCounts which displays the number of words in the Trie with each prefix.
    public void displayPrefixCounts() {
        Collections.sort(wordsList);
        for (String word : wordsList) {
            int count = countPrefix(word);
            System.out.println(word + ": " + (count - 1));
        }
    }

    //This is displayWordsWithPrefix which displays the words in the Trie with a given prefix.
    public void displayWordsWithPrefix(String prefix) {
        if (!containsPrefix(prefix)) {
            System.out.println("No words with prefix '" + prefix + "'");
            return;
        }
        for (String word : wordsList) {
            if (word.startsWith(prefix)) {
                System.out.println(word);
            }
        }
    }

    //This is displayAllWords which displays all the words in the Trie.
    public void addReversedWord(String word) {
        Node currentNode = rootNode;
        for (int i = word.length() - 1; i >= 0; i--) {
            int index = word.charAt(i) - 'a';
            if (currentNode.getChildNodes()[index] == null) {
                currentNode.getChildNodes()[index] = new Node();
            }
            currentNode = currentNode.getChildNodes()[index];
        }
        currentNode.setEnd(true);
    }

    //This is displayAllWords which displays all the words in the Trie.
    public void displayAllWords() {
        for (String word : wordsList) {
            System.out.println(word);
        }
    }

    //This is reverseFind which finds all the words in the Trie that end with a given suffix.
    public List<String> reverseFind(String suffix) {
        List<String> results = new ArrayList<>();
        Node currentNode = rootNode;
        for (int i = suffix.length() - 1; i >= 0; i--) {
            int index = suffix.charAt(i) - 'a';
            if (currentNode.getChildNodes()[index] == null) {
                return results;
            }
            currentNode = currentNode.getChildNodes()[index];
        }
        retrieveAllWords(currentNode, new StringBuilder(suffix), results);
        return results;
    }

    //This is retrieveAllWords which retrieves all the words in the Trie that end with a given suffix.
    private void retrieveAllWords(Node node, StringBuilder currentWord, List<String> results) {
        if (node.isEnd()) {
            results.add(currentWord.toString());
        }
        for (char c = 'a'; c <= 'z'; c++) {
            Node nextNode = node.getChildNodes()[c - 'a'];
            if (nextNode != null) {
                currentWord.insert(0, c);
                retrieveAllWords(nextNode, currentWord, results);
                currentWord.deleteCharAt(0);
            }
        }
    }

    //This is displayReverseFind which displays all the words in the Trie that end with a given suffix.
    public void displayReverseFind(String suffix) {
        List<String> words = reverseFind(suffix);
        if (words.isEmpty()) {
            System.out.println("No words ending with '" + suffix + "'");
        } else {
            for (String word : words) {
                System.out.println(word);
            }
        }
    }

    //This is countWordsWithSuffix which counts the number of words in the Trie that end with a given suffix.
    public int countWordsWithSuffix(String suffix) {
        int count = 0;
        for (String word : wordsList) {
            if (word.endsWith(suffix)) {
                count++;
            }
        }
        return count;
    }

    //This is shortestUniquePrefix which finds the shortest unique prefix of a given word.
    public String shortestUniquePrefix(String word) {
        if (!isValidString(word)) {
            return "Invalid input. Please enter lowercase English letters only.";
        }
        Node currentNode = rootNode;
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (currentNode.getChildNodes()[index] == null) {
                return "not exists";
            }
            currentNode = currentNode.getChildNodes()[index];
            prefix.append(word.charAt(i));
            if (currentNode.getPrefixCounter() == 1) {
                return prefix.toString(); // Return prefix if it is unique
            }
        }
        return "not exists"; // Return "not exists" if no unique prefix is found
    }    

    //This is displayShortestUniquePrefixes which displays the shortest unique prefix of each word in the Trie.
    public void displayShortestUniquePrefixes() {
        Map<String, String> wordToPrefix = new HashMap<>();
        for (String word : wordsList) {
            String prefix = shortestUniquePrefix(word);
            wordToPrefix.put(word, prefix);
        }
        wordToPrefix.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }

    //This is longestCommonPrefix which finds the longest common prefix of all the words in the Trie.
    public String longestCommonPrefix() {
        String prefix = "";
        Node pCrawl = rootNode;
        while (calculateNonNullChildNodes(pCrawl) == 1 && !pCrawl.isEnd()) {
            for (int i = 0; i < 26; i++) {
                if (pCrawl.getChildNodes()[i] != null) {
                    prefix += (char)(i + 'a');
                    pCrawl = pCrawl.getChildNodes()[i];
                    break;
                }
            }
        }
        return prefix.isEmpty() ? "not exists" : prefix;
    }

    //This is displayLongestCommonPrefix which displays the longest common prefix of all the words in the Trie.
    public void displayLongestCommonPrefix() {
        String prefix = longestCommonPrefix();
        System.out.println(prefix);
    }

    //This is calculateNonNullChildNodes which calculates the number of non-null child nodes of a given node.
    private int calculateNonNullChildNodes(Node node) {
        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (node.getChildNodes()[i] != null) {
                count++;
            }
        }
        return count;
    }

    //This is isValidString which returns true if a given string is valid and false if it is not valid.
    private static boolean isValidString(String str) {
        return str.matches("[a-z]+");
    }  
}