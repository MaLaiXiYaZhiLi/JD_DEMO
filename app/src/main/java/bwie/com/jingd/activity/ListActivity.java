package bwie.com.jingd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import bwie.com.jingd.R;
import bwie.com.jingd.adapter.MyListAdapter;
import bwie.com.jingd.bean.ListBean;
import bwie.com.jingd.presenter.ListPresenter;
import bwie.com.jingd.view.IListView;

public class ListActivity extends AppCompatActivity implements IListView {

    @BindView(R.id.fanhui)
    ImageView fanhui;
    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.search_text)
    TextView searchText;
    @BindView(R.id.sousuo_recyview)
    RecyclerView sousuoRecyview;
    private ListPresenter listPresenter;
    private String keywords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        listPresenter = new ListPresenter(this);
        Intent intent = getIntent();
        keywords = intent.getStringExtra("keywords");
        if(keywords !=null){
            listPresenter.getList(keywords);
        }
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

    }


    @Override
    public void Success(ListBean listBean) {

        if(listBean!=null&&listBean.getData()!=null){
            final List<ListBean.DataBean> data = listBean.getData();

            sousuoRecyview.setLayoutManager(new LinearLayoutManager(this));
            MyListAdapter adapter = new MyListAdapter(this,data);
            sousuoRecyview.setAdapter(adapter);

            adapter.setOnItemClickListener(new MyListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                    intent.putExtra("pid",data.get(position).getPid());
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void Failure(String msg) {

    }


}
