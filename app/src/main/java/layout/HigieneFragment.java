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

public class HigieneFragment extends Fragment {

    private EditText textoTarefa6;
    private ImageView botaoAdicionar6;
    private ListView listaTarefas6;
    private SQLiteDatabase bancoDados6;
    private ArrayAdapter<String> itensAdaptador6;
    private ArrayList<String> itens6;
    private ArrayList<Integer> ids6;
    private ImageView C_Higiene;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_higiene, container, false);

        C_Higiene = (ImageView) view.findViewById(R.id.C_Higieneid);

        C_Higiene.setOnClickListener(new View.OnClickListener() {
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
                intent.putExtra(Intent.EXTRA_TEXT, "LISTA DE COMPRAS (HIGIENE)" + itens6 );
                // inicia a intent
                startActivity(intent);
            }

        });

        try {

            //Recuperar componentes
            textoTarefa6 = (EditText) view.findViewById(R.id.textoHigieneid);
            botaoAdicionar6 = (ImageView) view.findViewById(R.id.botaoHigieneid);

            //lista
            listaTarefas6 = (ListView) view.findViewById(R.id.listaHigieneid);

            //Banco dados
            bancoDados6 = getActivity().openOrCreateDatabase("apptarefas", MODE_PRIVATE, null);

            //tabela tarefas
            bancoDados6.execSQL("CREATE TABLE IF NOT EXISTS Higiene(id INTEGER PRIMARY KEY AUTOINCREMENT, Higiene VARCHAR ) ");

            botaoAdicionar6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String textoDigitado = textoTarefa6.getText().toString();
                    salvarTarefa(textoDigitado);

                }
            });

            listaTarefas6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    removerTarefa( ids6.get( position ) );
                }
            });

            listaTarefas6.setLongClickable(true);
            listaTarefas6.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    removerTarefa( ids6.get( position ) );
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
                bancoDados6.execSQL("INSERT INTO Higiene (Higiene) VALUES('" + texto + "') ");
                Toast.makeText(getActivity(), "Produto salvo com sucesso!", Toast.LENGTH_SHORT).show();
                recuperarTarefas();
                textoTarefa6.setText("");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void recuperarTarefas(){
        try{

            //Recuperar as tarefas
            Cursor cursor = bancoDados6.rawQuery("SELECT * FROM Higiene ORDER BY id DESC", null);

            //recuperar os ids das colunas
            int indiceColunaId = cursor.getColumnIndex("id");
            int indiceColunaTarefa = cursor.getColumnIndex("Higiene");

            //Criar adaptador
            itens6 = new ArrayList<String>();
            ids6 = new ArrayList<Integer>();
            itensAdaptador6 = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                    android.R.layout.simple_list_item_2,
                    android.R.id.text2,
                    itens6){
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {

                    View view = super.getView(position, convertView, parent);
                    TextView text = (TextView) view.findViewById(android.R.id.text2);
                    text.setTextColor(Color.BLACK);
                    return view;

                }
            };
            listaTarefas6.setAdapter( itensAdaptador6 );

            //listar as tarefas
            cursor.moveToFirst();
            while ( cursor != null ){

                Log.i("Resultado - ", "Id Higiene: " + cursor.getString( indiceColunaId ) + " Higiene: " + cursor.getString( indiceColunaTarefa ) );
                itens6.add(cursor.getString(indiceColunaTarefa));
                ids6.add( Integer.parseInt(cursor.getString(indiceColunaId)) );

                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void removerTarefa(Integer id){
        try{

            bancoDados6.execSQL("DELETE FROM Higiene WHERE id="+id);
            recuperarTarefas();
            Toast.makeText(getActivity(), "Produto removido com sucesso!", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}


