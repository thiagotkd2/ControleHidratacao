package com.thiago.agua.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.thiago.agua.R;
import com.thiago.agua.model.Copo;

public class CopoViewModel extends ViewModel {
    private MutableLiveData<Integer> imagemCopo;
    private MutableLiveData<String> volume = new MutableLiveData<>();
    private boolean isBebido;
    private Copo copo;

    public CopoViewModel(){
        this.imagemCopo = new MutableLiveData<>(R.drawable.copo_colorido);
    }

    public Copo getCopo() {
        return copo;
    }

    public void setCopo(Copo copo) {
        this.copo = copo;
        if(!(copo==null)) {
            this.volume = new MutableLiveData<>(String.valueOf(copo.getVolume()));
            this.isBebido = !copo.isCheio();
        }
    }

    public LiveData<Integer> getImagemCopo() {
        return imagemCopo;
    }

    public LiveData<String> getVolume() {
        return volume;
    }

    public boolean isBebido() {
        return isBebido;
    }

    private void beber(){
        copo.beber();
        this.isBebido=!copo.isCheio();
        imagemCopo.setValue(R.drawable.copo_cinza);
    }

    private void desbeber(){
        copo.desbeber();
        this.isBebido=!copo.isCheio();
        imagemCopo.setValue(R.drawable.copo_colorido);
    }

    public void toggleBeber(){
        if (this.isBebido()){
            desbeber();
        }else {
            beber();
        }

    }
}
