package com.ju.lib.utils.system;

import b.c.a.a;
import b.d.a.a.a.b;
import b.d.a.a.a.c;
import b.d.a.a.d;
import b.d.a.a.g;
import b.d.a.a.j;

/* JADX INFO: loaded from: classes2.dex */
public final class SpaceInfo$$JsonObjectMapper extends a<SpaceInfo> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // b.c.a.a
    public SpaceInfo parse(g gVar) {
        SpaceInfo spaceInfo = new SpaceInfo();
        if (((c) gVar).f70b == null) {
            gVar.u();
        }
        if (((c) gVar).f70b != j.START_OBJECT) {
            gVar.v();
            return null;
        }
        while (gVar.u() != j.END_OBJECT) {
            b bVar = (b) gVar;
            j jVar = bVar.f70b;
            String str = (jVar == j.START_OBJECT || jVar == j.START_ARRAY) ? bVar.t.f136c.f139f : bVar.t.f139f;
            gVar.u();
            parseField(spaceInfo, str, gVar);
            gVar.v();
        }
        return spaceInfo;
    }

    @Override // b.c.a.a
    public void parseField(SpaceInfo spaceInfo, String str, g gVar) {
        if ("available".equals(str)) {
            spaceInfo.setAvailable(gVar.t());
            return;
        }
        if ("availableCount".equals(str)) {
            spaceInfo.setAvailableCount(gVar.t());
            return;
        }
        if ("blockCount".equals(str)) {
            spaceInfo.setBlockCount(gVar.t());
            return;
        }
        if ("blockSize".equals(str)) {
            spaceInfo.setBlockSize(gVar.t());
        } else if ("freeCount".equals(str)) {
            spaceInfo.setFreeCount(gVar.t());
        } else if ("total".equals(str)) {
            spaceInfo.setTotal(gVar.t());
        }
    }

    @Override // b.c.a.a
    public void serialize(SpaceInfo spaceInfo, d dVar, boolean z) {
        if (z) {
            dVar.u();
        }
        long available = spaceInfo.getAvailable();
        dVar.b("available");
        dVar.g(available);
        long availableCount = spaceInfo.getAvailableCount();
        dVar.b("availableCount");
        dVar.g(availableCount);
        long blockCount = spaceInfo.getBlockCount();
        dVar.b("blockCount");
        dVar.g(blockCount);
        long blockSize = spaceInfo.getBlockSize();
        dVar.b("blockSize");
        dVar.g(blockSize);
        long freeCount = spaceInfo.getFreeCount();
        dVar.b("freeCount");
        dVar.g(freeCount);
        long total = spaceInfo.getTotal();
        dVar.b("total");
        dVar.g(total);
        if (z) {
            dVar.r();
        }
    }
}
