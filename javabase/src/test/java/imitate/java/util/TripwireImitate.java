package imitate.java.util;

import sun.util.logging.PlatformLogger;

import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * user: xiangyu.wang
 * date: 2018/5/4 17:21
 */
final class TripwireImitate {

    private static final String TRIPWIRE_PROPERTY_IM = "org.openjdk.java.util.stream.tripwire";

    static final boolean ENABLED = AccessController.doPrivileged(
            (PrivilegedAction<Boolean>) () -> Boolean.getBoolean(TRIPWIRE_PROPERTY_IM));

    private TripwireImitate(){}

    static void trip(Class<?> trippingClass, String msg){
        PlatformLogger.getLogger(trippingClass.getName()).warning(msg, trippingClass.getName());
    }
}
