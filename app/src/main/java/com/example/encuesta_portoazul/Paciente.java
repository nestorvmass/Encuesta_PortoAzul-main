package com.example.encuesta_portoazul;

public class Paciente {

    /*
    * Así mismo el hospital desea conocer la información del paciente quien respondió
    * el cuestionario, por temas de transparencia y para actualizar la información del paciente.
    *  Por lo que los datos que se desean guardan son los
    * siguientes: Nombre, Apellido, Teléfono, Dirección, estado civil,
    * profesión, estrato, Cargo, ultimo nivel de estudio.
    *
    *
    * */

    String id, nombre, apellido, direccion, estado_civil, profesion, estrato, cargo, telefono, nivel_estudio;
    public Paciente(){

    }

    public Paciente(String id, String nombre, String apellido, String direccion,
                    String estado_civil, String profesion, String estrato,
                    String cargo, String telefono, String nivel_estudio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.estado_civil = estado_civil;
        this.profesion = profesion;
        this.estrato = estrato;
        this.cargo = cargo;
        this.telefono = telefono;
        this.nivel_estudio = nivel_estudio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String Profesion) {
        this.profesion = Profesion;
    }

    public String getEstrato() {
        return estrato;
    }

    public void setEstrato(String estrato) {
        this.estrato = estrato;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNivel_estudio() {
        return nivel_estudio;
    }

    public void setNivel_estudio(String nivel_estudio) {
        this.nivel_estudio = nivel_estudio;
    }


}
