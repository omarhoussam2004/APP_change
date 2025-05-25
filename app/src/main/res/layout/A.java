package layout;

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    android:padding="8dp"
    android:background="@drawable/border"
    android:layout_marginBottom="8dp"
    android:gravity="center_vertical">

    <TextView
        android:id="@+id/textConversion"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="100 MAD → 110 EUR"
        android:textSize="16sp" />

    <ImageButton
        android:id="@+id/buttonEdit"
        android:layout_width="36dp"
        android:layout_height="36dp"
        android:src="@android:drawable/ic_menu_edit"
        android:background="?android:selectableItemBackground"
        android:contentDescription="Modifier"
        android:padding="4dp" />

    <ImageButton
        android:id="@+id/buttonDelete"
        android:layout_width="36dp"
        android:layout_height="36dp"
        android:src="@android:drawable/ic_menu_delete"
        android:background="?android:selectableItemBackground"
        android:contentDescription="Supprimer"
        android:padding="4dp"
        android:layout_marginStart="8dp" />
</LinearLayout>
