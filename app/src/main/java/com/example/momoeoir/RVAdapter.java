package com.example.momoeoir;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ExampleViewHolder>  {
    private ArrayList<CustomCardView> mExampleList;
    private OnItemClickListener mListener;
    Context con;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public RelativeLayout mRelative;
        public ExampleViewHolder(View itemView,final OnItemClickListener listener,Context cu) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
            mTextView3 = itemView.findViewById(R.id.textview2);
            mRelative = itemView.findViewById(R.id.relativecard);
            switch (new SetDecor(cu).getDecor())
            {
                case "pink":
                    mRelative.setBackgroundResource(R.color.pinkcardbg);
                    mTextView1.setTextColor(cu.getResources().getColor(R.color.textpink));
                    mTextView2.setTextColor(cu.getResources().getColor(R.color.textpink));
                    mTextView3.setTextColor(cu.getResources().getColor(R.color.textpink));
                    break;
                case "dark":
                    mRelative.setBackgroundResource(R.color.darkcardbg);
                    mTextView1.setTextColor(cu.getResources().getColor(R.color.white));
                    mTextView2.setTextColor(cu.getResources().getColor(R.color.white));
                    mTextView3.setTextColor(cu.getResources().getColor(R.color.white));
                    break;
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
    public RVAdapter(ArrayList<CustomCardView> exampleList, Context con) {
        this.con = con;
        mExampleList = exampleList;
    }
    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardshizzle, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v,mListener,con);
        return evh;
    }
    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        CustomCardView currentItem = mExampleList.get(position);
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
        holder.mTextView3.setText(currentItem.getText3());
    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
