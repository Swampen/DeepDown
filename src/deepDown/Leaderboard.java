package deepDown;

import java.io.*;
import java.util.ArrayList;

public class Leaderboard {
    private String filePath;
    private String highScores;

    private ArrayList<Integer> topScores;

    public Leaderboard() {
        filePath = new File("Files").getAbsolutePath();
        highScores = "Scores";

        topScores = new ArrayList<Integer>();
    }

    public void addScore(int score){
        for(int i = 0; i < topScores.size(); i++) {
            if(score >= topScores.get(i)){
                topScores.add(i, score);
                topScores.remove(topScores.size()-1);
                return;
            }
        }
    }

    public void loadScores() {
        try {
            File f = new File(filePath, highScores);
            if(!f.isFile()){
                createSaveData();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

            topScores.clear();

            String[] scores = reader.readLine().split("-");

            for (int i =0; i < scores.length; i++){
                topScores.add(Integer.parseInt(scores[i]));
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveScores(){
        FileWriter output = null;
        try {
            File f = new File(filePath, highScores);
            output = new FileWriter(f);
            BufferedWriter writer = new BufferedWriter(output);

            writer.write(topScores.get(0) + "-" + topScores.get(1) + "-" + topScores.get(2) + "-" + topScores.get(3) + "-" + topScores.get(4));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void createSaveData(){
        FileWriter output = null;
        try {
            File f = new File(filePath, highScores);
            output = new FileWriter(f);
            BufferedWriter writer = new BufferedWriter(output);

            writer.write("0-0-0-0-0");
            writer.newLine();
            writer.write("0-0-0-0-0");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getHighScore(int i) {
        return topScores.get(i);
    }
}