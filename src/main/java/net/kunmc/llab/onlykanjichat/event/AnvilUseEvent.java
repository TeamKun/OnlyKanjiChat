package net.kunmc.llab.onlykanjichat.event;

import net.kunmc.llab.onlykanjichat.logic.KanjiExtractor;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AnvilUseEvent implements Listener {
    @EventHandler(ignoreCancelled = true)

    /**
     * 名づけアイテム対応
     * */
    public void onInventoryClick(InventoryClickEvent event) {

        // インベントリが金床か判定
        Inventory inventory = event.getInventory();
        if(!(inventory instanceof AnvilInventory)) {
            return;
        }

        // アイテムメタを取得
        ItemStack item = event.getCurrentItem();
        ItemMeta itemMeta = item.getItemMeta();

        // アイテムを掴んだときか判定
        if (itemMeta == null) {
            return;
        }

        // 変更後の名前を取得
        String renameText = itemMeta.displayName().toString();

        // 名づけされたアイテムか判定
        if (renameText == null) {
            return;
        }

        // 名前から漢字を抽出
        String extractResult = KanjiExtractor.extractKanji(renameText);

        // 抽出後の名前をセット
        itemMeta.displayName(Component.text(extractResult));
        item.setItemMeta(itemMeta);
    }
}
