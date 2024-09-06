package com.thiago.agua;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thiago.agua.databinding.LayoutAguadiariaBinding;
import com.thiago.agua.model.AguaDiaria;
import com.thiago.agua.model.Copo;
import com.thiago.agua.viewmodel.AguaDiariaViewModel;
import com.thiago.agua.viewmodel.CopoViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutAguadiariaBinding LayAguaDiariaBinding = DataBindingUtil.setContentView(this, R.layout.layout_aguadiaria);
        LayAguaDiariaBinding.setLifecycleOwner(this);

        AguaDiariaViewModel vm = new ViewModelProvider(this).get(AguaDiariaViewModel.class);
        LayAguaDiariaBinding.setVm2(vm);

        RecyclerView recyclerView = findViewById(R.id.listaCopo);

        int numberOfColumns = 6;
        GridLayoutManager layoutManager = new GridLayoutManager(this, numberOfColumns);
        recyclerView.setLayoutManager(layoutManager);
        AguaDiariaAdapter adapter = new AguaDiariaAdapter(this, vm);

        LayAguaDiariaBinding.setAdapter(adapter);
        LayAguaDiariaBinding.executePendingBindings();

    }

    @BindingAdapter("atualizarLista")
    public static void atualizarLista(RecyclerView view, List<CopoViewModel> copos) {
        AguaDiariaAdapter adapter = (AguaDiariaAdapter) view.getAdapter();

        if (adapter != null && copos!=null)
            adapter.atualizarLista(copos);


    }

    @BindingAdapter("setAdapter")
    public static void setAdapter(RecyclerView view, @NonNull AguaDiariaAdapter adapter) {
        view.setAdapter(adapter);
    }
}