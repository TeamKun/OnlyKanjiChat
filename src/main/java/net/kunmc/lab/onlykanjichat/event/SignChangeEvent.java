package net.kunmc.lab.onlykanjichat.event;

import net.kunmc.lab.onlykanjichat.logic.KanjiExtractor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SignChangeEvent implements Listener {
    /**
     * 看板入力
     * */
    @EventHandler(ignoreCancelled = true)
    public void onSignChange(org.bukkit.event.block.SignChangeEvent event) {

        /** 行ごとの入力内容 */
        String[] lines = event.getLines();

        /** 行カウンタ */
        int index = 0;

        // １行づつ処理
        for (String line : lines) {
            // 行から漢字のみを抽出
            String extractResult = KanjiExtractor.extractKanji(line);

            // 看板にセット
            event.setLine(index ,extractResult);

            index ++;
        }
    }
}
