/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mel-Srt
 */
public class WordsLoader {
    private static WordsLoader instance;
    File f;
    
    //Using: call WordsLoader.getInstance().method();
    public static synchronized WordsLoader getInstance() {
        if (instance == null) {
            instance = new WordsLoader();
        }
        return instance;
    }
    
    public List<String> readFile() {
        String pathFile = "files/words.csv";
        List<String> wordsList = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(pathFile))) {

            String sCurrentLine;
 
            while ((sCurrentLine = br.readLine()) != null) {
                String[] parts = sCurrentLine.split(";");
                String word = parts[0];
                wordsList.add(word);
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordsList;
    }

}
