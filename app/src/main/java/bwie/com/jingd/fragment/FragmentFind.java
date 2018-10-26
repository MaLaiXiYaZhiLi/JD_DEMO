package bwie.com.jingd.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import bwie.com.jingd.R;

public class FragmentFind extends Fragment {
    @BindView(R.id.webview)
    WebView webview;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);

        unbinder = ButterKnife.bind(this, view);
//        WebSettings settings = webview.getSettings();
//        settings.setJavaScriptCanOpenWindowsAutomatically(true);//允许js弹出窗AAA
//        settings.setAllowFileAccess(true);// 设置允许访问文件数据
//        settings.setUseWideViewPort(true); //将图片调整到适合webview的大小
//        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//
//        webview.setWebChromeClient(new WebChromeClient());

        init();
        return view;
    }
    private void init() {


        webview.loadUrl("http://www.baidu.com/");

        //覆盖系统浏览器打开，使目标在webview中打开
        webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;//ture为在webview中打开
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
