// probe2.ts — test the REAL exported ops (currentFocus, screencap, openAndCapture)
import { currentFocus, screencap } from "./ops.ts";
import { ensureDevice } from "./device.ts";

const serial = await ensureDevice();
console.log("serial:", serial);
const f = await currentFocus();
console.log("focus:", f.slice(0, 80));
const cap = await screencap("/tmp/t3.png");
console.log("screencap bytes:", cap.bytes);
console.log("DONE");