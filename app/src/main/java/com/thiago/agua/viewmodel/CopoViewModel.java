package com.thiago.agua.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.thiago.agua.R;
import com.thiago.agua.model.Copo;

public class CopoViewModel extends ViewModel {
    private MutableLiveData<Integer> imagemCopo = new MutableLiveData<>();
    private MutableLiveData<String> volume = new MutableLiveData<>();
    private boolean isBebido;
    private Copo copo;

    public CopoViewModel(){
        this.imagemCopo = new MutableLiveData<>(R.drawable.copo_colorido);
        if(!(copo==null)) {
            this.volume = new MutableLiveData<>(String.valueOf(copo.getVolume()));
            this.isBebido = !copo.isCheio();
        }
    }

    public Copo getCopo() {
        return copo;
    }

    public void setImagemCopo(MutableLiveData<Integer> imagemCopo) {
        this.imagemCopo = imagemCopo;
    }

    public void setVolume(MutableLiveData<String> volume) {
        this.volume = volume;
        copo = new Copo(Float.parseFloat(volume.getValue()));
    }

    public void setBebido(boolean bebido) {
        isBebido = bebido;
    }

    public void setCopo(Copo copo) {
        this.copo = copo;
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

    public void beber(){
        this.isBebido=false;
        imagemCopo.setValue(R.drawable.copo_cinza);
    }

    public void desbeber(){
        this.isBebido=true;
        imagemCopo.setValue(R.drawable.copo_colorido);
    }
}
