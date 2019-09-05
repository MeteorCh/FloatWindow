package com.example.lc.floatbutton;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by wengyiming on 2017/9/13.
 */

public class FloatWindow extends BaseFloatDailog {
    private TextView leftBackText;
    private TextView rightBackText;
    public interface IOnItemClicked{
        void onBackItemClick();//返回键按下
        void onCloseItemClick();//关闭键按下
        void onClose();//对话框折叠
        void onExpand();//对话框展开
    }
    IOnItemClicked itemClickedListener;
    public FloatWindow(Context context, IOnItemClicked callBack) {
        super(context);
        this.itemClickedListener=callBack;
    }

    @Override
    protected View getLeftView(LayoutInflater inflater, View.OnTouchListener touchListener) {
        View view = inflater.inflate(R.layout.widget_float_window_left, null);
        leftBackText=(TextView)view.findViewById(R.id.back_item);
        leftBackText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickedListener.onBackItemClick();
            }
        });
        FrameLayout closeItem=(FrameLayout)view.findViewById(R.id.close_item);
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
        View view = inflater.inflate(R.layout.widget_float_window_right, null);
        rightBackText=(TextView)view.findViewById(R.id.back_item);
        rightBackText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickedListener.onBackItemClick();
            }
        });
        FrameLayout closeItem=(FrameLayout)view.findViewById(R.id.close_item);
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
        return inflater.inflate(R.layout.widget_float_window_logo, null);
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
            logoView.setBackgroundResource(R.drawable.widget_float_button_logo_bg);
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

    public void show(String info) {
        super.show();
        if (leftBackText!=null)
            leftBackText.setText(Html.fromHtml(info));
        if (rightBackText!=null)
            rightBackText.setText(Html.fromHtml(info));
    }
}
