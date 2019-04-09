package by.epam.javaweb.evgeniyyaskevich.task2.entity;

import java.util.LinkedList;
import java.util.List;

public class Composite extends Component {
    private List<Component> components = new LinkedList<>();

    public Composite(RecordType recordType) {
        super(recordType);
    }

    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void add(int index, Component component) {
        components.add(index, component);
    }

    @Override
    public void remove(Component component) {
        components.remove(component);
    }

    public List<Component> getListOfChildren() {
        return components;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Composite)) {
            return false;
        } else {
            Composite composite = (Composite) obj;
            return this.components.equals(composite.components);
        }
    }
}
