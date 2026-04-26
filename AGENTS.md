# AGENTS.md

## Project Snapshot
- Single-module Android app (`:app`) with package `com.stockmaster` (`settings.gradle`, `app/build.gradle`).
- This is a UI-heavy demo app: most data is hardcoded in code/layouts, not loaded from network or storage.
- App entry is `SplashActivity` -> `MainActivity` (`app/src/main/AndroidManifest.xml`).

## Big-Picture Architecture
- `MainActivity` owns navigation and swaps fragments into `R.id.fragment_container` (`app/src/main/java/com/stockmaster/activities/MainActivity.kt`, `app/src/main/res/layout/activity_main.xml`).
- Bottom nav IDs (`nav_dashboard`, `nav_inventory`, `nav_pos`, `nav_analytics`) are wired manually to fragment transactions; highlighting uses `alpha` changes.
- Dashboard/Inventory are two sections inside `InventoryFragment`: when already on that fragment, nav taps call `scrollToDashboardSection()`/`scrollToInventorySection()` instead of replacing the fragment (`MainActivity.setupBottomNav()`, `InventoryFragment`).
- Inventory flow is the only multi-step data path:
  - `Product.getSampleProducts()` seeds data (`app/src/main/java/com/stockmaster/models/Product.kt`).
  - `InventoryFragment` filters that list and feeds `InventoryAdapter`.
  - Item click passes `Product` via Bundle key `"product"` to `ItemDetailFragment`, which pops back stack on back button.
- POS (`POSFragment`) and Analytics (`AnalyticsFragment`) keep local in-memory UI state only; no shared repository/viewmodel layer.
- `DashboardFragment.kt` exists but is not currently wired from `MainActivity` navigation.

## Project-Specific Conventions
- Source is annotated with feature/layout markers (`F1`-`F5`, `L1`-`L10`) in comments; preserve these tags when editing related areas.
- Status/category logic depends on exact strings (`"critical"`, `"low"`, `"ok"`, `"Electronics"`) in multiple files (`Product.kt`, `InventoryFragment.kt`, `InventoryAdapter.kt`, `ItemDetailFragment.kt`).
- Fragment argument and Intent-extra keys are literal strings (e.g., `"USER_NAME"`, `"STORE_ID"`, `"product"`); avoid renaming without coordinated updates.
- Combined home-section behavior also depends on literal argument key `"open_inventory_section"` and `MainActivity.updateHomeNavHighlight(0/1)` sync calls from `InventoryFragment`.
- Layout composition is include-based (`layout_topbar`, `layout_filter_row`, `layout_stats_grid`, `layout_bottom_nav`) rather than custom views.

## Build/Test/Debug Workflows
- README workflow assumes Android Studio sync/run (`README.md`).
- Gradle wrapper scripts in repo are truncated and currently fail from CLI (`gradlew`, `gradlew.bat`), so treat Android Studio as the reliable build path unless wrapper scripts are repaired.
- If wrapper is repaired, expected commands are:
  - `./gradlew :app:assembleDebug`
  - `./gradlew :app:lintDebug`
  - `./gradlew :app:testDebugUnitTest`
- There are currently no unit or instrumentation test sources under `app/src/test` or `app/src/androidTest`.
- Debug APK output path: `app/build/outputs/apk/debug/`.

## Integration Points and Dependencies
- Declared libraries are AndroidX/Material UI stack only; no Retrofit/Room/Hilt/etc. (`app/build.gradle`).
- `INTERNET` permission is present, but no network client usage exists in current Kotlin sources.
- Theme + palette are centralized in `app/src/main/res/values/styles.xml` and `app/src/main/res/values/colors.xml`; many screen texts are still hardcoded in layouts/fragments.

## Safe Change Tips for Agents
- When adding a new inventory filter/status, update all dependent string checks and badge visuals together.
- When adding a new tab/fragment, update both `layout_bottom_nav.xml` and `MainActivity.setupBottomNav()`/`updateNavHighlight()`.
- When changing Dashboard/Inventory behavior, update both sides of the sync contract: `MainActivity.updateHomeNavHighlight()` and `InventoryFragment` section methods (`newInstance`, `scrollToDashboardSection`, `scrollToInventorySection`, `notifyHomeSection`).
- Prefer reusing existing drawables/colors (`bg_badge_*`, `bg_chip_*`, `color*`) to stay consistent with the current visual language.
