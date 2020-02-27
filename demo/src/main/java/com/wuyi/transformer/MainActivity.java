package com.wuyi.transformer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.wuyi.transformer.annotation.Layout;

@Layout(R.layout.activity_main)
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        View contentView = Transformer.getView(this);
//        setContentView(contentView);
    }
}

