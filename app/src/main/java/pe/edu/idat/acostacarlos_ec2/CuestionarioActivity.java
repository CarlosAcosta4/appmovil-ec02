package pe.edu.idat.acostacarlos_ec2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import pe.edu.idat.acostacarlos_ec2.databinding.ActivityCuestionarioBinding;

public class CuestionarioActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    private ActivityCuestionarioBinding binding;

    private List<String> personas = new ArrayList<>();
    private List<String> sintomas = new ArrayList<>();
    private List<String> servicios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCuestionarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.cb01.setOnClickListener(this);
        binding.cb02.setOnClickListener(this);
        binding.cb03.setOnClickListener(this);
        binding.cb04.setOnClickListener(this);
        binding.cb05.setOnClickListener(this);
        binding.cb06.setOnClickListener(this);
        binding.cb10.setOnClickListener(this);
        binding.cb11.setOnClickListener(this);
        binding.cb12.setOnClickListener(this);
        binding.cb14.setOnClickListener(this);
        binding.btnresolver.setOnClickListener(this);
    }

    private void agregarQuitarServicio(View view, String servicio){
        boolean checked = ((CheckBox)view).isChecked();
        if(checked)
            servicios.add(servicio);
        else
            servicios.remove(servicio);

    }

    private void agregarQuitarSintomas(View view, String sintoma){
        boolean checked = ((CheckBox)view).isChecked();
        if(checked)
            sintomas.add(sintoma);
        else
            sintomas.remove(sintoma);
    }

    private Boolean validarServicios(){
        boolean respuesta = false;
        if(binding.cb01.isChecked() || binding.cb02.isChecked()
                || binding.cb03.isChecked() || binding.cb04.isChecked() || binding.cb05.isChecked() || binding.cb06.isChecked()){
            respuesta = true;
        }
        return respuesta;
    }

    private Boolean validarSintomas(){
        boolean respuesta = false;
        if(binding.cb10.isChecked() || binding.cb11.isChecked()
                || binding.cb12.isChecked() || binding.cb14.isChecked()){
            respuesta = true;
        }
        return respuesta;
    }

    private String obtenerFiebre(){
        String fiebre = "";
        if(binding.rg2.getCheckedRadioButtonId() == R.id.rb2no){
            fiebre = binding.rb2no.getText().toString();
        }else{
            fiebre = binding.rb2si.getText().toString();
        }
        return fiebre;
    }

    private String obtenerCasa(){
        String casa = "";
        if(binding.rg3.getCheckedRadioButtonId() == R.id.rb3no){
            casa = binding.rb3no.getText().toString();
        }else{
            casa = binding.rb3si.getText().toString();
        }
        return casa;
    }

    private String obtenerAdultoMayor(){
        String adulto = "";
        if(binding.rg4.getCheckedRadioButtonId() == R.id.rb4no){
            adulto = binding.rb4no.getText().toString();
        }else{
            adulto = binding.rb4si.getText().toString();
        }
        return adulto;
    }

    private Boolean validarFiebre(){
        boolean respuesta = true;
        if(binding.rg2.getCheckedRadioButtonId() == -1){
            respuesta = false;
        }
        return respuesta;
    }

    private Boolean validarSoloCasa(){
        boolean respuesta = true;
        if(binding.rg3.getCheckedRadioButtonId() == -1){
            respuesta = false;
        }
        return respuesta;
    }

    private Boolean validarAdultoMayor(){
        boolean respuesta = true;
        if(binding.rg4.getCheckedRadioButtonId() == -1){
            respuesta = false;
        }
        return respuesta;
    }

    private Boolean validarFormulario(){
        boolean respuesta = false;
        String mensaje = "";
        if(!validarSintomas()){
            mensaje ="Seleccione un sintoma ";
        }else if(!validarFiebre()){
            mensaje ="Seleccione una respuesta en la pregunta 02";
        }else if(!validarSoloCasa()){
            mensaje = "Seleccione una respuesta en la pregunta 03";
        }else if(!validarAdultoMayor()){
            mensaje = "Seleccione una respuesta en la pregunta 04";
        }else if(!validarServicios()){
            mensaje ="Seleccione un servicio";
        }else
            respuesta = true;
        if(!respuesta)
            Snackbar.make(binding.getRoot(), mensaje, Toast.LENGTH_LONG + 2000).show();
        return respuesta;
    }

    private void registrarPersona(){
        if(validarFormulario()){
            StringBuilder infoPersona = new StringBuilder();
            infoPersona.append(sintomas.toString()+"-");
            infoPersona.append(obtenerFiebre()+"-");
            infoPersona.append(obtenerCasa()+"-");
            infoPersona.append(obtenerAdultoMayor()+"-");
            infoPersona.append(servicios.toString()+"-");

            infoPersona.append(binding.swnotificacion.isChecked());
            personas.add(infoPersona.toString());


            Snackbar.make(binding.getRoot(), "Persona Registrada", Toast.LENGTH_LONG).show();
            setearControles();
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.cb01){
            agregarQuitarSintomas(v, "Disminucion del gusto");
        }else if(v.getId() == R.id.cb02){
            agregarQuitarSintomas(v, "Tos");
        }else if(v.getId() == R.id.cb03){
            agregarQuitarSintomas(v, "Dolor de garganta");
        }else if(v.getId() == R.id.cb04) {
            agregarQuitarSintomas(v, "Congestion nasal");
        }else if(v.getId() == R.id.cb05) {
            agregarQuitarSintomas(v, "Fiebre");
        }else if(v.getId() == R.id.cb06) {
            agregarQuitarSintomas(v, "Ninguno");
        }else if (v.getId() == R.id.btnresolver){
            Intent intentLista = new Intent(getApplicationContext(),
                    ListadoActivity.class);
            intentLista.putExtra("listapersonas",
                    (ArrayList<String>)personas);
            startActivity(intentLista);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
