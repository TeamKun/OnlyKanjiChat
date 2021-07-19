package net.kunmc.llab.onlykanjichat.event;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kunmc.llab.onlykanjichat.logic.KanjiExtractor;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

public class ChatEvent implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        // チャットから漢字のみを抽出
        String extractResult = KanjiExtractor.extractKanji(event.getMessage());

        // 抽出結果が0文字
        if (extractResult.length() == 0) {
            // イベントをキャンセル
            event.setCancelled(true);
        }

        // 抽出結果をチャット
        event.setMessage(extractResult);
    }
}
