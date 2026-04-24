package com.stockmaster.models

import android.os.Parcel
import android.os.Parcelable

data class Product(
    val id: Int,
    val name: String,
    val category: String,
    val price: Double,
    val stock: Int,
    val status: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(category)
        parcel.writeDouble(price)
        parcel.writeInt(stock)
        parcel.writeString(status)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product = Product(parcel)
        override fun newArray(size: Int): Array<Product?> = arrayOfNulls(size)

        fun getSampleProducts(): List<Product> = listOf(
            Product(1, "HyperBass Wireless Headphones", "Electronics", 129.00, 2, "critical"),
            Product(2, "Nordic Oak Desk Chair", "Furniture", 345.50, 48, "ok"),
            Product(3, "Pro-Type Mechanical Keyboard", "Electronics", 89.00, 124, "ok"),
            Product(4, "Zenith Smart Watch Pro", "Wearables", 449.00, 8, "low"),
            Product(5, "SonicPro Wireless Earbuds", "Electronics", 249.00, 31, "ok"),
            Product(6, "Vantage Watch 5", "Wearables", 399.00, 15, "ok"),
            Product(7, "ErgoDesk Standing Desk", "Furniture", 699.00, 5, "low"),
            Product(8, "UltraSharp Monitor 27in", "Electronics", 529.00, 3, "critical"),
            Product(9, "Premium Leather Wallet", "Accessories", 89.00, 67, "ok"),
            Product(10, "Minimalist Backpack Pro", "Accessories", 179.00, 22, "ok"),
            Product(11, "Smart Fitness Band", "Wearables", 149.00, 6, "low"),
            Product(12, "4K Webcam Ultra", "Electronics", 199.00, 1, "critical")
        )
    }
}
