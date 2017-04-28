package com.example.anderson.webcoleta.model;

import java.io.Serializable;

/**
 * Created by Anderson on 12/04/2017.
 */

public class GarbagePlaceInfo implements Serializable {

    private String Intervalo;
    private String Setor;
    private String Endereco;
    private String Turno;
    private String RotaSetor;
    private String Frequencia;
    private String Id;


    public String getIntervalo ()
    {
        return this.Intervalo;
    }

    public void setIntervalo (String Intervalo)
    {
        this.Intervalo = Intervalo;
    }

    public String getSetor ()
    {
        return this.Setor;
    }

    public void setSetor (String Setor)
    {
        this.Setor = Setor;
    }

    public String getId ()
    {
        return Id;
    }

    public void setId (String Id)
    {
        this.Id = Id;
    }

    public String getEndereco ()
    {
        return this.Endereco;
    }

    public void setEndereco (String Endereco)
    {
        this.Endereco = Endereco;
    }

    public String getTurno() { return  this.Turno; }

    public void setTurno(String Turno) {this.Turno = Turno; }

    public String getRotaSetor() {return this.RotaSetor; }

    public void setRotaSetor(String RotaSetor) {this.RotaSetor = RotaSetor; }

    public String getFrequencia() {return this.Frequencia; }

    public void setFrequencia(String Frequencia) {this.Frequencia = Frequencia; }

    @Override
    public String toString()
    {
        return "ClassPojo [Intervalo = "+Intervalo+", Setor = "+Setor+", Id = "+Id+", Endereco = "+Endereco+

                ", Turno = "+Turno+", RotaSetor = "+RotaSetor+", Frequencia = "+ Frequencia+"]";
    }


}
