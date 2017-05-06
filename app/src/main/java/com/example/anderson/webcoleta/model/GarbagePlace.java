package com.example.anderson.webcoleta.model;

import java.io.Serializable;

/**
 * Created by Anderson on 12/04/2017.
 */

public class GarbagePlace implements Serializable {

    private String mInterval;
    private String mSector;
    private String mStreet;
    private String mShift;
    private String mRouteSector;
    private String mFrequency;
    private String mId;
    private boolean mAutoComp;

    public String getInterval()
    {
        return this.mInterval;
    }

    public void setInterval(String Intervalo)
    {
        this.mInterval = Intervalo;
    }

    public String getSector ()
    {
        return this.mSector;
    }

    public void setSector (String setor)
    {
        this.mSector = setor;
    }

    public String getId ()
    {
        return mId;
    }

    public void setId (String id)
    {
        this.mId = id;
    }

    public String getStreet ()
    {
        return this.mStreet;
    }

    public void setStreet(String endereco)
    {
        this.mStreet = endereco;
    }

    public String getShift() { return  this.mShift; }

    public void setShift(String turno) {this.mShift = turno; }

    public String getRouteSector() {return this.mRouteSector; }

    public void setRouteSector(String rotaSetor) {this.mRouteSector = rotaSetor; }

    public String getFrequency()  {return this.mFrequency; }

    public void setFrequency(String Frequencia) {this.mFrequency = Frequencia; }

    // Atribuindo True ou False se está atribuind o autoComp ou não.
    public void setAutoComp(boolean flag) {
        this.mAutoComp = flag;
    }

    public boolean isAutoComp() {
        return mAutoComp;
    }

    @Override
    public String toString()
    {
        // se a chamada está vindo do autoCompView
        if(this.isAutoComp()) {
            return this.getStreet();
        } else {
            return "ClassPojo [Intervalo = "+mInterval+", Setor = "+ mSector +", Id = "+mId+", mStreet = "+ mStreet +

                    ", Turno = "+mShift+", RotaSetor = "+mRouteSector+", Frequencia = "+ mFrequency+"]";
        }

    }

}
