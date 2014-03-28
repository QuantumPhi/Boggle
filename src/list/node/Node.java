package list.node;

import list.WordList;

public class Node {
    private Node parent;
    private Node[] subNodes;
    
    String nodeString;
    
    public Node(char character) { init(character); }
    
    public Node(char character, Node parent) {
        this.parent = parent;
        init(character);
    }
    
    private void init(char character) {
        nodeString = parent != null ? parent.nodeString() + character : "" + character;
        calculateSubNodes();
    }
    
    private void calculateSubNodes() {
        char[] subChars = WordList.charsAfterPrefix(nodeString);
        subNodes = new Node[subChars.length];
        for(int i = 0; i < subChars.length; i++)
            subNodes[i] = new Node(subChars[i], this);
    }
    
    public void calculateSubNodes(char[] letters) {
        char[] subChars = nodeString.length() != 0 ? 
                WordList.charsAfterPrefix(nodeString) : 
                letters;
        subNodes = new Node[subChars.length];
        for(int i = 0; i < subChars.length; i++)
            subNodes[i] = new Node(subChars[i], this);
    }
    
    public String nodeString() { return nodeString; }
    public char nodeCharacter() { return nodeString.charAt(nodeString.length() - 1); }
    public String parentString() { return (parent != null ? parent.nodeString : ""); }
    public boolean hasSubNodes() { return subNodes != null; }
    public Node getSubNode(int index) { return subNodes[index]; }
    public Node getSubNode(char character) {
        if(!hasSubNodes())
            return null;
        for(Node subNode : subNodes)
            if(subNode.nodeCharacter() == character)
                return subNode;
        return null;
    }
    
    public Node getNode(String nodeString) {
        Node temp = this;
        char[] nodeCharacter = nodeString.toCharArray();
        int index = 0;
        while(true) {
            if(temp.nodeString().equals(nodeString))
                return temp;
            else if(temp.getSubNode(nodeCharacter[index]) != null) {
                temp = temp.getSubNode(nodeCharacter[index]);
                index++;
            }  else if(temp.getSubNode(nodeCharacter[index]) == null)
                break;
        }
        return null;   
    }
}
