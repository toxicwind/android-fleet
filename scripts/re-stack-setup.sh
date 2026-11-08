#!/usr/bin/env bash
# re-stack-setup.sh — universal reverse-engineering stack (idempotent)
# Installs: jadx, apktool, baksmali, rizin/radare2 (have), ghidra (gh release),
#           floss, androguard, capstone (python venv), binwalk, xxd (have).
# Targets Arch/CachyOS + python venv; non-root. Re-run safely.
set -euo pipefail

VENV_DIR="${RE_VENV:-$HOME/.venvs/re-stack}"
GHIDRA_DIR="$HOME/.local/share/ghidra"
BIN="$HOME/.local/bin"

echo "[re-stack] python venv: $VENV_DIR"
if [ ! -d "$VENV_DIR" ]; then python3 -m venv "$VENV_DIR"; fi
# shellcheck disable=SC1091
source "$VENV_DIR/bin/activate"

echo "[re-stack] pip deps..."
pip install -q --upgrade androguard "floss[all]" capstone

echo "[re-stack] pacman deps (idempotent)..."
for pkg in jadx apktool baksmali rizin radare2 binwalk; do
  pacman -Q "$pkg" >/dev/null 2>&1 || { echo "install $pkg"; sudo pacman -S --noconfirm --needed "$pkg" || true; }
done

echo "[re-stack] ghidra from GitHub release..."
if [ ! -d "$GHIDRA_DIR" ]; then
  mkdir -p "$GHIDRA_DIR"
  URL=$(gh release view --repo NationalSecurityAgency/ghidra --json assets -q '.assets[].url' 2>/dev/null | grep -i zipped | head -1)
  if [ -n "$URL" ]; then
    cd /tmp
    gh release download --repo NationalSecurityAgency/ghidra --pattern "ghidra_*_*.zip" --dir /tmp/ghidra-dl --clobber 2>/dev/null || true
    Z=$(ls /tmp/ghidra-dl/*.zip 2>/dev/null | head -1)
    if [ -n "$Z" ]; then unzip -q -o "$Z" -d "$GHIDRA_DIR-raw"; chmod +x "$GHIDRA_DIR-raw"/ghidra_*/ghidraRun; ln -sfn "$GHIDRA_DIR-raw"/ghidra_* "$GHIDRA_DIR" 2>/dev/null || true; fi
  fi
fi
if [ -f "$GHIDRA_DIR"/ghidraRun ]; then ln -sf "$GHIDRA_DIR/ghidraRun" "$BIN/ghidraRun"; fi

echo "[re-stack] symlink all python entrypoints..."
for t in androguard androlyze floss capstone; do
  if command -v "$t" >/dev/null 2>&1; then ln -sf "$(command -v "$t")" "$BIN/$t" 2>/dev/null || true; fi
done

echo "[re-stack] FINAL VERIFY:"
for t in jadx apktool baksmali rizin radare2 binwalk ghidraRun floss androguard capstone xxd objdump nm readelf; do
  if command -v "$t" >/dev/null 2>&1 || [ -x "$BIN/$t" ]; then echo "  OK  $t"; else echo "  MISS $t"; fi
done