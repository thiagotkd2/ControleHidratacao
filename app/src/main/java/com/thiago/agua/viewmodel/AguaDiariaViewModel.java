package com.thiago.agua.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.thiago.agua.AguaDiariaAdapter;
import com.thiago.agua.model.AguaDiaria;
import com.thiago.agua.model.Copo;

import java.util.ArrayList;
import java.util.List;

public class AguaDiariaViewModel extends ViewModel {
    MutableLiveData<String> peso;
    MutableLiveData<String> bebeuAteAgora;
    MutableLiveData<String> faltando;
    MutableLiveData<String> volume;
    MutableLiveData<List<CopoViewModel>> coposViewModel = new MutableLiveData<>();
    AguaDiaria aguaDiaria;

    private final float volumeCopo = 150;

    public float getVolumeCopo() {
        return volumeCopo;
    }

    public AguaDiaria getAguaDiaria() {
        return aguaDiaria;
    }

    public MutableLiveData<String> getVolume() {
        if(volume!=null)
            return volume;
        volume.setValue("");
        return volume;
    }


    public AguaDiariaViewModel(){
        this.peso=new MutableLiveData<>();
        bebeuAteAgora = new MutableLiveData<>();
        faltando = new MutableLiveData<>();
        volume = new MutableLiveData<>();
        coposViewModel.setValue(new ArrayList<>());
    }

    public void setCoposViewModel(MutableLiveData<List<CopoViewModel>> coposViewModel) {
        this.coposViewModel = coposViewModel;
    }

    public LiveData<List<CopoViewModel>> getCoposViewModel() {
        return coposViewModel;
    }

    public MutableLiveData<String> getFaltando() {
        if (aguaDiaria!=null) {
            faltando.setValue(String.valueOf(aguaDiaria.mililitrosBebidosAteAgora()));
            return faltando;
        }
        faltando.setValue("");
        return faltando;
    }

    public MutableLiveData<String> getBebeuAteAgora() {
        return bebeuAteAgora;
    }

    public MutableLiveData<String> getPeso() {
        return peso;
    }

    public void calcular(){
        Log.i("teste",this.peso.getValue());
        faltando.setValue(String.valueOf(Float.parseFloat(peso.getValue())*35));
        volume.setValue(faltando.getValue());
        Log.i("teste", faltando.getValue());
        aguaDiaria = new AguaDiaria(Float.parseFloat(peso.getValue()));
        int numero_copos = (int)
                Math.floor(Float.parseFloat(volume.getValue())/
                        volumeCopo); // 1 copo
        float resto = Float.parseFloat(volume.getValue())%volumeCopo;

        for(int i = 0; i<numero_copos; i++){
            Log.i("teste", "volume.getValue()");
            CopoViewModel cvm = new CopoViewModel();
            cvm.setCopo(new Copo(volumeCopo));
            cvm.setVolume(new MutableLiveData<String>(String.valueOf(volumeCopo)));
            coposViewModel.getValue().add(cvm);
            aguaDiaria.addCopos(volumeCopo);
        }
        Log.i("teste", String.valueOf(resto));
        if(resto>0) {
            CopoViewModel cvm = new CopoViewModel();
            cvm.setCopo(new Copo(resto));
            cvm.setVolume(new MutableLiveData<String>(String.valueOf(resto)));
            coposViewModel.getValue().add(cvm);
            aguaDiaria.addCopos(resto);
        }

        if (this.coposViewModel.getValue().get(0).getCopo()!=null)
            Log.i("teste",String.valueOf(this.coposViewModel.getValue().get(0).getCopo().getVolume()));
    }

    public void zerar(){
        peso.setValue("");
        bebeuAteAgora.setValue("0");
        faltando.setValue("0");
        coposViewModel.getValue().clear();
        aguaDiaria=null;
    }
}
