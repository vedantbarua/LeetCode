public class MinimumWindowSubstring {
    //Question :Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
    // The testcases will be generated such that the answer is unique.
    public String minWindow(String s, String t) {
        int[] freq = new int[128];
        for (char c : t.toCharArray()) {
            freq[c]++;
        }

        int left = 0, right = 0, count = t.length(), minLen = Integer.MAX_VALUE, start = 0;
        while (right < s.length()) {
            char cRight = s.charAt(right);
            if (freq[cRight] > 0) {
                count--;
            }
            freq[cRight]--;
            right++;

            while (count == 0) {
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }
                char cLeft = s.charAt(left);
                freq[cLeft]++;
                if (freq[cLeft] > 0) {
                    count++;
                }
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring solution = new MinimumWindowSubstring();
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String result = solution.minWindow(s, t);
        System.out.println(result); // Output: "BANC"
    }
}
