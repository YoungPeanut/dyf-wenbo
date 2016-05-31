package com.dyf.wenbo.presenter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.dyf.wenbo.app.Configer;
import com.dyf.wenbo.exception.RegistException;
import com.dyf.wenbo.model.UserModel;
import com.dyf.wenbo.utils.HuanXinUtil;
import com.dyf.wenbo.utils.MyBitMapUtil;
import com.zhonghua.dileber.mvp.presenter.ActivityPresenter;
import com.dyf.wenbo.R;
import com.dyf.wenbo.delegate.RegistViewDelegate;
import com.dyf.wenbo.scm.IRegistScm;
import com.dyf.wenbo.scm.RegistScm;
import com.zhonghua.dileber.mvp.scm.INetListener;
import com.zhonghua.dileber.tools.annotation.CloseTitle;

import java.io.File;

@CloseTitle
public class RegistActivity extends ActivityPresenter<RegistViewDelegate>  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected Class<RegistViewDelegate> getDelegateClass() {
        return RegistViewDelegate.class;
    }

    ImageButton people_icon;
    IRegistScm registSrc;

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        registSrc = new RegistScm();
        people_icon = viewDelegate.get(R.id.people_icon);
        people_icon.setOnClickListener(this);
        viewDelegate.get(R.id.regist_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                UserModel userModel = null;
                try {
                    userModel = viewDelegate.getUser();
                } catch (RegistException e) {
                    //e.printStackTrace();
                    viewDelegate.toast(e.getMessage(), Toast.LENGTH_SHORT);
                    return;
                }
                final UserModel finalUserModel = userModel;
                registSrc.regist(userModel, new INetListener<UserModel>() {


                    @Override
                    public void before() {
                        viewDelegate.loading();
                    }

                    @Override
                    public void success(UserModel model) {
                        viewDelegate.loadDialogDismiss();
                        viewDelegate.toast("欢迎", Toast.LENGTH_SHORT);
                        //Intent it = new Intent();
                        //it.setClass(RegisterActivity.this, HomeActivity.class);
                        //startActivity(it);
                        finish();
                        HuanXinUtil.huanXinRx(finalUserModel);
                    }


                    @Override
                    public void failed() {
                        viewDelegate.loadDialogDismiss();
                    }

                    @Override
                    public void errMsg(String msg) {
                        viewDelegate.loadDialogDismiss();
                        viewDelegate.showAlert(viewDelegate.DIALOG_ERROR, msg);
                    }
                });
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.people_icon:
                btnPeopleIconClick(view);
                break;
        }
    }

    private Uri photoUri = null;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    if (data != null && data.getData() != null) {
                        Uri uri = data.getData();
                        photoUri = uri;
                    }
                    startImageAction(photoUri, 200, 200,3, true);
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    if (data != null && data.getData() != null) {
                        Uri uri = data.getData();
                        photoUri = uri;
                    }
                    startImageAction(photoUri, 200, 200,3, true);
                }
                break;
            case 3:
                if (data == null) {
                    return;
                } else {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Bitmap bitmap = extras.getParcelable("data");
                        if (bitmap != null) {
                            people_icon.setImageBitmap(MyBitMapUtil.toRoundBitmap(bitmap));
                        }
                    }
                }
                break;
            case Activity.RESULT_CANCELED:
                break;

        }

    }

    private void startImageAction(Uri uri, int outputX, int outputY,
                                  int requestCode, boolean isCrop) {
        Intent intent = null;
        if (isCrop) {
            intent = new Intent("com.android.camera.action.CROP");
        } else {
            intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        }
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent, requestCode);
    }


    private AlertDialog camdialog = null;
    public void btnPeopleIconClick(View v) {
        camdialog=new AlertDialog.Builder(this).create();
        camdialog.show();
        camdialog.getWindow().setWindowAnimations(R.anim.animation_in);
        camdialog.getWindow().setContentView(R.layout.dialog_camera);
        camdialog.getWindow().findViewById(R.id.photo_btn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                // 指定开启系统相机的Action
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addCategory(Intent.CATEGORY_DEFAULT);

                File cache = new File(Configer.SDCARD_DIR, Configer._CACHE_);

                final File file = new File(cache, "icon.jpg");
                if (file.exists()) {
                    file.delete();
                }
                photoUri = Uri.fromFile(file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, 1);
                camdialog.cancel();
            }
        });
        camdialog.getWindow().findViewById(R.id.carmera_btn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// 调用android的图库
                startActivityForResult(i, 2);
                camdialog.cancel();
            }
        });
        camdialog.getWindow().findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                camdialog.cancel();
            }
        });

    }

}