package com.jasonoh.jsonpullparsermoviesex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<Member> members;

    public MoviesAdapter(Context context, ArrayList<Member> members) {
        this.context = context;
        this.members = members;
    }

    public MoviesAdapter() {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(context).inflate(R.layout.recycler_item_json, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH) holder;
        vh.setText();
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    class VH extends RecyclerView.ViewHolder {

        TextView tvRank, tvName, tvOpenData, tvAudiCnt, tvAudiAcc;

        public VH(@NonNull View itemView) {
            super(itemView);

            tvRank = itemView.findViewById(R.id.tv_rank);
            tvName = itemView.findViewById(R.id.tv_name);
            tvOpenData = itemView.findViewById(R.id.tv_openDate);
            tvAudiCnt = itemView.findViewById(R.id.tv_audiCnt);
            tvAudiAcc = itemView.findViewById(R.id.tv_audiAcc);

        }

        public void setText(){
            tvRank.setText( members.get( getLayoutPosition() ).rank );
            tvName.setText( members.get(getLayoutPosition()).movieNm );
            tvOpenData.setText(members.get(getLayoutPosition()).openDt);
            tvAudiCnt.setText(members.get(getLayoutPosition()).audiCnt);
            tvAudiAcc.setText(members.get(getLayoutPosition()).audiAcc);
        }

    }

}
