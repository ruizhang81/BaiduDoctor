package com.baidu.doctor.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.baidu.doctor.R;

import java.util.ArrayList;

public abstract class BodyView extends RelativeLayout {

    private final static SparseArray<String> bodyData = new SparseArray<String>();

    static{
        bodyData.append(R.id.intelligence_highlight_0100, "-1");
        bodyData.append(R.id.intelligence_highlight_0101, "男额头");//额头
        bodyData.append(R.id.intelligence_highlight_0102, "男眼睛");//眼睛
        bodyData.append(R.id.intelligence_highlight_0103, "男鼻子");//鼻子
        bodyData.append(R.id.intelligence_highlight_male_0104, "男嘴");//嘴
        bodyData.append(R.id.intelligence_highlight_0105, "男耳朵");//耳朵
        bodyData.append(R.id.intelligence_highlight_0106, "男脸");//脸
        bodyData.append(R.id.intelligence_highlight_0107, "男脖子");//脖子
        bodyData.append(R.id.intelligence_highlight_0108, "男手臂");//手臂
        bodyData.append(R.id.intelligence_highlight_0109, "男手");//手
        bodyData.append(R.id.intelligence_highlight_0110, "男胸");//胸
        bodyData.append(R.id.intelligence_highlight_0111, "男腹部");//腹部
        bodyData.append(R.id.intelligence_highlight_0112, "男背部");//背部
        bodyData.append(R.id.intelligence_highlight_0113, "男腰部");//腰部
        bodyData.append(R.id.intelligence_highlight_0114, "男屁股");//屁股
        bodyData.append(R.id.intelligence_highlight_0115, "男生殖");//生殖
        bodyData.append(R.id.intelligence_highlight_0116, "男腿");//腿
        bodyData.append(R.id.intelligence_highlight_0117, "男脚");//脚
        bodyData.append(R.id.intelligence_highlight_0200, "-2");
        bodyData.append(R.id.intelligence_highlight_0201, "女额头");//额头
        bodyData.append(R.id.intelligence_highlight_0202, "女眼睛");//眼睛
        bodyData.append(R.id.intelligence_highlight_0203, "女鼻子");//鼻子
        bodyData.append(R.id.intelligence_highlight_0204, "女嘴");//嘴
        bodyData.append(R.id.intelligence_highlight_0205, "女耳朵");//耳朵
        bodyData.append(R.id.intelligence_highlight_0206, "女脸");//脸
        bodyData.append(R.id.intelligence_highlight_0207, "女脖子");//脖子
        bodyData.append(R.id.intelligence_highlight_0208, "女手臂");//手臂
        bodyData.append(R.id.intelligence_highlight_0209, "女手");//手
        bodyData.append(R.id.intelligence_highlight_0210, "女胸");//胸
        bodyData.append(R.id.intelligence_highlight_0211, "女腹部");//腹部
        bodyData.append(R.id.intelligence_highlight_0212, "女背部");//背部
        bodyData.append(R.id.intelligence_highlight_0213, "女腰部");//腰部
        bodyData.append(R.id.intelligence_highlight_0214, "女屁股");//屁股
        bodyData.append(R.id.intelligence_highlight_0215, "女生殖");//生殖
        bodyData.append(R.id.intelligence_highlight_0216, "女腿");//腿
        bodyData.append(R.id.intelligence_highlight_0217, "女脚");//脚
    }

    private boolean hasCompute;
    private ArrayList<ImageView> imageViews;
    private ArrayList<ImageInfo> imageInfos;
    private ImageView bgImageView;
    private RelativeLayout body_root;
    private ImageInfo bgImageInfos;
    private int triggerIndex = -1;
    private int rootPostionX;
    private int rootPostionY;

    public BodyView(Context context) {
        super(context);
        superInit();
    }

    public BodyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        superInit();
    }

    private OnClickBodyPartListener mListener;

    public interface OnClickBodyPartListener {
        void onClickBodyPart(String bodyPartId);
    }

    public void setOnClickBodyPartListener(OnClickBodyPartListener listener) {
        mListener = listener;
    }

    public abstract void init();

    private void superInit() {
        init();
        imageViews = new ArrayList<ImageView>();
        body_root = (RelativeLayout) findViewById(R.id.body_root);
        int size = body_root.getChildCount();
        for (int i = 0; i < size; i++) {
            View view = body_root.getChildAt(i);
            if (view.getId() != R.id.imageView) {
                imageViews.add((ImageView) view);
            }
        }
        setGone(-1);

        bgImageView = (ImageView) findViewById(R.id.imageView);
        body_root.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        which(event.getX(), event.getY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        which(event.getX(), event.getY());
                        break;
                    case MotionEvent.ACTION_UP:
                        which(event.getX(), event.getY());
                        if (triggerIndex != -1) {
                            String bodyPartId = bodyData.get(triggerIndex);
                            if (mListener != null) {
                                mListener.onClickBodyPart(bodyPartId);
                            }
                        }
                        triggerIndex = -1;
                        setGone(-1);
                        break;
                }
                return true;
            }
        });
    }

    private void setGone(int redId) {
        for (ImageView imageView : imageViews) {
            if (imageView.getId() == redId) {
                imageView.setAlpha(255);
            } else {
                imageView.setAlpha(0);
            }
        }
    }

    public void which(float x, float y) {
        compute();
        int size = imageInfos.size();
        x = x + rootPostionX;
        y = y + rootPostionY;

//        Logg.e("", "x=" + x + " y=" + y);
        for (int i = 0; i < size; i++) {
            ImageInfo imageInfo = imageInfos.get(i);

//            Logg.e("", "imageInfo.leftAbsolute=" + imageInfo.leftAbsolute + "  imageInfo.rightAbsolute=" + imageInfo.rightAbsolute + "  imageInfo.topAbsolute=" + imageInfo.topAbsolute + " imageInfo.bottomAbsolute=" + imageInfo.bottomAbsolute);
//            Logg.e("", "bgImageInfos.leftAbsolute=" + bgImageInfos.leftAbsolute + "  bgImageInfos.rightAbsolute=" + bgImageInfos.rightAbsolute + "  bgImageInfos.topAbsolute=" + bgImageInfos.topAbsolute + " bgImageInfos.bottomAbsolute=" + bgImageInfos.bottomAbsolute);

            if ((int) x <= imageInfo.leftAbsolute || (int) x >= imageInfo.rightAbsolute || (int) y <= imageInfo.topAbsolute || (int) y >= imageInfo.bottomAbsolute) {
                //不在范围中
//                Logg.e("", "i=" + i + " out1");
            } else {
                int color = imageInfo.bitmap.getPixel((int) x - imageInfo.leftAbsolute, (int) y - imageInfo.topAbsolute);
                if (color != 0) {
                    triggerIndex = imageInfo.id;
                    break;
                } else {
                    if ((int) x <= bgImageInfos.leftAbsolute || (int) x >= bgImageInfos.rightAbsolute || (int) y <= bgImageInfos.topAbsolute || (int) y >= bgImageInfos.bottomAbsolute) {
                        triggerIndex = -1;
                        break;
                    } else {
                        int bgColor = bgImageInfos.bitmap.getPixel((int) x - bgImageInfos.leftAbsolute, (int) y - bgImageInfos.topAbsolute);
                        if (bgColor != 0) {
                            triggerIndex = imageInfo.id;
                            continue;
                        } else {
                            triggerIndex = -1;
                            break;
                        }
                    }
                }
            }
        }
        setGone(triggerIndex);

    }

    private class ImageInfo {
        Bitmap bitmap;
        int leftAbsolute;
        int rightAbsolute;
        int topAbsolute;
        int bottomAbsolute;
        int id;

        public ImageInfo() {
        }

        public ImageInfo(ImageView imageView, int id) {
            this.id = id;
            Drawable drawable = imageView.getDrawable();
            int width = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            int height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            imageView.measure(width, height);
            width = imageView.getMeasuredWidth();
            height = imageView.getMeasuredHeight();

            int[] location = new int[2];
            imageView.getLocationOnScreen(location);
            int postionX = location[0];
            int postionY = location[1];
            leftAbsolute = postionX;
            rightAbsolute = postionX + width;
            topAbsolute = postionY;
            bottomAbsolute = postionY + height;
            bitmap = ((BitmapDrawable) drawable).getBitmap();

//            Log.e("xxx", "tag=" + id + " leftAbsolute=" + leftAbsolute + " rightAbsolute=" + rightAbsolute +
//                    " topAbsolute=" + topAbsolute + " bottomAbsolute=" + bottomAbsolute);
        }
    }

    private void compute() {
        if (!hasCompute) {
            int[] rootLocation = new int[2];
            body_root.getLocationOnScreen(rootLocation);
            rootPostionX = rootLocation[0];
            rootPostionY = rootLocation[1];

            //init parts
            imageInfos = new ArrayList<ImageInfo>();
            int size = imageViews.size();
            for (int i = 0; i < size; i++) {
                imageInfos.add(new ImageInfo(imageViews.get(i), imageViews.get(i).getId()));
            }

            //init body
            bgImageInfos = new ImageInfo();
            int[] bgLocation = new int[2];
            bgImageView.getLocationOnScreen(bgLocation);
            int bgPostionX = bgLocation[0];
            int bgPostionY = bgLocation[1];

            Drawable bgDrawable = bgImageView.getDrawable();
            int width = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            int height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            bgImageView.measure(width, height);
            width = bgImageView.getMeasuredWidth();
            height = bgImageView.getMeasuredHeight();
            bgImageInfos.leftAbsolute = bgPostionX;
            bgImageInfos.rightAbsolute = bgPostionX + width;
            bgImageInfos.topAbsolute = bgPostionY;
            bgImageInfos.bottomAbsolute = bgPostionY + height;
            bgImageInfos.id = -1;
//            Log.e("xxx", "bg tag=" + bgImageInfos.id + " leftAbsolute=" + bgImageInfos.leftAbsolute
//                    + " rightAbsolute=" + bgImageInfos.rightAbsolute +
//                    " topAbsolute=" + bgImageInfos.topAbsolute +
//                    " bottomAbsolute=" + bgImageInfos.bottomAbsolute);
            bgImageInfos.bitmap = ((BitmapDrawable) bgDrawable).getBitmap();

            hasCompute = true;
        }

    }
}
