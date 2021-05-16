package com.example.encuesta_portoazul;

public class Cuestionario {

    String id_paciente,pregunta4;
    int pregunta1, pregunta2, pregunta3, pregunta5;
    double promedio;

    public Cuestionario() {

    }


    public String getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(String id_paciente) {
        this.id_paciente = id_paciente;
    }

    public int getPregunta1() {
        return pregunta1;
    }

    public void setPregunta1(int pregunta1) {
        this.pregunta1 = pregunta1;
    }

    public int getPregunta2() {
        return pregunta2;
    }

    public void setPregunta2(int pregunta2) {
        this.pregunta2 = pregunta2;
    }

    public int getPregunta3() {
        return pregunta3;
    }

    public void setPregunta3(int pregunta3) {
        this.pregunta3 = pregunta3;
    }

    public String getPregunta4() {
        return pregunta4;
    }

    public void setPregunta4(String pregunta4) {
        this.pregunta4 = pregunta4;
    }

    public int getPregunta5() {
        return pregunta5;
    }

    public void setPregunta5(int pregunta5) {
        this.pregunta5 = pregunta5;
    }

    public double getPromedio() {
        promedio = (pregunta1+pregunta2+pregunta3+pregunta5)/4;
        return promedio;
    }

}
