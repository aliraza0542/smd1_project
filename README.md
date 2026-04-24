# StockMaster — Android Studio Project


##  Features
- Inventory management with search and filter functionality  
- Sales analytics with Daily, Weekly, and Monthly views  
- POS system with cart and live total calculation  
- Smooth navigation using fragments and tab layout  
- Clean UI using modern Android layout patterns  

---

## ⚙️ Requirements
- Android Studio (latest version recommended)  
- Minimum SDK: API 24+  
- Android device or emulator  

## How to Open

1. Extract `StockMaster.zip`
2. Open **Android Studio**
3. Select **File → Open** and choose the extracted `StockMaster/` folder
4. Let Android Studio sync the Gradle files (first sync downloads dependencies)
5. Click **Run ▶** to build and deploy to a device or emulator (API 24+)

## Architecture

| File | Purpose |
|---|---|
| `SplashActivity.kt` | Entry point; passes user data via `Intent Extras` to `MainActivity` |
| `MainActivity.kt` | Hosts 4-tab navigation; performs `Fragment Transactions` |
| `InventoryFragment.kt` | `RecyclerView` + real-time search/filter (F3, F5) |
| `AnalyticsFragment.kt` | `RadioGroup` period toggle (L9) + `TableLayout` (L7) |
| `POSFragment.kt` | Cart quantity controls + live price totals |
| `ItemDetailFragment.kt` | Receives `Product` via `Bundle` argument (F2) |
| `InventoryAdapter.kt` | Custom `RecyclerView.Adapter` + `ViewHolder` (F3) |
| `Product.kt` | `Parcelable` data model with sample data |

## Layout Patterns Covered

| Tag | Layout | Where Used |
|---|---|---|
| L1 | CoordinatorLayout + CollapsingToolbarLayout | `fragment_inventory.xml` |
| L2 | ConstraintLayout + Barrier + Guideline + Chain | `item_inventory_card.xml` |
| L3 | LinearLayout with `layout_weight="1"` | `layout_bottom_nav.xml` |
| L4 | RelativeLayout with `layout_toRightOf`, `layout_alignParentEnd` | `layout_filter_row.xml` |
| L5 | FrameLayout (notification badge overlay) | `layout_topbar.xml` |
| L6 | GridLayout 2×2 | `layout_stats_grid.xml` |
| L7 | TableLayout with `stretchColumns` | `fragment_analytics.xml` |
| L8 | HorizontalScrollView for chips | `layout_filter_row.xml` |
| L9 | RadioGroup (Daily/Weekly/Monthly) | `fragment_analytics.xml` |
| L10 | ConstraintLayout Flow for tag wrapping | `fragment_analytics.xml` |

## Colors

- `colorPrimaryDark` = `#1A1A2E`
- `colorAccent` = `#00BFA5`
- `colorBackground` = `#EBF4FB`
- `colorWarning` = `#FF7043`
- `colorError` = `#FF5252`
