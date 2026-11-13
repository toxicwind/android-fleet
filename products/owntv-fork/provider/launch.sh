#!/bin/bash
# Launch the master Xtream provider gateway on :8081.
#
# Sources come from fleet_portals.csv (the tracked SSOT at
# fleet-tv/app/src/main/assets/fleet_portals.csv) — every non-dead portal becomes
# an upstream `xtream|id=sN|name=...` entry. Override with PROVIDER_SOURCES to
# curate (e.g. a hand-picked subset for a smaller catalog).
#
# Usage: ./launch.sh            (defaults: master/master on 0.0.0.0:8081)
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
      const [url, user, pass] = l.split(",");
      if (!url || !user || !pass) continue;
      i++;
      const name = url.replace(/^https?:\/\//, "").replace(/:\d+$/, "");
      parts.push(`xtream|id=s${i}|name=${name}|url=${url}|user=${user}|pass=${pass}`);
    }
    console.log(parts.join(";"));
  ' "$CSV")"
fi

export PROVIDER_SOURCES \
  PROVIDER_USER="${PROVIDER_USER:-master}" \
  PROVIDER_PASS="${PROVIDER_PASS:-master}" \
  PROVIDER_PORT="${PROVIDER_PORT:-8081}" \
  PROVIDER_HOST="${PROVIDER_HOST:-0.0.0.0}" \
  PROVIDER_CATALOG_TTL_MS="${PROVIDER_CATALOG_TTL_MS:-600000}" \
  PROVIDER_EPG_REFRESH_MS="${PROVIDER_EPG_REFRESH_MS:-21600000}" \
  PROVIDER_EPG_LOOKAHEAD_DAYS="${PROVIDER_EPG_LOOKAHEAD_DAYS:-2}" \
  PROVIDER_FETCH_TIMEOUT_MS="${PROVIDER_FETCH_TIMEOUT_MS:-30000}" \
  PROVIDER_STREAM_CONNECT_TIMEOUT_MS="${PROVIDER_STREAM_CONNECT_TIMEOUT_MS:-15000}" \
  PROVIDER_PROXY_TIMEOUT_MS="${PROVIDER_PROXY_TIMEOUT_MS:-60000}"

LOG="${PROVIDER_LOG:-/tmp/provider.log}"
nohup bun run src/index.ts > "$LOG" 2>&1 &
echo "provider pid $! -> $LOG (${PROVIDER_SOURCES:+sources from env/CSV})"