package pac;
public abstract class Packages{
    protected Class<?> pactype;
    public Packages(){}
    abstract public Packages addAll(Packages pac) throws Exception;
    public Class<?> getPacType(){
        return pactype;
    }
}