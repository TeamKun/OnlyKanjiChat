package net.kunmc.llab.onlykanjichat.event;

import net.kunmc.llab.onlykanjichat.logic.KanjiExtractor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SignChangeEvent implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onSignChange(org.bukkit.event.block.SignChangeEvent event) {
        // １行づつ処理
        String[] lines = event.getLines();

        int index = 0;
        for (String line : lines) {
            String extractResult = KanjiExtractor.extractKanji(line);
            event.setLine(index ,extractResult);
            index ++;
        }
    }
}
