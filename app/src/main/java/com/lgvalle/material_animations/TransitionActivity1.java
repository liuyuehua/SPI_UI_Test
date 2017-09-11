package com.lgvalle.material_animations;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import tech.linjiang.suitlines.SuitLines;
import tech.linjiang.suitlines.Unit;

public class TransitionActivity1 extends Activity {

    SuitLines suitLines;

    private Data2Bitmap data2Bitmap;
    private ImageView imageView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition1);
        suitLines = (SuitLines) findViewById(R.id.suitlines);

        imageView = (ImageView)findViewById(R.id.image_view);
        data2Bitmap = new Data2Bitmap(this);
        data2Bitmap.loadData(R.raw.histogram);
        imageView.setImageBitmap(data2Bitmap.generateBitmap());
    }

    public void onBtnClick(View view) {
        suitLines.anim();
    }

    private boolean enable;
    public void onBtnClick1(View view) {
        suitLines.setCoverLine(enable = !enable);
    }

    public void onBtnClick13(View view) {
        int[] colors = new int[2];
        colors[0] = color[new SecureRandom().nextInt(7)];
        colors[1] = Color.WHITE;
        suitLines.setDefaultOneLineColor(colors);
    }

    private int curCount = 1;

    public void onBtnClick2(View view) {
        suitLines.setXySize(textSize = 8);
        init(curCount = 1);
    }

    public void onBtnClick3(View view) {
        init(++curCount);
    }

    public void onBtnClick4(View view) {
        if (curCount <= 1) {
            curCount = 1;
        }
        init(--curCount);
    }

    public void onBtnClick5(View view) {
        suitLines.setLineForm(!suitLines.isLineFill());
    }


    public void onBtnClick6(View view) {
        suitLines.setLineStyle(suitLines.isLineDashed()?SuitLines.SOLID:SuitLines.DASHED);
    }

    public void onBtnClick7(View view) {
        suitLines.setLineType(suitLines.getLineType() == SuitLines.CURVE ? SuitLines.SEGMENT : SuitLines.CURVE);
    }

    public void onBtnClick8(View view) {
        suitLines.disableEdgeEffect();
    }

    public void onBtnClick9(View view) {
        suitLines.setEdgeEffectColor(color[new SecureRandom().nextInt(7)]);
    }

    public void onBtnClick10(View view) {
        suitLines.setXyColor(color[new SecureRandom().nextInt(7)]);
    }

    private float textSize = 8;

    public void onBtnClick11(View view) {
        suitLines.setXySize(++textSize);
    }

    public void onBtnClick12(View view) {
        if (textSize < 6) {
            textSize = 6;
        }
        suitLines.setXySize(--textSize);
    }
    public void onBtnClick14(View view) {
        suitLines.disableClickHint();
    }
    public void onBtnClick15(View view) {
        suitLines.setHintColor(color[new SecureRandom().nextInt(7)]);
    }

    private int[] color = {Color.RED, Color.GRAY, Color.BLACK, Color.BLUE, 0xFFF76055, 0xFF9B3655, 0xFFF7A055};

    public void init(int count) {
        if (count <= 0) {
            count = 0;
        }
        if (count == 1) {
            List<Unit> lines = new ArrayList<>();
            for (int i = 0; i < 14; i++) {
                lines.add(new Unit(new SecureRandom().nextInt(48), i + "d"));
            }
            suitLines.feedWithAnim(lines);
            return;
        }

        SuitLines.LineBuilder builder = new SuitLines.LineBuilder();
        for (int j = 0; j < count; j++) {
            List<Unit> lines = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                lines.add(new Unit(new SecureRandom().nextInt(128), "" + i));
            }
            builder.add(lines, new int[]{color[new SecureRandom().nextInt(7)], Color.WHITE});
        }
        builder.build(suitLines, true);

    }
}


//    private void setupWindowAnimations() {
//        Visibility enterTransition = buildEnterTransition();
//        getWindow().setEnterTransition(enterTransition);
//    }


//    private void setupLayout() {
////        findViewById(R.id.sample1_button1).setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent i = new Intent(TransitionActivity1.this, TransitionActivity2.class);
////                i.putExtra(EXTRA_SAMPLE, sample);
////                i.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
////                transitionTo(i);
////            }
////        });
////
////        findViewById(R.id.sample1_button2).setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent i = new Intent(TransitionActivity1.this, TransitionActivity2.class);
////                i.putExtra(EXTRA_SAMPLE, sample);
////                i.putExtra(EXTRA_TYPE, TYPE_XML);
////                transitionTo(i);
////            }
////        });
////
////        findViewById(R.id.sample1_button3).setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent i = new Intent(TransitionActivity1.this, TransitionActivity3.class);
////                i.putExtra(EXTRA_SAMPLE, sample);
////                i.putExtra(EXTRA_TYPE, TYPE_PROGRAMMATICALLY);
////                transitionTo(i);
////            }
////        });
////
////        findViewById(R.id.sample1_button4).setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent i = new Intent(TransitionActivity1.this, TransitionActivity3.class);
////                i.putExtra(EXTRA_SAMPLE, sample);
////                i.putExtra(EXTRA_TYPE, TYPE_XML);
////                transitionTo(i);
////            }
////        });
////
////        findViewById(R.id.sample1_button5).setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Visibility returnTransition = buildReturnTransition();
////                getWindow().setReturnTransition(returnTransition);
////
////                finishAfterTransition();
////            }
////        });
////        findViewById(R.id.sample1_button6).setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                /**
////                 * If no return transition is defined Android will use reversed enter transition
////                 * In this case, return transition will be a reversed Slide (defined in buildEnterTransition)
////                 */
////                finishAfterTransition();
////            }
////        });
////    }
//
////    private Visibility buildEnterTransition() {
////        Fade enterTransition = new Fade();
////        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
////        // This view will not be affected by enter transition animation
////        enterTransition.excludeTarget(R.id.square_red, true);
////        return enterTransition;
////    }
////
////    private Visibility buildReturnTransition() {
////        Visibility enterTransition = new Slide();
////        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
////        return enterTransition;
////    }
//}
