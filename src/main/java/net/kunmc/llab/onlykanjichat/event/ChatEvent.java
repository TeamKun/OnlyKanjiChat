package net.kunmc.llab.onlykanjichat.event;

import net.kunmc.llab.onlykanjichat.logic.KanjiExtractor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;


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
     * tell,w,msgコマンド
     * */
    @EventHandler(ignoreCancelled = true)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {

        String[] commands = event.getMessage().split(" ");
        /** コマンド */
        String command = commands[0];

        // tell,w,msgコマンドであるか判定
        if (!command.equalsIgnoreCase("/tell") &&
                !command.equalsIgnoreCase("/w") &&
                !command.equalsIgnoreCase("/msg")) {
            return;
        }

        // 引数チェック
        if (commands.length < 3) {
            return;
        }

        /** 対象 */
        String target = commands[1];

        /** チャット */
        String chat = "";

        for (int i = 2; i < commands.length; i++) {
            chat += commands[i];
        }

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
