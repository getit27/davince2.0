package pac.inter;

public interface Storable {
    public Class<?> getPacType();
    public String getTableName();
    public String[] getColumns();
    public String[] getComments();
    public String[] getPrimaryKeys();
    public int[]getNpLocation();
    public int[]getPrimaryLocation();
    public Class<?>[] getType();
    public Object[][] getStoreData();
}
