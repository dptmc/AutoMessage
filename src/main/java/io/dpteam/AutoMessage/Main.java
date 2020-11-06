package io.dpteam.AutoMessage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Main extends JavaPlugin {
	public List messages = new ArrayList();
	public BukkitTask task;
	int timer;
	int count;

	public main() {
		super();
	}

	public void onEnable() {
		System.out.println("AutoMessage Enabled");
		this.saveDefaultConfig();
		this.saveConfig();
		this.getCommand("automessage").setExecutor(new Commands(this));
		this.timer = this.getConfig().getInt("timer");
		this.loadMessages();
		this.repeatingTask();
	}

	public void onDisable() {
		System.out.println("AutoMessage Disabled");
		this.messages.clear();
	}

	public void loadMessages() {
		this.count = 0;
		FileConfiguration c = this.getConfig();

		for(Iterator var3 = c.getStringList("messages").iterator(); var3.hasNext(); ++this.count) {
			String s = (String)var3.next();
			String[] messages = s.split(",,");
			int id = Integer.parseInt(messages[0]);
			String message = messages[1];
			this.messages.add(new messages(message, id));
		}

		System.out.println("<AutoMessage> Loaded " + this.count + " messages!");
	}

	public void repeatingTask() {
		this.task = (new BukkitRunnable() {
			public void run() {
				int messageSize = main.this.messages.size();
				Random rand = new Random();
				int randomNumber = rand.nextInt(messageSize);
				Iterator var5 = main.this.messages.iterator();

				while(var5.hasNext()) {
					messages m = (messages)var5.next();
					if (m.id == randomNumber) {
						Bukkit.broadcastMessage(main.this.Colour(m.message));
						return;
					}
				}

			}
		}).runTaskTimer(this, (long)(20 * this.timer), (long)(20 * this.timer));
	}

	public String Colour(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}
