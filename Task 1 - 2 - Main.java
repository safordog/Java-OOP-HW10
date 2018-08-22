/*
 * 1. Написать программу - переводчик, которая будет переводить
 * текст в файле English.in, написанный на английском языке, на
 * украинский язык, согласно заранее составленному словарю.
 * Результат сохранить в файл Ukrainian.out.
 * 2. Сделать ф-ю ручного наполнения словаря и возможность его
 * сохранения на диск.
 */
package homework10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 *
 * @author safordog
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        File english = new File("English.in"); 
        HashMap<String, String> vocab = new HashMap<>();
        setVocabulary("i", "я", vocab);
        setVocabulary("have", "маю", vocab);
        setVocabulary("dream", "мрія", vocab);
        setVocabulary("that", "що", vocab);
        setVocabulary("one", "один", vocab);
        setVocabulary("day", "день", vocab);
        setVocabulary("this", "ця", vocab);
        setVocabulary("nation", "нація", vocab);
        setVocabulary("will", "буде", vocab);
        setVocabulary("rise", "зростати", vocab);
        setVocabulary("up", "вгору", vocab);
        setVocabulary("and", "і", vocab);
        setVocabulary("liveout", "усвідомити", vocab);
        setVocabulary("true", "справжній", vocab);
        setVocabulary("meaning", "зміст", vocab);
        setVocabulary("its", "свого", vocab);
        setVocabulary("creed", "віровчення", vocab);
        System.out.println(getTranslate(vocab).toString());
    }
  
    public static void setVocabulary(String eng, String ukr, HashMap<String, String> map) {
        map.put(eng, ukr);
        File file = new File("Vocabulary.txt");
        try (PrintWriter pw = new PrintWriter(file)) {
            map.entrySet().forEach((temp) -> {
                pw.println(temp.getKey() + " - " + temp.getValue());
            });
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            }
    }
    
    public static ArrayList getTranslate(HashMap map) throws IOException {
        ArrayList<String> in = Files.lines(Paths.get("English.in"))
                .collect(Collectors.toCollection(ArrayList::new));
        String strIn = in.toString();
        String[] arrStr = strIn.split(" ");
        String strOut = "";
        Path out = Paths.get("Ukrainian.out");
        for (int i = 0; i < arrStr.length; i++) {
            if (map.containsKey(arrStr[i])) {
                strOut = strOut + (map.get(arrStr[i]) + " ");
            }
        }
        ArrayList<String> arrOut = new ArrayList<>(Arrays.asList (strOut));
        Files.write(out,arrOut);
        return arrOut;
    }
    
}
