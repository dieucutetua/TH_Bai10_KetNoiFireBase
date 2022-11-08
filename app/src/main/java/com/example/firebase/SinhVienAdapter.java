package com.example.firebase;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SinhVienAdapter extends RecyclerView.Adapter<SinhVienAdapter.SinhVienViewHolder> {
    private List<SinhVien> sinhVienList;

    public SinhVienAdapter(List<SinhVien> sinhVienList) {
        this.sinhVienList = sinhVienList;
    }

    @NonNull
    @Override
    public SinhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new SinhVienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SinhVienViewHolder holder, int position) {
        SinhVien sinhVien = sinhVienList.get(position);
        if(sinhVien == null){
            return;
        }
        holder.tvTen.setText("hoTen: " + sinhVien.getHoTen());
        holder.tvMasv.setText("maSV:" + sinhVien.getMaSV());
        holder.tvLop.setText("lop: " + sinhVien.getLop());

    }

    @Override
    public int getItemCount() {
        if(sinhVienList!=null){
            return sinhVienList.size();
        }
        return 0;
    }

    public class SinhVienViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTen;
        private TextView tvMasv;
        private TextView tvLop;

        public SinhVienViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTen = itemView.findViewById(R.id.tvTen);
            tvMasv = itemView.findViewById(R.id.tvMasv);
            tvLop = itemView.findViewById(R.id.tvLop);
        }
    }
}
