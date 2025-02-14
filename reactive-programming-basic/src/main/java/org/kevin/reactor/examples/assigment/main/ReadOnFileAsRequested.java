package org.kevin.reactor.examples.assigment.main;

import lombok.extern.slf4j.Slf4j;
import org.kevin.reactor.examples.assigment.fileoperations.FileReaderServiceImpl;
import org.kevin.reactor.examples.common.Util;
import org.kevin.reactor.examples.fluxcreate.subscriber.SubscriberImpl;

import java.nio.file.Path;

@Slf4j
public class ReadOnFileAsRequested {
    public static void main(String[] args) {
        Path PATH = Path.of("src/main/resources/testfile/file.txt");


//        var subscriber = new SubscriberImpl();
//        FileReaderServiceImpl service = new FileReaderServiceImpl();
//
//        service.read(PATH)
//                .subscribe(subscriber);

//        Util.sleepSeconds(2);
//        subscriber.getSubscription().request(4);
//        subscriber.getSubscription().cancel();

        FileReaderServiceImpl service = new FileReaderServiceImpl();

        service.read(PATH)
                .take(6)  //takeUntil(s ->s.equals("line10"))
                .subscribe(Util.subscriber());
    }
}
