package list.sort;

import java.util.List;

public class Searcher {
    public static int binarySearch(List<String> list, String word) {
        int low = 0;
        int high  = list.size() - 1;
        while(low <= high) {
            int mid = (high + low) >>> 1;
            int compare = list.get(mid).compareTo(word);
            if(compare < 0)
                low = mid + 1;
            else if(compare > 0)
                high = mid - 1;
            else if(compare == 0)
                return mid;
        }
        return -(low + 1);
    }
    
    public static int binarySearch(String[] list, String word) {
        int low = 0;
        int high  = list.length- 1;
        while(low <= high) {
            int mid = (high + low) >>> 1;
            int compare = list[mid].compareTo(word);
            if(compare < 0)
                low = mid + 1;
            else if(compare > 0)
                high = mid - 1;
            else if(compare == 0)
                return mid;
        }
        return -(low + 1);
    }
}
