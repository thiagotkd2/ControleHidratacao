package com.thiago.agua;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.thiago.agua.model.Copo;
import com.thiago.agua.viewmodel.AguaDiariaViewModel;
import com.thiago.agua.viewmodel.CopoViewModel;
import com.thiago.agua.databinding.LayoutCopoBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AguaDiariaAdapter extends RecyclerView.Adapter<AguaDiariaAdapter.CopoViewHolder> {

    private List<CopoViewModel> copos;
    private LifecycleOwner lifecycleOwner;
    private AguaDiariaViewModel aguaViewModel;

    public AguaDiariaAdapter(LifecycleOwner lifecycleOwner, AguaDiariaViewModel vm) {
        this.copos = vm.getCoposViewModel().getValue();
        this.lifecycleOwner = lifecycleOwner;
        aguaViewModel = vm;
    }

    public void atualizarLista(List<CopoViewModel> copos) {
        this.copos = copos;
        Log.i("teste", "Number of items: " + copos.size());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CopoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflador = LayoutInflater.from(parent.getContext());
        LayoutCopoBinding copoItem = DataBindingUtil.inflate(inflador, R.layout.layout_copo, parent, false);
        copoItem.setLifecycleOwner(lifecycleOwner);
        return new CopoViewHolder(copoItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CopoViewHolder holder, int position) {
        holder.bind(aguaViewModel.getCoposViewModel().getValue().get(position));
    }

    @Override
    public int getItemCount() {
        Log.i("teste",String.valueOf(copos.size()));
        return copos.size();
    }

    static class CopoViewHolder extends RecyclerView.ViewHolder {

        private LayoutCopoBinding binding;
        public CopoViewHolder(@NonNull LayoutCopoBinding copoItem) {
            super(copoItem.getRoot());
            this.binding = copoItem;
        }

        public void bind(CopoViewModel vm) {
            binding.setCopo(vm.getCopo());
            binding.setVm(vm);

            binding.executePendingBindings();
        }
    }

}
