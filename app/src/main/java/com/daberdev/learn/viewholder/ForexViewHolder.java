package com.daberdev.learn.viewholder;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daberdev.learn.R;

public class ForexViewHolder extends RecyclerView.ViewHolder {

    public TextView currency;
    public TextView description;
    public TextView value;
    public ForexViewHolder(@NonNull View itemView) {
        super(itemView);

        this.currency = itemView.findViewById(R.id.currency);
        this.description = itemView.findViewById(R.id.description);
        this.value = itemView.findViewById(R.id.value);
    }
}
