#!/usr/bin/env bash
set -euo pipefail

TV_IP="${1:-${TV_IP:-}}"
if [[ -z "$TV_IP" ]]; then
  echo "Usage: $0 TV_IP" >&2
  exit 2
fi
adb connect "${TV_IP}:5555"
adb -s "${TV_IP}:5555" uninstall com.gathin.tvhome
echo "Clean Home removed. Press Home to return to the Amazon launcher."
