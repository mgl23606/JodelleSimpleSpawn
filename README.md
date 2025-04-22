# 🛏️ Jodelle Simple Spawn  
*A lightweight, configurable spawn teleport plugin for Spigot servers.*

![🌍 Spigot](https://img.shields.io/badge/Platform-Spigot-green)

---

## 📌 Overview  
**JodelleSimpleSpawn** is a straightforward plugin that teleports players to a predefined spawn location **every time they join your server**. With a simple in-game command, admins can set the spawn point, and the plugin takes care of the rest.

💖 If you enjoy using this plugin, consider [donating](https://www.paypal.com/donate?hosted_button_id=QG8WUHMEEBXWW) to support future updates and development!

---

## ✨ Features  

✔ **Teleport on Join** – Players are automatically teleported to a saved location when they join.  
✔ **Set Spawn Easily** – Use an in-game command to update the spawn point.  
✔ **Permission-Based Access** – Only authorized users can set the spawn.  
✔ **Clean, Colorful Feedback** – Helpful messages for players and clear logging for admins.  
✔ **Simple Configuration** – Enable or disable teleportation through the config file.

---

## 📥 Installation  

1. Download the latest `.jar` file from the [Releases](https://github.com/mgl23606/JodelleSimpleSpawn/releases).  
2. Drop it into the `plugins` folder of your **Spigot** server.  
3. Start or reload your server.  
4. Use `/jsp setspawn` to define the teleport location.  

---

## 🔧 Configuration  

A `config.yml` file is generated automatically on first run.  
Here's what it includes:

```yaml
enabled: true
spawn:
  x: 0.0
  y: 64.0
  z: 0.0
