package com.example.carstore;

import androidx.appcompat.app.AppCompatActivity;

import com.evgenii.jsevaluator.JsEvaluator;
import com.evgenii.jsevaluator.interfaces.JsCallback;

import kotlin.reflect.KFunction;

public class JavascriptActivity extends AppCompatActivity {
    JsEvaluator jsEvaluator = new JsEvaluator(this);

    public JavascriptActivity() {

        runOnUiThread(new MyThread(jsEvaluator));

    }
    public int run(){
        jsEvaluator.evaluate("2 * 17", new JsCallback() {
            @Override
            public void onResult(String result) {
                // Process result here.
                // This method is called in the UI thread.
            }

            @Override
            public void onError(String errorMessage) {
                // Process JavaScript error here.
                // This method is called in the UI thread.
            }
        });
        return 1;
    }
}
