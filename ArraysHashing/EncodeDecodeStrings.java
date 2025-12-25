/**
 * LeetCode 271 - Encode and Decode Strings
 * Approach: Length-prefix each string with delimiter to safely encode/decode.
 * Time: O(total characters)
 * Space: O(total characters)
 */
import java.util.ArrayList;
import java.util.List;

public class EncodeDecodeStrings {
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s.length()).append('#').append(s);
        }
        return sb.toString();
    }

    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int j = i;
            while (s.charAt(j) != '#') {
                j++;
            }
            int len = Integer.parseInt(s.substring(i, j));
            j++; // move past '#'
            res.add(s.substring(j, j + len));
            i = j + len;
        }
        return res;
    }
}
