package org.acme;

import io.smallrye.reactive.messaging.annotations.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;

@ApplicationScoped
public class MyMessagingApplication {

    @OnOverflow(value = OnOverflow.Strategy.FAIL)
    @Channel("run-upload-out")
    Emitter<Integer> runUploadEmitter;

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public void sendMsg(Integer msg) {
        runUploadEmitter.send(msg);
    }


    @Incoming("run-upload-in")
    @Blocking(ordered = false)
    public void toUpperCase(Integer message) {
        System.out.println(">> " + message);
    }

}
