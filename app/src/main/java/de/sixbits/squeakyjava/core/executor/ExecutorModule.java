package de.sixbits.squeakyjava.core.executor;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import de.sixbits.reactive.executor.PostExecutionThread;
import de.sixbits.reactive.executor.ThreadExecutor;

@Module
@InstallIn(SingletonComponent.class)
public abstract class ExecutorModule {
    @Binds
    abstract ThreadExecutor bindThreadExecutor(JobExecutor jobExecutor);

    @Binds
    abstract PostExecutionThread bindPostExecutionThread(UIThread uiThread);
}
