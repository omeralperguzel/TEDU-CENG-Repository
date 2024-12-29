public class Node {
    Node[] childNodes;
    boolean isEnd;
    private int prefixCounter;

    //This is the constructor of the Node class.
    public Node() {
        this.childNodes = new Node[26]; //This is the array of child nodes. It is initialized with 26 null values because there are 26 letters in the English alphabet.
        this.isEnd = false;
        this.prefixCounter = 0;
        for (int i = 0; i < 26; i++) {
            childNodes[i] = null;
        }
    }

    //This method gets the prefix counter of the current node and returns it.
    public int getPrefixCounter() {
        return prefixCounter;
    }

    //This method increments the prefix counter of the current node.
    public void incrementPrefixCounter() {
        prefixCounter++;
    }

    //This method decrements the prefix counter of the current node.
    public void decrementPrefixCounter() {
        prefixCounter--;
    }

    //This method gets the child node of the current node that corresponds to the given character and returns it.
    public Node[] getChildNodes() {
        return childNodes;
    }

    //This method is a boolean method that checks if the current node has a child node that corresponds to the given character.
    public boolean hasChildren() {
        for (Node child : childNodes) {
            if (child != null) {
                return true;
            }
        }
        return false;
    }

    //This method counts the number of child nodes of the current node and returns it.
    public int countChildren() {
        int count = 0;
        for (Node child : childNodes) {
            if (child != null) {
                count++;
            }
        }
        return count;
    }

    //This boolean method checks if it is the end of the word or not. If so it returns true, otherwise it returns false.
    public boolean isEnd() {
        return isEnd;
    }

    //This method sets the end of the word.
    public void setEnd(boolean end) {
        isEnd = end;
    }

    //This method gets the first child node of the current node and returns it.
    public Node getFirstChild() {
        for (Node child : childNodes) {
            if (child != null) {
                return child;
            }
        }
        return null;
    }

    //This method clears the child nodes of the current node by setting them to null.
    public void clearChildren() {
        for (int i = 0; i < childNodes.length; i++) {
            if (childNodes[i] != null) {
                childNodes[i] = null;
            }
        }
    }
}