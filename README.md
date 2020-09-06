# AndroidTags

A simple Android Library for Using Tags on Ui with Custom Texts having Many shapes

# For Installation

# Step 1. Add the JitPack repository to your build file
```
Add it in your root build.gradle at the end of repositories:

allprojects 
{
		repositories 
		{
			...
			maven { url 'https://jitpack.io' }
		}
}
```
  
  # Step 2. Add the dependency
  ```
  	dependencies 
	{
	        implementation 'com.github.Nadeembhat:AndroidTags:1.0.0'
	}
```

# Usage Inside Your Layout
```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <in.ernb.shorttags.TagView
        android:id="@+id/tagView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="137dp"
        android:layout_marginBottom="28dp"
        android:padding="5dp"
        android:text="classic"
        app:layout_constraintBottom_toTopOf="@+id/tagView3"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:tagColor="@color/colorAccent"
        app:tagType="classic" />

    <in.ernb.shorttags.TagView
        android:id="@+id/tagView3"
        android:layout_width="96dp"
        android:layout_height="0dp"
        android:layout_marginBottom="508dp"
        android:text="Sharp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/tagView"
        app:tagType="sharp" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
