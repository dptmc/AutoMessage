package io.dpteam.AutoMessage;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {
	public main m;

	public Commands(main m) {
		super();
		this.m = m;
	}

	public boolean onCommand(CommandSender s, Command cmd, String lable, String[] args) {
		if (cmd.getName().equalsIgnoreCase("automessage")) {
			if (args.length < 1) {
				s.sendMessage(ChatColor.GOLD + "AutoMessage - Commands");
				s.sendMessage(ChatColor.GREEN + "/automessage reload - Reload the config.");
				return true;
			}

			if (args[0].equalsIgnoreCase("reload")) {
				if (s.hasPermission("automessage.reload")) {
					this.m.reloadConfig();
					this.m.task.cancel();
					this.m.messages.clear();
					this.m.loadMessages();
					this.m.repeatingTask();
					s.sendMessage(ChatColor.GOLD + "AutoMessage: " + ChatColor.GREEN + "Reloaded config!");
					return true;
				}

				s.sendMessage(ChatColor.GOLD + "Error: You don't have permissions for this command!");
			}
		}

		return false;
	}
}
