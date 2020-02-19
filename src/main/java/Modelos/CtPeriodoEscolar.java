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
public class CtPeriodoEscolar {
    private int idtbperiodo;
    private String nombre;
    private String fechainicio;
    private String fechafin;
    private int status;
    private int tipoescuela;

    public String getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(String fechainicio) {
        this.fechainicio = fechainicio;
    }

    public String getFechafin() {
        return fechafin;
    }

    public void setFechafin(String fechafin) {
        this.fechafin = fechafin;
    }

    public int getIdtbperiodo() {
        return idtbperiodo;
    }

    public void setIdtbperiodo(int idtbperiodo) {
        this.idtbperiodo = idtbperiodo;
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
