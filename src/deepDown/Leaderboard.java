package deepDown;

import java.io.*;
import java.util.ArrayList;

public class Leaderboard {
    private String filePath;
    private String highScores;

    private ArrayList<Integer> topScores;
    private ArrayList<String> topName;

    /**
     * Constructor which makes/locates the file and allocates the ArrayLists.
     */
    public Leaderboard() {
        filePath = new File("Files").getAbsolutePath();
        highScores = "scores";

        topScores = new ArrayList<Integer>();
        topName = new ArrayList<String>();
    }

    /**
     * Method for adding scores to the leaderboard by checking if the supplied score is greater than any
     * of those already on the leaderboards.
     * @param score Supplied score
     * @param name Supplied name
     */
    public void addScore(int score, String name){
        for(int i = 0; i < topScores.size(); i++) {
            if(score >= topScores.get(i)){
                topScores.add(i, score);
                topScores.remove(topScores.size()-1);
                if(name.isEmpty()){
                    topName.add(i, "Anonymous");
                    topName.remove(topName.size()-1);
                }else {
                    topName.add(i, name);
                    topName.remove(topName.size() - 1);
                }
                return;
            }
        }
    }

    /**
     * Loads the scores already stored and also checks if the scores file exists
     * if is doesn't then it runs the createSaveData() method.
     */
    public void loadScores() {
        try {
            File f = new File(filePath, highScores);
            if(!f.isFile()){
                createSaveData();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

            topScores.clear();

            String[] scores = reader.readLine().split("-");
            String[] names = reader.readLine().split("-");

            for (int i =0; i < scores.length; i++){
                topScores.add(Integer.parseInt(scores[i]));
                topName.add(names[i]);
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the scores and names in the ArrayLists to the scores file.
     */
    public void saveScores(){

        try {
            File f = new File(filePath, highScores);
            FileWriter output = new FileWriter(f);
            BufferedWriter writer = new BufferedWriter(output);

            writer.write(topScores.get(0) + "-" + topScores.get(1) + "-" + topScores.get(2) + "-" + topScores.get(3) + "-" + topScores.get(4));
            writer.newLine();
            writer.write(topName.get(0) + "-" + topName.get(1) + "-" + topName.get(2) + "-" + topName.get(3) + "-" + topName.get(4));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *Creates an empty scores file.
     */
    private void createSaveData(){
        FileWriter output = null;
        try {
            File f = new File(filePath, highScores);
            output = new FileWriter(f);
            BufferedWriter writer = new BufferedWriter(output);

            writer.write("0-0-0-0-0");
            writer.newLine();
            writer.write(".....-.....-.....-.....-.....");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Getter for the score in the index i of the topScores ArrayList.
     * @param i the index for the ArrayList.
     * @return score value of that index.
     */
    public int getHighScore(int i) {
        return topScores.get(i);
    }

    /**
     * Getter for the name in the index i of the topNames ArrayList.
     * @param i the index for the ArrayList.
     * @return name value of that index.
     */
    public String getNames(int i) {
        return topName.get(i);
    }
}
