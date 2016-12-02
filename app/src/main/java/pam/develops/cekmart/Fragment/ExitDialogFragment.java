package pam.develops.cekmart.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import pam.develops.cekmart.R;

/**
 * Created by paulms on 5/15/2016.
 */
public class ExitDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
// set message in string.xml
        builder.setMessage(R.string.dialog_exit)
// if choose positive button
                .setPositiveButton(R.string.exit, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if choose positive button, set Toast Deleted
                        //Toast.makeText(getActivity().getApplicationContext(), "Exited", Toast.LENGTH_LONG).show();
                        getActivity().finishAffinity();
                    }
                })
// if choose negative button
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if choose negative button, set Toast Canceled
                        //Toast.makeText(getActivity().getApplicationContext(), "Canceled", Toast.LENGTH_LONG).show();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
