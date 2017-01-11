package com.example.androidsp.appgplx.BtnDethi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.androidsp.appgplx.R;

import java.util.ArrayList;

public class MainOver_Activity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private long TIMER = 0;
    private TextView tv_timer, tv_dat, tv_khongdat, tv_index_ques;
    private RadioButton rb_sum, rb_mark, rb_wrong, rb_exclamation;
    private GridView gv_ques;
    private int mark = 0, wrong = 0, excl = 0;

    public ArrayList<String> dsDapanchon = new ArrayList<>();
    private ArrayList<ObjCauHoi> listCauhoi = new ArrayList<>();
    private ArrayList<ObjCauHoi> arrCauhoi = new ArrayList<>();
    private ArrayList<ObjectMainOver> arrResult = new ArrayList<>();
    private ArrayList<ObjectMainOver> arrResultCheck = new ArrayList<>();


    private MainOverAdapter maniOverAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_over);


        getDataDef();
        findViewById();
        setupData();


    }

    private void getDataDef() {
        TIMER = getIntent().getLongExtra("Thời gian",0);
        dsDapanchon = (ArrayList<String>) getIntent().getSerializableExtra("Dapanchon");
    }

    private void setupData() {
        getDapan();

        tv_index_ques.setText(mark + "/" + arrCauhoi.size());

        if (mark >= 16) {
            tv_dat.setVisibility(View.VISIBLE);
            tv_khongdat.setVisibility(View.GONE);
        } else {
            tv_dat.setVisibility(View.GONE);
            tv_khongdat.setVisibility(View.VISIBLE);
        }

        //text sum
        rb_sum.setChecked(true);
        rb_sum.setText(arrCauhoi.size() + "");
        //text mark
        rb_mark.setText(mark + "");
        //text wrong
        rb_wrong.setText(wrong + "");
        //text exclamation
        rb_exclamation.setText(excl + "");

        arrResultCheck.clear();
        arrResultCheck.addAll(arrResult);
        maniOverAdapter = new MainOverAdapter(this, arrResultCheck);
        gv_ques.setAdapter(maniOverAdapter);
    }

    private void getDapan() {
        mark = 0;
        wrong = 0;
        excl = 0;
        for (int i = 0; i < arrCauhoi.size(); i++) {
            if (dsDapanchon.get(i) == null) {
                arrResult.add(new ObjectMainOver(i, 3));
                excl++;
            } else {
                if (dsDapanchon.get(i).trim().isEmpty()) {
                    arrResult.add(new ObjectMainOver(i, 3));
                    excl++;
                } else if (dsDapanchon.get(i).trim().equals(arrCauhoi.get(i).getDapandung())) {
                    arrResult.add(new ObjectMainOver(i, 1));
                    mark++;
                } else {
                    arrResult.add(new ObjectMainOver(i, 2));
                    wrong++;

                    boolean check = true;
                    for (ObjCauHoi obj : listCauhoi) {
                        if (obj.getIdCauhoi() == arrCauhoi.get(i).getIdCauhoi()) {
                            check = false;
                        }
                    }

                    if (check) {
                        listCauhoi.add(arrCauhoi.get(i));
                    }
                }
            }
        }

    }

    private void findViewById() {
        tv_timer = (TextView) findViewById(R.id.tv_timer);
        tv_dat = (TextView) findViewById(R.id.tv_dat);
        tv_khongdat = (TextView) findViewById(R.id.tv_khongdat);
        tv_index_ques = (TextView) findViewById(R.id.tv_index_ques);

        rb_sum = (RadioButton) findViewById(R.id.rb_sum);
        rb_mark = (RadioButton) findViewById(R.id.rb_mark);
        rb_wrong = (RadioButton) findViewById(R.id.rb_wrong);
        rb_exclamation = (RadioButton) findViewById(R.id.rb_exclamation);

        gv_ques = (GridView) findViewById(R.id.gv_ques);

        rb_sum.setOnCheckedChangeListener(this);
        rb_mark.setOnCheckedChangeListener(this);
        rb_wrong.setOnCheckedChangeListener(this);
        rb_exclamation.setOnCheckedChangeListener(this);

        int minutes = (int) (TIMER / 1000 / 60);
        int seconds = (int) ((TIMER / 1000) % 60);
        String Minutes = String.format("%02d", minutes);
        String Seconds = String.format("%02d", seconds);

        tv_timer.setText(Minutes + ":" + Seconds);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (maniOverAdapter != null && isChecked) {
            switch (buttonView.getId()) {
                case R.id.rb_sum:
                    arrResultCheck.clear();
                    arrResultCheck.addAll(arrResult);
                    break;

                case R.id.rb_mark:
                    arrResultCheck.clear();
                    for (int i = 0; i < arrResult.size(); i++) {
                        if (arrResult.get(i).getCheckQues() == 1) {
                            arrResultCheck.add(arrResult.get(i));
                        }
                    }
                    break;

                case R.id.rb_wrong:
                    arrResultCheck.clear();
                    for (int i = 0; i < arrResult.size(); i++) {
                        if (arrResult.get(i).getCheckQues() == 2) {
                            arrResultCheck.add(arrResult.get(i));
                        }
                    }
                    break;

                case R.id.rb_exclamation:
                    arrResultCheck.clear();
                    for (int i = 0; i < arrResult.size(); i++) {
                        if (arrResult.get(i).getCheckQues() == 3) {
                            arrResultCheck.add(arrResult.get(i));
                        }
                    }
                    break;
            }
            maniOverAdapter.notifyDataSetChanged();
        }
    }
}
