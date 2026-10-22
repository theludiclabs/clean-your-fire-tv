# Clean your Fire TV

A **prompt-first** guide for reviewing and cleaning up a Fire TV safely. Start with [`PROMPT.md`](PROMPT.md), give it and this repository to an assistant that can access your computer and TV over ADB, and follow the TV-specific checklist. The launcher source is an optional companion for devices where sideloaded home screens are useful.

**Design and code credit:** This project was inspired by and its optional launcher is adapted from [Gathin23/CleanYourTV](https://github.com/Gathin23/cleanyourtv). Gathin's MIT copyright and permission notice are preserved in [`LICENSE`](LICENSE); see [`THIRD_PARTY_NOTICES.md`](THIRD_PARTY_NOTICES.md) for details. This repository is independently maintained and is not affiliated with the upstream project.

![Clean Home running on Fire TV](screenshots/clean-home-after.png)

*On-device interface example. This screenshot shows artwork from streaming apps installed on the pictured TV; that artwork is not included in the launcher package and remains the property of its publishers. The Wi-Fi name is masked.*

## What the prompt helps you do

- Back up installed packages, disabled packages, system properties, and settings before making changes.
- Review the TV's installed packages and decide which ones, if any, to disable.
- Keep streaming apps, voice controls, casting, and system updates enabled unless you explicitly choose otherwise.
- Create a restore script for packages disabled during the session.
- Verify device changes with ADB screenshots when the device and tools support it.

The prompt does not automatically disable packages. It instructs the assistant to review candidates with you and issue package commands individually.

## Fire TV limitations

This project does not replace or remove Fire OS, root the TV, or uninstall system packages. Amazon controls whether a third-party app can become the Home-button destination and whether system packages can be disabled.

On the Fire TV Stick tested for this project (AFTT, Fire OS 5.2.8.4), Fire OS rejected attempts to disable manufacturer packages, and the Home button continued to open Amazon Home. Clean Home could still be opened from **Your Apps & Channels** or over ADB. Other models and software versions may behave differently.

### Start here

1. Enable **ADB Debugging** in the TV's Developer Options and find its IP address under **Settings → My Fire TV → About → Network**.
2. Put your computer and TV on the same network.
3. Read [`PROMPT.md`](PROMPT.md), fill in your TV model, and give it and this repository to your coding assistant.
4. Accept the **Allow USB debugging?** prompt on the TV when it appears.
5. Review the backup and package list before authorizing any disabling. Keep the restore script somewhere safe.

If ADB reports `No route to host` on macOS even though the TV is reachable, macOS Local Network privacy may be blocking the terminal. The prompt explains the Python `adb-shell` fallback and chunked transfer option.

## Optional launcher source

The launcher is optional and is not required to run the cleanup prompt. It provides a black, Apple TV inspired home screen with large app artwork, a dock, an app grid, and scenic wallpapers. The launcher requests a best-effort launch after a cold boot on Fire OS versions that allow boot receivers to open an activity. This is not a supported replacement for Amazon Home, does not cover wake-from-sleep on every model, and cannot be guaranteed across Fire OS releases. On the tested Fire OS 5 build, the Home button still returns to Amazon Home; reopen Clean Home through **Your Apps & Channels** or the ADB helper below.

The launcher source is adapted from [Gathin23/CleanYourTV](https://github.com/Gathin23/cleanyourtv), which is MIT licensed. This repository was created independently, so GitHub will not label it as a fork. The source attribution and original MIT notice are retained in [`LICENSE`](LICENSE). We changed the Android package ID to `com.theludiclabs.cleanhome` to give this build its own identity.

To build and install the optional launcher, install Android Platform Tools, Java 17, Android SDK Platform 34, and Android Build Tools, then run:

```bash
git clone https://github.com/theludiclabs/clean-your-fire-tv.git
cd clean-your-fire-tv
./build-and-install.sh TV_IP
```

Accept the debugging prompt on the TV. This builds a debug APK and opens Clean Home; it does not change the default Home app or disable packages. GitHub Actions also builds the optional APK for testing.

If you installed an earlier Clean Home build that used `com.gathin.tvhome`, this renamed build installs as a separate app. Install and verify the new one first. Then, if the old package is your previous Clean Home installation, you can remove it with `adb -s TV_IP:5555 uninstall com.gathin.tvhome`. The uninstall command is deliberately not run by this project's scripts.

If Clean Home is already installed, open it again with:

```bash
./open-clean-home.sh TV_IP
```

To remove only this project's renamed optional launcher app:

```bash
./uninstall.sh TV_IP
```

This does not re-enable any packages disabled during cleanup. Use the restore script created during your setup, or run `adb -s TV_IP:5555 shell pm enable --user 0 PACKAGE_NAME` for each package you chose to disable.

## Customize the optional launcher

- **Dock:** Focus an app and press Menu or hold OK to pin/unpin it or move it left/right. Defaults are in `launcher/app/src/main/res/values/config.xml`.
- **Wallpaper:** Focus Settings and press Menu or hold OK. Choose Dynamic, Black, Morning, Afternoon, Evening, or Night.
- **Your own wallpaper:** Replace the matching `bg_*.jpg` images in `launcher/app/src/main/res/drawable-nodpi/`, preserving filenames, then rebuild. Sources and terms are listed in [`CREDITS.md`](CREDITS.md).

## Repository map

| Path | Purpose |
| --- | --- |
| [`PROMPT.md`](PROMPT.md) | Main entry point: safety-first, assistant-guided setup checklist |
| `launcher/` | Optional native Android TV launcher source |
| `build-and-install.sh` | Builds and opens the optional launcher over ADB |
| `open-clean-home.sh` | Reopens the optional launcher over ADB |
| `uninstall.sh` | Removes only the optional launcher app |
| `screenshots/` | Masked on-device interface example |
| [`CREDITS.md`](CREDITS.md) | Wallpaper and other asset attributions |
| [`THIRD_PARTY_NOTICES.md`](THIRD_PARTY_NOTICES.md) | Upstream code and dependency notices |

## Names and trademarks

“Fire TV,” “Fire OS,” “Apple TV,” and the streaming service names identify products or installed apps for compatibility and comparison. This independent project is not affiliated with Amazon, Apple, or any streaming service. Their names, marks, and app artwork belong to their respective owners.

## License

Project source is available under the MIT License. The launcher uses Android's built-in sans-serif fonts instead of bundling font files. Photos, AndroidX RecyclerView, and adapted upstream launcher code retain their own licenses and notices; see [`LICENSE`](LICENSE), [`THIRD_PARTY_NOTICES.md`](THIRD_PARTY_NOTICES.md), and [`CREDITS.md`](CREDITS.md).
