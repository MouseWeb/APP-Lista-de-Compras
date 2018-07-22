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

public class OutrosFragment extends Fragment {

    private EditText textoTarefa8;
    private ImageView botaoAdicionar8;
    private ListView listaTarefas8;
    private SQLiteDatabase bancoDados8;
    private ArrayAdapter<String> itensAdaptador8;
    private ArrayList<String> itens8;
    private ArrayList<Integer> ids8;
    private ImageView C_Outros;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_outros, container, false);

        C_Outros = (ImageView) view.findViewById(R.id.C_Outrosid);

        C_Outros.setOnClickListener(new View.OnClickListener() {
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
                intent.putExtra(Intent.EXTRA_TEXT, "LISTA DE COMPRAS (OUTROS)" + itens8 );
                // inicia a intent
                startActivity(intent);
            }

        });

        try {

            //Recuperar componentes
            textoTarefa8 = (EditText) view.findViewById(R.id.textoOutrosid);
            botaoAdicionar8 = (ImageView) view.findViewById(R.id.botaoOutrosid);

            //lista
            listaTarefas8 = (ListView) view.findViewById(R.id.listaOutrosid);

            //Banco dados
            bancoDados8 = getActivity().openOrCreateDatabase("apptarefas", MODE_PRIVATE, null);

            //tabela tarefas
            bancoDados8.execSQL("CREATE TABLE IF NOT EXISTS Outros(id INTEGER PRIMARY KEY AUTOINCREMENT, Outros VARCHAR ) ");

            botaoAdicionar8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String textoDigitado = textoTarefa8.getText().toString();
                    salvarTarefa(textoDigitado);

                }
            });

            listaTarefas8.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    removerTarefa( ids8.get( position ) );
                }
            });

            listaTarefas8.setLongClickable(true);
            listaTarefas8.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    removerTarefa( ids8.get( position ) );
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
                bancoDados8.execSQL("INSERT INTO Outros (Outros) VALUES('" + texto + "') ");
                Toast.makeText(getActivity(), "Produto salvo com sucesso!", Toast.LENGTH_SHORT).show();
                recuperarTarefas();
                textoTarefa8.setText("");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void recuperarTarefas(){
        try{

            //Recuperar as tarefas
            Cursor cursor = bancoDados8.rawQuery("SELECT * FROM Outros ORDER BY id DESC", null);

            //recuperar os ids das colunas
            int indiceColunaId = cursor.getColumnIndex("id");
            int indiceColunaTarefa = cursor.getColumnIndex("Outros");

            //Criar adaptador
            itens8 = new ArrayList<String>();
            ids8 = new ArrayList<Integer>();
            itensAdaptador8 = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                    android.R.layout.simple_list_item_2,
                    android.R.id.text2,
                    itens8){
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {

                    View view = super.getView(position, convertView, parent);
                    TextView text = (TextView) view.findViewById(android.R.id.text2);
                    text.setTextColor(Color.BLACK);
                    return view;

                }
            };
            listaTarefas8.setAdapter( itensAdaptador8 );

            //listar as tarefas
            cursor.moveToFirst();
            while ( cursor != null ){

                Log.i("Resultado - ", "Id Outros: " + cursor.getString( indiceColunaId ) + " Outros: " + cursor.getString( indiceColunaTarefa ) );
                itens8.add(cursor.getString(indiceColunaTarefa));
                ids8.add( Integer.parseInt(cursor.getString(indiceColunaId)) );

                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void removerTarefa(Integer id){
        try{

            bancoDados8.execSQL("DELETE FROM Outros WHERE id="+id);
            recuperarTarefas();
            Toast.makeText(getActivity(), "Produto removido com sucesso!", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}

