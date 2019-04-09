package by.epam.javaweb.evgeniyyaskevich.task2.entity;

public class Leaf extends Component {
    private String data;

    public Leaf(String data, RecordType recordType) {
        super(recordType);
        this.data = data;
    }

    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException("Leaf doesn`t support this operation.");
    }

    @Override
    public void remove(Component component) {
        throw new UnsupportedOperationException("Leaf doesn`t support this operation.");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Leaf)) {
            return false;
        } else {
            Leaf leaf = (Leaf) obj;
            return this.data.equals(leaf.data);
        }
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return data;
    }
}
