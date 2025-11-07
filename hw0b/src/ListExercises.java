import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
    public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        int cnt=0;
        for(int i=0;i<L.size();i++){
            cnt+=L.get(i);
        }
        return cnt;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        for(int i=0;i<L.size();i++){
            if(L.get(i)%2==1){
                L.remove(i);
                i--;
            }
        }
        return L;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        for(int i=0;i<L1.size();i++){
            if(!L2.contains(L1.get(i))){
                L1.remove(i);
                i--;
            }
        }
        return L1;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function. 
        int sum=0;
        for(int i=0;i<words.size();i++){
            String word=words.get(i);
            for(int j=0;j<word.length();j++){
                if(word.charAt(j)==c){
                    sum++;
                }
            }
        }
        return sum;
    }
}
