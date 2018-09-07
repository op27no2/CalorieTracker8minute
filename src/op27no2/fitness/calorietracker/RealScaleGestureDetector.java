package op27no2.fitness.calorietracker;
 
 
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.ScaleGestureDetector;

@SuppressLint("NewApi")
public class RealScaleGestureDetector extends ScaleGestureDetector {
	public RealScaleGestureDetector(Context context, final op27no2.fitness.calorietracker.ScaleGestureDetector fakeScaleGestureDetector, final op27no2.fitness.calorietracker.ScaleGestureDetector.SimpleOnScaleGestureListener fakeListener) {
		super(context, new android.view.ScaleGestureDetector.SimpleOnScaleGestureListener() {
			@Override
			public boolean onScale(ScaleGestureDetector detector) {
				return fakeListener.onScale(fakeScaleGestureDetector);
			}
		});
	}
}