package game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by rkh on 2013-12-14.
 */
public class WordModel {

    private String mSecretWord;
    private Random rand;

    public WordModel() {
        rand = new Random();
    }

    public void createSecretWordFromList(ArrayList<String> words) throws IllegalArgumentException {
        if (words.size() == 0) throw new IllegalArgumentException("List size must be greater than zero");
        mSecretWord = words.get(rand.nextInt(words.size()));
    }

    public boolean doWordContainChar(char ch) {
        for(Character wordch : mSecretWord.toCharArray()) {
            if (toLowerCase(wordch) == ch) {
                return true;
            }
        }
        return false;
    }

    public char toLowerCase(char old) {
        return Character.toLowerCase(old);
    }

    public ArrayList<String> readFile(String filename) throws IOException {
        ArrayList<String> list = new ArrayList<String>();
        BufferedReader br;
        String line;

        br = new BufferedReader(new FileReader(filename));

        while ((line = br.readLine()) != null) {
            list.add(line);
        }

        br.close();

        return list;
    }
}
