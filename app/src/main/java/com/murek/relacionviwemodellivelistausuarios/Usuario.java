package com.murek.relacionviwemodellivelistausuarios;

public class Usuario {

    //atributos
    private String nombre;
    private int edad;

    //constructor
    public Usuario(String nombre, int edad){
        this.nombre=nombre;
        this.edad=edad;
    }

    //getters
    public String getNombre(){
        return nombre;
    }

    public int getEdad(){
        return edad;
    }
}
