import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'acmTeam' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING_ARRAY topic as parameter.
     */

    public static List<Integer> acmTeam(List<String> topic) 
    {
    // Write your code here
           int maxSkills = 0;
        int teamsWithMaxSkills = 0;

        for (int i = 0; i < topic.size() - 1; i++) {
            for (int j = i + 1; j < topic.size(); j++) {
                int skills = countSkills(topic.get(i), topic.get(j));
                if (skills > maxSkills) {
                    maxSkills = skills;
                    teamsWithMaxSkills = 1;
                } else if (skills == maxSkills) {
                    teamsWithMaxSkills++;
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        result.add(maxSkills);
        result.add(teamsWithMaxSkills);
        return result;
    }

    private static int countSkills(String topic1, String topic2) {
        int count = 0;
        for (int i = 0; i < topic1.length(); i++) {
            if (topic1.charAt(i) == '1' || topic2.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<String> topic = IntStream.range(0, n).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        List<Integer> result = Result.acmTeam(topic);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
