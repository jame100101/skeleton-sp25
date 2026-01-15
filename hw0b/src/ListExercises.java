import java.util.List;
import java.util.ArrayList;
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
        List<Integer>res=new ArrayList<>();
        for(int x:L){
            if(x%2==0){
                res.add(x);
            }
        }
        return res;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        List<Integer> res=new ArrayList<>();
        for(int x:L1){
            if(L2.contains(x)){
                res.add(x);
            }
        }
        return res;
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
