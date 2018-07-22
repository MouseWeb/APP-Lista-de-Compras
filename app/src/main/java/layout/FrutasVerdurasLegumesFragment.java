package layout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import paixaoporti.com.br.listadecompras.R;
import static android.content.Context.MODE_PRIVATE;

public class FrutasVerdurasLegumesFragment extends Fragment {

    private EditText textoTarefa5;
    private ImageView botaoAdicionar5;
    private ListView listaTarefas5;
    private SQLiteDatabase bancoDados5;
    private ArrayAdapter<String> itensAdaptador5;
    private ArrayList<String> itens5;
    private ArrayList<Integer> ids5;
    private ImageView C_Frutas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frutas_verduras_legumes, container, false);

        C_Frutas = (ImageView) view.findViewById(R.id.C_Frutasid);

        C_Frutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareText();
            }

            private void shareText() {
                // cria a intent e define a ação
                Intent intent = new Intent(Intent.ACTION_SEND);
                // tipo de conteúdo da intent
                intent.setType("text/plain");
                // string a ser enviada para outra intent
                intent.putExtra(Intent.EXTRA_TEXT, "LISTA DE COMPRAS (FRUTAS, VERDURAS E LEGUMES)" + itens5 );
                // inicia a intent
                startActivity(intent);
            }

        });

        try {

            //Recuperar componentes
            textoTarefa5 = (EditText) view.findViewById(R.id.textoFrutasid);
            botaoAdicionar5 = (ImageView) view.findViewById(R.id.botaoFrutasid);

            //lista
            listaTarefas5 = (ListView) view.findViewById(R.id.listaFrutasid);

            //Banco dados
            bancoDados5 = getActivity().openOrCreateDatabase("apptarefas", MODE_PRIVATE, null);

            //tabela tarefas
            bancoDados5.execSQL("CREATE TABLE IF NOT EXISTS Frutas(id INTEGER PRIMARY KEY AUTOINCREMENT, Frutas VARCHAR ) ");

            botaoAdicionar5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String textoDigitado = textoTarefa5.getText().toString();
                    salvarTarefa(textoDigitado);

                }
            });

            listaTarefas5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    removerTarefa( ids5.get( position ) );
                }
            });

            listaTarefas5.setLongClickable(true);
            listaTarefas5.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    removerTarefa( ids5.get( position ) );
                    return true;
                }
            });

            //recuperar tarefas
            recuperarTarefas();


        }catch(Exception e){
            e.printStackTrace();
        }
        return view;
    }

    private void salvarTarefa(String texto){

        try{

            if( texto.equals("") ){
                Toast.makeText(getActivity(), "Digite um produto", Toast.LENGTH_SHORT).show();
            }else{
                bancoDados5.execSQL("INSERT INTO Frutas (Frutas) VALUES('" + texto + "') ");
                Toast.makeText(getActivity(), "Produto salvo com sucesso!", Toast.LENGTH_SHORT).show();
                recuperarTarefas();
                textoTarefa5.setText("");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void recuperarTarefas(){
        try{

            //Recuperar as tarefas
            Cursor cursor = bancoDados5.rawQuery("SELECT * FROM Frutas ORDER BY id DESC", null);

            //recuperar os ids das colunas
            int indiceColunaId = cursor.getColumnIndex("id");
            int indiceColunaTarefa = cursor.getColumnIndex("Frutas");

            //Criar adaptador
            itens5 = new ArrayList<String>();
            ids5 = new ArrayList<Integer>();
            itensAdaptador5 = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                    android.R.layout.simple_list_item_2,
                    android.R.id.text2,
                    itens5){
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {

                    View view = super.getView(position, convertView, parent);
                    TextView text = (TextView) view.findViewById(android.R.id.text2);
                    text.setTextColor(Color.BLACK);
                    return view;

                }
            };
            listaTarefas5.setAdapter( itensAdaptador5 );

            //listar as tarefas
            cursor.moveToFirst();
            while ( cursor != null ){

                Log.i("Resultado - ", "Id Frios: " + cursor.getString( indiceColunaId ) + " Frutas: " + cursor.getString( indiceColunaTarefa ) );
                itens5.add(cursor.getString(indiceColunaTarefa));
                ids5.add( Integer.parseInt(cursor.getString(indiceColunaId)) );

                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void removerTarefa(Integer id){
        try{

            bancoDados5.execSQL("DELETE FROM Frutas WHERE id="+id);
            recuperarTarefas();
            Toast.makeText(getActivity(), "Produto removido com sucesso!", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}


