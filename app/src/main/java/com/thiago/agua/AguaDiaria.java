package com.thiago.agua;

import java.util.ArrayList;
import java.util.List;

public class AguaDiaria {
    private float peso;
    private float volumeCopo;
    private List<Copo> copos;

    public AguaDiaria(float peso, float volumeCopo){
        this.peso=peso;
        this.volumeCopo=volumeCopo;
        criaLista();
    }

    private void criaLista(){
        copos = new ArrayList<>();
        float volumePorDia = peso *30;
        int numero_copos = (int) Math.floor(volumePorDia/(volumeCopo));
        float resto = volumePorDia%volumeCopo;
        for(int i = 0; i<numero_copos; i++){
            copos.add(new Copo(volumeCopo));
        }
        copos.add(new Copo(resto));
    }

    public List<Copo> getCopos() {
        return copos;
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
