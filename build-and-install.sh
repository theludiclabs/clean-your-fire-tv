#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "$0")" && pwd)"
TV_IP="${1:-${TV_IP:-}}"

if [[ -z "$TV_IP" ]]; then
  echo "Usage: $0 TV_IP" >&2
  exit 2
fi
if ! command -v adb >/dev/null 2>&1; then
  echo "adb not found. Install Android Platform Tools and add adb to PATH." >&2
  exit 1
fi

"$ROOT/launcher/gradlew" -p "$ROOT/launcher" assembleDebug --console=plain
adb connect "${TV_IP}:5555"
adb -s "${TV_IP}:5555" install -r "$ROOT/launcher/app/build/outputs/apk/debug/app-debug.apk"
adb -s "${TV_IP}:5555" shell am start -n com.theludiclabs.cleanhome/.MainActivity
