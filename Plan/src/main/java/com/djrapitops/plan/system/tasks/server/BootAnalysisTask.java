package com.djrapitops.plan.system.tasks.server;

import com.djrapitops.plan.PlanPlugin;
import com.djrapitops.plan.system.info.InfoSystem;
import com.djrapitops.plan.system.info.connection.WebExceptionLogger;
import com.djrapitops.plan.system.info.request.GenerateAnalysisPageRequest;
import com.djrapitops.plan.system.info.server.ServerInfo;
import com.djrapitops.plugin.api.utility.log.Log;
import com.djrapitops.plugin.task.AbsRunnable;

public class BootAnalysisTask extends AbsRunnable {

    @Override
    public void run() {
        try {
            WebExceptionLogger.logIfOccurs(this.getClass(), () ->
                    InfoSystem.getInstance().sendRequest(new GenerateAnalysisPageRequest(ServerInfo.getServerUUID_Old()))
            );
        } catch (IllegalStateException e) {
            if (!PlanPlugin.getInstance().isReloading()) {
                Log.toLog(this.getClass(), e);
            }
        } finally {
            cancel();
        }
    }
}
