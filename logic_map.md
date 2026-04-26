# Logic Map

| Requirement ID | Class / File | Function / Method | Description |
|---|---|---|---|
| F1 (API) | `BrowseCatalogFragment.kt` | `loadCatalogFromApi()` | Calls Retrofit-backed repository method in coroutine scope and updates catalog RecyclerView, loading, empty, and error states. |
| F2 (Schema) | `DatabaseHelper.kt` | `onCreate()` | Creates `products` and `sales` tables with foreign key constraint and seeds 8 textile inventory records. |
| F3 (CRUD) | `ProductDao.kt` | `insertProduct()`, `updateProduct()`, `deleteProduct()` | Full product CRUD using SQLite and `withContext(Dispatchers.IO)` for background thread execution. |
| F4 (Strategy) | `ProductRepository.kt` | `getLocalProducts()`, `getApiProducts()` | Keeps SQLite local inventory and REST catalog as separate data sources in one repository boundary. |
| F5 (SQL) | `ProductDao.kt` | `searchProducts()`, `filterByStatus()`, `sortByStockAscending()` | Dynamic SQL (`LIKE`, `WHERE`, `ORDER BY`) for search/filter/sort flows in inventory list. |

