package by.bntu.fitr.povt.prostrmk.ItNews.model.util;

public class StringsWork {

    public static String firstUpperCase(String word){
        if(word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

}
