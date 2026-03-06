public class  LongestRepeatingCharacterReplacement {
    //Question: Given a string s and an integer k, return the length of the longest substring of s that contains at most k distinct characters.
    public int characterReplacement(String s, int k) {
        int maxLength = 0;
        int left = 0;
        int[] charCount = new int[26];
        int maxCount = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            charCount[currentChar - 'A']++;
            maxCount = Math.max(maxCount, charCount[currentChar - 'A']);

            while (right - left + 1 - maxCount > k) {
                char leftChar = s.charAt(left);
                charCount[leftChar - 'A']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement solution = new LongestRepeatingCharacterReplacement();
        String input = "AABABBA";
        int k = 1;
        int result = solution.characterReplacement(input, k);
        System.out.println("Length of longest substring with at most " + k + " distinct characters: " + result); // Output: 4
    }
    
}