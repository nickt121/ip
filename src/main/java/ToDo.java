public class ToDo extends Task{
    public ToDo(String description){
        super(description);
    }

    @Override
    public String toString(){
        return super.toString();
    }

    @Override
    public String getIcon(){
        return "[T]";
    }
}
