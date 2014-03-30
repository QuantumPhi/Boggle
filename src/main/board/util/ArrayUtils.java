package main.board.util;

public class ArrayUtils {
    public static boolean[][] cloneBoolean(boolean[][] indices) {
        boolean[][] indicesClone = new boolean[indices.length][indices[0].length];
        for(int i = 0; i < indices.length; i++)
            System.arraycopy(indices[i], 0, indicesClone[i], 0, indices[i].length);
        return indicesClone;
    }
    
    public static boolean isComplete(boolean[][] indices) {
        for (boolean[] index : indices)
            for (int j = 0; j < index.length; j++)
                if (!index[j])
                    return false;
        return true;
    }
    
    public static boolean contains(char[] set, char character) {
        return new String(set).indexOf(character) != -1;
    }
}
