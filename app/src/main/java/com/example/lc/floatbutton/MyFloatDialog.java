package com.example.lc.floatbutton;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by wengyiming on 2017/9/13.
 */

public class MyFloatDialog extends BaseFloatDailog {

    public interface IOnItemClicked{
        void onBackItemClick();//返回键按下
        void onCloseItemClick();//关闭键按下
        void onClose();//对话框折叠
        void onExpand();//对话框展开
    }
    IOnItemClicked itemClickedListener;
    public MyFloatDialog(Context context,IOnItemClicked callBack) {
        super(context);
        this.itemClickedListener=callBack;
    }

    @Override
    protected View getLeftView(LayoutInflater inflater, View.OnTouchListener touchListener) {
        View view = inflater.inflate(R.layout.layout_menu_left, null);
        LinearLayout backItem=(LinearLayout)view.findViewById(R.id.back_item);
        backItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickedListener.onBackItemClick();
            }
        });
        LinearLayout closeItem=(LinearLayout)view.findViewById(R.id.close_item);
        closeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickedListener.onCloseItemClick();
            }
        });
        return view;
    }

    @Override
    protected View getRightView(LayoutInflater inflater, View.OnTouchListener touchListener) {
        View view = inflater.inflate(R.layout.layout_menu_right, null);
        LinearLayout backItem=(LinearLayout)view.findViewById(R.id.back_item);
        backItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickedListener.onBackItemClick();
            }
        });
        LinearLayout closeItem=(LinearLayout)view.findViewById(R.id.close_item);
        closeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickedListener.onCloseItemClick();
            }
        });
        return view;
    }

    @Override
    protected View getLogoView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.layout_menu_logo, null);
    }

    @Override
    protected void resetLogoViewSize(int hintLocation, View logoView) {
        logoView.clearAnimation();
        logoView.setTranslationX(0);
        logoView.setScaleX(1);
        logoView.setScaleY(1);
    }

    @Override
    protected void dragingLogoViewOffset(View logoView, boolean isDraging, boolean isResetPosition, float offset) {
        if (isDraging && offset > 0) {
            logoView.setBackgroundDrawable(null);
            logoView.setScaleX(1 + offset);
            logoView.setScaleY(1 + offset);
        } else {
            logoView.setBackgroundResource(R.drawable.float_menu_bg);
            logoView.setTranslationX(0);
            logoView.setScaleX(1);
            logoView.setScaleY(1);
        }


        if (isResetPosition) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                logoView.setRotation(offset * 360);
            }
        } else {
            logoView.setRotation(offset * 360);
        }
    }

    @Override
    public void shrinkLeftLogoView(View smallView) {
        smallView.setTranslationX(-smallView.getWidth() / 3);
    }

    @Override
    public void shrinkRightLogoView(View smallView) {
        smallView.setTranslationX(smallView.getWidth() / 3);
    }

    @Override
    public void leftViewOpened(View leftView) {
        this.itemClickedListener.onExpand();
    }

    @Override
    public void rightViewOpened(View rightView) {
        this.itemClickedListener.onExpand();
    }

    @Override
    public void leftOrRightViewClosed(View smallView) {
        this.itemClickedListener.onClose();
    }

    @Override
    protected void onDestoryed() {
        if(isApplictionDialog()){
            if(getContext() instanceof Activity){
                dismiss();
            }
        }
    }
}
