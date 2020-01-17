/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Jonat
 */
public class CtSemanaFiscal {
    private int idtbsemana;
    private String nombre;
    private int status;
    private int tipoescuela;

    public int getIdtbsemana() {
        return idtbsemana;
    }

    public void setIdtbsemana(int idtbsemana) {
        this.idtbsemana = idtbsemana;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTipoescuela() {
        return tipoescuela;
    }

    public void setTipoescuela(int tipoescuela) {
        this.tipoescuela = tipoescuela;
    }
    
}
