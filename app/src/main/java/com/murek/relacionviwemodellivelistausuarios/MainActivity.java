package com.murek.relacionviwemodellivelistausuarios;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.murek.relacionviwemodellivelistausuarios.databinding.ActivityMainBinding;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding; //para acceder a la ui
    private UserViewModel userVM; //almacena y gestiona datos de usuario

    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //binding para acceder a los componentes
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //userVM para que se mantengan los datos durante la vida útil de la actividad
        userVM=new ViewModelProvider(this).get(UserViewModel.class);
        tarea();
    }

    //metodo para los botones y el Observer
    public void tarea(){

        //boton guardar
        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obtengo valores declarados por el usuario
                String nombre = binding.etNombre.getText().toString();
                int edad;

                //valido valores
                if (!nombre.isEmpty() && !binding.etEdad.getText().toString().isEmpty()) {
                    try {
                        edad = Integer.parseInt(binding.etEdad.getText().toString());
                        Usuario usuario = new Usuario(nombre, edad);
                        userVM.addUser(usuario);
                        // Limpiamos los campos de texto una vez guardado
                        binding.etNombre.setText("");
                        binding.etEdad.setText("");
                    } catch (NumberFormatException e) {
                        binding.tvLista.setText("Error de formato en la edad");
                    }
                } else {
                    binding.tvLista.setText("ERROR :(");
                }
                // Limpiamos los campos de entrada de texto
                binding.etNombre.setText("");
                binding.etEdad.setText("");
            }
        });

        // Definimos un Observer para observar los cambios en la lista de usuarios
        final Observer<List<Usuario>> listObserver=new Observer<List<Usuario>>() {
            @Override
            public void onChanged(List<Usuario> usuarios) {
                // Variable que contendrá el texto a mostrar en el TextView
                String texto="";
                // Recorremos la lista de usuarios para mostrar su información
                for (Usuario u: usuarios){
                    texto+="Nombre: "+u.getNombre()+" Edad: "+u.getEdad()+"\n";
                }
                // Mostramos la lista de usuarios en el TextView (tvLista)
                binding.tvLista.setText(texto);
            }
        };
        // El ViewModel observa los cambios en la lista de usuarios (LiveData)
        userVM.getUserList().observe(this, listObserver);
    }
}