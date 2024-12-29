import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //This is a scanner to read the input from the console.
        Trie trie = new Trie(); //This is the trie that will be used to store the words.
        Trie trieLCP = new Trie(); //This is the trie that will be used to store the words for Longest Common Prefix.

        //This stores the number of words that will be added to the trie.
        int numWords = Integer.parseInt(scanner.nextLine());
        //This loop adds the words to the trie.
        for (int i = 0; i < numWords; i++) {
            String word = scanner.nextLine();
            trie.addWord(word); //It adds the word to the trie.
            trieLCP.addWordForLCP(word); //It adds the word to the trie for Longest Common Prefix.
            trie.addReversedWord(word); //It adds the reversed word to the trie.
        }

        //This loop asks the user for the operation code and performs the operation based on the operation code.
        while (scanner.hasNextLine()) {
            System.out.println("Enter Operation Code:");
            int operation = Integer.parseInt(scanner.nextLine()); //It stores the operation code in the variable operation for retrieve it from Trie class.
            String inputWord; //It stores the word that will be used in the operation.
            switch (operation) {
                case 1: // Search
                    inputWord = scanner.hasNextLine() ? scanner.nextLine() : "";
                    trie.displaySearchResults(inputWord);
                    break;
                case 2: // CountPrefix
                    trie.displayPrefixCounts();
                    break;
                case 3: // ReverseFind
                    inputWord = scanner.hasNextLine() ? scanner.nextLine() : "";
                    trie.displayReverseFind(inputWord);
                    break;
                case 4: // ShortestUniquePrefix
                    trie.displayShortestUniquePrefixes();
                    break;
                case 5: // LongestCommonPrefix
                    trieLCP.displayLongestCommonPrefix();
                    break;
                case 6: // Exit
                    return;
                default: //In default case, the operation code is invalid so it prints an error message.
                    System.out.println("Invalid operation code.");
            }
        }
        scanner.close(); //It closes the scanner.
    }
}
