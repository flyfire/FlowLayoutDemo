# FlowLayoutDemo
FlowLayoutDemo

Android 流式布局

``compile 'com.solarexsoft.flowlayout:flowlayout:2.0.0'``

<img src="https://github.com/flyfire/FlowLayoutDemo/raw/master/screenshot.png" width="270" height="480"/>

Usage:

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:orientation="vertical">

    <com.solarexsoft.flowlayout.FlowLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <TextView
            style="@style/text_flag_01"
            android:textSize="18sp"
            android:text="hello"/>

        <TextView
            style="@style/text_flag_01"
            android:textSize="19sp"
            android:text="hello123"/>

        <TextView
            style="@style/text_flag_01"
            android:textSize="8sp"
            android:text="你好"/>

        <TextView
            style="@style/text_flag_01"
            android:textSize="20sp"
            android:text="大王叫我来巡山"/>

        <TextView
            style="@style/text_flag_01"
            android:textSize="30sp"
            android:text="我把人间转一转"/>

        <TextView
            style="@style/text_flag_01"
            android:textSize="15sp"
            android:text="helloworld"/>

        <TextView
            style="@style/text_flag_01"
            android:textSize="16sp"
            android:text="worldwonder"/>

        <TextView
            style="@style/text_flag_01"
            android:textSize="15sp"
            android:text="wonderofland"/>

        <TextView
            style="@style/text_flag_01"
            android:textSize="10sp"
            android:text="landscape"/>

        <TextView
            style="@style/text_flag_01"
            android:textSize="5sp"
            android:text="接不下去了"/>

        <TextView
            style="@style/text_flag_01"
            android:textSize="10sp"
            android:text="肿么办"/>


    </com.solarexsoft.flowlayout.FlowLayout>
</LinearLayout>
```