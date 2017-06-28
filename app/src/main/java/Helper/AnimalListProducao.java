package Helper;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.assis.firebase.R;

import java.util.List;

import Entidades.Animal;

public class AnimalListProducao  extends ArrayAdapter<Animal> {

    private Activity context;
    private List<Animal> animais;

    public AnimalListProducao (Activity context, List<Animal> animaisList){
        super(context, R.layout.list_layout, animaisList);
        this.context = context;
        this.animais = animaisList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_producao_layout, null, true);


        TextView textViewNome = (TextView) listViewItem.findViewById(R.id.textViewNome);
        TextView textViewNumero = (TextView) listViewItem.findViewById(R.id.textViewNumero);
        EditText editTextPl  = (EditText) listViewItem.findViewById(R.id.editTextManha);
        Animal animal = animais.get(position);


        textViewNumero.setText(animal.getAnimalNumero());
        textViewNome.setText(animal.getAnimalNome());


        return listViewItem;

    }
}