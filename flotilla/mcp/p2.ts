// probe-final.ts — test flotilla device ops (correct imports)
import { shellText, ensureDevice } from "./device.ts";
import { currentFocus, screencap } from "./ops.ts";

const s = await shellText("echo flotilla-adbkit-OK");
console.log("shellText:", JSON.stringify(s.trim()));
const f = await currentFocus();
console.log("focus:", f.slice(0, 90));
try {
  const cap = await screencap("/tmp/test-flotilla.png");
  console.log("screencap bytes:", cap.bytes);
} catch (e) {
  console.log("screencap ERR:", (e as Error).message.slice(0, 120));
}
const dev = await ensureDevice();
console.log("device id:", dev.id);