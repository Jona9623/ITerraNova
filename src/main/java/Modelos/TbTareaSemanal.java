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
public class TbTareaSemanal {
    private int idtbtarea;
    private int rsemana;
    private int rperiodo;
    private String tarea;
    private int rdia;
    private String dia;
    private int rpersonal;
    private String personal;
    private String apellidop;
    private String apellidom;
    private String fechaentrega;
    private int status;
    private int tipoescuela;

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getApellidom() {
        return apellidom;
    }

    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public int getRperiodo() {
        return rperiodo;
    }

    public void setRperiodo(int rperiodo) {
        this.rperiodo = rperiodo;
    }

    public String getFechaentrega() {
        return fechaentrega;
    }

    public void setFechaentrega(String fechaentrega) {
        this.fechaentrega = fechaentrega;
    }

    public int getIdtbtarea() {
        return idtbtarea;
    }

    public void setIdtbtarea(int idtbtarea) {
        this.idtbtarea = idtbtarea;
    }

    public int getRsemana() {
        return rsemana;
    }

    public void setRsemana(int rsemana) {
        this.rsemana = rsemana;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public int getRdia() {
        return rdia;
    }

    public void setRdia(int rdia) {
        this.rdia = rdia;
    }

    public int getRpersonal() {
        return rpersonal;
    }

    public void setRpersonal(int rpersonal) {
        this.rpersonal = rpersonal;
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
