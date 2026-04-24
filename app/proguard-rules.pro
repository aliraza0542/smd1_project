# StockMaster ProGuard Rules

# Keep Parcelable model classes
-keep class com.stockmaster.models.** { *; }

# Keep Activity and Fragment classes
-keep class com.stockmaster.activities.** { *; }
-keep class com.stockmaster.fragments.** { *; }

# Keep RecyclerView adapters
-keep class com.stockmaster.adapters.** { *; }

# Material Components
-keep class com.google.android.material.** { *; }

# AndroidX
-keep class androidx.** { *; }
