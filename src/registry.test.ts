import { describe, expect, it } from "bun:test";
import { DeviceRegistry, CANONICAL_ESTATE_DEVICES, type DeviceProfile } from "./registry";

describe("android-fleet: DeviceRegistry & Multi-Device Profiles", () => {
  it("registers canonical estate devices (Pixel + Hisense TV)", () => {
    const reg = new DeviceRegistry(CANONICAL_ESTATE_DEVICES);
    const list = reg.list();
    expect(list.length).toBe(2);
    expect(reg.get("pixel-9-pro-xl")?.defaultIp).toBe("10.0.0.77");
    expect(reg.get("hisense-wuyishan-tv")?.defaultPort).toBe(5555);
  });

  it("updates active rotating wireless debugging port for Pixel", () => {
    const reg = new DeviceRegistry(CANONICAL_ESTATE_DEVICES);
    expect(reg.getActiveEndpoint("pixel-9-pro-xl")).toBe("10.0.0.77:44933");
    const updated = reg.updateActivePort("pixel-9-pro-xl", 38921);
    expect(updated).toBe(true);
    expect(reg.getActiveEndpoint("pixel-9-pro-xl")).toBe("10.0.0.77:38921");
  });

  it("broadcasts concurrent commands across 2+ authorized devices", async () => {
    const reg = new DeviceRegistry(CANONICAL_ESTATE_DEVICES);
    const results = await reg.broadcastCommand("getprop ro.build.version.release", async (endpoint) => {
      // Mock concurrent command executor
      return {
        stdout: endpoint.startsWith("10.0.0.77") ? "17\n" : "11\n",
        stderr: "",
        exitCode: 0,
      };
    });

    expect(results.length).toBe(2);
    const pixelRes = results.find((r) => r.deviceId === "pixel-9-pro-xl");
    const tvRes = results.find((r) => r.deviceId === "hisense-wuyishan-tv");
    expect(pixelRes?.stdout.trim()).toBe("17");
    expect(tvRes?.stdout.trim()).toBe("11");
  });
});
