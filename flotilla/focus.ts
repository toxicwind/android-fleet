/**
 * focus.ts — flotilla focus-parsing pure logic (extracted from server.ts for TDD).
 *
 * The flotilla covenant: input tools land on the FOCUSED window; reading tools
 * (open/screenshot) must never leave the user's foreground app displaced.
 * These pure helpers parse `dumpsys window` focus lines and decide when a
 * restore is needed — no adb side effects here, so they're unit-testable.
 */

/** Extract the package from a focus line (`mCurrentFocus=Window{.. pkg/act}`) or null. */
export function focusPackage(focus: string): string | null {
	const m = focus.match(/pkg\/([^\s}]+)/);
	if (m?.[1]) {
		const pkg = m[1].split("}")[0]!;
		const slash = pkg.indexOf("/");
		return slash > 0 ? pkg.slice(0, slash) : pkg;
	}
	const m2 = focus.match(/mCurrentFocus=.*?([A-Za-z0-9_.]+\/[A-Za-z0-9_.]+)/);
	return m2?.[1]?.split("/")[0] ?? null;
}

/** True when `pkg` owns the given focus line. */
export function isFocusOn(focus: string, pkg: string): boolean {
	const p = focusPackage(focus);
	return p !== null && p === pkg;
}

/** True when the focus line is a browser window (fenix/chrome/geckoview/webview). */
export function isBrowserFocus(focus: string): boolean {
	const pkg = focusPackage(focus);
	return pkg ? /fenix|chrome|geckoview|webview/i.test(pkg) : false;
}

/** Strip a port from a device serial (`10.0.0.77:42357` -> `10.0.0.77`). */
export function deviceHost(serial: string): string {
	const i = serial.lastIndexOf(":");
	return i > 0 ? serial.slice(0, i) : serial;
}