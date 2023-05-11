package com.example.kirill;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyEditText extends androidx.appcompat.widget.AppCompatEditText {

    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public  String text() {
        return super.getText().toString();
    }
}
