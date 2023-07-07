package chasm.chasmpvp.objects;

public class StorageObject<T> {

    private final T identifier;

    public StorageObject(T identifier) {
        this.identifier = identifier;
    }

    public T getIdentifier() {
        return identifier;
    }
}