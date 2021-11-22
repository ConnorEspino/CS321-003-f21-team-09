package cs321.btree;

import java.util.Objects;

public class TreeObject<E> {
    E key;
    int pos;
    public TreeObject<E> left;
    public TreeObject<E> right;
    public TreeObject<E> parent;

    public TreeObject(E key1, int pos1) {
        key = key1;
        pos = pos1;
        left = null;
        right = null;
        parent = null;
    }

    public E getKey(){
        return this.key;
    }

    public int equal(E o) {
        if (this.key.hashCode() == o.hashCode()) return 0;
        if (o.hashCode() > this.key.hashCode()) return -1;
        if (o.hashCode() < this.key.hashCode()) return 1;
        return 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }


}
