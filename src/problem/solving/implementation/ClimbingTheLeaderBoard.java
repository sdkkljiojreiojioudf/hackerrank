package problem.solving.implementation;

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


public class ClimbingTheLeaderBoard {

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        // Write your code here
        List<Integer> ranks = new ArrayList<>();
        ranked = new ArrayList<>(
                new LinkedHashSet<>(ranked)
        );
        int cpt = 0;
        Integer playerScore = player.get(0);
        for (int i = ranked.size()-1; i >= 0; i--) {
            int score = ranked.get(i);
            if(cpt==player.size()){
                break;
            }else{
                playerScore = player.get(cpt);
            }
            if(playerScore<score){
                ranks.add(i+2);
                i+=1;
                cpt+=1;
            }else if(playerScore==score){
                ranks.add(i+1);
                i+=1;
                cpt+=1;
            }else if(i==0 && playerScore>score ){
                i+=1;
                ranks.add(1);
                cpt+=1;
            }

        }

        return ranks;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:\\dev\\projets_persos\\hackerrank\\src\\problem\\solving\\implementation\\input06.txt"))));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int rankedCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> ranked = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int playerCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> player = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = climbingLeaderboard(ranked, player);

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
