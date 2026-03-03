package Blind75;

import java.util.ArrayList;
import java.util.List;

public class EncodeAndDecodeStrings {
    public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder encoded = new StringBuilder();
        for (String str : strs) {
            encoded.append(str.length()).append('#').append(str);
        }
        return encoded.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int j = i;
            while (s.charAt(j) != '#') {
                j++;
            }
            int len = Integer.parseInt(s.substring(i, j));
            int start = j + 1;
            int end = start + len;
            result.add(s.substring(start, end));
            i = end;
        }
        return result;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));

public static void main(String[] args) {
    EncodeAndDecodeStrings eds = new EncodeAndDecodeStrings();
    Codec codec = eds.new Codec();
    List<String> strs = new ArrayList<>();
    strs.add("Hello");
    strs.add("World");
    String encoded = codec.encode(strs);
    System.out.println("Encoded: " + encoded);
    List<String> decoded = codec.decode(encoded);
    System.out.println("Decoded: " + decoded);
}
}
