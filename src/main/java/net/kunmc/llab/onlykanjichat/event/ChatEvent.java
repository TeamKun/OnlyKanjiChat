package net.kunmc.llab.onlykanjichat.event;

import net.kunmc.llab.onlykanjichat.logic.KanjiExtractor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;


public class ChatEvent implements Listener {

    /**
     * チャットイベント
     * */
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

    /**
     * tellコマンド
     * */
    @EventHandler(ignoreCancelled = true)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {

        String[] commands = event.getMessage().split(" ");
        /** コマンド */
        String command = commands[0];

        // tellコマンドであるか判定
        if (!command.equalsIgnoreCase("/tell")) {
            return;
        }

        /** 対象 */
        String target = commands[1];

        /** チャット */
        String chat = commands[2];

        // チャットから漢字のみを抽出
        String extractResult = KanjiExtractor.extractKanji(chat);

        // 抽出結果が0文字
        if (extractResult.length() == 0) {
            event.getPlayer().sendMessage("§c漢字が含まれていないためささやけません");
            // イベントをキャンセル
            event.setCancelled(true);
        }

        // 返還後の文字列をtell
        event.setMessage(command + " " + target + " " + extractResult);
    }
}
