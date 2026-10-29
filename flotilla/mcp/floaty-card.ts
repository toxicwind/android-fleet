export function floatyCardHtml(opts: {
  message: string;
  sub?: string;
  accent?: string;
  secs?: number;
  fontB64?: string;
}): string {
  const msg = String(opts.message ?? "").slice(0, 400);
  const sub = String(opts.sub ?? "").slice(0, 200);
  const accent = /^#[0-9a-fA-F]{3,8}$/.test(String(opts.accent ?? "")) ? String(opts.accent) : "#7CFC00";
  const secs = Math.max(0, Math.floor(Number(opts.secs ?? 0)));
  const fontB64 = opts.fontB64 ?? "";
  return `<!doctype html><html><head><meta charset=utf-8><meta name=viewport content="width=device-width,initial-scale=1"><style>
@font-face{font-family:DM;src:url(data:font/otf;base64,${fontB64}) format("opentype")}
html,body{margin:0;height:100%;background:transparent;font-family:DM,monospace;overflow:hidden}
.card{position:fixed;inset:auto 12px max(18px,env(safe-area-inset-bottom)) 12px;
background:#0b0d10f2;border:2px solid ${accent};border-radius:14px;padding:18px 20px;
color:#e8f6e8;animation:floaty .45s ease-out;box-shadow:0 8px 40px #000a,0 0 0 1px #0006 inset;
backdrop-filter:blur(6px);-webkit-backdrop-filter:blur(6px)}
.card::before{content:"";position:absolute;inset:0;border-radius:12px;pointer-events:none;
background:repeating-linear-gradient(0deg,transparent 0 3px,#00000022 3px 4px)}
.tag{font-size:10px;letter-spacing:2px;color:#5c7264;margin-bottom:10px;text-transform:uppercase}
.msg{font-size:22px;line-height:1.35;letter-spacing:.5px;color:${accent};
text-shadow:0 0 10px ${accent}66;word-break:break-word}
.sub{font-size:13px;margin-top:10px;color:#9fb3a8;letter-spacing:.3px;word-break:break-word}
@keyframes floaty{from{transform:translateY(24px);opacity:0}to{transform:translateY(0);opacity:1}}
@media(prefers-reduced-motion:reduce){.card{animation:none}}
</style></head><body><div class=card><div class=tag>&#9679; flotilla channel</div>
<div class=msg></div>${sub ? `<div class=sub></div>` : ""}</div>
<script>document.querySelector('.msg').textContent=${JSON.stringify(msg)};${sub ? `document.querySelector('.sub').textContent=${JSON.stringify(sub)};` : ""}${secs > 0 ? `setTimeout(()=>close(),${secs * 1000});` : ""}</script>
</body></html>`;
}
