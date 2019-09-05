package com.example.lc.floatbutton;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    FloatWindow dialog;
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
        dialog=new FloatWindow(this, new FloatWindow.IOnItemClicked() {
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
                dialog.dismiss();
                dialog.show("返回高级查询中的搜索结果");
            }
        });
        Button textColorBtn=(Button)findViewById(R.id.set_text_color);
        textColorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                dialog.show("返回高级查询中有关<font color='red'>“"+"关键字"+"”</font>的搜索结果");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
