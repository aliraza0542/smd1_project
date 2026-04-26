package com.stockmaster.models

import android.os.Parcel
import android.os.Parcelable

data class Product(
    val id: Int = 0,
    val name: String,
    val sku: String,
    val category: String,
    val price: Double,
    val stock: Int,
    val status: String,
    val description: String = "",
    val thumbnailUrl: String = "",
    val createdAt: Long = System.currentTimeMillis()
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(sku)
        parcel.writeString(category)
        parcel.writeDouble(price)
        parcel.writeInt(stock)
        parcel.writeString(status)
        parcel.writeString(description)
        parcel.writeString(thumbnailUrl)
        parcel.writeLong(createdAt)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product = Product(parcel)
        override fun newArray(size: Int): Array<Product?> = arrayOfNulls(size)

        fun getSampleProducts(): List<Product> = listOf(
            Product(1, "Cotton Fabric Roll", "FAB-CTN-001", "Fabric", 79.0, 4, "critical", "Premium combed cotton roll"),
            Product(2, "Linen Fabric Roll", "FAB-LIN-002", "Fabric", 95.0, 9, "low", "Breathable linen material"),
            Product(3, "Denim Jeans - Blue", "APP-DNM-003", "Apparel", 49.0, 28, "in_stock", "Classic fit denim jeans"),
            Product(4, "Formal Shirt - White", "APP-SHT-004", "Apparel", 32.0, 18, "in_stock", "Wrinkle resistant shirt"),
            Product(5, "Tailor Scissors", "ACC-TLR-005", "Accessories", 15.5, 12, "in_stock", "Stainless steel tailor scissors"),
            Product(6, "Polyester Thread Pack", "ACC-THR-006", "Accessories", 7.99, 7, "low", "20-spool assorted thread pack"),
            Product(7, "Khaki Trousers", "APP-TRS-007", "Apparel", 39.0, 2, "critical", "Slim fit cotton trousers"),
            Product(8, "Printed Rayon Fabric", "FAB-RYN-008", "Fabric", 88.0, 21, "in_stock", "Soft printed rayon roll")
        )
    }
}
