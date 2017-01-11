package com.example.androidsp.appgplx.BtnDethi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.androidsp.appgplx.R;

import java.util.ArrayList;

/**
 * Created by Androidsp on 10/01/2017.
 */

public class PlaceholderFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    TextView txtv_NoidungCauhoi;
    private CheckBox chkDapAn1, chkDapAn2, chkDapAn3, chkDapAn4;
    private ObjCauHoi objCauHoi;
    private LinearLayout ll_image;
    private ImageView img_image;
    private String[] dapan;
    private int dapanchon = 0;
    private static int tongdapandung = 0;

    public ArrayList<String> dsDapanchon = new ArrayList<>();
    private static final String ARG_SECTION_NUMBER = "section_number";


    public static PlaceholderFragment newInstance(ObjCauHoi objCauHoi) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_SECTION_NUMBER, objCauHoi);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            objCauHoi = (ObjCauHoi) getArguments().getSerializable(ARG_SECTION_NUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_giaodien_dethi, container, false);

        initView(rootView);

        hienthiviewpage();
        //Log.e("TAG", objCauHoi.getTenAnh());
//        Intent intent = new Intent(getActivity(),MainOver_Activity.class);
////        intent.putExtra(Constants.LIST_TEST, arrCauhoi);
//        intent.putExtra("Dapanchon", dsDapanchon);
//        startActivity(intent);

        return rootView;
    }

    private void initView(View rootView) {

        ll_image = (LinearLayout) rootView.findViewById(R.id.ll_image);
        img_image = (ImageView) rootView.findViewById(R.id.img_image);


        txtv_NoidungCauhoi = (TextView) rootView.findViewById(R.id.txtv_DbNoiDungCauHoi);

        chkDapAn1 = (CheckBox) rootView.findViewById(R.id.checkBox_DapAn1);
        chkDapAn2 = (CheckBox) rootView.findViewById(R.id.checkBox_DapAn2);
        chkDapAn3 = (CheckBox) rootView.findViewById(R.id.checkBox_DapAn3);
        chkDapAn4 = (CheckBox) rootView.findViewById(R.id.checkBox_DapAn4);

        chkDapAn1.setOnCheckedChangeListener(this);
        chkDapAn2.setOnCheckedChangeListener(this);
        chkDapAn3.setOnCheckedChangeListener(this);
        chkDapAn4.setOnCheckedChangeListener(this);
    }

    private void hienthiviewpage() {

        // TODO: Hiển thị Nội dung câu hỏi - Đáp án 1 , 2
        txtv_NoidungCauhoi.setText(objCauHoi.getNoidung());
        chkDapAn1.setText(objCauHoi.getDapAn1());
        chkDapAn2.setText(objCauHoi.getDapAn2());

        // TODO: Ẩn đáp án 3-4 nếu không có dữ liệu)
        chkDapAn3.setVisibility(View.GONE);
        chkDapAn4.setVisibility(View.GONE);

        showDapan3();
        showDapan4();

        showanh();

    }

    private void showDapan4() {
        if (objCauHoi.getDapAn4().trim().length() != 0) {
            chkDapAn4.setText(objCauHoi.getDapAn4());
            chkDapAn4.setVisibility(View.VISIBLE);
        }
    }

    private void showDapan3() {
        if (objCauHoi.getDapAn3().trim().length() != 0) {
            chkDapAn3.setText(objCauHoi.getDapAn3());
            chkDapAn3.setVisibility(View.VISIBLE);
        }
    }

    // TODO: Load ảnh
    private void showanh() {
        if (objCauHoi.getTenAnh() == null) {
            ll_image.setVisibility(View.GONE);

        } else {
            if (objCauHoi.getTenAnh().isEmpty()) {
                ll_image.setVisibility(View.GONE);

            } else if (objCauHoi.getTenAnh().equals(" ")) {
                //Toast.makeText(getActivity(), "rỗng", Toast.LENGTH_SHORT).show();
                img_image.setVisibility(View.GONE);
                ll_image.setVisibility(View.GONE);

            } else {
                //Toast.makeText(getActivity(), objCauHoi.getTenAnh()+"", Toast.LENGTH_SHORT).show();

                img_image.setVisibility(View.VISIBLE);
                ll_image.setVisibility(View.VISIBLE);
                Glide.with(this).load("file:///android_asset/imagesques/" + objCauHoi.getTenAnh() + ".jpg").into(img_image);
            }
        }
    }

    //TODO: Xét check đáp án
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        dapan = String.valueOf(objCauHoi.getDapandung()).split("");

        // 1
        if (buttonView == chkDapAn1 && isChecked == true) {
            if (tongdapandung != 0 && objCauHoi.isKiemtra() == true)
                tongdapandung--;
                dsDapanchon.add("1");
                dapanchon++;

        } else if (buttonView == chkDapAn1 && isChecked == false) {
            dapanchon--;
            dsDapanchon.remove("1");
            if (tongdapandung != 0 && objCauHoi.isKiemtra() == true) tongdapandung--;
        }

        // 2
        if (buttonView == chkDapAn2 && isChecked == true) {
            if (tongdapandung != 0 && objCauHoi.isKiemtra() == true) tongdapandung--;
            dsDapanchon.add("2");
            dapanchon++;
        } else if (buttonView == chkDapAn2 && isChecked == false) {
            dapanchon--;
            dsDapanchon.remove("2");
            if (tongdapandung != 0 && objCauHoi.isKiemtra() == true) tongdapandung--;
        }

        // 3
        if (buttonView == chkDapAn3 && isChecked == true) {
            if (tongdapandung != 0 && objCauHoi.isKiemtra() == true) tongdapandung--;
            dsDapanchon.add("3");
            dapanchon++;
        } else if (buttonView == chkDapAn3 && isChecked == false) {
            dapanchon--;
            dsDapanchon.remove("3");
            if (tongdapandung != 0 && objCauHoi.isKiemtra() == true) tongdapandung--;
        }

        // 4
        if (buttonView == chkDapAn4 && isChecked == true) {
            if (tongdapandung != 0 && objCauHoi.isKiemtra() == true)

            tongdapandung--;
            dsDapanchon.add("4");
            dapanchon++;
        } else if (buttonView == chkDapAn4 && isChecked == false) {
            dapanchon--;
            dsDapanchon.remove("4");
            if (tongdapandung != 0 && objCauHoi.isKiemtra() == true) tongdapandung--;
        }

        int demdapandung = 0;

        if (dapanchon == dapan.length - 1) {
            for (int i = 1; i < dapan.length; i++) {
                if (!dapan[i].isEmpty()) {
                    for (int j = 0; j < dsDapanchon.size(); j++) {
                        if (Integer.parseInt(dapan[i]) == Integer.parseInt(dsDapanchon.get(j))) {
                            demdapandung++;
                        }
                    }
                }
            }
            //
            if (demdapandung == dapan.length - 1) {
                objCauHoi.setKiemtra(true);
                tongdapandung++;
                Toast.makeText(getActivity(), "Đúng " + tongdapandung + " Câu", Toast.LENGTH_SHORT).show();
            } else {
                objCauHoi.setKiemtra(false);
            }
        } else {
            objCauHoi.setKiemtra(false);
            Toast.makeText(getActivity(), "Sai ", Toast.LENGTH_SHORT).show();
        }
    }
}

