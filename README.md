# 🚀 StockMaster — Android Inventory Management App

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-green?style=for-the-badge">
  <img src="https://img.shields.io/badge/Language-Kotlin-blue?style=for-the-badge">
  <img src="https://img.shields.io/badge/Min%20SDK-24-orange?style=for-the-badge">
  <img src="https://img.shields.io/badge/Architecture-Fragment--Based-purple?style=for-the-badge">
</p>

<p align="center">
  <b style="color:#00BFA5; font-size:18px;">
    Smart • Fast • Modern Inventory Solution
  </b>
</p>

---

## 👨‍💻 Team Members
<table>
<tr>
<td align="center">🟢 <b>Ali Raza</b></td>
<td align="center">🔵 <b>Yasir Ali</b></td>
<td align="center">🟣 <b>Naeem ul Rehman</b></td>
</tr>
</table>

---

## 📱 Project Overview
StockMaster is a **modern Android inventory management application** developed using **Kotlin**.  
It is designed to manage stock, track sales, and provide quick analytics with an intuitive UI.

🔹 Focus: Clean UI + Efficient Data Handling  
🔹 Approach: Modular Fragment-based Architecture  

---

## ✨ Key Features

### 📦 Inventory Module
- Real-time product search 🔍  
- Category-based filtering  
- RecyclerView with optimized performance  

### 📊 Analytics Module
- Daily / Weekly / Monthly insights  
- Data displayed using TableLayout  
- Interactive UI using RadioGroup  

### 🛒 POS (Point of Sale)
- Add/remove items from cart  
- Live total price calculation  
- Quantity management system  

### 🔍 Product Detail
- Parcelable data transfer  
- Clean detail UI with structured layout  

---

## 🧠 Technical Highlights
- Fragment Transactions for navigation  
- RecyclerView + ViewHolder pattern  
- Intent & Bundle data passing  
- Parcelable data model  
- Modular and scalable structure  

---

## 🧱 Project Architecture

| Layer | Description |
|------|------------|
| UI Layer | Activities + Fragments |
| Adapter Layer | RecyclerView Adapter |
| Data Layer | Product Model (Parcelable) |
| Navigation | Fragment Transactions |

---

## 📂 File Structure

| File | Description |
|------|------------|
| `SplashActivity.kt` | App entry & navigation start |
| `MainActivity.kt` | Controls tabs and fragments |
| `InventoryFragment.kt` | Displays product list |
| `AnalyticsFragment.kt` | Handles analytics UI |
| `POSFragment.kt` | Shopping cart logic |
| `ItemDetailFragment.kt` | Product detail screen |
| `InventoryAdapter.kt` | Adapter for list rendering |
| `Product.kt` | Data model |

---

## 🎨 UI / Layout Design

| Tag | Layout Type | Usage |
|-----|------------|------|
| L1 | CoordinatorLayout | Collapsing toolbar |
| L2 | ConstraintLayout | Item card design |
| L3 | LinearLayout | Bottom navigation |
| L4 | RelativeLayout | Filter section |
| L5 | FrameLayout | Notification badge |
| L6 | GridLayout | Stats display |
| L7 | TableLayout | Analytics |
| L8 | HorizontalScrollView | Filter chips |
| L9 | RadioGroup | Period selection |
| L10 | Constraint Flow | Tag wrapping |

---

## 🎨 App Theme

| Element | Color |
|--------|------|
| Primary Dark | `#1A1A2E` |
| Accent | `#00BFA5` |
| Background | `#EBF4FB` |
| Warning | `#FF7043` |
| Error | `#FF5252` |

---

## ⚙️ Requirements
- Android Studio (Latest Version)  
- Minimum SDK: API 24+  
- Emulator or Physical Device  

---

## 🛠️ Installation Guide
1. Extract project files  
2. Open Android Studio  
3. Click **File → Open**  
4. Select project folder  
5. Wait for Gradle sync  
6. Click **Run ▶**

---

## 🔮 Future Improvements
- Firebase integration 🔥  
- User authentication system  
- Cloud database support  
- Dark mode 🌙  
- Barcode scanner integration  

---

## 📌 Learning Outcomes
- Practical use of Android layouts  
- Fragment-based app development  
- RecyclerView optimization  
- UI/UX design principles  

---

<p align="center">
  <b style="color:#00BFA5;">
     Built with dedication by Team StockMaster 
  </b>
</p>
