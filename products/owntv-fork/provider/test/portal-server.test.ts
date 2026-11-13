import { describe, expect, test } from "bun:test";
import { handlePortal } from "../src/portal-server.ts";
import type { VirtualChannel } from "../src/upstreams.ts";

const config = {
  port: 8081,
  host: "0.0.0.0",
  clientUsername: "master",
  clientPassword: "master",
  sources: [],
} as any;

function ch(id: number, name: string, categoryId: string, categoryName: string): VirtualChannel {
  return {
    id,
    sourceId: "s1",
    sourceName: "Test",
    name,
    categoryId,
    categoryName,
    catchup: true,
    catchupDays: 7,
    addedAt: 1,
    upstream: { kind: "xtream", url: "", base: "", streamId: String(id) },
  };
}

const channels = [ch(1001, "BBC One", "cat1", "UK"), ch(1002, "RTL", "cat2", "DE")];

const portal = (url: string) => handlePortal(new URL(url), new Request(url), channels, config);

describe("portal.php serving surface", () => {
  test("handshake mints a token", async () => {
    const res = portal("http://x/portal.php?type=stb&action=handshake&mac=AA:BB:CC:DD:EE:00");
    const body = await res.json() as any;
    expect(body.js.token).toBeTruthy();
  });

  test("get_profile requires the token", () => {
    const res = portal("http://x/portal.php?type=stb&action=get_profile&mac=AA:BB:CC:DD:EE:00");
    expect(res.status).toBe(401);
  });

  test("get_all_channels returns OwnTV-consumable shape", async () => {
    const hs = await portal("http://x/portal.php?type=stb&action=handshake&mac=AA:BB:CC:DD:EE:00").json() as any;
    const token = hs.js.token;
    const res = portal(`http://x/portal.php?type=itv&action=get_all_channels&mac=AA:BB:CC:DD:EE:00&token=${token}`);
    const body = await res.json() as any;
    expect(Array.isArray(body.js)).toBe(true);
    expect(body.js).toHaveLength(2);
    const first = body.js[0];
    expect(first.id).toBe("1001");
    expect(first.name).toBe("BBC One");
    expect(first.tv_genre_id).toBe("cat1");
    expect(first.cmd).toContain("/live/master/master/1001.m3u8");
    expect(first.tv_archive).toBe(1);
  });

  test("create_link echoes our own playable cmd", async () => {
    const hs = await portal("http://x/portal.php?type=stb&action=handshake&mac=AA:BB:CC:DD:EE:00").json() as any;
    const token = hs.js.token;
    const res = portal(`http://x/portal.php?type=itv&action=create_link&cmd=/live/master/master/1001.m3u8&token=${token}`);
    const body = await res.json() as any;
    expect(body.js.cmd).toBe("/live/master/master/1001.m3u8");
  });

  test("get_genres dedupes categories", async () => {
    const hs = await portal("http://x/portal.php?type=stb&action=handshake&mac=AA:BB:CC:DD:EE:00").json() as any;
    const token = hs.js.token;
    const res = portal(`http://x/portal.php?type=itv&action=get_genres&token=${token}`);
    const body = await res.json() as any;
    expect(body.js).toHaveLength(2);
    expect(body.js[0]).toEqual({ id: "cat1", title: "UK" });
  });

  test("get_ordered_list pages with totals (client-walkable)", async () => {
    const hs = await portal("http://x/portal.php?type=stb&action=handshake&mac=AA:BB:CC:DD:EE:00").json() as any;
    const token = hs.js.token;
    const res = portal(`http://x/portal.php?type=itv&action=get_ordered_list&genre=cat1&p=1&token=${token}`);
    const body = await res.json() as any;
    expect(body.js.total_items).toBe(1); // only BBC One is cat1
    expect(body.js.max_page_items).toBe(14);
    expect(body.js.data).toHaveLength(1);
    expect(body.js.data[0].name).toBe("BBC One");
  });

  test("get_ordered_list page 2 returns empty data with correct totals", async () => {
    const hs = await portal("http://x/portal.php?type=stb&action=handshake&mac=AA:BB:CC:DD:EE:00").json() as any;
    const token = hs.js.token;
    const res = portal(`http://x/portal.php?type=itv&action=get_ordered_list&genre=*&p=2&token=${token}`);
    const body = await res.json() as any;
    expect(body.js.total_items).toBe(2);
    expect(body.js.data).toHaveLength(0); // 2 channels, page 2 of 14 → empty
  });
});