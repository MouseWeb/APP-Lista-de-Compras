package paixaoporti.com.br.listadecompras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dica10Activity extends AppCompatActivity {

    private Button Dicas10Voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dica10);

        Dicas10Voltar = (Button) findViewById(R.id.voltar10id);
        Dicas10Voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                Intent f = new Intent(dica10Activity.this,DicasActivity.class);
                startActivity(f);
                finish();
            }

        });
    }
}
