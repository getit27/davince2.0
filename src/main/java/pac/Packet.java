package pac;
public abstract class Packet {
    protected Class<?> pactype;
    public Packet(){}
    abstract public Packet addAll(Packet pac) throws Exception;
    public Class<?> getPacType(){
        return pactype;
    }
}