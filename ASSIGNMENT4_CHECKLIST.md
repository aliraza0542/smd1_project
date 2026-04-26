# Assignment 4 Completion Checklist

Status legend: PASS / PARTIAL / BLOCKED

## F1 — REST API Integration
- PASS: Retrofit client exists in `app/src/main/java/com/stockmaster/network/ApiClient.kt` with base URL `https://dummyjson.com/`.
- PASS: API contract exists in `app/src/main/java/com/stockmaster/network/ApiService.kt` with `getProducts(limit)`.
- PASS: Catalog screen exists in `app/src/main/java/com/stockmaster/fragments/BrowseCatalogFragment.kt` with loading/error/empty states and RecyclerView.
- PASS: Glide-based thumbnail loading exists in `app/src/main/java/com/stockmaster/adapters/CatalogAdapter.kt`.
- BLOCKED (environment): CLI Gradle wrapper is broken in this workspace, so dependency resolution/build is not verifiable from terminal.

## F2 — SQLite Schema Design
- PASS: `SQLiteOpenHelper` implementation exists in `app/src/main/java/com/stockmaster/database/DatabaseHelper.kt`.
- PASS: `products` and `sales` tables are created with FK `sales.product_id -> products.id` and `ON DELETE CASCADE`.
- PASS: `onCreate()` seeds at least 8 textile/retail products.

## F3 — CRUD Operations
- PASS: Product CRUD exists in `app/src/main/java/com/stockmaster/database/dao/ProductDao.kt` and runs in `withContext(Dispatchers.IO)`.
- PASS: Sale CRUD exists in `app/src/main/java/com/stockmaster/database/dao/SaleDao.kt` and runs in `withContext(Dispatchers.IO)`.
- PASS: Add product FAB + add/edit bottom sheet exists in `app/src/main/java/com/stockmaster/fragments/InventoryFragment.kt` and `app/src/main/java/com/stockmaster/fragments/AddEditProductFragment.kt`.
- PASS: Swipe-right edit and swipe-left delete with undo delay exist in `app/src/main/java/com/stockmaster/fragments/InventoryFragment.kt`.
- PASS: Record sale flow exists in `app/src/main/java/com/stockmaster/fragments/ItemDetailFragment.kt` and updates stock via repository.

## F4 — Strategy B (Separate Modules)
- PASS: Local inventory module uses SQLite (`InventoryFragment` + DAO/repository path).
- PASS: Global catalog module uses REST (`BrowseCatalogFragment` + Retrofit path).
- PASS: Optional import path is implemented (catalog item -> dialog -> insert local product).

## F5 — Dynamic SQL Queries
- PASS: Search SQL (`name LIKE ? OR sku LIKE ?`) exists in `ProductDao.searchProducts()`.
- PASS: Status filter SQL exists in `ProductDao.filterByStatus()`.
- PASS: Stock and price sort SQL exists in `ProductDao` sort methods.
- PASS: Sales summary JOIN exists in `ProductDao.getSalesSummaryPerProduct()`.
- PASS: Inventory search/chips/sort are wired in `InventoryFragment`.

## Assignment 4 Deliverables
- PASS: Kotlin source files for activities/fragments/adapters/models/database/network/repository are present under `app/src/main/java/com/stockmaster/`.
- PASS: `AndroidManifest.xml` includes `INTERNET` permission and activity declarations.
- PASS: `app/build.gradle` includes Retrofit + Gson converter + Glide + coroutine Android dependency.
- PASS: New XML layouts are present (`fragment_browse_catalog.xml`, `item_catalog_card.xml`, `dialog_import_catalog.xml`, `fragment_add_edit_product.xml`).
- PASS: Logic map content exists in `logic_map.md`.
- PASS: `logic_map.pdf` generated at project root from `logic_map.md`.
- PASS: Root `README.md` updated with project structure and contribution notes.

