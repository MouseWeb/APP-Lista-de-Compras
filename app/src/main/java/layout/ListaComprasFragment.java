package layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import paixaoporti.com.br.listadecompras.DicasActivity;
import paixaoporti.com.br.listadecompras.R;

public class ListaComprasFragment extends Fragment {

    private ImageView Dicas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_compras, container, false);

        Dicas = (ImageView) view.findViewById(R.id.Dicasid);
        Dicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                Intent f = new Intent(getActivity(),DicasActivity.class);
                startActivity(f);
            }

        });
        return view;
    }
}
