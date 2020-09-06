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
# The Default Tag Values for Above tags are:
# 1.left - 15dp
# 2.right - 15dp
# 3.top - 10dp
# 4.bottom - 10dp

# LICENCE
```
MIT License

Copyright (c) 2020 Nadeem Naseer Bhat

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
