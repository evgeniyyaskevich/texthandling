package by.epam.javaweb.evgeniyyaskevich.task2.entity;

import java.util.List;

public abstract class Component {
    private RecordType recordType;

    protected Component(RecordType recordType) {
        this.recordType = recordType;
    }

    public abstract void add(Component component);
    public abstract void remove(Component component);
    public void add(int index, Component component) {
        throw new UnsupportedOperationException();
    }

    public List<Component> getListOfChildren() {
        return null;
    }

    public RecordType getRecordType() {
        return recordType;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
