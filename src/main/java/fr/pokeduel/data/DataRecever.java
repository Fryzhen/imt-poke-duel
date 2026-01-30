package fr.pokeduel.data;

public class DataRecever<T> {
    private T data;

    public DataRecever(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

}
