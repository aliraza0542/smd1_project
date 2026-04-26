# StockMaster (StitchSmart Assignment Project)

StockMaster is an Android app for retail/textile inventory management built with Kotlin + XML.
It demonstrates Assignment 3 UI/navigation requirements and Assignment 4 data requirements (SQLite CRUD + REST catalog).

## Open and Run

1. Open the project folder in Android Studio.
2. Allow Gradle sync to download dependencies.
3. Run on emulator/device (API 24+).

> Note: In this workspace, `gradlew.bat` currently fails from terminal. Android Studio sync/run is the reliable workflow.

## Project Structure

```text
app/src/main/java/com/stockmaster/
  activities/         SplashActivity, MainActivity
  fragments/          Inventory, ItemDetail, POS, Analytics, BrowseCatalog, AddEditProduct
  adapters/           InventoryAdapter, CatalogAdapter
  models/             Product, Sale, CatalogProduct, SalesSummary
  database/           DatabaseHelper
	dao/              ProductDao, SaleDao
  network/            ApiClient, ApiService
  repository/         ProductRepository
  utils/              FormatUtils
```

## Feature Map

- `SplashActivity` -> `MainActivity` uses `Intent` extras (`USER_NAME`, `USER_ROLE`, etc.).
- `MainActivity` hosts bottom-nav fragment transactions for Dashboard/Inventory, POS, Analytics, Catalog.
- `InventoryFragment` provides SQLite-backed product list with search, status filters, sorting, add/edit, swipe delete (undo), and product detail routing.
- `ItemDetailFragment` receives a `Product` via Bundle (`"product"`) and records sales.
- `BrowseCatalogFragment` fetches global catalog from `https://dummyjson.com/products` via Retrofit and supports local import.
- `AnalyticsFragment` reads SQLite sales totals and renders KPIs.

## Data Layer

- Local source: `SQLiteOpenHelper` (`DatabaseHelper`) with `products` and `sales` tables (FK `sales.product_id` -> `products.id`).
- Remote source: Retrofit (`ApiClient` + `ApiService`) for catalog browsing.
- Repository: `ProductRepository` keeps local inventory and API catalog flows separated.

## Design System Notes

- Primary: `#051125` (`colorPrimaryDark`), Accent: `#006B5F` (`colorAccent`), text primary `#071E27`.
- Cards and list rows use background tiers (`surface_low`, `surface_lowest`) with no divider lines.
- Status visuals use `bg_badge_ok`, `bg_badge_low`, `bg_badge_critical`.

## Contributing

1. Keep feature markers (`F1`-`F5`) and layout markers (`L1`-`L10`) intact in edited files.
2. Preserve literal keys used for navigation/data passing (`"product"`, `"open_inventory_section"`, `"USER_NAME"`, etc.).
3. If adding navigation tabs, update both `layout_bottom_nav.xml` and `MainActivity.kt` highlight logic.
4. Prefer updating existing drawables/colors/resources instead of introducing one-off styles.
