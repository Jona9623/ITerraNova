/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.File;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Jonat
 */
public class TbReporteDisciplinar {
    private int idtbreporte;
    private int rperiodo;
    private int ralumno;
    private int rpersonal;
    private String hora;
    private String fecha;
    private String fechareporte;
    private int rpersonalsolicita;
    private int rpersonalllena;
    private int rmateria;
    private String lugar;
    private int rtipoincidente;
    private int nivel;
    private String descripcion;
    private String foto;
    private int status;
    private int tipoescuela;

    public int getIdtbreporte() {
        return idtbreporte;
    }

    public void setIdtbreporte(int idtbreporte) {
        this.idtbreporte = idtbreporte;
    }
    
    public int getRperiodo() {
        return rperiodo;
    }

    public void setRperiodo(int rperiodo) {
        this.rperiodo = rperiodo;
    }
    
    public int getRalumno() {
        return ralumno;
    }

    public void setRalumno(int ralumno) {
        this.ralumno = ralumno;
    }

    public int getRpersonal() {
        return rpersonal;
    }

    public void setRpersonal(int rpersonal) {
        this.rpersonal = rpersonal;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechareporte() {
        return fechareporte;
    }

    public void setFechareporte(String fechareporte) {
        this.fechareporte = fechareporte;
    }

    public int getRpersonalsolicita() {
        return rpersonalsolicita;
    }

    public void setRpersonalsolicita(int rpersonalsolicita) {
        this.rpersonalsolicita = rpersonalsolicita;
    }

    public int getRpersonalllena() {
        return rpersonalllena;
    }

    public void setRpersonalllena(int rpersonalllena) {
        this.rpersonalllena = rpersonalllena;
    }

    public int getRmateria() {
        return rmateria;
    }

    public void setRmateria(int rmateria) {
        this.rmateria = rmateria;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getRtipoincidente() {
        return rtipoincidente;
    }

    public void setRtipoincidente(int rtipoincidente) {
        this.rtipoincidente = rtipoincidente;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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
