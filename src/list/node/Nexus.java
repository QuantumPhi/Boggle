package list.node;

public class Nexus {
    private Node[] nodes;
    
    public Nexus(char[] characters) { init(characters); }
    
    private void init(char[] characters) {
        characters = filter(characters);
        nodes = new Node[characters.length];
        for(int i = 0; i < nodes.length; i++)
            nodes[i] = new Node(characters[i]);
    }
        
    public Node getBranchNode(String string) {
        return getBranch(string.charAt(0)).getNode(string);
    }
    
    private Node getBranch(char character) {
        for(Node node : nodes)
            if(node.nodeCharacter() == character)
                return node;
        return null;
    }
    
    private static char[] filter(char[] characters) {
        String returnChar = new String();
        for(char character : characters)
            if(returnChar.indexOf(character) == -1)
                returnChar += character;
        return returnChar.toCharArray();
    }
}
