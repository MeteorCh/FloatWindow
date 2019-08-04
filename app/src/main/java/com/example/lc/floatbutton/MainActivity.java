package com.example.lc.floatbutton;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyFloatDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SpeedDialOverlayLayout mask=findViewById(R.id.mask);
        mask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.openOrCloseMenu();
            }
        });
        dialog=new MyFloatDialog(this, new MyFloatDialog.IOnItemClicked() {
            @Override
            public void onBackItemClick() {
                Toast.makeText(MainActivity.this,"返回",Toast.LENGTH_SHORT).show();
                dialog.openOrCloseMenu();
            }

            @Override
            public void onCloseItemClick() {
                Toast.makeText(MainActivity.this,"关闭",Toast.LENGTH_SHORT).show();
                mask.hide();
                dialog.dismiss();
            }

            @Override
            public void onClose() {
                mask.hide();
            }

            @Override
            public void onExpand() {
                mask.show();
            }
        });
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
