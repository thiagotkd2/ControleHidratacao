package com.thiago.agua.model;

import java.util.ArrayList;
import java.util.List;

public class AguaDiaria {
    private float peso;
    private List<Copo> copos;

    public AguaDiaria(float peso){
        this.peso=peso;
        this.copos = new ArrayList<>();
    }

    public List<Copo> getCopos() {
        return copos;
    }

    public float getPeso() {
        return peso;
    }


    public void setPeso(float peso) {
        this.peso = peso;
    }


    public void addCopos(float volume){
        copos.add(new Copo(volume));
    }

    public float mililitrosBebidosAteAgora(){
        // realizar somatorio do volume de todos os copos vazios(cheio=False)
        float somatorio=0;
        for(Copo copo :copos){
            if(!copo.isCheio()){
                somatorio+=copo.getVolume();
            }
        }
        return somatorio;

    }

    public List<Copo> getCoposFaltando(){
        List<Copo> coposFaltando = new ArrayList<>();
        for(Copo copo: copos){
            if(copo.isCheio()){
                coposFaltando.add(copo);
            }
        }
        return coposFaltando;

    }
}
