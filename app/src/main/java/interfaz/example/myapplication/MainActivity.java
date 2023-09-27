package interfaz.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText etTexto;
    Button btnGuardarTexto;
    Button btnMostrarTexto;
    TextView tvContenidoArchivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTexto = findViewById(R.id.etTexto);
        btnGuardarTexto = findViewById(R.id.btnGuardarTexto);
        btnMostrarTexto = findViewById(R.id.btnMostrarTexto);
        tvContenidoArchivo = findViewById(R.id.tvContenidoArchivo);

        // ACCIÓN PARA EL BOTÓN DE GUARDAR
        btnGuardarTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // LLAMADA AL MÉTODO PARA GUARDAR EL TEXTO EN UN ARCHIVO
                guardarArchivo();
            }
        });

        // ACCIÓN PARA EL BOTÓN DE MOSTRAR
        btnMostrarTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // LLAMADA AL MÉTODO PARA LEER Y MOSTRAR EL CONTENIDO DEL ARCHIVO
                mostrarContenidoArchivo();
            }
        });
    }

    // MÉTODO PARA CREAR Y GUARDAR LO ESCRITO EN EL EDITTEXT
    public void guardarArchivo() {
        try {
            FileOutputStream archivo = openFileOutput("Archivo.txt", Context.MODE_PRIVATE);
            // ESCRIBE EL CONTENIDO DEL EDITTEXT EN EL ARCHIVO
            archivo.write(etTexto.getText().toString().getBytes());
            archivo.close();

            Toast.makeText(this, "Texto guardado en el archivo", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("Archivo", "ERROR al escribir el archivo en memoria interna");
        }
    }

    // MÉTODO PARA LEER EL ARCHIVO Y MOSTRARLO EN EL TEXTVIEW
    public void mostrarContenidoArchivo() {
        try {
            BufferedReader aux = new BufferedReader(new InputStreamReader(openFileInput("Archivo.txt")));
            StringBuilder contenido = new StringBuilder();
            String linea;

            // LEE EL CONTENIDO DEL ARCHIVO LÍNEA POR LÍNEA
            while ((linea = aux.readLine()) != null) {
                contenido.append(linea).append("\n");
            }

            // MUESTRA EL CONTENIDO EN EL TEXTVIEW
            tvContenidoArchivo.setText("Contenido del Archivo:\n" + contenido.toString());
        } catch (Exception e) {
            Log.e("Archivo", "ERROR al leer el archivo desde la memoria interna");
        }
    }
}