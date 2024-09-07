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
    private MutableLiveData<String> peso;
    private MutableLiveData<String> bebeuAteAgora;
    private MutableLiveData<String> faltando;
    private MutableLiveData<String> volume;
    private MutableLiveData<List<CopoViewModel>> coposViewModel;
    private AguaDiaria aguaDiaria;

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
        coposViewModel=new MutableLiveData<>(new ArrayList<>());
    }

    public void setCoposViewModel(List<CopoViewModel> coposViewModel) {
        this.coposViewModel.setValue(coposViewModel);
    }

    public LiveData<List<CopoViewModel>> getCoposViewModel() {
        return coposViewModel;
    }

    public MutableLiveData<String> getFaltando() {
        if (aguaDiaria!=null) {
            faltando.setValue(String.valueOf((Float.parseFloat(peso.getValue())*35)-aguaDiaria.mililitrosBebidosAteAgora()));
            return faltando;
        }
        faltando.setValue("0");
        return faltando;
    }

    public MutableLiveData<String> getBebeuAteAgora() {
        if (aguaDiaria!=null) {
            bebeuAteAgora.setValue(String.valueOf(aguaDiaria.mililitrosBebidosAteAgora()));
            return bebeuAteAgora;
        }
        bebeuAteAgora.setValue("0");
        return bebeuAteAgora;
    }

    public MutableLiveData<String> getPeso() {
        return peso;
    }

    public void calcular(){
        Log.i("Teste", peso.getValue() + "");
        if(peso.getValue()!=null){
            faltando.setValue(String.valueOf(Float.parseFloat(peso.getValue())*35));
            volume.setValue(faltando.getValue());
            Log.i("teste", faltando.getValue());
            aguaDiaria = new AguaDiaria(Float.parseFloat(peso.getValue()));
            coposViewModel.getValue().clear();


            List<CopoViewModel> listCvm = new ArrayList<>();


            int numero_copos = (int)
                    Math.floor(Float.parseFloat(volume.getValue())/
                            volumeCopo); // 1 copo
            float resto = Float.parseFloat(volume.getValue())%volumeCopo;

            for(int i = 0; i<numero_copos; i++){
                Log.i("teste", "volume.getValue()");

                Copo c = new Copo(volumeCopo);
                CopoViewModel cvm = new CopoViewModel();
                aguaDiaria.getCopos().add(c);
                cvm.setCopo(c);
                listCvm.add(cvm);
            }

            Log.i("teste", String.valueOf(resto));
            if(resto>0) {
                Copo c = new Copo(resto);
                CopoViewModel cvm = new CopoViewModel();
                aguaDiaria.getCopos().add(c);
                cvm.setCopo(c);
                listCvm.add(cvm);
            }
            coposViewModel.postValue(listCvm);

        }
        Log.i("test", String.valueOf(coposViewModel.hasActiveObservers()));
    }

    public void zerar(){
        peso.setValue(null);
        bebeuAteAgora.setValue("0");
        faltando.setValue("0");
        coposViewModel.getValue().clear();
        aguaDiaria=null;
    }
}
