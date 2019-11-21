package com.norbertoledo.pac_desarrollo_m08;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;


public class DialogForm extends AppCompatDialogFragment {

    // Declaraciones
    private EditText formName;
    private EditText formSurname;
    private EditText formPhone;

    DialogFormListener listener;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    RadioGroup genderRadioGroup;
    RadioButton rbMale;
    RadioButton rbFemale;

    private boolean[] fieldsContentData = {false, false, false};


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Definir la configuracion de la vista y setear los botones del formulario
        builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_form, null);

        builder.setView(view)
        .setTitle(R.string.form_title)
        .setNegativeButton(R.string.form_btn_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
        .setPositiveButton(R.string.form_btn_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String name = formName.getText().toString();
                String surname = formSurname.getText().toString();
                String phone = formPhone.getText().toString();
                String gender;
                if(rbMale.isChecked()){
                    gender = "M";
                } else{
                    gender = "F";
                }

                listener.applyData(name, surname, phone, gender);

            }
        });

        // Instancias
        formName = view.findViewById(R.id.formName);
        formSurname = view.findViewById(R.id.formSurname);
        formPhone = view.findViewById(R.id.formPhone);
        rbMale = view.findViewById(R.id.rbMale);
        rbFemale = view.findViewById(R.id.rbFemale);


        // Listener para validar que todos los campos tengan valor
        // y así poder activar el boton de de envio de datos.

        // Validacion campo Name
        formName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count>0){
                    fieldsContentData[0]=true;
                }else{
                    fieldsContentData[0]=false;
                }
                validateSendButton();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        // Validacion campo surname
        formSurname.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count>0){
                    fieldsContentData[1]=true;
                }else{
                    fieldsContentData[1]=false;
                }
                validateSendButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

        // Validacion campo phone
        formPhone.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count>0){
                    fieldsContentData[2]=true;
                }else{
                    fieldsContentData[2]=false;
                }
                validateSendButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

        // Validar estado de Boton de envío en el foco inicial del campo name
        formName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                validateSendButton();
            }
        });

        // Crear Dialog
        dialog = builder.create();

        return dialog;
    }


    // Validar boton de envio
    public void validateSendButton(){
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        for( boolean field : fieldsContentData) {
            if (!field) {
                return;
            }
        }
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogFormListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException( context.toString() + "Debe implementar DialogFormListener");
        }

    }

    // Enviar datos del formulario
    public interface DialogFormListener{
        void applyData(String name, String surname, String phone, String gender);
    }
}
