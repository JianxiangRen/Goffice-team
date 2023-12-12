package com.example.officebooking.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.officebooking.R;
import com.example.officebooking.bean.BookBean;

public class ClassAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ClassAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_1,item)
                .addOnClickListener(R.id.iv_1);
    }
}
