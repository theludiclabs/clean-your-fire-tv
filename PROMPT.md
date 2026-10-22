# Assistant-guided Fire TV setup

Use this repository as context with a coding assistant that can access your computer's terminal. On-device work requires the TV to be reachable over ADB. Fill in the TV model before starting.

---

I want to review and clean up my real Fire TV safely. The TV model is: **[enter exact model]**. Read this repository's README and this prompt before making changes. Work step by step, and verify device changes with screenshots pulled from the TV when the required tools are available.

This is a Fire TV cleanup workflow. Fire OS cannot be removed by this project, and a third-party launcher may not be allowed to take over the remote's Home button. Do not promise that Clean Home replaces Fire OS or becomes the default home screen.

## Connect

- Find the TV on my local network, or ask me for its IP address. Connect over ADB on port 5555.
- Tell me when the **Allow USB debugging?** prompt will appear so I can accept it.
- If stock `adb` on macOS returns `No route to host` even though the TV is reachable, treat macOS Local Network privacy as the likely cause. Use Python's `adb-shell` in a virtual environment as the ADB client instead of spending time debugging the ADB binary.
- If pushing a large file hangs, transfer base64 chunks through the shell and verify the MD5 checksum.

## Back up before changes

Save the following to a dated backup folder before changing settings or packages:

- Full installed package list and disabled-package list.
- `getprop` output.
- System, global, and secure settings lists, where the firmware permits reading them.
- A restore script that re-enables every package you disable.

Do not include Wi-Fi credentials or other secrets in public files, screenshots, or logs. Keep the backup local unless I explicitly ask to publish it.

## Review package candidates

- Inventory installed packages and classify them as manufacturer telemetry/analytics, manufacturer content and advertising, background utilities, preloaded apps, and streaming apps.
- Explain what each candidate appears to do and whether disabling it could affect device features.
- Never uninstall system packages. Use `pm disable-user --user 0` only for individual packages I approve.
- Keep everything enabled unless I explicitly identify it for disabling. Before touching streaming apps, Alexa or other voice control, casting/AirPlay/Miracast, or the update service, ask me with a checklist and disable only items I explicitly choose.
- Run each `pm` command in its own shell call and verify its result. Do not chain multiple package changes.
- Fire OS firmware may reject package disabling. If a command fails, report the exact limitation and leave the package enabled.

## Optional launcher

Do not build or install the optional Clean Home launcher unless I choose it. If I do choose it:

- Read the README and use the repository's `build-and-install.sh` script.
- Explain that it is an app, not a replacement OS, and Fire TV may continue opening Amazon Home when I press Home.
- Explain that boot auto-launch is best effort and may be blocked by some Fire OS builds; test it on this model before claiming it works.
- Do not set it as the default Home app unless the TV exposes a supported setting and I choose that option.
- On Fire OS versions that do not support a third-party default launcher, explain how to open Clean Home from **Your Apps & Channels** or `open-clean-home.sh TV_IP`.
- After installation, send remote D-pad input over ADB where supported, capture a screenshot from the TV, and inspect it before calling the install complete.

Ask about preferences before changing the dock order, wallpaper, greeting, or package disable list. At the end, tell me what worked, what Fire OS blocked, where the backup and restore script are, and how to re-enable a package.
