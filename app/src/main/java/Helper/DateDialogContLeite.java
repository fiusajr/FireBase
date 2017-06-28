package Helper;

        import android.app.DatePickerDialog;
        import android.app.Dialog;
        import android.app.DialogFragment;
        import android.icu.util.Calendar;
        import android.os.Build;
        import android.os.Bundle;
        import android.support.annotation.RequiresApi;
        import android.view.View;
        import android.widget.DatePicker;
        import android.widget.EditText;

/**
 * Created by Assis on 27/06/2017.
 */

public class DateDialogContLeite extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    EditText editTextDataContLeite;

    public DateDialogContLeite(View view){
        editTextDataContLeite=(EditText) view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Dialog onCreateDialog (Bundle savedInstanceState){

        final Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return  new DatePickerDialog(getActivity(),this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        String data = day +"/"+(month+1) +"/"+year;
        editTextDataContLeite.setText(data);


    }
}
