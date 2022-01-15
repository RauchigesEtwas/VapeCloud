package de.vapecloud.driver.events;

/*
 * Projectname: VapeCloud
 * Created AT: 15.01.2022/11:19
 * Created by Robin B. (RauchigesEtwas)
 */


import de.vapecloud.driver.events.bin.EventListener;
import de.vapecloud.driver.events.bin.EventProvider;
import de.vapecloud.driver.events.bin.IEvent;

import java.lang.reflect.Method;
import java.util.*;

public class EventExecutor {
    public static final int PRE = -1;
    public static final int ALL = 0;
    public static final int POST = 1;

    private final Map<Class<? extends IEvent>, Collection<EventHandler>> bindings;
    private final Set<de.vapecloud.driver.events.bin.EventListener> registeredListeners;

    private boolean debug = false;
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public EventExecutor() {
        this.bindings = new HashMap<Class<? extends IEvent>, Collection<EventHandler>>();
        this.registeredListeners = new HashSet<de.vapecloud.driver.events.bin.EventListener>();
    }

    public List<EventHandler> getListenersFor(Class<? extends IEvent> clazz) {
        if (!this.bindings.containsKey(clazz))
            return new ArrayList<EventHandler>(); // No handlers so we return an empty list
        return new ArrayList<EventHandler>(this.bindings.get(clazz));
    }

    public <T extends IEvent> T executeEvent(T event, int i) {
        Collection<EventHandler> handlers = this.bindings.get(event.getClass());
        if (handlers == null) {
            return event;
        }
        for (EventHandler handler : handlers) {
             if (i == PRE && handler.getPriority() >= 0)
                continue;
            if (i == POST && handler.getPriority() < 0)
                continue;
            handler.execute(event);
        }
        return event;
    }
    public <T extends IEvent> T executeEvent(T event) {
        return this.executeEvent(event, ALL);
    }

    public void registerListener(final de.vapecloud.driver.events.bin.EventListener listener) {


        if (registeredListeners.contains(listener)) {
            return;
        }

        Method[] methods = listener.getClass().getDeclaredMethods();
        this.registeredListeners.add(listener);
        for (final Method method : methods) {
            EventProvider annotation = method.getAnnotation(EventProvider.class);
            if (annotation == null)
                continue;

            Class<?>[] parameters = method.getParameterTypes();
            if (parameters.length != 1) // all listener methods should only have one parameter
                continue;

            Class<?> param = parameters[0];

            if (!method.getReturnType().equals(void.class)) {
                continue;
            }

            if (IEvent.class.isAssignableFrom(param)) {
                @SuppressWarnings("unchecked") // Java just doesn't understand that this actually is a safe cast because of the above if-statement
                Class<? extends IEvent> realParam = (Class<? extends IEvent>) param;

                if (!this.bindings.containsKey(realParam)) {
                    this.bindings.put(realParam, new TreeSet<EventHandler>());
                }
                Collection<EventHandler> eventHandlersForEvent = this.bindings.get(realParam);
                eventHandlersForEvent.add(createEventHandler(listener, method, annotation));
            }
        }
    }

    private EventHandler createEventHandler(final de.vapecloud.driver.events.bin.EventListener listener, final Method method, final EventProvider annotation) {
        return new EventHandler(listener, method, annotation);
    }

    public void clearListeners() {
        this.bindings.clear();
        this.registeredListeners.clear();
    }

    public void removeListener(de.vapecloud.driver.events.bin.EventListener listener) {
        for (Map.Entry<Class<? extends IEvent>, Collection<EventHandler>> ee : bindings.entrySet()) {
            Iterator<EventHandler> it = ee.getValue().iterator();
            while (it.hasNext()) {
                EventHandler curr = it.next();
                if (curr.getListener() == listener)
                    it.remove();
            }
        }
        this.registeredListeners.remove(listener);
    }
    public Map<Class<? extends IEvent>, Collection<EventHandler>> getBindings() {
        return new HashMap<Class<? extends IEvent>, Collection<EventHandler>>(bindings);
    }
    public Set<de.vapecloud.driver.events.bin.EventListener> getRegisteredListeners() {
        return new HashSet<EventListener>(registeredListeners);
    }
}
