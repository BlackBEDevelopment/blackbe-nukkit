package com.wcpe.McBan;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

/**
 * BlackBE BlackBEDevelopment
 * @github https://github.com/BlackBEDevelopment
 */
public class Main extends PluginBase implements Listener {

	private String Message;

	@Override
	public void onLoad() {
		this.getLogger().info(TextFormat.WHITE + "正在加载!");
	}

	@Override
	public void onEnable() {
		saveDefaultConfig();
		Message = this.getConfig().getString("Message");
		this.getLogger().info(TextFormat.DARK_GREEN + "加载完毕 启用!");
		this.getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {
		this.getLogger().info(TextFormat.DARK_RED + "卸载完毕 再见!");
	}


	@EventHandler
	public void playerJoin(PlayerJoinEvent event) {
		if (Integer.parseInt(HttpClient.doGet("https://blackbe.xyz/api/check?id=" + event.getPlayer().getName())
				.substring(0, 1)) == 1) {
			event.getPlayer().kick(Message);

		} else {
			this.getLogger().info(TextFormat.DARK_GREEN + "玩家" + event.getPlayer().getName() + "不在云黑名单中");
		}
	}

}