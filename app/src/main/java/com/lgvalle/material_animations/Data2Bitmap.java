package com.lgvalle.material_animations;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.DataInputStream;
import java.io.InputStream;

/**
 * Created by lshq on 2017/9/4.
 */

public class Data2Bitmap {
    final private String TAG = "Data2Bitmap";
    private Bitmap bitmap;
    private Context context;
    private int [][] randData = null;
    private int [][] histoData = null;
    public final int HISTO_WIDTH = 512;
    public final int HISTO_HEIGHT = 512;

    public Data2Bitmap(Context context)
    {
        this.context = context;
        initBitmap();
        initData();
    }
    protected void initBitmap()
    {
        bitmap = Bitmap.createBitmap(HISTO_HEIGHT,HISTO_WIDTH, Bitmap.Config.ARGB_8888);
    }
    protected void initData()
    {
        histoData = new int[HISTO_HEIGHT][HISTO_WIDTH];
        randData = new int[HISTO_HEIGHT][HISTO_WIDTH];
    }
    public void loadData(int id)
    {
        byte buffer[] = new byte[4];
        InputStream is =  context.getResources().openRawResource(id);
        DataInputStream dataInputStream = new DataInputStream(is);
        try {
            for (int i = 0; i < HISTO_HEIGHT; i++) {
                for (int j = 0; j < HISTO_WIDTH; j++) {
                    for(int k=0;k<4;k++)
                    {
                        buffer[k] = dataInputStream.readByte();
                    }
                    histoData[i][j]=buffer[0];
                }
            }
        }
        catch (java.io.IOException e)
        {
            Log.e(TAG, "loadReconParam: "+e.toString());
        }
    }
    protected int getMax(int [][]arr)
    {
        int maxVal = arr[0][0];
        for(int i=0;i<HISTO_HEIGHT;i++)
            for (int j=0;j<HISTO_WIDTH;j++)
            {
                maxVal = (maxVal>arr[i][j])?maxVal:arr[i][j];
            }
        return maxVal;
    }
    protected void fillPixelWithData(int [][]arr,int maxVal)
    {
        for(int i=0;i<HISTO_HEIGHT;i++)
            for (int j=0;j<HISTO_WIDTH;j++)
            {
                int val = arr[i][j];
                int index = 255*val/maxVal;
                int color = 0xFF000000 + (index<<16)+(index<<8)+index;
                bitmap.setPixel(i,j,color);
            }
    }
    public Bitmap generateBitmap()
    {
        int maxVal = getMax(histoData);
        fillPixelWithData(histoData,maxVal);
        return bitmap;
    }
}
