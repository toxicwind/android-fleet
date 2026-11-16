#!/bin/bash
# Launch the master Xtream provider gateway on :25818.
#
# Sources come from fleet_portals.csv (the tracked SSOT at
# fleet-tv/app/src/main/assets/fleet_portals.csv) — every non-dead portal becomes
# an upstream `xtream|id=sN|name=...` entry. Override with PROVIDER_SOURCES to
# curate (e.g. a hand-picked subset for a smaller catalog).
#
# Usage: ./launch.sh            (defaults: master/master on 0.0.0.0:25818)
#        PROVIDER_SOURCES="..." ./launch.sh
set -euo pipefail
cd "$(dirname "$0")"

CSV="${PROVIDER_CSV:-../../../fleet-tv/app/src/main/assets/fleet_portals.csv}"
if [ -z "${PROVIDER_SOURCES:-}" ] && [ -f "$CSV" ]; then
  PROVIDER_SOURCES="$(bun -e '
    const fs = require("fs");
    const lines = fs.readFileSync(process.argv[1], "utf8").split("\n").filter(l => l && !l.startsWith("#"));
    const parts = [];
    let i = 0;
    for (const l of lines) {
      const [url, user, pass, streamStatus] = l.split(",");
      if (!url || !user || !pass) continue;
      i++;
      const name = url.replace(/^https?:\/\//, "").replace(/:\d+$/, "");
      // streamStatus column (from the VLC-audit) is passed through as status=
      // for the provider to classify (first-class upstream capability), not
      // dropped here — the provider decides playability per source.
      const st = (streamStatus || "").trim();
      const statusField = st === "stream-ok" || st === "no-streams" ? `|status=${st}` : "";
      parts.push(`xtream|id=s${i}|name=${name}|url=${url}|user=${user}|pass=${pass}${statusField}`);
    }
    console.error(`[launch] ${i} sources, streamStatus passthrough`);
    console.log(parts.join(";"));
  ' "$CSV")"
fi

export PROVIDER_SOURCES \
  PROVIDER_USER="${PROVIDER_USER:-master}" \
  PROVIDER_PASS="${PROVIDER_PASS:-master}" \
  PROVIDER_PORT="${PROVIDER_PORT:-25818}" \
  PROVIDER_HOST="${PROVIDER_HOST:-0.0.0.0}" \
  PROVIDER_CATALOG_TTL_MS="${PROVIDER_CATALOG_TTL_MS:-600000}" \
  PROVIDER_EPG_REFRESH_MS="${PROVIDER_EPG_REFRESH_MS:-21600000}" \
  PROVIDER_EPG_LOOKAHEAD_DAYS="${PROVIDER_EPG_LOOKAHEAD_DAYS:-2}" \
  PROVIDER_FETCH_TIMEOUT_MS="${PROVIDER_FETCH_TIMEOUT_MS:-30000}" \
  PROVIDER_STREAM_CONNECT_TIMEOUT_MS="${PROVIDER_STREAM_CONNECT_TIMEOUT_MS:-15000}" \
  PROVIDER_PROXY_TIMEOUT_MS="${PROVIDER_PROXY_TIMEOUT_MS:-60000}" \
  PROVIDER_FREEINFERENCE_KEY="${PROVIDER_FREEINFERENCE_KEY:-$(cat "${FREEINFERENCE_KEY_FILE:-/home/toxic/.tau/freeinference.key}" 2>/dev/null || true)}"

PORT="${PROVIDER_PORT:-25818}"
# Kill any stale process holding our port before starting. This prevents the
# EADDRINUSE race where pitchfork restarts while the crashed predecessor still
# holds the socket — which previously wedged the daemon in "stopped" state.
STALE_PID="$(ss -tlnp 2>/dev/null | grep ":$PORT " | grep -oP 'pid=\K[0-9]+' | head -1 || true)"
if [ -n "${STALE_PID}" ]; then
  echo "[launch] killing stale pid ${STALE_PID} on port ${PORT}"
  kill "${STALE_PID}" 2>/dev/null || true
  # give it a moment to release the socket
  for _ in 1 2 3 4 5; do
    ss -tln 2>/dev/null | grep -q ":$PORT " || break
    sleep 0.2
  done
fi

if [ "${PROVIDER_FOREGROUND:-0}" = "1" ]; then
  # pitchfork daemon mode: stay in the foreground so the supervisor owns and
  # tracks this process (matches the `exec ...` convention in other pens).
  exec bun run src/index.ts
fi

LOG="${PROVIDER_LOG:-$PWD/provider.log}"
nohup bun run src/index.ts > "$LOG" 2>&1 &
echo "provider pid $! -> $LOG (${PROVIDER_SOURCES:+sources from env/CSV})"