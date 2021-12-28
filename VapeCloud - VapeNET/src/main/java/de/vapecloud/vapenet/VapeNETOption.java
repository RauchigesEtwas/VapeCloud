package de.vapecloud.vapenet;

/*
 * Projectname: VapeCloud
 * Created AT: 28.12.2021/17:36
 * Created by Robin B. (RauchigesEtwas)
 */

public class VapeNETOption<T> {


    public static final VapeNETOption<Integer> TIMEOUT = new VapeNETOption<>(-1);
    public static final VapeNETOption<Integer> BUFFER_SIZE = new VapeNETOption<>(1024);
    public static final VapeNETOption<Boolean> DENNY_NIO = new VapeNETOption<>(true);
    public static final VapeNETOption<Boolean> TCP_DELAY = new VapeNETOption<>(false);
    public static final VapeNETOption<Boolean> KEEPALIVE = new VapeNETOption<>(true);
    public static final VapeNETOption<Boolean> AUTOKRECONNECT = new VapeNETOption<>(false);

    protected T value;

    protected VapeNETOption(T value) {
        this.value = value;
    }

    protected T getValue() {
        return value;
    }

    @SuppressWarnings("unchecked")
    protected void setValue(Object value) {
        this.value = (T) value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
