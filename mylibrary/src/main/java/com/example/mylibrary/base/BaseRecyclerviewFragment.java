package com.example.mylibrary.base;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.mylibrary.R;
import com.example.mylibrary.util.Util_collection;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;


/**
 * Created by young
 */

public abstract class BaseRecyclerviewFragment<T> extends BaseFragment implements OnItemClickListener {
    public int page;
    protected RecyclerView mRecyclerView;
    protected RefreshLayout mRefreshLayout;
    protected BaseQuickAdapter<T, BaseViewHolder> mAdapter;

    protected abstract BaseQuickAdapter<T, BaseViewHolder> initAdapter();

    public abstract void refresh();

    protected List parseListResponse(JSONObject json) {
        return null;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    protected int getDefautPage() {
        return 1;
    }

    @Override
    public void initViews(View parentView) {
        mRecyclerView = parentView.findViewById(R.id.recyclerView);
        mRefreshLayout = parentView.findViewById(R.id.refreshLayout);
    }

    @Override
    public int getLayoutId() {
        return R.layout.recyclerview_refresh_title;
    }

    @Override
    public void setViews(View parentView) {
        page = getDefautPage();
        mRecyclerView.setAdapter(mAdapter = initAdapter());
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setLayoutManager(setLayoutManager());
        mRecyclerView.setHasFixedSize(true);
        mRefreshLayout.autoRefresh();
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = getDefautPage();
                refresh();
            }
        });
//        mRefreshLayout.autoLoadMore();
        //监听加载，而不是监听 刷新
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout mRefreshLayout) {
                page++;
                refresh();
            }
        });
    }

    /**
     * 第一次加载数据。
     */
    public void loadData() {

    }

    @NonNull
    protected RecyclerView.LayoutManager setLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    public class ListCallback extends BaseStringCallback {

        @Override
        public void onSuccess(JSONObject jsonObject) {
            super.onSuccess(jsonObject);
            try {
                List<T> datas = parseListResponse(jsonObject);
                boolean isRefresh = page == getDefautPage();
                if (isRefresh) {
                    mAdapter.setNewData(datas);
                } else {
                    if (Util_collection.isEmpty(datas)) {
                        mRefreshLayout.finishLoadMoreWithNoMoreData();
                        page--;
                    } else {
                        mAdapter.addData(datas);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFinish() {
            super.onFinish();
            mRefreshLayout.finishRefresh();
            mRefreshLayout.finishLoadMore();
        }

        @Override
        public void onError(Response<String> response) {
            super.onError(response);
        }
    }
}
