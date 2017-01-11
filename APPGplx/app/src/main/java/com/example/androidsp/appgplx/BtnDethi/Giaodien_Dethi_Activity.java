package com.example.androidsp.appgplx.BtnDethi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.example.androidsp.appgplx.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class Giaodien_Dethi_Activity extends AppCompatActivity implements View.OnClickListener{

    private long mTimer;
    private long TIMER = 15 * 60 * 1000;
    private TextView txtvTimer, tv_index_ques;
    private int dem = 0;
    private CountDownTimer mCountDownTimer;
    private ImageView img_done;

    public ArrayList<ObjCauHoi> arrCauHoiRandom;

    private ObjCauHoi objCauHoi;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private TabLayout tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaodien_dethi);

        initView();
        setupData();

        setHamtinhthoigian();


        mViewPager.setPageTransformer(true, new RotateUpTransformer());

        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                page.setRotationY(position * -30);
            }
        });


    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.container);
        img_done = (ImageView) findViewById(R.id.img_done);
        tv_index_ques = (TextView) findViewById(R.id.tv_index_ques);
        txtvTimer = (TextView) findViewById(R.id.txtvTimer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        img_done.setOnClickListener(this);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
    }

    private void setupData() {

        arrCauHoiRandom = new ArrayList<ObjCauHoi>();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference mData = firebaseDatabase.getReference("tbCauhoi");
        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dem = (int) dataSnapshot.getChildrenCount();
                
                for (int i = 0; i < dem; i++) {
                    objCauHoi = new ObjCauHoi();

                    //TODO: Load dữ liệu từ Firebase
                    objCauHoi.setIdCauhoi(dataSnapshot.child(String.valueOf(i) + "/idCauhoi").getValue(Integer.class));
                    objCauHoi.setIdCauhoi(dataSnapshot.child(String.valueOf(i) + "/idLoaiCauhoi").getValue(Integer.class));
                    objCauHoi.setNoidung(dataSnapshot.child(String.valueOf(i) + "/Cauhoi").getValue(String.class));
                    objCauHoi.setDapAn1(dataSnapshot.child(String.valueOf(i) + "/DapAn1").getValue(String.class));
                    objCauHoi.setDapAn2(dataSnapshot.child(String.valueOf(i) + "/DapAn2").getValue(String.class));
                    objCauHoi.setDapAn3(dataSnapshot.child(String.valueOf(i) + "/DapAn3").getValue(String.class));
                    objCauHoi.setDapAn4(dataSnapshot.child(String.valueOf(i) + "/DapAn4").getValue(String.class));
                    objCauHoi.setTenAnh(dataSnapshot.child(String.valueOf(i) + "/TenAnh").getValue(String.class));
                    objCauHoi.setDapandung(dataSnapshot.child(String.valueOf(i) + "/DapAnDung").getValue(Integer.class));

                    arrCauHoiRandom.add(objCauHoi);

                }


                for (int i = 0; i <=10; i++){
                    if (objCauHoi.getIdLoaicauhoi() == 1) {
                        arrCauHoiRandom.size();
                    }
                }
                for (int i = 0; i <=5; i++){
                    if (objCauHoi.getIdLoaicauhoi() == 2) {
                        arrCauHoiRandom.size();
                    }
                }
                for (int i = 0; i <=5; i++){
                    if (objCauHoi.getIdLoaicauhoi() == 3) {
                        arrCauHoiRandom.size();
                    }
                }
                Collections.shuffle(arrCauHoiRandom);
                //Log.e("TAG", objCauHoi.getTenAnh());
                arrCauHoiRandom.size();
                mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), arrCauHoiRandom);
                mViewPager.setAdapter(mSectionsPagerAdapter);
                tabLayout.setupWithViewPager(mViewPager);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_done:
                showDialogFinish();
                break;
        }

    }

    private void showDialogFinish() {
        Dialog dialog;
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn kết thúc bài thi không?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Có",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishTest();
                    }
                });

        builder.setNegativeButton(
                "Không",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        showDialogBack();
    }

    private void showDialogBack() {
        Dialog dialog;
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn thoát không?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Có",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });

        builder.setNegativeButton(
                "Không",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        dialog = builder.create();
        dialog.show();
    }

    //TODO: Tính thời gian
    private void setHamtinhthoigian() {
        txtvTimer.setText("15:00");
        mTimer = TIMER;
        startCountdown();
    }

    private void startCountdown() {
        mCountDownTimer = new CountDownTimer(mTimer, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimer = millisUntilFinished;

                int minutes = (int) (millisUntilFinished / 1000 / 60);
                int seconds = (int) ((millisUntilFinished / 1000) % 60);

                String Minutes = String.format("%02d", minutes);
                String Seconds = String.format("%02d", seconds);

                txtvTimer.setText(minutes + ":" + Seconds);
            }

            @Override
            public void onFinish() {
                mTimer = 0;
                txtvTimer.setText("00:00");
                finishTest();
                if (mCountDownTimer != null) {
                    mCountDownTimer.cancel();
                    mCountDownTimer = null;
                }
            }
        }.start();
    }

    private void finishTest() {
        Toast.makeText(this, "Kết thúc bài thi", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainOver_Activity.class);
//        intent.putExtra(Constants.LIST_TEST, arrCauhoi);
//        intent.putExtra(Constants.LIST_DAPANCHON, arrDapanchon);
        intent.putExtra("Thời gian", TIMER - mTimer);
//        intent.putExtra(Constants.CAUHOIBIENBAO, listCauhoiBienbao);
        startActivity(intent);
        finish();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_giaodien_dethi, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case android.R.id.home:
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
