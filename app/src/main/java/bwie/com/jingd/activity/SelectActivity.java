package bwie.com.jingd.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fynn.fluidlayout.FluidLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import bwie.com.jingd.R;

public class SelectActivity extends AppCompatActivity {
    private String mNAME[] = {
            "手机",
            "电动牙刷",
            "尤妮佳",
            "豆豆鞋",
            "沐浴露",
            "日东红茶",
            "电脑",
            "电动牙刷",
            "雅诗莱黛",
            "豆豆鞋"
    };
    @BindView(R.id.sou_et)
    EditText souEt;
    @BindView(R.id.selfin)
    ImageView selFin;
    @BindView(R.id.sousuo)
    Button sousuo;
    @BindView(R.id.linear)
    LinearLayout linear;
    @BindView(R.id.sousuofaxian)
    TextView sousuofaxian;
    @BindView(R.id.liushi)
    FluidLayout liushi;
    @BindView(R.id.lishi)
    TextView lishi;
    @BindView(R.id.liu)
    FluidLayout liu;
    private List<String> list;
    private String keywords;
    private String keyword;
    private TextView textView;
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        ButterKnife.bind(this);
        initView();
        initData();
        selFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
    private void initView() {

        list = new ArrayList<>();

    }

    private void initData() {
        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s = souEt.getText().toString();

                String liua[] = {s};
                for (int i = 0; i < liua.length; i++) {
                    FluidLayout.LayoutParams params =
                            new FluidLayout.LayoutParams(
                                    ViewGroup.LayoutParams.WRAP_CONTENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.setMargins(12, 12, 12, 12);

                    tv = new TextView(SelectActivity.this);
                    tv.setText(liua[i]);
                    keywords = liua[i].toString();

                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(SelectActivity.this, ListActivity.class);
                            intent.putExtra("keywords", keywords);
                            startActivity(intent);
                        }
                    });
                    tv.setBackgroundResource(R.drawable.text_bg);
                    liu.addView(tv, params);

                    Intent intent = new Intent(SelectActivity.this, ListActivity.class);
                    intent.putExtra("keywords", s);
                    startActivity(intent);
                }
            }
        });


        for (int i = 0; i < mNAME.length; i++) {
            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(12, 12, 12, 12);
            textView = new TextView(this);
            textView.setText(mNAME[i]);

            textView.setBackgroundResource(R.drawable.text_bg);
            textView.setTextColor(Color.BLACK);

            liushi.addView(textView, params);

            keyword = mNAME[i].toString();

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SelectActivity.this, ListActivity.class);
                    intent.putExtra("keywords", keyword);
                    startActivity(intent);
                }
            });
        }
    }
}
