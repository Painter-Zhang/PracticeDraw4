package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice12CameraRotateFixedView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);
    Camera camera = new Camera();

    public Practice12CameraRotateFixedView(Context context) {
        super(context);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        camera.save();
        int offsetX = point1.x + bitmap.getWidth() / 2;
        int offsetY = point1.y + bitmap.getHeight() / 2;
        canvas.translate(offsetX, offsetY);
        camera.rotateX(30);
        camera.applyToCanvas(canvas);
        canvas.drawBitmap(bitmap, -bitmap.getWidth() / 2, -bitmap.getHeight() / 2, paint);
        canvas.translate(-offsetX, -offsetY);

        camera.restore();
        canvas.restore();

        /*-----------------------------------*/
        int offsetX2 = point2.x + bitmap.getWidth() / 2;
        int offsetY2 = point2.y + bitmap.getHeight() / 2;

        canvas.save();
        camera.save();
        Matrix matrix = new Matrix();
        camera.rotateY(30);
        camera.getMatrix(matrix);
        matrix.preTranslate(-offsetX2, -offsetY2);
        matrix.postTranslate(offsetX2, offsetY2);
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        camera.restore();
        canvas.restore();
    }
}
