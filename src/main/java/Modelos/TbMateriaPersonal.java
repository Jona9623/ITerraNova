/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Complx
 */
public class TbMateriaPersonal {
    private int idtbmateriapersonal;
    private int r_personal;
    private String personal;
    private String apellidop;
    private String apeliidom;
    private int r_materia;
    private String materia;
    private int r_periodo;

    public int getR_periodo() {
        return r_periodo;
    }

    public void setR_periodo(int r_periodo) {
        this.r_periodo = r_periodo;
    }

    public int getIdtbmateriapersonal() {
        return idtbmateriapersonal;
    }

    public void setIdtbmateriapersonal(int idtbmateriapersonal) {
        this.idtbmateriapersonal = idtbmateriapersonal;
    }

    public int getR_personal() {
        return r_personal;
    }

    public void setR_personal(int r_personal) {
        this.r_personal = r_personal;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getApeliidom() {
        return apeliidom;
    }

    public void setApeliidom(String apeliidom) {
        this.apeliidom = apeliidom;
    }

    public int getR_materia() {
        return r_materia;
    }

    public void setR_materia(int r_materia) {
        this.r_materia = r_materia;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }
    
}
