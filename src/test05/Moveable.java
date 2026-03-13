package test05;

public interface Moveable {
    public abstract void left();
    public abstract void right();
    public abstract void up();
    public abstract void down();

    //Adapter 클래스가 너무 많이 생겨서 default문법을
    //인터페이스 측에서
    default void main(){

    }
}
