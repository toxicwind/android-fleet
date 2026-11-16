#!/usr/bin/env bash
# migrate-provider-port.sh — move an OwnTV install's stored provider endpoint.
#
#   tools/migrate-provider-port.sh verify   [serial]
#   tools/migrate-provider-port.sh apply    [serial]
#
# The Xtream provider gateway moved 8081 -> 25818. The port is embedded in every
# stored absolute URL (sources.url, channels.streamUrl, EPG links, catch-up
# templates), so the move needs a sweep over every text column of every table.
#
# Host scoping is mandatory: the library also holds third-party panels that
# genuinely serve on 8081, and a bare ":8081" sweep would break them.
#
# Verification goes through SQL, never grep. SQLite does not zero freed pages,
# so a correctly migrated file still contains the old string in freelist space;
# only a live-row query is an honest answer.
set -euo pipefail

SERIAL="${2:-10.0.0.225:5555}"
HOST="10.0.0.218"
FROM="8081"
TO="25818"
PKG="tv.own.owntv"
DBDIR="/data/data/$PKG/databases"
STAGE="/data/local/tmp"
HERE="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
TOOL="$HERE/tools/set-provider-port.ts"
WORK="$HERE/.migrate-work"

sh() { adb -s "$SERIAL" shell "$1"; }
as_root() { adb -s "$SERIAL" shell "run-as $PKG sh -c '$1'"; }

pull_db() { # $1 = dest
	sh "rm -f $STAGE/m.db $STAGE/m.db-wal"
	sh "run-as $PKG cp $DBDIR/owntv.db $STAGE/m.db"
	sh "chmod 666 $STAGE/m.db"
	adb -s "$SERIAL" pull "$STAGE/m.db" "$1" >/dev/null 2>&1
	# Carry the WAL so committed-but-uncheckpointed rows are not lost.
	sh "run-as $PKG cp $DBDIR/owntv.db-wal $STAGE/m.db-wal 2>/dev/null || true"
	sh "chmod 666 $STAGE/m.db-wal 2>/dev/null || true"
	adb -s "$SERIAL" pull "$STAGE/m.db-wal" "$1-wal" >/dev/null 2>&1 || true
	sh "rm -f $STAGE/m.db $STAGE/m.db-wal"
}

case "${1:-verify}" in
verify)
	mkdir -p "$WORK"
	pull_db "$WORK/verify.db"
	bun "$TOOL" "$WORK/verify.db" --host "$HOST" --from "$FROM" --to "$TO" --check
	rm -rf "$WORK"
	;;
apply)
	sh "am force-stop $PKG"
	mkdir -p "$WORK"
	pull_db "$WORK/live.db"
	bun "$TOOL" "$WORK/live.db" --host "$HOST" --from "$FROM" --to "$TO"
	adb -s "$SERIAL" push "$WORK/live.db" "$STAGE/new.db" >/dev/null
	sh "chmod 666 $STAGE/new.db"
	sh "run-as $PKG sh -c 'cp $STAGE/new.db $DBDIR/owntv.db && chmod 660 $DBDIR/owntv.db && rm -f $DBDIR/owntv.db-wal $DBDIR/owntv.db-shm'"
	sh "rm -f $STAGE/new.db"
	rm -rf "$WORK"
	sh "monkey -p $PKG -c android.intent.category.LAUNCHER 1" >/dev/null 2>&1 || true
	echo "applied; run 'verify' to confirm"
	;;
*)
	echo "usage: $0 {verify|apply} [adb-serial]" >&2
	exit 2
	;;
esac