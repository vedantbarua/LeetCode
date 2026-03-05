public class ValidAnagram {
    //Question: Given two strings s and t, return true if t is an anagram of s, and false otherwise. 
    // An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        int[] counter = new int[26];
        for ( int i =0; i<s.length(); i++){
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }

        for (int count : counter){
            if(count != 0)
                return false;
            }
        return true;
    }
    
    public static void main(String[] args) {
        ValidAnagram solution = new ValidAnagram();
        String s = "anagram";
        String t = "nagaram";
        System.out.println(solution.isAnagram(s, t)); // Output: true

        String s1 = "rat";
        String t1 = "car";
        System.out.println(solution.isAnagram(s1, t1)); // Output: false
    }
}
