package app.Complexity;

public class EntityClass implements Comparable<EntityClass> {
    private int id;

    public EntityClass(int id){
        this.id = id;
    }

    @Override
    public int compareTo(EntityClass o) {
        return Integer.compare(this.id, o.id);
    }

    @Override
    public boolean  equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityClass that = (EntityClass) o;
        return  id == that.id;
    }

    @Override
    public int hashCode(){
        return Integer.hashCode(id);
    }
}
