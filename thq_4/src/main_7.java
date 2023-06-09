import java.util.Scanner;
import java.util.Vector;

public class main_7 {
    public static char[] convNum(int i) {
        //Convert numbers to their corresponding letters.
        return switch (i) {
            case 2 -> new char[]{'a', 'b', 'c'};
            case 3 -> new char[]{'d', 'e', 'f'};
            case 4 -> new char[]{'g', 'h', 'i'};
            case 5 -> new char[]{'j', 'k', 'l'};
            case 6 -> new char[]{'m', 'n', 'o'};
            case 7 -> new char[]{'p', 'q', 'r', 's'};
            case 8 -> new char[]{'t', 'u', 'v'};
            case 9 -> new char[]{'w', 'x', 'y', 'z'};
            default -> new char[]{'-'};
        };
    }

    public static void printLeft(Vector<String> left) {
        for (int j = 0; j < left.size(); j++) {
            System.out.printf("#{%d} candidate is : < %s >\n", j, left.elementAt(j));
        }
    }

    public static void main(String[] args) {
        String[] raw = new String[]{"work", "back", "come", "deal", "desk", "book", "java", "tool", "face"};
        System.out.println("Dictionary:");
        for (int w = 1; w <= raw.length; w++) {
            //Iterating the dictionary and print out, with automated new line
            System.out.printf("%s %s", raw[w - 1], w % 5 == 0 ? "\n" : "\t");
        }
        System.out.print("\n--------------");
        //noinspection InfiniteLoopStatement
        while (true) {
            Vector<String> left = new Vector<>();
            for (String s : raw) {
                //same as `for(int i=0;i<raw.length;i++)`
                left.addElement(s);
            }
            System.out.println("\nPlease enter your index below:");
            Scanner scanner = new Scanner(System.in);
            int inputRaw = scanner.nextInt();
            // code above acquired a index string from user.below is splitting up the number to single
            String inputRawStr = Integer.toString(inputRaw);
            for (int i = 0; i < inputRawStr.length(); i++) {
                // Get the user0input index at position i
                int input_at_i = Character.getNumericValue(inputRawStr.charAt(i));
                // Get the possible letter corresponding to the `input_at_i`
                char[] char_matches_at_i = convNum(input_at_i);
                for (int j = 0; j < left.size(); ) {
                    //Iterating Vector left to verify each element
                    String leftStr_at_j = left.elementAt(j);
//                for (int k = 0; k < char_matches_at_i.length; k++) {}
                    // if none of `char_matches_at_i` matches, then the `leftStr_at_j`
                    // would be removed from `left`
                    int matches_count_at_j = 0;
                    for (char k : char_matches_at_i) {
                        if (leftStr_at_j.charAt(i) == k) {
                            matches_count_at_j++;
                        }
                    }
                    if (matches_count_at_j == 0) {
                        left.removeElementAt(j);
                        // If the remove was successful, then there should not be j++,
                        // as the deletion makes the next ele become the current one.
                    } else j++;
                }
            }
            System.out.println("After filtering:");
            printLeft(left);
            // Simple filtering based on `left.size()`
            if (left.size() == 0) {
                System.out.println("***** HTTP/1.1 404 Not Found! Maybe there is a typo?");
            } else if (left.size() == 1) {
                System.out.printf("***** Your desired String is: %s.\n", left.elementAt(0));
            } else {
                System.out.printf("***** There are %d matches to your request.\n", left.size());
            }
            System.out.println("Now the loop will re-run and welcome back to this program!");
        }
    }
}
