package com.money.moneyworld.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.money.moneyworld.R;

import java.util.ArrayList;
import java.util.List;

public class Transaaction_history_adapter extends RecyclerView.Adapter<Transaaction_history_adapter.myViewHolder> /*implements Filterable */{
    /*
        ArrayList<TransactionHistoryResponse.Result> transactionHistoryModels;
        Context context;
        Activity activity;
        private HistoryClickLister historyClickLister;
        SharedPreferences sharedPreferences;
        private List<TransactionHistoryResponse.Result> exampleListFull;

        public Transaaction_history_adapter(ArrayList<TransactionHistoryResponse.Result> transactionHistoryModels, Context context, HistoryClickLister historyClickLister) {
            this.transactionHistoryModels = transactionHistoryModels;
            this.context = context;
            this.historyClickLister = historyClickLister;
            exampleListFull = new ArrayList<>(transactionHistoryModels);
        }
    */
    Context context;
    String[] amount;

    public Transaaction_history_adapter(Context context, String[] amount) {
        this.context = context;
        this.amount = amount;
    }

    public Transaaction_history_adapter() {

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_transactio_history, parent, false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.transaction_amount.setText(amount[position]);

        setAnimation(holder.itemView);

    }

    @Override
    public int getItemCount() {
        return amount.length;
    }

    private void setAnimation(View view) {

        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        view.setAnimation(animation);
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView transaction_status, transaction_amount, transaction_username, transaction_time, transaction_date;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            transaction_status = itemView.findViewById(R.id.transaction_status);
            transaction_amount = itemView.findViewById(R.id.transaction_amount);
            transaction_username = itemView.findViewById(R.id.transaction_username);
            transaction_date = itemView.findViewById(R.id.transaction_date);
            transaction_time = itemView.findViewById(R.id.transaction_Time);


          /*  itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    historyClickLister.onHistoryItemClickListener(getAdapterPosition());
                }
            });
*/
        }
    }

   /* @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<TransactionHistoryResponse.Result> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (TransactionHistoryResponse.Result item : exampleListFull) {
                    if (item.getReceiverName().toLowerCase().contains(filterPattern) || item.getId().toLowerCase().contains(filterPattern) || item.getSenderName().toLowerCase().contains(filterPattern) || item.getCreatedAt().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            transactionHistoryModels.clear();
            transactionHistoryModels.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };*/
}
