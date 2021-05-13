package com.money.moneyworld.Fragment;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.money.moneyworld.R;
import com.money.moneyworld.databinding.FragmentContactUsBinding;

import de.mateware.snacky.Snacky;


public class Contact_Us extends Fragment implements View.OnClickListener {
    private FragmentContactUsBinding binding;
    private View view;

    public Contact_Us() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contact__us,container,false);
        view = binding.getRoot();

        binding.cardEmail.setOnClickListener(this);
        binding.cardPhoneNo.setOnClickListener(this);
        binding.cardWebSite.setOnClickListener(this);
    return binding.getRoot();}


    @SuppressLint("LongLogTag")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_email:
               Intent intent1 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","imxchange.imx@gmail.com", "Contact_us"));
                try {
                    startActivity(Intent.createChooser(intent1, "Choose an Email client :"));
                    Log.i("Finished sending email...", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Snacky.builder()
                            .setActivity(getActivity())
                            .setText("There is no email client installed.")
                            .setTextColor(getResources().getColor(R.color.white))
                            .setDuration(Snacky.LENGTH_SHORT)
                            .setActionText(android.R.string.ok)
                            .error()
                            .show();
                 }
                break;

            case R.id.card_phone_no_:
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+66646981930"));
                startActivity(intent);
                break;

            case R.id.card_webSite:
                String url = "https://www.imx.global";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;

        }
    }
}