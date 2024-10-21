package com.murek.relacionviwemodellivelistausuarios;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends ViewModel {

    // MutableLiveData que almacena y observa una lista de usuarios (List<Usuario>)
    private MutableLiveData<List<Usuario>> listaUsuariosLiveData; //almacenar y observar una lista de usuarios (List<Usuario>)
    // Lista de usuarios que almacenará los datos
    private List<Usuario> listaUsuarios;


    // Método que devuelve la lista observable de usuarios
    public LiveData<List<Usuario>> getUserList(){ //devuelve la instancia de LiveData<List<Usuario>>
        /*Como se devuelve LiveData y no MutableLiveData, cualquier componente externo que observe esta
        lista puede leerla, pero no modificarla directamente*/
        if (listaUsuariosLiveData==null){
            listaUsuariosLiveData=new MutableLiveData<>();
            listaUsuarios=new ArrayList<>();
        }
        // Retornamos la lista de usuarios como LiveData (no se puede modificar directamente desde fuera)
        return listaUsuariosLiveData;
    }

    // Método para agregar un nuevo usuario a la lista
    public void addUser(Usuario usuario){
        listaUsuarios.add(usuario);
        // Actualizamos el valor de MutableLiveData, notificando a los observadores
        listaUsuariosLiveData.setValue(listaUsuarios);
    }

}
