// 从数据库中取数据

package acc;

import pac.inter.Accessible;

public interface Accesser {
    public Class<?> getPacType();
    public Accessible getPackage();
}
