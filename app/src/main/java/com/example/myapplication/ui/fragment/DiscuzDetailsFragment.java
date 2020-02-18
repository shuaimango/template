package com.example.myapplication.ui.fragment;

import android.os.Bundle;
import androidx.collection.ArrayMap;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.constant.CommonKey;
import com.example.myapplication.entity.Discuz;
import com.example.myapplication.entity.DiscuzComment;
import com.example.myapplication.ui.adapter.DiscuzCommentAdapter;
import com.example.mylibrary.base.BaseRecyclerviewFragment;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class DiscuzDetailsFragment extends BaseRecyclerviewFragment {
    private static ArrayMap<String, List<DiscuzComment>> map;

    private View headView;
    EditText et_inputComment;
    Discuz item ;
    public static DiscuzDetailsFragment newInstance(Bundle args) {
        DiscuzDetailsFragment fragment = new DiscuzDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static ArrayMap<String, List<DiscuzComment>> getMap() {
        if (map == null || map.size() == 0) {
            map = new ArrayMap<>();
            List<DiscuzComment> commentList1=new ArrayList<>();
            commentList1.add( new DiscuzComment("1","1", "猫女王", "海贼迷集合啦，留下你最喜欢的海贼角色",  "2019-11-05"));
            commentList1.add( new DiscuzComment("2","1", "美国不过如此", "娜美",  "2019-11-05"));
            commentList1.add( new DiscuzComment("3","1",  "阳光小鱼", "索隆", "2019-11-05"));
            commentList1.add( new DiscuzComment("4","1",  "上下求索", "罗宾", "2019-11-06"));
            commentList1.add( new DiscuzComment("5","1",  "仅仅如此",  "乔巴","2019-11-06"));
            commentList1.add( new DiscuzComment("6","1",  "呢子裙",  "火拳艾斯","2019-11-07"));
            commentList1.add( new DiscuzComment("7","1", "Marnus", "香吉士",  "2019-11-08"));
            commentList1.add( new DiscuzComment("8","1",  "UU168", "乔巴", "2019-11-08"));
            map.put("1", commentList1);
            List<DiscuzComment> commentList2=new ArrayList<>();
            commentList2.add( new DiscuzComment("1","2", "只为明天的梦", "楼主性格内向不敢跟人说话，这是不是影响了我的人生？",  "2019-11-03"));
            commentList2.add( new DiscuzComment("2","2", "购物的魔女", "会影响命运吧，楼主要加油啊",  "2019-11-03"));
            commentList2.add( new DiscuzComment("3","2", "泥土路上", "会的",  "2019-11-03"));
            commentList2.add( new DiscuzComment("4","2", "萱萱", "内向也没什么不好的啊，很多人喜欢安静的人",  "2019-11-03"));
            commentList2.add( new DiscuzComment("5","2", "猫女王", "不会影响的",  "2019-11-04"));
            map.put("2", commentList2);
            List<DiscuzComment> commentList3=new ArrayList<>();
            commentList3.add( new DiscuzComment("1","3", "棉绒", "来吧，留下你最喜欢的火影角色！我先来，日向宁次！",  "2019-11-02"));
            commentList3.add( new DiscuzComment("2","3", "猫女王", "鸣人",  "2019-11-02"));
            commentList3.add( new DiscuzComment("3","3", "妞一枚", "纲手",  "2019-11-02"));
            commentList3.add( new DiscuzComment("4","3", "C罗", "卡卡西老师",  "2019-11-02"));
            commentList3.add( new DiscuzComment("5","3", "做好是不留名的汉子", "再不斩，令人心疼",  "2019-11-02"));
            commentList3.add( new DiscuzComment("6","3", "Candy糖果", "鹿丸",  "2019-11-02"));
            commentList3.add( new DiscuzComment("7","3", "来自南韩的张先生", "鸣人",  "2019-11-02"));
            map.put("3", commentList3);
            List<DiscuzComment> commentList4=new ArrayList<>();
            commentList4.add( new DiscuzComment("1","4", "小小5子", "感觉路飞每天就是嘻嘻哈哈的，他真的没烦恼么？",  "2019-11-02"));
            commentList4.add( new DiscuzComment("2","4", "春风十里", "烦恼什么？烦恼为什么没有肉吃？",  "2019-11-02"));
            commentList4.add( new DiscuzComment("3","4", "Ken917", "没烦恼吧",  "2019-11-02"));
            commentList4.add( new DiscuzComment("4","4", "逍遥游子", "楼主这么问明显不了解路飞的人设，这个角色就是打造一种没烦恼的感觉啊",  "2019-11-02"));
            commentList4.add( new DiscuzComment("5","4", "奋斗人生", "烦恼什么时候可以当上海贼王",  "2019-11-02"));
            commentList4.add( new DiscuzComment("6","4", "幸福", "艾斯算是他心里的坎吧",  "2019-11-02"));
            map.put("4", commentList4);
            List<DiscuzComment> commentList5=new ArrayList<>();
            commentList5.add( new DiscuzComment("1","5", "飞舞的小辣椒", "是什么原因让大家喜欢上漫画的呢",  "2019-11-01"));
            commentList5.add( new DiscuzComment("2","5", "田鼠2019", "漫画的热血吧",  "2019-11-01"));
            commentList5.add( new DiscuzComment("3","5", "笑骂由人", "漫画的治愈吧",  "2019-11-01"));
            commentList5.add( new DiscuzComment("4","5", "安心简单", "漫画里的世界是另一个世界",  "2019-11-01"));
            commentList5.add( new DiscuzComment("5","5", "香菇油", "感觉漫画里的认识真实存在的",  "2019-11-01"));
            map.put("5", commentList5);
            List<DiscuzComment> commentList6=new ArrayList<>();
            commentList6.add( new DiscuzComment("1","6", "开心就好2019", "宝宝经常睡觉，有时不听话，但还是很开心的鸭~",  "2019-10-31"));
            commentList6.add( new DiscuzComment("2","6", "风的方向", " 哇 恭喜恭喜~",  "2019-10-31"));
            commentList6.add( new DiscuzComment("3","6", "又见你了", "恭喜，做妈妈不简单的呢",  "2019-10-31"));
            commentList6.add( new DiscuzComment("4","6", "又见你了", "恭喜，我也是第一次做妈妈~",  "2019-10-31"));
            commentList6.add( new DiscuzComment("5","6", "爱吃菠菜", "宝宝开心长大就好",  "2019-10-31"));
            map.put("6", commentList6);
            List<DiscuzComment> commentList7=new ArrayList<>();
            commentList7.add( new DiscuzComment("1","7", "夏奇拉", "柯南今年到底多大啦？",  "2019-10-30"));
            commentList7.add( new DiscuzComment("2","7", "圆圆的肚子", "小二吧",  "2019-10-30"));
            commentList7.add( new DiscuzComment("3","7", "万年单身狗", "初二吧",  "2019-10-30"));
            commentList7.add( new DiscuzComment("4","7", "碧海蓝天", "好不容易小一升小二吧",  "2019-10-30"));
            map.put("7", commentList7);
            List<DiscuzComment> commentList8=new ArrayList<>();
            commentList8.add( new DiscuzComment("1","8", "春暖花开", "去过1次日本，挺喜欢日本这个国家的",  "2019-10-29"));
            commentList8.add( new DiscuzComment("1","8", "秋风中的女子", "有礼貌的国家",  "2019-10-29"));
            commentList8.add( new DiscuzComment("1","8", "加油的小摩托", "压力特别大",  "2019-10-29"));
            commentList8.add( new DiscuzComment("1","8", "猫mi", "生活在那里的人挺极端的",  "2019-10-29"));
            commentList8.add( new DiscuzComment("1","8", "所幸没来", "旅游还行啦，生活不是很好，那里上班压力挺大的",  "2019-10-30"));
            commentList8.add( new DiscuzComment("1","8", "红色", "是个很多漫画的国家",  "2019-10-30"));
            map.put("8", commentList8);
        }
        return map;
    }


    @Override
    protected BaseQuickAdapter initAdapter() {
        return new DiscuzCommentAdapter();
    }

    @Override
    protected List parseListResponse(JSONObject jsonObject) {
        List<DiscuzComment> commentList = getMap().get(item.id);
        return commentList;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Object item = adapter.getItem(position);
        Bundle bundle = new Bundle();
//        bundle.putString(CommonKey.ID,item.id);
//        Util_skipPage.startMyFragmentActivity(AULiveApplication.mContext, bundle,StarDetailFragment.class.getName());
    }

    @Override
    public void initViews(View parentView) {
        super.initViews(parentView);
        et_inputComment = parentView.findViewById(R.id.et_inputComment);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_discuz_details;
    }

    @Override
    public void setViews(View parentView) {
        super.setViews(parentView);
        headView = LayoutInflater.from(getActivity()).inflate(R.layout.headview_discuz, null);
        TextView tv_content = headView.findViewById(R.id.tv_content);
        TextView tv_viewNum = headView.findViewById(R.id.tv_viewNum);
        TextView tv_replyNum = headView.findViewById(R.id.tv_replyNum);
        tv_content.setText(item.title);
        tv_viewNum.setText(item.view_num+"");
        tv_replyNum.setText(item.msg_num+"");
        mAdapter.addHeaderView(headView);
        titlebar.setVisibility(View.VISIBLE);
        titlebar.setTitle("帖子详情");
        et_inputComment.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    sendComment();
                }
                return false;
            }
        });
    }

    protected void sendComment() {
        String content = et_inputComment.getText().toString().trim();
        if (android.text.TextUtils.isEmpty(content)) {
            ToastUtils.showShort("你还没有输入呢");
            return;
        }
        String string = SPStaticUtils.getString(MyReplyDiscuzFragment.MyReplyDiscuz);
        List<DiscuzComment> list2 = null;
        if (TextUtils.isEmpty(string)) {
            list2 = new ArrayList<>();
        } else {
            try {
                list2 = JSON.parseArray(string, DiscuzComment.class);
                if (CollectionUtils.isEmpty(list2)) {
                    list2 = new ArrayList<>();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        DiscuzComment data = new DiscuzComment();
        List datas = mAdapter.getData();
        data.id = CollectionUtils.isEmpty(datas)?"1":String.valueOf(datas.size()+1);
        data.discuzId = item.id;
        data.user_name = MyApplication.getUserInfo().nickname;
        data.content = content;
        data.date = TimeUtils.millis2String(System.currentTimeMillis(),"yyyy-MM-dd");
        if( CollectionUtils.isEmpty(datas)){
            mAdapter.addData(data);
        }else {
            mAdapter.addData(1, data);
        }
        mAdapter.notifyDataSetChanged();
        list2.add(0,data);
        SPStaticUtils.put(MyReplyDiscuzFragment.MyReplyDiscuz, JSON.toJSONString(list2));
        ToastUtils.showShort("回帖成功");
        et_inputComment.setText("");
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        item=extras.getParcelable(CommonKey.DATA);
    }

    @Override
    public void refresh() {
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.setNewData(parseListResponse(null));
                mRefreshLayout.finishRefresh();
            }
        },2000);
    }

}
