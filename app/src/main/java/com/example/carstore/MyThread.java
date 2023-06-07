package com.example.carstore;

import androidx.annotation.NonNull;

import com.evgenii.jsevaluator.JsEvaluator;
import com.evgenii.jsevaluator.interfaces.JsCallback;

public class MyThread extends Thread {

    public MyThread(@NonNull JsEvaluator jsEvaluator) {
        jsEvaluator.evaluate("2 * 17", new JsCallback() {
            @Override
            public void onResult(String result) {
                int s;
                // This method is called in the UI thread.
            }

            @Override
            public void onError(String errorMessage) {
                // Process JavaScript error here.
                // This method is called in the UI thread.
            }
        });
    }
}
