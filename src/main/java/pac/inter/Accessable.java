// 能被从数据库中取出

package pac.inter;

import java.sql.ResultSet;

public interface Accessable {
    public boolean isAccessed();
    public void getAccessed();
    public String getTableName();
    public String getColumn(int index);
    public int getColumnLength();
    public void accImportData(ResultSet rs) throws Exception;
}
