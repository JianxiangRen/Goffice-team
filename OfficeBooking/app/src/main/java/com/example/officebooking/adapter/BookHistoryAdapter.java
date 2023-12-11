package com.example.officebooking.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.officebooking.R;
import com.example.officebooking.bean.BookBean;

public class BookHistoryAdapter extends BaseQuickAdapter<BookBean, BaseViewHolder> {
    public BookHistoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, BookBean item) {
        helper.setText(R.id.tv_1,item.getStr1())
                .setText(R.id.tv_2,item.getStr3())
                .setText(R.id.tv_3,item.getStr2())
                .setText(R.id.tv_4,item.getStr4())
                .setText(R.id.tv_5,item.getStr5())
                .setText(R.id.tv_6,item.getStr6())
                .addOnClickListener(R.id.iv_play);
    }
}
