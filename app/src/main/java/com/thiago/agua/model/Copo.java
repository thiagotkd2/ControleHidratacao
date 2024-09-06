package com.thiago.agua.model;

public class Copo {

    private boolean cheio;
    private float volume;

    public Copo(float volume){
        this.volume = volume;
        this.cheio = true;
    }

    public void beber(){
        cheio = false;
    }
    public boolean isCheio(){
        return cheio;
    }
    public float getVolume(){
        return volume;
    }
    public void desbeber(){
        cheio = true;
    }
}
