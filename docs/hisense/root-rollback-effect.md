# ROOT-APPLY rollback — canonical `effect` primitives

No custom monad: use the estate-standard `effect` library
(`pens/estate/docs/typescript-monadic-standard.md`). The primitives:

- `Effect.gen` — sequential steps.
- `Effect.tryPromise` — step with labeled error.
- `Effect.acquireRelease(acquire, release)` — step + its inverse; release runs
  automatically on failure (the "monadic catch" primitive).
- `Effect.scoped` — wraps the whole pipeline; on ANY failure the scope's
  finalizers run **LIFO** (the estate's rollback-on-fail order).

```typescript
import { Effect } from "effect";

const step = (name: string, cmd: string) =>
  Effect.tryPromise({
    try: () => run(name, cmd),               // real exec: adb shell …
    catch: (e) => new Error(`${name} failed: ${String(e)}`),
  });

const applyRoot = Effect.gen(function* () {
  // acquireRelease: on failure, its `release` runs immediately.
  yield* Effect.acquireRelease(
    step("plant-cvt", "touch /mnt/usb/FAC_BOOT_SMART.cvt"),
    () => step("unplant", "rm /mnt/usb/FAC_BOOT_SMART.cvt").pipe(Effect.ignore),
  );
  yield* Effect.acquireRelease(
    step("wait-remount", "until [ -w / ]; do sleep 1; done"),
    () => Effect.sleep("500ms").pipe(Effect.ignore), // stick out next boot
  );
  yield* Effect.acquireRelease(
    step("su-shim", "cp /system/bin/toybox /system/xbin/su && chmod 6755 /system/xbin/su"),
    () => step("rm-shim", "rm -f /system/xbin/su").pipe(Effect.ignore),
  );
  yield* Effect.acquireRelease(
    step("shizuku-root", "su -c CLASSPATH=$(pm path moe.shizuku.privileged.api…) app_process …"),
    () => step("kill-server", "killall shizuku_server").pipe(Effect.ignore),
  );
  const uid = yield* step("verify", "id");
  if (!uid.includes("uid=0")) return yield* Effect.fail(new Error("root not reached"));
  return uid;
});

const run = Effect.runPromise(Effect.scoped(applyRoot));
```

Behavior on failure (verified by the estate standard):
1. The failing step's `acquireRelease` release runs immediately.
2. `Effect.scoped` then runs the outer finalizers LIFO (deepest first).
3. Errors are labeled (`<step> failed`), never swallowed — log tag `monadicRollback`.

This is the FULL implementation note; no custom Result/rollback monad needed.
Fallback for dry-run simulation (no real exec): stub `run()` with an in-memory
map — same Effect structure, zero custom primitives.