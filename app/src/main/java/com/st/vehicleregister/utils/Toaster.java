package com.st.vehicleregister.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.st.vehicleregister.R;

public class Toaster {
    public static void showToast(Context context, String message) {
        Toast toast = new Toast(context);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);
        TextView textView = (TextView) view.findViewById(R.id.custom_toast_text);
        textView.setText(message);
        toast.setView(view);
        toast.setGravity(Gravity.BOTTOM, 0, 40);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
