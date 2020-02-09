// 从数据库中取数据

package acc;

import pac.inter.Accessable;

public interface Accesser {
    public Class<?> getPacType();
    public Accessable getPackage();
}
