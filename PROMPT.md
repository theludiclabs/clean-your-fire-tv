# Assistant-guided setup prompt

Use this prompt with a coding assistant that has access to this repository, a terminal, and (if you want on-device verification) the TV over ADB. Replace the model placeholder before starting.

---

I want to set up this Clean your Fire TV project on my real Fire TV. The TV model is: **[enter exact model]**. Read this repository's README and scripts before making changes. Work step by step, and verify any on-device change with a screenshot pulled from the TV when the required tools are available.

## Device access and consent

- Find the TV on my local network or ask me for its IP. Connect with ADB over TCP on port 5555.
- Tell me when the **Allow USB debugging?** prompt will appear so I can accept it.
- If stock `adb` on macOS returns `No route to host` even though the TV port is reachable, treat macOS Local Network privacy as a likely cause. Do not spend time debugging the ADB binary; use Python's `adb-shell` in a virtual environment as the ADB client instead. If a large push hangs, transfer base64 chunks through the shell and verify the MD5 checksum.
- Before any cleanup, save the package list, disabled-package list, `getprop`, and settings to a dated backup folder. Create a restore script for every package you disable.

## Review before changing anything

- Inventory installed packages and classify them as manufacturer telemetry/analytics, manufacturer content and advertising, background utilities, preloaded apps, and streaming apps.
- Do not uninstall system packages. Only use `pm disable-user --user 0` for packages we have reviewed.
- Keep everything enabled unless I explicitly identify it for disabling. Ask me with a checklist before touching streaming apps, Alexa/voice assistant, casting/AirPlay/Miracast, or the update service. Ask what apps I use before proposing any app removal/disable list.
- Run each `pm` command in its own shell call and verify its result. Never batch several package changes into one command.
- Fire TV firmware may refuse package disabling. If a command fails, report the exact limitation and leave the package enabled.

## Install and verify Clean Home

- Build and install the native launcher using the repository's `build-and-install.sh` script. Do not install unrelated launchers.
- Fire TV may not allow a third-party default launcher. Do not claim Fire OS has been replaced. If Home still opens Amazon Home, explain how to open Clean Home through **Your Apps & Channels** or `open-clean-home.sh TV_IP`.
- After installation, open Clean Home, send remote D-pad input over ADB where supported, and capture a screenshot from the TV. Inspect the screenshot before calling the setup complete.
- Do not modify the user's dock order, wallpaper, greeting, or disable list without asking about those preferences first.

At the end, summarize what worked, what Fire OS blocked, where the backup and restore script are, and how to return to Clean Home.
