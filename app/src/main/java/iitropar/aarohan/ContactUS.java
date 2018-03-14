package iitropar.aarohan;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ContactUS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        getSupportActionBar().setTitle("Contact Us");
        TextView mail_ids[] = {findViewById(R.id.coreteam_email1), findViewById(R.id.coreteam_email2), findViewById(R.id.coreteam_email3), findViewById(R.id.coreteam_email4), findViewById(R.id.coreteam_email5), findViewById(R.id.codteam_email1), findViewById(R.id.codteam_email2), findViewById(R.id.codteam_email3), findViewById(R.id.codteam_email4), findViewById(R.id.codteam_email5), findViewById(R.id.codteam_email6), findViewById(R.id.codteam_email7), findViewById(R.id.codteam_email8),
                findViewById(R.id.codteam_email9), findViewById(R.id.codteam_email10), findViewById(R.id.codteam_email11), findViewById(R.id.codteam_email12), findViewById(R.id.codteam_email13), findViewById(R.id.codteam_email14), findViewById(R.id.codteam_email15)};

        View.OnClickListener EmailOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = (TextView) v;
                String[] sendTo = {text.getText().toString()};
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, sendTo);

                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        };
        for (int i = 0; i < mail_ids.length; i++)
            mail_ids[i].setOnClickListener(EmailOnClick);

        View.OnClickListener PhoneOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = (TextView) v;
                Intent phone = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + text.getText().toString()));
//                phone.putExtra(Intent.EXTRA_PHONE_NUMBER, text.getText().toString());
                startActivity(Intent.createChooser(phone, "Choose an Phone client :"));
            }
        };
        TextView PhoneNos[] = {findViewById(R.id.coreteam_phone1), findViewById(R.id.coreteam_phone2), findViewById(R.id.coreteam_phone3), findViewById(R.id.coreteam_phone4), findViewById(R.id.coreteam_phone5), findViewById(R.id.codteam_phone1), findViewById(R.id.codteam_phone2), findViewById(R.id.codteam_phone3), findViewById(R.id.codteam_phone4), findViewById(R.id.codteam_phone5), findViewById(R.id.codteam_phone6), findViewById(R.id.codteam_phone7), findViewById(R.id.codteam_phone8),
                findViewById(R.id.codteam_phone9), findViewById(R.id.codteam_phone10), findViewById(R.id.codteam_phone11), findViewById(R.id.codteam_phone12), findViewById(R.id.codteam_phone13), findViewById(R.id.codteam_phone14), findViewById(R.id.codteam_phone15)};
        for (int i = 0; i < PhoneNos.length; i++)
            PhoneNos[i].setOnClickListener(PhoneOnClick);
    }
}
