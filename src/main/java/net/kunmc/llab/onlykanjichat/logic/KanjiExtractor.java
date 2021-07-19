package net.kunmc.llab.onlykanjichat.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KanjiExtractor {

    /** 正規表現 */
    private static final String REGEX = "^[\\u4E00-\\u9FFF\\u3005-\\u3007]+$";

    /**
     * チャットから漢字を抽出する。
     *
     * @param chat
     * @return 漢字抽出後の文字列
     * */
    public static String extractKanji(String chat) {
        Pattern pattern = Pattern.compile(REGEX);

        /** 抽出結果 */
        String result = "";

        // 文字列を1文字づつ判定
        String[] chatArr = chat.split("");
        for (String s : chatArr) {
            Matcher matcher = pattern.matcher(s);

            if (matcher.find()) {
                result += matcher.group();
            }
        }
        return result;
    }
}
