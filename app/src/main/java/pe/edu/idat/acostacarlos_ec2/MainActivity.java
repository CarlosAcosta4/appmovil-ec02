package pe.edu.idat.acostacarlos_ec2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pe.edu.idat.acostacarlos_ec2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.btnlistado.setOnClickListener(this);
        binding.btnform.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnlistado){
            startActivity(new Intent(MainActivity.this,
                    ListadoActivity.class));
            //...
        }else if(v.getId() == R.id.btnform){
            startActivity(new Intent(MainActivity.this,
                    CuestionarioActivity.class));
        }
    }
}

