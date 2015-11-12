/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmt.hocmay.utility;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Tuan
 */
public class Ultilities {

    public static ArrayList<String> getWords(String filePath) {
        ArrayList<String> str = new ArrayList<String>();
        PTBTokenizer<CoreLabel> ptbt;
        try {
            ptbt = new PTBTokenizer<>(new FileReader(filePath),
                    new CoreLabelTokenFactory(), "untokenizable=allDelete");
            while (ptbt.hasNext()) {
                CoreLabel label = ptbt.next();
                str.add(label.toString().toLowerCase());
//                System.out.println(label.toString().toLowerCase());
            }
            //Sắp xếp từ
            Collections.sort(str, new Comparator<String>() {
                public int compare(String str1, String str2) {
//                System.out.println(str1+" "+str2);
                    if (str1.compareTo(str2) > 0) {
                        return 1;
                    }else if(str1.compareTo(str2) == 0){
                        return 0;
                    }else {
                        return -1;
                    }
                }
            });
            //Loại bỏ từ giống nhau
            for (int i = 0; i < str.size() - 1; i++) {
                if (str.get(i).equals(str.get(i + 1))) {
                    str.remove(i);
                    i--;
                }
            }
        } catch (FileNotFoundException ex) {

        }
        return str;
    }
}
