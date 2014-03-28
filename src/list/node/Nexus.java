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
    
    public Node getNode(char character) {
        for(Node node : nodes)
            if(node.nodeCharacter() == character)
                return node;
        return null;
    }
    
    public Node getNode(String string) {
        return getNode(string.charAt(0)).getNode(string);
    }
    
    private static char[] filter(char[] characters) {
        String returnChar = new String();
        for(char character : characters)
            if(returnChar.indexOf(character) == -1)
                returnChar += character;
        return returnChar.toCharArray();
    }
}
