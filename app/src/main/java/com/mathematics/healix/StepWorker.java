package com.mathematics.healix;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class StepWorker extends Worker {

    public StepWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d("StepWorker", "Saving step data: " + StepCounterService.stepsSinceBoot);
        return Result.success();
    }
}