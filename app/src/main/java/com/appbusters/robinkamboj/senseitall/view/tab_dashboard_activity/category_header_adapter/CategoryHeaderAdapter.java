package com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.category_header_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryHeaderAdapter extends RecyclerView.Adapter<CategoryHeaderAdapter.HeaderHolder> {

    private List<String> headersList;
    private Context activityContext;
    private HeaderClickListener headerClickListener;

    public CategoryHeaderAdapter(List<String> headers, Context context, HeaderClickListener headerClickListener) {
        this.headersList = headers;
        this.activityContext = context;
        this.headerClickListener = headerClickListener;
    }

    @NonNull
    @Override
    public HeaderHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
        return new HeaderHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_tab_header, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull final HeaderHolder headerHolder, final int position) {

        final int fixedPosition = position;

        headerHolder.headerTextView.setText(headersList.get(position));

        headerHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerClickListener.handleHeaderClicked(headersList.get(fixedPosition));
            }
        });
    }

    @Override
    public int getItemCount() {
        return headersList == null ? 0 : headersList.size();
    }

    class HeaderHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.header_text_view)
        TextView headerTextView;

        HeaderHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
