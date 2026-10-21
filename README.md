# Clean your Fire TV

A small, native Android TV launcher for a calmer Fire TV home screen. It trades Amazon's promoted rows for a quiet, Apple TV inspired layout with large app artwork, a four app dock, a scrollable app grid, a clock, Wi-Fi name, and scenic wallpapers.

![Recent Clean Home screenshot from Fire TV](screenshots/clean-home-after.png)

The Wi-Fi name is masked in this public screenshot. The displayed dock artwork is from the recent on-device capture; the packaged source now reads app artwork from installed apps at runtime.

## What it does

- Replaces the busy launcher presentation with a clean home screen, a dock, and an app grid, hiding promoted rows while Clean Home is open.
- Uses the banner or icon provided by each installed TV app at runtime. App artwork is not copied into this repository.
- Includes four similar mountain and lake wallpapers for morning, afternoon, evening, and night, plus a wallpaper picker and a black option.
- Keeps the interface light: static images, no video, no blur, and no background network polling.
- Lets you pin, remove, and reorder dock apps with the remote.
- Offers Inputs, Wi-Fi, and Settings shortcuts where the TV exposes the matching system feature.
- Reduces visible clutter and can be paired with selective package disabling on firmware that permits it.

The Apple TV comparison describes the visual inspiration. This project is an independent launcher and does not include Apple TV software or content.

## Fire TV limitations

Clean your Fire TV is a launcher app. It does **not** replace or remove Fire OS, root the TV, or uninstall system packages.

Amazon controls which apps can become the default Home screen and which preloaded packages can be disabled. On the tested Fire TV Stick (AFTT, Fire OS 5.2.8.4), Fire OS rejected attempts to disable manufacturer packages, and the Home button continued to open Amazon Home. No packages were disabled on that device. Other models and software versions may allow more, less, or different behavior.

On devices that allow it, you can disable selected unwanted packages with Android's `pm disable-user` command after reviewing them. This project never removes system packages. Do not disable streaming apps, voice control, casting, or system updates unless you know you do not need them.

Disable only one reviewed package at a time:

```bash
adb -s TV_IP:5555 shell pm disable-user --user 0 PACKAGE_NAME
```

To re-enable that package later:

```bash
adb -s TV_IP:5555 shell pm enable --user 0 PACKAGE_NAME
```

### Open Clean Home again after Amazon Home opens

On the tested Fire OS 5 device:

1. Open **Your Apps & Channels** from Amazon Home.
2. Select **Clean Home**.

Or connect ADB and run `./open-clean-home.sh TV_IP` from this folder. The Home button itself returns to Amazon Home on this Fire OS version.

If a future Fire OS update exposes a **Default Home app** setting, choose **Clean Home** there. If the Home button opens a chooser, choose **Clean Home** and select **Always**. Those options are not available on the tested Fire OS 5 build.

## Install

### 1. Prepare the TV

1. On the TV, open **Settings → My Fire TV → Developer Options** and enable **ADB Debugging**. On newer versions, first enable Developer Options by selecting **About → Fire TV Stick** several times.
2. Make sure your computer and TV are on the same network.
3. Find the TV's IP address under **Settings → My Fire TV → About → Network**.
4. Install Android Platform Tools (`adb`) and Java 17 on your computer.

### 2. Build and install

Clone this repository, open a terminal in its folder, and run:

```bash
./build-and-install.sh TV_IP
```

For example:

```bash
./build-and-install.sh 192.168.1.42
```

The script builds the debug APK, connects to the TV over ADB, installs Clean Home, and opens it. The first connection may show an **Allow USB debugging?** prompt on the TV; accept it. The script does not need root.

Requirements: Java 17, Android SDK Platform 34, Android Build Tools, and `adb` on your PATH. The Gradle wrapper downloads its pinned Gradle version and dependencies as needed.

### Return to Clean Home

If Amazon Home appears, use **Your Apps & Channels → Clean Home**, or run:

```bash
./open-clean-home.sh TV_IP
```

## Dock and wallpapers

- **Dock order:** on a tile, press Menu or hold OK; choose Pin, Unpin, Move Left, or Move Right. The default packages are listed in `launcher/app/src/main/res/values/config.xml`. Missing apps are skipped; available Leanback apps are discovered from the TV.
- **Wallpaper:** focus Settings and press Menu or hold OK. Select Dynamic, Black, Morning, Afternoon, Evening, or Night. Dynamic follows the TV's clock and changes between the four included mountain and lake images. Replace the matching `bg_*.jpg` files in `launcher/app/src/main/res/drawable-nodpi/` to use your own images; keep the same filenames and rebuild.
- **More wallpapers:** the four bundled backgrounds use a consistent mountain and lake theme at different times of day. Their sources and usage terms are documented in [CREDITS.md](CREDITS.md). You can add your own images by replacing those files.

## App icons and licensing

The dock shows the artwork supplied by each installed app through Android's package manager, falling back to that app's launcher icon when it does not provide a TV banner. This keeps icons faithful to the installed apps without copying their logos into the repository. An Apple TV icon will only appear if a compatible Apple TV app is installed and pinned; it was not available on the tested device.

Flaticon offers useful app-logo icons, but its terms restrict redistribution of its content. To keep this repository usable under MIT, no Flaticon downloads are bundled. See [Flaticon's license terms](https://www.flaticon.com/legal/).

## License

The launcher source code is available under the MIT License. Bundled photos and fonts retain their own licenses; see [CREDITS.md](CREDITS.md), [FONT-LICENSE-OFL.txt](FONT-LICENSE-OFL.txt), and the license notices in `launcher/`.

Streaming service and device names are trademarks of their respective owners.
